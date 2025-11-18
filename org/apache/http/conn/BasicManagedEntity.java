/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.conn.ConnectionReleaseTrigger
 *  org.apache.http.conn.EofSensorInputStream
 *  org.apache.http.conn.EofSensorWatcher
 *  org.apache.http.conn.ManagedClientConnection
 *  org.apache.http.entity.HttpEntityWrapper
 *  org.apache.http.util.Args
 *  org.apache.http.util.EntityUtils
 */
package org.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.conn.EofSensorInputStream;
import org.apache.http.conn.EofSensorWatcher;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;

@Deprecated
public class BasicManagedEntity
extends HttpEntityWrapper
implements EofSensorWatcher,
ConnectionReleaseTrigger {
    protected ManagedClientConnection managedConn;
    protected final boolean attemptReuse;

    public InputStream getContent() throws IOException {
        return new EofSensorInputStream(this.wrappedEntity.getContent(), (EofSensorWatcher)this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void abortConnection() throws IOException {
        if (this.managedConn == null) return;
        try {
            this.managedConn.abortConnection();
        }
        finally {
            this.managedConn = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void ensureConsumed() throws IOException {
        if (this.managedConn == null) {
            return;
        }
        try {
            if (this.attemptReuse) {
                EntityUtils.consume((HttpEntity)this.wrappedEntity);
                this.managedConn.markReusable();
            } else {
                this.managedConn.unmarkReusable();
            }
        }
        finally {
            this.releaseManagedConnection();
        }
    }

    public void releaseConnection() throws IOException {
        this.ensureConsumed();
    }

    public boolean streamAbort(InputStream wrapped) throws IOException {
        if (this.managedConn == null) return false;
        this.managedConn.abortConnection();
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean eofDetected(InputStream wrapped) throws IOException {
        try {
            if (this.managedConn == null) return false;
            if (this.attemptReuse) {
                wrapped.close();
                this.managedConn.markReusable();
            } else {
                this.managedConn.unmarkReusable();
            }
        }
        finally {
            this.releaseManagedConnection();
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean streamClosed(InputStream wrapped) throws IOException {
        block6: {
            try {
                if (this.managedConn == null) return false;
                if (this.attemptReuse) {
                    boolean valid = this.managedConn.isOpen();
                    try {
                        wrapped.close();
                        this.managedConn.markReusable();
                        break block6;
                    }
                    catch (SocketException ex) {
                        if (!valid) return false;
                        throw ex;
                    }
                }
                this.managedConn.unmarkReusable();
            }
            finally {
                this.releaseManagedConnection();
            }
        }
        return false;
    }

    public BasicManagedEntity(HttpEntity entity, ManagedClientConnection conn, boolean reuse) {
        super(entity);
        Args.notNull((Object)conn, (String)"Connection");
        this.managedConn = conn;
        this.attemptReuse = reuse;
    }

    @Deprecated
    public void consumeContent() throws IOException {
        this.ensureConsumed();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void releaseManagedConnection() throws IOException {
        if (this.managedConn == null) return;
        try {
            this.managedConn.releaseConnection();
        }
        finally {
            this.managedConn = null;
        }
    }

    public void writeTo(OutputStream outStream) throws IOException {
        super.writeTo(outStream);
        this.ensureConsumed();
    }

    public boolean isRepeatable() {
        return false;
    }
}
