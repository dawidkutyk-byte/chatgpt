/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.AbstractWebSocket
 *  org.java_websocket.WebSocket
 *  org.java_websocket.WebSocketImpl
 *  org.java_websocket.WebSocketListener
 *  org.java_websocket.client.DnsResolver
 *  org.java_websocket.client.WebSocketClient$WebsocketWriteThread
 *  org.java_websocket.drafts.Draft
 *  org.java_websocket.drafts.Draft_6455
 *  org.java_websocket.enums.Opcode
 *  org.java_websocket.enums.ReadyState
 *  org.java_websocket.exceptions.InvalidHandshakeException
 *  org.java_websocket.framing.Framedata
 *  org.java_websocket.handshake.ClientHandshakeBuilder
 *  org.java_websocket.handshake.HandshakeImpl1Client
 *  org.java_websocket.handshake.Handshakedata
 *  org.java_websocket.handshake.ServerHandshake
 *  org.java_websocket.protocols.IProtocol
 */
package org.java_websocket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketListener;
import org.java_websocket.client.DnsResolver;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeImpl1Client;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.protocols.IProtocol;

public abstract class WebSocketClient
extends AbstractWebSocket
implements Runnable,
WebSocket {
    private CountDownLatch \u015aJD4;
    private DnsResolver Z\u00f30\u0142 = null;
    private Proxy \u00d3u\u015aC = Proxy.NO_PROXY;
    private Thread Jk9D;
    private Socket Ml\u0118n = null;
    protected URI Ay8f = null;
    private OutputStream ZA\u0107\u017c;
    private SocketFactory \u01440S5 = null;
    private Map<String, String> B78Z;
    private Draft \u015a5qj;
    private int np3\u0119 = 0;
    private WebSocketImpl N\u0144Fs = null;
    private Thread \u00d3vKo;
    private CountDownLatch FV\u015bg = new CountDownLatch(1);

    public void addHeader(String key, String value) {
        if (this.B78Z == null) {
            this.B78Z = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        }
        this.B78Z.put(key, value);
    }

    public boolean isOpen() {
        return this.N\u0144Fs.isOpen();
    }

    public final void onWriteDemand(WebSocket conn) {
    }

    private int getPort() {
        int port = this.Ay8f.getPort();
        String scheme = this.Ay8f.getScheme();
        if ("wss".equals(scheme)) {
            return port == -1 ? 443 : port;
        }
        if (!"ws".equals(scheme)) throw new IllegalArgumentException("unknown scheme: " + scheme);
        return port == -1 ? 80 : port;
    }

    public void sendFragmentedFrame(Opcode op, ByteBuffer buffer, boolean fin) {
        this.N\u0144Fs.sendFragmentedFrame(op, buffer, fin);
    }

    public abstract void onMessage(String var1);

    public Draft getDraft() {
        return this.\u015a5qj;
    }

    public final void onWebsocketError(WebSocket conn, Exception ex) {
        this.onError(ex);
    }

    public void onMessage(ByteBuffer bytes) {
    }

    public void send(String text) {
        this.N\u0144Fs.send(text);
    }

    public boolean isFlushAndClose() {
        return this.N\u0144Fs.isFlushAndClose();
    }

    public String removeHeader(String key) {
        if (this.B78Z != null) return this.B78Z.remove(key);
        return null;
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return this.N\u0144Fs.getRemoteSocketAddress();
    }

    protected void onSetSSLParameters(SSLParameters sslParameters) {
        sslParameters.setEndpointIdentificationAlgorithm("HTTPS");
    }

    public void closeBlocking() throws InterruptedException {
        this.close();
        this.\u015aJD4.await();
    }

    public void close() {
        if (this.\u00d3vKo == null) return;
        this.N\u0144Fs.close(1000);
    }

    public boolean connectBlocking() throws InterruptedException {
        this.connect();
        this.FV\u015bg.await();
        return this.N\u0144Fs.isOpen();
    }

    public void close(int code, String message) {
        this.N\u0144Fs.close(code, message);
    }

    public void sendFrame(Framedata framedata) {
        this.N\u0144Fs.sendFrame(framedata);
    }

    static /* synthetic */ Socket access$400(WebSocketClient x0) {
        return x0.Ml\u0118n;
    }

    public WebSocket getConnection() {
        return this.N\u0144Fs;
    }

    static /* synthetic */ OutputStream access$300(WebSocketClient x0) {
        return x0.ZA\u0107\u017c;
    }

    public WebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        this.\u015aJD4 = new CountDownLatch(1);
        if (serverUri == null) {
            throw new IllegalArgumentException();
        }
        if (protocolDraft == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        }
        this.Ay8f = serverUri;
        this.\u015a5qj = protocolDraft;
        this.Z\u00f30\u0142 = new /* Unavailable Anonymous Inner Class!! */;
        if (httpHeaders != null) {
            this.B78Z = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
            this.B78Z.putAll(httpHeaders);
        }
        this.np3\u0119 = connectTimeout;
        this.setTcpNoDelay(false);
        this.setReuseAddr(false);
        this.N\u0144Fs = new WebSocketImpl((WebSocketListener)this, protocolDraft);
    }

    public void sendPing() {
        this.N\u0144Fs.sendPing();
    }

    public boolean connectBlocking(long timeout, TimeUnit timeUnit) throws InterruptedException {
        this.connect();
        return this.FV\u015bg.await(timeout, timeUnit) && this.N\u0144Fs.isOpen();
    }

    public final void onWebsocketMessage(WebSocket conn, ByteBuffer blob) {
        this.onMessage(blob);
    }

    public void send(ByteBuffer bytes) {
        this.N\u0144Fs.send(bytes);
    }

    public void setProxy(Proxy proxy) {
        if (proxy == null) {
            throw new IllegalArgumentException();
        }
        this.\u00d3u\u015aC = proxy;
    }

    public boolean isClosing() {
        return this.N\u0144Fs.isClosing();
    }

    protected Collection<WebSocket> getConnections() {
        return Collections.singletonList(this.N\u0144Fs);
    }

    public void reconnect() {
        this.reset();
        this.connect();
    }

    public abstract void onClose(int var1, String var2, boolean var3);

    public void setSocketFactory(SocketFactory socketFactory) {
        this.\u01440S5 = socketFactory;
    }

    @Deprecated
    public void setSocket(Socket socket) {
        if (this.Ml\u0118n != null) {
            throw new IllegalStateException("socket has already been set");
        }
        this.Ml\u0118n = socket;
    }

    public void onCloseInitiated(int code, String reason) {
    }

    public String getResourceDescriptor() {
        return this.Ay8f.getPath();
    }

    public void setDnsResolver(DnsResolver dnsResolver) {
        this.Z\u00f30\u0142 = dnsResolver;
    }

    public boolean isClosed() {
        return this.N\u0144Fs.isClosed();
    }

    private void handleIOException(IOException e) {
        if (e instanceof SSLException) {
            this.onError(e);
        }
        this.N\u0144Fs.eot();
    }

    public void send(byte[] data) {
        this.N\u0144Fs.send(data);
    }

    public void clearHeaders() {
        this.B78Z = null;
    }

    public void connect() {
        if (this.Jk9D != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        this.Jk9D = new Thread(this);
        this.Jk9D.setName("WebSocketConnectReadThread-" + this.Jk9D.getId());
        this.Jk9D.start();
    }

    private boolean prepareSocket() throws IOException {
        boolean upgradeSocketToSSLSocket = false;
        if (this.\u00d3u\u015aC != Proxy.NO_PROXY) {
            this.Ml\u0118n = new Socket(this.\u00d3u\u015aC);
            upgradeSocketToSSLSocket = true;
        } else if (this.\u01440S5 != null) {
            this.Ml\u0118n = this.\u01440S5.createSocket();
        } else {
            if (this.Ml\u0118n != null) {
                if (!this.Ml\u0118n.isClosed()) return upgradeSocketToSSLSocket;
                throw new IOException();
            }
            this.Ml\u0118n = new Socket(this.\u00d3u\u015aC);
            upgradeSocketToSSLSocket = true;
        }
        return upgradeSocketToSSLSocket;
    }

    public SSLSession getSSLSession() {
        if (this.hasSSLSupport()) return ((SSLSocket)this.Ml\u0118n).getSession();
        throw new IllegalArgumentException("This websocket uses ws instead of wss. No SSLSession available.");
    }

    public InetSocketAddress getLocalSocketAddress() {
        return this.N\u0144Fs.getLocalSocketAddress();
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket conn) {
        if (this.Ml\u0118n == null) return null;
        return (InetSocketAddress)this.Ml\u0118n.getRemoteSocketAddress();
    }

    static /* synthetic */ WebSocketImpl access$200(WebSocketClient x0) {
        return x0.N\u0144Fs;
    }

    static /* synthetic */ void access$000(WebSocketClient x0, IOException x1) {
        x0.handleIOException(x1);
    }

    static /* synthetic */ Thread access$102(WebSocketClient x0, Thread x1) {
        x0.\u00d3vKo = x1;
        return x0.\u00d3vKo;
    }

    public final void onWebsocketMessage(WebSocket conn, String message) {
        this.onMessage(message);
    }

    public void onWebsocketCloseInitiated(WebSocket conn, int code, String reason) {
        this.onCloseInitiated(code, reason);
    }

    public abstract void onOpen(ServerHandshake var1);

    public InetSocketAddress getLocalSocketAddress(WebSocket conn) {
        if (this.Ml\u0118n == null) return null;
        return (InetSocketAddress)this.Ml\u0118n.getLocalSocketAddress();
    }

    public final void onWebsocketClose(WebSocket conn, int code, String reason, boolean remote) {
        this.stopConnectionLostTimer();
        if (this.\u00d3vKo != null) {
            this.\u00d3vKo.interrupt();
        }
        this.onClose(code, reason, remote);
        this.FV\u015bg.countDown();
        this.\u015aJD4.countDown();
    }

    private void sendHandshake() throws InvalidHandshakeException {
        String part1 = this.Ay8f.getRawPath();
        String part2 = this.Ay8f.getRawQuery();
        String path = part1 == null || part1.length() == 0 ? "/" : part1;
        if (part2 != null) {
            path = path + '?' + part2;
        }
        int port = this.getPort();
        String host = this.Ay8f.getHost() + (port != 80 && port != 443 ? ":" + port : "");
        HandshakeImpl1Client handshake = new HandshakeImpl1Client();
        handshake.setResourceDescriptor(path);
        handshake.put("Host", host);
        if (this.B78Z != null) {
            for (Map.Entry<String, String> kv : this.B78Z.entrySet()) {
                handshake.put(kv.getKey(), kv.getValue());
            }
        }
        this.N\u0144Fs.startHandshake((ClientHandshakeBuilder)handshake);
    }

    public void onWebsocketClosing(WebSocket conn, int code, String reason, boolean remote) {
        this.onClosing(code, reason, remote);
    }

    public WebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
        this(serverUri, protocolDraft, httpHeaders, 0);
    }

    @Override
    public void run() {
        InputStream istream;
        try {
            boolean upgradeSocketToSSLSocket = this.prepareSocket();
            this.Ml\u0118n.setTcpNoDelay(this.isTcpNoDelay());
            this.Ml\u0118n.setReuseAddress(this.isReuseAddr());
            if (!this.Ml\u0118n.isConnected()) {
                InetSocketAddress addr = this.Z\u00f30\u0142 == null ? InetSocketAddress.createUnresolved(this.Ay8f.getHost(), this.getPort()) : new InetSocketAddress(this.Z\u00f30\u0142.resolve(this.Ay8f), this.getPort());
                this.Ml\u0118n.connect(addr, this.np3\u0119);
            }
            if (upgradeSocketToSSLSocket && "wss".equals(this.Ay8f.getScheme())) {
                this.upgradeSocketToSSL();
            }
            if (this.Ml\u0118n instanceof SSLSocket) {
                SSLSocket sslSocket = (SSLSocket)this.Ml\u0118n;
                SSLParameters sslParameters = sslSocket.getSSLParameters();
                this.onSetSSLParameters(sslParameters);
                sslSocket.setSSLParameters(sslParameters);
            }
            istream = this.Ml\u0118n.getInputStream();
            this.ZA\u0107\u017c = this.Ml\u0118n.getOutputStream();
            this.sendHandshake();
        }
        catch (Exception e) {
            this.onWebsocketError((WebSocket)this.N\u0144Fs, e);
            this.N\u0144Fs.closeConnection(-1, e.getMessage());
            return;
        }
        catch (InternalError e) {
            if (!(e.getCause() instanceof InvocationTargetException)) throw e;
            if (!(e.getCause().getCause() instanceof IOException)) throw e;
            IOException cause = (IOException)e.getCause().getCause();
            this.onWebsocketError((WebSocket)this.N\u0144Fs, cause);
            this.N\u0144Fs.closeConnection(-1, cause.getMessage());
            return;
        }
        this.\u00d3vKo = new Thread((Runnable)new WebsocketWriteThread(this, this));
        this.\u00d3vKo.start();
        byte[] rawbuffer = new byte[16384];
        try {
            int readBytes;
            while (!this.isClosing() && !this.isClosed() && (readBytes = istream.read(rawbuffer)) != -1) {
                this.N\u0144Fs.decode(ByteBuffer.wrap(rawbuffer, 0, readBytes));
            }
            this.N\u0144Fs.eot();
        }
        catch (IOException e) {
            this.handleIOException(e);
        }
        catch (RuntimeException e) {
            this.onError(e);
            this.N\u0144Fs.closeConnection(1006, e.getMessage());
        }
        this.Jk9D = null;
    }

    private void reset() {
        Thread current = Thread.currentThread();
        if (current == this.\u00d3vKo) throw new IllegalStateException("You cannot initialize a reconnect out of the websocket thread. Use reconnect in another thread to ensure a successful cleanup.");
        if (current == this.Jk9D) {
            throw new IllegalStateException("You cannot initialize a reconnect out of the websocket thread. Use reconnect in another thread to ensure a successful cleanup.");
        }
        try {
            this.closeBlocking();
            if (this.\u00d3vKo != null) {
                this.\u00d3vKo.interrupt();
                this.\u00d3vKo = null;
            }
            if (this.Jk9D != null) {
                this.Jk9D.interrupt();
                this.Jk9D = null;
            }
            this.\u015a5qj.reset();
            if (this.Ml\u0118n != null) {
                this.Ml\u0118n.close();
                this.Ml\u0118n = null;
            }
        }
        catch (Exception e) {
            this.onError(e);
            this.N\u0144Fs.closeConnection(1006, e.getMessage());
            return;
        }
        this.FV\u015bg = new CountDownLatch(1);
        this.\u015aJD4 = new CountDownLatch(1);
        this.N\u0144Fs = new WebSocketImpl((WebSocketListener)this, this.\u015a5qj);
    }

    public abstract void onError(Exception var1);

    public URI getURI() {
        return this.Ay8f;
    }

    public ReadyState getReadyState() {
        return this.N\u0144Fs.getReadyState();
    }

    public IProtocol getProtocol() {
        return this.N\u0144Fs.getProtocol();
    }

    public boolean reconnectBlocking() throws InterruptedException {
        this.reset();
        return this.connectBlocking();
    }

    public boolean hasSSLSupport() {
        return this.Ml\u0118n instanceof SSLSocket;
    }

    public <T> void setAttachment(T attachment) {
        this.N\u0144Fs.setAttachment(attachment);
    }

    public final void onWebsocketOpen(WebSocket conn, Handshakedata handshake) {
        this.startConnectionLostTimer();
        this.onOpen((ServerHandshake)handshake);
        this.FV\u015bg.countDown();
    }

    public void sendFrame(Collection<Framedata> frames) {
        this.N\u0144Fs.sendFrame(frames);
    }

    public WebSocketClient(URI serverUri, Draft protocolDraft) {
        this(serverUri, protocolDraft, null, 0);
    }

    public void close(int code) {
        this.N\u0144Fs.close(code);
    }

    public <T> T getAttachment() {
        return (T)this.N\u0144Fs.getAttachment();
    }

    public void onClosing(int code, String reason, boolean remote) {
    }

    public boolean hasBufferedData() {
        return this.N\u0144Fs.hasBufferedData();
    }

    public Socket getSocket() {
        return this.Ml\u0118n;
    }

    private void upgradeSocketToSSL() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        SSLSocketFactory factory;
        if (this.\u01440S5 instanceof SSLSocketFactory) {
            factory = (SSLSocketFactory)this.\u01440S5;
        } else {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            factory = sslContext.getSocketFactory();
        }
        this.Ml\u0118n = factory.createSocket(this.Ml\u0118n, this.Ay8f.getHost(), this.getPort(), true);
    }

    public WebSocketClient(URI serverUri) {
        this(serverUri, (Draft)new Draft_6455());
    }

    public void closeConnection(int code, String message) {
        this.N\u0144Fs.closeConnection(code, message);
    }

    public WebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        this(serverUri, (Draft)new Draft_6455(), httpHeaders);
    }
}
