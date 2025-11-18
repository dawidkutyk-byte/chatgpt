/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.WebSocket
 *  org.java_websocket.WebSocketAdapter
 *  org.java_websocket.drafts.Draft
 */
package org.java_websocket;

import java.util.List;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.drafts.Draft;

public interface WebSocketFactory {
    public WebSocket createWebSocket(WebSocketAdapter var1, List<Draft> var2);

    public WebSocket createWebSocket(WebSocketAdapter var1, Draft var2);
}
