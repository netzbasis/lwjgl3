/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package llvm.templates

import llvm.*
import org.lwjgl.generator.*

val LLVMInitialization = "LLVMInitialization".nativeClass(
    Module.LLVM,
    prefixConstant = "LLVM",
    prefixMethod = "LLVM",
    binding = LLVM_BINDING_DELEGATE
) {
    documentation =
        """
        This module contains routines used to initialize the LLVM system.

        Removed in LLVM 17.
        """

    void("InitializeCore", "", LLVMPassRegistryRef("R", ""))
    void("InitializeTransformUtils", "", LLVMPassRegistryRef("R", ""))
    void("InitializeScalarOpts", "", LLVMPassRegistryRef("R", ""))
    IgnoreMissing..void("InitializeObjCARCOpts", "", LLVMPassRegistryRef("R", ""))
    void("InitializeVectorization", "", LLVMPassRegistryRef("R", ""))
    IgnoreMissing..void("InitializeInstCombine", "", LLVMPassRegistryRef("R", ""))
    IgnoreMissing..void("InitializeAggressiveInstCombiner", "", LLVMPassRegistryRef("R", ""))
    void("InitializeIPO", "", LLVMPassRegistryRef("R", ""))
    IgnoreMissing..void("InitializeInstrumentation", "", LLVMPassRegistryRef("R", ""))
    void("InitializeAnalysis", "", LLVMPassRegistryRef("R", ""))
    void("InitializeIPA", "", LLVMPassRegistryRef("R", ""))
    void("InitializeCodeGen", "", LLVMPassRegistryRef("R", ""))
    void("InitializeTarget", "", LLVMPassRegistryRef("R", ""))
}