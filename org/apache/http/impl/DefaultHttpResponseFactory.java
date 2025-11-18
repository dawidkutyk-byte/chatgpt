/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpResponseFactory
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.ReasonPhraseCatalog
 *  org.apache.http.StatusLine
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.impl.EnglishReasonPhraseCatalog
 *  org.apache.http.message.BasicHttpResponse
 *  org.apache.http.message.BasicStatusLine
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.impl;

import java.util.Locale;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.impl.EnglishReasonPhraseCatalog;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public class DefaultHttpResponseFactory
implements HttpResponseFactory {
    protected final ReasonPhraseCatalog reasonCatalog;
    public static final DefaultHttpResponseFactory INSTANCE = new DefaultHttpResponseFactory();

    public HttpResponse newHttpResponse(ProtocolVersion ver, int status, HttpContext context) {
        Args.notNull((Object)ver, (String)"HTTP version");
        Locale loc = this.determineLocale(context);
        String reason = this.reasonCatalog.getReason(status, loc);
        BasicStatusLine statusline = new BasicStatusLine(ver, status, reason);
        return new BasicHttpResponse((StatusLine)statusline, this.reasonCatalog, loc);
    }

    protected Locale determineLocale(HttpContext context) {
        return Locale.getDefault();
    }

    public DefaultHttpResponseFactory(ReasonPhraseCatalog catalog) {
        this.reasonCatalog = (ReasonPhraseCatalog)Args.notNull((Object)catalog, (String)"Reason phrase catalog");
    }

    public DefaultHttpResponseFactory() {
        this((ReasonPhraseCatalog)EnglishReasonPhraseCatalog.INSTANCE);
    }

    public HttpResponse newHttpResponse(StatusLine statusline, HttpContext context) {
        Args.notNull((Object)statusline, (String)"Status line");
        return new BasicHttpResponse(statusline, this.reasonCatalog, this.determineLocale(context));
    }
}
