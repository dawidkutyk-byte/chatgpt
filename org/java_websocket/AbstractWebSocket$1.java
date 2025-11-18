/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.AbstractWebSocket
 *  org.java_websocket.WebSocket
 */
package org.java_websocket;

import java.util.ArrayList;
import java.util.Iterator;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.WebSocket;

/*
 * Exception performing whole class analysis ignored.
 */
class AbstractWebSocket.1
implements Runnable {
    private ArrayList<WebSocket> connections = new ArrayList();

    AbstractWebSocket.1() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        this.connections.clear();
        try {
            long minimumPongTime;
            this.connections.addAll(AbstractWebSocket.this.getConnections());
            Iterator<WebSocket> iterator = AbstractWebSocket.access$000((AbstractWebSocket)AbstractWebSocket.this);
            synchronized (iterator) {
                minimumPongTime = (long)((double)System.nanoTime() - (double)AbstractWebSocket.access$100((AbstractWebSocket)AbstractWebSocket.this) * 1.5);
            }
            for (WebSocket conn : this.connections) {
                AbstractWebSocket.access$200((AbstractWebSocket)AbstractWebSocket.this, (WebSocket)conn, (long)minimumPongTime);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.connections.clear();
    }
}
