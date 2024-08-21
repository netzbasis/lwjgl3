/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension adds support for the following SPIR-V extension in Vulkan:
 * 
 * <ul>
 * <li>{@code SPV_KHR_storage_buffer_storage_class}</li>
 * </ul>
 * 
 * <p>This extension provides a new SPIR-V {@code StorageBuffer} storage class. A {@code Block}-decorated object in this class is equivalent to a {@code BufferBlock}-decorated object in the {@code Uniform} storage class.</p>
 * 
 * <h5>Promotion to Vulkan 1.1</h5>
 * 
 * <p>All functionality in this extension is included in core Vulkan 1.1.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_KHR_storage_buffer_storage_class}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>132</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>SPIR-V Dependencies</b></dt>
 * <dd><ul>
 * <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/KHR/SPV_KHR_storage_buffer_storage_class.html">SPV_KHR_storage_buffer_storage_class</a></li>
 * </ul></dd>
 * <dt><b>Deprecation State</b></dt>
 * <dd><ul>
 * <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.1-promotions">Vulkan 1.1</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Alexander Galazin <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_storage_buffer_storage_class]%20@alegal-arm%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_storage_buffer_storage_class%20extension*">alegal-arm</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2017-09-05</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Alexander Galazin, ARM</li>
 * <li>David Neto, Google</li>
 * </ul></dd>
 * </dl>
 */
public final class KHRStorageBufferStorageClass {

    /** The extension specification version. */
    public static final int VK_KHR_STORAGE_BUFFER_STORAGE_CLASS_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_KHR_STORAGE_BUFFER_STORAGE_CLASS_EXTENSION_NAME = "VK_KHR_storage_buffer_storage_class";

    private KHRStorageBufferStorageClass() {}

}