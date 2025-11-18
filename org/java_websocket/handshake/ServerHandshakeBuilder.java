/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.handshake.HandshakeBuilder
 *  org.java_websocket.handshake.ServerHandshake
 */
package org.java_websocket.handshake;

import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshake;

public interface ServerHandshakeBuilder
extends ServerHandshake,
HandshakeBuilder {
    public void setHttpStatus(short var1);

    public void setHttpStatusMessage(String var1);
}
