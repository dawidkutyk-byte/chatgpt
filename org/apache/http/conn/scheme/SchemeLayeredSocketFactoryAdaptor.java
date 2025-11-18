/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.scheme.LayeredSocketFactory
 *  org.apache.http.conn.scheme.SchemeLayeredSocketFactory
 *  org.apache.http.conn.scheme.SchemeSocketFactoryAdaptor
 *  org.apache.http.conn.scheme.SocketFactory
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SchemeLayeredSocketFactory;
import org.apache.http.conn.scheme.SchemeSocketFactoryAdaptor;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpParams;

@Deprecated
class SchemeLayeredSocketFactoryAdaptor
extends SchemeSocketFactoryAdaptor
implements SchemeLayeredSocketFactory {
    private final LayeredSocketFactory factory;

    public Socket createLayeredSocket(Socket socket, String target, int port, HttpParams params) throws UnknownHostException, IOException {
        return this.factory.createSocket(socket, target, port, true);
    }

    SchemeLayeredSocketFactoryAdaptor(LayeredSocketFactory factory) {
        super((SocketFactory)factory);
        this.factory = factory;
    }
}
