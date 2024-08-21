/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package openal.templates

import org.lwjgl.generator.*
import openal.*

val AL_SOFTX_hold_on_disconnect = "SOFTXHoldOnDisconnect".nativeClassAL("SOFTX_hold_on_disconnect") {
    documentation =
        """
        Native bindings to the $specLinkOpenALSoft extension.

        LWJGL: This extension is experimental.
        """

    IntConstant(
        "Accepted by the {@code target} parameter of #Enable(), #Disable(), and #IsEnabled().",

        "STOP_SOURCES_ON_DISCONNECT_SOFT"..0x19AB
    )
}