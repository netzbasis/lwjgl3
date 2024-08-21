/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension enables C-like structure layout for SPIR-V blocks. It modifies the alignment rules for uniform buffers, storage buffers and push constants, allowing non-scalar types to be aligned solely based on the size of their components, without additional requirements.
 * 
 * <h5>Promotion to Vulkan 1.2</h5>
 * 
 * <p>Functionality in this extension is included in core Vulkan 1.2, with the EXT suffix omitted. However, if Vulkan 1.2 is supported and this extension is not, the {@code scalarBlockLayout} capability is optional. The original type, enum and command names are still available as aliases of the core functionality.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_EXT_scalar_block_layout}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>222</dd>
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
 * <li>Tobias Hector <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_scalar_block_layout]%20@tobski%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_scalar_block_layout%20extension*">tobski</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2018-11-14</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Jeff Bolz</li>
 * <li>Jan-Harald Fredriksen</li>
 * <li>Graeme Leese</li>
 * <li>Faith Ekstrand</li>
 * <li>John Kessenich</li>
 * </ul></dd>
 * </dl>
 */
public final class EXTScalarBlockLayout {

    /** The extension specification version. */
    public static final int VK_EXT_SCALAR_BLOCK_LAYOUT_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_EXT_SCALAR_BLOCK_LAYOUT_EXTENSION_NAME = "VK_EXT_scalar_block_layout";

    /** Extends {@code VkStructureType}. */
    public static final int VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_SCALAR_BLOCK_LAYOUT_FEATURES_EXT = 1000221000;

    private EXTScalarBlockLayout() {}

}