/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderIterator
 *  org.apache.http.message.BasicHeader
 *  org.apache.http.message.BasicListHeaderIterator
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicListHeaderIterator;
import org.apache.http.util.CharArrayBuffer;

public class HeaderGroup
implements Serializable,
Cloneable {
    private static final Header[] EMPTY = new Header[0];
    private final List<Header> headers = new ArrayList<Header>(16);
    private static final long serialVersionUID = 2608834160639271617L;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Header getLastHeader(String name) {
        int i = this.headers.size() - 1;
        while (i >= 0) {
            Header header = this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
            --i;
        }
        return null;
    }

    public Header getCondensedHeader(String name) {
        Header[] hdrs = this.getHeaders(name);
        if (hdrs.length == 0) {
            return null;
        }
        if (hdrs.length == 1) {
            return hdrs[0];
        }
        CharArrayBuffer valueBuffer = new CharArrayBuffer(128);
        valueBuffer.append(hdrs[0].getValue());
        int i = 1;
        while (i < hdrs.length) {
            valueBuffer.append(", ");
            valueBuffer.append(hdrs[i].getValue());
            ++i;
        }
        return new BasicHeader(name.toLowerCase(Locale.ROOT), valueBuffer.toString());
    }

    public HeaderIterator iterator() {
        return new BasicListHeaderIterator(this.headers, null);
    }

    public void updateHeader(Header header) {
        if (header == null) {
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.headers.size()) {
                this.headers.add(header);
                return;
            }
            Header current = this.headers.get(i);
            if (current.getName().equalsIgnoreCase(header.getName())) {
                this.headers.set(i, header);
                return;
            }
            ++i;
        }
    }

    public Header[] getAllHeaders() {
        return this.headers.toArray(new Header[this.headers.size()]);
    }

    public void setHeaders(Header[] headers) {
        this.clear();
        if (headers == null) {
            return;
        }
        Collections.addAll(this.headers, headers);
    }

    public void clear() {
        this.headers.clear();
    }

    public HeaderGroup copy() {
        HeaderGroup clone = new HeaderGroup();
        clone.headers.addAll(this.headers);
        return clone;
    }

    public void removeHeader(Header header) {
        if (header == null) {
            return;
        }
        this.headers.remove(header);
    }

    public String toString() {
        return this.headers.toString();
    }

    public void addHeader(Header header) {
        if (header == null) {
            return;
        }
        this.headers.add(header);
    }

    public Header getFirstHeader(String name) {
        int i = 0;
        while (i < this.headers.size()) {
            Header header = this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
            ++i;
        }
        return null;
    }

    public Header[] getHeaders(String name) {
        ArrayList<Header> headersFound = null;
        for (int i = 0; i < this.headers.size(); ++i) {
            Header header = this.headers.get(i);
            if (!header.getName().equalsIgnoreCase(name)) continue;
            if (headersFound == null) {
                headersFound = new ArrayList<Header>();
            }
            headersFound.add(header);
        }
        return headersFound != null ? headersFound.toArray(new Header[headersFound.size()]) : EMPTY;
    }

    public HeaderIterator iterator(String name) {
        return new BasicListHeaderIterator(this.headers, name);
    }

    public boolean containsHeader(String name) {
        int i = 0;
        while (i < this.headers.size()) {
            Header header = this.headers.get(i);
            if (header.getName().equalsIgnoreCase(name)) {
                return true;
            }
            ++i;
        }
        return false;
    }
}
