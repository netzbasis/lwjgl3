/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension adds support for the {@code ShaderViewportIndexLayerEXT} capability from the {@code SPV_EXT_shader_viewport_index_layer} extension in Vulkan.
 * 
 * <p>This extension allows variables decorated with the {@code Layer} and {@code ViewportIndex} built-ins to be exported from vertex or tessellation shaders, using the {@code ShaderViewportIndexLayerEXT} capability.</p>
 * 
 * <p>When using GLSL source-based shading languages, the {@code gl_ViewportIndex} and {@code gl_Layer} built-in variables map to the SPIR-V {@code ViewportIndex} and {@code Layer} built-in decorations, respectively. Behavior of these variables is extended as described in the {@code GL_ARB_shader_viewport_layer_array} (or the precursor {@code GL_AMD_vertex_shader_layer}, {@code GL_AMD_vertex_shader_viewport_index}, and {@code GL_NV_viewport_array2} extensions).</p>
 * 
 * <div style="margin-left: 26px; border-left: 1px solid gray; padding-left: 14px;"><h5>Note</h5>
 * 
 * <p>The {@code ShaderViewportIndexLayerEXT} capability is equivalent to the {@code ShaderViewportIndexLayerNV} capability added by {@link NVViewportArray2 VK_NV_viewport_array2}.</p>
 * </div>
 * 
 * <h5>Promotion to Vulkan 1.2</h5>
 * 
 * <p>All functionality in this extension is included in core Vulkan 1.2.</p>
 * 
 * <p>The single {@code ShaderViewportIndexLayerEXT} capability from the {@code SPV_EXT_shader_viewport_index_layer} extension is replaced by the <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#spirvenv-capabilities-table-ShaderViewportIndex">{@code ShaderViewportIndex}</a> and <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#spirvenv-capabilities-table-ShaderLayer">{@code ShaderLayer}</a> capabilities from SPIR-V 1.5 which are enabled by the <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#features-shaderOutputViewportIndex">{@code shaderOutputViewportIndex}</a> and <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#features-shaderOutputLayer">{@code shaderOutputLayer}</a> features, respectively. Additionally, if Vulkan 1.2 is supported but this extension is not, these capabilities are optional.</p>
 * 
 * <p>Enabling both features is equivalent to enabling the {@code VK_EXT_shader_viewport_index_layer} extension.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_EXT_shader_viewport_index_layer}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>163</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>SPIR-V Dependencies</b></dt>
 * <dd><ul>
 * <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/EXT/SPV_EXT_shader_viewport_index_layer.html">SPV_EXT_shader_viewport_index_layer</a></li>
 * </ul></dd>
 * <dt><b>Deprecation State</b></dt>
 * <dd><ul>
 * <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.2-promotions">Vulkan 1.2</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Daniel Koch <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_shader_viewport_index_layer]%20@dgkoch%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_shader_viewport_index_layer%20extension*">dgkoch</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2017-08-08</dd>
 * <dt><b>Interactions and External Dependencies</b></dt>
 * <dd><ul>
 * <li>This extension provides API support for <a href="https://registry.khronos.org/OpenGL/extensions/ARB/ARB_shader_viewport_layer_array.txt">{@code GL_ARB_shader_viewport_layer_array}</a>, <a href="https://registry.khronos.org/OpenGL/extensions/AMD/AMD_vertex_shader_layer.txt">{@code GL_AMD_vertex_shader_layer}</a>, <a href="https://registry.khronos.org/OpenGL/extensions/AMD/AMD_vertex_shader_viewport_index.txt">{@code GL_AMD_vertex_shader_viewport_index}</a>, and <a href="https://registry.khronos.org/OpenGL/extensions/NV/NV_viewport_array2.txt">{@code GL_NV_viewport_array2}</a></li>
 * <li>This extension requires the {@code multiViewport} feature.</li>
 * <li>This extension interacts with the {@code tessellationShader} feature.</li>
 * </ul></dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Piers Daniell, NVIDIA</li>
 * <li>Jeff Bolz, NVIDIA</li>
 * <li>Jan-Harald Fredriksen, ARM</li>
 * <li>Daniel Rakos, AMD</li>
 * <li>Slawomir Grajeswki, Intel</li>
 * </ul></dd>
 * </dl>
 */
public final class EXTShaderViewportIndexLayer {

    /** The extension specification version. */
    public static final int VK_EXT_SHADER_VIEWPORT_INDEX_LAYER_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_EXT_SHADER_VIEWPORT_INDEX_LAYER_EXTENSION_NAME = "VK_EXT_shader_viewport_index_layer";

    private EXTShaderViewportIndexLayer() {}

}