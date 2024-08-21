/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.util.harfbuzz;

import org.lwjgl.system.*;
import org.lwjgl.system.libffi.*;

import static org.lwjgl.system.APIUtil.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.libffi.LibFFI.*;

/**
 * <h3>Type</h3>
 * 
 * <pre><code>
 * hb_codepoint_t (*{@link #invoke}) (
 *     hb_unicode_funcs_t *ufuncs,
 *     hb_codepoint_t unicode,
 *     void *user_data
 * )</code></pre>
 */
@FunctionalInterface
@NativeType("hb_unicode_mirroring_func_t")
public interface hb_unicode_mirroring_func_tI extends CallbackI {

    FFICIF CIF = apiCreateCIF(
        FFI_DEFAULT_ABI,
        ffi_type_uint32,
        ffi_type_pointer, ffi_type_uint32, ffi_type_pointer
    );

    @Override
    default FFICIF getCallInterface() { return CIF; }

    @Override
    default void callback(long ret, long args) {
        int __result = invoke(
            memGetAddress(memGetAddress(args)),
            memGetInt(memGetAddress(args + POINTER_SIZE)),
            memGetAddress(memGetAddress(args + 2 * POINTER_SIZE))
        );
        apiClosureRet(ret, __result);
    }

    /**
     * A virtual method for the {@code hb_unicode_funcs_t} structure.
     * 
     * <p>This method should retrieve the Bi-Directional Mirroring Glyph code point for a specified Unicode code point.</p>
     * 
     * <p>Note: If a code point does not have a specified Bi-Directional Mirroring Glyph defined, the method should return the original code point.</p>
     * 
     * <p>Return value: The {@code hb_codepoint_t} of the Mirroring Glyph for unicode</p>
     *
     * @param ufuncs    a Unicode-functions structure
     * @param unicode   the code point to query
     * @param user_data user data pointer passed by the caller
     */
    @NativeType("hb_codepoint_t") int invoke(@NativeType("hb_unicode_funcs_t *") long ufuncs, @NativeType("hb_codepoint_t") int unicode, @NativeType("void *") long user_data);

}