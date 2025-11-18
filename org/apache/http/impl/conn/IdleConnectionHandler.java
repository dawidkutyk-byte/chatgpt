/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.HttpConnection
 *  org.apache.http.impl.conn.IdleConnectionHandler$TimeValues
 */
package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpConnection;
import org.apache.http.impl.conn.IdleConnectionHandler;

/*
 * Exception performing whole class analysis ignored.
 */
@Deprecated
public class IdleConnectionHandler {
    private final Log log = LogFactory.getLog(this.getClass());
    private final Map<HttpConnection, TimeValues> connectionToTimes = new HashMap<HttpConnection, TimeValues>();

    public boolean remove(HttpConnection connection) {
        TimeValues times = this.connectionToTimes.remove(connection);
        if (times != null) return System.currentTimeMillis() <= TimeValues.access$000((TimeValues)times);
        this.log.warn((Object)"Removing a connection that never existed!");
        return true;
    }

    public void closeExpiredConnections() {
        long now = System.currentTimeMillis();
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Checking for expired connections, now: " + now));
        }
        Iterator<Map.Entry<HttpConnection, TimeValues>> i$ = this.connectionToTimes.entrySet().iterator();
        while (i$.hasNext()) {
            Map.Entry<HttpConnection, TimeValues> entry = i$.next();
            HttpConnection conn = entry.getKey();
            TimeValues times = entry.getValue();
            if (TimeValues.access$000((TimeValues)times) > now) continue;
            if (this.log.isDebugEnabled()) {
                this.log.debug((Object)("Closing connection, expired @: " + TimeValues.access$000((TimeValues)times)));
            }
            try {
                conn.close();
            }
            catch (IOException ex) {
                this.log.debug((Object)"I/O error closing connection", (Throwable)ex);
            }
        }
    }

    public void removeAll() {
        this.connectionToTimes.clear();
    }

    public void add(HttpConnection connection, long validDuration, TimeUnit unit) {
        long timeAdded = System.currentTimeMillis();
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Adding connection at: " + timeAdded));
        }
        this.connectionToTimes.put(connection, new TimeValues(timeAdded, validDuration, unit));
    }

    public void closeIdleConnections(long idleTime) {
        long idleTimeout = System.currentTimeMillis() - idleTime;
        if (this.log.isDebugEnabled()) {
            this.log.debug((Object)("Checking for connections, idle timeout: " + idleTimeout));
        }
        Iterator<Map.Entry<HttpConnection, TimeValues>> i$ = this.connectionToTimes.entrySet().iterator();
        while (i$.hasNext()) {
            Map.Entry<HttpConnection, TimeValues> entry = i$.next();
            HttpConnection conn = entry.getKey();
            TimeValues times = entry.getValue();
            long connectionTime = TimeValues.access$100((TimeValues)times);
            if (connectionTime > idleTimeout) continue;
            if (this.log.isDebugEnabled()) {
                this.log.debug((Object)("Closing idle connection, connection time: " + connectionTime));
            }
            try {
                conn.close();
            }
            catch (IOException ex) {
                this.log.debug((Object)"I/O error closing connection", (Throwable)ex);
            }
        }
    }
}
