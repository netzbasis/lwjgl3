/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val LUNARG_direct_driver_loading = "LUNARGDirectDriverLoading".nativeClassVK("LUNARG_direct_driver_loading", type = "instance", postfix = "LUNARG") {
    documentation =
        """
        This extension provides a mechanism for applications to add drivers to the implementation. This allows drivers to be included with an application without requiring installation and is capable of being used in any execution environment, such as a process running with elevated privileges.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_LUNARG_direct_driver_loading}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Instance extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>460</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Charles Giessen <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_LUNARG_direct_driver_loading]%20@charles-lunarg%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_LUNARG_direct_driver_loading%20extension*">charles-lunarg</a></li>
            </ul></dd>

            <dt><b>Extension Proposal</b></dt>
            <dd><a href="https://github.com/KhronosGroup/Vulkan-Docs/tree/main/proposals/VK_LUNARG_direct_driver_loading.adoc">VK_LUNARG_direct_driver_loading</a></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2022-11-29</dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Charles Giessen, LunarG</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "LUNARG_DIRECT_DRIVER_LOADING_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "LUNARG_DIRECT_DRIVER_LOADING_EXTENSION_NAME".."VK_LUNARG_direct_driver_loading"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_DIRECT_DRIVER_LOADING_INFO_LUNARG".."1000459000",
        "STRUCTURE_TYPE_DIRECT_DRIVER_LOADING_LIST_LUNARG".."1000459001"
    )

    EnumConstant(
        """
        VkDirectDriverLoadingModeLUNARG - Specify loader behavior of added drivers

        <h5>Description</h5>
        <ul>
            <li>#DIRECT_DRIVER_LOADING_MODE_EXCLUSIVE_LUNARG specifies that the provided drivers are used instead of the system-loaded drivers.</li>
            <li>#DIRECT_DRIVER_LOADING_MODE_INCLUSIVE_LUNARG specifies that the provided drivers are used in addition to the system-loaded drivers.</li>
        </ul>

        <h5>See Also</h5>
        ##VkDirectDriverLoadingListLUNARG
        """,

        "DIRECT_DRIVER_LOADING_MODE_EXCLUSIVE_LUNARG".."0",
        "DIRECT_DRIVER_LOADING_MODE_INCLUSIVE_LUNARG".."1"
    )
}