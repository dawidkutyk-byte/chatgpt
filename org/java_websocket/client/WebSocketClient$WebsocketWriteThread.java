/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.WebSocket
 *  org.java_websocket.client.WebSocketClient
 */
package org.java_websocket.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;

/*
 * Exception performing whole class analysis ignored.
 */
private class WebSocketClient.WebsocketWriteThread
implements Runnable {
    private final WebSocketClient webSocketClient;

    private void closeSocket() {
        try {
            if (WebSocketClient.access$400((WebSocketClient)WebSocketClient.this) == null) return;
            WebSocketClient.access$400((WebSocketClient)WebSocketClient.this).close();
        }
        catch (IOException ex) {
            WebSocketClient.this.onWebsocketError((WebSocket)this.webSocketClient, (Exception)ex);
        }
    }

    private void runWriteData() throws IOException {
        try {
            while (!Thread.interrupted()) {
                ByteBuffer buffer = (ByteBuffer)WebSocketClient.access$200((WebSocketClient)WebSocketClient.this).outQueue.take();
                WebSocketClient.access$300((WebSocketClient)WebSocketClient.this).write(buffer.array(), 0, buffer.limit());
                WebSocketClient.access$300((WebSocketClient)WebSocketClient.this).flush();
            }
            return;
        }
        catch (InterruptedException e) {
            for (ByteBuffer buffer : WebSocketClient.access$200((WebSocketClient)WebSocketClient.this).outQueue) {
                WebSocketClient.access$300((WebSocketClient)WebSocketClient.this).write(buffer.array(), 0, buffer.limit());
                WebSocketClient.access$300((WebSocketClient)WebSocketClient.this).flush();
            }
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName("WebSocketWriteThread-" + Thread.currentThread().getId());
        try {
            this.runWriteData();
        }
        catch (IOException e) {
            WebSocketClient.access$000((WebSocketClient)WebSocketClient.this, (IOException)e);
        }
        finally {
            this.closeSocket();
            WebSocketClient.access$102((WebSocketClient)WebSocketClient.this, null);
        }
    }

    WebSocketClient.WebsocketWriteThread(WebSocketClient webSocketClient2) {
        this.webSocketClient = webSocketClient2;
    }
}
