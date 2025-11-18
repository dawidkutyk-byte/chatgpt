/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.protocol.HttpContext
 *  org.apache.http.util.Args
 */
package org.apache.http.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE_CONDITIONAL)
public class BasicHttpContext
implements HttpContext {
    private final Map<String, Object> map = new ConcurrentHashMap<String, Object>();
    private final HttpContext parentContext;

    public void clear() {
        this.map.clear();
    }

    public BasicHttpContext(HttpContext parentContext) {
        this.parentContext = parentContext;
    }

    public Object getAttribute(String id) {
        Args.notNull((Object)id, (String)"Id");
        Object obj = this.map.get(id);
        if (obj != null) return obj;
        if (this.parentContext == null) return obj;
        obj = this.parentContext.getAttribute(id);
        return obj;
    }

    public BasicHttpContext() {
        this(null);
    }

    public String toString() {
        return this.map.toString();
    }

    public Object removeAttribute(String id) {
        Args.notNull((Object)id, (String)"Id");
        return this.map.remove(id);
    }

    public void setAttribute(String id, Object obj) {
        Args.notNull((Object)id, (String)"Id");
        if (obj != null) {
            this.map.put(id, obj);
        } else {
            this.map.remove(id);
        }
    }
}
