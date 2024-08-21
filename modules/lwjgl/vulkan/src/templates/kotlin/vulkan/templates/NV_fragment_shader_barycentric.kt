/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val NV_fragment_shader_barycentric = "NVFragmentShaderBarycentric".nativeClassVK("NV_fragment_shader_barycentric", type = "device", postfix = "NV") {
    documentation =
        """
        This extension adds support for the following SPIR-V extension in Vulkan:

        <ul>
            <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/NV/SPV_NV_fragment_shader_barycentric.html">{@code SPV_NV_fragment_shader_barycentric}</a></li>
        </ul>

        The extension provides access to three additional fragment shader variable decorations in SPIR-V:

        <ul>
            <li>{@code PerVertexNV}, which indicates that a fragment shader input will not have interpolated values, but instead must be accessed with an extra array index that identifies one of the vertices of the primitive producing the fragment</li>
            <li>{@code BaryCoordNV}, which indicates that the variable is a three-component floating-point vector holding barycentric weights for the fragment produced using perspective interpolation</li>
            <li>{@code BaryCoordNoPerspNV}, which indicates that the variable is a three-component floating-point vector holding barycentric weights for the fragment produced using linear interpolation</li>
        </ul>

        When using GLSL source-based shader languages, the following variables from {@code GL_NV_fragment_shader_barycentric} maps to these SPIR-V built-in decorations:

        <ul>
            <li>{@code in vec3 gl_BaryCoordNV;} → {@code BaryCoordNV}</li>
            <li>{@code in vec3 gl_BaryCoordNoPerspNV;} → {@code BaryCoordNoPerspNV}</li>
        </ul>

        GLSL variables declared using the {@code __pervertexNV} GLSL qualifier are expected to be decorated with {@code PerVertexNV} in SPIR-V.

        <h5>Promotion to {@code VK_KHR_fragment_shader_barycentric}</h5>
        All functionality in this extension is included in {@link KHRFragmentShaderBarycentric VK_KHR_fragment_shader_barycentric}, with the suffix changed to KHR.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_NV_fragment_shader_barycentric}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>204</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>SPIR-V Dependencies</b></dt>
            <dd><ul>
                <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/NV/SPV_NV_fragment_shader_barycentric.html">SPV_NV_fragment_shader_barycentric</a></li>
            </ul></dd>

            <dt><b>Deprecation State</b></dt>
            <dd><ul>
                <li><em>Promoted</em> to {@link KHRFragmentShaderBarycentric VK_KHR_fragment_shader_barycentric} extension</li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Pat Brown <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_NV_fragment_shader_barycentric]%20@nvpbrown%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_NV_fragment_shader_barycentric%20extension*">nvpbrown</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2018-08-03</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Interactions and External Dependencies</b></dt>
            <dd><ul>
                <li>This extension provides API support for <a href="https://github.com/KhronosGroup/GLSL/blob/main/extensions/nv/GLSL_NV_fragment_shader_barycentric.txt">{@code GL_NV_fragment_shader_barycentric}</a></li>
            </ul></dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Pat Brown, NVIDIA</li>
                <li>Daniel Koch, NVIDIA</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "NV_FRAGMENT_SHADER_BARYCENTRIC_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "NV_FRAGMENT_SHADER_BARYCENTRIC_EXTENSION_NAME".."VK_NV_fragment_shader_barycentric"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_FRAGMENT_SHADER_BARYCENTRIC_FEATURES_NV".."1000203000"
    )
}