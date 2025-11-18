/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE)
public class UriPatternMatcher<T> {
    private final Map<String, T> map = new LinkedHashMap<String, T>();

    public synchronized T lookup(String path) {
        Args.notNull((Object)path, (String)"Request path");
        T obj = this.map.get(path);
        if (obj != null) return obj;
        String bestMatch = null;
        Iterator<String> i$ = this.map.keySet().iterator();
        while (i$.hasNext()) {
            String pattern = i$.next();
            if (!this.matchUriRequestPattern(pattern, path) || bestMatch != null && bestMatch.length() >= pattern.length() && (bestMatch.length() != pattern.length() || !pattern.endsWith("*"))) continue;
            obj = this.map.get(pattern);
            bestMatch = pattern;
        }
        return obj;
    }

    @Deprecated
    public synchronized Map<String, T> getObjects() {
        return this.map;
    }

    public synchronized void register(String pattern, T obj) {
        Args.notNull((Object)pattern, (String)"URI request pattern");
        this.map.put(pattern, obj);
    }

    @Deprecated
    public synchronized void setHandlers(Map<String, T> map) {
        Args.notNull(map, (String)"Map of handlers");
        this.map.clear();
        this.map.putAll(map);
    }

    public String toString() {
        return this.map.toString();
    }

    @Deprecated
    public synchronized void setObjects(Map<String, T> map) {
        Args.notNull(map, (String)"Map of handlers");
        this.map.clear();
        this.map.putAll(map);
    }

    public synchronized void unregister(String pattern) {
        if (pattern == null) {
            return;
        }
        this.map.remove(pattern);
    }

    protected boolean matchUriRequestPattern(String pattern, String path) {
        if (!pattern.equals("*")) return pattern.endsWith("*") && path.startsWith(pattern.substring(0, pattern.length() - 1)) || pattern.startsWith("*") && path.endsWith(pattern.substring(1, pattern.length()));
        return true;
    }

    public synchronized Set<Map.Entry<String, T>> entrySet() {
        return new HashSet<Map.Entry<String, T>>(this.map.entrySet());
    }
}
