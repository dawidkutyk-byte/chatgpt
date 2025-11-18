/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpException
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpResponseInterceptor
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.protocol.HttpProcessor
 *  org.apache.http.protocol.HttpRequestInterceptorList
 *  org.apache.http.protocol.HttpResponseInterceptorList
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestInterceptorList;
import org.apache.http.protocol.HttpResponseInterceptorList;
import org.apache.http.util.Args;

@Deprecated
public final class BasicHttpProcessor
implements HttpRequestInterceptorList,
HttpResponseInterceptorList,
Cloneable,
HttpProcessor {
    protected final List<HttpResponseInterceptor> responseInterceptors;
    protected final List<HttpRequestInterceptor> requestInterceptors = new ArrayList<HttpRequestInterceptor>();

    public void clearRequestInterceptors() {
        this.requestInterceptors.clear();
    }

    protected void copyInterceptors(BasicHttpProcessor target) {
        target.requestInterceptors.clear();
        target.requestInterceptors.addAll(this.requestInterceptors);
        target.responseInterceptors.clear();
        target.responseInterceptors.addAll(this.responseInterceptors);
    }

    public void clearInterceptors() {
        this.clearRequestInterceptors();
        this.clearResponseInterceptors();
    }

    public final void addInterceptor(HttpResponseInterceptor interceptor) {
        this.addResponseInterceptor(interceptor);
    }

    public int getResponseInterceptorCount() {
        return this.responseInterceptors.size();
    }

    public BasicHttpProcessor() {
        this.responseInterceptors = new ArrayList<HttpResponseInterceptor>();
    }

    public void addResponseInterceptor(HttpResponseInterceptor itcp) {
        if (itcp == null) {
            return;
        }
        this.responseInterceptors.add(itcp);
    }

    public HttpRequestInterceptor getRequestInterceptor(int index) {
        if (index < 0) return null;
        if (index < this.requestInterceptors.size()) return this.requestInterceptors.get(index);
        return null;
    }

    public int getRequestInterceptorCount() {
        return this.requestInterceptors.size();
    }

    public BasicHttpProcessor copy() {
        BasicHttpProcessor clone = new BasicHttpProcessor();
        this.copyInterceptors(clone);
        return clone;
    }

    public final void addInterceptor(HttpResponseInterceptor interceptor, int index) {
        this.addResponseInterceptor(interceptor, index);
    }

    public void addRequestInterceptor(HttpRequestInterceptor itcp) {
        if (itcp == null) {
            return;
        }
        this.requestInterceptors.add(itcp);
    }

    public HttpResponseInterceptor getResponseInterceptor(int index) {
        if (index < 0) return null;
        if (index < this.responseInterceptors.size()) return this.responseInterceptors.get(index);
        return null;
    }

    public void process(HttpRequest request, HttpContext context) throws IOException, HttpException {
        Iterator<HttpRequestInterceptor> i$ = this.requestInterceptors.iterator();
        while (i$.hasNext()) {
            HttpRequestInterceptor interceptor = i$.next();
            interceptor.process(request, context);
        }
    }

    public void addResponseInterceptor(HttpResponseInterceptor itcp, int index) {
        if (itcp == null) {
            return;
        }
        this.responseInterceptors.add(index, itcp);
    }

    public void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> clazz) {
        Iterator<HttpRequestInterceptor> it = this.requestInterceptors.iterator();
        while (it.hasNext()) {
            HttpRequestInterceptor request = it.next();
            if (!request.getClass().equals(clazz)) continue;
            it.remove();
        }
    }

    public final void addInterceptor(HttpRequestInterceptor interceptor, int index) {
        this.addRequestInterceptor(interceptor, index);
    }

    public void setInterceptors(List<?> list) {
        Args.notNull(list, (String)"Inteceptor list");
        this.requestInterceptors.clear();
        this.responseInterceptors.clear();
        Iterator<?> i$ = list.iterator();
        while (i$.hasNext()) {
            Object obj = i$.next();
            if (obj instanceof HttpRequestInterceptor) {
                this.addInterceptor((HttpRequestInterceptor)obj);
            }
            if (!(obj instanceof HttpResponseInterceptor)) continue;
            this.addInterceptor((HttpResponseInterceptor)obj);
        }
    }

    public final void addInterceptor(HttpRequestInterceptor interceptor) {
        this.addRequestInterceptor(interceptor);
    }

    public void clearResponseInterceptors() {
        this.responseInterceptors.clear();
    }

    public void process(HttpResponse response, HttpContext context) throws IOException, HttpException {
        Iterator<HttpResponseInterceptor> i$ = this.responseInterceptors.iterator();
        while (i$.hasNext()) {
            HttpResponseInterceptor interceptor = i$.next();
            interceptor.process(response, context);
        }
    }

    public void addRequestInterceptor(HttpRequestInterceptor itcp, int index) {
        if (itcp == null) {
            return;
        }
        this.requestInterceptors.add(index, itcp);
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpProcessor clone = (BasicHttpProcessor)super.clone();
        this.copyInterceptors(clone);
        return clone;
    }

    public void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> clazz) {
        Iterator<HttpResponseInterceptor> it = this.responseInterceptors.iterator();
        while (it.hasNext()) {
            HttpResponseInterceptor request = it.next();
            if (!request.getClass().equals(clazz)) continue;
            it.remove();
        }
    }
}
