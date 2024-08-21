/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.fmod;

import javax.annotation.*;

import org.lwjgl.system.*;

import static org.lwjgl.system.MemoryUtil.*;

/**
 * <h3>Type</h3>
 * 
 * <pre><code>
 * FMOD_RESULT (*{@link #invoke}) (
 *     struct FMOD_OUTPUT_STATE *output_state,
 *     FMOD_PORT_TYPE portType,
 *     FMOD_PORT_INDEX portIndex,
 *     int *portId,
 *     int *portRate,
 *     int *portChannels,
 *     FMOD_SOUND_FORMAT *portFormat
 * )</code></pre>
 */
public abstract class FMOD_OUTPUT_OPENPORT_CALLBACK extends Callback implements FMOD_OUTPUT_OPENPORT_CALLBACKI {

    /**
     * Creates a {@code FMOD_OUTPUT_OPENPORT_CALLBACK} instance from the specified function pointer.
     *
     * @return the new {@code FMOD_OUTPUT_OPENPORT_CALLBACK}
     */
    public static FMOD_OUTPUT_OPENPORT_CALLBACK create(long functionPointer) {
        FMOD_OUTPUT_OPENPORT_CALLBACKI instance = Callback.get(functionPointer);
        return instance instanceof FMOD_OUTPUT_OPENPORT_CALLBACK
            ? (FMOD_OUTPUT_OPENPORT_CALLBACK)instance
            : new Container(functionPointer, instance);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code functionPointer} is {@code NULL}. */
    @Nullable
    public static FMOD_OUTPUT_OPENPORT_CALLBACK createSafe(long functionPointer) {
        return functionPointer == NULL ? null : create(functionPointer);
    }

    /** Creates a {@code FMOD_OUTPUT_OPENPORT_CALLBACK} instance that delegates to the specified {@code FMOD_OUTPUT_OPENPORT_CALLBACKI} instance. */
    public static FMOD_OUTPUT_OPENPORT_CALLBACK create(FMOD_OUTPUT_OPENPORT_CALLBACKI instance) {
        return instance instanceof FMOD_OUTPUT_OPENPORT_CALLBACK
            ? (FMOD_OUTPUT_OPENPORT_CALLBACK)instance
            : new Container(instance.address(), instance);
    }

    protected FMOD_OUTPUT_OPENPORT_CALLBACK() {
        super(CIF);
    }

    FMOD_OUTPUT_OPENPORT_CALLBACK(long functionPointer) {
        super(functionPointer);
    }

    private static final class Container extends FMOD_OUTPUT_OPENPORT_CALLBACK {

        private final FMOD_OUTPUT_OPENPORT_CALLBACKI delegate;

        Container(long functionPointer, FMOD_OUTPUT_OPENPORT_CALLBACKI delegate) {
            super(functionPointer);
            this.delegate = delegate;
        }

        @Override
        public int invoke(long output_state, int portType, long portIndex, long portId, long portRate, long portChannels, long portFormat) {
            return delegate.invoke(output_state, portType, portIndex, portId, portRate, portChannels, portFormat);
        }

    }

}