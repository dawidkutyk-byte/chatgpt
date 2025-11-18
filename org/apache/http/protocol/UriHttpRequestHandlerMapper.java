/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.protocol.HttpRequestHandler
 *  org.apache.http.protocol.HttpRequestHandlerMapper
 *  org.apache.http.protocol.UriPatternMatcher
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import org.apache.http.HttpRequest;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerMapper;
import org.apache.http.protocol.UriPatternMatcher;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE)
public class UriHttpRequestHandlerMapper
implements HttpRequestHandlerMapper {
    private final UriPatternMatcher<HttpRequestHandler> matcher;

    public HttpRequestHandler lookup(HttpRequest request) {
        Args.notNull((Object)request, (String)"HTTP request");
        return (HttpRequestHandler)this.matcher.lookup(this.getRequestPath(request));
    }

    public void register(String pattern, HttpRequestHandler handler) {
        Args.notNull((Object)pattern, (String)"Pattern");
        Args.notNull((Object)handler, (String)"Handler");
        this.matcher.register(pattern, (Object)handler);
    }

    public void unregister(String pattern) {
        this.matcher.unregister(pattern);
    }

    protected UriHttpRequestHandlerMapper(UriPatternMatcher<HttpRequestHandler> matcher) {
        this.matcher = (UriPatternMatcher)Args.notNull(matcher, (String)"Pattern matcher");
    }

    public UriHttpRequestHandlerMapper() {
        this((UriPatternMatcher<HttpRequestHandler>)new UriPatternMatcher());
    }

    protected String getRequestPath(HttpRequest request) {
        String uriPath = request.getRequestLine().getUri();
        int index = uriPath.indexOf(63);
        if (index != -1) {
            uriPath = uriPath.substring(0, index);
        } else {
            index = uriPath.indexOf(35);
            if (index == -1) return uriPath;
            uriPath = uriPath.substring(0, index);
        }
        return uriPath;
    }
}
