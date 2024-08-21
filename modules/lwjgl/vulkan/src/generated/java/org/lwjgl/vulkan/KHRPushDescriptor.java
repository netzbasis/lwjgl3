/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

import org.lwjgl.system.*;

import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;

/**
 * This extension allows descriptors to be written into the command buffer, while the implementation is responsible for managing their memory. Push descriptors may enable easier porting from older APIs and in some cases can be more efficient than writing descriptors into descriptor sets.
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_KHR_push_descriptor}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>81</dd>
 * <dt><b>Revision</b></dt>
 * <dd>2</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1">Version 1.1</a></dd>
 * <dt><b>API Interactions</b></dt>
 * <dd><ul>
 * <li>Interacts with VK_VERSION_1_1</li>
 * <li>Interacts with VK_KHR_descriptor_update_template</li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Jeff Bolz <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_push_descriptor]%20@jeffbolznv%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_push_descriptor%20extension*">jeffbolznv</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2017-09-12</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Jeff Bolz, NVIDIA</li>
 * <li>Michael Worcester, Imagination Technologies</li>
 * </ul></dd>
 * </dl>
 */
public class KHRPushDescriptor {

    /** The extension specification version. */
    public static final int VK_KHR_PUSH_DESCRIPTOR_SPEC_VERSION = 2;

    /** The extension name. */
    public static final String VK_KHR_PUSH_DESCRIPTOR_EXTENSION_NAME = "VK_KHR_push_descriptor";

    /** Extends {@code VkStructureType}. */
    public static final int VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_PUSH_DESCRIPTOR_PROPERTIES_KHR = 1000080000;

    /** Extends {@code VkDescriptorSetLayoutCreateFlagBits}. */
    public static final int VK_DESCRIPTOR_SET_LAYOUT_CREATE_PUSH_DESCRIPTOR_BIT_KHR = 0x1;

    /** Extends {@code VkDescriptorUpdateTemplateType}. */
    public static final int VK_DESCRIPTOR_UPDATE_TEMPLATE_TYPE_PUSH_DESCRIPTORS_KHR = 1;

    protected KHRPushDescriptor() {
        throw new UnsupportedOperationException();
    }

    // --- [ vkCmdPushDescriptorSetKHR ] ---

    /**
     * Unsafe version of: {@link #vkCmdPushDescriptorSetKHR CmdPushDescriptorSetKHR}
     *
     * @param descriptorWriteCount the number of elements in the {@code pDescriptorWrites} array.
     */
    public static void nvkCmdPushDescriptorSetKHR(VkCommandBuffer commandBuffer, int pipelineBindPoint, long layout, int set, int descriptorWriteCount, long pDescriptorWrites) {
        long __functionAddress = commandBuffer.getCapabilities().vkCmdPushDescriptorSetKHR;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPJPV(commandBuffer.address(), pipelineBindPoint, layout, set, descriptorWriteCount, pDescriptorWrites, __functionAddress);
    }

