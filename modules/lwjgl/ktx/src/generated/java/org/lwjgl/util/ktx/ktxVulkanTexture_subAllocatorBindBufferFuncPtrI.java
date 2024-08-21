/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.util.ktx;

import org.lwjgl.system.*;
import org.lwjgl.system.libffi.*;

import static org.lwjgl.system.APIUtil.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.libffi.LibFFI.*;

/**
 * <h3>Type</h3>
 * 
 * <pre><code>
 * void * (*{@link #invoke}) (
 *     VkBuffer buffer,
 *     uint64_t allocId
 * )</code></pre>
 */
@FunctionalInterface
@NativeType("void * (*) (VkBuffer, uint64_t)")
public interface ktxVulkanTexture_subAllocatorBindBufferFuncPtrI extends CallbackI {

    FFICIF CIF = apiCreateCIF(
        apiStdcall(),
        ffi_type_pointer,
        ffi_type_uint64, ffi_type_uint64
    );

    @Override
    default FFICIF getCallInterface() { return CIF; }

    @Override
    default void callback(long ret, long args) {
        long __result = invoke(
            memGetLong(memGetAddress(args)),
            memGetLong(memGetAddress(args + POINTER_SIZE))
        );
        apiClosureRetP(ret, __result);
    }

    /** The bind-buffer-to-suballocation(s) function. */
    @NativeType("void *") long invoke(@NativeType("VkBuffer") long buffer, @NativeType("uint64_t") long allocId);

}