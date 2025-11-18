/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpHost
 *  org.apache.http.conn.routing.RouteInfo$LayerType
 *  org.apache.http.conn.routing.RouteInfo$TunnelType
 */
package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.RouteInfo;

public interface RouteInfo {
    public HttpHost getProxyHost();

    public InetAddress getLocalAddress();

    public boolean isLayered();

    public int getHopCount();

    public HttpHost getTargetHost();

    public boolean isTunnelled();

    public TunnelType getTunnelType();

    public HttpHost getHopTarget(int var1);

    public LayerType getLayerType();

    public boolean isSecure();
}
