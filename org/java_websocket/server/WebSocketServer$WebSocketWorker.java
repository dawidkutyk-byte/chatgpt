/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.WebSocket
 *  org.java_websocket.WebSocketImpl
 *  org.java_websocket.server.WebSocketServer
 */
package org.java_websocket.server;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.server.WebSocketServer;

/*
 * Exception performing whole class analysis ignored.
 */
public class WebSocketServer.WebSocketWorker
extends Thread {
    private BlockingQueue<WebSocketImpl> iqueue = new LinkedBlockingQueue<WebSocketImpl>();

    @Override
    public void run() {
        WebSocketImpl ws = null;
        try {
            while (true) {
                ws = this.iqueue.take();
                ByteBuffer buf = (ByteBuffer)ws.inQueue.poll();
                assert (buf != null);
                this.doDecode(ws, buf);
                ws = null;
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        catch (LinkageError | ThreadDeath | VirtualMachineError e) {
            WebSocketServer.access$000((WebSocketServer)WebSocketServer.this).error("Got fatal error in worker thread {}", (Object)this.getName());
            Exception exception = new Exception(e);
            WebSocketServer.access$100((WebSocketServer)WebSocketServer.this, (WebSocket)ws, (Exception)exception);
        }
        catch (Throwable e) {
            WebSocketServer.access$000((WebSocketServer)WebSocketServer.this).error("Uncaught exception in thread {}: {}", (Object)this.getName(), (Object)e);
            if (ws == null) return;
            Exception exception = new Exception(e);
            WebSocketServer.this.onWebsocketError((WebSocket)ws, exception);
            ws.close();
        }
    }

    public WebSocketServer.WebSocketWorker() {
        this.setName("WebSocketWorker-" + this.getId());
        this.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new /* Unavailable Anonymous Inner Class!! */);
    }

    public void put(WebSocketImpl ws) throws InterruptedException {
        this.iqueue.put(ws);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void doDecode(WebSocketImpl ws, ByteBuffer buf) throws InterruptedException {
        try {
            ws.decode(buf);
        }
        catch (Exception e) {
            WebSocketServer.access$000((WebSocketServer)WebSocketServer.this).error("Error while reading from remote connection", e);
        }
        finally {
            WebSocketServer.access$200((WebSocketServer)WebSocketServer.this, (ByteBuffer)buf);
        }
    }
}
