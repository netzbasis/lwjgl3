/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val EXT_image_robustness = "EXTImageRobustness".nativeClassVK("EXT_image_robustness", type = "device", postfix = "EXT") {
    documentation =
        """
        This extension adds stricter requirements for how out of bounds reads from images are handled. Rather than returning undefined values, most out of bounds reads return R, G, and B values of zero and alpha values of either zero or one. Components not present in the image format may be set to zero or to values based on the format as described in <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#textures-conversion-to-rgba">Conversion to RGBA</a>.

        <h5>Promotion to Vulkan 1.3</h5>
        Functionality in this extension is included in core Vulkan 1.3, with the EXT suffix omitted. The original type, enum and command names are still available as aliases of the core functionality.

        <h5>Examples</h5>
        None.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_EXT_image_robustness}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>336</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>Deprecation State</b></dt>
            <dd><ul>
                <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.3-promotions">Vulkan 1.3</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Graeme Leese <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_image_robustness]%20@gnl21%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_image_robustness%20extension*">gnl21</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2020-04-27</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Graeme Leese, Broadcom</li>
                <li>Jan-Harald Fredriksen, ARM</li>
                <li>Jeff Bolz, NVIDIA</li>
                <li>Spencer Fricke, Samsung</li>
                <li>Courtney Goeltzenleuchter, Google</li>
                <li>Slawomir Cygan, Intel</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "EXT_IMAGE_ROBUSTNESS_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "EXT_IMAGE_ROBUSTNESS_EXTENSION_NAME".."VK_EXT_image_robustness"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_IMAGE_ROBUSTNESS_FEATURES_EXT".."1000335000"
    )
}