/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.WebSocket
 *  org.java_websocket.WebSocketListener
 *  org.java_websocket.drafts.Draft
 *  org.java_websocket.exceptions.InvalidDataException
 *  org.java_websocket.framing.Framedata
 *  org.java_websocket.framing.PingFrame
 *  org.java_websocket.framing.PongFrame
 *  org.java_websocket.handshake.ClientHandshake
 *  org.java_websocket.handshake.HandshakeImpl1Server
 *  org.java_websocket.handshake.ServerHandshake
 *  org.java_websocket.handshake.ServerHandshakeBuilder
 */
package org.java_websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketListener;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.framing.PongFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.HandshakeImpl1Server;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public abstract class WebSocketAdapter
implements WebSocketListener {
    private PingFrame \u017c8a\u0141;

    public void onWebsocketHandshakeReceivedAsClient(WebSocket conn, ClientHandshake request, ServerHandshake response) throws InvalidDataException {
    }

    public void onWebsocketPong(WebSocket conn, Framedata f) {
    }

    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        return new HandshakeImpl1Server();
    }

    public void onWebsocketPing(WebSocket conn, Framedata f) {
        conn.sendFrame((Framedata)new PongFrame((PingFrame)f));
    }

    public PingFrame onPreparePing(WebSocket conn) {
        if (this.\u017c8a\u0141 != null) return this.\u017c8a\u0141;
        this.\u017c8a\u0141 = new PingFrame();
        return this.\u017c8a\u0141;
    }

    public void onWebsocketHandshakeSentAsClient(WebSocket conn, ClientHandshake request) throws InvalidDataException {
    }
}
