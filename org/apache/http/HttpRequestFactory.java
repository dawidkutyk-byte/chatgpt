/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpRequest
 *  org.apache.http.MethodNotSupportedException
 *  org.apache.http.RequestLine
 */
package org.apache.http;

import org.apache.http.HttpRequest;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.RequestLine;

public interface HttpRequestFactory {
    public HttpRequest newHttpRequest(RequestLine var1) throws MethodNotSupportedException;

    public HttpRequest newHttpRequest(String var1, String var2) throws MethodNotSupportedException;
}
