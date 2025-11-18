/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.auth.AuthScheme
 *  org.apache.http.auth.AuthSchemeFactory
 *  org.apache.http.auth.AuthSchemeProvider
 *  org.apache.http.config.Lookup
 *  org.apache.http.params.HttpParams
 *  org.apache.http.util.Args
 */
package org.apache.http.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.config.Lookup;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE)
public final class AuthSchemeRegistry
implements Lookup<AuthSchemeProvider> {
    private final ConcurrentHashMap<String, AuthSchemeFactory> registeredSchemes = new ConcurrentHashMap();

    public AuthScheme getAuthScheme(String name, HttpParams params) throws IllegalStateException {
        Args.notNull((Object)name, (String)"Name");
        AuthSchemeFactory factory = this.registeredSchemes.get(name.toLowerCase(Locale.ENGLISH));
        if (factory == null) throw new IllegalStateException("Unsupported authentication scheme: " + name);
        return factory.newInstance(params);
    }

    public List<String> getSchemeNames() {
        return new ArrayList<String>(this.registeredSchemes.keySet());
    }

    public void setItems(Map<String, AuthSchemeFactory> map) {
        if (map == null) {
            return;
        }
        this.registeredSchemes.clear();
        this.registeredSchemes.putAll(map);
    }

    public void unregister(String name) {
        Args.notNull((Object)name, (String)"Name");
        this.registeredSchemes.remove(name.toLowerCase(Locale.ENGLISH));
    }

    public void register(String name, AuthSchemeFactory factory) {
        Args.notNull((Object)name, (String)"Name");
        Args.notNull((Object)factory, (String)"Authentication scheme factory");
        this.registeredSchemes.put(name.toLowerCase(Locale.ENGLISH), factory);
    }

    public AuthSchemeProvider lookup(String name) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }
}
