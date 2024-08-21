/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension adds support for the following SPIR-V extension in Vulkan:
 * 
 * <ul>
 * <li>{@code SPV_KHR_post_depth_coverage}</li>
 * </ul>
 * 
 * <p>which allows the fragment shader to control whether values in the {@code SampleMask} built-in input variable reflect the coverage after early <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#fragops-depth">depth</a> and <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#fragops-stencil">stencil</a> tests are applied.</p>
 * 
 * <p>This extension adds a new {@code PostDepthCoverage} execution mode under the {@code SampleMaskPostDepthCoverage} capability. When this mode is specified along with {@code EarlyFragmentTests}, the value of an input variable decorated with the <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#interfaces-builtin-variables-samplemask">{@code SampleMask}</a> built-in reflects the coverage after the early fragment tests are applied. Otherwise, it reflects the coverage before the depth and stencil tests.</p>
 * 
 * <p>When using GLSL source-based shading languages, the {@code post_depth_coverage} layout qualifier from GL_ARB_post_depth_coverage or GL_EXT_post_depth_coverage maps to the {@code PostDepthCoverage} execution mode.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_EXT_post_depth_coverage}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>156</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>SPIR-V Dependencies</b></dt>
 * <dd><ul>
 * <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/KHR/SPV_KHR_post_depth_coverage.html">SPV_KHR_post_depth_coverage</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Daniel Koch <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_post_depth_coverage]%20@dgkoch%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_post_depth_coverage%20extension*">dgkoch</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2017-07-17</dd>
 * <dt><b>Interactions and External Dependencies</b></dt>
 * <dd><ul>
 * <li>This extension provides API support for <a href="https://registry.khronos.org/OpenGL/extensions/ARB/ARB_post_depth_coverage.txt">{@code GL_ARB_post_depth_coverage}</a> and <a href="https://registry.khronos.org/OpenGL/extensions/EXT/EXT_post_depth_coverage.txt">{@code GL_EXT_post_depth_coverage}</a></li>
 * </ul></dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Jeff Bolz, NVIDIA</li>
 * </ul></dd>
 * </dl>
 */
public final class EXTPostDepthCoverage {

    /** The extension specification version. */
    public static final int VK_EXT_POST_DEPTH_COVERAGE_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_EXT_POST_DEPTH_COVERAGE_EXTENSION_NAME = "VK_EXT_post_depth_coverage";

    private EXTPostDepthCoverage() {}

}