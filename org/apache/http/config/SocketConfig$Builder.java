/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.config.SocketConfig
 */
package org.apache.http.config;

import org.apache.http.config.SocketConfig;

public static class SocketConfig.Builder {
    private int soLinger = -1;
    private boolean soReuseAddress;
    private boolean soKeepAlive;
    private boolean tcpNoDelay = true;
    private int backlogSize;
    private int soTimeout;
    private int rcvBufSize;
    private int sndBufSize;

    public SocketConfig build() {
        return new SocketConfig(this.soTimeout, this.soReuseAddress, this.soLinger, this.soKeepAlive, this.tcpNoDelay, this.sndBufSize, this.rcvBufSize, this.backlogSize);
    }

    public SocketConfig.Builder setTcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
        return this;
    }

    public SocketConfig.Builder setSoReuseAddress(boolean soReuseAddress) {
        this.soReuseAddress = soReuseAddress;
        return this;
    }

    public SocketConfig.Builder setRcvBufSize(int rcvBufSize) {
        this.rcvBufSize = rcvBufSize;
        return this;
    }

    public SocketConfig.Builder setSndBufSize(int sndBufSize) {
        this.sndBufSize = sndBufSize;
        return this;
    }

    public SocketConfig.Builder setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
        return this;
    }

    public SocketConfig.Builder setSoKeepAlive(boolean soKeepAlive) {
        this.soKeepAlive = soKeepAlive;
        return this;
    }

    SocketConfig.Builder() {
    }

    public SocketConfig.Builder setSoLinger(int soLinger) {
        this.soLinger = soLinger;
        return this;
    }

    public SocketConfig.Builder setBacklogSize(int backlogSize) {
        this.backlogSize = backlogSize;
        return this;
    }
}
