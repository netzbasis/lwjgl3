/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension allows the use of a null constant initializer on shader Workgroup memory variables, allowing implementations to expose any special hardware or instructions they may have. Zero initialization is commonly used by applications running untrusted content (e.g. web browsers) as way of defeating memory-scraping attacks.
 * 
 * <h5>Promotion to Vulkan 1.3</h5>
 * 
 * <p>Functionality in this extension is included in core Vulkan 1.3, with the KHR suffix omitted. The original type, enum and command names are still available as aliases of the core functionality.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_KHR_zero_initialize_workgroup_memory}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>326</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1">Version 1.1</a></dd>
 * <dt><b>Deprecation State</b></dt>
 * <dd><ul>
 * <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.3-promotions">Vulkan 1.3</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Alan Baker <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_zero_initialize_workgroup_memory]%20@alan-baker%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_zero_initialize_workgroup_memory%20extension*">alan-baker</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2020-11-18</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Alan Baker, Google</li>
 * <li>Jeff Bolz, Nvidia</li>
 * <li>Faith Ekstrand, Intel</li>
 * </ul></dd>
 * </dl>
 */
public final class KHRZeroInitializeWorkgroupMemory {

    /** The extension specification version. */
    public static final int VK_KHR_ZERO_INITIALIZE_WORKGROUP_MEMORY_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_KHR_ZERO_INITIALIZE_WORKGROUP_MEMORY_EXTENSION_NAME = "VK_KHR_zero_initialize_workgroup_memory";

    /** Extends {@code VkStructureType}. */
    public static final int VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_ZERO_INITIALIZE_WORKGROUP_MEMORY_FEATURES_KHR = 1000325000;

    private KHRZeroInitializeWorkgroupMemory() {}

}