/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http;

import org.apache.http.Header;
import org.apache.http.util.CharArrayBuffer;

public interface FormattedHeader
extends Header {
    public CharArrayBuffer getBuffer();

    public int getValuePos();
}
