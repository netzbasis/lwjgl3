/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package egl.templates

import egl.*
import org.lwjgl.generator.*

val EXT = "EXT"
val KHR = "KHR"

val ANDROID = "ANDROID"
val ANGLE = "ANGLE"
val ARM = "ARM"
val HI = "HI"
val IMG = "IMG"
val MESA = "MESA"
val NOK = "NOK"
val NV = "NV"
val OVR = "OVR"
val TIZEN = "TIZEN"
val WL = "WL"

private val NativeClass.cap: String get() = "{@link #$capName $templateName}"

val EXT_client_extensions = EXT_FLAG.nativeClassEGL("EXT_client_extensions", postfix = EXT) {
    documentation =
        """
        When true, the $registryLink extension is supported.

        This extension introduces the concept of *extension type*, requires that each EGL extension belong to exactly one type, and defines two types: display
        and client. It also provides a method to query, without initializing a display, the set of supported client extensions.

        A display extension adds functionality to an individual EGLDisplay. This type of extension has always existed but, until EGL_EXT_client_extensions,
        lacked an identifying name.

        A client extension adds functionality that is independent of any display. In other words, it adds functionality to the EGL client library itself. This
        is a new type of extension defined by EGL_EXT_client_extensions. EGL_EXT_client_extensions is itself a client extension.

        We suggest that each future extension clearly state its type by including the following toplevel section in its extension specification, preceding the
        Dependencies section. For client extensions, this suggestion is a requirement.
        ${codeBlock("""
        Extension Type

            &lt;Either "EGL display extension" or "EGL client extension" or
             a future extension type.&gt;""")}

        By cleanly separating display extensions from client extensions, EGL_EXT_client_extensions solves a bootstrap problem for future EGL extensions that
        will modify display initialization. To query for such extensions without EGL_EXT_client_extensions, an EGL client would need to initialize a throw-away
        EGLDisplay solely to query its extension string. Initialization of the throw-away display may have undesired side-effects (discussed in the issues
        section below) for EGL clients that wish to use the new methods of display initialization.
        """
}

val EXT_explicit_device = EXT_FLAG.nativeClassEGL("EXT_explicit_device", postfix = EXT) {
    documentation =
        """
        When true, the $registryLink extension is supported.

        A system may support rendering with multiple devices for the same windowing system. In that case, an EGL implementation must select a default device
        based on the native display.

        This extension allows an application to explicitly request a device to use for rendering instead of the implementation's default.

        This differs from ${EXT_platform_device.link} in that {@code EGL_EXT_platform_device} uses an {@code EGLDeviceEXT} instead of a native display. Thus,
        {@code EGL_EXT_platform_device} allows offscreen rendering to a pbuffer or FBO, but it does not require or use a windowing system, and thus does not
        allow pixmap or window surfaces.

        Using {@code EGL_EXT_explicit_device} with {@code EGL_MESA_platform_surfaceless} is functionally identical to {@code EGL_EXT_platform_device}.
        """
}

val EXT_query_reset_notification_strategy = EXT_FLAG.nativeClassEGL("EXT_query_reset_notification_strategy", postfix = EXT) {
    documentation =
        """
        When true, the $registryLink extension is supported.

        This extension complements {@code EXT_create_context_robustness} and enables an application or framework to retrieve an existing context's reset
        notification strategy in order to create a compatible shared context.

        Requires ${EGL14.core} and ${EXT_create_context_robustness.link}.
        """
}

val KHR_client_get_all_proc_addresses = EXT_FLAG.nativeClassEGL("KHR_client_get_all_proc_addresses", postfix = KHR) {
    documentation =
        """
        When true, the ${registryLink("KHR", "EGL_KHR_get_all_proc_addresses")} extension is supported.

        eglGetProcAddress is currently defined to not support the querying of non-extension EGL or client API functions. Non-extension functions are expected
        to be exposed as library symbols that can be resolved statically at link time, or dynamically at run time using OS-specific runtime linking mechanisms.

        With the addition of OpenGL and OpenGL ES 3 support to EGL, the definition of a non-extension function becomes less clear. It is common for one OpenGL
        library to implement many versions of OpenGL. The suggested library name for OpenGL ES 3 is the same as that of OpenGL ES 2. If OpenGL ES 3
        applications linked statically to OpenGL ES 3 functions are run on a system with only OpenGL ES 2 support, they may fail to load. Similar problems
        would be encountered by an application linking statically to various OpenGL functions.

        To avoid requiring applications to fall back to OS-specific dynamic linking mechanisms, this extension drops the requirement that eglGetProcAddress
        return only non-extension functions. If the extension string is present, applications can query all EGL and client API functions using
        eglGetProcAddress.

        To allow users to query this extension before initializing a display, and to also allow vendors to ship this extension without
        EGL_EXT_client_extensions, two names are assigned to this extension: one a display extension and the other a client extension. Identical functionality
        is exposed by each name, but users query each name using different methods. Users query EGL_KHR_get_all_proc_addresses in the usual way; that is, by
        calling eglQueryString(dpy, EGL_EXTENSIONS) on an initialized display. To query EGL_KHR_client_get_all_proc_addresses, users must use a different
        method which is described below in the section concerning EGL_EXT_client_extensions.

        Requires ${EGL12.core}.
        """
}

