/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderIterator
 *  org.apache.http.HttpMessage
 *  org.apache.http.message.BasicHeader
 *  org.apache.http.message.HeaderGroup
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 */
package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpMessage;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

public abstract class AbstractHttpMessage
implements HttpMessage {
    protected HeaderGroup headergroup = new HeaderGroup();
    @Deprecated
    protected HttpParams params;

    public HeaderIterator headerIterator() {
        return this.headergroup.iterator();
    }

    @Deprecated
    public void setParams(HttpParams params) {
        this.params = (HttpParams)Args.notNull((Object)params, (String)"HTTP parameters");
    }

    public HeaderIterator headerIterator(String name) {
        return this.headergroup.iterator(name);
    }

    public boolean containsHeader(String name) {
        return this.headergroup.containsHeader(name);
    }

    public void setHeaders(Header[] headers) {
        this.headergroup.setHeaders(headers);
    }

    protected AbstractHttpMessage() {
        this(null);
    }

    public void addHeader(Header header) {
        this.headergroup.addHeader(header);
    }

    @Deprecated
    protected AbstractHttpMessage(HttpParams params) {
        this.params = params;
    }

    public void removeHeaders(String name) {
        if (name == null) {
            return;
        }
        HeaderIterator i = this.headergroup.iterator();
        while (i.hasNext()) {
            Header header = i.nextHeader();
            if (!name.equalsIgnoreCase(header.getName())) continue;
            i.remove();
        }
    }

    public Header getFirstHeader(String name) {
        return this.headergroup.getFirstHeader(name);
    }

    public Header[] getHeaders(String name) {
        return this.headergroup.getHeaders(name);
    }

    public Header[] getAllHeaders() {
        return this.headergroup.getAllHeaders();
    }

    public void addHeader(String name, String value) {
        Args.notNull((Object)name, (String)"Header name");
        this.headergroup.addHeader((Header)new BasicHeader(name, value));
    }

    public void setHeader(Header header) {
        this.headergroup.updateHeader(header);
    }

    @Deprecated
    public HttpParams getParams() {
        if (this.params != null) return this.params;
        this.params = new BasicHttpParams();
        return this.params;
    }

    public void removeHeader(Header header) {
        this.headergroup.removeHeader(header);
    }

    public Header getLastHeader(String name) {
        return this.headergroup.getLastHeader(name);
    }

    public void setHeader(String name, String value) {
        Args.notNull((Object)name, (String)"Header name");
        this.headergroup.updateHeader((Header)new BasicHeader(name, value));
    }
}
