/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ConnectionReuseStrategy
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpEntityEnclosingRequest
 *  org.apache.http.HttpException
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpResponseFactory
 *  org.apache.http.HttpServerConnection
 *  org.apache.http.HttpVersion
 *  org.apache.http.MethodNotSupportedException
 *  org.apache.http.ProtocolException
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.UnsupportedHttpVersionException
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.entity.ByteArrayEntity
 *  org.apache.http.impl.DefaultConnectionReuseStrategy
 *  org.apache.http.impl.DefaultHttpResponseFactory
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.protocol.HttpExpectationVerifier
 *  org.apache.http.protocol.HttpProcessor
 *  org.apache.http.protocol.HttpRequestHandler
 *  org.apache.http.protocol.HttpRequestHandlerMapper
 *  org.apache.http.protocol.HttpRequestHandlerResolver
 *  org.apache.http.protocol.HttpService$HttpRequestHandlerResolverAdapter
 *  org.apache.http.util.Args
 *  org.apache.http.util.EncodingUtils
 *  org.apache.http.util.EntityUtils
 */
package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpServerConnection;
import org.apache.http.HttpVersion;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.UnsupportedHttpVersionException;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpExpectationVerifier;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerMapper;
import org.apache.http.protocol.HttpRequestHandlerResolver;
import org.apache.http.protocol.HttpService;
import org.apache.http.util.Args;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public class HttpService {
    private volatile HttpResponseFactory responseFactory = null;
    private volatile HttpExpectationVerifier expectationVerifier = null;
    private volatile HttpProcessor processor = null;
    private volatile ConnectionReuseStrategy connStrategy = null;
    private volatile HttpRequestHandlerMapper handlerMapper = null;
    private volatile HttpParams params = null;

    public HttpService(HttpProcessor processor, ConnectionReuseStrategy connStrategy, HttpResponseFactory responseFactory, HttpRequestHandlerMapper handlerMapper, HttpExpectationVerifier expectationVerifier) {
        this.processor = (HttpProcessor)Args.notNull((Object)processor, (String)"HTTP processor");
        this.connStrategy = connStrategy != null ? connStrategy : DefaultConnectionReuseStrategy.INSTANCE;
        this.responseFactory = responseFactory != null ? responseFactory : DefaultHttpResponseFactory.INSTANCE;
        this.handlerMapper = handlerMapper;
        this.expectationVerifier = expectationVerifier;
    }

    @Deprecated
    public void setHandlerResolver(HttpRequestHandlerResolver handlerResolver) {
        this.handlerMapper = new HttpRequestHandlerResolverAdapter(handlerResolver);
    }

    @Deprecated
    public HttpService(HttpProcessor processor, ConnectionReuseStrategy connStrategy, HttpResponseFactory responseFactory, HttpRequestHandlerResolver handlerResolver, HttpExpectationVerifier expectationVerifier, HttpParams params) {
        this(processor, connStrategy, responseFactory, (HttpRequestHandlerMapper)new HttpRequestHandlerResolverAdapter(handlerResolver), expectationVerifier);
        this.params = params;
    }

    @Deprecated
    public void setResponseFactory(HttpResponseFactory responseFactory) {
        Args.notNull((Object)responseFactory, (String)"Response factory");
        this.responseFactory = responseFactory;
    }

    @Deprecated
    public void setExpectationVerifier(HttpExpectationVerifier expectationVerifier) {
        this.expectationVerifier = expectationVerifier;
    }

    private boolean canResponseHaveBody(HttpRequest request, HttpResponse response) {
        if (request == null || !"HEAD".equalsIgnoreCase(request.getRequestLine().getMethod())) int status;
        return (status = response.getStatusLine().getStatusCode()) >= 200 && status != 204 && status != 304 && status != 205;
        return false;
    }

    @Deprecated
    public HttpService(HttpProcessor processor, ConnectionReuseStrategy connStrategy, HttpResponseFactory responseFactory, HttpRequestHandlerResolver handlerResolver, HttpParams params) {
        this(processor, connStrategy, responseFactory, (HttpRequestHandlerMapper)new HttpRequestHandlerResolverAdapter(handlerResolver), null);
        this.params = params;
    }

    @Deprecated
    public HttpService(HttpProcessor proc, ConnectionReuseStrategy connStrategy, HttpResponseFactory responseFactory) {
        this.setHttpProcessor(proc);
        this.setConnReuseStrategy(connStrategy);
        this.setResponseFactory(responseFactory);
    }

    public HttpService(HttpProcessor processor, HttpRequestHandlerMapper handlerMapper) {
        this(processor, null, null, handlerMapper, null);
    }

    @Deprecated
    public void setHttpProcessor(HttpProcessor processor) {
        Args.notNull((Object)processor, (String)"HTTP processor");
        this.processor = processor;
    }

    @Deprecated
    public HttpParams getParams() {
        return this.params;
    }

    @Deprecated
    public void setConnReuseStrategy(ConnectionReuseStrategy connStrategy) {
        Args.notNull((Object)connStrategy, (String)"Connection reuse strategy");
        this.connStrategy = connStrategy;
    }

    public HttpService(HttpProcessor processor, ConnectionReuseStrategy connStrategy, HttpResponseFactory responseFactory, HttpRequestHandlerMapper handlerMapper) {
        this(processor, connStrategy, responseFactory, handlerMapper, null);
    }

    protected void doService(HttpRequest request, HttpResponse response, HttpContext context) throws IOException, HttpException {
        HttpRequestHandler handler = null;
        if (this.handlerMapper != null) {
            handler = this.handlerMapper.lookup(request);
        }
        if (handler != null) {
            handler.handle(request, response, context);
        } else {
            response.setStatusCode(501);
        }
    }

    public void handleRequest(HttpServerConnection conn, HttpContext context) throws HttpException, IOException {
        context.setAttribute("http.connection", (Object)conn);
        HttpRequest request = null;
        HttpResponse response = null;
        try {
            request = conn.receiveRequestHeader();
            if (request instanceof HttpEntityEnclosingRequest) {
                if (((HttpEntityEnclosingRequest)request).expectContinue()) {
                    response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_1, 100, context);
                    if (this.expectationVerifier != null) {
                        try {
                            this.expectationVerifier.verify(request, response, context);
                        }
                        catch (HttpException ex) {
                            response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_0, 500, context);
                            this.handleException(ex, response);
                        }
                    }
                    if (response.getStatusLine().getStatusCode() < 200) {
                        conn.sendResponseHeader(response);
                        conn.flush();
                        response = null;
                        conn.receiveRequestEntity((HttpEntityEnclosingRequest)request);
                    }
                } else {
                    conn.receiveRequestEntity((HttpEntityEnclosingRequest)request);
                }
            }
            context.setAttribute("http.request", (Object)request);
            if (response == null) {
                response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_1, 200, context);
                this.processor.process(request, context);
                this.doService(request, response, context);
            }
            if (request instanceof HttpEntityEnclosingRequest) {
                HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
                EntityUtils.consume((HttpEntity)entity);
            }
        }
        catch (HttpException ex) {
            response = this.responseFactory.newHttpResponse((ProtocolVersion)HttpVersion.HTTP_1_0, 500, context);
            this.handleException(ex, response);
        }
        context.setAttribute("http.response", (Object)response);
        this.processor.process(response, context);
        conn.sendResponseHeader(response);
        if (this.canResponseHaveBody(request, response)) {
            conn.sendResponseEntity(response);
        }
        conn.flush();
        if (this.connStrategy.keepAlive(response, context)) return;
        conn.close();
    }

    @Deprecated
    public void setParams(HttpParams params) {
        this.params = params;
    }

    protected void handleException(HttpException ex, HttpResponse response) {
        if (ex instanceof MethodNotSupportedException) {
            response.setStatusCode(501);
        } else if (ex instanceof UnsupportedHttpVersionException) {
            response.setStatusCode(505);
        } else if (ex instanceof ProtocolException) {
            response.setStatusCode(400);
        } else {
            response.setStatusCode(500);
        }
        String message = ex.getMessage();
        if (message == null) {
            message = ex.toString();
        }
        byte[] msg = EncodingUtils.getAsciiBytes((String)message);
        ByteArrayEntity entity = new ByteArrayEntity(msg);
        entity.setContentType("text/plain; charset=US-ASCII");
        response.setEntity((HttpEntity)entity);
    }
}
