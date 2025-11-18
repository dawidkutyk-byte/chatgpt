/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.ReasonPhraseCatalog
 *  org.apache.http.StatusLine
 *  org.apache.http.message.AbstractHttpMessage
 *  org.apache.http.message.BasicStatusLine
 *  org.apache.http.util.Args
 *  org.apache.http.util.TextUtils
 */
package org.apache.http.message;

import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.util.Args;
import org.apache.http.util.TextUtils;

public class BasicHttpResponse
extends AbstractHttpMessage
implements HttpResponse {
    private int code;
    private final ReasonPhraseCatalog reasonCatalog;
    private String reasonPhrase;
    private Locale locale;
    private StatusLine statusline;
    private HttpEntity entity;
    private ProtocolVersion ver;

    public void setStatusLine(ProtocolVersion ver, int code) {
        Args.notNegative((int)code, (String)"Status code");
        this.statusline = null;
        this.ver = ver;
        this.code = code;
        this.reasonPhrase = null;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setReasonPhrase(String reason) {
        this.statusline = null;
        this.reasonPhrase = TextUtils.isBlank((CharSequence)reason) ? null : reason;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.ver;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getStatusLine());
        sb.append(' ');
        sb.append(this.headergroup);
        if (this.entity == null) return sb.toString();
        sb.append(' ');
        sb.append(this.entity);
        return sb.toString();
    }

    public void setLocale(Locale locale) {
        this.locale = (Locale)Args.notNull((Object)locale, (String)"Locale");
        this.statusline = null;
    }

    public BasicHttpResponse(StatusLine statusline, ReasonPhraseCatalog catalog, Locale locale) {
        this.statusline = (StatusLine)Args.notNull((Object)statusline, (String)"Status line");
        this.ver = statusline.getProtocolVersion();
        this.code = statusline.getStatusCode();
        this.reasonPhrase = statusline.getReasonPhrase();
        this.reasonCatalog = catalog;
        this.locale = locale;
    }

    public BasicHttpResponse(ProtocolVersion ver, int code, String reason) {
        Args.notNegative((int)code, (String)"Status code");
        this.statusline = null;
        this.ver = ver;
        this.code = code;
        this.reasonPhrase = reason;
        this.reasonCatalog = null;
        this.locale = null;
    }

    protected String getReason(int code) {
        return this.reasonCatalog != null ? this.reasonCatalog.getReason(code, this.locale != null ? this.locale : Locale.getDefault()) : null;
    }

    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }

    public void setStatusLine(StatusLine statusline) {
        this.statusline = (StatusLine)Args.notNull((Object)statusline, (String)"Status line");
        this.ver = statusline.getProtocolVersion();
        this.code = statusline.getStatusCode();
        this.reasonPhrase = statusline.getReasonPhrase();
    }

    public void setStatusLine(ProtocolVersion ver, int code, String reason) {
        Args.notNegative((int)code, (String)"Status code");
        this.statusline = null;
        this.ver = ver;
        this.code = code;
        this.reasonPhrase = reason;
    }

    public StatusLine getStatusLine() {
        if (this.statusline != null) return this.statusline;
        this.statusline = new BasicStatusLine((ProtocolVersion)(this.ver != null ? this.ver : HttpVersion.HTTP_1_1), this.code, this.reasonPhrase != null ? this.reasonPhrase : this.getReason(this.code));
        return this.statusline;
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public void setStatusCode(int code) {
        Args.notNegative((int)code, (String)"Status code");
        this.statusline = null;
        this.code = code;
        this.reasonPhrase = null;
    }

    public BasicHttpResponse(StatusLine statusline) {
        this.statusline = (StatusLine)Args.notNull((Object)statusline, (String)"Status line");
        this.ver = statusline.getProtocolVersion();
        this.code = statusline.getStatusCode();
        this.reasonPhrase = statusline.getReasonPhrase();
        this.reasonCatalog = null;
        this.locale = null;
    }
}
