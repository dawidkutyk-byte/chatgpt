/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection$Method
 */
package org.jsoup;

import java.net.URL;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;

public static interface Connection.Base<T extends Connection.Base<T>> {
    public boolean hasHeader(String var1);

    public @7\u015aCz String cookie(String var1);

    public @7\u015aCz String header(String var1);

    public T method(Connection.Method var1);

    public Map<String, String> headers();

    public boolean hasHeaderWithValue(String var1, String var2);

    public T cookie(String var1, String var2);

    public boolean hasCookie(String var1);

    public T header(String var1, String var2);

    public List<String> headers(String var1);

    public T url(URL var1);

    public URL url();

    public Connection.Method method();

    public Map<String, String> cookies();

    public T addHeader(String var1, String var2);

    public Map<String, List<String>> multiHeaders();

    public T removeHeader(String var1);

    public T removeCookie(String var1);
}
