/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension enables tighter array and struct packing to be used with uniform buffers.
 * 
 * <p>It modifies the alignment rules for uniform buffers, allowing for tighter packing of arrays and structures. This allows, for example, the std430 layout, as defined in <a href="https://registry.khronos.org/OpenGL/specs/gl/GLSLangSpec.4.60.pdf">GLSL</a> to be supported in uniform buffers.</p>
 * 
 * <h5>Promotion to Vulkan 1.2</h5>
 * 
 * <p>All functionality in this extension is included in core Vulkan 1.2, with the KHR suffix omitted. The original type, enum and command names are still available as aliases of the core functionality.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_KHR_uniform_buffer_standard_layout}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>254</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1">Version 1.1</a></dd>
 * <dt><b>Deprecation State</b></dt>
 * <dd><ul>
 * <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.2-promotions">Vulkan 1.2</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Graeme Leese <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_uniform_buffer_standard_layout]%20@gnl21%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_uniform_buffer_standard_layout%20extension*">gnl21</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2019-01-25</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Graeme Leese, Broadcom</li>
 * <li>Jeff Bolz, NVIDIA</li>
 * <li>Tobias Hector, AMD</li>
 * <li>Faith Ekstrand, Intel</li>
 * <li>Neil Henning, AMD</li>
 * </ul></dd>
 * </dl>
 */
public final class KHRUniformBufferStandardLayout {

    /** The extension specification version. */
    public static final int VK_KHR_UNIFORM_BUFFER_STANDARD_LAYOUT_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_KHR_UNIFORM_BUFFER_STANDARD_LAYOUT_EXTENSION_NAME = "VK_KHR_uniform_buffer_standard_layout";

    /** Extends {@code VkStructureType}. */
    public static final int VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_UNIFORM_BUFFER_STANDARD_LAYOUT_FEATURES_KHR = 1000253000;

    private KHRUniformBufferStandardLayout() {}

}