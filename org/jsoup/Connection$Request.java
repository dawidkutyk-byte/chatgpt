/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection$Base
 *  org.jsoup.Connection$KeyVal
 *  org.jsoup.helper.RequestAuthenticator
 *  org.jsoup.parser.Parser
 */
package org.jsoup;

import java.net.Proxy;
import java.util.Collection;
import javax.net.ssl.SSLSocketFactory;
import org.jsoup.Connection;
import org.jsoup.helper.RequestAuthenticator;
import org.jsoup.parser.Parser;

public static interface Connection.Request
extends Connection.Base<Connection.Request> {
    public @7\u015aCz Proxy proxy();

    public Connection.Request ignoreHttpErrors(boolean var1);

    public Connection.Request maxBodySize(int var1);

    public boolean ignoreHttpErrors();

    public Connection.Request data(Connection.KeyVal var1);

    public Connection.Request timeout(int var1);

    public boolean followRedirects();

    public @7\u015aCz SSLSocketFactory sslSocketFactory();

    public Collection<Connection.KeyVal> data();

    default public Connection.Request auth(@7\u015aCz RequestAuthenticator authenticator) {
        throw new UnsupportedOperationException();
    }

    public Parser parser();

    public String postDataCharset();

    public Connection.Request proxy(@7\u015aCz Proxy var1);

    public Connection.Request postDataCharset(String var1);

    public Connection.Request followRedirects(boolean var1);

    public int maxBodySize();

    public Connection.Request parser(Parser var1);

    public Connection.Request requestBody(@7\u015aCz String var1);

    public Connection.Request proxy(String var1, int var2);

    default public @7\u015aCz RequestAuthenticator auth() {
        throw new UnsupportedOperationException();
    }

    public @7\u015aCz String requestBody();

    public Connection.Request ignoreContentType(boolean var1);

    public int timeout();

    public boolean ignoreContentType();

    public void sslSocketFactory(SSLSocketFactory var1);
}
