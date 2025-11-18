/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.entity.BasicHttpEntity
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.io.ChunkedInputStream
 *  org.apache.http.impl.io.ContentLengthInputStream
 *  org.apache.http.impl.io.IdentityInputStream
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.entity;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.io.ChunkedInputStream;
import org.apache.http.impl.io.ContentLengthInputStream;
import org.apache.http.impl.io.IdentityInputStream;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public class EntityDeserializer {
    private final ContentLengthStrategy lenStrategy;

    public HttpEntity deserialize(SessionInputBuffer inBuffer, HttpMessage message) throws HttpException, IOException {
        Args.notNull((Object)inBuffer, (String)"Session input buffer");
        Args.notNull((Object)message, (String)"HTTP message");
        return this.doDeserialize(inBuffer, message);
    }

    public EntityDeserializer(ContentLengthStrategy lenStrategy) {
        this.lenStrategy = (ContentLengthStrategy)Args.notNull((Object)lenStrategy, (String)"Content length strategy");
    }

    protected BasicHttpEntity doDeserialize(SessionInputBuffer inBuffer, HttpMessage message) throws IOException, HttpException {
        Header contentEncodingHeader;
        BasicHttpEntity entity = new BasicHttpEntity();
        long len = this.lenStrategy.determineLength(message);
        if (len == -2L) {
            entity.setChunked(true);
            entity.setContentLength(-1L);
            entity.setContent((InputStream)new ChunkedInputStream(inBuffer));
        } else if (len == -1L) {
            entity.setChunked(false);
            entity.setContentLength(-1L);
            entity.setContent((InputStream)new IdentityInputStream(inBuffer));
        } else {
            entity.setChunked(false);
            entity.setContentLength(len);
            entity.setContent((InputStream)new ContentLengthInputStream(inBuffer, len));
        }
        Header contentTypeHeader = message.getFirstHeader("Content-Type");
        if (contentTypeHeader != null) {
            entity.setContentType(contentTypeHeader);
        }
        if ((contentEncodingHeader = message.getFirstHeader("Content-Encoding")) == null) return entity;
        entity.setContentEncoding(contentEncodingHeader);
        return entity;
    }
}
