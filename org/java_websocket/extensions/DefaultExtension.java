/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.exceptions.InvalidDataException
 *  org.java_websocket.exceptions.InvalidFrameException
 *  org.java_websocket.extensions.IExtension
 *  org.java_websocket.framing.Framedata
 */
package org.java_websocket.extensions;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.extensions.IExtension;
import org.java_websocket.framing.Framedata;

public class DefaultExtension
implements IExtension {
    public boolean acceptProvidedExtensionAsServer(String inputExtension) {
        return true;
    }

    public void encodeFrame(Framedata inputFrame) {
    }

    public void reset() {
    }

    public String getProvidedExtensionAsServer() {
        return "";
    }

    public boolean acceptProvidedExtensionAsClient(String inputExtension) {
        return true;
    }

    public String getProvidedExtensionAsClient() {
        return "";
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void decodeFrame(Framedata inputFrame) throws InvalidDataException {
    }

    public void isFrameValid(Framedata inputFrame) throws InvalidDataException {
        if (inputFrame.isRSV1()) throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
        if (inputFrame.isRSV2()) throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
        if (!inputFrame.isRSV3()) return;
        throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
    }

    public int hashCode() {
        return this.getClass().hashCode();
    }

    public boolean equals(Object o) {
        return this == o || o != null && this.getClass() == o.getClass();
    }

    public IExtension copyInstance() {
        return new DefaultExtension();
    }
}
