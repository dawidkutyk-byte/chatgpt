/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.LogFactory
 */
package org.apache.commons.logging;

import java.security.PrivilegedAction;
import org.apache.commons.logging.LogFactory;

/*
 * Exception performing whole class analysis ignored.
 */
static final class LogFactory.2
implements PrivilegedAction {
    private final /* synthetic */ ClassLoader val$classLoader;
    private final /* synthetic */ String val$factoryClass;

    public Object run() {
        return LogFactory.createFactory((String)this.val$factoryClass, (ClassLoader)this.val$classLoader);
    }

    LogFactory.2(String string, ClassLoader classLoader) {
        this.val$factoryClass = string;
        this.val$classLoader = classLoader;
    }
}
