/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ConnectTimeoutException
 *  org.apache.http.conn.scheme.SchemeSocketFactory
 *  org.apache.http.conn.scheme.SocketFactory
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

@Deprecated
class SocketFactoryAdaptor
implements SocketFactory {
    private final SchemeSocketFactory factory;

    public SchemeSocketFactory getFactory() {
        return this.factory;
    }

    SocketFactoryAdaptor(SchemeSocketFactory factory) {
        this.factory = factory;
    }

    public boolean isSecure(Socket socket) throws IllegalArgumentException {
        return this.factory.isSecure(socket);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this != obj) return obj instanceof SocketFactoryAdaptor ? this.factory.equals(((SocketFactoryAdaptor)obj).factory) : this.factory.equals(obj);
        return true;
    }

    public int hashCode() {
        return this.factory.hashCode();
    }

    public Socket createSocket() throws IOException {
        BasicHttpParams params = new BasicHttpParams();
        return this.factory.createSocket((HttpParams)params);
    }

    public Socket connectSocket(Socket socket, String host, int port, InetAddress localAddress, int localPort, HttpParams params) throws UnknownHostException, ConnectTimeoutException, IOException {
        InetSocketAddress local = null;
        if (localAddress != null || localPort > 0) {
            local = new InetSocketAddress(localAddress, localPort > 0 ? localPort : 0);
        }
        InetAddress remoteAddress = InetAddress.getByName(host);
        InetSocketAddress remote = new InetSocketAddress(remoteAddress, port);
        return this.factory.connectSocket(socket, remote, local, params);
    }
}
