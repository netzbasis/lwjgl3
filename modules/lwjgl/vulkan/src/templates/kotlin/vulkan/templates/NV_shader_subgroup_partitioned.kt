/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val NV_shader_subgroup_partitioned = "NVShaderSubgroupPartitioned".nativeClassVK("NV_shader_subgroup_partitioned", type = "device", postfix = "NV") {
    documentation =
        """
        This extension enables support for a new class of <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#shaders-group-operations">group operations</a> on <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#shaders-scope-subgroup">subgroups</a> via the <a href="https://github.com/KhronosGroup/GLSL/blob/main/extensions/nv/GL_NV_shader_subgroup_partitioned.txt">{@code GL_NV_shader_subgroup_partitioned}</a> GLSL extension and <a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/NV/SPV_NV_shader_subgroup_partitioned.html">{@code SPV_NV_shader_subgroup_partitioned}</a> SPIR-V extension. Support for these new operations is advertised via the #SUBGROUP_FEATURE_PARTITIONED_BIT_NV bit.

        This extension requires Vulkan 1.1, for general subgroup support.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_NV_shader_subgroup_partitioned}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>199</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>SPIR-V Dependencies</b></dt>
            <dd><ul>
                <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/NV/SPV_NV_shader_subgroup_partitioned.html">SPV_NV_shader_subgroup_partitioned</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Jeff Bolz <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_NV_shader_subgroup_partitioned]%20@jeffbolznv%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_NV_shader_subgroup_partitioned%20extension*">jeffbolznv</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2018-03-17</dd>

            <dt><b>Interactions and External Dependencies</b></dt>
            <dd><ul>
                <li>This extension provides API support for <a href="https://github.com/KhronosGroup/GLSL/blob/main/extensions/nv/GL_NV_shader_subgroup_partitioned.txt">{@code GL_NV_shader_subgroup_partitioned}</a></li>
            </ul></dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Jeff Bolz, NVIDIA</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "NV_SHADER_SUBGROUP_PARTITIONED_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "NV_SHADER_SUBGROUP_PARTITIONED_EXTENSION_NAME".."VK_NV_shader_subgroup_partitioned"
    )

    EnumConstant(
        "Extends {@code VkSubgroupFeatureFlagBits}.",

        "SUBGROUP_FEATURE_PARTITIONED_BIT_NV".enum(0x00000100)
    )
}