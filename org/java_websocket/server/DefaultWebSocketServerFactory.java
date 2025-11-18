/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.WebSocketAdapter
 *  org.java_websocket.WebSocketImpl
 *  org.java_websocket.WebSocketListener
 *  org.java_websocket.WebSocketServerFactory
 *  org.java_websocket.drafts.Draft
 */
package org.java_websocket.server;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketListener;
import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.drafts.Draft;

public class DefaultWebSocketServerFactory
implements WebSocketServerFactory {
    public void close() {
    }

    public SocketChannel wrapChannel(SocketChannel channel, SelectionKey key) {
        return channel;
    }

    public WebSocketImpl createWebSocket(WebSocketAdapter a, List<Draft> d) {
        return new WebSocketImpl((WebSocketListener)a, d);
    }

    public WebSocketImpl createWebSocket(WebSocketAdapter a, Draft d) {
        return new WebSocketImpl((WebSocketListener)a, d);
    }
}
