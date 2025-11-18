/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequestInterceptor
 */
package org.apache.http.protocol;

import java.util.List;
import org.apache.http.HttpRequestInterceptor;

@Deprecated
public interface HttpRequestInterceptorList {
    public void addRequestInterceptor(HttpRequestInterceptor var1, int var2);

    public void setInterceptors(List<?> var1);

    public void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> var1);

    public void clearRequestInterceptors();

    public HttpRequestInterceptor getRequestInterceptor(int var1);

    public void addRequestInterceptor(HttpRequestInterceptor var1);

    public int getRequestInterceptorCount();
}
