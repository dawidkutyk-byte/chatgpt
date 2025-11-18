/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpHost
 *  org.apache.http.util.Args
 */
package org.apache.http.conn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import org.apache.http.HttpHost;
import org.apache.http.util.Args;

@Deprecated
public class HttpInetSocketAddress
extends InetSocketAddress {
    private final HttpHost httphost;
    private static final long serialVersionUID = -6650701828361907957L;

    @Override
    public String toString() {
        return this.httphost.getHostName() + ":" + this.getPort();
    }

    public HttpHost getHttpHost() {
        return this.httphost;
    }

    public HttpInetSocketAddress(HttpHost httphost, InetAddress addr, int port) {
        super(addr, port);
        Args.notNull((Object)httphost, (String)"HTTP host");
        this.httphost = httphost;
    }
}
