/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpInetConnection
 *  org.apache.http.conn.routing.HttpRoute
 */
package org.apache.http.conn;

import javax.net.ssl.SSLSession;
import org.apache.http.HttpInetConnection;
import org.apache.http.conn.routing.HttpRoute;

@Deprecated
public interface HttpRoutedConnection
extends HttpInetConnection {
    public SSLSession getSSLSession();

    public HttpRoute getRoute();

    public boolean isSecure();
}
