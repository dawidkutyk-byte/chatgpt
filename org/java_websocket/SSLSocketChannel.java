/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.SSLSocketChannel$1
 *  org.java_websocket.WrappedByteChannel
 *  org.java_websocket.interfaces.ISSLChannel
 *  org.java_websocket.util.ByteBufferUtils
 */
package org.java_websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import org.java_websocket.SSLSocketChannel;
import org.java_websocket.WrappedByteChannel;
import org.java_websocket.interfaces.ISSLChannel;
import org.java_websocket.util.ByteBufferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLSocketChannel
implements ByteChannel,
ISSLChannel,
WrappedByteChannel {
    private ByteBuffer myAppData;
    private final SocketChannel socketChannel;
    private ExecutorService executor;
    private final SSLEngine engine;
    private final Logger log = LoggerFactory.getLogger(SSLSocketChannel.class);
    private ByteBuffer peerAppData;
    private ByteBuffer peerNetData;
    private ByteBuffer myNetData;

    private boolean doHandshake() throws IOException {
        int appBufferSize = this.engine.getSession().getApplicationBufferSize();
        this.myAppData = ByteBuffer.allocate(appBufferSize);
        this.peerAppData = ByteBuffer.allocate(appBufferSize);
        this.myNetData.clear();
        this.peerNetData.clear();
        SSLEngineResult.HandshakeStatus handshakeStatus = this.engine.getHandshakeStatus();
        boolean handshakeComplete = false;
        block27: while (!handshakeComplete) {
            switch (1.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus.ordinal()]) {
                case 1: {
                    boolean bl = handshakeComplete = !this.peerNetData.hasRemaining();
                    if (handshakeComplete) {
                        return true;
                    }
                    this.socketChannel.write(this.peerNetData);
                    continue block27;
                }
                case 2: {
                    SSLEngineResult result;
                    if (this.socketChannel.read(this.peerNetData) < 0) {
                        if (this.engine.isInboundDone() && this.engine.isOutboundDone()) {
                            return false;
                        }
                        try {
                            this.engine.closeInbound();
                        }
                        catch (SSLException sSLException) {
                            // empty catch block
                        }
                        this.engine.closeOutbound();
                        handshakeStatus = this.engine.getHandshakeStatus();
                        continue block27;
                    }
                    this.peerNetData.flip();
                    try {
                        result = this.engine.unwrap(this.peerNetData, this.peerAppData);
                        this.peerNetData.compact();
                        handshakeStatus = result.getHandshakeStatus();
                    }
                    catch (SSLException sslException) {
                        this.engine.closeOutbound();
                        handshakeStatus = this.engine.getHandshakeStatus();
                        continue block27;
                    }
                    switch (1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[result.getStatus().ordinal()]) {
                        case 1: {
                            continue block27;
                        }
                        case 3: {
                            this.peerAppData = this.enlargeApplicationBuffer(this.peerAppData);
                            continue block27;
                        }
                        case 2: {
                            this.peerNetData = this.handleBufferUnderflow(this.peerNetData);
                            continue block27;
                        }
                        case 4: {
                            if (this.engine.isOutboundDone()) {
                                return false;
                            }
                            this.engine.closeOutbound();
                            handshakeStatus = this.engine.getHandshakeStatus();
                            continue block27;
                        }
                    }
                    throw new IllegalStateException("Invalid SSL status: " + (Object)((Object)result.getStatus()));
                }
                case 3: {
                    SSLEngineResult result;
                    this.myNetData.clear();
                    try {
                        result = this.engine.wrap(this.myAppData, this.myNetData);
                        handshakeStatus = result.getHandshakeStatus();
                    }
                    catch (SSLException sslException) {
                        this.engine.closeOutbound();
                        handshakeStatus = this.engine.getHandshakeStatus();
                        continue block27;
                    }
                    switch (1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[result.getStatus().ordinal()]) {
                        case 1: {
                            this.myNetData.flip();
                            while (true) {
                                if (!this.myNetData.hasRemaining()) continue block27;
                                this.socketChannel.write(this.myNetData);
                            }
                        }
                        case 3: {
                            this.myNetData = this.enlargePacketBuffer(this.myNetData);
                            continue block27;
                        }
                        case 2: {
                            throw new SSLException("Buffer underflow occurred after a wrap. I don't think we should ever get here.");
                        }
                        case 4: {
                            try {
                                this.myNetData.flip();
                                while (this.myNetData.hasRemaining()) {
                                    this.socketChannel.write(this.myNetData);
                                }
                                this.peerNetData.clear();
                            }
                            catch (Exception e) {
                                handshakeStatus = this.engine.getHandshakeStatus();
                            }
                            continue block27;
                        }
                    }
                    throw new IllegalStateException("Invalid SSL status: " + (Object)((Object)result.getStatus()));
                }
                case 4: {
                    Runnable task;
                    while ((task = this.engine.getDelegatedTask()) != null) {
                        this.executor.execute(task);
                    }
                    handshakeStatus = this.engine.getHandshakeStatus();
                    continue block27;
                }
                case 5: {
                    continue block27;
                }
            }
        }
        return true;
        throw new IllegalStateException("Invalid SSL status: " + (Object)((Object)handshakeStatus));
    }

    private ByteBuffer enlargePacketBuffer(ByteBuffer buffer) {
        return this.enlargeBuffer(buffer, this.engine.getSession().getPacketBufferSize());
    }

    public int readMore(ByteBuffer dst) throws IOException {
        return this.read(dst);
    }

    private ByteBuffer enlargeApplicationBuffer(ByteBuffer buffer) {
        return this.enlargeBuffer(buffer, this.engine.getSession().getApplicationBufferSize());
    }

    private void closeConnection() throws IOException {
        this.engine.closeOutbound();
        try {
            this.doHandshake();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        this.socketChannel.close();
    }

    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }

    public boolean isNeedWrite() {
        return false;
    }

    private ByteBuffer handleBufferUnderflow(ByteBuffer buffer) {
        if (this.engine.getSession().getPacketBufferSize() < buffer.limit()) {
            return buffer;
        }
        ByteBuffer replaceBuffer = this.enlargePacketBuffer(buffer);
        buffer.flip();
        replaceBuffer.put(buffer);
        return replaceBuffer;
    }

    /*
     * Exception decompiling
     */
    @Override
    public synchronized int write(ByteBuffer output) throws IOException {
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

    @Override
    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    private void handleEndOfStream() throws IOException {
        try {
            this.engine.closeInbound();
        }
        catch (Exception e) {
            this.log.error("This engine was forced to close inbound, without having received the proper SSL/TLS close notification message from the peer, due to end of stream.");
        }
        this.closeConnection();
    }

    @Override
    public synchronized int read(ByteBuffer dst) throws IOException {
        if (!dst.hasRemaining()) {
            return 0;
        }
        if (this.peerAppData.hasRemaining()) {
            this.peerAppData.flip();
            return ByteBufferUtils.transferByteBuffer((ByteBuffer)this.peerAppData, (ByteBuffer)dst);
        }
        this.peerNetData.compact();
        int bytesRead = this.socketChannel.read(this.peerNetData);
        if (bytesRead > 0 || this.peerNetData.hasRemaining()) {
            this.peerNetData.flip();
            if (this.peerNetData.hasRemaining()) {
                SSLEngineResult result;
                this.peerAppData.compact();
                try {
                    result = this.engine.unwrap(this.peerNetData, this.peerAppData);
                }
                catch (SSLException e) {
                    this.log.error("SSLException during unwrap", e);
                    throw e;
                }
                switch (1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[result.getStatus().ordinal()]) {
                    case 1: {
                        this.peerAppData.flip();
                        return ByteBufferUtils.transferByteBuffer((ByteBuffer)this.peerAppData, (ByteBuffer)dst);
                    }
                    case 2: {
                        this.peerAppData.flip();
                        return ByteBufferUtils.transferByteBuffer((ByteBuffer)this.peerAppData, (ByteBuffer)dst);
                    }
                    case 3: {
                        this.peerAppData = this.enlargeApplicationBuffer(this.peerAppData);
                        return this.read(dst);
                    }
                    case 4: {
                        this.closeConnection();
                        dst.clear();
                        return -1;
                    }
                }
                throw new IllegalStateException("Invalid SSL status: " + (Object)((Object)result.getStatus()));
            }
        } else if (bytesRead < 0) {
            this.handleEndOfStream();
        }
        ByteBufferUtils.transferByteBuffer((ByteBuffer)this.peerAppData, (ByteBuffer)dst);
        return bytesRead;
    }

    public void writeMore() throws IOException {
    }

    public SSLEngine getSSLEngine() {
        return this.engine;
    }

    @Override
    public void close() throws IOException {
        this.closeConnection();
    }

    public boolean isNeedRead() {
        return this.peerNetData.hasRemaining() || this.peerAppData.hasRemaining();
    }

    private ByteBuffer enlargeBuffer(ByteBuffer buffer, int sessionProposedCapacity) {
        buffer = sessionProposedCapacity > buffer.capacity() ? ByteBuffer.allocate(sessionProposedCapacity) : ByteBuffer.allocate(buffer.capacity() * 2);
        return buffer;
    }

    public SSLSocketChannel(SocketChannel inputSocketChannel, SSLEngine inputEngine, ExecutorService inputExecutor, SelectionKey key) throws IOException {
        if (inputSocketChannel == null) throw new IllegalArgumentException("parameter must not be null");
        if (inputEngine == null) throw new IllegalArgumentException("parameter must not be null");
        if (this.executor == inputExecutor) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = inputSocketChannel;
        this.engine = inputEngine;
        this.executor = inputExecutor;
        this.myNetData = ByteBuffer.allocate(this.engine.getSession().getPacketBufferSize());
        this.peerNetData = ByteBuffer.allocate(this.engine.getSession().getPacketBufferSize());
        this.engine.beginHandshake();
        if (this.doHandshake()) {
            if (key == null) return;
            key.interestOps(key.interestOps() | 4);
        } else {
            try {
                this.socketChannel.close();
            }
            catch (IOException e) {
                this.log.error("Exception during the closing of the channel", e);
            }
        }
    }
}
