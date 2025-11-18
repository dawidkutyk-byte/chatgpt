/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.server.WebSocketServer
 */
package org.java_websocket.server;

import org.java_websocket.server.WebSocketServer;

/*
 * Exception performing whole class analysis ignored.
 */
class WebSocketServer.WebSocketWorker.1
implements Thread.UncaughtExceptionHandler {
    final /* synthetic */ WebSocketServer val$this$0;

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        WebSocketServer.access$000((WebSocketServer)WebSocketWorker.this.this$0).error("Uncaught exception in thread {}: {}", (Object)t.getName(), (Object)e);
    }

    WebSocketServer.WebSocketWorker.1() {
        this.val$this$0 = webSocketServer;
    }
}
