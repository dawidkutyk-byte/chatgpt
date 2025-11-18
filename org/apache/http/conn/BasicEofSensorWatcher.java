/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.EofSensorWatcher
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.util.Args
 */
package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.conn.EofSensorWatcher;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.util.Args;

@Deprecated
public class BasicEofSensorWatcher
implements EofSensorWatcher {
    protected final ManagedClientConnection managedConn;
    protected final boolean attemptReuse;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean eofDetected(InputStream wrapped) throws IOException {
        try {
            if (!this.attemptReuse) return false;
            wrapped.close();
            this.managedConn.markReusable();
        }
        finally {
            this.managedConn.releaseConnection();
        }
        return false;
    }

    public BasicEofSensorWatcher(ManagedClientConnection conn, boolean reuse) {
        Args.notNull((Object)conn, (String)"Connection");
        this.managedConn = conn;
        this.attemptReuse = reuse;
    }

    public boolean streamAbort(InputStream wrapped) throws IOException {
        this.managedConn.abortConnection();
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean streamClosed(InputStream wrapped) throws IOException {
        try {
            if (!this.attemptReuse) return false;
            wrapped.close();
            this.managedConn.markReusable();
        }
        finally {
            this.managedConn.releaseConnection();
        }
        return false;
    }
}
