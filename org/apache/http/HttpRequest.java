/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpMessage
 *  org.apache.http.RequestLine
 */
package org.apache.http;

import org.apache.http.HttpMessage;
import org.apache.http.RequestLine;

public interface HttpRequest
extends HttpMessage {
    public RequestLine getRequestLine();
}
