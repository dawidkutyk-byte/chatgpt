/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection$KeyVal
 *  org.jsoup.Connection$Method
 *  org.jsoup.Connection$Request
 *  org.jsoup.Connection$Response
 *  org.jsoup.helper.RequestAuthenticator
 *  org.jsoup.nodes.Document
 *  org.jsoup.parser.Parser
 */
package org.jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.net.CookieStore;
import java.net.Proxy;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import org.jsoup.Connection;
import org.jsoup.helper.RequestAuthenticator;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

public interface Connection {
    public Connection parser(Parser var1);

    public Connection url(String var1);

    public Connection proxy(String var1, int var2);

    public Document get() throws IOException;

    default public Connection auth(@7\u015aCz RequestAuthenticator authenticator) {
        throw new UnsupportedOperationException();
    }

    public Connection request(Request var1);

    public Connection userAgent(String var1);

    public Connection data(Collection<KeyVal> var1);

    public Connection cookie(String var1, String var2);

    public Connection followRedirects(boolean var1);

    public Connection method(Method var1);

    public Connection requestBody(String var1);

    public Connection timeout(int var1);

    public Connection data(Map<String, String> var1);

    public Connection data(String var1, String var2, InputStream var3);

    public Connection ignoreHttpErrors(boolean var1);

    public Connection data(String var1, String var2, InputStream var3, String var4);

    public Connection newRequest();

    public Connection cookieStore(CookieStore var1);

    public Response response();

    public Response execute() throws IOException;

    public Connection cookies(Map<String, String> var1);

    public Connection response(Response var1);

    public Document post() throws IOException;

    public // Could not load outer class - annotation placement on inner may be incorrect
    @7\u015aCz Connection.KeyVal data(String var1);

    public Request request();

    public Connection data(String ... var1);

    public Connection data(String var1, String var2);

    public Connection headers(Map<String, String> var1);

    public Connection referrer(String var1);

    public CookieStore cookieStore();

    default public Connection newRequest(String url) {
        return this.newRequest().url(url);
    }

    public Connection maxBodySize(int var1);

    public Connection sslSocketFactory(SSLSocketFactory var1);

    public Connection url(URL var1);

    public Connection ignoreContentType(boolean var1);

    default public Connection newRequest(URL url) {
        return this.newRequest().url(url);
    }

    public Connection postDataCharset(String var1);

    public Connection proxy(@7\u015aCz Proxy var1);

    public Connection header(String var1, String var2);
}
