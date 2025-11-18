/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.AbstractWebSocket$1
 *  org.java_websocket.WebSocket
 *  org.java_websocket.WebSocketAdapter
 *  org.java_websocket.WebSocketImpl
 *  org.java_websocket.util.NamedThreadFactory
 */
package org.java_websocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public abstract class AbstractWebSocket
extends WebSocketAdapter {
    private ScheduledFuture<?> gDl\u0141;
    private boolean xZ7\u0105;
    private final Object \u0141l\u0179G;
    private boolean H3T7;
    private long 0SDR;
    private boolean \u0119hSr = false;
    private ScheduledExecutorService b\u011821;
    private final Logger t\u017c\u01423 = LoggerFactory.getLogger(AbstractWebSocket.class);

    public void setTcpNoDelay(boolean tcpNoDelay) {
        this.xZ7\u0105 = tcpNoDelay;
    }

    public AbstractWebSocket() {
        this.0SDR = TimeUnit.SECONDS.toNanos(60L);
        this.\u0141l\u0179G = new Object();
    }

    static /* synthetic */ Object access$000(AbstractWebSocket x0) {
        return x0.\u0141l\u0179G;
    }

    private void cancelConnectionLostTimer() {
        if (this.b\u011821 != null) {
            this.b\u011821.shutdownNow();
            this.b\u011821 = null;
        }
        if (this.gDl\u0141 == null) return;
        this.gDl\u0141.cancel(false);
        this.gDl\u0141 = null;
    }

    protected abstract Collection<WebSocket> getConnections();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void stopConnectionLostTimer() {
        Object object = this.\u0141l\u0179G;
        synchronized (object) {
            if (this.b\u011821 == null) {
                if (this.gDl\u0141 == null) return;
            }
            this.\u0119hSr = false;
            this.t\u017c\u01423.trace("Connection lost timer stopped");
            this.cancelConnectionLostTimer();
        }
    }

    public boolean isReuseAddr() {
        return this.H3T7;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setConnectionLostTimeout(int connectionLostTimeout) {
        Object object = this.\u0141l\u0179G;
        synchronized (object) {
            this.0SDR = TimeUnit.SECONDS.toNanos(connectionLostTimeout);
            if (this.0SDR <= 0L) {
                this.t\u017c\u01423.trace("Connection lost timer stopped");
                this.cancelConnectionLostTimer();
                return;
            }
            if (!this.\u0119hSr) return;
            this.t\u017c\u01423.trace("Connection lost timer restarted");
            try {
                ArrayList<WebSocket> connections = new ArrayList<WebSocket>(this.getConnections());
                for (WebSocket conn : connections) {
                    if (!(conn instanceof WebSocketImpl)) continue;
                    WebSocketImpl webSocketImpl = (WebSocketImpl)conn;
                    webSocketImpl.updateLastPong();
                }
            }
            catch (Exception e) {
                this.t\u017c\u01423.error("Exception during connection lost restart", e);
            }
            this.restartConnectionLostTimer();
        }
    }

    static /* synthetic */ void access$200(AbstractWebSocket x0, WebSocket x1, long x2) {
        x0.executeConnectionLostDetection(x1, x2);
    }

    static /* synthetic */ long access$100(AbstractWebSocket x0) {
        return x0.0SDR;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getConnectionLostTimeout() {
        Object object = this.\u0141l\u0179G;
        synchronized (object) {
            return (int)TimeUnit.NANOSECONDS.toSeconds(this.0SDR);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void startConnectionLostTimer() {
        Object object = this.\u0141l\u0179G;
        synchronized (object) {
            if (this.0SDR <= 0L) {
                this.t\u017c\u01423.trace("Connection lost timer deactivated");
                return;
            }
            this.t\u017c\u01423.trace("Connection lost timer started");
            this.\u0119hSr = true;
            this.restartConnectionLostTimer();
        }
    }

    public boolean isTcpNoDelay() {
        return this.xZ7\u0105;
    }

    public void setReuseAddr(boolean reuseAddr) {
        this.H3T7 = reuseAddr;
    }

    private void executeConnectionLostDetection(WebSocket webSocket, long minimumPongTime) {
        if (!(webSocket instanceof WebSocketImpl)) {
            return;
        }
        WebSocketImpl webSocketImpl = (WebSocketImpl)webSocket;
        if (webSocketImpl.getLastPong() < minimumPongTime) {
            this.t\u017c\u01423.trace("Closing connection due to no pong received: {}", (Object)webSocketImpl);
            webSocketImpl.closeConnection(1006, "The connection was closed because the other endpoint did not respond with a pong in time. For more information check: https://github.com/TooTallNate/Java-WebSocket/wiki/Lost-connection-detection");
        } else if (webSocketImpl.isOpen()) {
            webSocketImpl.sendPing();
        } else {
            this.t\u017c\u01423.trace("Trying to ping a non open connection: {}", (Object)webSocketImpl);
        }
    }

    private void restartConnectionLostTimer() {
        this.cancelConnectionLostTimer();
        this.b\u011821 = Executors.newSingleThreadScheduledExecutor((ThreadFactory)new NamedThreadFactory("connectionLostChecker"));
        1 connectionLostChecker = new /* Unavailable Anonymous Inner Class!! */;
        this.gDl\u0141 = this.b\u011821.scheduleAtFixedRate((Runnable)connectionLostChecker, this.0SDR, this.0SDR, TimeUnit.NANOSECONDS);
    }
}
