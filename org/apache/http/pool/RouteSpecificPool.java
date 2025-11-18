/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.pool.PoolEntry
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.pool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.Future;
import org.apache.http.pool.PoolEntry;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

abstract class RouteSpecificPool<T, C, E extends PoolEntry<T, C>> {
    private final T route;
    private final LinkedList<E> available;
    private final Set<E> leased;
    private final LinkedList<Future<E>> pending;

    protected abstract E createEntry(C var1);

    public int getLeasedCount() {
        return this.leased.size();
    }

    public int getAllocatedCount() {
        return this.available.size() + this.leased.size();
    }

    public void unqueue(Future<E> future) {
        if (future == null) {
            return;
        }
        this.pending.remove(future);
    }

    public E getLastUsed() {
        return (E)(this.available.isEmpty() ? null : (PoolEntry)this.available.getLast());
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[route: ");
        buffer.append(this.route);
        buffer.append("][leased: ");
        buffer.append(this.leased.size());
        buffer.append("][available: ");
        buffer.append(this.available.size());
        buffer.append("][pending: ");
        buffer.append(this.pending.size());
        buffer.append("]");
        return buffer.toString();
    }

    public E getFree(Object state) {
        PoolEntry entry;
        Iterator it;
        if (this.available.isEmpty()) return null;
        if (state != null) {
            it = this.available.iterator();
            while (it.hasNext()) {
                entry = (PoolEntry)it.next();
                if (!state.equals(entry.getState())) continue;
                it.remove();
                this.leased.add(entry);
                return (E)entry;
            }
        }
        it = this.available.iterator();
        do {
            if (!it.hasNext()) return null;
        } while ((entry = (PoolEntry)it.next()).getState() != null);
        it.remove();
        this.leased.add(entry);
        return (E)entry;
    }

    public void queue(Future<E> future) {
        if (future == null) {
            return;
        }
        this.pending.add(future);
    }

    public final T getRoute() {
        return this.route;
    }

    public void shutdown() {
        for (Future future : this.pending) {
            future.cancel(true);
        }
        this.pending.clear();
        for (PoolEntry poolEntry : this.available) {
            poolEntry.close();
        }
        this.available.clear();
        Iterator i$ = this.leased.iterator();
        while (true) {
            if (!i$.hasNext()) {
                this.leased.clear();
                return;
            }
            PoolEntry poolEntry = (PoolEntry)i$.next();
            poolEntry.close();
        }
    }

    public int getPendingCount() {
        return this.pending.size();
    }

    RouteSpecificPool(T route) {
        this.route = route;
        this.leased = new HashSet();
        this.available = new LinkedList();
        this.pending = new LinkedList();
    }

    public void free(E entry, boolean reusable) {
        Args.notNull(entry, (String)"Pool entry");
        boolean found = this.leased.remove(entry);
        Asserts.check((boolean)found, (String)"Entry %s has not been leased from this pool", entry);
        if (!reusable) return;
        this.available.addFirst(entry);
    }

    public E add(C conn) {
        E entry = this.createEntry(conn);
        this.leased.add(entry);
        return entry;
    }

    public boolean remove(E entry) {
        Args.notNull(entry, (String)"Pool entry");
        if (this.available.remove(entry)) return true;
        if (this.leased.remove(entry)) return true;
        return false;
    }

    public Future<E> nextPending() {
        return this.pending.poll();
    }

    public int getAvailableCount() {
        return this.available.size();
    }
}
