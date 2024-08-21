/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
#include "common_tools.h"
#include "opengles.h"

typedef jint (APIENTRY *glGetGraphicsResetStatusKHRPROC) (void);
typedef void (APIENTRY *glReadnPixelsKHRPROC) (jint, jint, jint, jint, jint, jint, jint, uintptr_t);
typedef void (APIENTRY *glGetnUniformfvKHRPROC) (jint, jint, jint, uintptr_t);
typedef void (APIENTRY *glGetnUniformivKHRPROC) (jint, jint, jint, uintptr_t);
typedef void (APIENTRY *glGetnUniformuivKHRPROC) (jint, jint, jint, uintptr_t);

EXTERN_C_ENTER

JNIEXPORT jint JNICALL Java_org_lwjgl_opengles_KHRRobustness_glGetGraphicsResetStatusKHR(JNIEnv *__env, jclass clazz) {
    glGetGraphicsResetStatusKHRPROC glGetGraphicsResetStatusKHR = (glGetGraphicsResetStatusKHRPROC)tlsGetFunction(596);
    UNUSED_PARAM(clazz)
    return glGetGraphicsResetStatusKHR();
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_KHRRobustness_nglReadnPixelsKHR__IIIIIIIJ(JNIEnv *__env, jclass clazz, jint x, jint y, jint width, jint height, jint format, jint type, jint bufSize, jlong pixelsAddress) {
    glReadnPixelsKHRPROC glReadnPixelsKHR = (glReadnPixelsKHRPROC)tlsGetFunction(597);
    uintptr_t pixels = (uintptr_t)pixelsAddress;
    UNUSED_PARAM(clazz)
    glReadnPixelsKHR(x, y, width, height, format, type, bufSize, pixels);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_KHRRobustness_nglGetnUniformfvKHR__IIIJ(JNIEnv *__env, jclass clazz, jint program, jint location, jint bufSize, jlong paramsAddress) {
    glGetnUniformfvKHRPROC glGetnUniformfvKHR = (glGetnUniformfvKHRPROC)tlsGetFunction(598);
    uintptr_t params = (uintptr_t)paramsAddress;
    UNUSED_PARAM(clazz)
    glGetnUniformfvKHR(program, location, bufSize, params);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_KHRRobustness_nglGetnUniformivKHR__IIIJ(JNIEnv *__env, jclass clazz, jint program, jint location, jint bufSize, jlong paramsAddress) {
    glGetnUniformivKHRPROC glGetnUniformivKHR = (glGetnUniformivKHRPROC)tlsGetFunction(599);
    uintptr_t params = (uintptr_t)paramsAddress;
    UNUSED_PARAM(clazz)
    glGetnUniformivKHR(program, location, bufSize, params);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengles_KHRRobustness_nglGetnUniformuivKHR__IIIJ(JNIEnv *__env, jclass clazz, jint program, jint location, jint bufSize, jlong paramsAddress) {
    glGetnUniformuivKHRPROC glGetnUniformuivKHR = (glGetnUniformuivKHRPROC)tlsGetFunction(600);
    uintptr_t params = (uintptr_t)paramsAddress;
    UNUSED_PARAM(clazz)
    glGetnUniformuivKHR(program, location, bufSize, params);
}

EXTERN_C_EXIT
