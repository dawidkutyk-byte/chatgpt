/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.enums.Opcode
 *  org.java_websocket.framing.DataFrame
 */
package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
import org.java_websocket.framing.DataFrame;

public class ContinuousFrame
extends DataFrame {
    public ContinuousFrame() {
        super(Opcode.CONTINUOUS);
    }
}
