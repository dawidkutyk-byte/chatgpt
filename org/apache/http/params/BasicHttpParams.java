/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.params.AbstractHttpParams
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.params;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.params.AbstractHttpParams;
import org.apache.http.params.HttpParams;

@Deprecated
@Contract(threading=ThreadingBehavior.SAFE)
public class BasicHttpParams
extends AbstractHttpParams
implements Cloneable,
Serializable {
    private final Map<String, Object> parameters = new ConcurrentHashMap<String, Object>();
    private static final long serialVersionUID = -7086398485908701455L;

    public Set<String> getNames() {
        return new HashSet<String>(this.parameters.keySet());
    }

    public void setParameters(String[] names, Object value) {
        String[] arr$ = names;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String name = arr$[i$];
            this.setParameter(name, value);
            ++i$;
        }
    }

    public String toString() {
        return "[parameters=" + this.parameters + "]";
    }

    public void copyParams(HttpParams target) {
        Iterator<Map.Entry<String, Object>> i$ = this.parameters.entrySet().iterator();
        while (i$.hasNext()) {
            Map.Entry<String, Object> me = i$.next();
            target.setParameter(me.getKey(), me.getValue());
        }
    }

    public boolean isParameterSet(String name) {
        return this.getParameter(name) != null;
    }

    public HttpParams copy() {
        try {
            return (HttpParams)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new UnsupportedOperationException("Cloning not supported");
        }
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpParams clone = (BasicHttpParams)super.clone();
        this.copyParams((HttpParams)clone);
        return clone;
    }

    public HttpParams setParameter(String name, Object value) {
        if (name == null) {
            return this;
        }
        if (value != null) {
            this.parameters.put(name, value);
        } else {
            this.parameters.remove(name);
        }
        return this;
    }

    public boolean removeParameter(String name) {
        if (!this.parameters.containsKey(name)) return false;
        this.parameters.remove(name);
        return true;
    }

    public void clear() {
        this.parameters.clear();
    }

    public Object getParameter(String name) {
        return this.parameters.get(name);
    }

    public boolean isParameterSetLocally(String name) {
        return this.parameters.get(name) != null;
    }
}
