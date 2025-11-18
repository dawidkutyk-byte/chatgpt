/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.ProtocolException
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.entity.LaxContentLengthStrategy
 */
package org.apache.http.impl.entity;

import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ProtocolException;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.entity.LaxContentLengthStrategy;

@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public class DisallowIdentityContentLengthStrategy
implements ContentLengthStrategy {
    public static final DisallowIdentityContentLengthStrategy INSTANCE = new DisallowIdentityContentLengthStrategy((ContentLengthStrategy)new LaxContentLengthStrategy(0));
    private final ContentLengthStrategy contentLengthStrategy;

    public DisallowIdentityContentLengthStrategy(ContentLengthStrategy contentLengthStrategy) {
        this.contentLengthStrategy = contentLengthStrategy;
    }

    public long determineLength(HttpMessage message) throws HttpException {
        long result = this.contentLengthStrategy.determineLength(message);
        if (result != -1L) return result;
        throw new ProtocolException("Identity transfer encoding cannot be used");
    }
}
