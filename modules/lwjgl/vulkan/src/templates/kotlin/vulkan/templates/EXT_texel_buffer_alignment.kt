/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val EXT_texel_buffer_alignment = "EXTTexelBufferAlignment".nativeClassVK("EXT_texel_buffer_alignment", type = "device", postfix = "EXT") {
    documentation =
        """
        This extension adds more expressive alignment requirements for uniform and storage texel buffers. Some implementations have single texel alignment requirements that cannot be expressed via ##VkPhysicalDeviceLimits{@code ::minTexelBufferOffsetAlignment}.

        <h5>Promotion to Vulkan 1.3</h5>
        Functionality in this extension is included in core Vulkan 1.3, with the EXT suffix omitted. However, only the properties structure is promoted. The feature structure is not promoted and {@code texelBufferAlignment} is enabled if using a Vulkan 1.3 instance. The original type name is still available as an alias of the core functionality.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_EXT_texel_buffer_alignment}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>282</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>Deprecation State</b></dt>
            <dd><ul>
                <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.3-promotions">Vulkan 1.3</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Jeff Bolz <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_texel_buffer_alignment]%20@jeffbolznv%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_texel_buffer_alignment%20extension*">jeffbolznv</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2019-06-06</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Jeff Bolz, NVIDIA</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "EXT_TEXEL_BUFFER_ALIGNMENT_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "EXT_TEXEL_BUFFER_ALIGNMENT_EXTENSION_NAME".."VK_EXT_texel_buffer_alignment"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_TEXEL_BUFFER_ALIGNMENT_FEATURES_EXT".."1000281000",
        "STRUCTURE_TYPE_PHYSICAL_DEVICE_TEXEL_BUFFER_ALIGNMENT_PROPERTIES_EXT".."1000281001"
    )
}