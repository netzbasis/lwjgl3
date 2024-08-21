/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package openxr.templates

import org.lwjgl.generator.*
import openxr.*

val KHR_loader_init = "KHRLoaderInit".nativeClassXR("KHR_loader_init", type = "instance", postfix = "KHR") {
    documentation =
        """
        The <a href="https://registry.khronos.org/OpenXR/specs/1.1/html/xrspec.html\#XR_KHR_loader_init">XR_KHR_loader_init</a> extension.

        On some platforms, before loading can occur the loader must be initialized with platform-specific parameters.

        Unlike other extensions, the presence of this extension is signaled by a successful call to #GetInstanceProcAddr() to retrieve the function pointer for #InitializeLoaderKHR() using #NULL_HANDLE as the {@code instance} parameter.

        If this extension is supported, its use <b>may</b> be required on some platforms and the use of the #InitializeLoaderKHR() function <b>must</b> precede other OpenXR calls except #GetInstanceProcAddr().

        This function exists as part of the loader library that the application is using and the loader <b>must</b> pass calls to #InitializeLoaderKHR() to the active runtime, and all enabled API layers that expose a #InitializeLoaderKHR() function exposed either through their manifest, or through their implementation of #GetInstanceProcAddr().

        If the #InitializeLoaderKHR() function is discovered through the manifest, #InitializeLoaderKHR() will be called before {@code xrNegotiateLoaderRuntimeInterface} or {@code xrNegotiateLoaderApiLayerInterface} has been called on the runtime or layer respectively.
        """

    IntConstant(
        "The extension specification version.",

        "KHR_loader_init_SPEC_VERSION".."2"
    )

    StringConstant(
        "The extension name.",

        "KHR_LOADER_INIT_EXTENSION_NAME".."XR_KHR_loader_init"
    )

    GlobalCommand..XrResult(
        "InitializeLoaderKHR",
        """
        Initializes loader.

        <h5>C Specification</h5>
        To initialize an OpenXR loader with platform or implementation-specific parameters, call:

        <pre><code>
￿XrResult xrInitializeLoaderKHR(
￿    const XrLoaderInitInfoBaseHeaderKHR*        loaderInitInfo);</code></pre>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>The {@link KHRLoaderInit XR_KHR_loader_init} extension <b>must</b> be enabled prior to calling #InitializeLoaderKHR()</li>
            <li>{@code loaderInitInfo} <b>must</b> be a pointer to a valid ##XrLoaderInitInfoBaseHeaderKHR-based structure</li>
        </ul>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_FUNCTION_UNSUPPORTED</li>
                <li>#ERROR_VALIDATION_FAILURE</li>
            </ul></dd>
        </dl>

        <h5>See Also</h5>
        ##XrLoaderInitInfoBaseHeaderKHR
        """,

        XrLoaderInitInfoBaseHeaderKHR.const.p("loaderInitInfo", "a pointer to an ##XrLoaderInitInfoBaseHeaderKHR structure, which is a polymorphic type defined by other platform- or implementation-specific extensions.")
    )
}