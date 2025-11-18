/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.LogFactory
 */
package org.apache.commons.logging;

import java.io.IOException;
import java.security.PrivilegedAction;
import org.apache.commons.logging.LogFactory;

/*
 * Exception performing whole class analysis ignored.
 */
static final class LogFactory.4
implements PrivilegedAction {
    private final /* synthetic */ ClassLoader val$loader;
    private final /* synthetic */ String val$name;

    public Object run() {
        try {
            if (this.val$loader == null) return ClassLoader.getSystemResources(this.val$name);
            return this.val$loader.getResources(this.val$name);
        }
        catch (IOException e) {
            if (!LogFactory.isDiagnosticsEnabled()) return null;
            LogFactory.access$000((String)new StringBuffer().append("Exception while trying to find configuration file ").append(this.val$name).append(":").append(e.getMessage()).toString());
            return null;
        }
        catch (NoSuchMethodError e) {
            return null;
        }
    }

    LogFactory.4(ClassLoader classLoader, String string) {
        this.val$loader = classLoader;
        this.val$name = string;
    }
}
