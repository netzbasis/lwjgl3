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
 * <li>{@code SPV_KHR_shader_ballot}</li>
 * </ul>
 * 
 * <p>This extension provides the ability for a group of invocations, which execute in parallel, to do limited forms of cross-invocation communication via a group broadcast of an invocation value, or broadcast of a bit array representing a predicate value from each invocation in the group.</p>
 * 
 * <p>This extension provides access to a number of additional built-in shader variables in Vulkan:</p>
 * 
 * <ul>
 * <li>{@code SubgroupEqMaskKHR}, containing the subgroup mask of the current subgroup invocation,</li>
 * <li>{@code SubgroupGeMaskKHR}, containing the subgroup mask of the invocations greater than or equal to the current invocation,</li>
 * <li>{@code SubgroupGtMaskKHR}, containing the subgroup mask of the invocations greater than the current invocation,</li>
 * <li>{@code SubgroupLeMaskKHR}, containing the subgroup mask of the invocations less than or equal to the current invocation,</li>
 * <li>{@code SubgroupLtMaskKHR}, containing the subgroup mask of the invocations less than the current invocation,</li>
 * <li>{@code SubgroupLocalInvocationId}, containing the index of an invocation within a subgroup, and</li>
 * <li>{@code SubgroupSize}, containing the maximum number of invocations in a subgroup.</li>
 * </ul>
 * 
 * <p>Additionally, this extension provides access to the new SPIR-V instructions:</p>
 * 
 * <ul>
 * <li>{@code OpSubgroupBallotKHR},</li>
 * <li>{@code OpSubgroupFirstInvocationKHR}, and</li>
 * <li>{@code OpSubgroupReadInvocationKHR},</li>
 * </ul>
 * 
 * <p>When using GLSL source-based shader languages, the following variables and shader functions from GL_ARB_shader_ballot can map to these SPIR-V built-in decorations and instructions:</p>
 * 
 * <ul>
 * <li>{@code in uint64_t gl_SubGroupEqMaskARB;} → {@code SubgroupEqMaskKHR},</li>
 * <li>{@code in uint64_t gl_SubGroupGeMaskARB;} → {@code SubgroupGeMaskKHR},</li>
 * <li>{@code in uint64_t gl_SubGroupGtMaskARB;} → {@code SubgroupGtMaskKHR},</li>
 * <li>{@code in uint64_t gl_SubGroupLeMaskARB;} → {@code SubgroupLeMaskKHR},</li>
 * <li>{@code in uint64_t gl_SubGroupLtMaskARB;} → {@code SubgroupLtMaskKHR},</li>
 * <li>{@code in uint gl_SubGroupInvocationARB;} → {@code SubgroupLocalInvocationId},</li>
 * <li>{@code uniform uint gl_SubGroupSizeARB;} → {@code SubgroupSize},</li>
 * <li>{@code ballotARB}() → {@code OpSubgroupBallotKHR},</li>
 * <li>{@code readFirstInvocationARB}() → {@code OpSubgroupFirstInvocationKHR}, and</li>
 * <li>{@code readInvocationARB}() → {@code OpSubgroupReadInvocationKHR}.</li>
 * </ul>
 * 
 * <h5>Deprecated by Vulkan 1.2</h5>
 * 
 * <p>Most of the functionality in this extension is superseded by the core Vulkan 1.1 <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#VkPhysicalDeviceSubgroupProperties">subgroup operations</a>. However, Vulkan 1.1 required the {@code OpGroupNonUniformBroadcast} “{@code Id}” to be constant. This restriction was removed in Vulkan 1.2 with the addition of the <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#features-subgroupBroadcastDynamicId">{@code subgroupBroadcastDynamicId}</a> feature.</p>
 * 
 * <dl>
 * <dt><b>Name String</b></dt>
 * <dd>{@code VK_EXT_shader_subgroup_ballot}</dd>
 * <dt><b>Extension Type</b></dt>
 * <dd>Device extension</dd>
 * <dt><b>Registered Extension Number</b></dt>
 * <dd>65</dd>
 * <dt><b>Revision</b></dt>
 * <dd>1</dd>
 * <dt><b>SPIR-V Dependencies</b></dt>
 * <dd><ul>
 * <li><a href="https://htmlpreview.github.io/?https://github.com/KhronosGroup/SPIRV-Registry/blob/master/extensions/KHR/SPV_KHR_shader_ballot.html">SPV_KHR_shader_ballot</a></li>
 * </ul></dd>
 * <dt><b>Deprecation State</b></dt>
 * <dd><ul>
 * <li><em>Deprecated</em> by <a href="https://registry.khronos.org/vulkan/specs/1.3-extensions/html/vkspec.html#versions-1.2-new-features">Vulkan 1.2</a></li>
 * </ul></dd>
 * <dt><b>Contact</b></dt>
 * <dd><ul>
 * <li>Daniel Koch <a href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?body=[VK_EXT_shader_subgroup_ballot]%20@dgkoch%250A*Here%20describe%20the%20issue%20or%20question%20you%20have%20about%20the%20VK_EXT_shader_subgroup_ballot%20extension*">dgkoch</a></li>
 * </ul></dd>
 * </dl>
 * 
 * <h5>Other Extension Metadata</h5>
 * 
 * <dl>
 * <dt><b>Last Modified Date</b></dt>
 * <dd>2016-11-28</dd>
 * <dt><b>IP Status</b></dt>
 * <dd>No known IP claims.</dd>
 * <dt><b>Interactions and External Dependencies</b></dt>
 * <dd><ul>
 * <li>This extension provides API support for <a href="https://registry.khronos.org/OpenGL/extensions/ARB/ARB_shader_ballot.txt">{@code GL_ARB_shader_ballot}</a></li>
 * </ul></dd>
 * <dt><b>Contributors</b></dt>
 * <dd><ul>
 * <li>Jeff Bolz, NVIDIA</li>
 * <li>Neil Henning, Codeplay</li>
 * <li>Daniel Koch, NVIDIA Corporation</li>
 * </ul></dd>
 * </dl>
 */
public final class EXTShaderSubgroupBallot {

    /** The extension specification version. */
    public static final int VK_EXT_SHADER_SUBGROUP_BALLOT_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String VK_EXT_SHADER_SUBGROUP_BALLOT_EXTENSION_NAME = "VK_EXT_shader_subgroup_ballot";

    private EXTShaderSubgroupBallot() {}

}