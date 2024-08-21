/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension has the same goal as the OpenGL ES {@code GL_OVR_multiview} extension. Multiview is a rendering technique originally designed for VR where it is more efficient to record a single set of commands to be executed with slightly different behavior for each “{@code view}”.
 * 
 * <p>It includes a concise way to declare a render pass with multiple views, and gives implementations freedom to render the views in the most efficient way possible. This is done with a multiview configuration specified during <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#renderpass">render pass</a> creation with the {@link VkRenderPassMultiviewCreateInfo} passed into {@link VkRenderPassCreateInfo}{@code ::pNext}.</p>
 * 
 * <p>This extension enables the use of the <a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/KHR/SPV_KHR_multiview.html">{@code SPV_KHR_multiview}</a> shader extension, which adds a new {@code ViewIndex} built-in type that allows shaders to control what to do for each view. If using GLSL there is also the <a href="https://github.com/KhronosGroup/GLSL/blob/main/extensions/ext/GL_EXT_multiview.txt">{@code GL_EXT_multiview}</a> extension that introduces a {@code highp int gl_ViewIndex;} built-in variable for vertex, tessellation, geometry, and fragment shaders.</p>
 * 
 * <h5>Promotion to Vulkan 1.1</h5>
 * 
 * <p>All functionality in this extension is included in core Vulkan 1.1, with the KHR suffix omitted. The original type, enum and command names are still available as aliases of the core functionality.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_KHR_multiview}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>54</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1">Version 1.1</a></dd>
 * <dt><b>SPIR-V Dependencies</b></dt>
 * <dd><ul>
 * <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/KHR/SPV_KHR_multiview.html">SPV_KHR_multiview</a></li>
 * </ul></dd>
 * <dt><b>Deprecation State</b></dt>
 * <dd><ul>
 * <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1-promotions">Vulkan 1.1</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Jeff Bolz <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_multiview]%20@jeffbolznv%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_multiview%20extension*">jeffbolznv</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2016-10-28</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Interactions and External Dependencies</b></dt>
 * <dd><ul>
 * <li>This extension provides API support for <a href="https://github.com/KhronosGroup/GLSL/blob/main/extensions/ext/GL_EXT_multiview.txt">{@code GL_EXT_multiview}</a></li>
 * </ul></dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Jeff Bolz, NVIDIA</li>
 * </ul></dd>
 * </dl>
 */
public final class KHRMultiview {

    /** The extension specification version. */
    public static final int VK_KHR_MULTIVIEW_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_KHR_MULTIVIEW_EXTENSION_NAME = "VK_KHR_multiview";

    /**
     * Extends {@code VkStructureType}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #VK_STRUCTURE_TYPE_RENDER_PASS_MULTIVIEW_CREATE_INFO_KHR STRUCTURE_TYPE_RENDER_PASS_MULTIVIEW_CREATE_INFO_KHR}</li>
     * <li>{@link #VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_MULTIVIEW_FEATURES_KHR STRUCTURE_TYPE_PHYSICAL_DEVICE_MULTIVIEW_FEATURES_KHR}</li>
     * <li>{@link #VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_MULTIVIEW_PROPERTIES_KHR STRUCTURE_TYPE_PHYSICAL_DEVICE_MULTIVIEW_PROPERTIES_KHR}</li>
     * </ul>
     */
    public static final int
        VK_STRUCTURE_TYPE_RENDER_PASS_MULTIVIEW_CREATE_INFO_KHR    = 1000053000,
        VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_MULTIVIEW_FEATURES_KHR   = 1000053001,
        VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_MULTIVIEW_PROPERTIES_KHR = 1000053002;

    /** Extends {@code VkDependencyFlagBits}. */
    public static final int VK_DEPENDENCY_VIEW_LOCAL_BIT_KHR = 0x2;

    private KHRMultiview() {}

}