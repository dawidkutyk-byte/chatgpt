/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.BackoffManager
 *  org.apache.http.conn.routing.HttpRoute
 *  org.apache.http.impl.client.Clock
 *  org.apache.http.impl.client.SystemClock
 *  org.apache.http.pool.ConnPoolControl
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.client;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.BackoffManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.Clock;
import org.apache.http.impl.client.SystemClock;
import org.apache.http.pool.ConnPoolControl;
import org.apache.http.util.Args;

public class AIMDBackoffManager
implements BackoffManager {
    private final ConnPoolControl<HttpRoute> connPerRoute;
    private final Clock clock;
    private final Map<HttpRoute, Long> lastRouteProbes;
    private final Map<HttpRoute, Long> lastRouteBackoffs;
    private double backoffFactor = 0.5;
    private int cap = 2;
    private long coolDown = 5000L;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void probe(HttpRoute route) {
        ConnPoolControl<HttpRoute> connPoolControl = this.connPerRoute;
        synchronized (connPoolControl) {
            int curr = this.connPerRoute.getMaxPerRoute((Object)route);
            int max = curr >= this.cap ? this.cap : curr + 1;
            Long lastProbe = this.getLastUpdate(this.lastRouteProbes, route);
            Long lastBackoff = this.getLastUpdate(this.lastRouteBackoffs, route);
            long now = this.clock.getCurrentTime();
            if (now - lastProbe < this.coolDown) return;
            if (now - lastBackoff < this.coolDown) {
                return;
            }
            this.connPerRoute.setMaxPerRoute((Object)route, max);
            this.lastRouteProbes.put(route, now);
        }
    }

    public void setBackoffFactor(double d) {
        Args.check((d > 0.0 && d < 1.0 ? 1 : 0) != 0, (String)"Backoff factor must be 0.0 < f < 1.0");
        this.backoffFactor = d;
    }

    public AIMDBackoffManager(ConnPoolControl<HttpRoute> connPerRoute) {
        this(connPerRoute, (Clock)new SystemClock());
    }

    public void setCooldownMillis(long l) {
        Args.positive((long)this.coolDown, (String)"Cool down");
        this.coolDown = l;
    }

    private int getBackedOffPoolSize(int curr) {
        if (curr > 1) return (int)Math.floor(this.backoffFactor * (double)curr);
        return 1;
    }

    public void setPerHostConnectionCap(int cap) {
        Args.positive((int)cap, (String)"Per host connection cap");
        this.cap = cap;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void backOff(HttpRoute route) {
        ConnPoolControl<HttpRoute> connPoolControl = this.connPerRoute;
        synchronized (connPoolControl) {
            int curr = this.connPerRoute.getMaxPerRoute((Object)route);
            Long lastUpdate = this.getLastUpdate(this.lastRouteBackoffs, route);
            long now = this.clock.getCurrentTime();
            if (now - lastUpdate < this.coolDown) {
                return;
            }
            this.connPerRoute.setMaxPerRoute((Object)route, this.getBackedOffPoolSize(curr));
            this.lastRouteBackoffs.put(route, now);
        }
    }

    private Long getLastUpdate(Map<HttpRoute, Long> updates, HttpRoute route) {
        Long lastUpdate = updates.get(route);
        if (lastUpdate != null) return lastUpdate;
        lastUpdate = 0L;
        return lastUpdate;
    }

    AIMDBackoffManager(ConnPoolControl<HttpRoute> connPerRoute, Clock clock) {
        this.clock = clock;
        this.connPerRoute = connPerRoute;
        this.lastRouteProbes = new HashMap<HttpRoute, Long>();
        this.lastRouteBackoffs = new HashMap<HttpRoute, Long>();
    }
}
