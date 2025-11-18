/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.params.AbstractHttpParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpParamsNames
 *  org.apache.http.util.Args
 */
package org.apache.http.params;

import java.util.HashSet;
import java.util.Set;
import org.apache.http.params.AbstractHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpParamsNames;
import org.apache.http.util.Args;

@Deprecated
public final class DefaultedHttpParams
extends AbstractHttpParams {
    private final HttpParams defaults;
    private final HttpParams local;

    public HttpParams copy() {
        HttpParams clone = this.local.copy();
        return new DefaultedHttpParams(clone, this.defaults);
    }

    public HttpParams setParameter(String name, Object value) {
        return this.local.setParameter(name, value);
    }

    public Set<String> getNames() {
        HashSet<String> combined = new HashSet<String>(this.getNames(this.defaults));
        combined.addAll(this.getNames(this.local));
        return combined;
    }

    public Set<String> getDefaultNames() {
        return new HashSet<String>(this.getNames(this.defaults));
    }

    public Object getParameter(String name) {
        Object obj = this.local.getParameter(name);
        if (obj != null) return obj;
        if (this.defaults == null) return obj;
        obj = this.defaults.getParameter(name);
        return obj;
    }

    public HttpParams getDefaults() {
        return this.defaults;
    }

    public DefaultedHttpParams(HttpParams local, HttpParams defaults) {
        this.local = (HttpParams)Args.notNull((Object)local, (String)"Local HTTP parameters");
        this.defaults = defaults;
    }

    public boolean removeParameter(String name) {
        return this.local.removeParameter(name);
    }

    private Set<String> getNames(HttpParams params) {
        if (!(params instanceof HttpParamsNames)) throw new UnsupportedOperationException("HttpParams instance does not implement HttpParamsNames");
        return ((HttpParamsNames)params).getNames();
    }

    public Set<String> getLocalNames() {
        return new HashSet<String>(this.getNames(this.local));
    }
}
