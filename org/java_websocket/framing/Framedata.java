/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.enums.Opcode
 */
package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;

public interface Framedata {
    public boolean isRSV2();

    public ByteBuffer getPayloadData();

    public boolean isRSV3();

    public Opcode getOpcode();

    public boolean getTransfereMasked();

    public void append(Framedata var1);

    public boolean isRSV1();

    public boolean isFin();
}
