/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val KHR_draw_indirect_count = "KHRDrawIndirectCount".nativeClassVK("KHR_draw_indirect_count", type = "device", postfix = "KHR") {
    documentation =
        """
        This extension is based on the {@link AMDDrawIndirectCount VK_AMD_draw_indirect_count} extension. This extension allows an application to source the number of draws for indirect drawing calls from a buffer.

        Applications might want to do culling on the GPU via a compute shader prior to drawing. This enables the application to generate an arbitrary number of drawing commands and execute them without host intervention.

        <h5>Promotion to Vulkan 1.2</h5>
        All functionality in this extension is included in core Vulkan 1.2, with the KHR suffix omitted. However, if Vulkan 1.2 is supported and this extension is not, the commands #CmdDrawIndirectCount() and #CmdDrawIndexedIndirectCount() are optional. The original type, enum and command names are still available as aliases of the core functionality.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_KHR_draw_indirect_count}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>170</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Deprecation State</b></dt>
            <dd><ul>
                <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.2-promotions">Vulkan 1.2</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Piers Daniell <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_draw_indirect_count]%20@pdaniell-nv%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_draw_indirect_count%20extension*">pdaniell-nv</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2017-08-25</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Matthaeus G. Chajdas, AMD</li>
                <li>Derrick Owens, AMD</li>
                <li>Graham Sellers, AMD</li>
                <li>Daniel Rakos, AMD</li>
                <li>Dominik Witczak, AMD</li>
                <li>Piers Daniell, NVIDIA</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "KHR_DRAW_INDIRECT_COUNT_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "KHR_DRAW_INDIRECT_COUNT_EXTENSION_NAME".."VK_KHR_draw_indirect_count"
    )

    void(
        "CmdDrawIndirectCountKHR",
        "See #CmdDrawIndirectCount().",

        VkCommandBuffer("commandBuffer", "the command buffer into which the command is recorded."),
        VkBuffer("buffer", "the buffer containing draw parameters."),
        VkDeviceSize("offset", "the byte offset into {@code buffer} where parameters begin."),
        VkBuffer("countBuffer", "the buffer containing the draw count."),
        VkDeviceSize("countBufferOffset", "the byte offset into {@code countBuffer} where the draw count begins."),
        uint32_t("maxDrawCount", "specifies the maximum number of draws that will be executed. The actual number of executed draw calls is the minimum of the count specified in {@code countBuffer} and {@code maxDrawCount}."),
        uint32_t("stride", "the byte stride between successive sets of draw parameters.")
    )

    void(
        "CmdDrawIndexedIndirectCountKHR",
        "See #CmdDrawIndexedIndirectCount().",

        VkCommandBuffer("commandBuffer", "the command buffer into which the command is recorded."),
        VkBuffer("buffer", "the buffer containing draw parameters."),
        VkDeviceSize("offset", "the byte offset into {@code buffer} where parameters begin."),
        VkBuffer("countBuffer", "the buffer containing the draw count."),
        VkDeviceSize("countBufferOffset", "the byte offset into {@code countBuffer} where the draw count begins."),
        uint32_t("maxDrawCount", "specifies the maximum number of draws that will be executed. The actual number of executed draw calls is the minimum of the count specified in {@code countBuffer} and {@code maxDrawCount}."),
        uint32_t("stride", "the byte stride between successive sets of draw parameters.")
    )
}