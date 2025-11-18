/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 */
package org.apache.http.client.params;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
public class HttpClientParams {
    public static void setRedirecting(HttpParams params, boolean value) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setBooleanParameter("http.protocol.handle-redirects", value);
    }

    public static void setConnectionManagerTimeout(HttpParams params, long timeout) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setLongParameter("http.conn-manager.timeout", timeout);
    }

    public static long getConnectionManagerTimeout(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        Long timeout = (Long)params.getParameter("http.conn-manager.timeout");
        if (timeout == null) return HttpConnectionParams.getConnectionTimeout((HttpParams)params);
        return timeout;
    }

    public static boolean isAuthenticating(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getBooleanParameter("http.protocol.handle-authentication", true);
    }

    public static String getCookiePolicy(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        String cookiePolicy = (String)params.getParameter("http.protocol.cookie-policy");
        if (cookiePolicy != null) return cookiePolicy;
        return "best-match";
    }

    public static void setAuthenticating(HttpParams params, boolean value) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setBooleanParameter("http.protocol.handle-authentication", value);
    }

    private HttpClientParams() {
    }

    public static boolean isRedirecting(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getBooleanParameter("http.protocol.handle-redirects", true);
    }

    public static void setCookiePolicy(HttpParams params, String cookiePolicy) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setParameter("http.protocol.cookie-policy", (Object)cookiePolicy);
    }
}
