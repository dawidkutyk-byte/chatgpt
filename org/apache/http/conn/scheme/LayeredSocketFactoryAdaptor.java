/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.scheme.LayeredSchemeSocketFactory
 *  org.apache.http.conn.scheme.LayeredSocketFactory
 *  org.apache.http.conn.scheme.SchemeSocketFactory
 *  org.apache.http.conn.scheme.SocketFactoryAdaptor
 */
package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.conn.scheme.SocketFactoryAdaptor;

@Deprecated
class LayeredSocketFactoryAdaptor
extends SocketFactoryAdaptor
implements LayeredSocketFactory {
    private final LayeredSchemeSocketFactory factory;

    LayeredSocketFactoryAdaptor(LayeredSchemeSocketFactory factory) {
        super((SchemeSocketFactory)factory);
        this.factory = factory;
    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws UnknownHostException, IOException {
        return this.factory.createLayeredSocket(socket, host, port, autoClose);
    }
}
