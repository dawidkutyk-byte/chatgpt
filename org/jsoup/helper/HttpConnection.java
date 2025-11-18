/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection
 *  org.jsoup.Connection$KeyVal
 *  org.jsoup.Connection$Method
 *  org.jsoup.Connection$Request
 *  org.jsoup.Connection$Response
 *  org.jsoup.helper.HttpConnection$KeyVal
 *  org.jsoup.helper.HttpConnection$Request
 *  org.jsoup.helper.HttpConnection$Response
 *  org.jsoup.helper.RequestAuthenticator
 *  org.jsoup.helper.Validate
 *  org.jsoup.nodes.Document
 *  org.jsoup.parser.Parser
 */
package org.jsoup.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.RequestAuthenticator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

/*
 * Exception performing whole class analysis ignored.
 */
public class HttpConnection
implements Connection {
    private // Could not load outer class - annotation placement on inner may be incorrect
    @7\u015aCz Connection.Response res;
    public static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String USER_AGENT = "User-Agent";
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    private static final int HTTP_TEMP_REDIR = 307;
    public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    private Request req;
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DEFAULT_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String DefaultUploadType = "application/octet-stream";

    public Connection auth(RequestAuthenticator authenticator) {
        this.req.auth(authenticator);
        return this;
    }

    public Connection.Response execute() throws IOException {
        this.res = Response.execute((Request)this.req);
        return this.res;
    }

    public Connection.Response response() {
        if (this.res != null) return this.res;
        throw new IllegalArgumentException("You must execute the request before getting a response.");
    }

    public Document post() throws IOException {
        this.req.method(Connection.Method.POST);
        this.execute();
        Validate.notNull((Object)this.res);
        return this.res.parse();
    }

    public Connection requestBody(String body) {
        this.req.requestBody(body);
        return this;
    }

    public Connection headers(Map<String, String> headers) {
        Validate.notNullParam(headers, (String)"headers");
        Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            this.req.header(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public Connection cookies(Map<String, String> cookies) {
        Validate.notNullParam(cookies, (String)"cookies");
        Iterator<Map.Entry<String, String>> iterator = cookies.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            this.req.cookie(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public Connection request(Connection.Request request) {
        this.req = (Request)request;
        return this;
    }

    public Connection.KeyVal data(String key) {
        Connection.KeyVal keyVal;
        Validate.notEmptyParam((String)key, (String)"key");
        Iterator iterator = this.request().data().iterator();
        do {
            if (!iterator.hasNext()) return null;
        } while (!(keyVal = (Connection.KeyVal)iterator.next()).key().equals(key));
        return keyVal;
    }

    public CookieStore cookieStore() {
        return Request.access$000((Request)this.req).getCookieStore();
    }

    public Connection header(String name, String value) {
        this.req.header(name, value);
        return this;
    }

    public Connection url(String url) {
        Validate.notEmptyParam((String)url, (String)"url");
        try {
            this.req.url(new URL(url));
        }
        catch (MalformedURLException e) {
            throw new IllegalArgumentException(String.format("The supplied URL, '%s', is malformed. Make sure it is an absolute URL, and starts with 'http://' or 'https://'. See https://jsoup.org/cookbook/extracting-data/working-with-urls", url), e);
        }
        return this;
    }

    public Connection data(Collection<Connection.KeyVal> data) {
        Validate.notNullParam(data, (String)"data");
        Iterator<Connection.KeyVal> iterator = data.iterator();
        while (iterator.hasNext()) {
            Connection.KeyVal entry = iterator.next();
            this.req.data(entry);
        }
        return this;
    }

    public Connection response(Connection.Response response) {
        this.res = response;
        return this;
    }

    public Connection ignoreContentType(boolean ignoreContentType) {
        this.req.ignoreContentType(ignoreContentType);
        return this;
    }

    public Document get() throws IOException {
        this.req.method(Connection.Method.GET);
        this.execute();
        Validate.notNull((Object)this.res);
        return this.res.parse();
    }

    public Connection userAgent(String userAgent) {
        Validate.notNullParam((Object)userAgent, (String)"userAgent");
        this.req.header("User-Agent", userAgent);
        return this;
    }

    public static Connection connect(String url) {
        HttpConnection con = new HttpConnection();
        con.url(url);
        return con;
    }

    public Connection data(String key, String filename, InputStream inputStream, String contentType) {
        this.req.data(KeyVal.create((String)key, (String)filename, (InputStream)inputStream).contentType(contentType));
        return this;
    }

    public HttpConnection() {
        this.req = new Request();
    }

    public Connection referrer(String referrer) {
        Validate.notNullParam((Object)referrer, (String)"referrer");
        this.req.header("Referer", referrer);
        return this;
    }

    public Connection proxy(String host, int port) {
        this.req.proxy(host, port);
        return this;
    }

    public Connection ignoreHttpErrors(boolean ignoreHttpErrors) {
        this.req.ignoreHttpErrors(ignoreHttpErrors);
        return this;
    }

    public Connection followRedirects(boolean followRedirects) {
        this.req.followRedirects(followRedirects);
        return this;
    }

    public Connection method(Connection.Method method) {
        this.req.method(method);
        return this;
    }

    private static boolean needsMultipart(Connection.Request req) {
        Connection.KeyVal keyVal;
        Iterator iterator = req.data().iterator();
        do {
            if (!iterator.hasNext()) return false;
        } while (!(keyVal = (Connection.KeyVal)iterator.next()).hasInputStream());
        return true;
    }

    public Connection data(Map<String, String> data) {
        Validate.notNullParam(data, (String)"data");
        Iterator<Map.Entry<String, String>> iterator = data.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            this.req.data((Connection.KeyVal)KeyVal.create((String)entry.getKey(), (String)entry.getValue()));
        }
        return this;
    }

    static /* synthetic */ String access$900(String x0) {
        return HttpConnection.encodeMimeName(x0);
    }

    public Connection url(URL url) {
        this.req.url(url);
        return this;
    }

    public Connection newRequest() {
        return new HttpConnection(this.req);
    }

    public Connection maxBodySize(int bytes) {
        this.req.maxBodySize(bytes);
        return this;
    }

    static /* synthetic */ Charset access$700() {
        return ISO_8859_1;
    }

    public Connection data(String key, String filename, InputStream inputStream) {
        this.req.data((Connection.KeyVal)KeyVal.create((String)key, (String)filename, (InputStream)inputStream));
        return this;
    }

    private HttpConnection(Request req, Response res) {
        this.req = req;
        this.res = res;
    }

    public Connection data(String ... keyvals) {
        Validate.notNullParam((Object)keyvals, (String)"keyvals");
        Validate.isTrue((keyvals.length % 2 == 0 ? 1 : 0) != 0, (String)"Must supply an even number of key value pairs");
        int i = 0;
        while (i < keyvals.length) {
            String key = keyvals[i];
            String value = keyvals[i + 1];
            Validate.notEmpty((String)key, (String)"Data key must not be empty");
            Validate.notNull((Object)value, (String)"Data value must not be null");
            this.req.data((Connection.KeyVal)KeyVal.create((String)key, (String)value));
            i += 2;
        }
        return this;
    }

    public Connection timeout(int millis) {
        this.req.timeout(millis);
        return this;
    }

    public static Connection connect(URL url) {
        HttpConnection con = new HttpConnection();
        con.url(url);
        return con;
    }

    private static String encodeMimeName(String val) {
        return val.replace("\"", "%22");
    }

    static /* synthetic */ boolean access$800(Connection.Request x0) {
        return HttpConnection.needsMultipart(x0);
    }

    public Connection cookieStore(CookieStore cookieStore) {
        Request.access$002((Request)this.req, (CookieManager)new CookieManager(cookieStore, null));
        return this;
    }

    public Connection.Request request() {
        return this.req;
    }

    public Connection parser(Parser parser) {
        this.req.parser(parser);
        return this;
    }

    public Connection cookie(String name, String value) {
        this.req.cookie(name, value);
        return this;
    }

    public Connection proxy(@7\u015aCz Proxy proxy) {
        this.req.proxy(proxy);
        return this;
    }

    public Connection sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.req.sslSocketFactory(sslSocketFactory);
        return this;
    }

    HttpConnection(Request copy) {
        this.req = new Request(copy);
    }

    public Connection data(String key, String value) {
        this.req.data((Connection.KeyVal)KeyVal.create((String)key, (String)value));
        return this;
    }

    public Connection postDataCharset(String charset) {
        this.req.postDataCharset(charset);
        return this;
    }
}
