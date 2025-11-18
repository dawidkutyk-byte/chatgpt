/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection$Base
 *  org.jsoup.Connection$Method
 *  org.jsoup.helper.UrlBuilder
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.internal.StringUtil
 */
package org.jsoup.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.helper.UrlBuilder;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;

private static abstract class HttpConnection.Base<T extends Connection.Base<T>>
implements Connection.Base<T> {
    URL url = UnsetUrl;
    Map<String, String> cookies;
    private static final URL UnsetUrl;
    Connection.Method method = Connection.Method.GET;
    Map<String, List<String>> headers;

    public T url(URL url) {
        Validate.notNullParam((Object)url, (String)"url");
        this.url = new UrlBuilder(url).build();
        return (T)this;
    }

    public boolean hasHeaderWithValue(String name, String value) {
        String candidate;
        Validate.notEmpty((String)name);
        Validate.notEmpty((String)value);
        List<String> values = this.headers(name);
        Iterator<String> iterator = values.iterator();
        do {
            if (!iterator.hasNext()) return false;
        } while (!value.equalsIgnoreCase(candidate = iterator.next()));
        return true;
    }

    public T method(Connection.Method method) {
        Validate.notNullParam((Object)method, (String)"method");
        this.method = method;
        return (T)this;
    }

    public Map<String, List<String>> multiHeaders() {
        return this.headers;
    }

    public T header(String name, String value) {
        Validate.notEmptyParam((String)name, (String)"name");
        this.removeHeader(name);
        this.addHeader(name, value);
        return (T)this;
    }

    public Connection.Method method() {
        return this.method;
    }

    private List<String> getHeadersCaseInsensitive(String name) {
        Map.Entry<String, List<String>> entry;
        Validate.notNull((Object)name);
        Iterator<Map.Entry<String, List<String>>> iterator = this.headers.entrySet().iterator();
        do {
            if (!iterator.hasNext()) return Collections.emptyList();
        } while (!name.equalsIgnoreCase((entry = iterator.next()).getKey()));
        return entry.getValue();
    }

    public T removeHeader(String name) {
        Validate.notEmptyParam((String)name, (String)"name");
        Map.Entry<String, List<String>> entry = this.scanHeaders(name);
        if (entry == null) return (T)this;
        this.headers.remove(entry.getKey());
        return (T)this;
    }

    public T removeCookie(String name) {
        Validate.notEmptyParam((String)name, (String)"name");
        this.cookies.remove(name);
        return (T)this;
    }

    private @7\u015aCz Map.Entry<String, List<String>> scanHeaders(String name) {
        Map.Entry<String, List<String>> entry;
        String lc = Normalizer.lowerCase((String)name);
        Iterator<Map.Entry<String, List<String>>> iterator = this.headers.entrySet().iterator();
        do {
            if (!iterator.hasNext()) return null;
        } while (!Normalizer.lowerCase((String)(entry = iterator.next()).getKey()).equals(lc));
        return entry;
    }

    static {
        try {
            UnsetUrl = new URL("http://undefined/");
        }
        catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    private HttpConnection.Base(HttpConnection.Base<T> copy) {
        this.url = copy.url;
        this.method = copy.method;
        this.headers = new LinkedHashMap<String, List<String>>();
        Iterator<Map.Entry<String, List<String>>> iterator = copy.headers.entrySet().iterator();
        while (true) {
            if (!iterator.hasNext()) {
                this.cookies = new LinkedHashMap<String, String>();
                this.cookies.putAll(copy.cookies);
                return;
            }
            Map.Entry<String, List<String>> entry = iterator.next();
            this.headers.put(entry.getKey(), new ArrayList(entry.getValue()));
        }
    }

    public Map<String, String> cookies() {
        return this.cookies;
    }

    public T cookie(String name, String value) {
        Validate.notEmptyParam((String)name, (String)"name");
        Validate.notNullParam((Object)value, (String)"value");
        this.cookies.put(name, value);
        return (T)this;
    }

    public Map<String, String> headers() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(this.headers.size());
        Iterator<Map.Entry<String, List<String>>> iterator = this.headers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> entry = iterator.next();
            String header = entry.getKey();
            List<String> values = entry.getValue();
            if (values.size() <= 0) continue;
            map.put(header, values.get(0));
        }
        return map;
    }

    public T addHeader(String name, @7\u015aCz String value) {
        Validate.notEmptyParam((String)name, (String)"name");
        value = value == null ? "" : value;
        List<String> values = this.headers(name);
        if (values.isEmpty()) {
            values = new ArrayList<String>();
            this.headers.put(name, values);
        }
        values.add(value);
        return (T)this;
    }

    public boolean hasCookie(String name) {
        Validate.notEmptyParam((String)name, (String)"name");
        return this.cookies.containsKey(name);
    }

    private HttpConnection.Base() {
        this.headers = new LinkedHashMap<String, List<String>>();
        this.cookies = new LinkedHashMap<String, String>();
    }

    public String header(String name) {
        Validate.notNullParam((Object)name, (String)"name");
        List<String> vals = this.getHeadersCaseInsensitive(name);
        if (vals.size() <= 0) return null;
        return StringUtil.join(vals, (String)", ");
    }

    public URL url() {
        if (this.url != UnsetUrl) return this.url;
        throw new IllegalArgumentException("URL not set. Make sure to call #url(...) before executing the request.");
    }

    public boolean hasHeader(String name) {
        Validate.notEmptyParam((String)name, (String)"name");
        return !this.getHeadersCaseInsensitive(name).isEmpty();
    }

    public String cookie(String name) {
        Validate.notEmptyParam((String)name, (String)"name");
        return this.cookies.get(name);
    }

    public List<String> headers(String name) {
        Validate.notEmptyParam((String)name, (String)"name");
        return this.getHeadersCaseInsensitive(name);
    }
}
