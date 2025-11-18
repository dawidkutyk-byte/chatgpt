/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpHost
 */
package org.apache.http.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.util.Arrays;
import org.apache.http.HttpHost;

public class ConnectTimeoutException
extends InterruptedIOException {
    private final HttpHost host;
    private static final long serialVersionUID = -4816682903149535989L;

    public ConnectTimeoutException(IOException cause, HttpHost host, InetAddress ... remoteAddresses) {
        super("Connect to " + (host != null ? host.toHostString() : "remote host") + (remoteAddresses != null && remoteAddresses.length > 0 ? " " + Arrays.asList(remoteAddresses) : "") + (cause != null && cause.getMessage() != null ? " failed: " + cause.getMessage() : " timed out"));
        this.host = host;
        this.initCause(cause);
    }

    public ConnectTimeoutException() {
        this.host = null;
    }

    public HttpHost getHost() {
        return this.host;
    }

    public ConnectTimeoutException(String message) {
        super(message);
        this.host = null;
    }
}
