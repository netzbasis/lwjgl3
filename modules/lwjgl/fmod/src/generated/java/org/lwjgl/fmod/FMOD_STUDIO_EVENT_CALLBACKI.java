/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.fmod;

import org.lwjgl.system.*;
import org.lwjgl.system.libffi.*;

import static org.lwjgl.system.APIUtil.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.libffi.LibFFI.*;

/**
 * <h3>Type</h3>
 * 
 * <pre><code>
 * FMOD_RESULT (*{@link #invoke}) (
 *     FMOD_STUDIO_EVENT_CALLBACK_TYPE type,
 *     FMOD_STUDIO_EVENTINSTANCE *event,
 *     void *parameters
 * )</code></pre>
 */
@FunctionalInterface
@NativeType("FMOD_STUDIO_EVENT_CALLBACK")
public interface FMOD_STUDIO_EVENT_CALLBACKI extends CallbackI {

    FFICIF CIF = apiCreateCIF(
        apiStdcall(),
        ffi_type_uint32,
        ffi_type_uint32, ffi_type_pointer, ffi_type_pointer
    );

    @Override
    default FFICIF getCallInterface() { return CIF; }

    @Override
    default void callback(long ret, long args) {
        int __result = invoke(
            memGetInt(memGetAddress(args)),
            memGetAddress(memGetAddress(args + POINTER_SIZE)),
            memGetAddress(memGetAddress(args + 2 * POINTER_SIZE))
        );
        apiClosureRet(ret, __result);
    }

    @NativeType("FMOD_RESULT") int invoke(@NativeType("FMOD_STUDIO_EVENT_CALLBACK_TYPE") int type, @NativeType("FMOD_STUDIO_EVENTINSTANCE *") long event, @NativeType("void *") long parameters);

}