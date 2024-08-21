/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension is based on the {@link NVFragmentShaderBarycentric VK_NV_fragment_shader_barycentric} extension, and adds support for the following SPIR-V extension in Vulkan:
 * 
 * <ul>
 * <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/KHR/SPV_KHR_fragment_shader_barycentric.html">{@code SPV_KHR_fragment_shader_barycentric}</a></li>
 * </ul>
 * 
 * <p>The extension provides access to three additional fragment shader variable decorations in SPIR-V:</p>
 * 
 * <ul>
 * <li>{@code PerVertexKHR}, which indicates that a fragment shader input will not have interpolated values, but instead must be accessed with an extra array index that identifies one of the vertices of the primitive producing the fragment</li>
 * <li>{@code BaryCoordKHR}, which indicates that the variable is a three-component floating-point vector holding barycentric weights for the fragment produced using perspective interpolation</li>
 * <li>{@code BaryCoordNoPerspKHR}, which indicates that the variable is a three-component floating-point vector holding barycentric weights for the fragment produced using linear interpolation</li>
 * </ul>
 * 
 * <p>When using GLSL source-based shader languages, the following variables from {@code GL_EXT_fragment_shader_barycentric} map to these SPIR-V built-in decorations:</p>
 * 
 * <ul>
 * <li>{@code in vec3 gl_BaryCoordEXT;} → {@code BaryCoordKHR}</li>
 * <li>{@code in vec3 gl_BaryCoordNoPerspEXT;} → {@code BaryCoordNoPerspKHR}</li>
 * </ul>
 * 
 * <p>GLSL variables declared using the {@code pervertexEXT} GLSL qualifier are expected to be decorated with {@code PerVertexKHR} in SPIR-V.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_KHR_fragment_shader_barycentric}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>323</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1">Version 1.1</a></dd>
 * <dt><b>SPIR-V Dependencies</b></dt>
 * <dd><ul>
 * <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/KHR/SPV_KHR_fragment_shader_barycentric.html">SPV_KHR_fragment_shader_barycentric</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Stu Smith</li>
 * </ul></dd>
 * <dt><b>Extension Proposal</b></dt>
 * <dd><a href="https://github.com/KhronosGroup/Vulkan-Docs/tree/main/proposals/VK_KHR_fragment_shader_barycentric.adoc">VK_KHR_fragment_shader_barycentric</a></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2022-03-10</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Interactions and External Dependencies</b></dt>
 * <dd><ul>
 * <li>This extension provides API support for <a href="https://github.com/KhronosGroup/GLSL/blob/main/extensions/ext/GLSL_EXT_fragment_shader_barycentric.txt">{@code GL_EXT_fragment_shader_barycentric}</a></li>
 * </ul></dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Stu Smith, AMD</li>
 * <li>Tobias Hector, AMD</li>
 * <li>Graeme Leese, Broadcom</li>
 * <li>Jan-Harald Fredriksen, Arm</li>
 * <li>Slawek Grajewski, Intel</li>
 * <li>Pat Brown, NVIDIA</li>
 * <li>Hans-Kristian Arntzen, Valve</li>
 * <li>Contributors to the VK_NV_fragment_shader_barycentric specification</li>
 * </ul></dd>
 * </dl>
 */
public final class KHRFragmentShaderBarycentric {

    /** The extension specification version. */
    public static final int VK_KHR_FRAGMENT_SHADER_BARYCENTRIC_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_KHR_FRAGMENT_SHADER_BARYCENTRIC_EXTENSION_NAME = "VK_KHR_fragment_shader_barycentric";

    /**
     * Extends {@code VkStructureType}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_FRAGMENT_SHADER_BARYCENTRIC_FEATURES_KHR STRUCTURE_TYPE_PHYSICAL_DEVICE_FRAGMENT_SHADER_BARYCENTRIC_FEATURES_KHR}</li>
     * <li>{@link #VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_FRAGMENT_SHADER_BARYCENTRIC_PROPERTIES_KHR STRUCTURE_TYPE_PHYSICAL_DEVICE_FRAGMENT_SHADER_BARYCENTRIC_PROPERTIES_KHR}</li>
     * </ul>
     */
    public static final int
        VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_FRAGMENT_SHADER_BARYCENTRIC_FEATURES_KHR   = 1000203000,
        VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_FRAGMENT_SHADER_BARYCENTRIC_PROPERTIES_KHR = 1000322000;

    private KHRFragmentShaderBarycentric() {}

}