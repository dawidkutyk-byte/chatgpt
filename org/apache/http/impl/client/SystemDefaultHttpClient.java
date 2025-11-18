/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ConnectionReuseStrategy
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.conn.routing.HttpRoutePlanner
 *  org.apache.http.impl.DefaultConnectionReuseStrategy
 *  org.apache.http.impl.NoConnectionReuseStrategy
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.impl.conn.PoolingClientConnectionManager
 *  org.apache.http.impl.conn.ProxySelectorRoutePlanner
 *  org.apache.http.impl.conn.SchemeRegistryFactory
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.impl.client;

import java.net.ProxySelector;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.params.HttpParams;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class SystemDefaultHttpClient
extends DefaultHttpClient {
    public SystemDefaultHttpClient(HttpParams params) {
        super(null, params);
    }

    public SystemDefaultHttpClient() {
        super(null, null);
    }

    protected ClientConnectionManager createClientConnectionManager() {
        PoolingClientConnectionManager connmgr = new PoolingClientConnectionManager(SchemeRegistryFactory.createSystemDefault());
        String s = System.getProperty("http.keepAlive", "true");
        if (!"true".equalsIgnoreCase(s)) return connmgr;
        s = System.getProperty("http.maxConnections", "5");
        int max = Integer.parseInt(s);
        connmgr.setDefaultMaxPerRoute(max);
        connmgr.setMaxTotal(2 * max);
        return connmgr;
    }

    protected ConnectionReuseStrategy createConnectionReuseStrategy() {
        String s = System.getProperty("http.keepAlive", "true");
        if (!"true".equalsIgnoreCase(s)) return new NoConnectionReuseStrategy();
        return new DefaultConnectionReuseStrategy();
    }

    protected HttpRoutePlanner createHttpRoutePlanner() {
        return new ProxySelectorRoutePlanner(this.getConnectionManager().getSchemeRegistry(), ProxySelector.getDefault());
    }
}
