/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.enums.Opcode
 *  org.java_websocket.exceptions.InvalidDataException
 *  org.java_websocket.framing.DataFrame
 *  org.java_websocket.util.Charsetfunctions
 */
package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.DataFrame;
import org.java_websocket.util.Charsetfunctions;

public class TextFrame
extends DataFrame {
    public TextFrame() {
        super(Opcode.TEXT);
    }

    public void isValid() throws InvalidDataException {
        super.isValid();
        if (Charsetfunctions.isValidUTF8((ByteBuffer)this.getPayloadData())) return;
        throw new InvalidDataException(1007, "Received text is no valid utf8 string!");
    }
}
