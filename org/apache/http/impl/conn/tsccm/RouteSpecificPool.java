/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.conn.OperatedClientConnection
 *  org.apache.http.conn.params.ConnPerRoute
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.conn.tsccm.BasicPoolEntry
 *  org.apache.http.impl.conn.tsccm.WaitingThread
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 *  org.apache.http.util.LangUtils
 */
package org.apache.http.impl.conn.tsccm;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.tsccm.BasicPoolEntry;
import org.apache.http.impl.conn.tsccm.WaitingThread;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;
import org.apache.http.util.LangUtils;

@Deprecated
public class RouteSpecificPool {
    protected final Queue<WaitingThread> waitingThreads;
    protected final int maxEntries;
    protected int numEntries;
    protected final ConnPerRoute connPerRoute;
    protected final HttpRoute route;
    private final Log log = LogFactory.getLog(this.getClass());
    protected final LinkedList<BasicPoolEntry> freeEntries;

    public RouteSpecificPool(HttpRoute route, ConnPerRoute connPerRoute) {
        this.route = route;
        this.connPerRoute = connPerRoute;
        this.maxEntries = connPerRoute.getMaxForRoute(route);
        this.freeEntries = new LinkedList();
        this.waitingThreads = new LinkedList<WaitingThread>();
        this.numEntries = 0;
    }

    public void removeThread(WaitingThread wt) {
        if (wt == null) {
            return;
        }
        this.waitingThreads.remove(wt);
    }

    public BasicPoolEntry allocEntry(Object state) {
        if (!this.freeEntries.isEmpty()) {
            ListIterator<BasicPoolEntry> it = this.freeEntries.listIterator(this.freeEntries.size());
            while (it.hasPrevious()) {
                BasicPoolEntry entry = it.previous();
                if (entry.getState() != null && !LangUtils.equals((Object)state, (Object)entry.getState())) continue;
                it.remove();
                return entry;
            }
        }
        if (this.getCapacity() != 0) return null;
        if (this.freeEntries.isEmpty()) return null;
        BasicPoolEntry entry = this.freeEntries.remove();
        entry.shutdownEntry();
        OperatedClientConnection conn = entry.getConnection();
        try {
            conn.close();
        }
        catch (IOException ex) {
            this.log.debug((Object)"I/O error closing connection", (Throwable)ex);
        }
        return entry;
    }

    public boolean deleteEntry(BasicPoolEntry entry) {
        boolean found = this.freeEntries.remove(entry);
        if (!found) return found;
        --this.numEntries;
        return found;
    }

    public WaitingThread nextThread() {
        return this.waitingThreads.peek();
    }

    public void createdEntry(BasicPoolEntry entry) {
        Args.check((boolean)this.route.equals((Object)entry.getPlannedRoute()), (String)"Entry not planned for this pool");
        ++this.numEntries;
    }

    public int getCapacity() {
        return this.connPerRoute.getMaxForRoute(this.route) - this.numEntries;
    }

    public boolean isUnused() {
        return this.numEntries < 1 && this.waitingThreads.isEmpty();
    }

    public void queueThread(WaitingThread wt) {
        Args.notNull((Object)wt, (String)"Waiting thread");
        this.waitingThreads.add(wt);
    }

    public final int getMaxEntries() {
        return this.maxEntries;
    }

    @Deprecated
    public RouteSpecificPool(HttpRoute route, int maxEntries) {
        this.route = route;
        this.maxEntries = maxEntries;
        this.connPerRoute = new /* Unavailable Anonymous Inner Class!! */;
        this.freeEntries = new LinkedList();
        this.waitingThreads = new LinkedList<WaitingThread>();
        this.numEntries = 0;
    }

    public void freeEntry(BasicPoolEntry entry) {
        if (this.numEntries < 1) {
            throw new IllegalStateException("No entry created for this pool. " + this.route);
        }
        if (this.numEntries <= this.freeEntries.size()) {
            throw new IllegalStateException("No entry allocated from this pool. " + this.route);
        }
        this.freeEntries.add(entry);
    }

    public void dropEntry() {
        Asserts.check((this.numEntries > 0 ? 1 : 0) != 0, (String)"There is no entry that could be dropped");
        --this.numEntries;
    }

    public final int getEntryCount() {
        return this.numEntries;
    }

    public final HttpRoute getRoute() {
        return this.route;
    }

    public boolean hasThread() {
        return !this.waitingThreads.isEmpty();
    }
}
