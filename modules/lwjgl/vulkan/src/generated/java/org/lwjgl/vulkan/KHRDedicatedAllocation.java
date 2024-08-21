/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension enables resources to be bound to a dedicated allocation, rather than suballocated. For any particular resource, applications <b>can</b> query whether a dedicated allocation is recommended, in which case using a dedicated allocation <b>may</b> improve the performance of access to that resource. Normal device memory allocations must support multiple resources per allocation, memory aliasing and sparse binding, which could interfere with some optimizations. Applications should query the implementation for when a dedicated allocation <b>may</b> be beneficial by adding a {@link VkMemoryDedicatedRequirementsKHR} structure to the {@code pNext} chain of the {@link VkMemoryRequirements2} structure passed as the {@code pMemoryRequirements} parameter of a call to {@code vkGetBufferMemoryRequirements2} or {@code vkGetImageMemoryRequirements2}. Certain external handle types and external images or buffers <b>may</b> also depend on dedicated allocations on implementations that associate image or buffer metadata with OS-level memory objects.
 * 
 * <p>This extension adds a two small structures to memory requirements querying and memory allocation: a new structure that flags whether an image/buffer should have a dedicated allocation, and a structure indicating the image or buffer that an allocation will be bound to.</p>
 * 
 * <h5>Promotion to Vulkan 1.1</h5>
 * 
 * <p>All functionality in this extension is included in core Vulkan 1.1, with the KHR suffix omitted. The original type, enum and command names are still available as aliases of the core functionality.</p>
 * 
 * <h5>Examples</h5>
 * 
 * <pre><code>
 *     // Create an image with a dedicated allocation based on the
 *     // implementation's preference
 * 
 *     VkImageCreateInfo imageCreateInfo =
 *     {
 *         // Image creation parameters
 *     };
 * 
 *     VkImage image;
 *     VkResult result = vkCreateImage(
 *         device,
 *         &amp;imageCreateInfo,
 *         NULL,               // pAllocator
 *         &amp;image);
 * 
 *     VkMemoryDedicatedRequirementsKHR dedicatedRequirements =
 *     {
 *         .sType = VK_STRUCTURE_TYPE_MEMORY_DEDICATED_REQUIREMENTS_KHR,
 *         .pNext = NULL,
 *     };
 * 
 *     VkMemoryRequirements2 memoryRequirements =
 *     {
 *         .sType = VK_STRUCTURE_TYPE_MEMORY_REQUIREMENTS_2,
 *         .pNext = &amp;dedicatedRequirements,
 *     };
 * 
 *     const VkImageMemoryRequirementsInfo2 imageRequirementsInfo =
 *     {
 *         .sType = VK_STRUCTURE_TYPE_IMAGE_MEMORY_REQUIREMENTS_INFO_2,
 *         .pNext = NULL,
 *         .image = image
 *     };
 * 
 *     vkGetImageMemoryRequirements2(
 *         device,
 *         &amp;imageRequirementsInfo,
 *         &amp;memoryRequirements);
 * 
 *     if (dedicatedRequirements.prefersDedicatedAllocation) {
 *         // Allocate memory with VkMemoryDedicatedAllocateInfoKHR::image
 *         // pointing to the image we are allocating the memory for
 * 
 *         VkMemoryDedicatedAllocateInfoKHR dedicatedInfo =
 *         {
 *             .sType = VK_STRUCTURE_TYPE_MEMORY_DEDICATED_ALLOCATE_INFO_KHR,
 *             .pNext = NULL,
 *             .image = image,
 *             .buffer = VK_NULL_HANDLE,
 *         };
 * 
 *         VkMemoryAllocateInfo memoryAllocateInfo =
 *         {
 *             .sType = VK_STRUCTURE_TYPE_MEMORY_ALLOCATE_INFO,
 *             .pNext = &amp;dedicatedInfo,
 *             .allocationSize = memoryRequirements.size,
 *             .memoryTypeIndex = FindMemoryTypeIndex(memoryRequirements.memoryTypeBits),
 *         };
 * 
 *         VkDeviceMemory memory;
 *         vkAllocateMemory(
 *             device,
 *             &amp;memoryAllocateInfo,
 *             NULL,               // pAllocator
 *             &amp;memory);
 * 
 *         // Bind the image to the memory
 * 
 *         vkBindImageMemory(
 *             device,
 *             image,
 *             memory,
 *             0);
 *     } else {
 *         // Take the normal memory sub-allocation path
 *     }</code></pre>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_KHR_dedicated_allocation}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>128</dd>
 * <dt><b>Revision</b></dt>
 * <dd>3</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRGetMemoryRequirements2 VK_KHR_get_memory_requirements2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1">Version 1.1</a></dd>
 * <dt><b>Deprecation State</b></dt>
 * <dd><ul>
 * <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1-promotions">Vulkan 1.1</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>James Jones <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_dedicated_allocation]%20@cubanismo%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_dedicated_allocation%20extension*">cubanismo</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2017-09-05</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Jeff Bolz, NVIDIA</li>
 * <li>Faith Ekstrand, Intel</li>
 * </ul></dd>
 * </dl>
 */
public final class KHRDedicatedAllocation {

    /** The extension specification version. */
    public static final int VK_KHR_DEDICATED_ALLOCATION_SPEC_VERSION = 3;

    /** The extension name. */
    public static final String VK_KHR_DEDICATED_ALLOCATION_EXTENSION_NAME = "VK_KHR_dedicated_allocation";

    /**
     * Extends {@code VkStructureType}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #VK_STRUCTURE_TYPE_MEMORY_DEDICATED_REQUIREMENTS_KHR STRUCTURE_TYPE_MEMORY_DEDICATED_REQUIREMENTS_KHR}</li>
     * <li>{@link #VK_STRUCTURE_TYPE_MEMORY_DEDICATED_ALLOCATE_INFO_KHR STRUCTURE_TYPE_MEMORY_DEDICATED_ALLOCATE_INFO_KHR}</li>
     * </ul>
     */
    public static final int
        VK_STRUCTURE_TYPE_MEMORY_DEDICATED_REQUIREMENTS_KHR  = 1000127000,
        VK_STRUCTURE_TYPE_MEMORY_DEDICATED_ALLOCATE_INFO_KHR = 1000127001;

    private KHRDedicatedAllocation() {}

}