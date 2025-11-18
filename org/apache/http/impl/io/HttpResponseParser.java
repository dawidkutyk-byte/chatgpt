/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.HttpResponseFactory
 *  org.apache.http.NoHttpResponseException
 *  org.apache.http.ParseException
 *  org.apache.http.StatusLine
 *  org.apache.http.impl.io.AbstractMessageParser
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.message.LineParser
 *  org.apache.http.message.ParserCursor
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponseFactory;
import org.apache.http.NoHttpResponseException;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.impl.io.AbstractMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

@Deprecated
public class HttpResponseParser
extends AbstractMessageParser<HttpMessage> {
    private final CharArrayBuffer lineBuf;
    private final HttpResponseFactory responseFactory;

    protected HttpMessage parseHead(SessionInputBuffer sessionBuffer) throws ParseException, IOException, HttpException {
        this.lineBuf.clear();
        int readLen = sessionBuffer.readLine(this.lineBuf);
        if (readLen == -1) {
            throw new NoHttpResponseException("The target server failed to respond");
        }
        ParserCursor cursor = new ParserCursor(0, this.lineBuf.length());
        StatusLine statusline = this.lineParser.parseStatusLine(this.lineBuf, cursor);
        return this.responseFactory.newHttpResponse(statusline, null);
    }

    public HttpResponseParser(SessionInputBuffer buffer, LineParser parser, HttpResponseFactory responseFactory, HttpParams params) {
        super(buffer, parser, params);
        this.responseFactory = (HttpResponseFactory)Args.notNull((Object)responseFactory, (String)"Response factory");
        this.lineBuf = new CharArrayBuffer(128);
    }
}
