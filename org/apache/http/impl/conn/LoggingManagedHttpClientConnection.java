/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.http.Header
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.config.MessageConstraints
 *  org.apache.http.entity.ContentLengthStrategy
 *  org.apache.http.impl.conn.DefaultManagedHttpClientConnection
 *  org.apache.http.impl.conn.LoggingInputStream
 *  org.apache.http.impl.conn.LoggingOutputStream
 *  org.apache.http.impl.conn.Wire
 *  org.apache.http.io.HttpMessageParserFactory
 *  org.apache.http.io.HttpMessageWriterFactory
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.config.MessageConstraints;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.conn.DefaultManagedHttpClientConnection;
import org.apache.http.impl.conn.LoggingInputStream;
import org.apache.http.impl.conn.LoggingOutputStream;
import org.apache.http.impl.conn.Wire;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriterFactory;

class LoggingManagedHttpClientConnection
extends DefaultManagedHttpClientConnection {
    private final Log headerLog;
    private final Log log;
    private final Wire wire;

    public void close() throws IOException {
        if (!super.isOpen()) return;
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)(this.getId() + ": Close connection"));
        }
        super.close();
    }

    public LoggingManagedHttpClientConnection(String id, Log log, Log headerLog, Log wireLog, int bufferSize, int fragmentSizeHint, CharsetDecoder charDecoder, CharsetEncoder charEncoder, MessageConstraints constraints, ContentLengthStrategy incomingContentStrategy, ContentLengthStrategy outgoingContentStrategy, HttpMessageWriterFactory<HttpRequest> requestWriterFactory, HttpMessageParserFactory<HttpResponse> responseParserFactory) {
        super(id, bufferSize, fragmentSizeHint, charDecoder, charEncoder, constraints, incomingContentStrategy, outgoingContentStrategy, requestWriterFactory, responseParserFactory);
        this.log = log;
        this.headerLog = headerLog;
        this.wire = new Wire(wireLog, id);
    }

    protected OutputStream getSocketOutputStream(Socket socket) throws IOException {
        OutputStream out = super.getSocketOutputStream(socket);
        if (!this.wire.enabled()) return out;
        out = new LoggingOutputStream(out, this.wire);
        return out;
    }

    protected InputStream getSocketInputStream(Socket socket) throws IOException {
        InputStream in = super.getSocketInputStream(socket);
        if (!this.wire.enabled()) return in;
        in = new LoggingInputStream(in, this.wire);
        return in;
    }

    public void shutdown() throws IOException {
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)(this.getId() + ": Shutdown connection"));
        }
        super.shutdown();
    }

    public void setSocketTimeout(int timeout) {
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)(this.getId() + ": set socket timeout to " + timeout));
        }
        super.setSocketTimeout(timeout);
    }

    protected void onResponseReceived(HttpResponse response) {
        Header[] headers;
        if (response == null) return;
        if (!this.headerLog.isDebugEnabled()) return;
        this.headerLog.debug((Object)(this.getId() + " << " + response.getStatusLine().toString()));
        Header[] arr$ = headers = response.getAllHeaders();
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            Header header = arr$[i$];
            this.headerLog.debug((Object)(this.getId() + " << " + header.toString()));
            ++i$;
        }
    }

    protected void onRequestSubmitted(HttpRequest request) {
        Header[] headers;
        if (request == null) return;
        if (!this.headerLog.isDebugEnabled()) return;
        this.headerLog.debug((Object)(this.getId() + " >> " + request.getRequestLine().toString()));
        Header[] arr$ = headers = request.getAllHeaders();
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            Header header = arr$[i$];
            this.headerLog.debug((Object)(this.getId() + " >> " + header.toString()));
            ++i$;
        }
    }
}
