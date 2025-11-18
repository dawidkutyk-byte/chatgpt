/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.concurrent.FutureCallback
 *  org.apache.http.pool.AbstractConnPool$1
 *  org.apache.http.pool.ConnFactory
 *  org.apache.http.pool.ConnPool
 *  org.apache.http.pool.ConnPoolControl
 *  org.apache.http.pool.PoolEntry
 *  org.apache.http.pool.PoolEntryCallback
 *  org.apache.http.pool.PoolStats
 *  org.apache.http.pool.RouteSpecificPool
 *  org.apache.http.util.Args
 *  org.apache.http.util.Asserts
 */
package org.apache.http.pool;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.pool.AbstractConnPool;
import org.apache.http.pool.ConnFactory;
import org.apache.http.pool.ConnPool;
import org.apache.http.pool.ConnPoolControl;
import org.apache.http.pool.PoolEntry;
import org.apache.http.pool.PoolEntryCallback;
import org.apache.http.pool.PoolStats;
import org.apache.http.pool.RouteSpecificPool;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public abstract class AbstractConnPool<T, C, E extends PoolEntry<T, C>>
implements ConnPool<T, E>,
ConnPoolControl<T> {
    private final Map<T, Integer> maxPerRoute;
    private final Condition condition;
    private volatile int maxTotal;
    private final LinkedList<E> available;
    private volatile boolean isShutDown;
    private final LinkedList<Future<E>> pending;
    private final Set<E> leased;
    private final Lock lock;
    private final Map<T, RouteSpecificPool<T, C, E>> routeToPool;
    private volatile int defaultMaxPerRoute;
    private final ConnFactory<T, C> connFactory;
    private volatile int validateAfterInactivity;

    public void closeExpired() {
        long now = System.currentTimeMillis();
        this.enumAvailable((PoolEntryCallback<T, C>)new /* Unavailable Anonymous Inner Class!! */);
    }

    public void setValidateAfterInactivity(int ms) {
        this.validateAfterInactivity = ms;
    }

    protected boolean validate(E entry) {
        return true;
    }

    protected void onReuse(E entry) {
    }

    public void closeIdle(long idletime, TimeUnit timeUnit) {
        Args.notNull((Object)((Object)timeUnit), (String)"Time unit");
        long time = timeUnit.toMillis(idletime);
        if (time < 0L) {
            time = 0L;
        }
        long deadline = System.currentTimeMillis() - time;
        this.enumAvailable((PoolEntryCallback<T, C>)new /* Unavailable Anonymous Inner Class!! */);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PoolStats getStats(T route) {
        Args.notNull(route, (String)"Route");
        this.lock.lock();
        try {
            RouteSpecificPool<T, C, E> pool = this.getPool(route);
            PoolStats poolStats = new PoolStats(pool.getLeasedCount(), pool.getPendingCount(), pool.getAvailableCount(), this.getMax(route));
            return poolStats;
        }
        finally {
            this.lock.unlock();
        }
    }

    public AbstractConnPool(ConnFactory<T, C> connFactory, int defaultMaxPerRoute, int maxTotal) {
        this.connFactory = (ConnFactory)Args.notNull(connFactory, (String)"Connection factory");
        this.defaultMaxPerRoute = Args.positive((int)defaultMaxPerRoute, (String)"Max per route value");
        this.maxTotal = Args.positive((int)maxTotal, (String)"Max total value");
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
        this.routeToPool = new HashMap<T, RouteSpecificPool<T, C, E>>();
        this.leased = new HashSet();
        this.available = new LinkedList();
        this.pending = new LinkedList();
        this.maxPerRoute = new HashMap<T, Integer>();
    }

    protected void onRelease(E entry) {
    }

    public int getValidateAfterInactivity() {
        return this.validateAfterInactivity;
    }

    static /* synthetic */ int access$400(AbstractConnPool x0) {
        return x0.validateAfterInactivity;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void enumLeased(PoolEntryCallback<T, C> callback) {
        this.lock.lock();
        try {
            Iterator<E> it = this.leased.iterator();
            while (it.hasNext()) {
                PoolEntry entry = (PoolEntry)it.next();
                callback.process(entry);
            }
            return;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getMaxTotal() {
        this.lock.lock();
        try {
            int n = this.maxTotal;
            return n;
        }
        finally {
            this.lock.unlock();
        }
    }

    protected abstract E createEntry(T var1, C var2);

    private int getMax(T route) {
        Integer v = this.maxPerRoute.get(route);
        return v != null ? v : this.defaultMaxPerRoute;
    }

    private RouteSpecificPool<T, C, E> getPool(T route) {
        1 pool = this.routeToPool.get(route);
        if (pool != null) return pool;
        pool = new /* Unavailable Anonymous Inner Class!! */;
        this.routeToPool.put(route, (RouteSpecificPool<T, C, E>)pool);
        return pool;
    }

    private static Exception operationAborted() {
        return new CancellationException("Operation aborted");
    }

    static /* synthetic */ Exception access$200() {
        return AbstractConnPool.operationAborted();
    }

    static /* synthetic */ Lock access$000(AbstractConnPool x0) {
        return x0.lock;
    }

    protected void onLease(E entry) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setDefaultMaxPerRoute(int max) {
        Args.positive((int)max, (String)"Max per route value");
        this.lock.lock();
        try {
            this.defaultMaxPerRoute = max;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setMaxTotal(int max) {
        Args.positive((int)max, (String)"Max value");
        this.lock.lock();
        try {
            this.maxTotal = max;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void release(E entry, boolean reusable) {
        this.lock.lock();
        try {
            if (!this.leased.remove(entry)) return;
            RouteSpecificPool<Object, C, E> pool = this.getPool(entry.getRoute());
            pool.free(entry, reusable);
            if (reusable && !this.isShutDown) {
                this.available.addFirst(entry);
            } else {
                entry.close();
            }
            this.onRelease(entry);
            Future<E> future = pool.nextPending();
            if (future != null) {
                this.pending.remove(future);
            } else {
                future = this.pending.poll();
            }
            if (future == null) return;
            this.condition.signalAll();
        }
        finally {
            this.lock.unlock();
        }
    }

    static /* synthetic */ PoolEntry access$300(AbstractConnPool x0, Object x1, Object x2, long x3, TimeUnit x4, Future x5) throws ExecutionException, InterruptedException, IOException, TimeoutException {
        return x0.getPoolEntryBlocking(x1, x2, x3, x4, x5);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void enumAvailable(PoolEntryCallback<T, C> callback) {
        this.lock.lock();
        try {
            Iterator it = this.available.iterator();
            while (it.hasNext()) {
                PoolEntry entry = (PoolEntry)it.next();
                callback.process(entry);
                if (!entry.isClosed()) continue;
                RouteSpecificPool<Object, C, E> pool = this.getPool(entry.getRoute());
                pool.remove(entry);
                it.remove();
            }
            this.purgePoolMap();
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Set<T> getRoutes() {
        this.lock.lock();
        try {
            HashSet<T> hashSet = new HashSet<T>(this.routeToPool.keySet());
            return hashSet;
        }
        finally {
            this.lock.unlock();
        }
    }

    private void purgePoolMap() {
        Iterator<Map.Entry<T, RouteSpecificPool<T, C, E>>> it = this.routeToPool.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<T, RouteSpecificPool<T, C, E>> entry = it.next();
            RouteSpecificPool<T, C, E> pool = entry.getValue();
            if (pool.getPendingCount() + pool.getAllocatedCount() != 0) continue;
            it.remove();
        }
    }

    public boolean isShutdown() {
        return this.isShutDown;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private E getPoolEntryBlocking(T route, Object state, long timeout, TimeUnit timeUnit, Future<E> future) throws IOException, ExecutionException, TimeoutException, InterruptedException {
        Date deadline = null;
        if (timeout > 0L) {
            deadline = new Date(System.currentTimeMillis() + timeUnit.toMillis(timeout));
        }
        this.lock.lock();
        try {
            boolean success;
            RouteSpecificPool<T, C, E> pool = this.getPool(route);
            do {
                int totalUsed;
                int freeCapacity;
                PoolEntry entry;
                Asserts.check((!this.isShutDown ? 1 : 0) != 0, (String)"Connection pool shut down");
                if (future.isCancelled()) {
                    throw new ExecutionException(AbstractConnPool.operationAborted());
                }
                while ((entry = pool.getFree(state)) != null) {
                    if (entry.isExpired(System.currentTimeMillis())) {
                        entry.close();
                    }
                    if (!entry.isClosed()) break;
                    this.available.remove(entry);
                    pool.free(entry, false);
                }
                if (entry != null) {
                    this.available.remove(entry);
                    this.leased.add(entry);
                    this.onReuse(entry);
                    PoolEntry poolEntry = entry;
                    return (E)poolEntry;
                }
                int maxPerRoute = this.getMax(route);
                int excess = Math.max(0, pool.getAllocatedCount() + 1 - maxPerRoute);
                if (excess > 0) {
                    PoolEntry lastUsed;
                    for (int i = 0; i < excess && (lastUsed = pool.getLastUsed()) != null; ++i) {
                        lastUsed.close();
                        this.available.remove(lastUsed);
                        pool.remove(lastUsed);
                    }
                }
                if (pool.getAllocatedCount() < maxPerRoute && (freeCapacity = Math.max(this.maxTotal - (totalUsed = this.leased.size()), 0)) > 0) {
                    int totalAvailable = this.available.size();
                    if (totalAvailable > freeCapacity - 1 && !this.available.isEmpty()) {
                        PoolEntry lastUsed = (PoolEntry)this.available.removeLast();
                        lastUsed.close();
                        RouteSpecificPool<Object, C, E> otherpool = this.getPool(lastUsed.getRoute());
                        otherpool.remove(lastUsed);
                    }
                    Object conn = this.connFactory.create(route);
                    entry = pool.add(conn);
                    this.leased.add(entry);
                    PoolEntry poolEntry = entry;
                    return (E)poolEntry;
                }
                success = false;
                try {
                    pool.queue(future);
                    this.pending.add(future);
                    if (deadline != null) {
                        success = this.condition.awaitUntil(deadline);
                    } else {
                        this.condition.await();
                        success = true;
                    }
                    if (future.isCancelled()) {
                        throw new ExecutionException(AbstractConnPool.operationAborted());
                    }
                }
                finally {
                    pool.unqueue(future);
                    this.pending.remove(future);
                }
            } while (success || deadline == null || deadline.getTime() > System.currentTimeMillis());
            throw new TimeoutException("Timeout waiting for connection");
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setMaxPerRoute(T route, int max) {
        Args.notNull(route, (String)"Route");
        this.lock.lock();
        try {
            if (max > -1) {
                this.maxPerRoute.put(route, max);
            } else {
                this.maxPerRoute.remove(route);
            }
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getDefaultMaxPerRoute() {
        this.lock.lock();
        try {
            int n = this.defaultMaxPerRoute;
            return n;
        }
        finally {
            this.lock.unlock();
        }
    }

    public Future<E> lease(T route, Object state, FutureCallback<E> callback) {
        Args.notNull(route, (String)"Route");
        Asserts.check((!this.isShutDown ? 1 : 0) != 0, (String)"Connection pool shut down");
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PoolStats getTotalStats() {
        this.lock.lock();
        try {
            PoolStats poolStats = new PoolStats(this.leased.size(), this.pending.size(), this.available.size(), this.maxTotal);
            return poolStats;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String toString() {
        this.lock.lock();
        try {
            StringBuilder buffer = new StringBuilder();
            buffer.append("[leased: ");
            buffer.append(this.leased);
            buffer.append("][available: ");
            buffer.append(this.available);
            buffer.append("][pending: ");
            buffer.append(this.pending);
            buffer.append("]");
            String string = buffer.toString();
            return string;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getMaxPerRoute(T route) {
        Args.notNull(route, (String)"Route");
        this.lock.lock();
        try {
            int n = this.getMax(route);
            return n;
        }
        finally {
            this.lock.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void shutdown() throws IOException {
        if (this.isShutDown) {
            return;
        }
        this.isShutDown = true;
        this.lock.lock();
        try {
            for (PoolEntry poolEntry : this.available) {
                poolEntry.close();
            }
            for (PoolEntry poolEntry : this.leased) {
                poolEntry.close();
            }
            for (RouteSpecificPool routeSpecificPool : this.routeToPool.values()) {
                routeSpecificPool.shutdown();
            }
            this.routeToPool.clear();
            this.leased.clear();
            this.available.clear();
        }
        finally {
            this.lock.unlock();
        }
    }

    static /* synthetic */ Condition access$100(AbstractConnPool x0) {
        return x0.condition;
    }

    public Future<E> lease(T route, Object state) {
        return this.lease(route, state, null);
    }
}
