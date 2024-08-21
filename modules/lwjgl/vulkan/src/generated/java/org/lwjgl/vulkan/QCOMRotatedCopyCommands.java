/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

/**
 * This extension extends adds an optional rotation transform to copy commands {@link KHRCopyCommands2#vkCmdBlitImage2KHR CmdBlitImage2KHR}, {@link KHRCopyCommands2#vkCmdCopyImageToBuffer2KHR CmdCopyImageToBuffer2KHR} and {@link KHRCopyCommands2#vkCmdCopyBufferToImage2KHR CmdCopyBufferToImage2KHR}. When copying between two resources, where one resource contains rotated content and the other does not, a rotated copy may be desired. This extension may be used in combination with VK_QCOM_render_pass_transform which adds rotated render passes.
 * 
 * <p>This extension adds an extension structure to the following commands: vkCmdBlitImage2KHR, vkCmdCopyImageToBuffer2KHR and vkCmdCopyBufferToImage2KHR</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_QCOM_rotated_copy_commands}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>334</dd>
 * <dt><b>Revision</b></dt>
 * <dd>2</dd>
 * <dt><b>Extension and Version Dependencies</b></dt>
 * <dd>{@link KHRCopyCommands2 VK_KHR_copy_commands2} or <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.3">Version 1.3</a></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Matthew Netsch <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_QCOM_rotated_copy_commands]%20@mnetsch%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_QCOM_rotated_copy_commands%20extension*">mnetsch</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2023-12-13</dd>
 * <dt><b>Interactions and External Dependencies</b></dt>
 * <dd><ul>
 * <li>This extension interacts with {@link KHRSwapchain VK_KHR_swapchain}</li>
 * <li>This extension interacts with {@link KHRSurface VK_KHR_surface}</li>
 * </ul></dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Jeff Leger, Qualcomm Technologies, Inc.</li>
 * <li>Matthew Netsch, Qualcomm Technologies, Inc.</li>
 * </ul></dd>
 * </dl>
 */
public final class QCOMRotatedCopyCommands {

    /** The extension specification version. */
    public static final int VK_QCOM_ROTATED_COPY_COMMANDS_SPEC_VERSION = 2;

    /** The extension name. */
    public static final String VK_QCOM_ROTATED_COPY_COMMANDS_EXTENSION_NAME = "VK_QCOM_rotated_copy_commands";

    /** Extends {@code VkStructureType}. */
    public static final int VK_STRUCTURE_TYPE_COPY_COMMAND_TRANSFORM_INFO_QCOM = 1000333000;

    private QCOMRotatedCopyCommands() {}

}