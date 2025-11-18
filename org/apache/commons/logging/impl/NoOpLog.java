/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 */
package org.apache.commons.logging.impl;

import java.io.Serializable;
import org.apache.commons.logging.Log;

public class NoOpLog
implements Serializable,
Log {
    private static final long serialVersionUID = 561423906191706148L;

    public void warn(Object message) {
    }

    public void trace(Object message) {
    }

    public NoOpLog(String name) {
    }

    public void fatal(Object message) {
    }

    public NoOpLog() {
    }

    public final boolean isFatalEnabled() {
        return false;
    }

    public final boolean isInfoEnabled() {
        return false;
    }

    public final boolean isTraceEnabled() {
        return false;
    }

    public void debug(Object message, Throwable t) {
    }

    public void info(Object message, Throwable t) {
    }

    public final boolean isDebugEnabled() {
        return false;
    }

    public void debug(Object message) {
    }

    public void fatal(Object message, Throwable t) {
    }

    public void warn(Object message, Throwable t) {
    }

    public void error(Object message, Throwable t) {
    }

    public void trace(Object message, Throwable t) {
    }

    public final boolean isErrorEnabled() {
        return false;
    }

    public void info(Object message) {
    }

    public void error(Object message) {
    }

    public final boolean isWarnEnabled() {
        return false;
    }
}
