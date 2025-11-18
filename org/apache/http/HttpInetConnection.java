/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpConnection
 */
package org.apache.http;

import java.net.InetAddress;
import org.apache.http.HttpConnection;

public interface HttpInetConnection
extends HttpConnection {
    public int getRemotePort();

    public InetAddress getLocalAddress();

    public int getLocalPort();

    public InetAddress getRemoteAddress();
}
