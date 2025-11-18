/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.methods.HttpEntityEnclosingRequestBase
 */
package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class HttpPost
extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "POST";

    public HttpPost(String uri) {
        this.setURI(URI.create(uri));
    }

    public HttpPost(URI uri) {
        this.setURI(uri);
    }

    public HttpPost() {
    }

    public String getMethod() {
        return METHOD_NAME;
    }
}
