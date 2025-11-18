/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpHost
 *  org.apache.http.conn.ConnectionReleaseTrigger
 *  org.apache.http.conn.HttpRoutedConnection
 *  org.apache.http.conn.ManagedHttpClientConnection
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.conn.HttpRoutedConnection;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Deprecated
public interface ManagedClientConnection
extends ManagedHttpClientConnection,
ConnectionReleaseTrigger,
HttpRoutedConnection {
    public boolean isMarkedReusable();

    public void unmarkReusable();

    public void layerProtocol(HttpContext var1, HttpParams var2) throws IOException;

    public void tunnelProxy(HttpHost var1, boolean var2, HttpParams var3) throws IOException;

    public SSLSession getSSLSession();

    public boolean isSecure();

    public void setState(Object var1);

    public HttpRoute getRoute();

    public void tunnelTarget(boolean var1, HttpParams var2) throws IOException;

    public void setIdleDuration(long var1, TimeUnit var3);

    public void open(HttpRoute var1, HttpContext var2, HttpParams var3) throws IOException;

    public Object getState();

    public void markReusable();
}
