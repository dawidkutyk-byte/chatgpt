/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderIterator
 *  org.apache.http.HttpException
 *  org.apache.http.HttpMessage
 *  org.apache.http.io.HttpMessageWriter
 *  org.apache.http.io.SessionOutputBuffer
 *  org.apache.http.message.BasicLineFormatter
 *  org.apache.http.message.LineFormatter
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.BasicLineFormatter;
import org.apache.http.message.LineFormatter;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractMessageWriter<T extends HttpMessage>
implements HttpMessageWriter<T> {
    protected final LineFormatter lineFormatter;
    protected final SessionOutputBuffer sessionBuffer;
    protected final CharArrayBuffer lineBuf;

    public AbstractMessageWriter(SessionOutputBuffer buffer, LineFormatter formatter) {
        this.sessionBuffer = (SessionOutputBuffer)Args.notNull((Object)buffer, (String)"Session input buffer");
        this.lineFormatter = formatter != null ? formatter : BasicLineFormatter.INSTANCE;
        this.lineBuf = new CharArrayBuffer(128);
    }

    @Deprecated
    public AbstractMessageWriter(SessionOutputBuffer buffer, LineFormatter formatter, HttpParams params) {
        Args.notNull((Object)buffer, (String)"Session input buffer");
        this.sessionBuffer = buffer;
        this.lineBuf = new CharArrayBuffer(128);
        this.lineFormatter = formatter != null ? formatter : BasicLineFormatter.INSTANCE;
    }

    protected abstract void writeHeadLine(T var1) throws IOException;

    public void write(T message) throws IOException, HttpException {
        Args.notNull(message, (String)"HTTP message");
        this.writeHeadLine(message);
        HeaderIterator it = message.headerIterator();
        while (true) {
            if (!it.hasNext()) {
                this.lineBuf.clear();
                this.sessionBuffer.writeLine(this.lineBuf);
                return;
            }
            Header header = it.nextHeader();
            this.sessionBuffer.writeLine(this.lineFormatter.formatHeader(this.lineBuf, header));
        }
    }
}
