/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val KHR_present_id = "KHRPresentId".nativeClassVK("KHR_present_id", type = "device", postfix = "KHR") {
    documentation =
        """
        This device extension allows an application that uses the {@link KHRSwapchain VK_KHR_swapchain} extension to provide an identifier for present operations on a swapchain. An application <b>can</b> use this to reference specific present operations in other extensions.

        

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_KHR_present_id}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>295</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd>{@link KHRSwapchain VK_KHR_swapchain} and {@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Keith Packard <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_present_id]%20@keithp%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_present_id%20extension*">keithp</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2019-05-15</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Keith Packard, Valve</li>
                <li>Ian Elliott, Google</li>
                <li>Alon Or-bach, Samsung</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "KHR_PRESENT_ID_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "KHR_PRESENT_ID_EXTENSION_NAME".."VK_KHR_present_id"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PRESENT_ID_KHR".."1000294000",
        "STRUCTURE_TYPE_PHYSICAL_DEVICE_PRESENT_ID_FEATURES_KHR".."1000294001"
    )
}