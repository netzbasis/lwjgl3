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
 *     VkMemoryAllocateInfo *allocInfo,
 *     VkMemoryRequirements *memReq,
 *     uint64_t *pageCount
 * )</code></pre>
 */
@FunctionalInterface
@NativeType("void * (*) (VkMemoryAllocateInfo *, VkMemoryRequirements *, uint64_t *)")
public interface ktxVulkanTexture_subAllocatorAllocMemFuncPtrI extends CallbackI {

    FFICIF CIF = apiCreateCIF(
        apiStdcall(),
        ffi_type_pointer,
        ffi_type_pointer, ffi_type_pointer, ffi_type_pointer
    );

    @Override
    default FFICIF getCallInterface() { return CIF; }

    @Override
    default void callback(long ret, long args) {
        long __result = invoke(
            memGetAddress(memGetAddress(args)),
            memGetAddress(memGetAddress(args + POINTER_SIZE)),
            memGetAddress(memGetAddress(args + 2 * POINTER_SIZE))
        );
        apiClosureRetP(ret, __result);
    }

    /** The memory procurement function. Can suballocate one or more pages. */
    @NativeType("void *") long invoke(@NativeType("VkMemoryAllocateInfo *") long allocInfo, @NativeType("VkMemoryRequirements *") long memReq, @NativeType("uint64_t *") long pageCount);

}