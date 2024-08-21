/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * The {@code VK_KHR_relaxed_block_layout} extension allows implementations to indicate they can support more variation in block {@code Offset} decorations. For example, placing a vector of three floats at an offset of <code>16×N + 4</code>.
 * 
 * <p>See <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#interfaces-resources-layout">Offset and Stride Assignment</a> for details.</p>
 * 
 * <h5>Promotion to Vulkan 1.1</h5>
 * 
 * <p>All functionality in this extension is included in core Vulkan 1.1, with the KHR suffix omitted. The original type, enum and command names are still available as aliases of the core functionality.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_KHR_relaxed_block_layout}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>145</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>Deprecation State</b></dt>
 * <dd><ul>
 * <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1-promotions">Vulkan 1.1</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>John Kessenich <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_relaxed_block_layout]%20@johnkslang%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_relaxed_block_layout%20extension*">johnkslang</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2017-03-26</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>John Kessenich, Google</li>
 * </ul></dd>
 * </dl>
 */
public final class KHRRelaxedBlockLayout {

    /** The extension specification version. */
    public static final int VK_KHR_RELAXED_BLOCK_LAYOUT_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_KHR_RELAXED_BLOCK_LAYOUT_EXTENSION_NAME = "VK_KHR_relaxed_block_layout";

    private KHRRelaxedBlockLayout() {}

}