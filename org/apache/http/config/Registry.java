/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.config.Lookup
 */
package org.apache.http.config;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.config.Lookup;

@Contract(threading=ThreadingBehavior.SAFE)
public final class Registry<I>
implements Lookup<I> {
    private final Map<String, I> map;

    public String toString() {
        return this.map.toString();
    }

    Registry(Map<String, I> map) {
        this.map = new ConcurrentHashMap<String, I>(map);
    }

    public I lookup(String key) {
        if (key != null) return this.map.get(key.toLowerCase(Locale.ROOT));
        return null;
    }
}
