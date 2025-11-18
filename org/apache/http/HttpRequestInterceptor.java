/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpException
 *  org.apache.http.HttpRequest
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

public interface HttpRequestInterceptor {
    public void process(HttpRequest var1, HttpContext var2) throws HttpException, IOException;
}
