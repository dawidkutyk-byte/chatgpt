/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.enums.Opcode
 *  org.java_websocket.exceptions.InvalidDataException
 *  org.java_websocket.framing.FramedataImpl1
 */
package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.FramedataImpl1;

public abstract class DataFrame
extends FramedataImpl1 {
    public void isValid() throws InvalidDataException {
    }

    public DataFrame(Opcode opcode) {
        super(opcode);
    }
}
