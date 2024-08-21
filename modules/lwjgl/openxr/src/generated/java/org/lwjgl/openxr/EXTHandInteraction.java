/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.openxr;

/**
 * The <a href="https://registry.khronos.org/OpenXR/specs/1.1/html/xrspec.html#XR_EXT_hand_interaction">XR_EXT_hand_interaction</a> extension.
 * 
 * <p>This extension defines four commonly used action poses for all user hand interaction profiles including both hand tracking devices and motion controller devices.</p>
 * 
 * <p>This extension also introduces a new interaction profile specifically designed for hand tracking devices to input through the <a href="https://registry.khronos.org/OpenXR/specs/1.1/html/xrspec.html#input">OpenXR action system</a>. Though, for runtimes with controller inputs, the runtime <b>should</b> also provide this interaction profile through action mappings from the controller inputs, so that an application whose suggested action bindings solely depending on this hand interaction profile is usable on such runtimes as well.</p>
 */
public final class EXTHandInteraction {

    /** The extension specification version. */
    public static final int XR_EXT_hand_interaction_SPEC_VERSION = 1;

    /** The extension name. */
    public static final String XR_EXT_HAND_INTERACTION_EXTENSION_NAME = "XR_EXT_hand_interaction";

    private EXTHandInteraction() {}

}