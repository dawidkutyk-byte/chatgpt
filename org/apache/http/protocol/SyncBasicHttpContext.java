/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.protocol.BasicHttpContext
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.protocol;

import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

@Deprecated
public class SyncBasicHttpContext
extends BasicHttpContext {
    public synchronized void setAttribute(String id, Object obj) {
        super.setAttribute(id, obj);
    }

    public synchronized Object removeAttribute(String id) {
        return super.removeAttribute(id);
    }

    public SyncBasicHttpContext() {
    }

    public synchronized Object getAttribute(String id) {
        return super.getAttribute(id);
    }

    public synchronized void clear() {
        super.clear();
    }

    public SyncBasicHttpContext(HttpContext parentContext) {
        super(parentContext);
    }
}
