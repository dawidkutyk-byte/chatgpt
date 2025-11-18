/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.protocol.HttpRequestHandler
 *  org.apache.http.protocol.HttpRequestHandlerResolver
 *  org.apache.http.protocol.UriPatternMatcher
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import java.util.Map;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerResolver;
import org.apache.http.protocol.UriPatternMatcher;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE)
public class HttpRequestHandlerRegistry
implements HttpRequestHandlerResolver {
    private final UriPatternMatcher<HttpRequestHandler> matcher = new UriPatternMatcher();

    public void unregister(String pattern) {
        this.matcher.unregister(pattern);
    }

    public Map<String, HttpRequestHandler> getHandlers() {
        return this.matcher.getObjects();
    }

    public HttpRequestHandler lookup(String requestURI) {
        return (HttpRequestHandler)this.matcher.lookup(requestURI);
    }

    public void setHandlers(Map<String, HttpRequestHandler> map) {
        this.matcher.setObjects(map);
    }

    public void register(String pattern, HttpRequestHandler handler) {
        Args.notNull((Object)pattern, (String)"URI request pattern");
        Args.notNull((Object)handler, (String)"Request handler");
        this.matcher.register(pattern, (Object)handler);
    }
}
