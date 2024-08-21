/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension defines the {@link #VK_FORMAT_A4R4G4B4_UNORM_PACK16_EXT FORMAT_A4R4G4B4_UNORM_PACK16_EXT} and {@link #VK_FORMAT_A4B4G4R4_UNORM_PACK16_EXT FORMAT_A4B4G4R4_UNORM_PACK16_EXT} formats which are defined in other current graphics APIs.
 * 
 * <p>This extension may be useful for building translation layers for those APIs or for porting applications that use these formats without having to resort to swizzles.</p>
 * 
 * <p>When VK_EXT_custom_border_color is used, these formats are not subject to the same restrictions for border color without format as with VK_FORMAT_B4G4R4A4_UNORM_PACK16.</p>
 * 
 * <h5>Promotion to Vulkan 1.3</h5>
 * 
 * <p>This extension has been partially promoted. The format enumerants introduced by the extension are included in core Vulkan 1.3, with the EXT suffix omitted. However, runtime support for these formats is optional in core Vulkan 1.3, while if this extension is supported, runtime support is mandatory. The feature structure is not promoted. The original enum names are still available as aliases of the core functionality.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_EXT_4444_formats}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>341</dd>
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
 * <li>Joshua Ashton <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_4444_formats]%20@Joshua-Ashton%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_4444_formats%20extension*">Joshua-Ashton</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2020-07-28</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Joshua Ashton, Valve</li>
 * <li>Faith Ekstrand, Intel</li>
 * </ul></dd>
 * </dl>
 */
public final class EXT4444Formats {

    /** The extension specification version. */
    public static final int VK_EXT_4444_FORMATS_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_EXT_4444_FORMATS_EXTENSION_NAME = "VK_EXT_4444_formats";

    /** Extends {@code VkStructureType}. */
    public static final int VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_4444_FORMATS_FEATURES_EXT = 1000340000;

    /**
     * Extends {@code VkFormat}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #VK_FORMAT_A4R4G4B4_UNORM_PACK16_EXT FORMAT_A4R4G4B4_UNORM_PACK16_EXT}</li>
     * <li>{@link #VK_FORMAT_A4B4G4R4_UNORM_PACK16_EXT FORMAT_A4B4G4R4_UNORM_PACK16_EXT}</li>
     * </ul>
     */
    public static final int
        VK_FORMAT_A4R4G4B4_UNORM_PACK16_EXT = 1000340000,
        VK_FORMAT_A4B4G4R4_UNORM_PACK16_EXT = 1000340001;

    private EXT4444Formats() {}

}