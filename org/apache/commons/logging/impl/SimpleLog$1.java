/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.impl.SimpleLog
 */
package org.apache.commons.logging.impl;

import java.security.PrivilegedAction;
import org.apache.commons.logging.impl.SimpleLog;

/*
 * Exception performing whole class analysis ignored.
 */
static final class SimpleLog.1
implements PrivilegedAction {
    private final /* synthetic */ String val$name;

    public Object run() {
        ClassLoader threadCL = SimpleLog.access$000();
        if (threadCL == null) return ClassLoader.getSystemResourceAsStream(this.val$name);
        return threadCL.getResourceAsStream(this.val$name);
    }

    SimpleLog.1(String string) {
        this.val$name = string;
    }
}
