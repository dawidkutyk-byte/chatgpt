/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.enums.Opcode
 *  org.java_websocket.framing.ControlFrame
 */
package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
import org.java_websocket.framing.ControlFrame;

public class PingFrame
extends ControlFrame {
    public PingFrame() {
        super(Opcode.PING);
    }
}
