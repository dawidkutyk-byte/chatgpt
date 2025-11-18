/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection$KeyVal
 *  org.jsoup.Connection$Method
 *  org.jsoup.Connection$Request
 *  org.jsoup.helper.DataUtil
 *  org.jsoup.helper.HttpConnection$Base
 *  org.jsoup.helper.RequestAuthenticator
 *  org.jsoup.helper.Validate
 *  org.jsoup.parser.Parser
 */
package org.jsoup.helper;

import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Collection;
import javax.net.ssl.SSLSocketFactory;
import org.jsoup.Connection;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.RequestAuthenticator;
import org.jsoup.helper.Validate;
import org.jsoup.parser.Parser;

public static class HttpConnection.Request
extends HttpConnection.Base<Connection.Request>
implements Connection.Request {
    private volatile boolean executing = false;
    private int maxBodySizeBytes;
    private @7\u015aCz String body = null;
    private boolean ignoreHttpErrors = false;
    private boolean ignoreContentType = false;
    private final Collection<Connection.KeyVal> data;
    private @7\u015aCz SSLSocketFactory sslSocketFactory;
    private CookieManager cookieManager;
    private boolean parserDefined = false;
    private @7\u015aCz Proxy proxy;
    private Parser parser;
    private int timeoutMilliseconds;
    private String postDataCharset = DataUtil.defaultCharsetName;
    private @7\u015aCz RequestAuthenticator authenticator;
    private boolean followRedirects;

    public Parser parser() {
        return this.parser;
    }

    public Connection.Request ignoreHttpErrors(boolean ignoreHttpErrors) {
        this.ignoreHttpErrors = ignoreHttpErrors;
        return this;
    }

    public boolean ignoreHttpErrors() {
        return this.ignoreHttpErrors;
    }

    public Collection<Connection.KeyVal> data() {
        return this.data;
    }

    static /* synthetic */ boolean access$300(HttpConnection.Request x0) {
        return x0.executing;
    }

    public SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }

    public Proxy proxy() {
        return this.proxy;
    }

    public Connection.Request followRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
        return this;
    }

    static /* synthetic */ boolean access$302(HttpConnection.Request x0, boolean x1) {
        x0.executing = x1;
        return x0.executing;
    }

    public int timeout() {
        return this.timeoutMilliseconds;
    }

    public HttpConnection.Request timeout(int millis) {
        Validate.isTrue((millis >= 0 ? 1 : 0) != 0, (String)"Timeout milliseconds must be 0 (infinite) or greater");
        this.timeoutMilliseconds = millis;
        return this;
    }

    public int maxBodySize() {
        return this.maxBodySizeBytes;
    }

    public String postDataCharset() {
        return this.postDataCharset;
    }

    public boolean followRedirects() {
        return this.followRedirects;
    }

    public HttpConnection.Request data(Connection.KeyVal keyval) {
        Validate.notNullParam((Object)keyval, (String)"keyval");
        this.data.add(keyval);
        return this;
    }

    public HttpConnection.Request parser(Parser parser) {
        this.parser = parser;
        this.parserDefined = true;
        return this;
    }

    public Connection.Request auth(@7\u015aCz RequestAuthenticator authenticator) {
        this.authenticator = authenticator;
        return this;
    }

    public Connection.Request requestBody(@7\u015aCz String body) {
        this.body = body;
        return this;
    }

    public String requestBody() {
        return this.body;
    }

    static /* synthetic */ RequestAuthenticator access$500(HttpConnection.Request x0) {
        return x0.authenticator;
    }

    public boolean ignoreContentType() {
        return this.ignoreContentType;
    }

    public void sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
    }

    HttpConnection.Request() {
        super(null);
        this.timeoutMilliseconds = 30000;
        this.maxBodySizeBytes = 0x200000;
        this.followRedirects = true;
        this.data = new ArrayList<Connection.KeyVal>();
        this.method = Connection.Method.GET;
        this.addHeader("Accept-Encoding", "gzip");
        this.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        this.parser = Parser.htmlParser();
        this.cookieManager = new CookieManager();
    }

    static /* synthetic */ boolean access$400(HttpConnection.Request x0) {
        return x0.parserDefined;
    }

    static /* synthetic */ CookieManager access$000(HttpConnection.Request x0) {
        return x0.cookieManager;
    }

    public Connection.Request ignoreContentType(boolean ignoreContentType) {
        this.ignoreContentType = ignoreContentType;
        return this;
    }

    public Connection.Request postDataCharset(String charset) {
        Validate.notNullParam((Object)charset, (String)"charset");
        if (!Charset.isSupported(charset)) {
            throw new IllegalCharsetNameException(charset);
        }
        this.postDataCharset = charset;
        return this;
    }

    public HttpConnection.Request proxy(String host, int port) {
        this.proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(host, port));
        return this;
    }

    public Connection.Request maxBodySize(int bytes) {
        Validate.isTrue((bytes >= 0 ? 1 : 0) != 0, (String)"maxSize must be 0 (unlimited) or larger");
        this.maxBodySizeBytes = bytes;
        return this;
    }

    CookieManager cookieManager() {
        return this.cookieManager;
    }

    static {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
    }

    static /* synthetic */ CookieManager access$002(HttpConnection.Request x0, CookieManager x1) {
        x0.cookieManager = x1;
        return x0.cookieManager;
    }

    public HttpConnection.Request proxy(@7\u015aCz Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    HttpConnection.Request(HttpConnection.Request copy) {
        super((HttpConnection.Base)copy, null);
        this.proxy = copy.proxy;
        this.postDataCharset = copy.postDataCharset;
        this.timeoutMilliseconds = copy.timeoutMilliseconds;
        this.maxBodySizeBytes = copy.maxBodySizeBytes;
        this.followRedirects = copy.followRedirects;
        this.data = new ArrayList<Connection.KeyVal>();
        this.ignoreHttpErrors = copy.ignoreHttpErrors;
        this.ignoreContentType = copy.ignoreContentType;
        this.parser = copy.parser.newInstance();
        this.parserDefined = copy.parserDefined;
        this.sslSocketFactory = copy.sslSocketFactory;
        this.cookieManager = copy.cookieManager;
        this.authenticator = copy.authenticator;
        this.executing = false;
    }

    public @7\u015aCz RequestAuthenticator auth() {
        return this.authenticator;
    }
}
