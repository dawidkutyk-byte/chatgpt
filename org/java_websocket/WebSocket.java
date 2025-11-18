/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.drafts.Draft
 *  org.java_websocket.enums.Opcode
 *  org.java_websocket.enums.ReadyState
 *  org.java_websocket.framing.Framedata
 *  org.java_websocket.protocols.IProtocol
 */
package org.java_websocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Collection;
import javax.net.ssl.SSLSession;
import org.java_websocket.drafts.Draft;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.framing.Framedata;
import org.java_websocket.protocols.IProtocol;

public interface WebSocket {
    public void sendPing();

    public void sendFrame(Collection<Framedata> var1);

    public boolean hasSSLSupport();

    public boolean isOpen();

    public <T> T getAttachment();

    public InetSocketAddress getLocalSocketAddress();

    public void send(ByteBuffer var1);

    public IProtocol getProtocol();

    public void send(byte[] var1);

    public void close(int var1, String var2);

    public boolean isClosed();

    public InetSocketAddress getRemoteSocketAddress();

    public void sendFrame(Framedata var1);

    public <T> void setAttachment(T var1);

    public boolean isFlushAndClose();

    public ReadyState getReadyState();

    public void close(int var1);

    public SSLSession getSSLSession() throws IllegalArgumentException;

    public void close();

    public boolean hasBufferedData();

    public boolean isClosing();

    public void send(String var1);

    public Draft getDraft();

    public void closeConnection(int var1, String var2);

    public String getResourceDescriptor();

    public void sendFragmentedFrame(Opcode var1, ByteBuffer var2, boolean var3);
}
