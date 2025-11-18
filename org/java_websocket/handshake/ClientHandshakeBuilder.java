/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.handshake.ClientHandshake
 *  org.java_websocket.handshake.HandshakeBuilder
 */
package org.java_websocket.handshake;

import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.HandshakeBuilder;

public interface ClientHandshakeBuilder
extends HandshakeBuilder,
ClientHandshake {
    public void setResourceDescriptor(String var1);
}
