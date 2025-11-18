/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.Header
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpResponse
 *  org.apache.http.ProtocolException
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.client.CircularRedirectException
 *  org.apache.http.client.RedirectStrategy
 *  org.apache.http.client.config.RequestConfig
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpHead
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.client.methods.RequestBuilder
 *  org.apache.http.client.protocol.HttpClientContext
 *  org.apache.http.client.utils.URIUtils
 *  org.apache.http.impl.client.RedirectLocations
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.EnumSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.RedirectLocations;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class DefaultRedirectStrategy
implements RedirectStrategy {
    private final Log log = LogFactory.getLog(this.getClass());
    private final String[] redirectMethods;
    public static final int SC_PERMANENT_REDIRECT = 308;
    public static final DefaultRedirectStrategy INSTANCE = new DefaultRedirectStrategy();
    @Deprecated
    public static final String REDIRECT_LOCATIONS = "http.protocol.redirect-locations";

    public DefaultRedirectStrategy() {
        this(new String[]{"GET", "HEAD"});
    }

    protected URI createLocationURI(String location) throws ProtocolException {
        try {
            return new URI(location);
        }
        catch (URISyntaxException ex) {
            throw new ProtocolException("Invalid redirect URI: " + location, (Throwable)ex);
        }
    }

    protected boolean isRedirectable(String method) {
        return Arrays.binarySearch(this.redirectMethods, method) >= 0;
    }

    public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        URI uri = this.getLocationURI(request, response, context);
        String method = request.getRequestLine().getMethod();
        if (method.equalsIgnoreCase("HEAD")) {
            return new HttpHead(uri);
        }
        if (!method.equalsIgnoreCase("GET")) int status;
        return (status = response.getStatusLine().getStatusCode()) == 307 || status == 308 ? RequestBuilder.copy((HttpRequest)request).setUri(uri).build() : new HttpGet(uri);
        return new HttpGet(uri);
    }

    public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        Args.notNull((Object)request, (String)"HTTP request");
        Args.notNull((Object)response, (String)"HTTP response");
        int statusCode = response.getStatusLine().getStatusCode();
        String method = request.getRequestLine().getMethod();
        Header locationHeader = response.getFirstHeader("location");
        switch (statusCode) {
            case 302: {
                return this.isRedirectable(method) && locationHeader != null;
            }
            case 301: 
            case 307: 
            case 308: {
                return this.isRedirectable(method);
            }
            case 303: {
                return true;
            }
        }
        return false;
    }

    public DefaultRedirectStrategy(String[] redirectMethods) {
        Object[] tmp = (String[])redirectMethods.clone();
        Arrays.sort(tmp);
        this.redirectMethods = tmp;
    }

    public URI getLocationURI(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
        Args.notNull((Object)request, (String)"HTTP request");
        Args.notNull((Object)response, (String)"HTTP response");
        Args.notNull((Object)context, (String)"HTTP context");
        HttpClientContext clientContext = HttpClientContext.adapt((HttpContext)context);
        Header locationHeader = response.getFirstHeader("location");
        if (locationHeader == null) {
            throw new ProtocolException("Received redirect response " + response.getStatusLine() + " but no location header");
        }
        String location = locationHeader.getValue();
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Redirect requested to location '" + location + "'"));
        }
        RequestConfig config = clientContext.getRequestConfig();
        URI uri = this.createLocationURI(location);
        try {
            if (config.isNormalizeUri()) {
                uri = URIUtils.normalizeSyntax((URI)uri);
            }
            if (!uri.isAbsolute()) {
                if (!config.isRelativeRedirectsAllowed()) {
                    throw new ProtocolException("Relative redirect location '" + uri + "' not allowed");
                }
                HttpHost target = clientContext.getTargetHost();
                Asserts.notNull((Object)target, (String)"Target host");
                URI requestURI = new URI(request.getRequestLine().getUri());
                URI absoluteRequestURI = URIUtils.rewriteURI((URI)requestURI, (HttpHost)target, (EnumSet)(config.isNormalizeUri() ? URIUtils.NORMALIZE : URIUtils.NO_FLAGS));
                uri = URIUtils.resolve((URI)absoluteRequestURI, (URI)uri);
            }
        }
        catch (URISyntaxException ex) {
            throw new ProtocolException(ex.getMessage(), (Throwable)ex);
        }
        RedirectLocations redirectLocations = (RedirectLocations)clientContext.getAttribute(REDIRECT_LOCATIONS);
        if (redirectLocations == null) {
            redirectLocations = new RedirectLocations();
            context.setAttribute(REDIRECT_LOCATIONS, (Object)redirectLocations);
        }
        if (!config.isCircularRedirectsAllowed() && redirectLocations.contains(uri)) {
            throw new CircularRedirectException("Circular redirect to '" + uri + "'");
        }
        redirectLocations.add(uri);
        return uri;
    }
}
