/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val EXT_mutable_descriptor_type = "EXTMutableDescriptorType".nativeClassVK("EXT_mutable_descriptor_type", type = "device", postfix = "EXT") {
    documentation =
        """
        This extension allows applications to reduce descriptor memory footprint by allowing a descriptor to be able to mutate to a given list of descriptor types depending on which descriptor types are written into, or copied into a descriptor set.

        The main use case this extension intends to address is descriptor indexing with #DESCRIPTOR_BINDING_VARIABLE_DESCRIPTOR_COUNT_BIT where the descriptor types are completely generic, as this means applications can allocate one large descriptor set, rather than having one large descriptor set per descriptor type, which significantly bloats descriptor memory usage and causes performance issues.

        This extension also adds a mechanism to declare that a descriptor pool, and therefore the descriptor sets that are allocated from it, reside only in host memory; as such these descriptors can only be updated/copied, but not bound.

        These features together allow much more efficient emulation of the raw D3D12 binding model. This extension is primarily intended to be useful for API layering efforts.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_EXT_mutable_descriptor_type}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>495</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd>{@link KHRMaintenance3 VK_KHR_maintenance3}</dd>

            <dt><b>Special Use</b></dt>
            <dd><ul>
                <li><a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#extendingvulkan-compatibility-specialuse">D3D support</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Joshua Ashton <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_mutable_descriptor_type]%20@Joshua-Ashton%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_mutable_descriptor_type%20extension*">Joshua-Ashton</a></li>
                <li>Hans-Kristian Arntzen <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_mutable_descriptor_type]%20@HansKristian-Work%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_mutable_descriptor_type%20extension*">HansKristian-Work</a></li>
            </ul></dd>

            <dt><b>Extension Proposal</b></dt>
            <dd><a href="https://github.com/KhronosGroup/Vulkan-Docs/tree/main/proposals/VK_EXT_mutable_descriptor_type.adoc">VK_EXT_mutable_descriptor_type</a></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2022-08-22</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Joshua Ashton, Valve</li>
                <li>Hans-Kristian Arntzen, Valve</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "EXT_MUTABLE_DESCRIPTOR_TYPE_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "EXT_MUTABLE_DESCRIPTOR_TYPE_EXTENSION_NAME".."VK_EXT_mutable_descriptor_type"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_MUTABLE_DESCRIPTOR_TYPE_FEATURES_EXT".."1000351000",
        "STRUCTURE_TYPE_MUTABLE_DESCRIPTOR_TYPE_CREATE_INFO_EXT".."1000351002"
    )

    EnumConstant(
        "Extends {@code VkDescriptorType}.",

        "DESCRIPTOR_TYPE_MUTABLE_EXT".."1000351000"
    )

    EnumConstant(
        "Extends {@code VkDescriptorPoolCreateFlagBits}.",

        "DESCRIPTOR_POOL_CREATE_HOST_ONLY_BIT_EXT".enum(0x00000004)
    )

    EnumConstant(
        "Extends {@code VkDescriptorSetLayoutCreateFlagBits}.",

        "DESCRIPTOR_SET_LAYOUT_CREATE_HOST_ONLY_POOL_BIT_EXT".enum(0x00000004)
    )
}