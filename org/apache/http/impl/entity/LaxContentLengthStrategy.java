/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderElement
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.ParseException
 *  org.apache.http.ProtocolException
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class LaxContentLengthStrategy
implements ContentLengthStrategy {
    private final int implicitLen;
    public static final LaxContentLengthStrategy INSTANCE = new LaxContentLengthStrategy();

    public LaxContentLengthStrategy(int implicitLen) {
        this.implicitLen = implicitLen;
    }

    public long determineLength(HttpMessage message) throws HttpException {
        Args.notNull((Object)message, (String)"HTTP message");
        Header transferEncodingHeader = message.getFirstHeader("Transfer-Encoding");
        if (transferEncodingHeader != null) {
            HeaderElement[] encodings;
            try {
                encodings = transferEncodingHeader.getElements();
            }
            catch (ParseException px) {
                throw new ProtocolException("Invalid Transfer-Encoding header value: " + transferEncodingHeader, (Throwable)px);
            }
            int len = encodings.length;
            if ("identity".equalsIgnoreCase(transferEncodingHeader.getValue())) {
                return -1L;
            }
            if (len <= 0) return -1L;
            if (!"chunked".equalsIgnoreCase(encodings[len - 1].getName())) return -1L;
            return -2L;
        }
        Header contentLengthHeader = message.getFirstHeader("Content-Length");
        if (contentLengthHeader == null) return this.implicitLen;
        long contentLen = -1L;
        Header[] headers = message.getHeaders("Content-Length");
        for (int i = headers.length - 1; i >= 0; --i) {
            Header header = headers[i];
            try {
                contentLen = Long.parseLong(header.getValue());
                break;
            }
            catch (NumberFormatException ignore) {
                continue;
            }
        }
        return contentLen >= 0L ? contentLen : -1L;
    }

    public LaxContentLengthStrategy() {
        this(-1);
    }
}
