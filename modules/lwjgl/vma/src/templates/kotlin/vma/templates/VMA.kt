/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package vma.templates

import org.lwjgl.generator.*
import vma.*
import vulkan.*

val VMA = "Vma".nativeClass(Module.VMA, "Vma", prefix = "VMA") {
    nativeDirective(
        """#include "lwjgl_malloc.h"
DISABLE_WARNINGS()
#ifdef __clang__
    _Pragma("GCC diagnostic ignored \"-Wnullability-completeness\"")
#endif
#define VMA_IMPLEMENTATION
#define VMA_STATIC_VULKAN_FUNCTIONS 0
#define VMA_DYNAMIC_VULKAN_FUNCTIONS 0
#define VMA_SYSTEM_ALIGNED_MALLOC(size, alignment) org_lwjgl_aligned_alloc((alignment), (size))
#define VMA_SYSTEM_ALIGNED_FREE(ptr) org_lwjgl_aligned_free(ptr)
#define VMA_VULKAN_VERSION 1003000
#define VMA_DEDICATED_ALLOCATION 1
#define VMA_BIND_MEMORY2 1
#define VMA_MEMORY_BUDGET 1
#define VMA_BUFFER_DEVICE_ADDRESS 1
#define VMA_MEMORY_PRIORITY 1
#define VMA_EXTERNAL_MEMORY 1
#define VMA_KHR_MAINTENANCE4 1
#define VMA_KHR_MAINTENANCE5 1
#include "vk_mem_alloc.h"
ENABLE_WARNINGS()"""
    )
    javaImport("org.lwjgl.vulkan.*")

    cpp = true
    documentation =
        """
        The Vulkan Memory Allocator.

        <h3>Quick start</h3>

        <h4>Initialization</h4>

        VMA offers library interface in a style similar to Vulkan, with object handles like {@code VmaAllocation}, structures describing parameters of objects
        to be created like {@code VmaAllocationCreateInfo}, and errors codes returned from functions using {@code VkResult} type.

        The first and the main object that needs to be created is {@code VmaAllocator}. It represents the initialization of the entire library. Only one such
        object should be created per {@code VkDevice}. You should create it at program startup, after {@code VkDevice} was created, and before any device
        memory allocator needs to be made. It must be destroyed before {@code VkDevice} is destroyed.

        At program startup:

        ${ol(
            "Initialize Vulkan to have {@code VkInstance}, {@code VkPhysicalDevice}, {@code VkDevice} and {@code VkInstance} object.",
            "Fill ##VmaAllocatorCreateInfo structure and call #CreateAllocator() to create {@code VmaAllocator} object."
        )}

        ${codeBlock(
            """
VmaVulkanFunctions vulkanFunctions = {};
vulkanFunctions.vkGetInstanceProcAddr = &vkGetInstanceProcAddr;
vulkanFunctions.vkGetDeviceProcAddr = &vkGetDeviceProcAddr;

VmaAllocatorCreateInfo allocatorCreateInfo = {};
allocatorCreateInfo.flags = VMA_ALLOCATOR_CREATE_EXT_MEMORY_BUDGET_BIT;
allocatorCreateInfo.vulkanApiVersion = VK_API_VERSION_1_2;
allocatorCreateInfo.physicalDevice = physicalDevice;
allocatorCreateInfo.device = device;
allocatorCreateInfo.instance = instance;
allocatorCreateInfo.pVulkanFunctions = &vulkanFunctions;

VmaAllocator allocator;
vmaCreateAllocator(&allocatorCreateInfo, &allocator);

// Entire program...

// At the end, don't forget to:
vmaDestroyAllocator(allocator);"""
        )}

        Only members {@code physicalDevice}, {@code device}, {@code instance} are required. However, you should inform the library which Vulkan version do you
        use by setting {@code VmaAllocatorCreateInfo::vulkanApiVersion} and which extensions did you enable by setting {@code VmaAllocatorCreateInfo::flags}.
        Otherwise, VMA would use only features of Vulkan 1.0 core with no extensions. See below for details.

        <h4>Resource allocation</h4>

        When you want to create a buffer or image:
        ${ol(
            "Fill {@code VkBufferCreateInfo} / {@code VkImageCreateInfo} structure.",
            "Fill {@code VmaAllocationCreateInfo} structure.",
            """
            Call #CreateBuffer() / #CreateImage() to get {@code VkBuffer}/{@code VkImage} with memory already allocated and bound to it, plus
            {@code VmaAllocation} objects that represents its underlying memory.
            """
        )}

        ${codeBlock(
            """
VkBufferCreateInfo bufferInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufferInfo.size = 65536;
bufferInfo.usage = VK_BUFFER_USAGE_VERTEX_BUFFER_BIT | VK_BUFFER_USAGE_TRANSFER_DST_BIT;

VmaAllocationCreateInfo allocInfo = {};
allocInfo.usage = VMA_MEMORY_USAGE_AUTO;

VkBuffer buffer;
VmaAllocation allocation;
vmaCreateBuffer(allocator, &bufferInfo, &allocInfo, &buffer, &allocation, nullptr);"""
        )}

        Don't forget to destroy your buffer and allocation objects when no longer needed:

        ${codeBlock(
            """
vmaDestroyBuffer(allocator, buffer, allocation);
vmaDestroyAllocator(allocator);"""
        )}

        If you need to map the buffer, you must set flag #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT or #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT in
        {@code VmaAllocationCreateInfo::flags}. There are many additional parameters that can control the choice of memory type to be used for the allocation
        and other features.

        <h3>Choosing memory type</h3>

        Physical devices in Vulkan support various combinations of memory heaps and types. Help with choosing correct and optimal memory type for your specific
        resource is one of the key features of this library. You can use it by filling appropriate members of VmaAllocationCreateInfo structure, as described
        below. You can also combine multiple methods.
        ${ol(
            """
            If you just want to find memory type index that meets your requirements, you can use function: #FindMemoryTypeIndexForBufferInfo(), 
            #FindMemoryTypeIndexForImageInfo(), #FindMemoryTypeIndex().
            """,
            """
            If you want to allocate a region of device memory without association with any specific image or buffer, you can use function #AllocateMemory().
            Usage of this function is not recommended and usually not needed. #AllocateMemoryPages() function is also provided for creating multiple
            allocations at once, which may be useful for sparse binding.
            """,
            """
            If you already have a buffer or an image created, you want to allocate memory for it and then you will bind it yourself, you can use function
            #AllocateMemoryForBuffer(), #AllocateMemoryForImage(). For binding you should use functions: #BindBufferMemory(), #BindImageMemory() or their
            extended versions: #BindBufferMemory2(), #BindImageMemory2().
            """,
            """
            If you want to create a buffer or an image, allocate memory for it, and bind them together, all in one call, you can use function #CreateBuffer(),
            #CreateImage(). <b>This is the easiest and recommended way to use this library!</b>
            """
        )}
        When using 3. or 4., the library internally queries Vulkan for memory types supported for that buffer or image (function
        {@code vkGetBufferMemoryRequirements()}) and uses only one of these types.

        If no memory type can be found that meets all the requirements, these functions return {@code VK_ERROR_FEATURE_NOT_PRESENT}.

        You can leave ##VmaAllocationCreateInfo structure completely filled with zeros. It means no requirements are specified for memory type. It is valid,
        although not very useful.

        <h4>Usage</h4>

        The easiest way to specify memory requirements is to fill member ##VmaAllocationCreateInfo{@code ::usage} using one of the values of enum
        {@code VmaMemoryUsage}. It defines high level, common usage types. Since version 3 of the library, it is recommended to use #MEMORY_USAGE_AUTO to let
        it select best memory type for your resource automatically.

        For example, if you want to create a uniform buffer that will be filled using transfer only once or infrequently and then used for rendering every
        frame as a uniform buffer, you can do it using following code. The buffer will most likely end up in a memory type with
        {@code VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT} to be fast to access by the GPU device.

        ${codeBlock("""
VkBufferCreateInfo bufferInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufferInfo.size = 65536;
bufferInfo.usage = VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT | VK_BUFFER_USAGE_TRANSFER_DST_BIT;

VmaAllocationCreateInfo allocInfo = {};
allocInfo.usage = VMA_MEMORY_USAGE_AUTO;

VkBuffer buffer;
VmaAllocation allocation;
vmaCreateBuffer(allocator, &bufferInfo, &allocInfo, &buffer, &allocation, nullptr);""")}

        If you have a preference for putting the resource in GPU (device) memory or CPU (host) memory on systems with discrete graphics card that have the
        memories separate, you can use #MEMORY_USAGE_AUTO_PREFER_DEVICE or #MEMORY_USAGE_AUTO_PREFER_HOST.

        When using {@code VMA_MEMORY_USAGE_AUTO*} while you want to map the allocated memory, you also need to specify one of the host access flags:
        #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT or #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT. This will help the library decide about preferred
        memory type to ensure it has {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT} so you can map it.

        For example, a staging buffer that will be filled via mapped pointer and then used as a source of transfer to the buffer described previously can be
        created like this. It will likely end up in a memory type that is {@code HOST_VISIBLE} and {@code HOST_COHERENT} but not {@code HOST_CACHED} (meaning
        uncached, write-combined) and not {@code DEVICE_LOCAL} (meaning system RAM).

        ${codeBlock("""
VkBufferCreateInfo stagingBufferInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
stagingBufferInfo.size = 65536;
stagingBufferInfo.usage = VK_BUFFER_USAGE_TRANSFER_SRC_BIT;

VmaAllocationCreateInfo stagingAllocInfo = {};
stagingAllocInfo.usage = VMA_MEMORY_USAGE_AUTO;
stagingAllocInfo.flags = VMA_ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT;

VkBuffer stagingBuffer;
VmaAllocation stagingAllocation;
vmaCreateBuffer(allocator, &stagingBufferInfo, &stagingAllocInfo, &stagingBuffer, &stagingAllocation, nullptr);""")}

        Usage values {@code VMA_MEMORY_USAGE_AUTO*} are legal to use only when the library knows about the resource being created by having
        {@code VkBufferCreateInfo} / {@code VkImageCreateInfo} passed, so they work with functions like: #CreateBuffer(), #CreateImage(),
        #FindMemoryTypeIndexForBufferInfo() etc. If you allocate raw memory using function #AllocateMemory(), you have to use other means of selecting memory
        type, as described below.

        ${note("""
        Old usage values (#MEMORY_USAGE_GPU_ONLY, #MEMORY_USAGE_CPU_ONLY, #MEMORY_USAGE_CPU_TO_GPU, #MEMORY_USAGE_GPU_TO_CPU, #MEMORY_USAGE_CPU_COPY) are still
        available and work same way as in previous versions of the library for backward compatibility, but they are deprecated.""")}

        <h4>Required and preferred flags</h4>

        You can specify more detailed requirements by filling members ##VmaAllocationCreateInfo{@code ::requiredFlags} and
        ##VmaAllocationCreateInfo{@code ::preferredFlags} with a combination of bits from enum {@code VkMemoryPropertyFlags}. For example, if you want to
        create a buffer that will be persistently mapped on host (so it must be {@code HOST_VISIBLE}) and preferably will also be {@code HOST_COHERENT} and
        {@code HOST_CACHED}, use following code:

        ${codeBlock("""
VmaAllocationCreateInfo allocInfo = {};
allocInfo.requiredFlags = VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT;
allocInfo.preferredFlags = VK_MEMORY_PROPERTY_HOST_COHERENT_BIT | VK_MEMORY_PROPERTY_HOST_CACHED_BIT;
allocInfo.flags = VMA_ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT | VMA_ALLOCATION_CREATE_MAPPED_BIT;

VkBuffer buffer;
VmaAllocation allocation;
vmaCreateBuffer(allocator, &bufferInfo, &allocInfo, &buffer, &allocation, nullptr);""")}

        A memory type is chosen that has all the required flags and as many preferred flags set as possible.

        Value passed in ##VmaAllocationCreateInfo{@code ::usage} is internally converted to a set of required and preferred flags, plus some extra "magic"
        (heuristics).

        <h4>Explicit memory types</h4>

        If you inspected memory types available on the physical device and <b>you have a preference for memory types that you want to use</b>, you can fill
        member ##VmaAllocationCreateInfo{@code ::memoryTypeBits}. It is a bit mask, where each bit set means that a memory type with that index is allowed to
        be used for the allocation. Special value 0, just like {@code UINT32_MAX}, means there are no restrictions to memory type index.

        Please note that this member is NOT just a memory type index. Still you can use it to choose just one, specific memory type. For example, if you
        already determined that your buffer should be created in memory type 2, use following code:

        ${codeBlock("""
uint32_t memoryTypeIndex = 2;

VmaAllocationCreateInfo allocInfo = {};
allocInfo.memoryTypeBits = 1u << memoryTypeIndex;

VkBuffer buffer;
VmaAllocation allocation;
vmaCreateBuffer(allocator, &bufferInfo, &allocInfo, &buffer, &allocation, nullptr);""")}

        You can also use this parameter to <b>exclude some memory types</b>. If you inspect memory heaps and types available on the current physical device and
        you determine that for some reason you don't want to use a specific memory type for the allocation, you can enable automatic memory type selection but
        exclude certain memory type or types by setting all bits of {@code memoryTypeBits} to 1 except the ones you choose.

        ${codeBlock("""
// ...
uint32_t excludedMemoryTypeIndex = 2;
VmaAllocationCreateInfo allocInfo = {};
allocInfo.usage = VMA_MEMORY_USAGE_AUTO;
allocInfo.memoryTypeBits = ~(1u << excludedMemoryTypeIndex);
// ...""")}

        <h4>Custom memory pools</h4>

        If you allocate from custom memory pool, all the ways of specifying memory requirements described above are not applicable and the aforementioned
        members of ##VmaAllocationCreateInfo structure are ignored. Memory type is selected explicitly when creating the pool and then used to make all the
        allocations from that pool. For further details, see Custom Memory Pools below.

        <h4>Dedicated allocations</h4>

        Memory for allocations is reserved out of larger block of {@code VkDeviceMemory} allocated from Vulkan internally. That is the main feature of this
        whole library. You can still request a separate memory block to be created for an allocation, just like you would do in a trivial solution without
        using any allocator. In that case, a buffer or image is always bound to that memory at offset 0. This is called a "dedicated allocation". You can
        explicitly request it by using flag #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT. The library can also internally decide to use dedicated allocation in some
        cases, e.g.:

        ${ul(
            "When the size of the allocation is large.",
            """
            When {@code VK_KHR_dedicated_allocation} extension is enabled and it reports that dedicated allocation is required or recommended for the resource.
            """,
            "When allocation of next big memory block fails due to not enough device memory, but allocation with the exact requested size succeeds."
        )}

        <h3>Memory mapping</h3>

        To "map memory" in Vulkan means to obtain a CPU pointer to {@code VkDeviceMemory}, to be able to read from it or write to it in CPU code. Mapping is
        possible only of memory allocated from a memory type that has {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT} flag. Functions {@code vkMapMemory()},
        {@code vkUnmapMemory()} are designed for this purpose. You can use them directly with memory allocated by this library, but it is not recommended
        because of following issue: Mapping the same {@code VkDeviceMemory} block multiple times is illegal - only one mapping at a time is allowed. This
        includes mapping disjoint regions. Mapping is not reference-counted internally by Vulkan. It is also not thread-safe. Because of this, Vulkan Memory
        Allocator provides following facilities:

        ${note("""
        If you want to be able to map an allocation, you need to specify one of the flags #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT or
        #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT in ##VmaAllocationCreateInfo{@code ::flags}. These flags are required for an allocation to be mappable when
        using #MEMORY_USAGE_AUTO or other {@code VMA_MEMORY_USAGE_AUTO*} enum values. For other usage values they are ignored and every such allocation made in
        {@code HOST_VISIBLE} memory type is mappable, but these flags can still be used for consistency.
        """)}

        <h4>Copy functions</h4>

        The easiest way to copy data from a host pointer to an allocation is to use convenience function #CopyMemoryToAllocation(). It automatically maps the
        Vulkan memory temporarily (if not already mapped), performs {@code memcpy}, and calls {@code vkFlushMappedMemoryRanges} (if required - if memory type
        is not {@code HOST_COHERENT}).

        It is also the safest one, because using {@code memcpy} avoids a risk of accidentally introducing memory reads (e.g. by doing
        {@code pMappedVectors[i] += v}), which may be very slow on memory types that are not {@code HOST_CACHED}.

        ${codeBlock("""
struct ConstantBuffer
{
    ...
};
ConstantBuffer constantBufferData = ...

VkBufferCreateInfo bufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufCreateInfo.size = sizeof(ConstantBuffer);
bufCreateInfo.usage = VK_BUFFER_USAGE_TRANSFER_SRC_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;
allocCreateInfo.flags = VMA_ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT;

VkBuffer buf;
VmaAllocation alloc;
vmaCreateBuffer(allocator, &bufCreateInfo, &allocCreateInfo, &buf, &alloc, nullptr);

vmaCopyMemoryToAllocation(allocator, &constantBufferData, alloc, 0, sizeof(ConstantBuffer));""")}

        Copy in the other direction - from an allocation to a host pointer can be performed the same way using function #CopyAllocationToMemory().

        <h4>Mapping functions</h4>

        The library provides following functions for mapping of a specific allocation: #MapMemory(), #UnmapMemory(). They are safer and more
        convenient to use than standard Vulkan functions. You can map an allocation multiple times simultaneously - mapping is reference-counted internally.
        You can also map different allocations simultaneously regardless of whether they use the same {@code VkDeviceMemory} block. The way it is implemented
        is that the library always maps entire memory block, not just region of the allocation. For further details, see description of #MapMemory() function.
        Example:

        ${codeBlock("""
// Having these objects initialized:
struct ConstantBuffer
{
    ...
};
ConstantBuffer constantBufferData = ...

VmaAllocator allocator = ...
VkBuffer constantBuffer = ...
VmaAllocation constantBufferAllocation = ...

// You can map and fill your buffer using following code:

void* mappedData;
vmaMapMemory(allocator, constantBufferAllocation, &mappedData);
memcpy(mappedData, &constantBufferData, sizeof(constantBufferData));
vmaUnmapMemory(allocator, constantBufferAllocation);""")}

        When mapping, you may see a warning from Vulkan validation layer similar to this one:

        <i>Mapping an image with layout {@code VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL} can result in undefined behavior if this memory is used by the
        device. Only {@code GENERAL} or {@code PREINITIALIZED} should be used.</i>

        It happens because the library maps entire {@code VkDeviceMemory} block, where different types of images and buffers may end up together, especially on
        GPUs with unified memory like Intel. You can safely ignore it if you are sure you access only memory of the intended object that you wanted to map.

        <h4>Persistently mapped memory</h4>

        Keeping your memory persistently mapped is generally OK in Vulkan. You don't need to unmap it before using its data on the GPU. The library provides a
        special feature designed for that: Allocations made with #ALLOCATION_CREATE_MAPPED_BIT flag set in ##VmaAllocationCreateInfo{@code ::flags} stay mapped
        all the time, so you can just access CPU pointer to it any time without a need to call any "map" or "unmap" function. Example:

        ${codeBlock("""
VkBufferCreateInfo bufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufCreateInfo.size = sizeof(ConstantBuffer);
bufCreateInfo.usage = VK_BUFFER_USAGE_TRANSFER_SRC_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;
allocCreateInfo.flags = VMA_ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT |
    VMA_ALLOCATION_CREATE_MAPPED_BIT;

VkBuffer buf;
VmaAllocation alloc;
VmaAllocationInfo allocInfo;
vmaCreateBuffer(allocator, &bufCreateInfo, &allocCreateInfo, &buf, &alloc, &allocInfo);

// Buffer is already mapped. You can access its memory.
memcpy(allocInfo.pMappedData, &constantBufferData, sizeof(constantBufferData));""")}

        ${note("""
        #ALLOCATION_CREATE_MAPPED_BIT by itself doesn't guarantee that the allocation will end up in a mappable memory type. For this, you need to also specify
        #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT or #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT. #ALLOCATION_CREATE_MAPPED_BIT only guarantees that if
        the memory is {@code HOST_VISIBLE}, the allocation will be mapped on creation.""")}

        <h4>Cache flush and invalidate</h4>

        Memory in Vulkan doesn't need to be unmapped before using it on GPU, but unless a memory types has {@code VK_MEMORY_PROPERTY_HOST_COHERENT_BIT} flag
        set, you need to manually <b>invalidate</b> cache before reading of mapped pointer and <b>flush</b> cache after writing to mapped pointer. Map/unmap
        operations don't do that automatically. Vulkan provides following functions for this purpose {@code vkFlushMappedMemoryRangs()},
        {@code vkInvalidateMappedMemoryRanges()}, but this library provides more convenient functions that refer to given allocation object:
        #FlushAllocation(), #InvalidateAllocation(), or multiple objects at once: #FlushAllocations(), #InvalidateAllocations().

        Regions of memory specified for flush/invalidate must be aligned to {@code VkPhysicalDeviceLimits::nonCoherentAtomSize}. This is automatically ensured
        by the library. In any memory type that is {@code HOST_VISIBLE} but not {@code HOST_COHERENT}, all allocations within blocks are aligned to this value,
        so their offsets are always multiply of {@code nonCoherentAtomSize} and two different allocations never share same "line" of this size.

        Please note that memory allocated with #MEMORY_USAGE_CPU_ONLY is guaranteed to be {@code HOST_COHERENT}.

        Also, Windows drivers from all 3 PC GPU vendors (AMD, Intel, NVIDIA) currently provide {@code HOST_COHERENT} flag on all memory types that are 
        {@code HOST_VISIBLE}, so on PC you may not need to bother.

        <h4>Finding out if memory is mappable</h4>

        It may happen that your allocation ends up in memory that is {@code HOST_VISIBLE} (available for mapping) despite it wasn't explicitly requested. For
        example, application may work on integrated graphics with unified memory (like Intel) or allocation from video memory might have failed, so the library
        chose system memory as fallback.

        You can detect this case and map such allocation to access its memory on CPU directly, instead of launching a transfer operation. In order to do that:
        call #GetAllocationMemoryProperties() and look for {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT} flag.

        ${codeBlock("""
VkBufferCreateInfo bufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufCreateInfo.size = sizeof(ConstantBuffer);
bufCreateInfo.usage = VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT | VK_BUFFER_USAGE_TRANSFER_DST_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_GPU_ONLY;
allocCreateInfo.preferredFlags = VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT;

VkBuffer buf;
VmaAllocation alloc;
vmaCreateBuffer(allocator, &bufCreateInfo, &allocCreateInfo, &buf, &alloc, nullptr);

VkMemoryPropertyFlags memFlags;
vmaGetAllocationMemoryProperties(allocator, alloc, &memFlags);
if((memFlags & VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT) != 0)
{
    // Allocation ended up in mappable memory. You can map it and access it directly.
    void* mappedData;
    vmaMapMemory(allocator, alloc, &mappedData);
    memcpy(mappedData, &constantBufferData, sizeof(constantBufferData));
    vmaUnmapMemory(allocator, alloc);
}
else
{
    // Allocation ended up in non-mappable memory.
    // You need to create CPU-side buffer in VMA_MEMORY_USAGE_CPU_ONLY and make a transfer.
}""")}

        You can even use #ALLOCATION_CREATE_MAPPED_BIT flag while creating allocations that are not necessarily {@code HOST_VISIBLE} (e.g. using
        #MEMORY_USAGE_GPU_ONLY). If the allocation ends up in memory type that is {@code HOST_VISIBL}E, it will be persistently mapped and you can use it
        directly. If not, the flag is just ignored. Example:

        ${codeBlock("""
VkBufferCreateInfo bufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufCreateInfo.size = sizeof(ConstantBuffer);
bufCreateInfo.usage = VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT | VK_BUFFER_USAGE_TRANSFER_DST_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_GPU_ONLY;
allocCreateInfo.flags = VMA_ALLOCATION_CREATE_MAPPED_BIT;

VkBuffer buf;
VmaAllocation alloc;
VmaAllocationInfo allocInfo;
vmaCreateBuffer(allocator, &bufCreateInfo, &allocCreateInfo, &buf, &alloc, &allocInfo);

if(allocInfo.pMappedData != nullptr)
{
    // Allocation ended up in mappable memory.
    // It is persistently mapped. You can access it directly.
    memcpy(allocInfo.pMappedData, &constantBufferData, sizeof(constantBufferData));
}
else
{
    // Allocation ended up in non-mappable memory.
    // You need to create CPU-side buffer in VMA_MEMORY_USAGE_CPU_ONLY and make a transfer.
}""")}

        <h3>Staying within budget</h3>

        When developing a graphics-intensive game or program, it is important to avoid allocating more GPU memory than it is physically available. When the
        memory is over-committed, various bad things can happen, depending on the specific GPU, graphics driver, and operating system:
        ${ul(
            "It may just work without any problems.",
            "The application may slow down because some memory blocks are moved to system RAM and the GPU has to access them through PCI Express bus.",
            "A new allocation may take very long time to complete, even few seconds, and possibly freeze entire system.",
            "The new allocation may fail with {@code VK_ERROR_OUT_OF_DEVICE_MEMORY}.",
            "It may even result in GPU crash (TDR), observed as {@code VK_ERROR_DEVICE_LOST} returned somewhere later."
        )}

        <h4>Querying for budget</h4>

        To query for current memory usage and available budget, use function #GetHeapBudgets(). Returned structure ##VmaBudget contains quantities expressed in
        bytes, per Vulkan memory heap.

        Please note that this function returns different information and works faster than #CalculateStatistics(). {@code vmaGetHeapBudgets()} can be called
        every frame or even before every allocation, while {@code vmaCalculateStatistics()} is intended to be used rarely, only to obtain statistical
        information, e.g. for debugging purposes.

        It is recommended to use <b>VK_EXT_memory_budget</b> device extension to obtain information about the budget from Vulkan device. VMA is able to use
        this extension automatically. When not enabled, the allocator behaves same way, but then it estimates current usage and available budget based on its
        internal information and Vulkan memory heap sizes, which may be less precise. In order to use this extension:
        ${ol(
            """
            Make sure extensions {@code VK_EXT_memory_budget} and {@code VK_KHR_get_physical_device_properties2} required by it are available and enable them.
            Please note that the first is a device extension and the second is instance extension!
            """,
            "Use flag #ALLOCATOR_CREATE_EXT_MEMORY_BUDGET_BIT when creating {@code VmaAllocator} object.",
            """
            Make sure to call #SetCurrentFrameIndex() every frame. Budget is queried from Vulkan inside of it to avoid overhead of querying it with every
            allocation.
            """
        )}

        <h4>Controlling memory usage</h4>

        There are many ways in which you can try to stay within the budget.

        First, when making new allocation requires allocating a new memory block, the library tries not to exceed the budget automatically. If a block with
        default recommended size (e.g. 256 MB) would go over budget, a smaller block is allocated, possibly even dedicated memory for just this resource.

        If the size of the requested resource plus current memory usage is more than the budget, by default the library still tries to create it, leaving it to
        the Vulkan implementation whether the allocation succeeds or fails. You can change this behavior by using #ALLOCATION_CREATE_WITHIN_BUDGET_BIT flag.
        With it, the allocation is not made if it would exceed the budget or if the budget is already exceeded. VMA then tries to make the allocation from the
        next eligible Vulkan memory type. The all of them fail, the call then fails with {@code VK_ERROR_OUT_OF_DEVICE_MEMORY}. Example usage pattern may be to
        pass the {@code VMA_ALLOCATION_CREATE_WITHIN_BUDGET_BIT} flag when creating resources that are not essential for the application (e.g. the texture of a
        specific object) and not to pass it when creating critically important resources (e.g. render targets).
        
        On AMD graphics cards there is a custom vendor extension available: {@code VK_AMD_memory_overallocation_behavior} that allows to control the behavior
        of the Vulkan implementation in out-of-memory cases - whether it should fail with an error code or still allow the allocation. Usage of this extension
        involves only passing extra structure on Vulkan device creation, so it is out of scope of this library.

        Finally, you can also use #ALLOCATION_CREATE_NEVER_ALLOCATE_BIT flag to make sure a new allocation is created only when it fits inside one of the
        existing memory blocks. If it would require to allocate a new block, if fails instead with {@code VK_ERROR_OUT_OF_DEVICE_MEMORY}. This also ensures
        that the function call is very fast because it never goes to Vulkan to obtain a new block.

        Note: Creating custom memory pools with ##VmaPoolCreateInfo{@code ::minBlockCount} set to more than 0 will currently try to allocate memory blocks
        without checking whether they fit within budget.

        <h3>Resource aliasing (overlap)</h3>

        New explicit graphics APIs (Vulkan and Direct3D 12), thanks to manual memory management, give an opportunity to alias (overlap) multiple resources in
        the same region of memory - a feature not available in the old APIs (Direct3D 11, OpenGL). It can be useful to save video memory, but it must be used
        with caution.

        For example, if you know the flow of your whole render frame in advance, you are going to use some intermediate textures or buffers only during a small
        range of render passes, and you know these ranges don't overlap in time, you can bind these resources to the same place in memory, even if they have
        completely different parameters (width, height, format etc.).

        Such scenario is possible using VMA, but you need to create your images manually. Then you need to calculate parameters of an allocation to be made
        using formula:
        ${ul(
            "allocation size = max(size of each image)",
            "allocation alignment = max(alignment of each image)",
            "allocation memoryTypeBits = bitwise AND(memoryTypeBits of each image)"
        )}

        Following example shows two different images bound to the same place in memory, allocated to fit largest of them.
        ${codeBlock("""
// A 512x512 texture to be sampled.
VkImageCreateInfo img1CreateInfo = { VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO };
img1CreateInfo.imageType = VK_IMAGE_TYPE_2D;
img1CreateInfo.extent.width = 512;
img1CreateInfo.extent.height = 512;
img1CreateInfo.extent.depth = 1;
img1CreateInfo.mipLevels = 10;
img1CreateInfo.arrayLayers = 1;
img1CreateInfo.format = VK_FORMAT_R8G8B8A8_SRGB;
img1CreateInfo.tiling = VK_IMAGE_TILING_OPTIMAL;
img1CreateInfo.initialLayout = VK_IMAGE_LAYOUT_UNDEFINED;
img1CreateInfo.usage = VK_IMAGE_USAGE_TRANSFER_DST_BIT | VK_IMAGE_USAGE_SAMPLED_BIT;
img1CreateInfo.samples = VK_SAMPLE_COUNT_1_BIT;

// A full screen texture to be used as color attachment.
VkImageCreateInfo img2CreateInfo = { VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO };
img2CreateInfo.imageType = VK_IMAGE_TYPE_2D;
img2CreateInfo.extent.width = 1920;
img2CreateInfo.extent.height = 1080;
img2CreateInfo.extent.depth = 1;
img2CreateInfo.mipLevels = 1;
img2CreateInfo.arrayLayers = 1;
img2CreateInfo.format = VK_FORMAT_R8G8B8A8_UNORM;
img2CreateInfo.tiling = VK_IMAGE_TILING_OPTIMAL;
img2CreateInfo.initialLayout = VK_IMAGE_LAYOUT_UNDEFINED;
img2CreateInfo.usage = VK_IMAGE_USAGE_SAMPLED_BIT | VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT;
img2CreateInfo.samples = VK_SAMPLE_COUNT_1_BIT;

VkImage img1;
res = vkCreateImage(device, &img1CreateInfo, nullptr, &img1);
VkImage img2;
res = vkCreateImage(device, &img2CreateInfo, nullptr, &img2);

VkMemoryRequirements img1MemReq;
vkGetImageMemoryRequirements(device, img1, &img1MemReq);
VkMemoryRequirements img2MemReq;
vkGetImageMemoryRequirements(device, img2, &img2MemReq);

VkMemoryRequirements finalMemReq = {};
finalMemReq.size = std::max(img1MemReq.size, img2MemReq.size);
finalMemReq.alignment = std::max(img1MemReq.alignment, img2MemReq.alignment);
finalMemReq.memoryTypeBits = img1MemReq.memoryTypeBits & img2MemReq.memoryTypeBits;
// Validate if(finalMemReq.memoryTypeBits != 0)

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.preferredFlags = VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT;

VmaAllocation alloc;
res = vmaAllocateMemory(allocator, &finalMemReq, &allocCreateInfo, &alloc, nullptr);

res = vmaBindImageMemory(allocator, alloc, img1);
res = vmaBindImageMemory(allocator, alloc, img2);

// You can use img1, img2 here, but not at the same time!

vmaFreeMemory(allocator, alloc);
vkDestroyImage(allocator, img2, nullptr);
vkDestroyImage(allocator, img1, nullptr);""")}

        VMA also provides convenience functions that create a buffer or image and bind it to memory represented by an existing {@code VmaAllocation}:
        #CreateAliasingBuffer(), #CreateAliasingBuffer2(), #CreateAliasingImage(), #CreateAliasingImage2(). Versions with "2" offer additional parameter
        {@code allocationLocalOffset}.

        Remember that using resources that alias in memory requires proper synchronization. You need to issue a memory barrier to make sure commands that use
        {@code img1} and {@code img2} don't overlap on GPU timeline. You also need to treat a resource after aliasing as uninitialized - containing garbage
        data. For example, if you use {@code img1} and then want to use {@code img2}, you need to issue an image memory barrier for {@code img2} with
        {@code oldLayout = VK_IMAGE_LAYOUT_UNDEFINED}.

        Additional considerations:
        ${ul(
            """
            Vulkan also allows to interpret contents of memory between aliasing resources consistently in some cases. See chapter 11.8. "Memory Aliasing" of
            Vulkan specification or {@code VK_IMAGE_CREATE_ALIAS_BIT} flag. 
            """,
            """
            You can create more complex layout where different images and buffers are bound at different offsets inside one large allocation. For example, one
            can imagine a big texture used in some render passes, aliasing with a set of many small buffers used between in some further passes. To bind a
            resource at non-zero offset in an allocation, use #BindBufferMemory2() / #BindImageMemory2().
            """,
            """
            Before allocating memory for the resources you want to alias, check {@code memoryTypeBits} returned in memory requirements of each resource to make
            sure the bits overlap. Some GPUs may expose multiple memory types suitable e.g. only for buffers or images with {@code COLOR_ATTACHMENT} usage, so
            the sets of memory types supported by your resources may be disjoint. Aliasing them is not possible in that case.
            """
        )}

        <h3>Custom memory pools</h3>

        A memory pool contains a number of {@code VkDeviceMemory} blocks. The library automatically creates and manages default pool for each memory type
        available on the device. Default memory pool automatically grows in size. Size of allocated blocks is also variable and managed automatically. You are
        using default pools whenever you leave {@code VmaAllocationCreateInfo::pool = null}.

        You can create custom pool and allocate memory out of it. It can be useful if you want to:
        ${ul(
            "Keep certain kind of allocations separate from others.",
            "Enforce particular, fixed size of Vulkan memory blocks.",
            "Limit maximum amount of Vulkan memory allocated for that pool.",
            "Reserve minimum or fixed amount of Vulkan memory always preallocated for that pool.",
            """
            Use extra parameters for a set of your allocations that are available in ##VmaPoolCreateInfo but not in ##VmaAllocationCreateInfo - e.g., custom
            minimum alignment, custom {@code pNext} chain.
            """,
            "Perform defragmentation on a specific subset of your allocations."
        )}

        To use custom memory pools:
        ${ol(
            "Fill ##VmaPoolCreateInfo structure.",
            "Call #CreatePool() to obtain {@code VmaPool} handle.",
            """
            When making an allocation, set ##VmaAllocationCreateInfo{@code ::pool} to this handle. You don't need to specify any other parameters of this
            structure, like {@code usage}.
            """
        )}
        Example:

        ${codeBlock("""
// Find memoryTypeIndex for the pool.
VkBufferCreateInfo sampleBufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
sampleBufCreateInfo.size = 0x10000; // Doesn't matter.
sampleBufCreateInfo.usage = VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT | VK_BUFFER_USAGE_TRANSFER_DST_BIT;

VmaAllocationCreateInfo sampleAllocCreateInfo = {};
sampleAllocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;

uint32_t memTypeIndex;
VkResult res = vmaFindMemoryTypeIndexForBufferInfo(allocator,
    &sampleBufCreateInfo, &sampleAllocCreateInfo, &memTypeIndex);
// Check res...

// Create a pool that can have at most 2 blocks, 128 MiB each.
VmaPoolCreateInfo poolCreateInfo = {};
poolCreateInfo.memoryTypeIndex = memTypeIndex
poolCreateInfo.blockSize = 128ull * 1024 * 1024;
poolCreateInfo.maxBlockCount = 2;

VmaPool pool;
res = vmaCreatePool(allocator, &poolCreateInfo, &pool);
// Check res...

// Allocate a buffer out of it.
VkBufferCreateInfo bufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufCreateInfo.size = 1024;
bufCreateInfo.usage = VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT | VK_BUFFER_USAGE_TRANSFER_DST_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.pool = pool;

VkBuffer buf;
VmaAllocation alloc;
res = vmaCreateBuffer(allocator, &bufCreateInfo, &allocCreateInfo, &buf, &alloc, nullptr);
// Check res...""")}

        You have to free all allocations made from this pool before destroying it.

        ${codeBlock("""
vmaDestroyBuffer(allocator, buf, alloc);
vmaDestroyPool(allocator, pool);""")}

        New versions of this library support creating dedicated allocations in custom pools. It is supported only when {@code VmaPoolCreateInfo::blockSize = 0}.
        To use this feature, set {@code VmaAllocationCreateInfo::pool} to the pointer to your custom pool and {@code VmaAllocationCreateInfo::flags} to
        #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT.

        <h4>Choosing memory type index</h4>

        When creating a pool, you must explicitly specify memory type index. To find the one suitable for your buffers or images, you can use helper functions
        #FindMemoryTypeIndexForBufferInfo(), #FindMemoryTypeIndexForImageInfo(). You need to provide structures with example parameters of buffers or images
        that you are going to create in that pool.

        ${codeBlock("""
VkBufferCreateInfo exampleBufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
exampleBufCreateInfo.size = 1024; // Doesn't matter
exampleBufCreateInfo.usage = VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT | VK_BUFFER_USAGE_TRANSFER_DST_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;

uint32_t memTypeIndex;
vmaFindMemoryTypeIndexForBufferInfo(allocator, &exampleBufCreateInfo, &allocCreateInfo, &memTypeIndex);

VmaPoolCreateInfo poolCreateInfo = {};
poolCreateInfo.memoryTypeIndex = memTypeIndex;
// ...""")}

        When creating buffers/images allocated in that pool, provide following parameters:

        ${ul(
            """
            {@code VkBufferCreateInfo}: Prefer to pass same parameters as above. Otherwise you risk creating resources in a memory type that is not suitable
            for them, which may result in undefined behavior. Using different {@code VK_BUFFER_USAGE_} flags may work, but you shouldn't create images in a
            pool intended for buffers or the other way around.
            """,
            "##VmaAllocationCreateInfo: You don't need to pass same parameters. Fill only {@code pool} member. Other members are ignored anyway."
        )}

        <h4>When not to use custom pools</h4> 

        Custom pools are commonly overused by VMA users. While it may feel natural to keep some logical groups of resources separate in memory, in most cases
        it does more harm than good. Using custom pool shouldn't be your first choice. Instead, please make all allocations from default pools first and only
        use custom pools if you can prove and measure that it is beneficial in some way, e.g. it results in lower memory usage, better performance, etc.

        Using custom pools has disadvantages:
        ${ul(
            """
            Each pool has its own collection of {@code VkDeviceMemory} blocks. Some of them may be partially or even completely empty. Spreading allocations
            across multiple pools increases the amount of wasted (allocated but unbound) memory.
            """,
            """
            You must manually choose specific memory type to be used by a custom pool (set as {@code VmaPoolCreateInfo::memoryTypeIndex}). When using default
            pools, best memory type for each of your allocations can be selected automatically using a carefully design algorithm that works across all kinds
            of GPUs.
            """,
            """
            If an allocation from a custom pool at specific memory type fails, entire allocation operation returns failure. When using default pools, VMA tries
            another compatible memory type.
            """,
            """
            If you set {@code VmaPoolCreateInfo::blockSize != 0}, each memory block has the same size, while default pools start from small blocks and only
            allocate next blocks larger and larger up to the preferred block size.
            """
        )}

        Many of the common concerns can be addressed in a different way than using custom pools:
        ${ul(
            """
            If you want to keep your allocations of certain size (small versus large) or certain lifetime (transient versus long lived) separate, you likely
            don't need to. VMA uses a high quality allocation algorithm that manages memory well in various cases. Please measure and check if using custom
            pools provides a benefit.
            """,
            "If you want to keep your images and buffers separate, you don't need to. VMA respects {@code bufferImageGranularity} limit automatically.",
            """
            If you want to keep your mapped and not mapped allocations separate, you don't need to. VMA respects {@code nonCoherentAtomSize} limit
            automatically. It also maps only those {@code VkDeviceMemory} blocks that need to map any allocation. It even tries to keep mappable and
            non-mappable allocations in separate blocks to minimize the amount of mapped memory.
            """,
            """
            If you want to choose a custom size for the default memory block, you can set it globally instead using 
            {@code VmaAllocatorCreateInfo::preferredLargeHeapBlockSize}.
            """,
            """
            If you want to select specific memory type for your allocation, you can set {@code VmaAllocationCreateInfo::memoryTypeBits} to
            {@code (1u << myMemoryTypeIndex)} instead.
            """,
            """
            If you need to create a buffer with certain minimum alignment, you can still do it using default pools with dedicated function
            #CreateBufferWithAlignment().
            """
        )}

        <h4>Linear allocation algorithm</h4>

        Each Vulkan memory block managed by this library has accompanying metadata that keeps track of used and unused regions. By default, the metadata
        structure and algorithm tries to find best place for new allocations among free regions to optimize memory usage. This way you can allocate and free
        objects in any order.

        Sometimes there is a need to use simpler, linear allocation algorithm. You can create custom pool that uses such algorithm by adding flag
        #POOL_CREATE_LINEAR_ALGORITHM_BIT to ##VmaPoolCreateInfo{@code ::flags} while creating {@code VmaPool} object. Then an alternative metadata management
        is used. It always creates new allocations after last one and doesn't reuse free regions after allocations freed in the middle. It results in better
        allocation performance and less memory consumed by metadata.

        With this one flag, you can create a custom pool that can be used in many ways: free-at-once, stack, double stack, and ring buffer. See below for
        details.

        <h5>Free-at-once</h5>

        In a pool that uses linear algorithm, you still need to free all the allocations individually, e.g. by using #FreeMemory() or #DestroyBuffer(). You can
        free them in any order. New allocations are always made after last one - free space in the middle is not reused. However, when you release all the
        allocation and the pool becomes empty, allocation starts from the beginning again. This way you can use linear algorithm to speed up creation of
        allocations that you are going to release all at once.

        This mode is also available for pools created with ##VmaPoolCreateInfo{@code ::maxBlockCount} value that allows multiple memory blocks.

        <h5>Stack</h5>

        When you free an allocation that was created last, its space can be reused. Thanks to this, if you always release allocations in the order opposite to
        their creation (LIFO - Last In First Out), you can achieve behavior of a stack.

        This mode is also available for pools created with ##VmaPoolCreateInfo{@code ::maxBlockCount} value that allows multiple memory blocks.

        <h5>Double stack</h5>

        The space reserved by a custom pool with linear algorithm may be used by two stacks:
        ${ul(
            "First, default one, growing up from offset 0.",
            "Second, \"upper\" one, growing down from the end towards lower offsets."
        )}

        To make allocation from upper stack, add flag #ALLOCATION_CREATE_UPPER_ADDRESS_BIT to ##VmaAllocationCreateInfo{@code ::flags}.

        Double stack is available only in pools with one memory block - ##VmaPoolCreateInfo{@code ::maxBlockCount} must be 1. Otherwise behavior is undefined.

        When the two stacks' ends meet so there is not enough space between them for a new allocation, such allocation fails with usual
        {@code VK_ERROR_OUT_OF_DEVICE_MEMORY} error.

        <h5>Ring buffer</h5>

        When you free some allocations from the beginning and there is not enough free space for a new one at the end of a pool, allocator's "cursor" wraps
        around to the beginning and starts allocation there. Thanks to this, if you always release allocations in the same order as you created them (FIFO -
        First In First Out), you can achieve behavior of a ring buffer / queue.

        Ring buffer is available only in pools with one memory block - ##VmaPoolCreateInfo{@code ::maxBlockCount} must be 1. Otherwise behavior is undefined.

        Note: defragmentation is not supported in custom pools created with #POOL_CREATE_LINEAR_ALGORITHM_BIT.

        <h3>Defragmentation</h3>

        Interleaved allocations and deallocations of many objects of varying size cause fragmentation over time, which can lead to a situation where the
        library is unable to find a continuous range of free memory for a new allocation despite there is enough free space, just scattered across many small
        free ranges between existing allocations.

        To mitigate this problem, you can use defragmentation feature. It doesn't happen automatically though and needs your cooperation, because VMA is a low
        level library that only allocates memory. It cannot recreate buffers and images in a new place as it doesn't remember the contents of
        {@code VkBufferCreateInfo} / {@code VkImageCreateInfo} structures. It cannot copy their contents as it doesn't record any commands to a command buffer.

        Example:
        ${codeBlock("""
VmaDefragmentationInfo defragInfo = {};
defragInfo.pool = myPool;
defragInfo.flags = VMA_DEFRAGMENTATION_FLAG_ALGORITHM_FAST_BIT;

VmaDefragmentationContext defragCtx;
VkResult res = vmaBeginDefragmentation(allocator, &defragInfo, &defragCtx);
// Check res...

for(;;)
{
    VmaDefragmentationPassMoveInfo pass;
    res = vmaBeginDefragmentationPass(allocator, defragCtx, &pass);
    if(res == VK_SUCCESS)
        break;
    else if(res != VK_INCOMPLETE)
        // Handle error...

    for(uint32_t i = 0; i < pass.moveCount; ++i)
    {
        // Inspect pass.pMoves[i].srcAllocation, identify what buffer/image it represents.
        VmaAllocationInfo allocInfo;
        vmaGetAllocationInfo(allocator, pass.pMoves[i].srcAllocation, &allocInfo);
        MyEngineResourceData* resData = (MyEngineResourceData*)allocInfo.pUserData;

        // Recreate and bind this buffer/image at: pass.pMoves[i].dstMemory, pass.pMoves[i].dstOffset.
        VkImageCreateInfo imgCreateInfo = ...
        VkImage newImg;
        res = vkCreateImage(device, &imgCreateInfo, nullptr, &newImg);
        // Check res...
        res = vmaBindImageMemory(allocator, pass.pMoves[i].dstTmpAllocation, newImg);
        // Check res...

        // Issue a vkCmdCopyBuffer/vkCmdCopyImage to copy its content to the new place.
        vkCmdCopyImage(cmdBuf, resData->img, ..., newImg, ...);
    }

    // Make sure the copy commands finished executing.
    vkWaitForFences(...);

    // Destroy old buffers/images bound with pass.pMoves[i].srcAllocation.
    for(uint32_t i = 0; i < pass.moveCount; ++i)
    {
        // ...
        vkDestroyImage(device, resData->img, nullptr);
    }

    // Update appropriate descriptors to point to the new places...

    res = vmaEndDefragmentationPass(allocator, defragCtx, &pass);
    if(res == VK_SUCCESS)
        break;
    else if(res != VK_INCOMPLETE)
        // Handle error...
}

vmaEndDefragmentation(allocator, defragCtx, nullptr);""")}

        Although functions like #CreateBuffer(), #CreateImage(), #DestroyBuffer(), #DestroyImage() create/destroy an allocation and a buffer/image at once,
        these are just a shortcut for creating the resource, allocating memory, and binding them together. Defragmentation works on memory allocations only.
        You must handle the rest manually. Defragmentation is an iterative process that should repreat "passes" as long as related functions return
        {@code VK_INCOMPLETE} not {@code VK_SUCCESS}. In each pass:
        ${ol(
            """
            #BeginDefragmentationPass() function call:
            ${ul(
                "Calculates and returns the list of allocations to be moved in this pass. Note this can be a time-consuming process.",
                """
                Reserves destination memory for them by creating temporary destination allocations that you can query for their {@code VkDeviceMemory} +
                {@code offset} using #GetAllocationInfo().
                """
            )}
            """,
            """
            Inside the pass, <b>you should</b>:
            ${ul(
                "Inspect the returned list of allocations to be moved.",
                "Create new buffers/images and bind them at the returned destination temporary allocations.",
                "Copy data from source to destination resources if necessary.",
                "Destroy the source buffers/images, but NOT their allocations."
            )}
            """,
            """
            #EndDefragmentationPass() function call:
            ${ul(
                "Frees the source memory reserved for the allocations that are moved.",
                "Modifies source {@code VmaAllocation} objects that are moved to point to the destination reserved memory.",
                "Frees {@code VkDeviceMemory} blocks that became empty."
            )}
            """
        )}

        Unlike in previous iterations of the defragmentation API, there is no list of "movable" allocations passed as a parameter. Defragmentation algorithm
        tries to move all suitable allocations. You can, however, refuse to move some of them inside a defragmentation pass, by setting
        {@code pass.pMoves[i].operation} to #DEFRAGMENTATION_MOVE_OPERATION_IGNORE. This is not recommended and may result in suboptimal packing of the
        allocations after defragmentation. If you cannot ensure any allocation can be moved, it is better to keep movable allocations separate in a custom
        pool.

        Inside a pass, for each allocation that should be moved:
        ${ul(
            """
            You should copy its data from the source to the destination place by calling e.g. {@code vkCmdCopyBuffer()}, {@code vkCmdCopyImage()}.
            
            You need to make sure these commands finished executing before destroying the source buffers/images and before calling #EndDefragmentationPass().
            """,
            """
            If a resource doesn't contain any meaningful data, e.g. it is a transient color attachment image to be cleared, filled, and used temporarily in
            each rendering frame, you can just recreate this image without copying its data.
            """,
            "If the resource is in {@code HOST_VISIBLE} and {@code HOST_CACHED} memory, you can copy its data on the CPU using {@code memcpy()}.",
            """
            If you cannot move the allocation, you can set {@code pass.pMoves[i].operation} to #DEFRAGMENTATION_MOVE_OPERATION_IGNORE. This will cancel the
            move.

            #EndDefragmentationPass() will then free the destination memory not the source memory of the allocation, leaving it unchanged.
            """,
            """
            If you decide the allocation is unimportant and can be destroyed instead of moved (e.g. it wasn't used for long time), you can set
            {@code pass.pMoves[i].operation} to #DEFRAGMENTATION_MOVE_OPERATION_DESTROY.

            #EndDefragmentationPass() will then free both source and destination memory, and will destroy the source {@code VmaAllocation} object.
            """
        )}

        You can defragment a specific custom pool by setting ##VmaDefragmentationInfo{@code ::pool} (like in the example above) or all the default pools by
        setting this member to null.

        Defragmentation is always performed in each pool separately. Allocations are never moved between different Vulkan memory types. The size of the
        destination memory reserved for a moved allocation is the same as the original one. Alignment of an allocation as it was determined using
        {@code vkGetBufferMemoryRequirements()} etc. is also respected after defragmentation. Buffers/images should be recreated with the same
        {@code VkBufferCreateInfo} / {@code VkImageCreateInfo} parameters as the original ones.

        You can perform the defragmentation incrementally to limit the number of allocations and bytes to be moved in each pass, e.g. to call it in sync with
        render frames and not to experience too big hitches. See members: ##VmaDefragmentationInfo{@code ::maxBytesPerPass},
        ##VmaDefragmentationInfo{@code ::maxAllocationsPerPass}.

        It is also safe to perform the defragmentation asynchronously to render frames and other Vulkan and VMA usage, possibly from multiple threads, with the
        exception that allocations returned in ##VmaDefragmentationPassMoveInfo{@code ::pMoves} shouldn't be destroyed until the defragmentation pass is ended.

        <b>Mapping</b> is preserved on allocations that are moved during defragmentation. Whether through #ALLOCATION_CREATE_MAPPED_BIT or #MapMemory(), the
        allocations are mapped at their new place. Of course, pointer to the mapped data changes, so it needs to be queried using
        ##VmaAllocationInfo{@code ::pMappedData}.

        Note: Defragmentation is not supported in custom pools created with #POOL_CREATE_LINEAR_ALGORITHM_BIT.

        <h3>Statistics</h3>

        This library contains several functions that return information about its internal state, especially the amount of memory allocated from Vulkan.

        <h4>Numeric statistics</h4>

        If you need to obtain basic statistics about memory usage per heap, together with current budget, you can call function #GetHeapBudgets() and inspect
        structure ##VmaBudget. This is useful to keep track of memory usage and stay within budget. Example:
        ${codeBlock("""
uint32_t heapIndex = ...

VmaBudget budgets[VK_MAX_MEMORY_HEAPS];
vmaGetHeapBudgets(allocator, budgets);

printf("My heap currently has %u allocations taking %llu B,\n",
    budgets[heapIndex].statistics.allocationCount,
    budgets[heapIndex].statistics.allocationBytes);
printf("allocated out of %u Vulkan device memory blocks taking %llu B,\n",
    budgets[heapIndex].statistics.blockCount,
    budgets[heapIndex].statistics.blockBytes);
printf("Vulkan reports total usage %llu B with budget %llu B.\n",
    budgets[heapIndex].usage,
    budgets[heapIndex].budget);""")}

        You can query for more detailed statistics per memory heap, type, and totals, including minimum and maximum allocation size and unused range size, by
        calling function #CalculateStatistics() and inspecting structure ##VmaTotalStatistics. This function is slower though, as it has to traverse all the
        internal data structures, so it should be used only for debugging purposes.

        You can query for statistics of a custom pool using function #GetPoolStatistics() or #CalculatePoolStatistics().

        You can query for information about a specific allocation using function #GetAllocationInfo(). It fills structure ##VmaAllocationInfo.

        <h3>JSON dump</h3>

        You can dump internal state of the allocator to a string in JSON format using function #BuildStatsString(). The result is guaranteed to be correct
        JSON. It uses ANSI encoding. Any strings provided by user are copied as-is and properly escaped for JSON, so if they use UTF-8, ISO-8859-2 or any other
        encoding, this JSON string can be treated as using this encoding. It must be freed using function #FreeStatsString().

        The format of this JSON string is not part of official documentation of the library, but it will not change in backward-incompatible way without
        increasing library major version number and appropriate mention in changelog.

        The JSON string contains all the data that can be obtained using #CalculateStatistics(). It can also contain detailed map of allocated memory blocks
        and their regions - free and occupied by allocations. This allows e.g. to visualize the memory or assess fragmentation.

        <h3>Allocation names and user data</h3>

        <h4>Allocation user data</h4>

        You can annotate allocations with your own information, e.g. for debugging purposes. To do that, fill ##VmaAllocationCreateInfo{@code ::pUserData}
        field when creating an allocation. It is an opaque {@code void*} pointer. You can use it e.g. as a pointer, some handle, index, key, ordinal number or
        any other value that would associate the allocation with your custom metadata. It is useful to identify appropriate data structures in your engine
        given {@code VmaAllocation}, e.g. when doing defragmentation.

        ${codeBlock("""
VkBufferCreateInfo bufCreateInfo = ...

MyBufferMetadata* pMetadata = CreateBufferMetadata();

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;
allocCreateInfo.pUserData = pMetadata;

VkBuffer buffer;
VmaAllocation allocation;
vmaCreateBuffer(allocator, &bufCreateInfo, &allocCreateInfo, &buffer, &allocation, nullptr);""")}

        The pointer may be later retrieved as ##VmaAllocationInfo{@code ::pUserData}:

        ${codeBlock("""
VmaAllocationInfo allocInfo;
vmaGetAllocationInfo(allocator, allocation, &allocInfo);
MyBufferMetadata* pMetadata = (MyBufferMetadata*)allocInfo.pUserData;""")}

        It can also be changed using function #SetAllocationUserData().

        Values of (non-zero) allocations' {@code pUserData} are printed in JSON report created by #BuildStatsString() in hexadecimal form.

        <h4>Allocation names</h4>

        An allocation can also carry a null-terminated string, giving a name to the allocation. To set it, call #SetAllocationName(). The library creates
        internal copy of the string, so the pointer you pass doesn't need to be valid for whole lifetime of the allocation. You can free it after the call.

        ${codeBlock("""
std::string imageName = "Texture: ";
imageName += fileName;
vmaSetAllocationName(allocator, allocation, imageName.c_str());""")}

        The string can be later retrieved by inspecting ##VmaAllocationInfo{@code ::pName}. It is also printed in JSON report created by #BuildStatsString().

        Note: Setting string name to VMA allocation doesn't automatically set it to the Vulkan buffer or image created with it. You must do it manually using
        an extension like {@code VK_EXT_debug_utils}, which is independent of this library.

        <h3>Virtual allocator</h3> 

        As an extra feature, the core allocation algorithm of the library is exposed through a simple and convenient API of "virtual allocator". It doesn't
        allocate any real GPU memory. It just keeps track of used and free regions of a "virtual block". You can use it to allocate your own memory or other
        objects, even completely unrelated to Vulkan. A common use case is sub-allocation of pieces of one large GPU buffer.

        <h4>Creating virtual block</h4>

        To use this functionality, there is no main "allocator" object. You don't need to have {@code VmaAllocator} object created. All you need to do is to
        create a separate {@code VmaVirtualBlock} object for each block of memory you want to be managed by the allocator:
        ${ul(
            "Fill in ##VmaVirtualBlockCreateInfo structure.",
            "Call #CreateVirtualBlock(). Get new {@code VmaVirtualBlock} object."
        )}

        Example:
        ${codeBlock("""
VmaVirtualBlockCreateInfo blockCreateInfo = {};
blockCreateInfo.size = 1048576; // 1 MB

VmaVirtualBlock block;
VkResult res = vmaCreateVirtualBlock(&blockCreateInfo, &block);""")}

        <h4>Making virtual allocations</h4>

        {@code VmaVirtualBlock} object contains internal data structure that keeps track of free and occupied regions using the same code as the main Vulkan
        memory allocator. Similarly to {@code VmaAllocation} for standard GPU allocations, there is {@code VmaVirtualAllocation} type that represents an opaque
        handle to an allocation within the virtual block.

        In order to make such allocation:
        ${ul(
            "Fill in ##VmaVirtualAllocationCreateInfo structure.",
            """
            Call #VirtualAllocate(). Get new {@code VmaVirtualAllocation} object that represents the allocation. You can also receive
            {@code VkDeviceSize offset} that was assigned to the allocation.
            """
        )}

        Example:
        ${codeBlock("""
VmaVirtualAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.size = 4096; // 4 KB

VmaVirtualAllocation alloc;
VkDeviceSize offset;
res = vmaVirtualAllocate(block, &allocCreateInfo, &alloc, &offset);
if(res == VK_SUCCESS)
{
    // Use the 4 KB of your memory starting at offset.
}
else
{
    // Allocation failed - no space for it could be found. Handle this error!
}""")}

        <h4>Deallocation</h4>

        When no longer needed, an allocation can be freed by calling #VirtualFree(). You can only pass to this function an allocation that was previously
        returned by {@code vmaVirtualAllocate()} called for the same {@code VmaVirtualBlock}.

        When whole block is no longer needed, the block object can be released by calling #DestroyVirtualBlock(). All allocations must be freed before the
        block is destroyed, which is checked internally by an assert. However, if you don't want to call {@code vmaVirtualFree()} for each allocation, you can
        use #ClearVirtualBlock() to free them all at once - a feature not available in normal Vulkan memory allocator. Example:
        ${codeBlock("""
vmaVirtualFree(block, alloc);
vmaDestroyVirtualBlock(block);""")}

        <h4>Allocation parameters</h4>

        You can attach a custom pointer to each allocation by using #SetVirtualAllocationUserData(). Its default value is null. It can be used to store any
        data that needs to be associated with that allocation - e.g. an index, a handle, or a pointer to some larger data structure containing more
        information. Example:
        ${codeBlock("""
struct CustomAllocData
{
    std::string m_AllocName;
};
CustomAllocData* allocData = new CustomAllocData();
allocData->m_AllocName = "My allocation 1";
vmaSetVirtualAllocationUserData(block, alloc, allocData);""")}

        The pointer can later be fetched, along with allocation offset and size, by passing the allocation handle to function #GetVirtualAllocationInfo() and
        inspecting returned structure {@code VmaVirtualAllocationInfo}. If you allocated a new object to be used as the custom pointer, don't forget to delete
        that object before freeing the allocation! Example:
        ${codeBlock("""
VmaVirtualAllocationInfo allocInfo;
vmaGetVirtualAllocationInfo(block, alloc, &allocInfo);
delete (CustomAllocData*)allocInfo.pUserData;

vmaVirtualFree(block, alloc);""")}

        <h4>Alignment and units</h4>

        It feels natural to express sizes and offsets in bytes. If an offset of an allocation needs to be aligned to a multiply of some number (e.g. 4 bytes),
        you can fill optional member {@code VmaVirtualAllocationCreateInfo::alignment} to request it. Example:
        ${codeBlock("""
VmaVirtualAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.size = 4096; // 4 KB
allocCreateInfo.alignment = 4; // Returned offset must be a multiply of 4 B

VmaVirtualAllocation alloc;
res = vmaVirtualAllocate(block, &allocCreateInfo, &alloc, nullptr);""")}

        Alignments of different allocations made from one block may vary. However, if all alignments and sizes are always multiply of some size e.g. 4 B or
        {@code sizeof(MyDataStruct)}, you can express all sizes, alignments, and offsets in multiples of that size instead of individual bytes. It might be
        more convenient, but you need to make sure to use this new unit consistently in all the places:
        ${ul(
            "{@code VmaVirtualBlockCreateInfo::size}",
            "{@code VmaVirtualAllocationCreateInfo::size} and {@code VmaVirtualAllocationCreateInfo::alignment}",
            "Using offset returned by {@code vmaVirtualAllocate()} or in {@code VmaVirtualAllocationInfo::offset}"
        )}

        <h4>Statistics</h4>

        You can obtain statistics of a virtual block using #GetVirtualBlockStatistics() (to get brief statistics that are fast to calculate) or 
        #CalculateVirtualBlockStatistics() (to get more detailed statistics, slower to calculate). The functions fill structures ##VmaStatistics,
        ##VmaDetailedStatistics respectively - same as used by the normal Vulkan memory allocator. Example:
        ${codeBlock("""
VmaStatistics stats;
vmaGetVirtualBlockStatistics(block, &stats);
printf("My virtual block has %llu bytes used by %u virtual allocations\n",
    stats.allocationBytes, stats.allocationCount);""")}

        You can also request a full list of allocations and free regions as a string in JSON format by calling #BuildVirtualBlockStatsString(). Returned string
        must be later freed using #FreeVirtualBlockStatsString(). The format of this string differs from the one returned by the main Vulkan allocator, but it
        is similar.

        <h4>Additional considerations</h4>

        The "virtual allocator" functionality is implemented on a level of individual memory blocks. Keeping track of a whole collection of blocks, allocating
        new ones when out of free space, deleting empty ones, and deciding which one to try first for a new allocation must be implemented by the user.

        Alternative allocation algorithms are supported, just like in custom pools of the real GPU memory. See enum {@code VmaVirtualBlockCreateFlagBits} to
        learn how to specify them (e.g. #VIRTUAL_BLOCK_CREATE_LINEAR_ALGORITHM_BIT). Allocation strategies are also supported. See enum
        {@code VmaVirtualAllocationCreateFlagBits} to learn how to specify them (e.g. #VIRTUAL_ALLOCATION_CREATE_STRATEGY_MIN_TIME_BIT).

        Following features are supported only by the allocator of the real GPU memory and not by virtual allocations: buffer-image granularity,
        {@code VMA_DEBUG_MARGIN}, {@code VMA_MIN_ALIGNMENT}.

        <h3>Debugging incorrect memory usage</h3>

        If you suspect a bug with memory usage, like usage of uninitialized memory or memory being overwritten out of bounds of an allocation, you can use
        debug features of this library to verify this.

        <h4>Memory initialization</h4>

        If you experience a bug with incorrect and nondeterministic data in your program and you suspect uninitialized memory to be used, you can enable
        automatic memory initialization to verify this. To do it, define macro {@code VMA_DEBUG_INITIALIZE_ALLOCATIONS} to 1.

        It makes memory of all allocations initialized to bit pattern {@code 0xDCDCDCDC}. Before an allocation is destroyed, its memory is filled with bit
        pattern {@code 0xEFEFEFEF}. Memory is automatically mapped and unmapped if necessary.

        If you find these values while debugging your program, good chances are that you incorrectly read Vulkan memory that is allocated but not initialized,
        or already freed, respectively.

        Memory initialization works only with memory types that are {@code HOST_VISIBLE} and with allocations that can be mapped.. It works also with dedicated
        allocations.

        <h4>Margins</h4>

        By default, allocations are laid out in memory blocks next to each other if possible (considering required alignment, {@code bufferImageGranularity},
        and {@code nonCoherentAtomSize}).

        Define macro {@code VMA_DEBUG_MARGIN} to some non-zero value (e.g. 16) to enforce specified number of bytes as a margin after every allocation.

        If your bug goes away after enabling margins, it means it may be caused by memory being overwritten outside of allocation boundaries. It is not 100%
        certain though. Change in application behavior may also be caused by different order and distribution of allocations across memory blocks after margins
        are applied.

        The margin is applied also before first and after last allocation in a block. It may occur only once between two adjacent allocations.

        Margins work with all types of memory.

        Margin is applied only to allocations made out of memory blocks and not to dedicated allocations, which have their own memory block of specific size.
        It is thus not applied to allocations made using #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT flag or those automatically decided to put into dedicated
        allocations, e.g. due to its large size or recommended by {@code VK_KHR_dedicated_allocation} extension.

        Margins appear in JSON dump as part of free space.

        Note that enabling margins increases memory usage and fragmentation.

        Margins do not apply to virtual allocator.

        <h4>Corruption detection</h4>

        You can additionally define macro {@code VMA_DEBUG_DETECT_CORRUPTION} to 1 to enable validation of contents of the margins.

        When this feature is enabled, number of bytes specified as {@code VMA_DEBUG_MARGIN} (it must be multiple of 4) after every allocation is
        filled with a magic number. This idea is also know as "canary". Memory is automatically mapped and unmapped if necessary.

        This number is validated automatically when the allocation is destroyed. If it is not equal to the expected value, {@code VMA_ASSERT()} is executed. It
        clearly means that either CPU or GPU overwritten the memory outside of boundaries of the allocation, which indicates a serious bug.

        You can also explicitly request checking margins of all allocations in all memory blocks that belong to specified memory types by using function
        #CheckCorruption(), or in memory blocks that belong to specified custom pool, by using function #CheckPoolCorruption().

        Margin validation (corruption detection) works only for memory types that are {@code HOST_VISIBLE} and {@code HOST_COHERENT}.

        <h3>Interop with other graphics APIs</h3> 

        VMA provides some features that help with interoperability with other graphics APIs, e.g. OpenGL.

        <h4>Exporting memory</h4> 

        If you want to attach {@code VkExportMemoryAllocateInfoKHR} or other structure to {@code pNext} chain of memory allocations made by the library:

        You can create custom memory pools for such allocations. Define and fill in your {@code VkExportMemoryAllocateInfoKHR} structure and attach it to
        ##VmaPoolCreateInfo{@code ::pMemoryAllocateNext} while creating the custom pool. Please note that the structure must remain alive and unchanged for the
        whole lifetime of the {@code VmaPool}, not only while creating it, as no copy of the structure is made, but its original pointer is used for each
        allocation instead.

        If you want to export all memory allocated by VMA from certain memory types, also dedicated allocations or other allocations made from default pools,
        an alternative solution is to fill in ##VmaAllocatorCreateInfo{@code ::pTypeExternalMemoryHandleTypes}. It should point to an array with
        {@code VkExternalMemoryHandleTypeFlagsKHR} to be automatically passed by the library through {@code VkExportMemoryAllocateInfoKHR} on each allocation
        made from a specific memory type. Please note that new versions of the library also support dedicated allocations created in custom pools.

        You should not mix these two methods in a way that allows to apply both to the same memory type. Otherwise, {@code VkExportMemoryAllocateInfoKHR}
        structure would be attached twice to the {@code pNext} chain of {@code VkMemoryAllocateInfo}.

        <h4>Custom alignment</h4>

        Buffers or images exported to a different API like OpenGL may require a different alignment, higher than the one used by the library automatically,
        queried from functions like {@code vkGetBufferMemoryRequirements}. To impose such alignment:

        You can create custom memory pools for such allocations. Set ##VmaPoolCreateInfo{@code ::minAllocationAlignment} member to the minimum alignment
        required for each allocation to be made out of this pool. The alignment actually used will be the maximum of this member and the alignment returned for
        the specific buffer or image from a function like {@code vkGetBufferMemoryRequirements}, which is called by VMA automatically.

        If you want to create a buffer with a specific minimum alignment out of default pools, use special function #CreateBufferWithAlignment(), which takes
        additional parameter {@code minAlignment}.

        Note the problem of alignment affects only resources placed inside bigger {@code VkDeviceMemory} blocks and not dedicated allocations, as these, by
        definition, always have {@code alignment = 0} because the resource is bound to the beginning of its dedicated block. You can ensure that an allocation
        is created as dedicated by using #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT. Contrary to Direct3D 12, Vulkan doesn't have a concept of alignment of the
        entire memory block passed on its allocation.

        <h4>Extended allocation information</h4>

        If you want to rely on VMA to allocate your buffers and images inside larger memory blocks, but you need to know the size of the entire block and
        whether the allocation was made with its own dedicated memory, use function #GetAllocationInfo2() to retrieve extended allocation information in
        structure {@code VmaAllocationInfo2}.

        <h3>Recommended usage patterns</h3>

        Vulkan gives great flexibility in memory allocation. This chapter shows the most common patterns.

        See also slides from talk: <a href="https://www.gdcvault.com/play/1025458/Advanced-Graphics-Techniques-Tutorial-New">Sawicki, Adam. Advanced Graphics
        Techniques Tutorial: Memory management in Vulkan and DX12. Game Developers Conference, 2018</a>

        <h4>GPU-only resource</h4> 

        <b>When:</b> Any resources that you frequently write and read on GPU, e.g. images used as color attachments (aka "render targets"), depth-stencil
        attachments, images/buffers used as storage image/buffer (aka "Unordered Access View (UAV)").

        <b>What to do:</b> Let the library select the optimal memory type, which will likely have {@code VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT}.
        ${codeBlock("""
VkImageCreateInfo imgCreateInfo = { VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO };
imgCreateInfo.imageType = VK_IMAGE_TYPE_2D;
imgCreateInfo.extent.width = 3840;
imgCreateInfo.extent.height = 2160;
imgCreateInfo.extent.depth = 1;
imgCreateInfo.mipLevels = 1;
imgCreateInfo.arrayLayers = 1;
imgCreateInfo.format = VK_FORMAT_R8G8B8A8_UNORM;
imgCreateInfo.tiling = VK_IMAGE_TILING_OPTIMAL;
imgCreateInfo.initialLayout = VK_IMAGE_LAYOUT_UNDEFINED;
imgCreateInfo.usage = VK_IMAGE_USAGE_SAMPLED_BIT | VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT;
imgCreateInfo.samples = VK_SAMPLE_COUNT_1_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;
allocCreateInfo.flags = VMA_ALLOCATION_CREATE_DEDICATED_MEMORY_BIT;
allocCreateInfo.priority = 1.0f;

VkImage img;
VmaAllocation alloc;
vmaCreateImage(allocator, &imgCreateInfo, &allocCreateInfo, &img, &alloc, nullptr);""")}

        <b>Also consider:</b> creating them as dedicated allocations using #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT, especially if they are large or if you plan
        to destroy and recreate them with different sizes e.g. when display resolution changes. Prefer to create such resources first and all other GPU
        resources (like textures and vertex buffers) later. When {@code VK_EXT_memory_priority} extension is enabled, it is also worth setting high priority to
        such allocation to decrease chances to be evicted to system memory by the operating system.

        <h4>Staging copy for upload</h4>

        <b>When:</b> A "staging" buffer than you want to map and fill from CPU code, then use as a source of transfer to some GPU resource.

        <b>What to do:</b> Use flag #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT. Let the library select the optimal memory type, which will always have
        {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT}.

        ${codeBlock("""
VkBufferCreateInfo bufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufCreateInfo.size = 65536;
bufCreateInfo.usage = VK_BUFFER_USAGE_TRANSFER_SRC_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;
allocCreateInfo.flags = VMA_ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT |
    VMA_ALLOCATION_CREATE_MAPPED_BIT;

VkBuffer buf;
VmaAllocation alloc;
VmaAllocationInfo allocInfo;
vmaCreateBuffer(allocator, &bufCreateInfo, &allocCreateInfo, &buf, &alloc, &allocInfo);

...

memcpy(allocInfo.pMappedData, myData, myDataSize);""")}

        <b>Also consider:</b> You can map the allocation using #MapMemory() or you can create it as persistenly mapped using #ALLOCATION_CREATE_MAPPED_BIT, as
        in the example above.

        <h4>Readback</h4>

        <b>When:</b> Buffers for data written by or transferred from the GPU that you want to read back on the CPU, e.g. results of some computations.

        <b>What to do:</b> Use flag #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT. Let the library select the optimal memory type, which will always have
        {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT} and {@code VK_MEMORY_PROPERTY_HOST_CACHED_BIT}.

        ${codeBlock("""
VkBufferCreateInfo bufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufCreateInfo.size = 65536;
bufCreateInfo.usage = VK_BUFFER_USAGE_TRANSFER_DST_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;
allocCreateInfo.flags = VMA_ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT |
    VMA_ALLOCATION_CREATE_MAPPED_BIT;

VkBuffer buf;
VmaAllocation alloc;
VmaAllocationInfo allocInfo;
vmaCreateBuffer(allocator, &bufCreateInfo, &allocCreateInfo, &buf, &alloc, &allocInfo);

...

const float* downloadedData = (const float*)allocInfo.pMappedData;""")}

        <h4>Advanced data uploading</h4>

        For resources that you frequently write on CPU via mapped pointer and frequently read on GPU e.g. as a uniform buffer (also called "dynamic"), multiple
        options are possible:

        ${ul(
            """
            Easiest solution is to have one copy of the resource in {@code HOST_VISIBLE} memory, even if it means system RAM (not {@code DEVICE_LOCAL}) on
            systems with a discrete graphics card, and make the device reach out to that resource directly. Reads performed by the device will then go through
            PCI Express bus. The performance of this access may be limited, but it may be fine depending on the size of this resource (whether it is small
            enough to quickly end up in GPU cache) and the sparsity of access.
            """,
            """
            On systems with unified memory (e.g. AMD APU or Intel integrated graphics, mobile chips), a memory type may be available that is both
            {@code HOST_VISIBLE} (available for mapping) and {@code DEVICE_LOCAL} (fast to access from the GPU). Then, it is likely the best choice for such
            type of resource.
            """,
            """
            Systems with a discrete graphics card and separate video memory may or may not expose a memory type that is both {@code HOST_VISIBLE} and
            {@code DEVICE_LOCAL}, also known as Base Address Register (BAR). If they do, it represents a piece of VRAM (or entire VRAM, if ReBAR is enabled in
            the motherboard BIOS) that is available to CPU for mapping. Writes performed by the host to that memory go through PCI Express bus. The performance
            of these writes may be limited, but it may be fine, especially on PCIe 4.0, as long as rules of using uncached and write-combined memory are
            followed - only sequential writes and no reads.
            """,
            """
            Finally, you may need or prefer to create a separate copy of the resource in {@code DEVICE_LOCAL} memory, a separate "staging" copy in
            {@code HOST_VISIBLE} memory and perform an explicit transfer command between them.
            """
        )}

        Thankfully, VMA offers an aid to create and use such resources in the the way optimal for the current Vulkan device. To help the library make the best
        choice, use flag #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT together with #ALLOCATION_CREATE_HOST_ACCESS_ALLOW_TRANSFER_INSTEAD_BIT. It will
        then prefer a memory type that is both {@code DEVICE_LOCAL} and {@code HOST_VISIBLE} (integrated memory or BAR), but if no such memory type is
        available or allocation from it fails (PC graphics cards have only 256 MB of BAR by default, unless ReBAR is supported and enabled in BIOS), it will
        fall back to {@code DEVICE_LOCAL} memory for fast GPU access. It is then up to you to detect that the allocation ended up in a memory type that is not
        {@code HOST_VISIBLE}, so you need to create another "staging" allocation and perform explicit transfers.

        ${codeBlock("""
VkBufferCreateInfo bufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
bufCreateInfo.size = 65536;
bufCreateInfo.usage = VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT | VK_BUFFER_USAGE_TRANSFER_DST_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;
allocCreateInfo.flags = VMA_ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT |
    VMA_ALLOCATION_CREATE_HOST_ACCESS_ALLOW_TRANSFER_INSTEAD_BIT |
    VMA_ALLOCATION_CREATE_MAPPED_BIT;

VkBuffer buf;
VmaAllocation alloc;
VmaAllocationInfo allocInfo;
VkResult result = vmaCreateBuffer(allocator, &bufCreateInfo, &allocCreateInfo, &buf, &alloc, &allocInfo);
// Check result...

VkMemoryPropertyFlags memPropFlags;
vmaGetAllocationMemoryProperties(allocator, alloc, &memPropFlags);

if(memPropFlags & VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT)
{
    // Allocation ended up in a mappable memory and is already mapped - write to it directly.

    // [Executed in runtime]:
    memcpy(allocInfo.pMappedData, myData, myDataSize);
    result = vmaFlushAllocation(allocator, alloc, 0, VK_WHOLE_SIZE);
    // Check result...

    VkBufferMemoryBarrier bufMemBarrier = { VK_STRUCTURE_TYPE_BUFFER_MEMORY_BARRIER };
    bufMemBarrier.srcAccessMask = VK_ACCESS_HOST_WRITE_BIT;
    bufMemBarrier.dstAccessMask = VK_ACCESS_UNIFORM_READ_BIT;
    bufMemBarrier.srcQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED;
    bufMemBarrier.dstQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED;
    bufMemBarrier.buffer = buf;
    bufMemBarrier.offset = 0;
    bufMemBarrier.size = VK_WHOLE_SIZE;

    vkCmdPipelineBarrier(cmdBuf, VK_PIPELINE_STAGE_HOST_BIT, VK_PIPELINE_STAGE_VERTEX_SHADER_BIT,
        0, 0, nullptr, 1, &bufMemBarrier, 0, nullptr);
}
else
{
    // Allocation ended up in a non-mappable memory - a transfer using a staging buffer is required.
    VkBufferCreateInfo stagingBufCreateInfo = { VK_STRUCTURE_TYPE_BUFFER_CREATE_INFO };
    stagingBufCreateInfo.size = 65536;
    stagingBufCreateInfo.usage = VK_BUFFER_USAGE_TRANSFER_SRC_BIT;

    VmaAllocationCreateInfo stagingAllocCreateInfo = {};
    stagingAllocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;
    stagingAllocCreateInfo.flags = VMA_ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT |
        VMA_ALLOCATION_CREATE_MAPPED_BIT;

    VkBuffer stagingBuf;
    VmaAllocation stagingAlloc;
    VmaAllocationInfo stagingAllocInfo;
    result = vmaCreateBuffer(allocator, &stagingBufCreateInfo, &stagingAllocCreateInfo,
        &stagingBuf, &stagingAlloc, &stagingAllocInfo);
    // Check result...

    // [Executed in runtime]:
    memcpy(stagingAllocInfo.pMappedData, myData, myDataSize);
    result = vmaFlushAllocation(allocator, stagingAlloc, 0, VK_WHOLE_SIZE);
    // Check result...

    VkBufferMemoryBarrier bufMemBarrier = { VK_STRUCTURE_TYPE_BUFFER_MEMORY_BARRIER };
    bufMemBarrier.srcAccessMask = VK_ACCESS_HOST_WRITE_BIT;
    bufMemBarrier.dstAccessMask = VK_ACCESS_TRANSFER_READ_BIT;
    bufMemBarrier.srcQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED;
    bufMemBarrier.dstQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED;
    bufMemBarrier.buffer = stagingBuf;
    bufMemBarrier.offset = 0;
    bufMemBarrier.size = VK_WHOLE_SIZE;

    vkCmdPipelineBarrier(cmdBuf, VK_PIPELINE_STAGE_HOST_BIT, VK_PIPELINE_STAGE_TRANSFER_BIT,
        0, 0, nullptr, 1, &bufMemBarrier, 0, nullptr);

    VkBufferCopy bufCopy = {
        0, // srcOffset
        0, // dstOffset,
        myDataSize, // size
    };

    vkCmdCopyBuffer(cmdBuf, stagingBuf, buf, 1, &bufCopy);

    VkBufferMemoryBarrier bufMemBarrier2 = { VK_STRUCTURE_TYPE_BUFFER_MEMORY_BARRIER };
    bufMemBarrier2.srcAccessMask = VK_ACCESS_TRANSFER_WRITE_BIT;
    bufMemBarrier2.dstAccessMask = VK_ACCESS_UNIFORM_READ_BIT; // We created a uniform buffer
    bufMemBarrier2.srcQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED;
    bufMemBarrier2.dstQueueFamilyIndex = VK_QUEUE_FAMILY_IGNORED;
    bufMemBarrier2.buffer = buf;
    bufMemBarrier2.offset = 0;
    bufMemBarrier2.size = VK_WHOLE_SIZE;

    vkCmdPipelineBarrier(cmdBuf, VK_PIPELINE_STAGE_TRANSFER_BIT, VK_PIPELINE_STAGE_VERTEX_SHADER_BIT,
        0, 0, nullptr, 1, &bufMemBarrier2, 0, nullptr);
}""")}

        <h4>Other use cases</h4>

        Here are some other, less obvious use cases and their recommended settings:
        ${ul(
            """
            An image that is used only as transfer source and destination, but it should stay on the device, as it is used to temporarily store a copy of some
            texture, e.g. from the current to the next frame, for temporal antialiasing or other temporal effects.
            ${ul(
                "Use {@code VkImageCreateInfo::usage = VK_IMAGE_USAGE_TRANSFER_SRC_BIT | VK_IMAGE_USAGE_TRANSFER_DST_BIT}",
                "Use {@code VmaAllocationCreateInfo::usage = VMA_MEMORY_USAGE_AUTO}"
            )}
            """,
            """
            An image that is used only as transfer source and destination, but it should be placed in the system RAM despite it doesn't need to be mapped,
            because it serves as a "swap" copy to evict least recently used textures from VRAM.
            ${ul(
                "Use {@code VkImageCreateInfo::usage = VK_IMAGE_USAGE_TRANSFER_SRC_BIT | VK_IMAGE_USAGE_TRANSFER_DST_BIT}",
                """
                Use {@code VmaAllocationCreateInfo::usage = VMA_MEMORY_USAGE_AUTO_PREFER_HOST}, as VMA needs a hint here to differentiate from the previous
                case.
                """
            )}
            """,
            """
            A buffer that you want to map and write from the CPU, directly read from the GPU (e.g. as a uniform or vertex buffer), but you have a clear
            preference to place it in device or host memory due to its large size.
            ${ul(
                "Use {@code VkBufferCreateInfo::usage = VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT}",
                "Use {@code VmaAllocationCreateInfo::usage = VMA_MEMORY_USAGE_AUTO_PREFER_DEVICE or VMA_MEMORY_USAGE_AUTO_PREFER_HOST}",
                "Use {@code VmaAllocationCreateInfo::flags = VMA_ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT}"
            )}
            """
        )}

        <h3>Configuration</h3>

        <h4>Custom host memory allocator</h4>

        If you use custom allocator for CPU memory rather than default operator {@code new} and {@code delete} from C++, you can make this library using your
        allocator as well by filling optional member ##VmaAllocatorCreateInfo{@code ::pAllocationCallbacks}. These functions will be passed to Vulkan, as well
        as used by the library itself to make any CPU-side allocations.

        <h4>Device memory allocation callbacks</h4>

        The library makes calls to {@code vkAllocateMemory()} and {@code vkFreeMemory()} internally. You can setup callbacks to be informed about these calls,
        e.g. for the purpose of gathering some statistics. To do it, fill optional member ##VmaAllocatorCreateInfo{@code ::pDeviceMemoryCallbacks}.

        <h4>Device heap memory limit</h4>

        When device memory of certain heap runs out of free space, new allocations may fail (returning error code) or they may succeed, silently pushing some
        existing memory blocks from GPU VRAM to system RAM (which degrades performance). This behavior is implementation-dependent - it depends on GPU vendor
        and graphics driver.

        On AMD cards it can be controlled while creating Vulkan device object by using {@code VK_AMD_memory_overallocation_behavior} extension, if available.

        Alternatively, if you want to test how your program behaves with limited amount of Vulkan devicememory available without switching your graphics card
        to one that really has smaller VRAM, you can use a feature of this library intended for this purpose. To do it, fill optional member
        ##VmaAllocatorCreateInfo{@code ::pHeapSizeLimit}.

        <h3>VK_KHR_dedicated_allocation</h3>

        {@code VK_KHR_dedicated_allocation} is a Vulkan extension which can be used to improve performance on some GPUs. It augments Vulkan API with
        possibility to query driver whether it prefers particular buffer or image to have its own, dedicated allocation (separate {@code VkDeviceMemory} block)
        for better efficiency - to be able to do some internal optimizations. The extension is supported by this library. It will be used automatically when
        enabled.

        It has been promoted to core Vulkan 1.1, so if you use eligible Vulkan version and inform VMA about it by setting
        ##VmaAllocatorCreateInfo{@code ::vulkanApiVersion}, you are all set.

        Otherwise, if you want to use it as an extension:

        1 . When creating Vulkan device, check if following 2 device extensions are supported (call {@code vkEnumerateDeviceExtensionProperties()}). If yes,
        enable them (fill ##VkDeviceCreateInfo{@code ::ppEnabledExtensionNames}).

        ${ul(
            "{@code VK_KHR_get_memory_requirements2}",
            "{@code VK_KHR_dedicated_allocation}"
        )}

        If you enabled these extensions:

        2 . Use #ALLOCATOR_CREATE_KHR_DEDICATED_ALLOCATION_BIT flag when creating your {@code VmaAllocator} to inform the library that you enabled required
        extensions and you want the library to use them.

        ${codeBlock("""
allocatorInfo.flags |= VMA_ALLOCATOR_CREATE_KHR_DEDICATED_ALLOCATION_BIT;

vmaCreateAllocator(&allocatorInfo, &allocator);""")}

        That is all. The extension will be automatically used whenever you create a buffer using #CreateBuffer() or image using #CreateImage().

        When using the extension together with Vulkan Validation Layer, you will receive warnings like this:
${codeBlock("""
vkBindBufferMemory(): Binding memory to buffer 0x33 but vkGetBufferMemoryRequirements() has not been called on that buffer.""")}

        It is OK, you should just ignore it. It happens because you use function {@code vkGetBufferMemoryRequirements2KHR()} instead of standard
        {@code vkGetBufferMemoryRequirements()}, while the validation layer seems to be unaware of it.

        To learn more about this extension, see:
        ${ul(
            """<a href="https://www.khronos.org/registry/vulkan/specs/1.2-extensions/html/chap50.html\#VK_KHR_dedicated_allocation">VK_KHR_dedicated_allocation in Vulkan specification</a>""",
            """<a href="https://asawicki.info/articles/VK_KHR_dedicated_allocation.php5">VK_KHR_dedicated_allocation unofficial manual</a>"""
        )}

        <h4>VK_EXT_memory_priority</h4>

        {@code VK_EXT_memory_priority} is a device extension that allows to pass additional "priority" value to Vulkan memory allocations that the
        implementation may use prefer certain buffers and images that are critical for performance to stay in device-local memory in cases when the memory is
        over-subscribed, while some others may be moved to the system memory.

        VMA offers convenient usage of this extension. If you enable it, you can pass "priority" parameter when creating allocations or custom pools and the
        library automatically passes the value to Vulkan using this extension.

        If you want to use this extension in connection with VMA, follow these steps:

        <h5>Initialization</h5>

        ${ol(
            """
            Call {@code vkEnumerateDeviceExtensionProperties} for the physical device. Check if the extension is supported - if returned array of
            {@code VkExtensionProperties} contains {@code "VK_EXT_memory_priority"}.
            """,
            """
            Call {@code vkGetPhysicalDeviceFeatures2} for the physical device instead of old {@code vkGetPhysicalDeviceFeatures}. Attach additional structure
            {@code VkPhysicalDeviceMemoryPriorityFeaturesEXT} to {@code VkPhysicalDeviceFeatures2::pNext} to be returned. Check if the device feature is really
            supported - check if {@code VkPhysicalDeviceMemoryPriorityFeaturesEXT::memoryPriority} is true.
            """,
            """
            While creating device with {@code vkCreateDevice}, enable this extension - add {@code "VK_EXT_memory_priority"} to the list passed as
            {@code VkDeviceCreateInfo::ppEnabledExtensionNames}.
            """,
            """
            While creating the device, also don't set {@code VkDeviceCreateInfo::pEnabledFeatures}. Fill in {@code VkPhysicalDeviceFeatures2} structure instead
            and pass it as {@code VkDeviceCreateInfo::pNext}. Enable this device feature - attach additional structure
            {@code VkPhysicalDeviceMemoryPriorityFeaturesEXT} to {@code VkPhysicalDeviceFeatures2::pNext} chain and set its member {@code memoryPriority} to
            {@code VK_TRUE}.
            """,
            """
            While creating {@code VmaAllocator} with #CreateAllocator() inform VMA that you have enabled this extension and feature - add
            #ALLOCATOR_CREATE_EXT_MEMORY_PRIORITY_BIT to ##VmaAllocatorCreateInfo{@code ::flags}.
            """
        )}

        <h5>Usage</h5> 

        When using this extension, you should initialize following member:
        ${ul(
            "##VmaAllocationCreateInfo{@code ::priority} when creating a dedicated allocation with #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT.",
            "##VmaPoolCreateInfo{@code ::priority} when creating a custom pool."
        )}

        It should be a floating-point value between {@code 0.0f} and {@code 1.0f}, where recommended default is {@code 0.5f}. Memory allocated with higher
        value can be treated by the Vulkan implementation as higher priority and so it can have lower chances of being pushed out to system memory,
        experiencing degraded performance.

        It might be a good idea to create performance-critical resources like color-attachment or depth-stencil images as dedicated and set high priority to
        them. For example:
        ${codeBlock("""
VkImageCreateInfo imgCreateInfo = { VK_STRUCTURE_TYPE_IMAGE_CREATE_INFO };
imgCreateInfo.imageType = VK_IMAGE_TYPE_2D;
imgCreateInfo.extent.width = 3840;
imgCreateInfo.extent.height = 2160;
imgCreateInfo.extent.depth = 1;
imgCreateInfo.mipLevels = 1;
imgCreateInfo.arrayLayers = 1;
imgCreateInfo.format = VK_FORMAT_R8G8B8A8_UNORM;
imgCreateInfo.tiling = VK_IMAGE_TILING_OPTIMAL;
imgCreateInfo.initialLayout = VK_IMAGE_LAYOUT_UNDEFINED;
imgCreateInfo.usage = VK_IMAGE_USAGE_SAMPLED_BIT | VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT;
imgCreateInfo.samples = VK_SAMPLE_COUNT_1_BIT;

VmaAllocationCreateInfo allocCreateInfo = {};
allocCreateInfo.usage = VMA_MEMORY_USAGE_AUTO;
allocCreateInfo.flags = VMA_ALLOCATION_CREATE_DEDICATED_MEMORY_BIT;
allocCreateInfo.priority = 1.0f;

VkImage img;
VmaAllocation alloc;
vmaCreateImage(allocator, &imgCreateInfo, &allocCreateInfo, &img, &alloc, nullptr);""")}

        {@code priority} member is ignored in the following situations:
        ${ul(
            """
            Allocations created in custom pools: They inherit the priority, along with all other allocation parameters from the parameters passed in
            ##VmaPoolCreateInfo when the pool was created.
            """,
            """
            Allocations created in default pools: They inherit the priority from the parameters VMA used when creating default pools, which means
            {@code priority == 0.5f}.
            """
        )}

        <h4>VK_AMD_device_coherent_memory</h4>

        {@code VK_AMD_device_coherent_memory} is a device extension that enables access to additional memory types with
        {@code VK_MEMORY_PROPERTY_DEVICE_COHERENT_BIT_AMD} and {@code VK_MEMORY_PROPERTY_DEVICE_UNCACHED_BIT_AMD} flag. It is useful mostly for allocation of
        buffers intended for writing "breadcrumb markers" in between passes or draw calls, which in turn are useful for debugging GPU crash/hang/TDR cases.

        When the extension is available but has not been enabled, Vulkan physical device still exposes those memory types, but their usage is forbidden. VMA
        automatically takes care of that - it returns {@code VK_ERROR_FEATURE_NOT_PRESENT} when an attempt to allocate memory of such type is made.

        If you want to use this extension in connection with VMA, follow these steps:

        <h5>Initialization</h5>
        ${ol(
            """
            Call {@code vkEnumerateDeviceExtensionProperties} for the physical device. Check if the extension is supported - if returned array of
            {@code VkExtensionProperties} contains {@code "VK_AMD_device_coherent_memory"}.
            """,
            """
            Call {@code vkGetPhysicalDeviceFeatures2} for the physical device instead of old {@code vkGetPhysicalDeviceFeatures}. Attach additional structure
            {@code VkPhysicalDeviceCoherentMemoryFeaturesAMD} to {@code VkPhysicalDeviceFeatures2::pNext} to be returned. Check if the device feature is really
            supported - check if {@code VkPhysicalDeviceCoherentMemoryFeaturesAMD::deviceCoherentMemory} is true.
            """,
            """
            While creating device with {@code vkCreateDevice}, enable this extension - add {@code "VK_AMD_device_coherent_memory"} to the list passed as
            {@code VkDeviceCreateInfo::ppEnabledExtensionNames}.
            """,
            """
            While creating the device, also don't set {@code VkDeviceCreateInfo::pEnabledFeatures}. Fill in {@code VkPhysicalDeviceFeatures2} structure instead
            and pass it as {@code VkDeviceCreateInfo::pNext}. Enable this device feature - attach additional structure
            {@code VkPhysicalDeviceCoherentMemoryFeaturesAMD} to {@code VkPhysicalDeviceFeatures2::pNext} and set its member {@code deviceCoherentMemory} to
            {@code VK_TRUE}.
            """,
            """
            While creating {@code VmaAllocator} with #CreateAllocator() inform VMA that you have enabled this extension and feature - add
            #ALLOCATOR_CREATE_AMD_DEVICE_COHERENT_MEMORY_BIT to ##VmaAllocatorCreateInfo{@code ::flags}.
            """
        )}

        <h5>Usage</h5>

        After following steps described above, you can create VMA allocations and custom pools out of the special {@code DEVICE_COHERENT} and
        {@code DEVICE_UNCACHED} memory types on eligible devices. There are multiple ways to do it, for example:
        ${ul(
            """
            You can request or prefer to allocate out of such memory types by adding {@code VK_MEMORY_PROPERTY_DEVICE_UNCACHED_BIT_AMD} to
            ##VmaAllocationCreateInfo{@code ::requiredFlags} or {@code VmaAllocationCreateInfo::preferredFlags}. Those flags can be freely mixed with other
            ways of choosing memory type, like setting {@code VmaAllocationCreateInfo::usage}.
            """,
            """
            If you manually found memory type index to use for this purpose, force allocation from this specific index by setting
            {@code VmaAllocationCreateInfo::memoryTypeBits = 1u << index}.
            """
        )}

        <h5>More information</h5>

        To learn more about this extension, see
        <a href="https://www.khronos.org/registry/vulkan/specs/1.2-extensions/man/html/VK_AMD_device_coherent_memory.html">VK_AMD_device_coherent_memory in Vulkan specification</a>.

        Example use of this extension can be found in the code of the sample and test suite accompanying this library.

        <h4>Enabling buffer device address</h4>

        Device extension {@code VK_KHR_buffer_device_address} allows to fetch raw GPU pointer to a buffer and pass it for usage in a shader code. It has been
        promoted to core Vulkan 1.2.

        If you want to use this feature in connection with VMA, follow these steps:

        <h5>Initialization</h5>
        ${ol(
            """
            (For Vulkan version &lt; 1.2) Call {@code vkEnumerateDeviceExtensionProperties} for the physical device. Check if the extension is supported - if
            returned array of {@code VkExtensionProperties} contains {@code "VK_KHR_buffer_device_address"}.
            """,
            """
            Call {@code vkGetPhysicalDeviceFeatures2} for the physical device instead of old {@code vkGetPhysicalDeviceFeatures}. Attach additional structure
            {@code VkPhysicalDeviceBufferDeviceAddressFeatures*} to {@code VkPhysicalDeviceFeatures2::pNext} to be returned. Check if the device feature is
            really supported - check if {@code VkPhysicalDeviceBufferDeviceAddressFeatures::bufferDeviceAddress} is true.
            """,
            """
            (For Vulkan version &lt; 1.2) While creating device with {@code vkCreateDevice}, enable this extension - add {@code "VK_KHR_buffer_device_address"}
            to the list passed as {@code VkDeviceCreateInfo::ppEnabledExtensionNames}.
            """,
            """
            While creating the device, also don't set {@code VkDeviceCreateInfo::pEnabledFeatures}. Fill in {@code VkPhysicalDeviceFeatures2} structure instead
            and pass it as {@code VkDeviceCreateInfo::pNext}. Enable this device feature - attach additional structure
            {@code VkPhysicalDeviceBufferDeviceAddressFeatures*} to {@code VkPhysicalDeviceFeatures2::pNext} and set its member {@code bufferDeviceAddress} to
            {@code VK_TRUE}.
            """,
            """
            While creating {@code VmaAllocator} with #CreateAllocator() inform VMA that you have enabled this feature - add
            #ALLOCATOR_CREATE_BUFFER_DEVICE_ADDRESS_BIT to ##VmaAllocatorCreateInfo{@code ::flags}.
            """
        )}

        <h5>Usage</h5> 

        After following steps described above, you can create buffers with {@code VK_BUFFER_USAGE_SHADER_DEVICE_ADDRESS_BIT*} using VMA. The library
        automatically adds {@code VK_MEMORY_ALLOCATE_DEVICE_ADDRESS_BIT*} to allocated memory blocks wherever it might be needed.

        Please note that the library supports only {@code VK_BUFFER_USAGE_SHADER_DEVICE_ADDRESS_BIT*}. The second part of this functionality related to
        "capture and replay" is not supported, as it is intended for usage in debugging tools like RenderDoc, not in everyday Vulkan usage.

        <h5>More information</h5> 

        To learn more about this extension, see
        <a href="https://www.khronos.org/registry/vulkan/specs/1.2-extensions/html/chap46.html\#VK_KHR_buffer_device_address">VK_KHR_buffer_device_address in Vulkan specification</a>

        Example use of this extension can be found in the code of the sample and test suite accompanying this library.

        <h3>General considerations</h3>

        <h4>Thread safety</h4>

        ${ul(
            """
            The library has no global state, so separate {@code VmaAllocator} objects can be used independently. There should be no need to create multiple
            such objects though - one per {@code VkDevice} is enough.
            """,
            """
            By default, all calls to functions that take {@code VmaAllocator} as first parameter are safe to call from multiple threads simultaneously because
            they are synchronized internally when needed. This includes allocation and deallocation from default memory pool, as well as custom
            {@code VmaPool}.
            """,
            """
            When the allocator is created with #ALLOCATOR_CREATE_EXTERNALLY_SYNCHRONIZED_BIT flag, calls to functions that take such {@code VmaAllocator}
            object must be synchronized externally.
            """,
            """
            Access to a {@code VmaAllocation} object must be externally synchronized. For example, you must not call #GetAllocationInfo() and #MapMemory() from
            different threads at the same time if you pass the same {@code VmaAllocation} object to these functions.
            """,
            "{@code VmaVirtualBlock} is not safe to be used from multiple threads simultaneously."
        )}

        <h4>Validation layer warnings</h4>

        When using this library, you can meet following types of warnings issued by Vulkan validation layer. They don't necessarily indicate a bug, so you may
        need to just ignore them.

        ${ul(
            """
            <i>{@code vkBindBufferMemory()}: Binding memory to buffer {@code 0xeb8e4} but {@code vkGetBufferMemoryRequirements()} has not been called on that
            buffer.</i>

            It happens when {@code VK_KHR_dedicated_allocation} extension is enabled. {@code vkGetBufferMemoryRequirements2KHR} function is used instead, while
            validation layer seems to be unaware of it.
            """,
            """
            <i>Mapping an image with layout {@code VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL} can result in undefined behavior if this memory is used by
            the device. Only {@code GENERAL} or {@code PREINITIALIZED} should be used.</i>

            It happens when you map a buffer or image, because the library maps entire {@code VkDeviceMemory} block, where different types of images and
            buffers may end up together, especially on GPUs with unified memory like Intel.
            """,
            """
            <i>Non-linear image {@code 0xebc91} is aliased with linear buffer {@code 0xeb8e4} which may indicate a bug.</i>

            It may happen when you use defragmentation.
            """
        )}

        <h4>Allocation algorithm</h4>

        The library uses following algorithm for allocation, in order:

        ${ol(
            "Try to find free range of memory in existing blocks.",
            "If failed, try to create a new block of {@code VkDeviceMemor}y, with preferred block size.",
            "If failed, try to create such block with {@code size / 2}, {@code size / 4}, {@code size / 8}.",
            "If failed, try to allocate separate {@code VkDeviceMemory} for this allocation, just like when you use #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT.",
            "If failed, choose other memory type that meets the requirements specified in ##VmaAllocationCreateInfo and go to point 1.",
            "If failed, return {@code VK_ERROR_OUT_OF_DEVICE_MEMORY}."
        )}

        <h4>Features not supported</h4>

        Features deliberately excluded from the scope of this library:
        ${ul(
            """
            <b>Data transfer</b>. Uploading (streaming) and downloading data of buffers and images between CPU and GPU memory and related synchronization is
            responsibility of the user.

            Defining some "texture" object that would automatically stream its data from a staging copy in CPU memory to GPU memory would rather be a feature
            of another, higher-level library implemented on top of VMA. VMA doesn't record any commands to a {@code VkCommandBuffer}. It just allocates memory.
            """,
            """
            <b>Recreation of buffers and images</b>. Although the library has functions for buffer and image creation: #CreateBuffer(), #CreateImage(), you
            need to recreate these objects yourself after defragmentation. That is because the big structures {@code VkBufferCreateInfo},
            {@code VkImageCreateInfo} are not stored in {@code VmaAllocation} object.
            """,
            """
            <b>Handling CPU memory allocation failures</b>. When dynamically creating small C++ objects in CPU memory (not Vulkan memory), allocation failures
            are not checked and handled gracefully, because that would complicate code significantly and is usually not needed in desktop PC applications
            anyway. Success of an allocation is just checked with an assert.
            """,
            """
            <b>Code free of any compiler warnings</b>. Maintaining the library to compile and work correctly on so many different platforms is hard enough. Being free
            of any warnings, on any version of any compiler, is simply not feasible.

            There are many preprocessor macros that make some variables unused, function parameters unreferenced, or conditional expressions constant in some
            configurations. The code of this library should not be bigger or more complicated just to silence these warnings. It is recommended to disable such
            warnings instead.
            """,
            """
            This is a C++ library with C interface. <b>Bindings or ports to any other programming languages</b> are welcome as external projects but are not
            going to be included into this repository.
            """
        )}
        """

    EnumConstant(
        "Flags for created {@code VmaAllocator}. ({@code VmaAllocatorCreateFlagBits})",

        "ALLOCATOR_CREATE_EXTERNALLY_SYNCHRONIZED_BIT".enum(
            """
            Allocator and all objects created from it will not be synchronized internally, so you must guarantee they are used from only one thread at a time
            or synchronized externally by you.

            Using this flag may increase performance because internal mutexes are not used.
            """,
            0x00000001
        ),
        "ALLOCATOR_CREATE_KHR_DEDICATED_ALLOCATION_BIT".enum(
            """
            Enables usage of {@code VK_KHR_dedicated_allocation} extension.

            The flag works only if ##VmaAllocatorCreateInfo{@code ::vulkanApiVersion == VK_API_VERSION_1_0}. When it is {@code VK_API_VERSION_1_1}, the flag is
            ignored because the extension has been promoted to Vulkan 1.1.

            Using this extension will automatically allocate dedicated blocks of memory for some buffers and images instead of suballocating place for them out
            of bigger memory blocks (as if you explicitly used #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT flag) when it is recommended by the driver. It may
            improve performance on some GPUs.

            You may set this flag only if you found out that following device extensions are supported, you enabled them while creating Vulkan device passed as
            ##VmaAllocatorCreateInfo{@code ::device}, and you want them to be used internally by this library:
            ${ul(
                "{@code VK_KHR_get_memory_requirements2} (device extension)",
                "{@code VK_KHR_dedicated_allocation} (device extension)"
            )}
            When this flag is set, you can experience following warnings reported by Vulkan validation layer. You can ignore them.
            ${codeBlock("""
> vkBindBufferMemory(): Binding memory to buffer 0x2d but vkGetBufferMemoryRequirements() has not been called on that buffer.""")}
            """,
            0x00000002
        ),
        "ALLOCATOR_CREATE_KHR_BIND_MEMORY2_BIT".enum(
            """
            Enables usage of {@code VK_KHR_bind_memory2} extension.

            The flag works only if ##VmaAllocatorCreateInfo{@code ::vulkanApiVersion == VK_API_VERSION_1_0}. When it is {@code VK_API_VERSION_1_1}, the flag is
            ignored because the extension has been promoted to Vulkan 1.1.

            You may set this flag only if you found out that this device extension is supported, you enabled it while creating Vulkan device passed as
            ##VmaAllocatorCreateInfo{@code ::device}, and you want it to be used internally by this library.

            The extension provides functions {@code vkBindBufferMemory2KHR} and {@code vkBindImageMemory2KHR}, which allow to pass a chain of {@code pNext}
            structures while binding. This flag is required if you use {@code pNext} parameter in #BindBufferMemory2() or #BindImageMemory2().
            """,
            0x00000004
        ),
        "ALLOCATOR_CREATE_EXT_MEMORY_BUDGET_BIT".enum(
            """
            Enables usage of {@code VK_EXT_memory_budget} extension.

            You may set this flag only if you found out that this device extension is supported, you enabled it while creating Vulkan device passed as
            ##VmaAllocatorCreateInfo{@code ::device}, and you want it to be used internally by this library, along with another instance extension
            {@code VK_KHR_get_physical_device_properties2}, which is required by it (or Vulkan 1.1, where this extension is promoted).

            The extension provides query for current memory usage and budget, which will probably be more accurate than an estimation used by the library
            otherwise.
            """,
            0x00000008
        ),
        "ALLOCATOR_CREATE_AMD_DEVICE_COHERENT_MEMORY_BIT".enum(
            """
            Enables usage of {@code VK_AMD_device_coherent_memory} extension.

            You may set this flag only if you:
            ${ul(
                "found out that this device extension is supported and enabled it while creating Vulkan device passed as VmaAllocatorCreateInfo::device,",
                "checked that `VkPhysicalDeviceCoherentMemoryFeaturesAMD::deviceCoherentMemory` is true and set it while creating the Vulkan device,",
                "want it to be used internally by this library."
            )}

            The extension and accompanying device feature provide access to memory types with `VK_MEMORY_PROPERTY_DEVICE_COHERENT_BIT_AMD` and `VK_MEMORY_PROPERTY_DEVICE_UNCACHED_BIT_AMD` flags. They are useful mostly for writing breadcrumb markers - a common method for debugging GPU crash/hang/TDR.

            When the extension is not enabled, such memory types are still enumerated, but their usage is illegal. To protect from this error, if you don't create the allocator with this flag, it will refuse to allocate any memory or create a custom pool in such memory type, returning `VK_ERROR_FEATURE_NOT_PRESENT`.
            """,
            0x00000010
        ),
        "ALLOCATOR_CREATE_BUFFER_DEVICE_ADDRESS_BIT".enum(
            """
            Enables usage of "buffer device address" feature, which allows you to use function {@code vkGetBufferDeviceAddress*} to get raw GPU pointer to a
            buffer and pass it for usage inside a shader.

            You may set this flag only if you:
            ${ol(
                """
                (For Vulkan version &lt; 1.2) Found as available and enabled device extension {@code VK_KHR_buffer_device_address}. This extension is promoted
                to core Vulkan 1.2.
                """,
                "Found as available and enabled device feature {@code VkPhysicalDeviceBufferDeviceAddressFeatures::bufferDeviceAddress}."
            )}

            When this flag is set, you can create buffers with {@code VK_BUFFER_USAGE_SHADER_DEVICE_ADDRESS_BIT} using VMA. The library automatically adds
            {@code VK_MEMORY_ALLOCATE_DEVICE_ADDRESS_BIT} to allocated memory blocks wherever it might be needed.
            """,
            0x00000020
        ),
        "ALLOCATOR_CREATE_EXT_MEMORY_PRIORITY_BIT".enum(
            """
            Enables usage of {@code VK_EXT_memory_priority} extension in the library.

            You may set this flag only if you found available and enabled this device extension, along with
            {@code VkPhysicalDeviceMemoryPriorityFeaturesEXT::memoryPriority == VK_TRUE}, while creating Vulkan device passed as
            {@code VmaAllocatorCreateInfo::device}.

            When this flag is used, {@code VmaAllocationCreateInfo::priority} and {@code VmaPoolCreateInfo::priority} are used to set priorities of allocated
            Vulkan memory. Without it, these variables are ignored.

            A priority must be a floating-point value between 0 and 1, indicating the priority of the allocation relative to other memory allocations. Larger
            values are higher priority. The granularity of the priorities is implementation-dependent. It is automatically passed to every call to
            {@code vkAllocateMemory} done by the library using structure {@code VkMemoryPriorityAllocateInfoEXT}. The value to be used for default priority is
            0.5. For more details, see the documentation of the {@code VK_EXT_memory_priority} extension.
            """,
            0x00000040
        ),
        "ALLOCATOR_CREATE_KHR_MAINTENANCE4_BIT".enum(
            """
            Enables usage of {@code VK_KHR_maintenance4} extension in the library.

            You may set this flag only if you found available and enabled this device extension, while creating Vulkan device passed as
            {@code VmaAllocatorCreateInfo::device}.
            """,
            0x00000080
        ),
        "ALLOCATOR_CREATE_KHR_MAINTENANCE5_BIT".enum(
            """
            Enables usage of {@code VK_KHR_maintenance5} extension in the library.

            You should set this flag if you found available and enabled this device extension, while creating Vulkan device passed as
            {@code VmaAllocatorCreateInfo::device}.
            """,
            0x00000100
        )
    )

    EnumConstant(
        "{@code VmaMemoryUsage}",

        "MEMORY_USAGE_UNKNOWN".enum(
            """
            No intended memory usage specified.

            Use other members of ##VmaAllocationCreateInfo to specify your requirements.
            """,
            "0"
        ),
        "MEMORY_USAGE_GPU_ONLY".enum(
            """
            Obsolete, preserved for backward compatibility.

            Prefers {@code VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT}.
            """
        ),
        "MEMORY_USAGE_CPU_ONLY".enum(
            """
            Obsolete, preserved for backward compatibility.

            Guarantees {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT} and {@code VK_MEMORY_PROPERTY_HOST_COHERENT_BIT}.
            """
        ),
        "MEMORY_USAGE_CPU_TO_GPU".enum(
            """
            Obsolete, preserved for backward compatibility.

            Guarantees {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT}, prefers {@code VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT}.
            """
        ),
        "MEMORY_USAGE_GPU_TO_CPU".enum(
            """
            Obsolete, preserved for backward compatibility.

            Guarantees {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT}, prefers {@code VK_MEMORY_PROPERTY_HOST_CACHED_BIT}.
            """
        ),
        "MEMORY_USAGE_CPU_COPY".enum(
            """
            Obsolete, preserved for backward compatibility.

            Prefers not {@code VK_MEMORY_PROPERTY_DEVICE_LOCAL_BIT}.
            """
        ),
        "MEMORY_USAGE_GPU_LAZILY_ALLOCATED".enum(
            """
            Lazily allocated GPU memory having {@code VK_MEMORY_PROPERTY_LAZILY_ALLOCATED_BIT}.

            Exists mostly on mobile platforms. Using it on desktop PC or other GPUs with no such memory type present will fail the allocation.

            Usage: Memory for transient attachment images (color attachments, depth attachments etc.), created with
            {@code VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT}.

            Allocations with this usage are always created as dedicated - it implies #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT.
            """
        ),
        "MEMORY_USAGE_AUTO".enum(
            """
            Selects best memory type automatically. This flag is recommended for most common use cases.

            When using this flag, if you want to map the allocation (using #MapMemory() or #ALLOCATION_CREATE_MAPPED_BIT), you must pass one of the flags:
            #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT or #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT in ##VmaAllocationCreateInfo{@code ::flags}.

            It can be used only with functions that let the library know {@code VkBufferCreateInfo} or {@code VkImageCreateInfo}, e.g. #CreateBuffer(),
            #CreateImage(), #FindMemoryTypeIndexForBufferInfo(), #FindMemoryTypeIndexForImageInfo() and not with generic memory allocation functions.
            """
        ),
        "MEMORY_USAGE_AUTO_PREFER_DEVICE".enum(
            """
            Selects best memory type automatically with preference for GPU (device) memory.

            When using this flag, if you want to map the allocation (using #MapMemory() or #ALLOCATION_CREATE_MAPPED_BIT), you must pass one of the flags:
            #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT or #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT in ##VmaAllocationCreateInfo{@code ::flags}.

            It can be used only with functions that let the library know {@code VkBufferCreateInfo} or {@code VkImageCreateInfo}, e.g. #CreateBuffer(),
            #CreateImage(), #FindMemoryTypeIndexForBufferInfo(), #FindMemoryTypeIndexForImageInfo() and not with generic memory allocation functions.
            """
        ),
        "MEMORY_USAGE_AUTO_PREFER_HOST".enum(
            """
            Selects best memory type automatically with preference for CPU (host) memory.

            When using this flag, if you want to map the allocation (using #MapMemory() or #ALLOCATION_CREATE_MAPPED_BIT), you must pass one of the flags:
            #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT or #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT in ##VmaAllocationCreateInfo{@code ::flags}.

            It can be used only with functions that let the library know {@code VkBufferCreateInfo} or {@code VkImageCreateInfo}, e.g. #CreateBuffer(),
            #CreateImage(), #FindMemoryTypeIndexForBufferInfo(), #FindMemoryTypeIndexForImageInfo() and not with generic memory allocation functions.
            """
        )
    )

    EnumConstant(
        "Flags to be passed as ##VmaAllocationCreateInfo{@code ::flags}. ({@code VmaAllocationCreateFlagBits})",

        "ALLOCATION_CREATE_DEDICATED_MEMORY_BIT".enum(
            """
            Set this flag if the allocation should have its own memory block.

            Use it for special, big resources, like fullscreen images used as attachments.

            If you use this flag while creating a buffer or an image, {@code VkMemoryDedicatedAllocateInfo} structure is applied if possible.
            """,
            "0x00000001"
        ),
        "ALLOCATION_CREATE_NEVER_ALLOCATE_BIT".enum(
            """
            Set this flag to only try to allocate from existing {@code VkDeviceMemory} blocks and never create new such block.

            If new allocation cannot be placed in any of the existing blocks, allocation fails with {@code VK_ERROR_OUT_OF_DEVICE_MEMORY} error.

            You should not use #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT and #ALLOCATION_CREATE_NEVER_ALLOCATE_BIT at the same time. It makes no sense.
            """,
            "0x00000002"
        ),
        "ALLOCATION_CREATE_MAPPED_BIT".enum(
            """
            Set this flag to use a memory that will be persistently mapped and retrieve pointer to it.

            Pointer to mapped memory will be returned through ##VmaAllocationInfo{@code ::pMappedData}.

            It is valid to use this flag for allocation made from memory type that is not {@code HOST_VISIBLE}. This flag is then ignored and memory is not
            mapped. This is useful if you need an allocation that is efficient to use on GPU ({@code DEVICE_LOCAL}) and still want to map it directly if
            possible on platforms that support it (e.g. Intel GPU).
            """,
            "0x00000004"
        ),
        "ALLOCATION_CREATE_USER_DATA_COPY_STRING_BIT".enum(
            """
            Preserved for backward compatibility. Consider using #SetAllocationName() instead.

            Set this flag to treat ##VmaAllocationCreateInfo{@code ::pUserData} as pointer to a null-terminated string. Instead of copying pointer value, a
            local copy of the string is made and stored in allocation's {@code pName}. The string is automatically freed together with the allocation. It is
            also used in #BuildStatsString().
            """,
            "0x00000020"
        ),
        "ALLOCATION_CREATE_UPPER_ADDRESS_BIT".enum(
            """
            Allocation will be created from upper stack in a double stack pool.

            This flag is only allowed for custom pools created with #POOL_CREATE_LINEAR_ALGORITHM_BIT flag.
            """,
            "0x00000040"
        ),
        "ALLOCATION_CREATE_DONT_BIND_BIT".enum(
            """
            Create both buffer/image and allocation, but don't bind them together.

            It is useful when you want to bind yourself to do some more advanced binding, e.g. using some extensions. The flag is meaningful only with
            functions that bind by default: #CreateBuffer(), #CreateImage(). Otherwise it is ignored.
            
            If you want to make sure the new buffer/image is not tied to the new memory allocation through {@code VkMemoryDedicatedAllocateInfoKHR} structure
            in case the allocation ends up in its own memory block, use also flag #ALLOCATION_CREATE_CAN_ALIAS_BIT.
            """,
            "0x00000080"
        ),
        "ALLOCATION_CREATE_WITHIN_BUDGET_BIT".enum(
            """
            Create allocation only if additional device memory required for it, if any, won't exceed memory budget.

            Otherwise return {@code VK_ERROR_OUT_OF_DEVICE_MEMORY}.
            """,
            "0x00000100"
        ),
        "ALLOCATION_CREATE_CAN_ALIAS_BIT".enum(
            """
            Set this flag if the allocated memory will have aliasing resources.

            Usage of this flag prevents supplying {@code VkMemoryDedicatedAllocateInfoKHR} when #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT is specified.
            Otherwise created dedicated memory will not be suitable for aliasing resources, resulting in Vulkan Validation Layer errors.
            """,
            "0x00000200"
        ),
        "ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT".enum(
            """
            Requests possibility to map the allocation (using #MapMemory() or #ALLOCATION_CREATE_MAPPED_BIT).

            ${ul(
                """
                If you use #MEMORY_USAGE_AUTO or other {@code VMA_MEMORY_USAGE_AUTO*} value, you must use this flag to be able to map the allocation.
                Otherwise, mapping is incorrect.
                """,
                """
                If you use other value of {@code VmaMemoryUsage}, this flag is ignored and mapping is always possible in memory types that are
                {@code HOST_VISIBLE}. This includes allocations created in custom memory pools.
                """
            )}

            Declares that mapped memory will only be written sequentially, e.g. using {@code memcpy()} or a loop writing number-by-number, never read or
            accessed randomly, so a memory type can be selected that is uncached and write-combined.

            Warning: Violating this declaration may work correctly, but will likely be very slow. Watch out for implicit reads introduced by doing e.g.
            {@code pMappedData[i] += x;}. Better prepare your data in a local variable and {@code memcpy()} it to the mapped pointer all at once.
            """,
            "0x00000400"
        ),
        "ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT".enum(
            """
            Requests possibility to map the allocation (using MapMemory() or #ALLOCATION_CREATE_MAPPED_BIT).

            ${ul(
                """
                If you use #MEMORY_USAGE_AUTO or other {@code VMA_MEMORY_USAGE_AUTO*} value, you must use this flag to be able to map the allocation.
                Otherwise, mapping is incorrect.
                """,
                """
                If you use other value of {@code VmaMemoryUsage}, this flag is ignored and mapping is always possible in memory types that are
                {@code HOST_VISIBLE}. This includes allocations created in custom memory pools.
                """
            )}

            Declares that mapped memory can be read, written, and accessed in random order, so a {@code HOST_CACHED} memory type is preferred.
            """,
            "0x00000800"
        ),
        "ALLOCATION_CREATE_HOST_ACCESS_ALLOW_TRANSFER_INSTEAD_BIT".enum(
            """
            Together with #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT or #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT, it says that despite request for
            host access, a not-{@code HOST_VISIBLE} memory type can be selected if it may improve performance.

            By using this flag, you declare that you will check if the allocation ended up in a {@code HOST_VISIBLE} memory type (e.g. using
            #GetAllocationMemoryProperties()) and if not, you will create some "staging" buffer and issue an explicit transfer to write/read your data. To
            prepare for this possibility, don't forget to add appropriate flags like {@code VK_BUFFER_USAGE_TRANSFER_DST_BIT},
            {@code VK_BUFFER_USAGE_TRANSFER_SRC_BIT} to the parameters of created buffer or image.
            """,
            "0x00001000"
        ),
        "ALLOCATION_CREATE_STRATEGY_MIN_MEMORY_BIT".enum(
            """
            Allocation strategy that chooses smallest possible free range for the allocation to minimize memory usage and fragmentation, possibly at the
            expense of allocation time.
            """,
            "0x00010000"
        ),
        "ALLOCATION_CREATE_STRATEGY_MIN_TIME_BIT".enum(
            """
            Allocation strategy that chooses first suitable free range for the allocation - not necessarily in terms of the smallest offset but the one that is
            easiest and fastest to find to minimize allocation time, possibly at the expense of allocation quality.
            """,
            "0x00020000"
        ),
        "ALLOCATION_CREATE_STRATEGY_MIN_OFFSET_BIT".enum(
            """
            Allocation strategy that chooses always the lowest offset in available space.

            This is not the most efficient strategy but achieves highly packed data. Used internally by defragmentation, not recommended in typical usage.
            """,
            "0x00040000"
        ),
        "ALLOCATION_CREATE_STRATEGY_BEST_FIT_BIT".enum(
            "Alias to #ALLOCATION_CREATE_STRATEGY_MIN_MEMORY_BIT.",
            "VMA_ALLOCATION_CREATE_STRATEGY_MIN_MEMORY_BIT"
        ),
        "VMA_ALLOCATION_CREATE_STRATEGY_FIRST_FIT_BIT".enum(
            "Alias to #ALLOCATION_CREATE_STRATEGY_MIN_TIME_BIT.",
            "VMA_ALLOCATION_CREATE_STRATEGY_MIN_TIME_BIT"
        ),
        "ALLOCATION_CREATE_STRATEGY_MASK".enum(
            "A bit mask to extract only {@code STRATEGY} bits from entire set of flags.",
            "VMA_ALLOCATION_CREATE_STRATEGY_MIN_MEMORY_BIT | VMA_ALLOCATION_CREATE_STRATEGY_MIN_TIME_BIT | VMA_ALLOCATION_CREATE_STRATEGY_MIN_OFFSET_BIT"
        )
    )

    EnumConstant(
        "Flags to be passed as ##VmaPoolCreateInfo{@code ::flags}. ({@code VmaPoolCreateFlagBits})",

        "POOL_CREATE_IGNORE_BUFFER_IMAGE_GRANULARITY_BIT".enum(
            """
            Use this flag if you always allocate only buffers and linear images or only optimal images out of this pool and so Buffer-Image Granularity can be
            ignored.

            This is an optional optimization flag.

            If you always allocate using #CreateBuffer(), #CreateImage(), #AllocateMemoryForBuffer(), then you don't need to use it because allocator knows
            exact type of your allocations so it can handle Buffer-Image Granularity in the optimal way.

            If you also allocate using #AllocateMemoryForImage() or #AllocateMemory(), exact type of such allocations is not known, so allocator must be
            conservative in handling Buffer-Image Granularity, which can lead to suboptimal allocation (wasted memory). In that case, if you can make sure you
            always allocate only buffers and linear images or only optimal images out of this pool, use this flag to make allocator disregard Buffer-Image
            Granularity and so make allocations faster and more optimal.
            """,
            0x00000002
        ),
        "POOL_CREATE_LINEAR_ALGORITHM_BIT".enum(
            """
            Enables alternative, linear allocation algorithm in this pool.

            Specify this flag to enable linear allocation algorithm, which always creates new allocations after last one and doesn't reuse space from
            allocations freed in between. It trades memory consumption for simplified algorithm and data structure, which has better performance and uses less
            memory for metadata.

            By using this flag, you can achieve behavior of free-at-once, stack, ring buffer, and double stack.
            """,
            0x00000004
        ),
        "POOL_CREATE_ALGORITHM_MASK".enum(
            "Bit mask to extract only {@code ALGORITHM} bits from entire set of flags.",
            "VMA_POOL_CREATE_LINEAR_ALGORITHM_BIT"
        )
    )

    EnumConstant(
        "Flags to be passed as ##VmaDefragmentationInfo{@code ::flags}. {@code VmaDefragmentationFlagBits}",

        "DEFRAGMENTATION_FLAG_ALGORITHM_FAST_BIT".enum(
            """
            Use simple but fast algorithm for defragmentation.

            May not achieve best results but will require least time to compute and least allocations to copy.
            """,
            "0x1"
        ),
        "DEFRAGMENTATION_FLAG_ALGORITHM_BALANCED_BIT".enum(
            """
            Default defragmentation algorithm, applied also when no {@code ALGORITHM} flag is specified.
    
            Offers a balance between defragmentation quality and the amount of allocations and bytes that need to be moved.
            """,
            "0x2"
        ),
        "DEFRAGMENTATION_FLAG_ALGORITHM_FULL_BIT".enum(
            """
            Perform full defragmentation of memory.

           Can result in notably more time to compute and allocations to copy, but will achieve best memory packing.
            """,
            "0x4"
        ),
        "DEFRAGMENTATION_FLAG_ALGORITHM_EXTENSIVE_BIT".enum(
            """
            Use the most roboust algorithm at the cost of time to compute and number of copies to make.

            Only available when {@code bufferImageGranularity} is greater than 1, since it aims to reduce alignment issues between different types of
            resources. Otherwise falls back to same behavior as #DEFRAGMENTATION_FLAG_ALGORITHM_FULL_BIT.
            """,
            "0x8"
        ),
        "DEFRAGMENTATION_FLAG_ALGORITHM_MASK".enum(
            "A bit mask to extract only {@code ALGORITHM} bits from entire set of flags.",
            "VMA_DEFRAGMENTATION_FLAG_ALGORITHM_FAST_BIT | VMA_DEFRAGMENTATION_FLAG_ALGORITHM_BALANCED_BIT | VMA_DEFRAGMENTATION_FLAG_ALGORITHM_FULL_BIT | VMA_DEFRAGMENTATION_FLAG_ALGORITHM_EXTENSIVE_BIT"
        )
    )

    EnumConstant(
        "VmaDefragmentationMoveOperation",

        "DEFRAGMENTATION_MOVE_OPERATION_COPY".enum(
            """
            Buffer/image has been recreated at {@code dstTmpAllocation}, data has been copied, old buffer/image has been destroyed. {@code srcAllocation}
            should be changed to point to the new place. This is the default value set by #BeginDefragmentationPass().
            """,
            "0"
        ),
        "DEFRAGMENTATION_MOVE_OPERATION_IGNORE".enum(
            """
            Set this value if you cannot move the allocation.

            New place reserved at {@code dstTmpAllocation} will be freed. {@code srcAllocation} will remain unchanged.
            """,
            "1"
        ),
        "DEFRAGMENTATION_MOVE_OPERATION_DESTROY".enum(
            """
            Set this value if you decide to abandon the allocation and you destroyed the buffer/image.

            New place reserved at {@code dstTmpAllocation} will be freed, along with {@code srcAllocation}, which will be destroyed.
            """,
            "2"
        ),
    )

    EnumConstant(
        "Flags to be passed as ##VmaVirtualBlockCreateInfo{@code ::flags}. ({@code VmaVirtualBlockCreateFlagBits})",

        "VIRTUAL_BLOCK_CREATE_LINEAR_ALGORITHM_BIT".enum(
            """
            Enables alternative, linear allocation algorithm in this virtual block.

            Specify this flag to enable linear allocation algorithm, which always creates new allocations after last one and doesn't reuse space from
            allocations freed in between. It trades memory consumption for simplified algorithm and data structure, which has better performance and uses less
            memory for metadata.

            By using this flag, you can achieve behavior of free-at-once, stack, ring buffer, and double stack.
            """,
            0x00000001
        ),
        "VIRTUAL_BLOCK_CREATE_ALGORITHM_MASK".enum(
            "Bit mask to extract only {@code ALGORITHM} bits from entire set of flags.",
            "VMA_VIRTUAL_BLOCK_CREATE_LINEAR_ALGORITHM_BIT"
        )
    )

    EnumConstant(
        "Flags to be passed as ##VmaVirtualAllocationCreateInfo{@code ::flags}. ({@code VmaVirtualAllocationCreateFlagBits})",

        "VIRTUAL_ALLOCATION_CREATE_UPPER_ADDRESS_BIT".enum(
            """
            Allocation will be created from upper stack in a double stack pool.

            This flag is only allowed for virtual blocks created with #VIRTUAL_BLOCK_CREATE_LINEAR_ALGORITHM_BIT flag.
            """,
            "VMA_ALLOCATION_CREATE_UPPER_ADDRESS_BIT"
        ),
        "VIRTUAL_ALLOCATION_CREATE_STRATEGY_MIN_MEMORY_BIT".enum(
            "Allocation strategy that tries to minimize memory usage.",
            "VMA_ALLOCATION_CREATE_STRATEGY_MIN_MEMORY_BIT"
        ),
        "VIRTUAL_ALLOCATION_CREATE_STRATEGY_MIN_TIME_BIT".enum(
            "Allocation strategy that tries to minimize allocation time.",
            "VMA_ALLOCATION_CREATE_STRATEGY_MIN_TIME_BIT"
        ),
        "VIRTUAL_ALLOCATION_CREATE_STRATEGY_MIN_OFFSET_BIT".enum(
            """
            Allocation strategy that chooses always the lowest offset in available space.

            This is not the most efficient strategy but achieves highly packed data.
            """,
            "VMA_ALLOCATION_CREATE_STRATEGY_MIN_OFFSET_BIT"
        ),
        "VIRTUAL_ALLOCATION_CREATE_STRATEGY_MASK".enum(
            """
            A bit mask to extract only {@code STRATEGY} bits from entire set of flags.

            These stategy flags are binary compatible with equivalent flags in {@code VmaAllocationCreateFlagBits}.
            """,
            "VMA_ALLOCATION_CREATE_STRATEGY_MASK"
        )
    )

    VkResult(
        "CreateAllocator",
        """
        Creates Allocator object.

        LWJGL: Use ##VmaVulkanFunctions{@code ::set(VkInstance, VkDevice)} to populate the ##VmaAllocatorCreateInfo{@code ::pVulkanFunctions} struct.
        """,

        VmaAllocatorCreateInfo.const.p("pCreateInfo", ""),
        Check(1)..VmaAllocator.p("pAllocator", "")
    )

    void(
        "DestroyAllocator",
        "Destroys allocator object.",

        VmaAllocator("allocator", "")
    )

    void(
        "GetAllocatorInfo",
        """
        Returns information about existing {@code VmaAllocator} object - handle to Vulkan device etc.

        It might be useful if you want to keep just the {@code VmaAllocator} handle and fetch other required handles to {@code VkPhysicalDevice},
        {@code VkDevice} etc. every time using this function.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocatorInfo.p("pAllocatorInfo", "")
    )

    void(
        "GetPhysicalDeviceProperties",
        """
        {@code PhysicalDeviceProperties} are fetched from {@code physicalDevice} by the allocator. You can access it here, without fetching it again on your
        own.
        """,

        VmaAllocator("allocator", ""),
        Check(1)..VkPhysicalDeviceProperties.const.p.p("ppPhysicalDeviceProperties", "")
    )

    void(
        "GetMemoryProperties",
        """
        {@code PhysicalDeviceMemoryProperties} are fetched from {@code physicalDevice} by the allocator. You can access it here, without fetching it again on
        your own.
        """,

        VmaAllocator("allocator", ""),
        Check(1)..VkPhysicalDeviceMemoryProperties.const.p.p("ppPhysicalDeviceMemoryProperties", "")
    )

    void(
        "GetMemoryTypeProperties",
        """
        Given Memory Type Index, returns Property Flags of this memory type.

        This is just a convenience function. Same information can be obtained using #GetMemoryProperties().
        """,

        VmaAllocator("allocator", ""),
        uint32_t("memoryTypeIndex", ""),
        Check(1)..VkMemoryPropertyFlags.p("pFlags", "")
    )

    void(
        "SetCurrentFrameIndex",
        "Sets index of the current frame.",

        VmaAllocator("allocator", ""),
        uint32_t("frameIndex", "")
    )

    void(
        "CalculateStatistics",
        """
        Retrieves statistics from current state of the Allocator.

        This function is called "calculate" not "get" because it has to traverse all internal data structures, so it may be quite slow. Use it for debugging
        purposes. For faster but more brief statistics suitable to be called every frame or every allocation, use #GetHeapBudgets().

        Note that when using allocator from multiple threads, returned information may immediately become outdated.
        """,

        VmaAllocator("allocator", ""),
        VmaTotalStatistics.p("pStats", "")
    )

    void(
        "GetHeapBudgets",
        """
        Retrieves information about current memory usage and budget for all memory heaps.

        This function is called "get" not "calculate" because it is very fast, suitable to be called every frame or every allocation. For more detailed
        statistics use #CalculateStatistics().

        Note that when using allocator from multiple threads, returned information may immediately become outdated.
        """,

        VmaAllocator("allocator", ""),
        Unsafe..VmaBudget.p("pBudget", "must point to array with number of elements at least equal to number of memory heaps in physical device used")
    )

    VkResult(
        "FindMemoryTypeIndex",
        """
        Helps to find {@code memoryTypeIndex}, given {@code memoryTypeBits} and ##VmaAllocationCreateInfo.

        This algorithm tries to find a memory type that:
        ${ul(
            "Is allowed by {@code memoryTypeBits}.",
            "Contains all the flags from {@code pAllocationCreateInfo->requiredFlags}.",
            "Matches intended usage.",
            "Has as many flags from {@code pAllocationCreateInfo->preferredFlags} as possible."
        )}
        """,

        VmaAllocator("allocator", ""),
        uint32_t("memoryTypeBits", ""),
        VmaAllocationCreateInfo.const.p("pAllocationCreateInfo", ""),
        Check(1)..uint32_t.p("pMemoryTypeIndex", ""),

        returnDoc =
        """
        {@code VK_ERROR_FEATURE_NOT_PRESENT} if not found.

        Receiving such result from this function or any other allocating function probably means that your device doesn't support any memory type with
        requested features for the specific type of resource you want to use it for. Please check parameters of your resource, like image layout
        ({@code OPTIMAL} versus LINEAR) or mip level count.
        """
    )

    VkResult(
        "FindMemoryTypeIndexForBufferInfo",
        """
        Helps to find {@code memoryTypeIndex}, given {@code VkBufferCreateInfo} and ##VmaAllocationCreateInfo.

        It can be useful e.g. to determine value to be used as ##VmaPoolCreateInfo{@code ::memoryTypeIndex}. It internally creates a temporary, dummy buffer
        that never has memory bound.
        """,

        VmaAllocator("allocator", ""),
        VkBufferCreateInfo.const.p("pBufferCreateInfo", ""),
        VmaAllocationCreateInfo.const.p("pAllocationCreateInfo", ""),
        Check(1)..uint32_t.p("pMemoryTypeIndex", "")
    )

    VkResult(
        "FindMemoryTypeIndexForImageInfo",
        """
        Helps to find {@code memoryTypeIndex}, given {@code VkImageCreateInfo} and ##VmaAllocationCreateInfo.

        It can be useful e.g. to determine value to be used as ##VmaPoolCreateInfo{@code ::memoryTypeIndex}. It internally creates a temporary, dummy image
        that never has memory bound.
        """,

        VmaAllocator("allocator", ""),
        VkImageCreateInfo.const.p("pImageCreateInfo", ""),
        VmaAllocationCreateInfo.const.p("pAllocationCreateInfo", ""),
        Check(1)..uint32_t.p("pMemoryTypeIndex", "")
    )

    VkResult(
        "CreatePool",
        "Allocates Vulkan device memory and creates {@code VmaPool} object.",

        VmaAllocator("allocator", "allocator object"),
        VmaPoolCreateInfo.const.p("pCreateInfo", "parameters of pool to create"),
        Check(1)..VmaPool.p("pPool", "handle to created pool")
    )

    void(
        "DestroyPool",
        "Destroys {@code VmaPool} object and frees Vulkan device memory.",

        VmaAllocator("allocator", ""),
        VmaPool("pool", "")
    )

    void(
        "GetPoolStatistics",
        "Retrieves statistics of existing VmaPool object.",

        VmaAllocator("allocator", "allocator object"),
        VmaPool("pool", "pool object"),
        VmaStatistics.p("pPoolStats", "statistics of specified pool")
    )

    void(
        "CalculatePoolStatistics",
        "Retrieves detailed statistics of existing {@code VmaPool} object.",

        VmaAllocator("allocator", "allocator object"),
        VmaPool("pool", "pool object"),
        VmaDetailedStatistics.p("pPoolStats", "statistics of specified pool")
    )

    VkResult(
        "CheckPoolCorruption",
        """
        Checks magic number in margins around all allocations in given memory pool in search for corruptions.

        Corruption detection is enabled only when {@code VMA_DEBUG_DETECT_CORRUPTION} macro is defined to nonzero, {@code VMA_DEBUG_MARGIN} is defined to
        nonzero and the pool is created in memory type that is {@code HOST_VISIBLE} and {@code HOST_COHERENT}.
        """,
        VmaAllocator("allocator", ""),
        VmaPool("pool", ""),

        returnDoc =
        """
        possible return values:
        ${ul(
            "{@code VK_ERROR_FEATURE_NOT_PRESENT} - corruption detection is not enabled for specified pool.",
            "{@code VK_SUCCESS} - corruption detection has been performed and succeeded.",
            """
            {@code VK_ERROR_UNKNOWN} - corruption detection has been performed and found memory corruptions around one of the allocations.
            {@code VMA_ASSERT} is also fired in that case.
            """,
            "Other value: Error returned by Vulkan, e.g. memory mapping failure."
        )}
        """
    )

    void(
        "GetPoolName",
        """
        Retrieves name of a custom pool.

        After the call {@code ppName} is either null or points to an internally-owned null-terminated string containing name of the pool that was previously
        set. The pointer becomes invalid when the pool is destroyed or its name is changed using #SetPoolName().
        """,

        VmaAllocator("allocator", ""),
        VmaPool("pool", ""),
        Check(1)..charASCII.const.p.p("ppName", "")
    )

    void(
        "SetPoolName",
        """
        Sets name of a custom pool.

        {@code pName} can be either null or pointer to a null-terminated string with new name for the pool. Function makes internal copy of the string, so it
        can be changed or freed immediately after this call.
        """,

        VmaAllocator("allocator", ""),
        VmaPool("pool", ""),
        nullable..charASCII.const.p("pName", "")
    )

    VkResult(
        "AllocateMemory",
        """
        General purpose memory allocation.

        You should free the memory using #FreeMemory() or #FreeMemoryPages().

        It is recommended to use #AllocateMemoryForBuffer(), #AllocateMemoryForImage(), #CreateBuffer(), #CreateImage() instead whenever possible.
        """,

        VmaAllocator("allocator", ""),
        VkMemoryRequirements.const.p("pVkMemoryRequirements", ""),
        VmaAllocationCreateInfo.const.p("pCreateInfo", ""),
        Check(1)..VmaAllocation.p("pAllocation", "handle to allocated memory"),
        nullable..VmaAllocationInfo.p(
            "pAllocationInfo",
            "information about allocated memory. Optional. It can be later fetched using function #GetAllocationInfo()."
        )
    )

    VkResult(
        "AllocateMemoryPages",
        """
        General purpose memory allocation for multiple allocation objects at once.

        You should free the memory using #FreeMemory() or #FreeMemoryPages().

        Word "pages" is just a suggestion to use this function to allocate pieces of memory needed for sparse binding. It is just a general purpose allocation
        function able to make multiple allocations at once. It may be internally optimized to be more efficient than calling #AllocateMemory()
        {@code allocationCount} times.

        All allocations are made using same parameters. All of them are created out of the same memory pool and type. If any allocation fails, all allocations
        already made within this function call are also freed, so that when returned result is not {@code VK_SUCCESS}, {@code pAllocation} array is always
        entirely filled with {@code VK_NULL_HANDLE}.
        """,

        VmaAllocator("allocator", "allocator object"),
        VkMemoryRequirements.const.p("pVkMemoryRequirements", "memory requirements for each allocation"),
        VmaAllocationCreateInfo.const.p("pCreateInfo", "creation parameters for each allocation"),
        AutoSize("pAllocations", "pAllocationInfo")..size_t("allocationCount", "number of allocations to make"),
        Check(1)..VmaAllocation.p("pAllocations", "pointer to array that will be filled with handles to created allocations"),
        nullable..VmaAllocationInfo.p(
            "pAllocationInfo",
            "pointer to array that will be filled with parameters of created allocations. Optional."
        )
    )

    VkResult(
        "AllocateMemoryForBuffer",
        """
        Allocates memory suitable for given {@code VkBuffer}.

        It only creates {@code VmaAllocation}. To bind the memory to the buffer, use #BindBufferMemory().

        This is a special-purpose function. In most cases you should use #CreateBuffer().

        You must free the allocation using #FreeMemory() when no longer needed.
        """,

        VmaAllocator("allocator", ""),
        VkBuffer("buffer", ""),
        VmaAllocationCreateInfo.const.p("pCreateInfo", ""),
        Check(1)..VmaAllocation.p("pAllocation", "handle to allocated memory"),
        nullable..VmaAllocationInfo.p(
            "pAllocationInfo",
            "information about allocated memory. Optional. It can be later fetched using function #GetAllocationInfo()."
        )
    )

    VkResult(
        "AllocateMemoryForImage",
        """
        Allocates memory suitable for given {@code VkImage}.

        It only creates {@code VmaAllocation}. To bind the memory to the buffer, use #BindImageMemory().

        This is a special-purpose function. In most cases you should use #CreateImage().

        You must free the allocation using #FreeMemory() when no longer needed.
        """,

        VmaAllocator("allocator", ""),
        VkImage("image", ""),
        VmaAllocationCreateInfo.const.p("pCreateInfo", ""),
        Check(1)..VmaAllocation.p("pAllocation", "handle to allocated memory"),
        nullable..VmaAllocationInfo.p(
            "pAllocationInfo",
            "information about allocated memory. Optional. It can be later fetched using function #GetAllocationInfo()."
        )
    )

    void(
        "FreeMemory",
        """
        Frees memory previously allocated using #AllocateMemory(), #AllocateMemoryForBuffer(), or #AllocateMemoryForImage().

        Passing {@code VK_NULL_HANDLE} as {@code allocation} is valid. Such function call is just skipped.
        """,

        VmaAllocator("allocator", ""),
        nullable..VmaAllocation.const("allocation", "")
    )

    void(
        "FreeMemoryPages",
        """
        Frees memory and destroys multiple allocations.

        Word "pages" is just a suggestion to use this function to free pieces of memory used for sparse binding. It is just a general purpose function to free
        memory and destroy allocations made using e.g. #AllocateMemory(), #AllocateMemoryPages() and other functions. It may be internally optimized to be more
        efficient than calling #FreeMemory() {@code allocationCount} times.

        Allocations in {@code pAllocations} array can come from any memory pools and types. Passing {@code VK_NULL_HANDLE} as elements of {@code pAllocations}
        array is valid. Such entries are just skipped.
        """,

        VmaAllocator("allocator", ""),
        AutoSize("pAllocations")..size_t("allocationCount", ""),
        VmaAllocation.const.p("pAllocations", "")
    )

    void(
        "GetAllocationInfo",
        """
        Returns current information about specified allocation.

        Current parameters of given allocation are returned in {@code pAllocationInfo}.

        Although this function doesn't lock any mutex, so it should be quite efficient, you should avoid calling it too often. You can retrieve same
        {@code VmaAllocationInfo} structure while creating your resource, from function #CreateBuffer(), #CreateImage(). You can remember it if you are sure
        parameters don't change (e.g. due to defragmentation).

        There is also a new function #GetAllocationInfo2() that offers extended information about the allocation, returned using new structure
        ##VmaAllocationInfo2.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VmaAllocationInfo.p("pAllocationInfo", "")
    )

    void(
        "GetAllocationInfo2",
        """
        Returns extended information about specified allocation.

        Current parameters of given allocation are returned in {@code pAllocationInfo}. Extended parameters in structure {@code VmaAllocationInfo2} include
        memory block size and a flag telling whether the allocation has dedicated memory. It can be useful e.g. for interop with OpenGL.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VmaAllocationInfo2.p("pAllocationInfo", "")
    )

    void(
        "SetAllocationUserData",
        """
        Sets {@code pUserData} in given allocation to new value.

        The value of pointer {@code pUserData} is copied to allocation's {@code pUserData}. It is opaque, so you can use it however you want - e.g. as a
        pointer, ordinal number or some handle to you own data.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        nullable..opaque_p("pUserData", "")
    )

    void(
        "SetAllocationName",
        "Sets {@code pName} in given allocation to new value.",

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        nullable..charUTF8.const.p(
            "pName",
            """
            must be either null, or pointer to a null-terminated string.

            The function makes local copy of the string and sets it as allocation's {@code pName}. String passed as {@code pName} doesn't need to be valid for
            whole lifetime of the allocation - you can free it after this call. String previously pointed by allocation's {@code pName} is freed from memory.
            """
        )
    )

    void(
        "GetAllocationMemoryProperties",
        """
        Given an allocation, returns Property Flags of its memory type.

        This is just a convenience function. Same information can be obtained using #GetAllocationInfo() + #GetMemoryProperties(). 
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        Check(1)..VkMemoryPropertyFlags.p("pFlags", "")
    )

    VkResult(
        "MapMemory",
        """
        Maps memory represented by given allocation and returns pointer to it.

        Maps memory represented by given allocation to make it accessible to CPU code. When succeeded, {@code *ppData} contains pointer to first byte of this
        memory.

        If the allocation is part of a bigger {@code VkDeviceMemory} block, returned pointer is correctly offsetted to the beginning of region assigned to this
        particular allocation. Unlike the result of {@code vkMapMemory}, it points to the allocation, not to the beginning of the whole block. You should not
        add {@code VmaAllocationInfo::offset} to it!

        Mapping is internally reference-counted and synchronized, so despite raw Vulkan function {@code vkMapMemory()} cannot be used to map same block of
        {@code VkDeviceMemory} multiple times simultaneously, it is safe to call this function on allocations assigned to the same memory block. Actual Vulkan
        memory will be mapped on first mapping and unmapped on last unmapping.

        If the function succeeded, you must call #UnmapMemory() to unmap the allocation when mapping is no longer needed or before freeing the allocation, at
        the latest.

        It also safe to call this function multiple times on the same allocation. You must call {@code vmaUnmapMemory()} same number of times as you called
        {@code vmaMapMemory()}.

        It is also safe to call this function on allocation created with #ALLOCATION_CREATE_MAPPED_BIT flag. Its memory stays mapped all the time. You must
        still call {@code vmaUnmapMemory()} same number of times as you called {@code vmaMapMemory()}. You must not call {@code vmaUnmapMemory()} additional
        time to free the "0-th" mapping made automatically due to #ALLOCATION_CREATE_MAPPED_BIT flag.

        This function fails when used on allocation made in memory type that is not {@code HOST_VISIBLE}.

        This function doesn't automatically flush or invalidate caches. If the allocation is made from a memory types that is not {@code HOST_COHERENT}, you
        also need to use #InvalidateAllocation() / #FlushAllocation(), as required by Vulkan specification.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        Check(1)..void.p.p("ppData", "")
    )

    void(
        "UnmapMemory",
        """
        Unmaps memory represented by given allocation, mapped previously using #MapMemory().

        For details, see description of {@code vmaMapMemory()}.

        This function doesn't automatically flush or invalidate caches. If the allocation is made from a memory types that is not {@code HOST_COHERENT}, you
        also need to use #InvalidateAllocation() / #FlushAllocation(), as required by Vulkan specification.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", "")
    )

    void(
        "FlushAllocation",
        """
        Flushes memory of given allocation.

        Calls {@code vkFlushMappedMemoryRanges()} for memory associated with given range of given allocation. It needs to be called after writing to a mapped
        memory for memory types that are not {@code HOST_COHERENT}. Unmap operation doesn't do that automatically.

        ${ul(
            "{@code offset} must be relative to the beginning of allocation.",
            "{@code size} can be {@code VK_WHOLE_SIZE}. It means all memory from {@code offset} the the end of given allocation.",
            "{@code offset} and {@code size} don't have to be aligned. They are internally rounded down/up to multiply of {@code nonCoherentAtomSize}.",
            "If {@code size} is 0, this call is ignored.",
            "If memory type that the {@code allocation} belongs to is not {@code HOST_VISIBLE} or it is {@code HOST_COHERENT}, this call is ignored."
        )}

        Warning! {@code offset} and {@code size} are relative to the contents of given {@code allocation}. If you mean whole allocation, you can pass 0 and
        {@code VK_WHOLE_SIZE}, respectively. Do not pass allocation's offset as {@code offset}!!!
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VkDeviceSize("offset", ""),
        VkDeviceSize("size", "")
    )

    void(
        "InvalidateAllocation",
        """
        Invalidates memory of given allocation.

        Calls {@code vkInvalidateMappedMemoryRanges()} for memory associated with given range of given allocation. It needs to be called before reading from a
        mapped memory for memory types that are not {@code HOST_COHERENT}. Map operation doesn't do that automatically.

        ${ul(
            "{@code offset} must be relative to the beginning of allocation.",
            "{@code size} can be {@code VK_WHOLE_SIZE}. It means all memory from {@code offset} the the end of given allocation.",
            "{@code offset} and {@code size} don't have to be aligned. They are internally rounded down/up to multiply of {@code nonCoherentAtomSize}.",
            "If {@code size} is 0, this call is ignored.",
            "If memory type that the {@code allocation} belongs to is not {@code HOST_VISIBLE} or it is {@code HOST_COHERENT}, this call is ignored."
        )}

        Warning! {@code offset} and {@code size} are relative to the contents of given {@code allocation}. If you mean whole allocation, you can pass 0 and
        {@code VK_WHOLE_SIZE}, respectively. Do not pass allocation's offset as {@code offset}!!!

        This function returns the {@code VkResult} from {@code vkFlushMappedMemoryRanges} if it is called, otherwise {@code VK_SUCCESS}.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VkDeviceSize("offset", ""),
        VkDeviceSize("size", "")
    )

    VkResult(
        "FlushAllocations",
        """
        Flushes memory of given set of allocations.

        Calls {@code vkFlushMappedMemoryRanges()} for memory associated with given ranges of given allocations. For more information, see documentation of #FlushAllocation().

        This function returns the {@code VkResult} from {@code vkFlushMappedMemoryRanges} if it is called, otherwise {@code VK_SUCCESS}.
        """,

        VmaAllocator("allocator", ""),
        AutoSize(
            "allocations",
            "offsets",
            "sizes"
        )..uint32_t("allocationCount", ""),
        VmaAllocation.const.p("allocations", ""),
        nullable..VkDeviceSize.const.p("offsets", "If not null, it must point to an array of offsets of regions to flush, relative to the beginning of respective allocations. Null means all offsets are zero."),
        nullable..VkDeviceSize.const.p("sizes", "If not null, it must point to an array of sizes of regions to flush in respective allocations. Null means `VK_WHOLE_SIZE` for all allocations.")
    )

    VkResult(
        "InvalidateAllocations",
        """
        Invalidates memory of given set of allocations.

        Calls {@code vkInvalidateMappedMemoryRanges()} for memory associated with given ranges of given allocations. For more information, see documentation of #InvalidateAllocation().

        This function returns the {@code VkResult} from {@code vkInvalidateMappedMemoryRanges} if it is called, otherwise {@code VK_SUCCESS}.
        """,

        VmaAllocator("allocator", ""),
        AutoSize(
            "allocations",
            "offsets",
            "sizes"
        )..uint32_t("allocationCount", ""),
        VmaAllocation.const.p("allocations", ""),
        nullable..VkDeviceSize.const.p(
            "offsets",
            """
            if not null, it must point to an array of offsets of regions to flush, relative to the beginning of respective allocations. Null means all offsets
            are zero.
            """
        ),
        nullable..VkDeviceSize.const.p(
            "sizes",
            """
            if not null, it must point to an array of sizes of regions to flush in respective allocations. Null means {@code VK_WHOLE_SIZE} for all
            allocations.
            """
        )
    )

    VkResult(
        "CopyMemoryToAllocation",
        """
        Maps the allocation temporarily if needed, copies data from specified host pointer to it, and flushes the memory from the host caches if needed.

        This is a convenience function that allows to copy data from a host pointer to an allocation easily. Same behavior can be achieved by calling
        #MapMemory(), {@code memcpy()}, #UnmapMemory(), #FlushAllocation().

        This function can be called only for allocations created in a memory type that has {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT} flag. It can be ensured
        e.g. by using #MEMORY_USAGE_AUTO and #ALLOCATION_CREATE_HOST_ACCESS_SEQUENTIAL_WRITE_BIT or #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT. Otherwise, the
        function will fail and generate a Validation Layers error.

        {@code dstAllocationLocalOffset} is relative to the contents of given {@code dstAllocation}. If you mean whole allocation, you should pass 0. Do not
        pass allocation's offset within device memory block this parameter!
        """,

        VmaAllocator("allocator", ""),
        void.const.p("pSrcHostPointer", "pointer to the host data that become source of the copy"),
        VmaAllocation("dstAllocation", "handle to the allocation that becomes destination of the copy"),
        VkDeviceSize("dstAllocationLocalOffset", "offset within {@code dstAllocation} where to write copied data, in bytes"),
        AutoSize("pSrcHostPointer")..VkDeviceSize("size", "number of bytes to copy")
    )

    VkResult(
        "CopyAllocationToMemory",
        """
        Invalidates memory in the host caches if needed, maps the allocation temporarily if needed, and copies data from it to a specified host pointer.

        This is a convenience function that allows to copy data from an allocation to a host pointer easily. Same behavior can be achieved by calling
        #InvalidateAllocation(), #MapMemory(), {@code memcpy()}, #UnmapMemory().

        This function should be called only for allocations created in a memory type that has {@code VK_MEMORY_PROPERTY_HOST_VISIBLE_BIT} and
        {@code VK_MEMORY_PROPERTY_HOST_CACHED_BIT} flag. It can be ensured e.g. by using #MEMORY_USAGE_AUTO and #ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT.
        Otherwise, the function may fail and generate a Validation Layers error. It may also work very slowly when reading from an uncached memory.

        {@code srcAllocationLocalOffset} is relative to the contents of given {@code srcAllocation}. If you mean whole allocation, you should pass 0. Do not
        pass allocation's offset within device memory block as this parameter!
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("srcAllocation", "handle to the allocation that becomes source of the copy"),
        VkDeviceSize("srcAllocationLocalOffset", "offset within {@code srcAllocation} where to read copied data, in bytes"),
        void.p("pDstHostPointer", "pointer to the host memory that become destination of the copy"),
        AutoSize("pDstHostPointer")..VkDeviceSize("size", "number of bytes to copy")
    )

    VkResult(
        "CheckCorruption",
        """
        Checks magic number in margins around all allocations in given memory types (in both default and custom pools) in search for corruptions.

        Corruption detection is enabled only when {@code VMA_DEBUG_DETECT_CORRUPTION} macro is defined to nonzero, {@code VMA_DEBUG_MARGIN} is defined to
        nonzero and only for memory types that are {@code HOST_VISIBLE} and {@code HOST_COHERENT}.
        """,

        VmaAllocator("allocator", ""),
        uint32_t("memoryTypeBits", "bit mask, where each bit set means that a memory type with that index should be checked"),

        returnDoc =
        """
        possible return values:
        ${ul(
            "{@code VK_ERROR_FEATURE_NOT_PRESENT} - corruption detection is not enabled for any of specified memory types.",
            "{@code VK_SUCCESS} - corruption detection has been performed and succeeded.",
            """
            {@code VK_ERROR_UNKNOWN} - corruption detection has been performed and found memory corruptions around one of the allocations.
            {@code VMA_ASSERT} is also fired in that case.
            """,
            "Other value: Error returned by Vulkan, e.g. memory mapping failure."
        )}
        """
    )

    VkResult(
        "BeginDefragmentation",
        "Begins defragmentation process.",

        VmaAllocator("allocator", "allocator object"),
        VmaDefragmentationInfo.const.p("pInfo", "structure filled with parameters of defragmentation"),
        Check(1)..VmaDefragmentationContext.p("pContext", "context object that must be passed to #EndDefragmentation() to finish defragmentation"),

        returnDoc = "{@code VK_SUCCESS} if defragmentation can begin. {@code VK_ERROR_FEATURE_NOT_PRESENT} if defragmentation is not supported."
    )

    void(
        "EndDefragmentation",
        """
        Ends defragmentation process.

        Use this function to finish defragmentation started by #BeginDefragmentation().
        """,

        VmaAllocator("allocator", "allocator object"),
        VmaDefragmentationContext("context", "context object that has been created by #BeginDefragmentation()"),
        nullable..VmaDefragmentationStats.p("pStats", "optional stats for the defragmentation. Can be null.")
    )

    VkResult(
        "BeginDefragmentationPass",
        "Starts single defragmentation pass.",

        VmaAllocator("allocator", "allocator object"),
        VmaDefragmentationContext("context", "context object that has been created by #BeginDefragmentation()"),
        VmaDefragmentationPassMoveInfo.p("pInfo", "computed information for current pass"),

        returnDoc =
        """
        {@code VK_SUCCESS} if no more moves are possible. Then you can omit call to #EndDefragmentationPass() and simply end whole defragmentation.
        {@code VK_INCOMPLETE} if there are pending moves returned in {@code pPassInfo}. You need to perform them, call {@code vmaEndDefragmentationPass()}, and
        then preferably try another pass with {@code vmaBeginDefragmentationPass()}.
        """
    )

    VkResult(
        "EndDefragmentationPass",
        """
        Ends single defragmentation pass.

        Ends incremental defragmentation pass and commits all defragmentation moves from {@code pPassInfo}.
        After this call:
        ${ul(
            """
            Allocations at {@code pPassInfo[i].srcAllocation} that had {@code pPassInfo[i].operation ==} #DEFRAGMENTATION_MOVE_OPERATION_COPY (which is the
            default) will be pointing to the new destination place.
            """,
            """
            Allocation at {@code pPassInfo[i].srcAllocation} that had {@code pPassInfo[i].operation ==} #DEFRAGMENTATION_MOVE_OPERATION_DESTROY will be freed.
            """
        )}

        If no more moves are possible you can end whole defragmentation.
        """,

        VmaAllocator("allocator", "allocator object"),
        VmaDefragmentationContext("context", "context object that has been created by #BeginDefragmentation()"),
        VmaDefragmentationPassMoveInfo.p(
            "pPassInfo",
            "computed information for current pass filled by #BeginDefragmentationPass() and possibly modified by you"
        )
    )

    VkResult(
        "BindBufferMemory",
        """
        Binds buffer to allocation.

        Binds specified buffer to region of memory represented by specified allocation. Gets {@code VkDeviceMemory} handle and offset from the allocation. If
        you want to create a buffer, allocate memory for it and bind them together separately, you should use this function for binding instead of standard
        {@code vkBindBufferMemory()}, because it ensures proper synchronization so that when a {@code VkDeviceMemory} object is used by multiple allocations,
        calls to {@code vkBind*Memory()} or {@code vkMapMemory()} won't happen from multiple threads simultaneously (which is illegal in Vulkan).

        It is recommended to use function #CreateBuffer() instead of this one.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VkBuffer("buffer", "")
    )

    VkResult(
        "BindBufferMemory2",
        """
        Binds buffer to allocation with additional parameters.

        This function is similar to #BindBufferMemory(), but it provides additional parameters.

        If {@code pNext} is not null, {@code VmaAllocator} object must have been created with #ALLOCATOR_CREATE_KHR_BIND_MEMORY2_BIT flag or with
        ##VmaAllocatorCreateInfo{@code ::vulkanApiVersion >= VK_API_VERSION_1_1}. Otherwise the call fails.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VkDeviceSize(
            "allocationLocalOffset",
            "additional offset to be added while binding, relative to the beginning of the {@code allocation}. Normally it should be 0."
        ),
        VkBuffer("buffer", ""),
        nullable..opaque_const_p(
            "pNext",
            "a chain of structures to be attached to {@code VkBindBufferMemoryInfoKHR} structure used internally. Normally it should be {@code null}."
        )
    )

    VkResult(
        "BindImageMemory",
        """
        Binds image to allocation.

        Binds specified image to region of memory represented by specified allocation. Gets {@code VkDeviceMemory} handle and offset from the allocation. If
        you want to create an image, allocate memory for it and bind them together separately, you should use this function for binding instead of standard
        {@code vkBindImageMemory()}, because it ensures proper synchronization so that when a {@code VkDeviceMemory} object is used by multiple allocations,
        calls to {@code vkBind*Memory()} or {@code vkMapMemory()} won't happen from multiple threads simultaneously (which is illegal in Vulkan).

        It is recommended to use function vmaCreateImage() instead of this one.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VkImage("image", "")
    )

    VkResult(
        "BindImageMemory2",
        """
        Binds image to allocation with additional parameters.

        This function is similar to #BindImageMemory(), but it provides additional parameters.

        If {@code pNext} is not null, {@code VmaAllocator} object must have been created with #ALLOCATOR_CREATE_KHR_BIND_MEMORY2_BIT flag or with
        ##VmaAllocatorCreateInfo{@code ::vulkanApiVersion >= VK_API_VERSION_1_1}. Otherwise the call fails.
        """,

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VkDeviceSize(
            "allocationLocalOffset",
            "additional offset to be added while binding, relative to the beginning of the {@code allocation}. Normally it should be 0."
        ),
        VkImage("image", ""),
        nullable..opaque_const_p(
            "pNext",
            "a chain of structures to be attached to {@code VkBindImageMemoryInfoKHR} structure used internally. Normally it should be null."
        )
    )

    val CreateBuffer = VkResult(
        "CreateBuffer",
        """
        Creates a new {@code VkBuffer}, allocates and binds memory for it.

        This function automatically:
        ${ul(
            "Creates buffer.",
            "Allocates appropriate memory for it.",
            "Binds the buffer with the memory."
        )}
        If any of these operations fail, buffer and allocation are not created, returned value is negative error code, {@code *pBuffer} and
        {@code *pAllocation} are null.

        If the function succeeded, you must destroy both buffer and allocation when you no longer need them using either convenience function #DestroyBuffer()
        or separately, using {@code vkDestroyBuffer()} and #FreeMemory().

        If #ALLOCATOR_CREATE_KHR_DEDICATED_ALLOCATION_BIT flag was used, {@code VK_KHR_dedicated_allocation} extension is used internally to query driver
        whether it requires or prefers the new buffer to have dedicated allocation. If yes, and if dedicated allocation is possible
        (#ALLOCATION_CREATE_NEVER_ALLOCATE_BIT is not used), it creates dedicated allocation for this buffer, just like when using
        #ALLOCATION_CREATE_DEDICATED_MEMORY_BIT.

        ${note("""
        This function creates a new {@code VkBuffer}. Sub-allocation of parts of one large buffer, although recommended as a good practice, is out of scope of
        this library and could be implemented by the user as a higher-level logic on top of VMA.
        """)}
        """,

        VmaAllocator("allocator", ""),
        VkBufferCreateInfo.const.p("pBufferCreateInfo", ""),
        VmaAllocationCreateInfo.const.p("pAllocationCreateInfo", ""),
        Check(1)..VkBuffer.p("pBuffer", "buffer that was created"),
        Check(1)..VmaAllocation.p("pAllocation", "allocation that was created"),
        nullable..VmaAllocationInfo.p(
            "pAllocationInfo",
            "information about allocated memory. Optional. It can be later fetched using function #GetAllocationInfo()."
        )
    )

    VkResult(
        "CreateBufferWithAlignment",
        """
        Similar to #CreateBuffer() but provides additional parameter {@code minAlignment} which allows to specify custom, minimum alignment to be used when
        placing the buffer inside a larger memory block, which may be needed e.g. for interop with OpenGL.
        """,

        CreateBuffer["allocator"],
        CreateBuffer["pBufferCreateInfo"],
        CreateBuffer["pAllocationCreateInfo"],
        VkDeviceSize("minAlignment", "custom, minimum alignment to be used when placing the buffer inside a larger memory block"),
        CreateBuffer["pBuffer"],
        CreateBuffer["pAllocation"],
        CreateBuffer["pAllocationInfo"]
    )

    VkResult(
        "CreateAliasingBuffer",
        """
        Creates a new {@code VkBuffer}, binds already created memory for it.

        This function automatically:
        ${ul(
            "Creates buffer.",
            "Binds the buffer with the supplied memory."
        )}
    
        If any of these operations fail, buffer is not created, returned value is negative error code and {@code *pBuffer} is null.

        If the function succeeded, you must destroy the buffer when you no longer need it using {@code vkDestroyBuffer()}. If you want to also destroy the
        corresponding allocation you can use convenience function #DestroyBuffer().

        Note: There is a new version of this function augmented with parameter {@code allocationLocalOffset} - see #CreateAliasingBuffer2().
        """,

        VmaAllocator("allocator", "allocator"),
        VmaAllocation("allocation", "allocation that provides memory to be used for binding new buffer to it"),
        VkBufferCreateInfo.const.p("pBufferCreateInfo", ""),
        Check(1)..VkBuffer.p("pBuffer", "buffer that was created")
    )

    VkResult(
        "CreateAliasingBuffer2",
        """
        Creates a new {@code VkBuffer}, binds already created memory for it.

        This function automatically:
        ${ul(
            "Creates buffer.",
            "Binds the buffer with the supplied memory."
        )}
    
        If any of these operations fail, buffer is not created, returned value is negative error code and {@code *pBuffer} is null.

        If the function succeeded, you must destroy the buffer when you no longer need it using {@code vkDestroyBuffer()}. If you want to also destroy the
        corresponding allocation you can use convenience function #DestroyBuffer().

        Note: This is a new version of the function augmented with parameter {@code allocationLocalOffset}.
        """,

        VmaAllocator("allocator", "allocator"),
        VmaAllocation("allocation", "allocation that provides memory to be used for binding new buffer to it"),
        VkDeviceSize(
            "allocationLocalOffset",
            "additional offset to be added while binding, relative to the beginning of the allocation. Normally it should be 0."
        ),
        VkBufferCreateInfo.const.p("pBufferCreateInfo", ""),
        Check(1)..VkBuffer.p("pBuffer", "buffer that was created")
    )

    void(
        "DestroyBuffer",
        """
        Destroys Vulkan buffer and frees allocated memory.

        This is just a convenience function equivalent to:
        ${codeBlock("""
vkDestroyBuffer(device, buffer, allocationCallbacks);
vmaFreeMemory(allocator, allocation);""")}

        It is safe to pass null as buffer and/or allocation.
        """,

        VmaAllocator("allocator", ""),
        VkBuffer("buffer", ""),
        nullable..VmaAllocation("allocation", "")
    )

    VkResult(
        "CreateImage",
        "Function similar to #CreateBuffer().",

        VmaAllocator("allocator", ""),
        VkImageCreateInfo.const.p("pImageCreateInfo", ""),
        VmaAllocationCreateInfo.const.p("pAllocationCreateInfo", ""),
        Check(1)..VkImage.p("pImage", "image that was created"),
        Check(1)..VmaAllocation.p("pAllocation", "allocation that was created"),
        nullable..VmaAllocationInfo.p(
            "pAllocationInfo",
            "information about allocated memory. Optional. It can be later fetched using function #GetAllocationInfo()."
        )
    )

    VkResult(
        "CreateAliasingImage",
        "Function similar to #CreateAliasingBuffer() but for images.",

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VkImageCreateInfo.const.p("pImageCreateInfo", ""),
        Check(1)..VkImage.p("pImage", "")
    )

    ///
    VkResult(
        "CreateAliasingImage2",
        "Function similar to #CreateAliasingBuffer2() but for images.",

        VmaAllocator("allocator", ""),
        VmaAllocation("allocation", ""),
        VkDeviceSize("allocationLocalOffset", ""),
        VkImageCreateInfo.const.p("pImageCreateInfo", ""),
        Check(1)..VkImage.p("pImage", "")
    )

    void(
        "DestroyImage",
        """
        Destroys Vulkan image and frees allocated memory.

        This is just a convenience function equivalent to:
        ${codeBlock("""
vkDestroyImage(device, image, allocationCallbacks);
vmaFreeMemory(allocator, allocation);""")}

        It is safe to pass null as image and/or allocation.
        """,

        VmaAllocator("allocator", ""),
        VkImage("image", ""),
        nullable..VmaAllocation("allocation", "")
    )

    VkResult(
        "CreateVirtualBlock",
        "Creates new {@code VmaVirtualBlock} object.",

        VmaVirtualBlockCreateInfo.const.p("pCreateInfo", "parameters for creation"),
        Check(1)..VmaVirtualBlock.p("pVirtualBlock", "returned virtual block object or #NULL if creation failed")
    )

    void(
        "DestroyVirtualBlock",
        """
        Destroys {@code VmaVirtualBlock} object.

        Please note that you should consciously handle virtual allocations that could remain unfreed in the block. You should either free them individually
        using #VirtualFree() or call #ClearVirtualBlock() if you are sure this is what you want. If you do neither, an assert is called.

        If you keep pointers to some additional metadata associated with your virtual allocations in their {@code pUserData}, don't forget to free them.
        """,

        VmaVirtualBlock("virtualBlock", "")
    )

    VkBool32(
        "IsVirtualBlockEmpty",
        "Returns true of the {@code VmaVirtualBlock} is empty - contains 0 virtual allocations and has all its space available for new allocations.",

        VmaVirtualBlock("virtualBlock", "")
    )

    void(
        "GetVirtualAllocationInfo",
        "Returns information about a specific virtual allocation within a virtual block, like its size and {@code pUserData} pointer.",

        VmaVirtualBlock("virtualBlock", ""),
        VmaVirtualAllocation("allocation", ""),
        VmaVirtualAllocationInfo.p("pVirtualAllocInfo", "")
    )

    VkResult(
        "VirtualAllocate",
        """
        Allocates new virtual allocation inside given {@code VmaVirtualBlock}.

        If the allocation fails due to not enough free space available, {@code VK_ERROR_OUT_OF_DEVICE_MEMORY} is returned (despite the function doesn't ever
        allocate actual GPU memory). {@code pAllocation} is then set to {@code VK_NULL_HANDLE} and {@code pOffset}, if not null, it set to {@code UINT64_MAX}.
        """,

        VmaVirtualBlock("virtualBlock", "virtual block"),
        VmaVirtualAllocationCreateInfo.const.p("pCreateInfo", "parameters for the allocation"),
        Check(1)..VmaVirtualAllocation.p("pAllocation", ""),
        Check(1)..nullable..VkDeviceSize.p("pOffset", "returned offset of the new allocation. Optional, can be null.")
    )

    void(
        "VirtualFree",
        """
        Frees virtual allocation inside given {@code VmaVirtualBlock}.

        It is correct to call this function with {@code allocation == VK_NULL_HANDLE} - it does nothing.
        """,

        VmaVirtualBlock("virtualBlock", ""),
        nullable..VmaVirtualAllocation("allocation", "")
    )

    void(
        "ClearVirtualBlock",
        """
        Frees all virtual allocations inside given {@code VmaVirtualBlock}.

        You must either call this function or free each virtual allocation individually with #VirtualFree() before destroying a virtual block. Otherwise, an
        assert is called.

        If you keep pointer to some additional metadata associated with your virtual allocation in its {@code pUserData}, don't forget to free it as well.
        """,

        VmaVirtualBlock("virtualBlock", "")
    )

    void(
        "SetVirtualAllocationUserData",
        "Changes custom pointer associated with given virtual allocation.",

        VmaVirtualBlock("virtualBlock", ""),
        VmaVirtualAllocation("allocation", ""),
        opaque_p("pUserData", "")
    )

    void(
        "GetVirtualBlockStatistics",
        """
        Calculates and returns statistics about virtual allocations and memory usage in given {@code VmaVirtualBlock}.
        
        This function is fast to call. For more detailed statistics, see #CalculateVirtualBlockStatistics().
        """,

        VmaVirtualBlock("virtualBlock", ""),
        VmaStatistics.p("pStats", "")
    )

    void(
        "CalculateVirtualBlockStatistics",
        """
        Calculates and returns detailed statistics about virtual allocations and memory usage in given {@code VmaVirtualBlock}.

        This function is slow to call. Use for debugging purposes. For less detailed statistics, see #GetVirtualBlockStatistics().
        """,

        VmaVirtualBlock("virtualBlock", ""),
        VmaDetailedStatistics.p("pStats", "")
    )

    void(
        "BuildVirtualBlockStatsString",
        """
        Builds and returns a null-terminated string in JSON format with information about given {@code VmaVirtualBlock}.

        Returned string must be freed using #FreeVirtualBlockStatsString().
        """,

        VmaVirtualBlock("virtualBlock", "virtual block"),
        Check(1)..charUTF8.p.p("ppStatsString", "returned string"),
        VkBool32(
            "detailedMap",
            """
            pass {@code VK_FALSE} to only obtain statistics as returned by #CalculateVirtualBlockStatistics(). Pass {@code VK_TRUE} to also obtain full list of
            allocations and free spaces.
            """
        )
    )

    void(
        "FreeVirtualBlockStatsString",
        "Frees a string returned by #BuildVirtualBlockStatsString().",

        VmaVirtualBlock("virtualBlock", ""),
        Unsafe..char.p("pStatsString", "")
    )

    void(
        "BuildStatsString",
        "Builds and returns statistics as a null-terminated string in JSON format.",

        VmaAllocator("allocator", ""),
        Check(1)..charASCII.p.p("ppStatsString", "must be freed using #FreeStatsString() function"),
        VkBool32("detailedMap", "")
    )

    void(
        "FreeStatsString",
        "",

        VmaAllocator("allocator", ""),
        Unsafe..char.p("pStatsString", "")
    )
}