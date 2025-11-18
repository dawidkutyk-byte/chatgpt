/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.methods.HttpEntityEnclosingRequestBase
 */
package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class HttpPut
extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "PUT";

    public HttpPut() {
    }

    public HttpPut(String uri) {
        this.setURI(URI.create(uri));
    }

    public HttpPut(URI uri) {
        this.setURI(uri);
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
