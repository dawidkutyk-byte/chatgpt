/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.params.CoreProtocolPNames
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HTTP
 *  org.apache.http.util.Args
 */
package org.apache.http.params;

import java.nio.charset.CodingErrorAction;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;

@Deprecated
public final class HttpProtocolParams
implements CoreProtocolPNames {
    public static String getHttpElementCharset(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        String charset = (String)params.getParameter("http.protocol.element-charset");
        if (charset != null) return charset;
        charset = HTTP.DEF_PROTOCOL_CHARSET.name();
        return charset;
    }

    public static void setUserAgent(HttpParams params, String useragent) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setParameter("http.useragent", (Object)useragent);
    }

    public static void setMalformedInputAction(HttpParams params, CodingErrorAction action) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setParameter("http.malformed.input.action", (Object)action);
    }

    public static void setUnmappableInputAction(HttpParams params, CodingErrorAction action) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setParameter("http.unmappable.input.action", (Object)action);
    }

    public static boolean useExpectContinue(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return params.getBooleanParameter("http.protocol.expect-continue", false);
    }

    public static CodingErrorAction getMalformedInputAction(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        Object param = params.getParameter("http.malformed.input.action");
        if (param != null) return (CodingErrorAction)param;
        return CodingErrorAction.REPORT;
    }

    public static String getContentCharset(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        String charset = (String)params.getParameter("http.protocol.content-charset");
        if (charset != null) return charset;
        charset = HTTP.DEF_CONTENT_CHARSET.name();
        return charset;
    }

    public static void setVersion(HttpParams params, ProtocolVersion version) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setParameter("http.protocol.version", (Object)version);
    }

    public static void setUseExpectContinue(HttpParams params, boolean b) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setBooleanParameter("http.protocol.expect-continue", b);
    }

    public static void setHttpElementCharset(HttpParams params, String charset) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setParameter("http.protocol.element-charset", (Object)charset);
    }

    public static CodingErrorAction getUnmappableInputAction(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        Object param = params.getParameter("http.unmappable.input.action");
        if (param != null) return (CodingErrorAction)param;
        return CodingErrorAction.REPORT;
    }

    private HttpProtocolParams() {
    }

    public static void setContentCharset(HttpParams params, String charset) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        params.setParameter("http.protocol.content-charset", (Object)charset);
    }

    public static ProtocolVersion getVersion(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        Object param = params.getParameter("http.protocol.version");
        if (param != null) return (ProtocolVersion)param;
        return HttpVersion.HTTP_1_1;
    }

    public static String getUserAgent(HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP parameters");
        return (String)params.getParameter("http.useragent");
    }
}
