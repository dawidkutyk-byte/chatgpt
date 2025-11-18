/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.handshake.Handshakedata
 */
package org.java_websocket.handshake;

import org.java_websocket.handshake.Handshakedata;

public interface ServerHandshake
extends Handshakedata {
    public String getHttpStatusMessage();

    public short getHttpStatus();
}
