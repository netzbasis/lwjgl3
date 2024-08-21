/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val NV_sample_mask_override_coverage = "NVSampleMaskOverrideCoverage".nativeClassVK("NV_sample_mask_override_coverage", type = "device", postfix = "NV") {
    documentation =
        """
        This extension adds support for the following SPIR-V extension in Vulkan:

        <ul>
            <li>{@code SPV_NV_sample_mask_override_coverage}</li>
        </ul>

        The extension provides access to the {@code OverrideCoverageNV} decoration under the {@code SampleMaskOverrideCoverageNV} capability. Adding this decoration to a variable with the {@code SampleMask} builtin decoration allows the shader to modify the coverage mask and affect which samples are used to process the fragment.

        When using GLSL source-based shader languages, the {@code override_coverage} layout qualifier from {@code GL_NV_sample_mask_override_coverage} maps to the {@code OverrideCoverageNV} decoration. To use the {@code override_coverage} layout qualifier in GLSL the {@code GL_NV_sample_mask_override_coverage} extension must be enabled. Behavior is described in the {@code GL_NV_sample_mask_override_coverage} extension spec.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_NV_sample_mask_override_coverage}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>95</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>SPIR-V Dependencies</b></dt>
            <dd><ul>
                <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/NV/SPV_NV_sample_mask_override_coverage.html">SPV_NV_sample_mask_override_coverage</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Piers Daniell <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_NV_sample_mask_override_coverage]%20@pdaniell-nv%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_NV_sample_mask_override_coverage%20extension*">pdaniell-nv</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2016-12-08</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Interactions and External Dependencies</b></dt>
            <dd><ul>
                <li>This extension provides API support for <a href="https://registry.khronos.org/OpenGL/extensions/NV/NV_sample_mask_override_coverage.txt">{@code GL_NV_sample_mask_override_coverage}</a></li>
            </ul></dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Daniel Koch, NVIDIA</li>
                <li>Jeff Bolz, NVIDIA</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "NV_SAMPLE_MASK_OVERRIDE_COVERAGE_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "NV_SAMPLE_MASK_OVERRIDE_COVERAGE_EXTENSION_NAME".."VK_NV_sample_mask_override_coverage"
    )
}