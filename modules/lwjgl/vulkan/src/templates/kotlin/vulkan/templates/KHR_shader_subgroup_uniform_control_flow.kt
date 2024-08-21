/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val KHR_shader_subgroup_uniform_control_flow = "KHRShaderSubgroupUniformControlFlow".nativeClassVK("KHR_shader_subgroup_uniform_control_flow", type = "device", postfix = "KHR") {
    documentation =
        """
        This extension allows the use of the {@code SPV_KHR_subgroup_uniform_control_flow} SPIR-V extension in shader modules. {@code SPV_KHR_subgroup_uniform_control_flow} provides stronger guarantees that diverged subgroups will reconverge.

        Developers should utilize this extension if they use subgroup operations to reduce the work performed by a uniform subgroup. This extension will guarantee that uniform subgroup will reconverge in the same manner as invocation groups (see “{@code Uniform Control Flow}” in the <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#spirv-spec">Khronos SPIR-V Specification</a>).

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_KHR_shader_subgroup_uniform_control_flow}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>324</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>SPIR-V Dependencies</b></dt>
            <dd><ul>
                <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/KHR/SPV_KHR_subgroup_uniform_control_flow.html">SPV_KHR_subgroup_uniform_control_flow</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Alan Baker <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_shader_subgroup_uniform_control_flow]%20@alan-baker%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_shader_subgroup_uniform_control_flow%20extension*">alan-baker</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2020-08-27</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Interactions and External Dependencies</b></dt>
            <dd><ul>
                <li>Requires SPIR-V 1.3.</li>
                <li>This extension provides API support for <a href="https://github.com/KhronosGroup/GLSL/blob/main/extensions/ext/GL_EXT_subgroupuniform_qualifier.txt">{@code GL_EXT_subgroupuniform_qualifier}</a></li>
            </ul></dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Alan Baker, Google</li>
                <li>Jeff Bolz, NVIDIA</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "KHR_SHADER_SUBGROUP_UNIFORM_CONTROL_FLOW_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "KHR_SHADER_SUBGROUP_UNIFORM_CONTROL_FLOW_EXTENSION_NAME".."VK_KHR_shader_subgroup_uniform_control_flow"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_SHADER_SUBGROUP_UNIFORM_CONTROL_FLOW_FEATURES_KHR".."1000323000"
    )
}