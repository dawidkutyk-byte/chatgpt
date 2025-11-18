/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.avalon.framework.logger.Logger
 *  org.apache.commons.logging.Log
 */
package org.apache.commons.logging.impl;

import org.apache.avalon.framework.logger.Logger;
import org.apache.commons.logging.Log;

public class AvalonLogger
implements Log {
    private final transient Logger logger;
    private static volatile Logger defaultLogger = null;

    public boolean isTraceEnabled() {
        return this.getLogger().isDebugEnabled();
    }

    public void fatal(Object message, Throwable t) {
        if (!this.getLogger().isFatalErrorEnabled()) return;
        this.getLogger().fatalError(String.valueOf(message), t);
    }

    public boolean isDebugEnabled() {
        return this.getLogger().isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return this.getLogger().isInfoEnabled();
    }

    public void debug(Object message) {
        if (!this.getLogger().isDebugEnabled()) return;
        this.getLogger().debug(String.valueOf(message));
    }

    public Logger getLogger() {
        return this.logger;
    }

    public void warn(Object message) {
        if (!this.getLogger().isWarnEnabled()) return;
        this.getLogger().warn(String.valueOf(message));
    }

    public void error(Object message, Throwable t) {
        if (!this.getLogger().isErrorEnabled()) return;
        this.getLogger().error(String.valueOf(message), t);
    }

    public AvalonLogger(String name) {
        if (defaultLogger == null) {
            throw new NullPointerException("default logger has to be specified if this constructor is used!");
        }
        this.logger = defaultLogger.getChildLogger(name);
    }

    public void error(Object message) {
        if (!this.getLogger().isErrorEnabled()) return;
        this.getLogger().error(String.valueOf(message));
    }

    public void info(Object message) {
        if (!this.getLogger().isInfoEnabled()) return;
        this.getLogger().info(String.valueOf(message));
    }

    public static void setDefaultLogger(Logger logger) {
        defaultLogger = logger;
    }

    public void fatal(Object message) {
        if (!this.getLogger().isFatalErrorEnabled()) return;
        this.getLogger().fatalError(String.valueOf(message));
    }

    public boolean isWarnEnabled() {
        return this.getLogger().isWarnEnabled();
    }

    public void trace(Object message, Throwable t) {
        if (!this.getLogger().isDebugEnabled()) return;
        this.getLogger().debug(String.valueOf(message), t);
    }

    public void trace(Object message) {
        if (!this.getLogger().isDebugEnabled()) return;
        this.getLogger().debug(String.valueOf(message));
    }

    public boolean isFatalEnabled() {
        return this.getLogger().isFatalErrorEnabled();
    }

    public void debug(Object message, Throwable t) {
        if (!this.getLogger().isDebugEnabled()) return;
        this.getLogger().debug(String.valueOf(message), t);
    }

    public boolean isErrorEnabled() {
        return this.getLogger().isErrorEnabled();
    }

    public void info(Object message, Throwable t) {
        if (!this.getLogger().isInfoEnabled()) return;
        this.getLogger().info(String.valueOf(message), t);
    }

    public AvalonLogger(Logger logger) {
        this.logger = logger;
    }

    public void warn(Object message, Throwable t) {
        if (!this.getLogger().isWarnEnabled()) return;
        this.getLogger().warn(String.valueOf(message), t);
    }
}
