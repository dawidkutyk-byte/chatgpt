/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpMessage
 *  org.apache.http.io.HttpMessageWriter
 *  org.apache.http.io.SessionOutputBuffer
 */
package org.apache.http.io;

import org.apache.http.HttpMessage;
import org.apache.http.io.HttpMessageWriter;
import org.apache.http.io.SessionOutputBuffer;

public interface HttpMessageWriterFactory<T extends HttpMessage> {
    public HttpMessageWriter<T> create(SessionOutputBuffer var1);
}