    /**
     * Pushes descriptor updates into a command buffer.
     * 
     * <h5>C Specification</h5>
     * 
     * <p>In addition to allocating descriptor sets and binding them to a command buffer, an application <b>can</b> record descriptor updates into the command buffer.</p>
     * 
     * <p>To push descriptor updates into a command buffer, call:</p>
     * 
     * <pre><code>
     * void vkCmdPushDescriptorSetKHR(
     *     VkCommandBuffer                             commandBuffer,
     *     VkPipelineBindPoint                         pipelineBindPoint,
     *     VkPipelineLayout                            layout,
     *     uint32_t                                    set,
     *     uint32_t                                    descriptorWriteCount,
     *     const VkWriteDescriptorSet*                 pDescriptorWrites);</code></pre>
     * 
     * <h5>Description</h5>
     * 
     * <p><em>Push descriptors</em> are a small bank of descriptors whose storage is internally managed by the command buffer rather than being written into a descriptor set and later bound to a command buffer. Push descriptors allow for incremental updates of descriptors without managing the lifetime of descriptor sets.</p>
     * 
     * <p>When a command buffer begins recording, all push descriptors are undefined. Push descriptors <b>can</b> be updated incrementally and cause shaders to use the updated descriptors for subsequent <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#pipelines-bindpoint-commands">bound pipeline commands</a> with the pipeline type set by {@code pipelineBindPoint} until the descriptor is overwritten, or else until the set is disturbed as described in <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#descriptorsets-compatibility">Pipeline Layout Compatibility</a>. When the set is disturbed or push descriptors with a different descriptor set layout are set, all push descriptors are undefined.</p>
     * 
     * <p>Push descriptors that are <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#shaders-staticuse">statically used</a> by a pipeline <b>must</b> not be undefined at the time that a drawing or dispatching command is recorded to execute using that pipeline. This includes immutable sampler descriptors, which <b>must</b> be pushed before they are accessed by a pipeline (the immutable samplers are pushed, rather than the samplers in {@code pDescriptorWrites}). Push descriptors that are not statically used <b>can</b> remain undefined.</p>
     * 
     * <p>Push descriptors do not use dynamic offsets. Instead, the corresponding non-dynamic descriptor types <b>can</b> be used and the {@code offset} member of {@link VkDescriptorBufferInfo} <b>can</b> be changed each time the descriptor is written.</p>
     * 
     * <p>Each element of {@code pDescriptorWrites} is interpreted as in {@link VkWriteDescriptorSet}, except the {@code dstSet} member is ignored.</p>
     * 
     * <p>To push an immutable sampler, use a {@link VkWriteDescriptorSet} with {@code dstBinding} and {@code dstArrayElement} selecting the immutable sampler’s binding. If the descriptor type is {@link VK10#VK_DESCRIPTOR_TYPE_SAMPLER DESCRIPTOR_TYPE_SAMPLER}, the {@code pImageInfo} parameter is ignored and the immutable sampler is taken from the push descriptor set layout in the pipeline layout. If the descriptor type is {@link VK10#VK_DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER}, the {@code sampler} member of the {@code pImageInfo} parameter is ignored and the immutable sampler is taken from the push descriptor set layout in the pipeline layout.</p>
     * 
     * <h5>Valid Usage</h5>
     * 
     * <ul>
     * <li>{@code set} <b>must</b> be less than {@link VkPipelineLayoutCreateInfo}{@code ::setLayoutCount} provided when {@code layout} was created</li>
     * <li>{@code set} <b>must</b> be the unique set number in the pipeline layout that uses a descriptor set layout that was created with {@link #VK_DESCRIPTOR_SET_LAYOUT_CREATE_PUSH_DESCRIPTOR_BIT_KHR DESCRIPTOR_SET_LAYOUT_CREATE_PUSH_DESCRIPTOR_BIT_KHR}</li>
     * <li>For each element <code>i</code> where {@code pDescriptorWrites}[i].{@code descriptorType} is {@link VK10#VK_DESCRIPTOR_TYPE_SAMPLER DESCRIPTOR_TYPE_SAMPLER}, {@link VK10#VK_DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER}, {@link VK10#VK_DESCRIPTOR_TYPE_SAMPLED_IMAGE DESCRIPTOR_TYPE_SAMPLED_IMAGE}, {@link VK10#VK_DESCRIPTOR_TYPE_STORAGE_IMAGE DESCRIPTOR_TYPE_STORAGE_IMAGE}, or {@link VK10#VK_DESCRIPTOR_TYPE_INPUT_ATTACHMENT DESCRIPTOR_TYPE_INPUT_ATTACHMENT}, {@code pDescriptorWrites}[i].{@code pImageInfo} <b>must</b> be a valid pointer to an array of {@code pDescriptorWrites}[i].{@code descriptorCount} valid {@link VkDescriptorImageInfo} structures</li>
     * <li>{@code pipelineBindPoint} <b>must</b> be supported by the {@code commandBuffer}’s parent {@code VkCommandPool}’s queue family</li>
     * </ul>
     * 
     * <h5>Valid Usage (Implicit)</h5>
     * 
     * <ul>
     * <li>{@code commandBuffer} <b>must</b> be a valid {@code VkCommandBuffer} handle</li>
     * <li>{@code pipelineBindPoint} <b>must</b> be a valid {@code VkPipelineBindPoint} value</li>
     * <li>{@code layout} <b>must</b> be a valid {@code VkPipelineLayout} handle</li>
     * <li>{@code pDescriptorWrites} <b>must</b> be a valid pointer to an array of {@code descriptorWriteCount} valid {@link VkWriteDescriptorSet} structures</li>
     * <li>{@code commandBuffer} <b>must</b> be in the <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#commandbuffers-lifecycle">recording state</a></li>
     * <li>The {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> support graphics, or compute operations</li>
     * <li>This command <b>must</b> only be called outside of a video coding scope</li>
     * <li>{@code descriptorWriteCount} <b>must</b> be greater than 0</li>
     * <li>Both of {@code commandBuffer}, and {@code layout} <b>must</b> have been created, allocated, or retrieved from the same {@code VkDevice}</li>
     * </ul>
     * 
     * <h5>Host Synchronization</h5>
     * 
     * <ul>
     * <li>Host access to {@code commandBuffer} <b>must</b> be externally synchronized</li>
     * <li>Host access to the {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> be externally synchronized</li>
     * </ul>
     * 
     * <h5>Command Properties</h5>
     * 
     * <table class="lwjgl">
     * <thead><tr><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#VkCommandBufferLevel">Command Buffer Levels</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#vkCmdBeginRenderPass">Render Pass Scope</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#vkCmdBeginVideoCodingKHR">Video Coding Scope</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#VkQueueFlagBits">Supported Queue Types</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#fundamentals-queueoperation-command-types">Command Type</a></th></tr></thead>
     * <tbody><tr><td>Primary Secondary</td><td>Both</td><td>Outside</td><td>Graphics Compute</td><td>State</td></tr></tbody>
     * </table>
     * 
     * <h5>See Also</h5>
     * 
     * <p>{@link VkWriteDescriptorSet}</p>
     *
     * @param commandBuffer     the command buffer that the descriptors will be recorded in.
     * @param pipelineBindPoint a {@code VkPipelineBindPoint} indicating the type of the pipeline that will use the descriptors. There is a separate set of push descriptor bindings for each pipeline type, so binding one does not disturb the others.
     * @param layout            a {@code VkPipelineLayout} object used to program the bindings.
     * @param set               the set number of the descriptor set in the pipeline layout that will be updated.
     * @param pDescriptorWrites a pointer to an array of {@link VkWriteDescriptorSet} structures describing the descriptors to be updated.
     */
    public static void vkCmdPushDescriptorSetKHR(VkCommandBuffer commandBuffer, @NativeType("VkPipelineBindPoint") int pipelineBindPoint, @NativeType("VkPipelineLayout") long layout, @NativeType("uint32_t") int set, @NativeType("VkWriteDescriptorSet const *") VkWriteDescriptorSet.Buffer pDescriptorWrites) {
        nvkCmdPushDescriptorSetKHR(commandBuffer, pipelineBindPoint, layout, set, pDescriptorWrites.remaining(), pDescriptorWrites.address());
    }

