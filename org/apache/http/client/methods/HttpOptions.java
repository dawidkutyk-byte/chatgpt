/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderElement
 *  org.apache.http.HeaderIterator
 *  org.apache.http.HttpResponse
 *  org.apache.http.client.methods.HttpRequestBase
 *  org.apache.http.util.Args
 */
package org.apache.http.client.methods;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.Args;

public class HttpOptions
extends HttpRequestBase {
    public static final String METHOD_NAME = "OPTIONS";

    public HttpOptions() {
    }

    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpOptions(String uri) {
        this.setURI(URI.create(uri));
    }

    public HttpOptions(URI uri) {
        this.setURI(uri);
    }

    public Set<String> getAllowedMethods(HttpResponse response) {
        Args.notNull((Object)response, (String)"HTTP response");
        HeaderIterator it = response.headerIterator("Allow");
        HashSet<String> methods = new HashSet<String>();
        block0: while (it.hasNext()) {
            HeaderElement[] elements;
            Header header = it.nextHeader();
            HeaderElement[] arr$ = elements = header.getElements();
            int len$ = arr$.length;
            int i$ = 0;
            while (true) {
                if (i$ >= len$) continue block0;
                HeaderElement element = arr$[i$];
                methods.add(element.getName());
                ++i$;
            }
            break;
        }
        return methods;
    }
}
