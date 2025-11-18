/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection$KeyVal
 *  org.jsoup.helper.Validate
 */
package org.jsoup.helper;

import java.io.InputStream;
import org.jsoup.Connection;
import org.jsoup.helper.Validate;

public static class HttpConnection.KeyVal
implements Connection.KeyVal {
    private String key;
    private @7\u015aCz String contentType;
    private String value;
    private @7\u015aCz InputStream stream;

    public boolean hasInputStream() {
        return this.stream != null;
    }

    public String contentType() {
        return this.contentType;
    }

    public String toString() {
        return this.key + "=" + this.value;
    }

    public HttpConnection.KeyVal inputStream(InputStream inputStream) {
        Validate.notNullParam((Object)this.value, (String)"inputStream");
        this.stream = inputStream;
        return this;
    }

    public InputStream inputStream() {
        return this.stream;
    }

    public String key() {
        return this.key;
    }

    private HttpConnection.KeyVal(String key, String value) {
        Validate.notEmptyParam((String)key, (String)"key");
        Validate.notNullParam((Object)value, (String)"value");
        this.key = key;
        this.value = value;
    }

    public static HttpConnection.KeyVal create(String key, String value) {
        return new HttpConnection.KeyVal(key, value);
    }

    public HttpConnection.KeyVal value(String value) {
        Validate.notNullParam((Object)value, (String)"value");
        this.value = value;
        return this;
    }

    public Connection.KeyVal contentType(String contentType) {
        Validate.notEmpty((String)contentType);
        this.contentType = contentType;
        return this;
    }

    public HttpConnection.KeyVal key(String key) {
        Validate.notEmptyParam((String)key, (String)"key");
        this.key = key;
        return this;
    }

    public static HttpConnection.KeyVal create(String key, String filename, InputStream stream) {
        return new HttpConnection.KeyVal(key, filename).inputStream(stream);
    }

    public String value() {
        return this.value;
    }
}
