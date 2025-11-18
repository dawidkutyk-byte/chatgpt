/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.io.HttpTransportMetrics
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.io;

import java.io.IOException;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.util.CharArrayBuffer;

public interface SessionOutputBuffer {
    public void write(int var1) throws IOException;

    public void writeLine(String var1) throws IOException;

    public void write(byte[] var1) throws IOException;

    public void flush() throws IOException;

    public void writeLine(CharArrayBuffer var1) throws IOException;

    public HttpTransportMetrics getMetrics();

    public void write(byte[] var1, int var2, int var3) throws IOException;
}
