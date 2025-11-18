/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.MessageConstraintException
 *  org.apache.http.ParseException
 *  org.apache.http.ProtocolException
 *  org.apache.http.config.MessageConstraints
 *  org.apache.http.io.HttpMessageParser
 *  org.apache.http.io.SessionInputBuffer
 *  org.apache.http.message.BasicLineParser
 *  org.apache.http.message.LineParser
 *  org.apache.http.params.HttpParamConfig
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.MessageConstraintException;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.config.MessageConstraints;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.params.HttpParamConfig;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractMessageParser<T extends HttpMessage>
implements HttpMessageParser<T> {
    private T message;
    private static final int HEAD_LINE = 0;
    private static final int HEADERS = 1;
    private int state;
    protected final LineParser lineParser;
    private final SessionInputBuffer sessionBuffer;
    private final List<CharArrayBuffer> headerLines;
    private final MessageConstraints messageConstraints;

    @Deprecated
    public AbstractMessageParser(SessionInputBuffer buffer, LineParser parser, HttpParams params) {
        Args.notNull((Object)buffer, (String)"Session input buffer");
        Args.notNull((Object)params, (String)"HTTP parameters");
        this.sessionBuffer = buffer;
        this.messageConstraints = HttpParamConfig.getMessageConstraints((HttpParams)params);
        this.lineParser = parser != null ? parser : BasicLineParser.INSTANCE;
        this.headerLines = new ArrayList<CharArrayBuffer>();
        this.state = 0;
    }

    protected abstract T parseHead(SessionInputBuffer var1) throws HttpException, ParseException, IOException;

    public AbstractMessageParser(SessionInputBuffer buffer, LineParser lineParser, MessageConstraints constraints) {
        this.sessionBuffer = (SessionInputBuffer)Args.notNull((Object)buffer, (String)"Session input buffer");
        this.lineParser = lineParser != null ? lineParser : BasicLineParser.INSTANCE;
        this.messageConstraints = constraints != null ? constraints : MessageConstraints.DEFAULT;
        this.headerLines = new ArrayList<CharArrayBuffer>();
        this.state = 0;
    }

    public static Header[] parseHeaders(SessionInputBuffer inBuffer, int maxHeaderCount, int maxLineLen, LineParser parser) throws HttpException, IOException {
        ArrayList<CharArrayBuffer> headerLines = new ArrayList<CharArrayBuffer>();
        return AbstractMessageParser.parseHeaders(inBuffer, maxHeaderCount, maxLineLen, (LineParser)(parser != null ? parser : BasicLineParser.INSTANCE), headerLines);
    }

    public T parse() throws IOException, HttpException {
        int st = this.state;
        switch (st) {
            case 0: {
                try {
                    this.message = this.parseHead(this.sessionBuffer);
                }
                catch (ParseException px) {
                    throw new ProtocolException(px.getMessage(), (Throwable)px);
                }
                this.state = 1;
            }
            case 1: {
                Header[] headers = AbstractMessageParser.parseHeaders(this.sessionBuffer, this.messageConstraints.getMaxHeaderCount(), this.messageConstraints.getMaxLineLength(), this.lineParser, this.headerLines);
                this.message.setHeaders(headers);
                T result = this.message;
                this.message = null;
                this.headerLines.clear();
                this.state = 0;
                return result;
            }
        }
        throw new IllegalStateException("Inconsistent parser state");
    }

    public static Header[] parseHeaders(SessionInputBuffer inBuffer, int maxHeaderCount, int maxLineLen, LineParser parser, List<CharArrayBuffer> headerLines) throws HttpException, IOException {
        int i;
        block10: {
            Args.notNull((Object)inBuffer, (String)"Session input buffer");
            Args.notNull((Object)parser, (String)"Line parser");
            Args.notNull(headerLines, (String)"Header line list");
            CharArrayBuffer current = null;
            CharArrayBuffer previous = null;
            do {
                char ch;
                if (current == null) {
                    current = new CharArrayBuffer(64);
                } else {
                    current.clear();
                }
                int readLen = inBuffer.readLine(current);
                if (readLen == -1 || current.length() < 1) break block10;
                if ((current.charAt(0) == ' ' || current.charAt(0) == '\t') && previous != null) {
                } else {
                    headerLines.add(current);
                    previous = current;
                    current = null;
                    continue;
                }
                for (i = 0; i < current.length() && ((ch = current.charAt(i)) == ' ' || ch == '\t'); ++i) {
                }
                if (maxLineLen > 0 && previous.length() + 1 + current.length() - i > maxLineLen) {
                    throw new MessageConstraintException("Maximum line length limit exceeded");
                }
                previous.append(' ');
                previous.append(current, i, current.length() - i);
            } while (maxHeaderCount <= 0 || headerLines.size() < maxHeaderCount);
            throw new MessageConstraintException("Maximum header count exceeded");
        }
        Header[] headers = new Header[headerLines.size()];
        i = 0;
        while (i < headerLines.size()) {
            CharArrayBuffer buffer = headerLines.get(i);
            try {
                headers[i] = parser.parseHeader(buffer);
            }
            catch (ParseException ex) {
                throw new ProtocolException(ex.getMessage());
            }
            ++i;
        }
        return headers;
    }
}
