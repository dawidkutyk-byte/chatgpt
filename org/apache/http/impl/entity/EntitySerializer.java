/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.io.ChunkedOutputStream
 *  org.apache.http.impl.io.ContentLengthOutputStream
 *  org.apache.http.impl.io.IdentityOutputStream
 *  org.apache.http.io.SessionOutputBuffer
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.entity;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.io.ChunkedOutputStream;
import org.apache.http.impl.io.ContentLengthOutputStream;
import org.apache.http.impl.io.IdentityOutputStream;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public class EntitySerializer {
    private final ContentLengthStrategy lenStrategy;

    public void serialize(SessionOutputBuffer outbuffer, HttpMessage message, HttpEntity entity) throws IOException, HttpException {
        Args.notNull((Object)outbuffer, (String)"Session output buffer");
        Args.notNull((Object)message, (String)"HTTP message");
        Args.notNull((Object)entity, (String)"HTTP entity");
        OutputStream outStream = this.doSerialize(outbuffer, message);
        entity.writeTo(outStream);
        outStream.close();
    }

    public EntitySerializer(ContentLengthStrategy lenStrategy) {
        this.lenStrategy = (ContentLengthStrategy)Args.notNull((Object)lenStrategy, (String)"Content length strategy");
    }

    protected OutputStream doSerialize(SessionOutputBuffer outbuffer, HttpMessage message) throws IOException, HttpException {
        long len = this.lenStrategy.determineLength(message);
        if (len == -2L) {
            return new ChunkedOutputStream(outbuffer);
        }
        if (len != -1L) return new ContentLengthOutputStream(outbuffer, len);
        return new IdentityOutputStream(outbuffer);
    }
}
