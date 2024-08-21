/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension adds the device coherent and device uncached memory types. Any device accesses to device coherent memory are automatically made visible to any other device access. Device uncached memory indicates to applications that caches are disabled for a particular memory type, which guarantees device coherence.
 * 
 * <p>Device coherent and uncached memory are expected to have lower performance for general access than non-device coherent memory, but can be useful in certain scenarios; particularly so for debugging.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_AMD_device_coherent_memory}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>230</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1">Version 1.1</a></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Tobias Hector <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_AMD_device_coherent_memory]%20@tobski%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_AMD_device_coherent_memory%20extension*">tobski</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2019-02-04</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Ping Fu, AMD</li>
 * <li>Timothy Lottes, AMD</li>
 * <li>Tobias Hector, AMD</li>
 * </ul></dd>
 * </dl>
 */
public final class AMDDeviceCoherentMemory {

    /** The extension specification version. */
    public static final int VK_AMD_DEVICE_COHERENT_MEMORY_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_AMD_DEVICE_COHERENT_MEMORY_EXTENSION_NAME = "VK_AMD_device_coherent_memory";

    /**
     * Extends {@code VkMemoryPropertyFlagBits}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #VK_MEMORY_PROPERTY_DEVICE_COHERENT_BIT_AMD MEMORY_PROPERTY_DEVICE_COHERENT_BIT_AMD}</li>
     * <li>{@link #VK_MEMORY_PROPERTY_DEVICE_UNCACHED_BIT_AMD MEMORY_PROPERTY_DEVICE_UNCACHED_BIT_AMD}</li>
     * </ul>
     */
    public static final int
        VK_MEMORY_PROPERTY_DEVICE_COHERENT_BIT_AMD = 0x40,
        VK_MEMORY_PROPERTY_DEVICE_UNCACHED_BIT_AMD = 0x80;

    /** Extends {@code VkStructureType}. */
    public static final int VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_COHERENT_MEMORY_FEATURES_AMD = 1000229000;

    private AMDDeviceCoherentMemory() {}

}