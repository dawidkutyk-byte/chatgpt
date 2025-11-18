/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.exceptions.InvalidDataException
 *  org.java_websocket.exceptions.InvalidFrameException
 *  org.java_websocket.extensions.DefaultExtension
 *  org.java_websocket.framing.ControlFrame
 *  org.java_websocket.framing.DataFrame
 *  org.java_websocket.framing.Framedata
 */
package org.java_websocket.extensions;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.extensions.DefaultExtension;
import org.java_websocket.framing.ControlFrame;
import org.java_websocket.framing.DataFrame;
import org.java_websocket.framing.Framedata;

public abstract class CompressionExtension
extends DefaultExtension {
    public void isFrameValid(Framedata inputFrame) throws InvalidDataException {
        if (inputFrame instanceof DataFrame) {
            if (inputFrame.isRSV2()) throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
            if (inputFrame.isRSV3()) {
                throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
            }
        }
        if (!(inputFrame instanceof ControlFrame)) return;
        if (inputFrame.isRSV1()) throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
        if (inputFrame.isRSV2()) throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
        if (!inputFrame.isRSV3()) return;
        throw new InvalidFrameException("bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame.isRSV3());
    }
}
