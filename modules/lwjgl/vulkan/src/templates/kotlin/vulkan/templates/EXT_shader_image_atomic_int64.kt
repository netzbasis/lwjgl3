/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val EXT_shader_image_atomic_int64 = "EXTShaderImageAtomicInt64".nativeClassVK("EXT_shader_image_atomic_int64", type = "device", postfix = "EXT") {
    documentation =
        """
        This extension extends existing 64-bit integer atomic support to enable these operations on images as well.

        When working with large 2- or 3-dimensional data sets (e.g. rasterization or screen-space effects), image accesses are generally more efficient than equivalent buffer accesses. This extension allows applications relying on 64-bit integer atomics in this manner to quickly improve performance with only relatively minor code changes.

        64-bit integer atomic support is guaranteed for optimally tiled images with the #FORMAT_R64_UINT and #FORMAT_R64_SINT formats.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_EXT_shader_image_atomic_int64}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>235</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>SPIR-V Dependencies</b></dt>
            <dd><ul>
                <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/EXT/SPV_EXT_shader_image_int64.html">SPV_EXT_shader_image_int64</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Tobias Hector <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_shader_image_atomic_int64]%20@tobski%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_shader_image_atomic_int64%20extension*">tobski</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2020-07-14</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Interactions and External Dependencies</b></dt>
            <dd><ul>
                <li>This extension provides API support for <a href="https://github.com/KhronosGroup/GLSL/blob/main/extensions/ext/GLSL_EXT_shader_image_int64.txt">{@code GLSL_EXT_shader_image_int64}</a></li>
            </ul></dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Matthaeus Chajdas, AMD</li>
                <li>Graham Wihlidal, Epic Games</li>
                <li>Tobias Hector, AMD</li>
                <li>Jeff Bolz, Nvidia</li>
                <li>Faith Ekstrand, Intel</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "EXT_SHADER_IMAGE_ATOMIC_INT64_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "EXT_SHADER_IMAGE_ATOMIC_INT64_EXTENSION_NAME".."VK_EXT_shader_image_atomic_int64"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_SHADER_IMAGE_ATOMIC_INT64_FEATURES_EXT".."1000234000"
    )
}