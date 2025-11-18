/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.config.SocketConfig$Builder
 *  org.apache.http.util.Args
 */
package org.apache.http.config;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.config.SocketConfig;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class SocketConfig
implements Cloneable {
    private final int soTimeout;
    private final boolean soReuseAddress;
    private final int rcvBufSize;
    private final int soLinger;
    private final int backlogSize;
    private final int sndBufSize;
    public static final SocketConfig DEFAULT = new Builder().build();
    private final boolean soKeepAlive;
    private final boolean tcpNoDelay;

    public int getRcvBufSize() {
        return this.rcvBufSize;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[soTimeout=").append(this.soTimeout).append(", soReuseAddress=").append(this.soReuseAddress).append(", soLinger=").append(this.soLinger).append(", soKeepAlive=").append(this.soKeepAlive).append(", tcpNoDelay=").append(this.tcpNoDelay).append(", sndBufSize=").append(this.sndBufSize).append(", rcvBufSize=").append(this.rcvBufSize).append(", backlogSize=").append(this.backlogSize).append("]");
        return builder.toString();
    }

    public int getSoTimeout() {
        return this.soTimeout;
    }

    public boolean isTcpNoDelay() {
        return this.tcpNoDelay;
    }

    public int getSoLinger() {
        return this.soLinger;
    }

    public boolean isSoReuseAddress() {
        return this.soReuseAddress;
    }

    public int getSndBufSize() {
        return this.sndBufSize;
    }

    protected SocketConfig clone() throws CloneNotSupportedException {
        return (SocketConfig)super.clone();
    }

    public static Builder custom() {
        return new Builder();
    }

    public int getBacklogSize() {
        return this.backlogSize;
    }

    public boolean isSoKeepAlive() {
        return this.soKeepAlive;
    }

    public static Builder copy(SocketConfig config) {
        Args.notNull((Object)config, (String)"Socket config");
        return new Builder().setSoTimeout(config.getSoTimeout()).setSoReuseAddress(config.isSoReuseAddress()).setSoLinger(config.getSoLinger()).setSoKeepAlive(config.isSoKeepAlive()).setTcpNoDelay(config.isTcpNoDelay()).setSndBufSize(config.getSndBufSize()).setRcvBufSize(config.getRcvBufSize()).setBacklogSize(config.getBacklogSize());
    }

    SocketConfig(int soTimeout, boolean soReuseAddress, int soLinger, boolean soKeepAlive, boolean tcpNoDelay, int sndBufSize, int rcvBufSize, int backlogSize) {
        this.soTimeout = soTimeout;
        this.soReuseAddress = soReuseAddress;
        this.soLinger = soLinger;
        this.soKeepAlive = soKeepAlive;
        this.tcpNoDelay = tcpNoDelay;
        this.sndBufSize = sndBufSize;
        this.rcvBufSize = rcvBufSize;
        this.backlogSize = backlogSize;
    }
}
