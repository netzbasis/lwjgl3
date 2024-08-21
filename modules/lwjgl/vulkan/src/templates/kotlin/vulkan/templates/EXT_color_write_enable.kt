/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val EXT_color_write_enable = "EXTColorWriteEnable".nativeClassVK("EXT_color_write_enable", type = "device", postfix = "EXT") {
    documentation =
        """
        This extension allows for selectively enabling and disabling writes to output color attachments via a pipeline dynamic state.

        The intended use cases for this new state are mostly identical to those of colorWriteMask, such as selectively disabling writes to avoid feedback loops between subpasses or bandwidth savings for unused outputs. By making the state dynamic, one additional benefit is the ability to reduce pipeline counts and pipeline switching via shaders that write a superset of the desired data of which subsets are selected dynamically. The reason for a new state, colorWriteEnable, rather than making colorWriteMask dynamic is that, on many implementations, the more flexible per-component semantics of the colorWriteMask state cannot be made dynamic in a performant manner.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_EXT_color_write_enable}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>382</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Sharif Elcott <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_color_write_enable]%20@selcott%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_color_write_enable%20extension*">selcott</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2020-02-25</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Sharif Elcott, Google</li>
                <li>Tobias Hector, AMD</li>
                <li>Piers Daniell, NVIDIA</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "EXT_COLOR_WRITE_ENABLE_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "EXT_COLOR_WRITE_ENABLE_EXTENSION_NAME".."VK_EXT_color_write_enable"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_COLOR_WRITE_ENABLE_FEATURES_EXT".."1000381000",
        "STRUCTURE_TYPE_PIPELINE_COLOR_WRITE_CREATE_INFO_EXT".."1000381001"
    )

    EnumConstant(
        "Extends {@code VkDynamicState}.",

        "DYNAMIC_STATE_COLOR_WRITE_ENABLE_EXT".."1000381000"
    )

    void(
        "CmdSetColorWriteEnableEXT",
        """
        Enable or disable writes to a color attachment dynamically for a command buffer.

        <h5>C Specification</h5>
        To <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#pipelines-dynamic-state">dynamically enable or disable</a> writes to a color attachment, call:

        <pre><code>
￿void                                    vkCmdSetColorWriteEnableEXT(
￿    VkCommandBuffer                             commandBuffer,
￿    uint32_t                                    attachmentCount,
￿    const VkBool32*                             pColorWriteEnables);</code></pre>

        <h5>Description</h5>
        This command sets the color write enables for subsequent drawing commands when drawing using <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#shaders-objects">shader objects</a>, or when the graphics pipeline is created with #DYNAMIC_STATE_COLOR_WRITE_ENABLE_EXT set in ##VkPipelineDynamicStateCreateInfo{@code ::pDynamicStates}. Otherwise, this state is specified by the ##VkPipelineColorWriteCreateInfoEXT{@code ::pColorWriteEnables} values used to create the currently active pipeline.

        <h5>Valid Usage</h5>
        <ul>
            <li>The <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#features-colorWriteEnable">{@code colorWriteEnable}</a> feature <b>must</b> be enabled</li>
            <li>{@code attachmentCount} <b>must</b> be less than or equal to the {@code maxColorAttachments} member of ##VkPhysicalDeviceLimits</li>
        </ul>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code commandBuffer} <b>must</b> be a valid {@code VkCommandBuffer} handle</li>
            <li>{@code pColorWriteEnables} <b>must</b> be a valid pointer to an array of {@code attachmentCount} {@code VkBool32} values</li>
            <li>{@code commandBuffer} <b>must</b> be in the <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#commandbuffers-lifecycle">recording state</a></li>
            <li>The {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> support graphics operations</li>
            <li>This command <b>must</b> only be called outside of a video coding scope</li>
            <li>{@code attachmentCount} <b>must</b> be greater than 0</li>
        </ul>

        <h5>Host Synchronization</h5>
        <ul>
            <li>Host access to {@code commandBuffer} <b>must</b> be externally synchronized</li>
            <li>Host access to the {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> be externally synchronized</li>
        </ul>

        <h5>Command Properties</h5>
        <table class="lwjgl">
            <thead><tr><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#VkCommandBufferLevel">Command Buffer Levels</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#vkCmdBeginRenderPass">Render Pass Scope</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#vkCmdBeginVideoCodingKHR">Video Coding Scope</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#VkQueueFlagBits">Supported Queue Types</a></th><th><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#fundamentals-queueoperation-command-types">Command Type</a></th></tr></thead>
            <tbody><tr><td>Primary Secondary</td><td>Both</td><td>Outside</td><td>Graphics</td><td>State</td></tr></tbody>
        </table>
        """,

        VkCommandBuffer("commandBuffer", "the command buffer into which the command will be recorded."),
        AutoSize("pColorWriteEnables")..uint32_t("attachmentCount", "the number of {@code VkBool32} elements in {@code pColorWriteEnables}."),
        VkBool32.const.p("pColorWriteEnables", "a pointer to an array of per target attachment boolean values specifying whether color writes are enabled for the given attachment.")
    )
}