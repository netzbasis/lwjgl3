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
 * hb_blob_t * (*{@link #invoke}) (
 *     hb_face_t *face,
 *     hb_tag_t tag,
 *     void *user_data
 * )</code></pre>
 */
@FunctionalInterface
@NativeType("hb_reference_table_func_t")
public interface hb_reference_table_func_tI extends CallbackI {

    FFICIF CIF = apiCreateCIF(
        FFI_DEFAULT_ABI,
        ffi_type_pointer,
        ffi_type_pointer, ffi_type_uint32, ffi_type_pointer
    );

    @Override
    default FFICIF getCallInterface() { return CIF; }

    @Override
    default void callback(long ret, long args) {
        long __result = invoke(
            memGetAddress(memGetAddress(args)),
            memGetInt(memGetAddress(args + POINTER_SIZE)),
            memGetAddress(memGetAddress(args + 2 * POINTER_SIZE))
        );
        apiClosureRetP(ret, __result);
    }

    /**
     * Callback function for {@link HarfBuzz#hb_face_create_for_tables face_create_for_tables}.
     * 
     * <p>Return value: (transfer full): A pointer to the tag table within face</p>
     *
     * @param face      an {@code hb_face_t} to reference table for
     * @param tag       the tag of the table to reference
     * @param user_data user data pointer passed by the caller
     */
    @NativeType("hb_blob_t *") long invoke(@NativeType("hb_face_t *") long face, @NativeType("hb_tag_t") int tag, @NativeType("void *") long user_data);

}