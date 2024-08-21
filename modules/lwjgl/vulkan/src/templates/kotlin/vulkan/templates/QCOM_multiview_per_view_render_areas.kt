/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val QCOM_multiview_per_view_render_areas = "QCOMMultiviewPerViewRenderAreas".nativeClassVK("QCOM_multiview_per_view_render_areas", type = "device", postfix = "QCOM") {
    documentation =
        """
        Certain use cases (e.g., side-by-side VR rendering) use multiview and render to distinct regions of the framebuffer for each view. On some implementations, there may be a performance benefit for providing per-view render areas to the implementation. Such per-view render areas can be used by the implementation to reduce the pixels that are affected by attachment load, store, and multisample resolve operations.

        The extension enables a multiview render pass instance to define per-view render areas. For each view of a multiview render pass instance, only those pixels in the per-view render area are affected by load, store and resolve operations.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_QCOM_multiview_per_view_render_areas}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>511</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Matthew Netsch <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_QCOM_multiview_per_view_render_areas]%20@mnetsch%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_QCOM_multiview_per_view_render_areas%20extension*">mnetsch</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2023-01-10</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Interactions and External Dependencies</b></dt>
            <dd><ul>
                <li>This extension interacts with {@link KHRDynamicRendering VK_KHR_dynamic_rendering}</li>
                <li>This extension interacts with {@link QCOMRenderPassTransform VK_QCOM_render_pass_transform}</li>
            </ul></dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Jeff Leger, Qualcomm</li>
                <li>Jonathan Tinkham, Qualcomm</li>
                <li>Jonathan Wicks, Qualcomm</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "QCOM_MULTIVIEW_PER_VIEW_RENDER_AREAS_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "QCOM_MULTIVIEW_PER_VIEW_RENDER_AREAS_EXTENSION_NAME".."VK_QCOM_multiview_per_view_render_areas"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_MULTIVIEW_PER_VIEW_RENDER_AREAS_FEATURES_QCOM".."1000510000",
        "STRUCTURE_TYPE_MULTIVIEW_PER_VIEW_RENDER_AREAS_RENDER_PASS_BEGIN_INFO_QCOM".."1000510001"
    )
}