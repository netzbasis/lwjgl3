/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension allows creating 3D views of 3D images such that the views contain a subset of the slices in the image, using a Z offset and range, for the purpose of using the views as storage image descriptors. This matches functionality in D3D12 and is primarily intended to support D3D12 emulation.
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_EXT_image_sliced_view_of_3d}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>419</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRMaintenance1 VK_KHR_maintenance1} and {@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1">Version 1.1</a></dd>
 * <dt><b>Special Use</b></dt>
 * <dd><ul>
 * <li><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#extendingvulkan-compatibility-specialuse">D3D support</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Mike Blumenkrantz <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_image_sliced_view_of_3d]%20@zmike%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_image_sliced_view_of_3d%20extension*">zmike</a></li>
 * </ul></dd>
 * <dt><b>Extension Proposal</b></dt>
 * <dd><a href="https://github.com/KhronosGroup/Vulkan-Docs/tree/main/proposals/VK_EXT_image_sliced_view_of_3d.adoc">VK_EXT_image_sliced_view_of_3d</a></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2023-01-24</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Mike Blumenkrantz, Valve</li>
 * <li>Hans-Kristian Arntzen, Valve</li>
 * <li>Ricardo Garcia, Igalia</li>
 * <li>Shahbaz Youssefi, Google</li>
 * <li>Piers Daniell, NVIDIA</li>
 * </ul></dd>
 * </dl>
 */
public final class EXTImageSlicedViewOf3d {

    /** The extension specification version. */
    public static final int VK_EXT_IMAGE_SLICED_VIEW_OF_3D_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_EXT_IMAGE_SLICED_VIEW_OF_3D_EXTENSION_NAME = "VK_EXT_image_sliced_view_of_3d";

    /**
     * Extends {@code VkStructureType}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_IMAGE_SLICED_VIEW_OF_3D_FEATURES_EXT STRUCTURE_TYPE_PHYSICAL_DEVICE_IMAGE_SLICED_VIEW_OF_3D_FEATURES_EXT}</li>
     * <li>{@link #VK_STRUCTURE_TYPE_IMAGE_VIEW_SLICED_CREATE_INFO_EXT STRUCTURE_TYPE_IMAGE_VIEW_SLICED_CREATE_INFO_EXT}</li>
     * </ul>
     */
    public static final int
        VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_IMAGE_SLICED_VIEW_OF_3D_FEATURES_EXT = 1000418000,
        VK_STRUCTURE_TYPE_IMAGE_VIEW_SLICED_CREATE_INFO_EXT                    = 1000418001;

    /** VK_REMAINING_3D_SLICES_EXT */
    public static final int VK_REMAINING_3D_SLICES_EXT = (~0);

    private EXTImageSlicedViewOf3d() {}

}