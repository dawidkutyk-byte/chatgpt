/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpResponse
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.StatusLine
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.protocol.HttpContext;

public interface HttpResponseFactory {
    public HttpResponse newHttpResponse(StatusLine var1, HttpContext var2);

    public HttpResponse newHttpResponse(ProtocolVersion var1, int var2, HttpContext var3);
}
