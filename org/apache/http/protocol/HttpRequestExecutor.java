/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolException
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.protocol.HttpProcessor
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class HttpRequestExecutor {
    private final int waitForContinue;
    public static final int DEFAULT_WAIT_FOR_CONTINUE = 3000;

    public HttpRequestExecutor(int waitForContinue) {
        this.waitForContinue = Args.positive((int)waitForContinue, (String)"Wait for continue time");
    }

    protected HttpResponse doReceiveResponse(HttpRequest request, HttpClientConnection conn, HttpContext context) throws HttpException, IOException {
        Args.notNull((Object)request, (String)"HTTP request");
        Args.notNull((Object)conn, (String)"Client connection");
        Args.notNull((Object)context, (String)"HTTP context");
        HttpResponse response = null;
        int statusCode = 0;
        while (true) {
            if (response != null) {
                if (statusCode >= 200) return response;
            }
            if ((statusCode = (response = conn.receiveResponseHeader()).getStatusLine().getStatusCode()) < 100) {
                throw new ProtocolException("Invalid response: " + response.getStatusLine());
            }
            if (!this.canResponseHaveBody(request, response)) continue;
            conn.receiveResponseEntity(response);
        }
    }

    protected HttpResponse doSendRequest(HttpRequest request, HttpClientConnection conn, HttpContext context) throws IOException, HttpException {
        Args.notNull((Object)request, (String)"HTTP request");
        Args.notNull((Object)conn, (String)"Client connection");
        Args.notNull((Object)context, (String)"HTTP context");
        HttpResponse response = null;
        context.setAttribute("http.connection", (Object)conn);
        context.setAttribute("http.request_sent", (Object)Boolean.FALSE);
        conn.sendRequestHeader(request);
        if (request instanceof HttpEntityEnclosingRequest) {
            boolean sendentity = true;
            ProtocolVersion ver = request.getRequestLine().getProtocolVersion();
            if (((HttpEntityEnclosingRequest)request).expectContinue() && !ver.lessEquals((ProtocolVersion)HttpVersion.HTTP_1_0)) {
                conn.flush();
                if (conn.isResponseAvailable(this.waitForContinue)) {
                    int status;
                    response = conn.receiveResponseHeader();
                    if (this.canResponseHaveBody(request, response)) {
                        conn.receiveResponseEntity(response);
                    }
                    if ((status = response.getStatusLine().getStatusCode()) < 200) {
                        if (status != 100) {
                            throw new ProtocolException("Unexpected response: " + response.getStatusLine());
                        }
                        response = null;
                    } else {
                        sendentity = false;
                    }
                }
            }
            if (sendentity) {
                conn.sendRequestEntity((HttpEntityEnclosingRequest)request);
            }
        }
        conn.flush();
        context.setAttribute("http.request_sent", (Object)Boolean.TRUE);
        return response;
    }

    public void postProcess(HttpResponse response, HttpProcessor processor, HttpContext context) throws IOException, HttpException {
        Args.notNull((Object)response, (String)"HTTP response");
        Args.notNull((Object)processor, (String)"HTTP processor");
        Args.notNull((Object)context, (String)"HTTP context");
        context.setAttribute("http.response", (Object)response);
        processor.process(response, context);
    }

    public void preProcess(HttpRequest request, HttpProcessor processor, HttpContext context) throws HttpException, IOException {
        Args.notNull((Object)request, (String)"HTTP request");
        Args.notNull((Object)processor, (String)"HTTP processor");
        Args.notNull((Object)context, (String)"HTTP context");
        context.setAttribute("http.request", (Object)request);
        processor.process(request, context);
    }

    protected boolean canResponseHaveBody(HttpRequest request, HttpResponse response) {
        if (!"HEAD".equalsIgnoreCase(request.getRequestLine().getMethod())) int status;
        return (status = response.getStatusLine().getStatusCode()) >= 200 && status != 204 && status != 304 && status != 205;
        return false;
    }

    public HttpRequestExecutor() {
        this(3000);
    }

    public HttpResponse execute(HttpRequest request, HttpClientConnection conn, HttpContext context) throws IOException, HttpException {
        Args.notNull((Object)request, (String)"HTTP request");
        Args.notNull((Object)conn, (String)"Client connection");
        Args.notNull((Object)context, (String)"HTTP context");
        try {
            HttpResponse response = this.doSendRequest(request, conn, context);
            if (response != null) return response;
            response = this.doReceiveResponse(request, conn, context);
            return response;
        }
        catch (IOException ex) {
            HttpRequestExecutor.closeConnection(conn);
            throw ex;
        }
        catch (HttpException ex) {
            HttpRequestExecutor.closeConnection(conn);
            throw ex;
        }
        catch (RuntimeException ex) {
            HttpRequestExecutor.closeConnection(conn);
            throw ex;
        }
    }

    private static void closeConnection(HttpClientConnection conn) {
        try {
            conn.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }
}
