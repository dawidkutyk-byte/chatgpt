/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpClientConnection
 *  org.apache.http.HttpConnectionFactory
 *  org.apache.http.HttpHost
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.config.ConnectionConfig
 *  org.apache.http.config.SocketConfig
 *  org.apache.http.impl.DefaultBHttpClientConnection
 *  org.apache.http.impl.DefaultBHttpClientConnectionFactory
 *  org.apache.http.params.HttpParamConfig
 *  org.apache.http.params.HttpParams
 *  org.apache.http.pool.ConnFactory
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.impl.pool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnectionFactory;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultBHttpClientConnectionFactory;
import org.apache.http.params.HttpParamConfig;
import org.apache.http.params.HttpParams;
import org.apache.http.pool.ConnFactory;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public class BasicConnFactory
implements ConnFactory<HttpHost, HttpClientConnection> {
    private final int connectTimeout;
    private final SocketFactory plainfactory;
    private final SocketConfig sconfig;
    private final HttpConnectionFactory<? extends HttpClientConnection> connFactory;
    private final SSLSocketFactory sslfactory;

    public BasicConnFactory(SocketFactory plainfactory, SSLSocketFactory sslfactory, int connectTimeout, SocketConfig sconfig, ConnectionConfig cconfig) {
        this.plainfactory = plainfactory;
        this.sslfactory = sslfactory;
        this.connectTimeout = connectTimeout;
        this.sconfig = sconfig != null ? sconfig : SocketConfig.DEFAULT;
        this.connFactory = new DefaultBHttpClientConnectionFactory(cconfig != null ? cconfig : ConnectionConfig.DEFAULT);
    }

    public HttpClientConnection create(HttpHost host) throws IOException {
        Socket socket;
        String scheme = host.getSchemeName();
        if ("http".equalsIgnoreCase(scheme)) {
            socket = this.plainfactory != null ? this.plainfactory.createSocket() : new Socket();
        } else {
            if (!"https".equalsIgnoreCase(scheme)) throw new IOException(scheme + " scheme is not supported");
            socket = (this.sslfactory != null ? this.sslfactory : SSLSocketFactory.getDefault()).createSocket();
        }
        String hostname = host.getHostName();
        int port = host.getPort();
        if (port == -1) {
            if (host.getSchemeName().equalsIgnoreCase("http")) {
                port = 80;
            } else if (host.getSchemeName().equalsIgnoreCase("https")) {
                port = 443;
            }
        }
        socket.setSoTimeout(this.sconfig.getSoTimeout());
        if (this.sconfig.getSndBufSize() > 0) {
            socket.setSendBufferSize(this.sconfig.getSndBufSize());
        }
        if (this.sconfig.getRcvBufSize() > 0) {
            socket.setReceiveBufferSize(this.sconfig.getRcvBufSize());
        }
        socket.setTcpNoDelay(this.sconfig.isTcpNoDelay());
        int linger = this.sconfig.getSoLinger();
        if (linger >= 0) {
            socket.setSoLinger(true, linger);
        }
        socket.setKeepAlive(this.sconfig.isSoKeepAlive());
        InetSocketAddress address = new InetSocketAddress(hostname, port);
        try {
            AccessController.doPrivileged(new /* Unavailable Anonymous Inner Class!! */);
        }
        catch (PrivilegedActionException e) {
            Asserts.check((boolean)(e.getCause() instanceof IOException), (String)("method contract violation only checked exceptions are wrapped: " + e.getCause()));
            throw (IOException)e.getCause();
        }
        return (HttpClientConnection)this.connFactory.createConnection(socket);
    }

    @Deprecated
    public BasicConnFactory(HttpParams params) {
        this(null, params);
    }

    @Deprecated
    protected HttpClientConnection create(Socket socket, HttpParams params) throws IOException {
        int bufsize = params.getIntParameter("http.socket.buffer-size", 8192);
        DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(bufsize);
        conn.bind(socket);
        return conn;
    }

    public BasicConnFactory(SocketConfig sconfig, ConnectionConfig cconfig) {
        this(null, null, 0, sconfig, cconfig);
    }

    @Deprecated
    public BasicConnFactory(SSLSocketFactory sslfactory, HttpParams params) {
        Args.notNull((Object)params, (String)"HTTP params");
        this.plainfactory = null;
        this.sslfactory = sslfactory;
        this.connectTimeout = params.getIntParameter("http.connection.timeout", 0);
        this.sconfig = HttpParamConfig.getSocketConfig((HttpParams)params);
        this.connFactory = new DefaultBHttpClientConnectionFactory(HttpParamConfig.getConnectionConfig((HttpParams)params));
    }

    public BasicConnFactory(int connectTimeout, SocketConfig sconfig, ConnectionConfig cconfig) {
        this(null, null, connectTimeout, sconfig, cconfig);
    }

    static /* synthetic */ int access$000(BasicConnFactory x0) {
        return x0.connectTimeout;
    }

    public BasicConnFactory() {
        this(null, null, 0, SocketConfig.DEFAULT, ConnectionConfig.DEFAULT);
    }
}
