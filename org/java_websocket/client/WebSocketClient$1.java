/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.client.DnsResolver
 */
package org.java_websocket.client;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import org.java_websocket.client.DnsResolver;

class WebSocketClient.1
implements DnsResolver {
    public InetAddress resolve(URI uri) throws UnknownHostException {
        return InetAddress.getByName(uri.getHost());
    }

    WebSocketClient.1() {
    }
}
