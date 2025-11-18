/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.RequestLine
 *  org.apache.http.StatusLine
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.message;

import org.apache.http.Header;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;

public interface LineFormatter {
    public CharArrayBuffer formatHeader(CharArrayBuffer var1, Header var2);

    public CharArrayBuffer appendProtocolVersion(CharArrayBuffer var1, ProtocolVersion var2);

    public CharArrayBuffer formatRequestLine(CharArrayBuffer var1, RequestLine var2);

    public CharArrayBuffer formatStatusLine(CharArrayBuffer var1, StatusLine var2);
}
