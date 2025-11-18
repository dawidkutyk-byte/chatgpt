/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.methods.HttpRequestBase
 */
package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.client.methods.HttpRequestBase;

public class HttpTrace
extends HttpRequestBase {
    public static final String METHOD_NAME = "TRACE";

    public HttpTrace(URI uri) {
        this.setURI(uri);
    }

    public HttpTrace(String uri) {
        this.setURI(URI.create(uri));
    }

    public HttpTrace() {
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
