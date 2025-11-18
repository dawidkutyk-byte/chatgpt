/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 */
package org.apache.commons.logging.impl;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;

public class Jdk13LumberjackLogger
implements Serializable,
Log {
    private static final long serialVersionUID = -8649807923527610591L;
    private String sourceMethodName = "unknown";
    protected transient Logger logger = null;
    protected String name = null;
    private boolean classAndMethodFound = false;
    protected static final Level dummyLevel = Level.FINE;
    private String sourceClassName = "unknown";

    public Jdk13LumberjackLogger(String name) {
        this.name = name;
        this.logger = this.getLogger();
    }

    private void getClassAndMethod() {
        try {
            Throwable throwable = new Throwable();
            throwable.fillInStackTrace();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            throwable.printStackTrace(printWriter);
            String traceString = stringWriter.getBuffer().toString();
            StringTokenizer tokenizer = new StringTokenizer(traceString, "\n");
            tokenizer.nextToken();
            String line = tokenizer.nextToken();
            while (line.indexOf(this.getClass().getName()) == -1) {
                line = tokenizer.nextToken();
            }
            while (line.indexOf(this.getClass().getName()) >= 0) {
                line = tokenizer.nextToken();
            }
            int start = line.indexOf("at ") + 3;
            int end = line.indexOf(40);
            String temp = line.substring(start, end);
            int lastPeriod = temp.lastIndexOf(46);
            this.sourceClassName = temp.substring(0, lastPeriod);
            this.sourceMethodName = temp.substring(lastPeriod + 1);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.classAndMethodFound = true;
    }

    private void log(Level level, String msg, Throwable ex) {
        if (!this.getLogger().isLoggable(level)) return;
        LogRecord record = new LogRecord(level, msg);
        if (!this.classAndMethodFound) {
            this.getClassAndMethod();
        }
        record.setSourceClassName(this.sourceClassName);
        record.setSourceMethodName(this.sourceMethodName);
        if (ex != null) {
            record.setThrown(ex);
        }
        this.getLogger().log(record);
    }

    public void fatal(Object message) {
        this.log(Level.SEVERE, String.valueOf(message), null);
    }

    public void info(Object message, Throwable exception) {
        this.log(Level.INFO, String.valueOf(message), exception);
    }

    public void error(Object message, Throwable exception) {
        this.log(Level.SEVERE, String.valueOf(message), exception);
    }

    public void trace(Object message, Throwable exception) {
        this.log(Level.FINEST, String.valueOf(message), exception);
    }

    public void debug(Object message) {
        this.log(Level.FINE, String.valueOf(message), null);
    }

    public boolean isDebugEnabled() {
        return this.getLogger().isLoggable(Level.FINE);
    }

    public void fatal(Object message, Throwable exception) {
        this.log(Level.SEVERE, String.valueOf(message), exception);
    }

    public boolean isErrorEnabled() {
        return this.getLogger().isLoggable(Level.SEVERE);
    }

    public void trace(Object message) {
        this.log(Level.FINEST, String.valueOf(message), null);
    }

    public void warn(Object message, Throwable exception) {
        this.log(Level.WARNING, String.valueOf(message), exception);
    }

    public void warn(Object message) {
        this.log(Level.WARNING, String.valueOf(message), null);
    }

    public boolean isWarnEnabled() {
        return this.getLogger().isLoggable(Level.WARNING);
    }

    public void info(Object message) {
        this.log(Level.INFO, String.valueOf(message), null);
    }

    public boolean isInfoEnabled() {
        return this.getLogger().isLoggable(Level.INFO);
    }

    public void debug(Object message, Throwable exception) {
        this.log(Level.FINE, String.valueOf(message), exception);
    }

    public Logger getLogger() {
        if (this.logger != null) return this.logger;
        this.logger = Logger.getLogger(this.name);
        return this.logger;
    }

    public boolean isFatalEnabled() {
        return this.getLogger().isLoggable(Level.SEVERE);
    }

    public void error(Object message) {
        this.log(Level.SEVERE, String.valueOf(message), null);
    }

    public boolean isTraceEnabled() {
        return this.getLogger().isLoggable(Level.FINEST);
    }
}
