/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.WrappedByteChannel
 */
package org.java_websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;
import org.java_websocket.WrappedByteChannel;

@Deprecated
public class AbstractWrappedByteChannel
implements WrappedByteChannel {
    private final ByteChannel channel;

    public int read(ByteBuffer dst) throws IOException {
        return this.channel.read(dst);
    }

    public void close() throws IOException {
        this.channel.close();
    }

    @Deprecated
    public AbstractWrappedByteChannel(WrappedByteChannel towrap) {
        this.channel = towrap;
    }

    public boolean isNeedWrite() {
        return this.channel instanceof WrappedByteChannel && ((WrappedByteChannel)this.channel).isNeedWrite();
    }

    public boolean isBlocking() {
        if (this.channel instanceof SocketChannel) {
            return ((SocketChannel)this.channel).isBlocking();
        }
        if (!(this.channel instanceof WrappedByteChannel)) return false;
        return ((WrappedByteChannel)this.channel).isBlocking();
    }

    public int write(ByteBuffer src) throws IOException {
        return this.channel.write(src);
    }

    public boolean isOpen() {
        return this.channel.isOpen();
    }

    public void writeMore() throws IOException {
        if (!(this.channel instanceof WrappedByteChannel)) return;
        ((WrappedByteChannel)this.channel).writeMore();
    }

    @Deprecated
    public AbstractWrappedByteChannel(ByteChannel towrap) {
        this.channel = towrap;
    }

    public int readMore(ByteBuffer dst) throws IOException {
        return this.channel instanceof WrappedByteChannel ? ((WrappedByteChannel)this.channel).readMore(dst) : 0;
    }

    public boolean isNeedRead() {
        return this.channel instanceof WrappedByteChannel && ((WrappedByteChannel)this.channel).isNeedRead();
    }
}
