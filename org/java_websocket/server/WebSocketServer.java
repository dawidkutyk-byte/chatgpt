/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.AbstractWebSocket
 *  org.java_websocket.SocketChannelIOHelper
 *  org.java_websocket.WebSocket
 *  org.java_websocket.WebSocketAdapter
 *  org.java_websocket.WebSocketFactory
 *  org.java_websocket.WebSocketImpl
 *  org.java_websocket.WebSocketServerFactory
 *  org.java_websocket.WrappedByteChannel
 *  org.java_websocket.drafts.Draft
 *  org.java_websocket.exceptions.WebsocketNotConnectedException
 *  org.java_websocket.exceptions.WrappedIOException
 *  org.java_websocket.framing.Framedata
 *  org.java_websocket.handshake.ClientHandshake
 *  org.java_websocket.handshake.Handshakedata
 *  org.java_websocket.server.DefaultWebSocketServerFactory
 *  org.java_websocket.server.WebSocketServer$WebSocketWorker
 */
package org.java_websocket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.SocketChannelIOHelper;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.WrappedByteChannel;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.exceptions.WrappedIOException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.server.DefaultWebSocketServerFactory;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WebSocketServer
extends AbstractWebSocket
implements Runnable {
    private final AtomicBoolean isclosed;
    private Selector selector;
    private final Collection<WebSocket> connections;
    private final InetSocketAddress address;
    private WebSocketServerFactory wsf;
    private BlockingQueue<ByteBuffer> buffers;
    private int queueinvokes = 0;
    private final AtomicInteger queuesize;
    private List<Draft> drafts;
    private ServerSocketChannel server;
    protected List<WebSocketWorker> decoders;
    private List<WebSocketImpl> iqueue;
    private Thread selectorthread;
    private int maxPendingConnections = -1;
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    public WebSocketServer() {
        this(new InetSocketAddress(80), AVAILABLE_PROCESSORS, null);
    }

    public void stop(int timeout) throws InterruptedException {
        this.stop(timeout, "");
    }

    private void pushBuffer(ByteBuffer buf) throws InterruptedException {
        if (this.buffers.size() > this.queuesize.intValue()) {
            return;
        }
        this.buffers.put(buf);
    }

    public abstract void onOpen(WebSocket var1, ClientHandshake var2);

    public abstract void onError(WebSocket var1, Exception var2);

    private boolean doRead(SelectionKey key, Iterator<SelectionKey> i) throws WrappedIOException, InterruptedException {
        WebSocketImpl conn = (WebSocketImpl)key.attachment();
        ByteBuffer buf = this.takeBuffer();
        if (conn.getChannel() == null) {
            key.cancel();
            this.handleIOException(key, (WebSocket)conn, new IOException());
            return false;
        }
        try {
            if (SocketChannelIOHelper.read((ByteBuffer)buf, (WebSocketImpl)conn, (ByteChannel)conn.getChannel())) {
                if (buf.hasRemaining()) {
                    conn.inQueue.put(buf);
                    this.queue(conn);
                    i.remove();
                    if (!(conn.getChannel() instanceof WrappedByteChannel)) return true;
                    if (!((WrappedByteChannel)conn.getChannel()).isNeedRead()) return true;
                    this.iqueue.add(conn);
                } else {
                    this.pushBuffer(buf);
                }
            } else {
                this.pushBuffer(buf);
            }
        }
        catch (IOException e) {
            this.pushBuffer(buf);
            throw new WrappedIOException((WebSocket)conn, e);
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void stop(int timeout, String closeMessage) throws InterruptedException {
        ArrayList<WebSocket> socketsToClose;
        if (!this.isclosed.compareAndSet(false, true)) {
            return;
        }
        Object object = this.connections;
        synchronized (object) {
            socketsToClose = new ArrayList<WebSocket>(this.connections);
        }
        for (WebSocket ws : socketsToClose) {
            ws.close(1001, closeMessage);
        }
        this.wsf.close();
        object = this;
        synchronized (object) {
            if (this.selectorthread == null) return;
            if (this.selector == null) return;
            this.selector.wakeup();
            this.selectorthread.join(timeout);
        }
    }

    public final void onWebsocketMessage(WebSocket conn, ByteBuffer blob) {
        this.onMessage(conn, blob);
    }

    static /* synthetic */ void access$200(WebSocketServer x0, ByteBuffer x1) throws InterruptedException {
        x0.pushBuffer(x1);
    }

    public void broadcast(String text, Collection<WebSocket> clients) {
        if (text == null) throw new IllegalArgumentException();
        if (clients == null) {
            throw new IllegalArgumentException();
        }
        this.doBroadcast(text, clients);
    }

    private Socket getSocket(WebSocket conn) {
        WebSocketImpl impl = (WebSocketImpl)conn;
        return ((SocketChannel)impl.getSelectionKey().channel()).socket();
    }

    private void doWrite(SelectionKey key) throws WrappedIOException {
        WebSocketImpl conn = (WebSocketImpl)key.attachment();
        try {
            if (!SocketChannelIOHelper.batch((WebSocketImpl)conn, (ByteChannel)conn.getChannel())) return;
            if (!key.isValid()) return;
            key.interestOps(1);
        }
        catch (IOException e) {
            throw new WrappedIOException((WebSocket)conn, e);
        }
    }

    public void onCloseInitiated(WebSocket conn, int code, String reason) {
    }

    static /* synthetic */ void access$100(WebSocketServer x0, WebSocket x1, Exception x2) {
        x0.handleFatal(x1, x2);
    }

    protected void releaseBuffers(WebSocket c) throws InterruptedException {
    }

    public int getMaxPendingConnections() {
        return this.maxPendingConnections;
    }

    public void broadcast(ByteBuffer data, Collection<WebSocket> clients) {
        if (data == null) throw new IllegalArgumentException();
        if (clients == null) {
            throw new IllegalArgumentException();
        }
        this.doBroadcast(data, clients);
    }

    private void fillFrames(Draft draft, Map<Draft, List<Framedata>> draftFrames, String strData, ByteBuffer byteData) {
        if (draftFrames.containsKey(draft)) return;
        List frames = null;
        if (strData != null) {
            frames = draft.createFrames(strData, false);
        }
        if (byteData != null) {
            frames = draft.createFrames(byteData, false);
        }
        if (frames == null) return;
        draftFrames.put(draft, frames);
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket conn) {
        return (InetSocketAddress)this.getSocket(conn).getLocalSocketAddress();
    }

    public abstract void onClose(WebSocket var1, int var2, String var3, boolean var4);

    private void handleIOException(SelectionKey key, WebSocket conn, IOException ex) {
        if (key != null) {
            key.cancel();
        }
        if (conn != null) {
            conn.closeConnection(1006, ex.getMessage());
        } else {
            if (key == null) return;
            SelectableChannel channel = key.channel();
            if (channel == null) return;
            if (!channel.isOpen()) return;
            try {
                channel.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
            this.log.trace("Connection closed because of exception", ex);
        }
    }

    private boolean doSetupSelectorAndServerThread() {
        this.selectorthread.setName("WebSocketSelector-" + this.selectorthread.getId());
        try {
            this.server = ServerSocketChannel.open();
            this.server.configureBlocking(false);
            ServerSocket socket = this.server.socket();
            socket.setReceiveBufferSize(16384);
            socket.setReuseAddress(this.isReuseAddr());
            socket.bind(this.address, this.getMaxPendingConnections());
            this.selector = Selector.open();
            this.server.register(this.selector, this.server.validOps());
            this.startConnectionLostTimer();
            for (WebSocketWorker ex : this.decoders) {
                ex.start();
            }
            this.onStart();
        }
        catch (IOException ex) {
            this.handleFatal(null, ex);
            return false;
        }
        return true;
    }

    public WebSocketServer(InetSocketAddress address, int decodercount) {
        this(address, decodercount, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void doBroadcast(Object data, Collection<WebSocket> clients) {
        ArrayList<WebSocket> clientCopy;
        String strData = null;
        if (data instanceof String) {
            strData = (String)data;
        }
        ByteBuffer byteData = null;
        if (data instanceof ByteBuffer) {
            byteData = (ByteBuffer)data;
        }
        if (strData == null && byteData == null) {
            return;
        }
        HashMap<Draft, List<Framedata>> draftFrames = new HashMap<Draft, List<Framedata>>();
        Object object = clients;
        synchronized (object) {
            clientCopy = new ArrayList<WebSocket>(clients);
        }
        object = clientCopy.iterator();
        while (object.hasNext()) {
            WebSocket client = (WebSocket)object.next();
            if (client == null) continue;
            Draft draft = client.getDraft();
            this.fillFrames(draft, draftFrames, strData, byteData);
            try {
                client.sendFrame((Collection)draftFrames.get(draft));
            }
            catch (WebsocketNotConnectedException websocketNotConnectedException) {}
        }
    }

    public abstract void onStart();

    public int getPort() {
        int port = this.getAddress().getPort();
        if (port != 0) return port;
        if (this.server == null) return port;
        port = this.server.socket().getLocalPort();
        return port;
    }

    public void start() {
        if (this.selectorthread != null) {
            throw new IllegalStateException(this.getClass().getName() + " can only be started once.");
        }
        new Thread(this).start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean doEnsureSingleThread() {
        WebSocketServer webSocketServer = this;
        synchronized (webSocketServer) {
            if (this.selectorthread != null) {
                throw new IllegalStateException(this.getClass().getName() + " can only be started once.");
            }
            this.selectorthread = Thread.currentThread();
            if (!this.isclosed.get()) return true;
            return false;
        }
    }

    public abstract void onMessage(WebSocket var1, String var2);

    public final void onWebsocketError(WebSocket conn, Exception ex) {
        this.onError(conn, ex);
    }

    private void doServerShutdown() {
        this.stopConnectionLostTimer();
        if (this.decoders != null) {
            for (WebSocketWorker w : this.decoders) {
                w.interrupt();
            }
        }
        if (this.selector != null) {
            try {
                this.selector.close();
            }
            catch (IOException e) {
                this.log.error("IOException during selector.close", e);
                this.onError(null, e);
            }
        }
        if (this.server == null) return;
        try {
            this.server.close();
        }
        catch (IOException e) {
            this.log.error("IOException during server.close", e);
            this.onError(null, e);
        }
    }

    public void setMaxPendingConnections(int numberOfConnections) {
        this.maxPendingConnections = numberOfConnections;
    }

    /*
     * Exception decompiling
     */
    @Override
    public void run() {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doClass(Driver.java:84)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:78)
         *     at the.bytecode.club.bytecodeviewer.decompilers.impl.CFRDecompiler.decompile(CFRDecompiler.java:89)
         *     at the.bytecode.club.bytecodeviewer.decompilers.impl.CFRDecompiler.decompileToZip(CFRDecompiler.java:133)
         *     at the.bytecode.club.bytecodeviewer.resources.ResourceDecompiling.decompileSaveAll(ResourceDecompiling.java:261)
         *     at the.bytecode.club.bytecodeviewer.resources.ResourceDecompiling.lambda$decompileSaveAll$0(ResourceDecompiling.java:111)
         *     at java.base/java.lang.Thread.run(Thread.java:1575)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public void onWebsocketClosing(WebSocket conn, int code, String reason, boolean remote) {
        this.onClosing(conn, code, reason, remote);
    }

    public final void onWebsocketOpen(WebSocket conn, Handshakedata handshake) {
        if (!this.addConnection(conn)) return;
        this.onOpen(conn, (ClientHandshake)handshake);
    }

    protected void queue(WebSocketImpl ws) throws InterruptedException {
        if (ws.getWorkerThread() == null) {
            ws.setWorkerThread(this.decoders.get(this.queueinvokes % this.decoders.size()));
            ++this.queueinvokes;
        }
        ws.getWorkerThread().put(ws);
    }

    private void handleFatal(WebSocket conn, Exception e) {
        this.log.error("Shutdown due to fatal error", e);
        this.onError(conn, e);
        String causeMessage = e.getCause() != null ? " caused by " + e.getCause().getClass().getName() : "";
        String errorMessage = "Got error on server side: " + e.getClass().getName() + causeMessage;
        try {
            this.stop(0, errorMessage);
        }
        catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
            this.log.error("Interrupt during stop", e);
            this.onError(null, e1);
        }
        if (this.decoders != null) {
            for (WebSocketWorker w : this.decoders) {
                w.interrupt();
            }
        }
        if (this.selectorthread == null) return;
        this.selectorthread.interrupt();
    }

    private void doAccept(SelectionKey key, Iterator<SelectionKey> i) throws InterruptedException, IOException {
        if (!this.onConnect(key)) {
            key.cancel();
            return;
        }
        SocketChannel channel = this.server.accept();
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        socket.setTcpNoDelay(this.isTcpNoDelay());
        socket.setKeepAlive(true);
        WebSocketImpl w = this.wsf.createWebSocket((WebSocketAdapter)this, this.drafts);
        w.setSelectionKey(channel.register(this.selector, 1, w));
        try {
            w.setChannel(this.wsf.wrapChannel(channel, w.getSelectionKey()));
            i.remove();
            this.allocateBuffers((WebSocket)w);
        }
        catch (IOException ex) {
            if (w.getSelectionKey() != null) {
                w.getSelectionKey().cancel();
            }
            this.handleIOException(w.getSelectionKey(), null, ex);
        }
    }

    public InetSocketAddress getAddress() {
        return this.address;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void onWebsocketClose(WebSocket conn, int code, String reason, boolean remote) {
        this.selector.wakeup();
        try {
            if (!this.removeConnection(conn)) return;
            this.onClose(conn, code, reason, remote);
        }
        finally {
            try {
                this.releaseBuffers(conn);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public WebSocketServer(InetSocketAddress address, int decodercount, List<Draft> drafts) {
        this(address, decodercount, drafts, new HashSet<WebSocket>());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected boolean removeConnection(WebSocket ws) {
        boolean removed = false;
        Collection<WebSocket> collection = this.connections;
        synchronized (collection) {
            if (this.connections.contains(ws)) {
                removed = this.connections.remove(ws);
            } else {
                this.log.trace("Removing connection which is not in the connections collection! Possible no handshake received! {}", (Object)ws);
            }
        }
        if (!this.isclosed.get()) return removed;
        if (!this.connections.isEmpty()) return removed;
        this.selectorthread.interrupt();
        return removed;
    }

    static /* synthetic */ Logger access$000(WebSocketServer x0) {
        return x0.log;
    }

    public WebSocketServer(InetSocketAddress address) {
        this(address, AVAILABLE_PROCESSORS, null);
    }

    public final void setWebSocketFactory(WebSocketServerFactory wsf) {
        if (this.wsf != null) {
            this.wsf.close();
        }
        this.wsf = wsf;
    }

    public void onMessage(WebSocket conn, ByteBuffer message) {
    }

    public void onClosing(WebSocket conn, int code, String reason, boolean remote) {
    }

    public WebSocketServer(InetSocketAddress address, int decodercount, List<Draft> drafts, Collection<WebSocket> connectionscontainer) {
        this.isclosed = new AtomicBoolean(false);
        this.queuesize = new AtomicInteger(0);
        this.wsf = new DefaultWebSocketServerFactory();
        if (address == null) throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
        if (decodercount < 1) throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
        if (connectionscontainer == null) {
            throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
        }
        this.drafts = drafts == null ? Collections.emptyList() : drafts;
        this.address = address;
        this.connections = connectionscontainer;
        this.setTcpNoDelay(false);
        this.setReuseAddr(false);
        this.iqueue = new LinkedList<WebSocketImpl>();
        this.decoders = new ArrayList<WebSocketWorker>(decodercount);
        this.buffers = new LinkedBlockingQueue<ByteBuffer>();
        int i = 0;
        while (i < decodercount) {
            WebSocketWorker ex = new WebSocketWorker(this);
            this.decoders.add(ex);
            ++i;
        }
    }

    public void broadcast(String text) {
        this.broadcast(text, this.connections);
    }

    public WebSocketServer(InetSocketAddress address, List<Draft> drafts) {
        this(address, AVAILABLE_PROCESSORS, drafts);
    }

    public List<Draft> getDraft() {
        return Collections.unmodifiableList(this.drafts);
    }

    public final void onWebsocketMessage(WebSocket conn, String message) {
        this.onMessage(conn, message);
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket conn) {
        return (InetSocketAddress)this.getSocket(conn).getRemoteSocketAddress();
    }

    private ByteBuffer takeBuffer() throws InterruptedException {
        return this.buffers.take();
    }

    public void broadcast(byte[] data) {
        this.broadcast(data, this.connections);
    }

    public void broadcast(ByteBuffer data) {
        this.broadcast(data, this.connections);
    }

    public void broadcast(byte[] data, Collection<WebSocket> clients) {
        if (data == null) throw new IllegalArgumentException();
        if (clients == null) {
            throw new IllegalArgumentException();
        }
        this.broadcast(ByteBuffer.wrap(data), clients);
    }

    protected void allocateBuffers(WebSocket c) throws InterruptedException {
        if (this.queuesize.get() >= 2 * this.decoders.size() + 1) {
            return;
        }
        this.queuesize.incrementAndGet();
        this.buffers.put(this.createBuffer());
    }

    public void stop() throws InterruptedException {
        this.stop(0);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected boolean addConnection(WebSocket ws) {
        if (this.isclosed.get()) {
            ws.close(1001);
            return true;
        }
        Collection<WebSocket> collection = this.connections;
        synchronized (collection) {
            return this.connections.add(ws);
        }
    }

    public void onWebsocketCloseInitiated(WebSocket conn, int code, String reason) {
        this.onCloseInitiated(conn, code, reason);
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(16384);
    }

    protected boolean onConnect(SelectionKey key) {
        return true;
    }

    private void doAdditionalRead() throws IOException, InterruptedException {
        while (!this.iqueue.isEmpty()) {
            WebSocketImpl conn = this.iqueue.remove(0);
            WrappedByteChannel c = (WrappedByteChannel)conn.getChannel();
            ByteBuffer buf = this.takeBuffer();
            try {
                if (SocketChannelIOHelper.readMore((ByteBuffer)buf, (WebSocketImpl)conn, (WrappedByteChannel)c)) {
                    this.iqueue.add(conn);
                }
                if (buf.hasRemaining()) {
                    conn.inQueue.put(buf);
                    this.queue(conn);
                    continue;
                }
                this.pushBuffer(buf);
            }
            catch (IOException e) {
                this.pushBuffer(buf);
                throw e;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Collection<WebSocket> getConnections() {
        Collection<WebSocket> collection = this.connections;
        synchronized (collection) {
            return Collections.unmodifiableCollection(new ArrayList<WebSocket>(this.connections));
        }
    }

    public final WebSocketFactory getWebSocketFactory() {
        return this.wsf;
    }

    public final void onWriteDemand(WebSocket w) {
        WebSocketImpl conn = (WebSocketImpl)w;
        try {
            conn.getSelectionKey().interestOps(5);
        }
        catch (CancelledKeyException e) {
            conn.outQueue.clear();
        }
        this.selector.wakeup();
    }
}
