/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.pool.BasicConnFactory
 */
package org.apache.http.impl.pool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.PrivilegedExceptionAction;
import org.apache.http.impl.pool.BasicConnFactory;

/*
 * Exception performing whole class analysis ignored.
 */
class BasicConnFactory.1
implements PrivilegedExceptionAction<Object> {
    final /* synthetic */ Socket val$socket;
    final /* synthetic */ InetSocketAddress val$address;

    BasicConnFactory.1(Socket socket, InetSocketAddress inetSocketAddress) {
        this.val$socket = socket;
        this.val$address = inetSocketAddress;
    }

    @Override
    public Object run() throws IOException {
        this.val$socket.connect(this.val$address, BasicConnFactory.access$000((BasicConnFactory)BasicConnFactory.this));
        return null;
    }
}
