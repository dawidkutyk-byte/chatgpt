/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.util.Args
 */
package org.apache.http.pool;

import java.util.concurrent.TimeUnit;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public abstract class PoolEntry<T, C> {
    private final long created;
    private volatile Object state;
    private final long validityDeadline;
    private final String id;
    private long updated;
    private final T route;
    private final C conn;
    private long expiry;

    @Deprecated
    public long getValidUnit() {
        return this.validityDeadline;
    }

    public abstract boolean isClosed();

    public T getRoute() {
        return this.route;
    }

    public long getCreated() {
        return this.created;
    }

    public synchronized void updateExpiry(long time, TimeUnit timeUnit) {
        Args.notNull((Object)((Object)timeUnit), (String)"Time unit");
        this.updated = System.currentTimeMillis();
        long newExpiry = time > 0L ? this.updated + timeUnit.toMillis(time) : Long.MAX_VALUE;
        this.expiry = Math.min(newExpiry, this.validityDeadline);
    }

    public long getValidityDeadline() {
        return this.validityDeadline;
    }

    public Object getState() {
        return this.state;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[id:");
        buffer.append(this.id);
        buffer.append("][route:");
        buffer.append(this.route);
        buffer.append("][state:");
        buffer.append(this.state);
        buffer.append("]");
        return buffer.toString();
    }

    public synchronized boolean isExpired(long now) {
        return now >= this.expiry;
    }

    public abstract void close();

    public synchronized long getExpiry() {
        return this.expiry;
    }

    public PoolEntry(String id, T route, C conn, long timeToLive, TimeUnit timeUnit) {
        long deadline;
        Args.notNull(route, (String)"Route");
        Args.notNull(conn, (String)"Connection");
        Args.notNull((Object)((Object)timeUnit), (String)"Time unit");
        this.id = id;
        this.route = route;
        this.conn = conn;
        this.updated = this.created = System.currentTimeMillis();
        this.validityDeadline = timeToLive > 0L ? ((deadline = this.created + timeUnit.toMillis(timeToLive)) > 0L ? deadline : Long.MAX_VALUE) : Long.MAX_VALUE;
        this.expiry = this.validityDeadline;
    }

    public String getId() {
        return this.id;
    }

    public C getConnection() {
        return this.conn;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public synchronized long getUpdated() {
        return this.updated;
    }

    public PoolEntry(String id, T route, C conn) {
        this(id, route, conn, 0L, TimeUnit.MILLISECONDS);
    }
}