    // --- [ vkCmdPushDescriptorSetWithTemplateKHR ] ---

    /**
     * Pushes descriptor updates into a command buffer using a descriptor update template.
     * 
     * <h5>C Specification</h5>
     * 
     * <p>It is also possible to use a descriptor update template to specify the push descriptors to update. To do so, call:</p>
     * 
     * <pre><code>
     * void vkCmdPushDescriptorSetWithTemplateKHR(
     *     VkCommandBuffer                             commandBuffer,
     *     VkDescriptorUpdateTemplate                  descriptorUpdateTemplate,
     *     VkPipelineLayout                            layout,
     *     uint32_t                                    set,
     *     const void*                                 pData);</code></pre>
     * 
     * <h5>Valid Usage</h5>
     * 
     * <ul>
     * <li>The {@code pipelineBindPoint} specified during the creation of the descriptor update template <b>must</b> be supported by the {@code commandBuffer}’s parent {@code VkCommandPool}’s queue family</li>
     * <li>{@code pData} <b>must</b> be a valid pointer to a memory containing one or more valid instances of {@link VkDescriptorImageInfo}, {@link VkDescriptorBufferInfo}, or {@code VkBufferView} in a layout defined by {@code descriptorUpdateTemplate} when it was created with {@link VK11#vkCreateDescriptorUpdateTemplate CreateDescriptorUpdateTemplate}</li>
     * <li>{@code layout} <b>must</b> be compatible with the layout used to create {@code descriptorUpdateTemplate}</li>
     * <li>{@code descriptorUpdateTemplate} <b>must</b> have been created with a {@code templateType} of {@link #VK_DESCRIPTOR_UPDATE_TEMPLATE_TYPE_PUSH_DESCRIPTORS_KHR DESCRIPTOR_UPDATE_TEMPLATE_TYPE_PUSH_DESCRIPTORS_KHR}</li>
     * <li>{@code set} <b>must</b> be the same value used to create {@code descriptorUpdateTemplate}</li>
     * <li>{@code set} <b>must</b> be less than {@link VkPipelineLayoutCreateInfo}{@code ::setLayoutCount} provided when {@code layout} was created</li>
     * <li>{@code set} <b>must</b> be the unique set number in the pipeline layout that uses a descriptor set layout that was created with {@link #VK_DESCRIPTOR_SET_LAYOUT_CREATE_PUSH_DESCRIPTOR_BIT_KHR DESCRIPTOR_SET_LAYOUT_CREATE_PUSH_DESCRIPTOR_BIT_KHR}</li>
     * </ul>
     * 
     * <h5>Valid Usage (Implicit)</h5>
     * 
     * <ul>
     * <li>{@code commandBuffer} <b>must</b> be a valid {@code VkCommandBuffer} handle</li>
     * <li>{@code descriptorUpdateTemplate} <b>must</b> be a valid {@code VkDescriptorUpdateTemplate} handle</li>
     * <li>{@code layout} <b>must</b> be a valid {@code VkPipelineLayout} handle</li>
     * <li>{@code commandBuffer} <b>must</b> be in the <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#commandbuffers-lifecycle">recording state</a></li>
     * <li>The {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> support graphics, or compute operations</li>
     * <li>This command <b>must</b> only be called outside of a video coding scope</li>
     * <li>Each of {@code commandBuffer}, {@code descriptorUpdateTemplate}, and {@code layout} <b>must</b> have been created, allocated, or retrieved from the same {@code VkDevice}</li>
     * </ul>
     * 
     * <h5>Host Synchronization</h5>
     * 
     * <ul>
     * <li>Host access to {@code commandBuffer} <b>must</b> be externally synchronized</li>
     * <li>Host access to the {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> be externally synchronized</li>
     * </ul>
     * 
     * <h5>Command Properties</h5>
     * 
     * <table class="lwjgl">
     * <thead><tr><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#VkCommandBufferLevel">Command Buffer Levels</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#vkCmdBeginRenderPass">Render Pass Scope</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#vkCmdBeginVideoCodingKHR">Video Coding Scope</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#VkQueueFlagBits">Supported Queue Types</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#fundamentals-queueoperation-command-types">Command Type</a></th></tr></thead>
     * <tbody><tr><td>Primary Secondary</td><td>Both</td><td>Outside</td><td>Graphics Compute</td><td>State</td></tr></tbody>
     * </table>
     * 
     * <pre><code>
     * struct AppDataStructure
     * {
     *     VkDescriptorImageInfo  imageInfo;          // a single image info
     *     // ... some more application-related data
     * };
     * 
     * const VkDescriptorUpdateTemplateEntry descriptorUpdateTemplateEntries[] =
     * {
     *     // binding to a single image descriptor
     *     {
     *         .binding = 0,
     *         .dstArrayElement = 0,
     *         .descriptorCount = 1,
     *         .descriptorType = VK_DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER,
     *         .offset = offsetof(AppDataStructure, imageInfo),
     *         .stride = 0     // not required if descriptorCount is 1
     *     }
     * };
     * 
     * // create a descriptor update template for push descriptor set updates
     * const VkDescriptorUpdateTemplateCreateInfo createInfo =
     * {
     *     .sType = VK_STRUCTURE_TYPE_DESCRIPTOR_UPDATE_TEMPLATE_CREATE_INFO,
     *     .pNext = NULL,
     *     .flags = 0,
     *     .descriptorUpdateEntryCount = 1,
     *     .pDescriptorUpdateEntries = descriptorUpdateTemplateEntries,
     *     .templateType = VK_DESCRIPTOR_UPDATE_TEMPLATE_TYPE_PUSH_DESCRIPTORS_KHR,
     *     .descriptorSetLayout = 0,   // ignored by given templateType
     *     .pipelineBindPoint = VK_PIPELINE_BIND_POINT_GRAPHICS,
     *     .pipelineLayout = myPipelineLayout,
     *     .set = 0,
     * };
     * 
     * VkDescriptorUpdateTemplate myDescriptorUpdateTemplate;
     * myResult = vkCreateDescriptorUpdateTemplate(
     *     myDevice,
     *     &amp;createInfo,
     *     NULL,
     *     &amp;myDescriptorUpdateTemplate);
     * 
     * AppDataStructure appData;
     * // fill appData here or cache it in your engine
     * vkCmdPushDescriptorSetWithTemplateKHR(myCmdBuffer, myDescriptorUpdateTemplate, myPipelineLayout, 0,&amp;appData);</code></pre>
     *
     * @param commandBuffer            the command buffer that the descriptors will be recorded in.
     * @param descriptorUpdateTemplate a descriptor update template defining how to interpret the descriptor information in {@code pData}.
     * @param layout                   a {@code VkPipelineLayout} object used to program the bindings. It <b>must</b> be compatible with the layout used to create the {@code descriptorUpdateTemplate} handle.
     * @param set                      the set number of the descriptor set in the pipeline layout that will be updated. This <b>must</b> be the same number used to create the {@code descriptorUpdateTemplate} handle.
     * @param pData                    a pointer to memory containing descriptors for the templated update.
     */
    public static void vkCmdPushDescriptorSetWithTemplateKHR(VkCommandBuffer commandBuffer, @NativeType("VkDescriptorUpdateTemplate") long descriptorUpdateTemplate, @NativeType("VkPipelineLayout") long layout, @NativeType("uint32_t") int set, @NativeType("void const *") long pData) {
        long __functionAddress = commandBuffer.getCapabilities().vkCmdPushDescriptorSetWithTemplateKHR;
        if (CHECKS) {
            check(__functionAddress);
        }
        callPJJPV(commandBuffer.address(), descriptorUpdateTemplate, layout, set, pData, __functionAddress);
    }

}