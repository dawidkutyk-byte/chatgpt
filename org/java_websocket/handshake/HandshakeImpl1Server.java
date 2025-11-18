/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.handshake.HandshakedataImpl1
 *  org.java_websocket.handshake.ServerHandshakeBuilder
 */
package org.java_websocket.handshake;

import org.java_websocket.handshake.HandshakedataImpl1;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public class HandshakeImpl1Server
extends HandshakedataImpl1
implements ServerHandshakeBuilder {
    private short httpstatus;
    private String httpstatusmessage;

    public void setHttpStatus(short status) {
        this.httpstatus = status;
    }

    public void setHttpStatusMessage(String message) {
        this.httpstatusmessage = message;
    }

    public String getHttpStatusMessage() {
        return this.httpstatusmessage;
    }

    public short getHttpStatus() {
        return this.httpstatus;
    }
}
