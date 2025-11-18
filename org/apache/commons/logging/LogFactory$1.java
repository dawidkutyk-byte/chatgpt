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
static final class LogFactory.1
implements PrivilegedAction {
    public Object run() {
        return LogFactory.directGetContextClassLoader();
    }

    LogFactory.1() {
    }
}
