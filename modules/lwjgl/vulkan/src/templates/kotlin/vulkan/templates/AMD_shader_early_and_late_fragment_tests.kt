/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val AMD_shader_early_and_late_fragment_tests = "AMDShaderEarlyAndLateFragmentTests".nativeClassVK("AMD_shader_early_and_late_fragment_tests", type = "device", postfix = "AMD") {
    documentation =
        """
        This extension adds support for the <a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/AMD/SPV_AMD_shader_early_and_late_fragment_tests.html">{@code SPV_AMD_shader_early_and_late_fragment_tests}</a> extension, allowing shaders to explicitly opt in to allowing both early <em>and</em> late fragment tests with the {@code EarlyAndLateFragmentTestsAMD} execution mode.

        If {@link EXTShaderStencilExport VK_EXT_shader_stencil_export} is supported, additional execution modes allowing early depth tests similar to {@code DepthUnchanged}, {@code DepthLess}, and {@code DepthGreater} are provided.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_AMD_shader_early_and_late_fragment_tests}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>322</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>SPIR-V Dependencies</b></dt>
            <dd><ul>
                <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/AMD/SPV_AMD_shader_early_and_late_fragment_tests.html">SPV_AMD_shader_early_and_late_fragment_tests</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Tobias Hector <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_AMD_shader_early_and_late_fragment_tests]%20@tobski%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_AMD_shader_early_and_late_fragment_tests%20extension*">tobski</a></li>
            </ul></dd>

            <dt><b>Extension Proposal</b></dt>
            <dd><a href="https://github.com/KhronosGroup/Vulkan-Docs/tree/main/proposals/VK_AMD_shader_early_and_late_fragment_tests.adoc">VK_AMD_shader_early_and_late_fragment_tests</a></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2021-09-14</dd>

            <dt><b>Interactions and External Dependencies</b></dt>
            <dd><ul>
                <li>This extension interacts with {@link EXTShaderStencilExport VK_EXT_shader_stencil_export}</li>
            </ul></dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Tobias Hector, AMD</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "AMD_SHADER_EARLY_AND_LATE_FRAGMENT_TESTS_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "AMD_SHADER_EARLY_AND_LATE_FRAGMENT_TESTS_EXTENSION_NAME".."VK_AMD_shader_early_and_late_fragment_tests"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_SHADER_EARLY_AND_LATE_FRAGMENT_TESTS_FEATURES_AMD".."1000321000"
    )
}