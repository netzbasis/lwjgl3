/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val KHR_vulkan_memory_model = "KHRVulkanMemoryModel".nativeClassVK("KHR_vulkan_memory_model", type = "device", postfix = "KHR") {
    documentation =
        """
        The {@link KHRVulkanMemoryModel VK_KHR_vulkan_memory_model} extension allows use of the features guarded by the {@code VulkanMemoryModel}, {@code VulkanMemoryModelDeviceScope}, and {@code VulkanMemoryModelAvailabilityVisibilityChains} capabilities in shader modules. The <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#memory-model">Vulkan Memory Model</a> formally defines how to synchronize memory accesses to the same memory locations performed by multiple shader invocations.

        <div style="margin-left: 26px; border-left: 1px solid gray; padding-left: 14px;"><h5>Note</h5>
        Version 3 of the spec added a member ({@code vulkanMemoryModelAvailabilityVisibilityChains}) to ##VkPhysicalDeviceVulkanMemoryModelFeaturesKHR, which is an incompatible change from version 2.
        </div>

        <h5>Promotion to Vulkan 1.2</h5>
        All functionality in this extension is included in core Vulkan 1.2, with the KHR suffix omitted. However, if Vulkan 1.2 is supported and this extension is not, the {@code vulkanMemoryModel} capability is optional. The original type, enum and command names are still available as aliases of the core functionality.

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_KHR_vulkan_memory_model}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>212</dd>

            <dt><b>Revision</b></dt>
            <dd>3</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd>{@link KHRGetPhysicalDeviceProperties2 VK_KHR_get_physical_device_properties2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.1">Version 1.1</a></dd>

            <dt><b>SPIR-V Dependencies</b></dt>
            <dd><ul>
                <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/KHR/SPV_KHR_vulkan_memory_model.html">SPV_KHR_vulkan_memory_model</a></li>
            </ul></dd>

            <dt><b>Deprecation State</b></dt>
            <dd><ul>
                <li><em>Promoted</em> to <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html\#versions-1.2-promotions">Vulkan 1.2</a></li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Jeff Bolz <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_KHR_vulkan_memory_model]%20@jeffbolznv%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_KHR_vulkan_memory_model%20extension*">jeffbolznv</a></li>
            </ul></dd>
        </dl>

        <h5>Other Extension Metadata</h5>
        <dl>
            <dt><b>Last Modified Date</b></dt>
            <dd>2018-12-10</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Jeff Bolz, NVIDIA</li>
                <li>Alan Baker, Google</li>
                <li>Tobias Hector, AMD</li>
                <li>David Neto, Google</li>
                <li>Robert Simpson, Qualcomm Technologies, Inc.</li>
                <li>Brian Sumner, AMD</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "KHR_VULKAN_MEMORY_MODEL_SPEC_VERSION".."3"
    )

    StringConstant(
        "The extension name.",

        "KHR_VULKAN_MEMORY_MODEL_EXTENSION_NAME".."VK_KHR_vulkan_memory_model"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_PHYSICAL_DEVICE_VULKAN_MEMORY_MODEL_FEATURES_KHR".."1000211000"
    )
}