/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ExceptionLogger
 */
package org.apache.http;

import org.apache.http.ExceptionLogger;

static final class ExceptionLogger.2
implements ExceptionLogger {
    ExceptionLogger.2() {
    }

    public void log(Exception ex) {
        ex.printStackTrace();
    }
}
