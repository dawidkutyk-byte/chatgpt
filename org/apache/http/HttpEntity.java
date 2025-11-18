/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 */
package org.apache.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;

public interface HttpEntity {
    public Header getContentType();

    public Header getContentEncoding();

    @Deprecated
    public void consumeContent() throws IOException;

    public boolean isChunked();

    public void writeTo(OutputStream var1) throws IOException;

    public long getContentLength();

    public InputStream getContent() throws IOException, UnsupportedOperationException;

    public boolean isStreaming();

    public boolean isRepeatable();
}
