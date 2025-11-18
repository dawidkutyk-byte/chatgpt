/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpHost
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.ConnectTimeoutException
 *  org.apache.http.conn.HttpInetSocketAddress
 *  org.apache.http.conn.scheme.HostNameResolver
 *  org.apache.http.conn.scheme.LayeredSchemeSocketFactory
 *  org.apache.http.conn.scheme.LayeredSocketFactory
 *  org.apache.http.conn.scheme.SchemeLayeredSocketFactory
 *  org.apache.http.conn.socket.LayeredConnectionSocketFactory
 *  org.apache.http.conn.ssl.AllowAllHostnameVerifier
 *  org.apache.http.conn.ssl.BrowserCompatHostnameVerifier
 *  org.apache.http.conn.ssl.SSLContexts
 *  org.apache.http.conn.ssl.SSLInitializationException
 *  org.apache.http.conn.ssl.StrictHostnameVerifier
 *  org.apache.http.conn.ssl.TrustStrategy
 *  org.apache.http.conn.ssl.X509HostnameVerifier
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 *  org.apache.http.util.TextUtils
 */
package org.apache.http.conn.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpInetSocketAddress;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SchemeLayeredSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLInitializationException;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;
import org.apache.http.util.TextUtils;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class SSLSocketFactory
implements LayeredSchemeSocketFactory,
SchemeLayeredSocketFactory,
LayeredSocketFactory,
LayeredConnectionSocketFactory {
    private final String[] supportedProtocols;
    private final String[] supportedCipherSuites;
    public static final String SSLV2 = "SSLv2";
    public static final String TLS = "TLS";
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER;
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER;
    public static final String SSL = "SSL";
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
    private volatile X509HostnameVerifier hostnameVerifier;
    private final HostNameResolver nameResolver;
    private final javax.net.ssl.SSLSocketFactory socketfactory;

    public Socket createLayeredSocket(Socket socket, String target, int port, HttpContext context) throws IOException {
        SSLSocket sslsock = (SSLSocket)this.socketfactory.createSocket(socket, target, port, true);
        this.internalPrepareSocket(sslsock);
        sslsock.startHandshake();
        this.verifyHostname(sslsock, target);
        return sslsock;
    }

    public SSLSocketFactory(String algorithm, KeyStore keystore, String keyPassword, KeyStore truststore, SecureRandom random, TrustStrategy trustStrategy, X509HostnameVerifier hostnameVerifier) throws KeyManagementException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        this(SSLContexts.custom().useProtocol(algorithm).setSecureRandom(random).loadKeyMaterial(keystore, keyPassword != null ? keyPassword.toCharArray() : null).loadTrustMaterial(truststore, trustStrategy).build(), hostnameVerifier);
    }

    public SSLSocketFactory(javax.net.ssl.SSLSocketFactory socketfactory, X509HostnameVerifier hostnameVerifier) {
        this(socketfactory, null, null, hostnameVerifier);
    }

    public Socket createSocket(HttpContext context) throws IOException {
        return SocketFactory.getDefault().createSocket();
    }

    private static String[] split(String s) {
        if (!TextUtils.isBlank((CharSequence)s)) return s.split(" *, *");
        return null;
    }

    public Socket createSocket(HttpParams params) throws IOException {
        return this.createSocket((HttpContext)null);
    }

    public SSLSocketFactory(String algorithm, KeyStore keystore, String keyPassword, KeyStore truststore, SecureRandom random, X509HostnameVerifier hostnameVerifier) throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        this(SSLContexts.custom().useProtocol(algorithm).setSecureRandom(random).loadKeyMaterial(keystore, keyPassword != null ? keyPassword.toCharArray() : null).loadTrustMaterial(truststore).build(), hostnameVerifier);
    }

    public Socket connectSocket(int connectTimeout, Socket socket, HttpHost host, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpContext context) throws IOException {
        Socket sock;
        Args.notNull((Object)host, (String)"HTTP host");
        Args.notNull((Object)remoteAddress, (String)"Remote address");
        Socket socket2 = sock = socket != null ? socket : this.createSocket(context);
        if (localAddress != null) {
            sock.bind(localAddress);
        }
        try {
            sock.connect(remoteAddress, connectTimeout);
        }
        catch (SocketTimeoutException ex) {
            throw new ConnectTimeoutException("Connect to " + remoteAddress + " timed out");
        }
        if (!(sock instanceof SSLSocket)) return this.createLayeredSocket(sock, host.getHostName(), remoteAddress.getPort(), context);
        SSLSocket sslsock = (SSLSocket)sock;
        sslsock.startHandshake();
        this.verifyHostname(sslsock, host.getHostName());
        return sock;
    }

    public X509HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public SSLSocketFactory(String algorithm, KeyStore keystore, String keyPassword, KeyStore truststore, SecureRandom random, HostNameResolver nameResolver) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException, UnrecoverableKeyException {
        this(SSLContexts.custom().useProtocol(algorithm).setSecureRandom(random).loadKeyMaterial(keystore, keyPassword != null ? keyPassword.toCharArray() : null).loadTrustMaterial(truststore).build(), nameResolver);
    }

    public SSLSocketFactory(TrustStrategy trustStrategy, X509HostnameVerifier hostnameVerifier) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, UnrecoverableKeyException {
        this(SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build(), hostnameVerifier);
    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws UnknownHostException, IOException {
        return this.createLayeredSocket(socket, host, port, autoClose);
    }

    public SSLSocketFactory(javax.net.ssl.SSLSocketFactory socketfactory, String[] supportedProtocols, String[] supportedCipherSuites, X509HostnameVerifier hostnameVerifier) {
        this.socketfactory = (javax.net.ssl.SSLSocketFactory)Args.notNull((Object)socketfactory, (String)"SSL socket factory");
        this.supportedProtocols = supportedProtocols;
        this.supportedCipherSuites = supportedCipherSuites;
        this.hostnameVerifier = hostnameVerifier != null ? hostnameVerifier : BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        this.nameResolver = null;
    }

    public SSLSocketFactory(TrustStrategy trustStrategy) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
        this(SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(SSLContext sslContext, HostNameResolver nameResolver) {
        this.socketfactory = sslContext.getSocketFactory();
        this.hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        this.nameResolver = nameResolver;
        this.supportedProtocols = null;
        this.supportedCipherSuites = null;
    }

    public Socket connectSocket(Socket socket, String host, int port, InetAddress local, int localPort, HttpParams params) throws UnknownHostException, IOException, ConnectTimeoutException {
        InetAddress remote = this.nameResolver != null ? this.nameResolver.resolve(host) : InetAddress.getByName(host);
        InetSocketAddress localAddress = null;
        if (local != null || localPort > 0) {
            localAddress = new InetSocketAddress(local, localPort > 0 ? localPort : 0);
        }
        HttpInetSocketAddress remoteAddress = new HttpInetSocketAddress(new HttpHost(host, port), remote, port);
        return this.connectSocket(socket, (InetSocketAddress)remoteAddress, localAddress, params);
    }

    private void verifyHostname(SSLSocket sslsock, String hostname) throws IOException {
        try {
            this.hostnameVerifier.verify(hostname, sslsock);
        }
        catch (IOException iox) {
            try {
                sslsock.close();
            }
            catch (Exception x) {
                // empty catch block
            }
            throw iox;
        }
    }

    public SSLSocketFactory(SSLContext sslContext, String[] supportedProtocols, String[] supportedCipherSuites, X509HostnameVerifier hostnameVerifier) {
        this(((SSLContext)Args.notNull((Object)sslContext, (String)"SSL context")).getSocketFactory(), supportedProtocols, supportedCipherSuites, hostnameVerifier);
    }

    public boolean isSecure(Socket sock) throws IllegalArgumentException {
        Args.notNull((Object)sock, (String)"Socket");
        Asserts.check((boolean)(sock instanceof SSLSocket), (String)"Socket not created by this factory");
        Asserts.check((!sock.isClosed() ? 1 : 0) != 0, (String)"Socket is closed");
        return true;
    }

    public Socket createLayeredSocket(Socket socket, String host, int port, boolean autoClose) throws UnknownHostException, IOException {
        return this.createLayeredSocket(socket, host, port, (HttpContext)null);
    }

    public SSLSocketFactory(KeyStore truststore) throws UnrecoverableKeyException, KeyStoreException, KeyManagementException, NoSuchAlgorithmException {
        this(SSLContexts.custom().loadTrustMaterial(truststore).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public Socket createSocket() throws IOException {
        return this.createSocket((HttpContext)null);
    }

    public SSLSocketFactory(SSLContext sslContext) {
        this(sslContext, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    protected void prepareSocket(SSLSocket socket) throws IOException {
    }

    public static SSLSocketFactory getSocketFactory() throws SSLInitializationException {
        return new SSLSocketFactory(SSLContexts.createDefault(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public void setHostnameVerifier(X509HostnameVerifier hostnameVerifier) {
        Args.notNull((Object)hostnameVerifier, (String)"Hostname verifier");
        this.hostnameVerifier = hostnameVerifier;
    }

    public Socket createLayeredSocket(Socket socket, String host, int port, HttpParams params) throws IOException, UnknownHostException {
        return this.createLayeredSocket(socket, host, port, (HttpContext)null);
    }

    public static SSLSocketFactory getSystemSocketFactory() throws SSLInitializationException {
        return new SSLSocketFactory((javax.net.ssl.SSLSocketFactory)javax.net.ssl.SSLSocketFactory.getDefault(), SSLSocketFactory.split(System.getProperty("https.protocols")), SSLSocketFactory.split(System.getProperty("https.cipherSuites")), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(SSLContext sslContext, X509HostnameVerifier hostnameVerifier) {
        this(((SSLContext)Args.notNull((Object)sslContext, (String)"SSL context")).getSocketFactory(), null, null, hostnameVerifier);
    }

    public SSLSocketFactory(KeyStore keystore, String keystorePassword) throws KeyStoreException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException {
        this(SSLContexts.custom().loadKeyMaterial(keystore, keystorePassword != null ? keystorePassword.toCharArray() : null).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    public SSLSocketFactory(KeyStore keystore, String keystorePassword, KeyStore truststore) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException, KeyStoreException {
        this(SSLContexts.custom().loadKeyMaterial(keystore, keystorePassword != null ? keystorePassword.toCharArray() : null).loadTrustMaterial(truststore).build(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }

    static {
        ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
        BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
        STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
    }

    private void internalPrepareSocket(SSLSocket socket) throws IOException {
        if (this.supportedProtocols != null) {
            socket.setEnabledProtocols(this.supportedProtocols);
        }
        if (this.supportedCipherSuites != null) {
            socket.setEnabledCipherSuites(this.supportedCipherSuites);
        }
        this.prepareSocket(socket);
    }

    public Socket connectSocket(Socket socket, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params) throws UnknownHostException, ConnectTimeoutException, IOException {
        Args.notNull((Object)remoteAddress, (String)"Remote address");
        Args.notNull((Object)params, (String)"HTTP parameters");
        HttpHost host = remoteAddress instanceof HttpInetSocketAddress ? ((HttpInetSocketAddress)remoteAddress).getHttpHost() : new HttpHost(remoteAddress.getHostName(), remoteAddress.getPort(), "https");
        int socketTimeout = HttpConnectionParams.getSoTimeout((HttpParams)params);
        int connectTimeout = HttpConnectionParams.getConnectionTimeout((HttpParams)params);
        socket.setSoTimeout(socketTimeout);
        return this.connectSocket(connectTimeout, socket, host, remoteAddress, localAddress, null);
    }
}
