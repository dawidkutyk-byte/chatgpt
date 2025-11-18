/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ProtocolVersion
 */
package org.apache.http;

import org.apache.http.ProtocolVersion;

public interface StatusLine {
    public String getReasonPhrase();

    public ProtocolVersion getProtocolVersion();

    public int getStatusCode();
}
