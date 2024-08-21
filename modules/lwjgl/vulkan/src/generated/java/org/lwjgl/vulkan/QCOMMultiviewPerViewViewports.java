/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * Certain use cases for multiview have a need for specifying a separate viewport and scissor for each view, without using shader-based viewport indexing as introduced with {@link EXTShaderViewportIndexLayer VK_EXT_shader_viewport_index_layer}.
 * 
 * <p>This extension adds a new way to control ViewportIndex with multiview. When the <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#features-multiview-per-view-viewports">{@code multiviewPerViewViewports}</a> feature is enabled and if the last pre-rasterization shader entry point’s interface does not use the {@code ViewportIndex} built-in decoration, then each view of a multiview render pass instance will use a viewport and scissor index equal to the {@code ViewIndex}.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_QCOM_multiview_per_view_viewports}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>489</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1">Version 1.1</a></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Matthew Netsch <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_QCOM_multiview_per_view_viewports]%20@mnetsch%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_QCOM_multiview_per_view_viewports%20extension*">mnetsch</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2022-11-22</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Interactions and External Dependencies</b></dt>
 * <dd><ul>
 * <li>This extension interacts with {@link KHRDynamicRendering VK_KHR_dynamic_rendering}</li>
 * <li>This extension interacts with {@link EXTExtendedDynamicState VK_EXT_extended_dynamic_state}</li>
 * </ul></dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Jeff Leger, Qualcomm</li>
 * <li>Jonathan Tinkham, Qualcomm</li>
 * <li>Jonathan Wicks, Qualcomm</li>
 * </ul></dd>
 * </dl>
 */
public final class QCOMMultiviewPerViewViewports {

    /** The extension specification version. */
    public static final int VK_QCOM_MULTIVIEW_PER_VIEW_VIEWPORTS_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_QCOM_MULTIVIEW_PER_VIEW_VIEWPORTS_EXTENSION_NAME = "VK_QCOM_multiview_per_view_viewports";

    /** Extends {@code VkStructureType}. */
    public static final int VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_MULTIVIEW_PER_VIEW_VIEWPORTS_FEATURES_QCOM = 1000488000;

    private QCOMMultiviewPerViewViewports() {}

}