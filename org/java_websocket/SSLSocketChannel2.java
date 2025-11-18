/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.WrappedByteChannel
 *  org.java_websocket.interfaces.ISSLChannel
 */
package org.java_websocket;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import org.java_websocket.WrappedByteChannel;
import org.java_websocket.interfaces.ISSLChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLSocketChannel2
implements ByteChannel,
WrappedByteChannel,
ISSLChannel {
    protected SSLEngineResult writeEngineResult;
    protected ByteBuffer inCrypt;
    protected SSLEngine sslEngine;
    protected ByteBuffer inData;
    protected List<Future<?>> tasks;
    protected static ByteBuffer emptybuffer = ByteBuffer.allocate(0);
    protected ExecutorService exec;
    protected int bufferallocations = 0;
    private byte[] saveCryptData = null;
    protected ByteBuffer outCrypt;
    private final Logger log = LoggerFactory.getLogger(SSLSocketChannel2.class);
    protected SelectionKey selectionKey;
    protected SSLEngineResult readEngineResult;
    protected SocketChannel socketChannel;

    private void tryRestoreCryptedData() {
        if (this.saveCryptData == null) return;
        this.inCrypt.clear();
        this.inCrypt.put(this.saveCryptData);
        this.inCrypt.flip();
        this.saveCryptData = null;
    }

    @Override
    public void close() throws IOException {
        this.sslEngine.closeOutbound();
        this.sslEngine.getSession().invalidate();
        try {
            if (!this.socketChannel.isOpen()) return;
            this.socketChannel.write(this.wrap(emptybuffer));
        }
        finally {
            this.socketChannel.close();
        }
    }

    private boolean isHandShakeComplete() {
        SSLEngineResult.HandshakeStatus status = this.sslEngine.getHandshakeStatus();
        return status == SSLEngineResult.HandshakeStatus.FINISHED || status == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    public boolean finishConnect() throws IOException {
        return this.socketChannel.finishConnect();
    }

    private synchronized ByteBuffer unwrap() throws SSLException {
        int rem;
        if (this.readEngineResult.getStatus() == SSLEngineResult.Status.CLOSED && this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            try {
                this.close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        do {
            rem = this.inData.remaining();
            this.readEngineResult = this.sslEngine.unwrap(this.inCrypt, this.inData);
        } while (this.readEngineResult.getStatus() == SSLEngineResult.Status.OK && (rem != this.inData.remaining() || this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_UNWRAP));
        this.inData.flip();
        return this.inData;
    }

    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        if (!this.isHandShakeComplete()) {
            this.processHandshake();
            return 0;
        }
        int num = this.socketChannel.write(this.wrap(src));
        if (this.writeEngineResult.getStatus() != SSLEngineResult.Status.CLOSED) return num;
        throw new EOFException("Connection is closed");
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        this.tryRestoreCryptedData();
        while (dst.hasRemaining()) {
            int purged;
            if (!this.isHandShakeComplete()) {
                if (this.isBlocking()) {
                    while (!this.isHandShakeComplete()) {
                        this.processHandshake();
                    }
                } else {
                    this.processHandshake();
                    if (!this.isHandShakeComplete()) {
                        return 0;
                    }
                }
            }
            if ((purged = this.readRemaining(dst)) != 0) {
                return purged;
            }
            assert (this.inData.position() == 0);
            this.inData.clear();
            if (!this.inCrypt.hasRemaining()) {
                this.inCrypt.clear();
            } else {
                this.inCrypt.compact();
            }
            if ((this.isBlocking() || this.readEngineResult.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW) && this.socketChannel.read(this.inCrypt) == -1) {
                return -1;
            }
            this.inCrypt.flip();
            this.unwrap();
            int transferred = this.transfereTo(this.inData, dst);
            if (transferred != 0) return transferred;
            if (!this.isBlocking()) return transferred;
        }
        return 0;
    }

    private int transfereTo(ByteBuffer from, ByteBuffer to) {
        int toremain;
        int fremain = from.remaining();
        if (fremain <= (toremain = to.remaining())) {
            to.put(from);
            return fremain;
        }
        int limit = Math.min(fremain, toremain);
        int i = 0;
        while (i < limit) {
            to.put(from.get());
            ++i;
        }
        return limit;
    }

    @Override
    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    private synchronized void processHandshake() throws IOException {
        if (this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            return;
        }
        if (!this.tasks.isEmpty()) {
            Iterator<Future<?>> it = this.tasks.iterator();
            while (it.hasNext()) {
                Future<?> f = it.next();
                if (!f.isDone()) {
                    if (!this.isBlocking()) return;
                    this.consumeFutureUninterruptible(f);
                    return;
                }
                it.remove();
            }
        }
        if (this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
            if (!this.isBlocking() || this.readEngineResult.getStatus() == SSLEngineResult.Status.BUFFER_UNDERFLOW) {
                this.inCrypt.compact();
                int read = this.socketChannel.read(this.inCrypt);
                if (read == -1) {
                    throw new IOException("connection closed unexpectedly by peer");
                }
                this.inCrypt.flip();
            }
            this.inData.compact();
            this.unwrap();
            if (this.readEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                this.createBuffers(this.sslEngine.getSession());
                return;
            }
        }
        this.consumeDelegatedTasks();
        if (this.tasks.isEmpty() || this.sslEngine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
            this.socketChannel.write(this.wrap(emptybuffer));
            if (this.writeEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                this.createBuffers(this.sslEngine.getSession());
                return;
            }
        }
        assert (this.sslEngine.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING);
        this.bufferallocations = 1;
    }

    public SelectableChannel configureBlocking(boolean b) throws IOException {
        return this.socketChannel.configureBlocking(b);
    }

    public boolean connect(SocketAddress remote) throws IOException {
        return this.socketChannel.connect(remote);
    }

    public void writeMore() throws IOException {
        this.write(this.outCrypt);
    }

    private synchronized ByteBuffer wrap(ByteBuffer b) throws SSLException {
        this.outCrypt.compact();
        this.writeEngineResult = this.sslEngine.wrap(b, this.outCrypt);
        this.outCrypt.flip();
        return this.outCrypt;
    }

    public SSLSocketChannel2(SocketChannel channel, SSLEngine sslEngine, ExecutorService exec, SelectionKey key) throws IOException {
        if (channel == null) throw new IllegalArgumentException("parameter must not be null");
        if (sslEngine == null) throw new IllegalArgumentException("parameter must not be null");
        if (exec == null) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = channel;
        this.sslEngine = sslEngine;
        this.exec = exec;
        this.readEngineResult = this.writeEngineResult = new SSLEngineResult(SSLEngineResult.Status.BUFFER_UNDERFLOW, sslEngine.getHandshakeStatus(), 0, 0);
        this.tasks = new ArrayList(3);
        if (key != null) {
            key.interestOps(key.interestOps() | 4);
            this.selectionKey = key;
        }
        this.createBuffers(sslEngine.getSession());
        this.socketChannel.write(this.wrap(emptybuffer));
        this.processHandshake();
    }

    public boolean isNeedRead() {
        return this.saveCryptData != null || this.inData.hasRemaining() || this.inCrypt.hasRemaining() && this.readEngineResult.getStatus() != SSLEngineResult.Status.BUFFER_UNDERFLOW && this.readEngineResult.getStatus() != SSLEngineResult.Status.CLOSED;
    }

    public Socket socket() {
        return this.socketChannel.socket();
    }

    protected void createBuffers(SSLSession session) {
        this.saveCryptedData();
        int netBufferMax = session.getPacketBufferSize();
        int appBufferMax = Math.max(session.getApplicationBufferSize(), netBufferMax);
        if (this.inData == null) {
            this.inData = ByteBuffer.allocate(appBufferMax);
            this.outCrypt = ByteBuffer.allocate(netBufferMax);
            this.inCrypt = ByteBuffer.allocate(netBufferMax);
        } else {
            if (this.inData.capacity() != appBufferMax) {
                this.inData = ByteBuffer.allocate(appBufferMax);
            }
            if (this.outCrypt.capacity() != netBufferMax) {
                this.outCrypt = ByteBuffer.allocate(netBufferMax);
            }
            if (this.inCrypt.capacity() != netBufferMax) {
                this.inCrypt = ByteBuffer.allocate(netBufferMax);
            }
        }
        if (this.inData.remaining() != 0 && this.log.isTraceEnabled()) {
            this.log.trace(new String(this.inData.array(), this.inData.position(), this.inData.remaining()));
        }
        this.inData.rewind();
        this.inData.flip();
        if (this.inCrypt.remaining() != 0 && this.log.isTraceEnabled()) {
            this.log.trace(new String(this.inCrypt.array(), this.inCrypt.position(), this.inCrypt.remaining()));
        }
        this.inCrypt.rewind();
        this.inCrypt.flip();
        this.outCrypt.rewind();
        this.outCrypt.flip();
        ++this.bufferallocations;
    }

    public boolean isNeedWrite() {
        return this.outCrypt.hasRemaining() || !this.isHandShakeComplete();
    }

    private void saveCryptedData() {
        if (this.inCrypt == null) return;
        if (this.inCrypt.remaining() <= 0) return;
        int saveCryptSize = this.inCrypt.remaining();
        this.saveCryptData = new byte[saveCryptSize];
        this.inCrypt.get(this.saveCryptData);
    }

    public SSLEngine getSSLEngine() {
        return this.sslEngine;
    }

    protected void consumeDelegatedTasks() {
        Runnable task;
        while ((task = this.sslEngine.getDelegatedTask()) != null) {
            this.tasks.add(this.exec.submit(task));
        }
    }

    private int readRemaining(ByteBuffer dst) throws SSLException {
        if (this.inData.hasRemaining()) {
            return this.transfereTo(this.inData, dst);
        }
        if (!this.inData.hasRemaining()) {
            this.inData.clear();
        }
        this.tryRestoreCryptedData();
        if (!this.inCrypt.hasRemaining()) return 0;
        this.unwrap();
        int amount = this.transfereTo(this.inData, dst);
        if (this.readEngineResult.getStatus() == SSLEngineResult.Status.CLOSED) {
            return -1;
        }
        if (amount <= 0) return 0;
        return amount;
    }

    public boolean isInboundDone() {
        return this.sslEngine.isInboundDone();
    }

    public int readMore(ByteBuffer dst) throws SSLException {
        return this.readRemaining(dst);
    }

    private void consumeFutureUninterruptible(Future<?> f) {
        try {
            while (true) {
                try {
                    f.get();
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    continue;
                }
                break;
            }
        }
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isConnected() {
        return this.socketChannel.isConnected();
    }
}
