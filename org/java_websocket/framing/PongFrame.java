/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.enums.Opcode
 *  org.java_websocket.framing.ControlFrame
 *  org.java_websocket.framing.PingFrame
 */
package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
import org.java_websocket.framing.ControlFrame;
import org.java_websocket.framing.PingFrame;

public class PongFrame
extends ControlFrame {
    public PongFrame() {
        super(Opcode.PONG);
    }

    public PongFrame(PingFrame pingFrame) {
        super(Opcode.PONG);
        this.setPayload(pingFrame.getPayloadData());
    }
}