val KHR_get_all_proc_addresses = EXT_FLAG.nativeClassEGL("KHR_get_all_proc_addresses", postfix = KHR) {
    documentation =
        """
        When true, the $registryLink extension is supported.

        eglGetProcAddress is currently defined to not support the querying of non-extension EGL or client API functions. Non-extension functions are expected
        to be exposed as library symbols that can be resolved statically at link time, or dynamically at run time using OS-specific runtime linking mechanisms.

        With the addition of OpenGL and OpenGL ES 3 support to EGL, the definition of a non-extension function becomes less clear. It is common for one OpenGL
        library to implement many versions of OpenGL. The suggested library name for OpenGL ES 3 is the same as that of OpenGL ES 2. If OpenGL ES 3
        applications linked statically to OpenGL ES 3 functions are run on a system with only OpenGL ES 2 support, they may fail to load. Similar problems
        would be encountered by an application linking statically to various OpenGL functions.

        To avoid requiring applications to fall back to OS-specific dynamic linking mechanisms, this extension drops the requirement that eglGetProcAddress
        return only non-extension functions. If the extension string is present, applications can query all EGL and client API functions using
        eglGetProcAddress.

        To allow users to query this extension before initializing a display, and to also allow vendors to ship this extension without
        EGL_EXT_client_extensions, two names are assigned to this extension: one a display extension and the other a client extension. Identical functionality
        is exposed by each name, but users query each name using different methods. Users query EGL_KHR_get_all_proc_addresses in the usual way; that is, by
        calling eglQueryString(dpy, EGL_EXTENSIONS) on an initialized display. To query EGL_KHR_client_get_all_proc_addresses, users must use a different
        method which is described below in the section concerning EGL_EXT_client_extensions.

        Requires ${EGL12.core}.
        """
}

val KHR_stream_producer_aldatalocator = EXT_FLAG.nativeClassEGL("KHR_stream_producer_aldatalocator", postfix = KHR) {
    documentation =
        """
        When true, the $registryLink extension is supported.

        This extension (in conjunction with the OpenMAX_AL_EGLStream_DataLocator extension to OpenMAX AL) allows an OpenMAX AL MediaPlayer object to be
        connected as the producer of an EGLStream.

        After the EGLStream is created and connected to a consumer, the OpenMAX AL MediaPlayer object is created by calling &lt;pEngine&gt;'s
        CreateMediaPlayer() method. The &lt;pImageVideoSnk&gt; argument points to an XADataLocator_EGLStream containing the EGLStreamKHR handle of the stream.
        The CreateMediaPlayer() method creates a MediaPlayer object and connects it as the producer of the EGLStream. (Note that the pFormat member of the
        XADataSink structure is ignored in this case and may be #NULL.)

        Once connected the MediaPlayer inserts image frames into the EGLStream.

        Requires ${EGL12.core} and ${KHR_stream.link}. Requires OpenMAX AL 1.1 and OpenMAX_AL_EGLStream_DataLocator.
        """
}

val KHR_surfaceless_context = EXT_FLAG.nativeClassEGL("KHR_surfaceless_context", postfix = KHR) {
    documentation =
        """
        When true, the $registryLink extension is supported.

        These extensions allows an application to make a context current by passing EGL_NO_SURFACE for the write and read surface in the call to
        eglMakeCurrent. The motivation is that applications that only want to render to client API targets (such as OpenGL framebuffer objects) should not need
        to create a throw-away EGL surface just to get a current context.

        The state of an OpenGL ES context with no default framebuffer provided by EGL is the same as a context with an incomplete framebuffer object bound.
        """
}

val NV_post_convert_rounding = EXT_FLAG.nativeClassEGL("NV_post_convert_rounding", postfix = NV) {
    documentation =
        """
        When true, the $registryLink extension is supported.

        This extension defines the conversions for posting operations when the destination's number of components or component sizes do not match the color
        buffer. This extension supports posting a 24 bit (888) color buffer to a 16 bit (565) destination buffer, posting a 16 bit (565) color buffer to a 24
        bit (888) destination buffer, and posting a component that is present in the source buffer, but not present in the destination buffer.
        """
}

val NV_stream_cross_object = EXT_FLAG.nativeClassEGL("NV_stream_cross_object", postfix = NV) { documentation = "See ${NV_stream_remote.link}." }
val NV_stream_cross_display = EXT_FLAG.nativeClassEGL("NV_stream_cross_display", postfix = NV) { documentation = "See ${NV_stream_remote.link}." }
val NV_stream_cross_process = EXT_FLAG.nativeClassEGL("NV_stream_cross_process", postfix = NV) { documentation = "See ${NV_stream_remote.link}." }
val NV_stream_cross_partition = EXT_FLAG.nativeClassEGL("NV_stream_cross_partition", postfix = NV) { documentation = "See ${NV_stream_remote.link}." }
val NV_stream_cross_system = EXT_FLAG.nativeClassEGL("NV_stream_cross_system", postfix = NV) { documentation = "See ${NV_stream_remote.link}." }