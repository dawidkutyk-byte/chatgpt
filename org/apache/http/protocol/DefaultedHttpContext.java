/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Deprecated
public final class DefaultedHttpContext
implements HttpContext {
    private final HttpContext local;
    private final HttpContext defaults;

    public DefaultedHttpContext(HttpContext local, HttpContext defaults) {
        this.local = (HttpContext)Args.notNull((Object)local, (String)"HTTP context");
        this.defaults = defaults;
    }

    public Object getAttribute(String id) {
        Object obj = this.local.getAttribute(id);
        if (obj != null) return obj;
        return this.defaults.getAttribute(id);
    }

    public Object removeAttribute(String id) {
        return this.local.removeAttribute(id);
    }

    public HttpContext getDefaults() {
        return this.defaults;
    }

    public void setAttribute(String id, Object obj) {
        this.local.setAttribute(id, obj);
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[local: ").append(this.local);
        buf.append("defaults: ").append(this.defaults);
        buf.append("]");
        return buf.toString();
    }
}
