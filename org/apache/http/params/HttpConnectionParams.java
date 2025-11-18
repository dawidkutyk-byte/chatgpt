/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.params.CoreConnectionPNames
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 */
package org.apache.http.params;

import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
public final class HttpConnectionParams
implements CoreConnectionPNames {
    private HttpConnectionParams() {
    }

    public static void setSoKeepalive(HttpParams params, boolean enableKeepalive) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setBooleanParameter("http.socket.keepalive", enableKeepalive);
    }

    public static void setSoReuseaddr(HttpParams params, boolean reuseaddr) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setBooleanParameter("http.socket.reuseaddr", reuseaddr);
    }

    public static boolean getSoKeepalive(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getBooleanParameter("http.socket.keepalive", false);
    }

    public static boolean getTcpNoDelay(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getBooleanParameter("http.tcp.nodelay", true);
    }

    public static void setLinger(HttpParams params, int value) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setIntParameter("http.socket.linger", value);
    }

    public static void setTcpNoDelay(HttpParams params, boolean value) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setBooleanParameter("http.tcp.nodelay", value);
    }

    public static boolean isStaleCheckingEnabled(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getBooleanParameter("http.connection.stalecheck", true);
    }

    public static void setStaleCheckingEnabled(HttpParams params, boolean value) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setBooleanParameter("http.connection.stalecheck", value);
    }

    public static int getSoTimeout(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getIntParameter("http.socket.timeout", 0);
    }

    public static int getConnectionTimeout(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getIntParameter("http.connection.timeout", 0);
    }

    public static boolean getSoReuseaddr(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getBooleanParameter("http.socket.reuseaddr", false);
    }

    public static int getLinger(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getIntParameter("http.socket.linger", -1);
    }

    public static void setSoTimeout(HttpParams params, int timeout) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setIntParameter("http.socket.timeout", timeout);
    }

    public static void setSocketBufferSize(HttpParams params, int size) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setIntParameter("http.socket.buffer-size", size);
    }

    public static int getSocketBufferSize(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getIntParameter("http.socket.buffer-size", -1);
    }

    public static void setConnectionTimeout(HttpParams params, int timeout) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setIntParameter("http.connection.timeout", timeout);
    }
}
