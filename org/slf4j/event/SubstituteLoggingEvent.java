/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.event;

import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.event.LoggingEvent;
import org.slf4j.helpers.SubstituteLogger;

public class SubstituteLoggingEvent
implements LoggingEvent {
    String threadName;
    Object[] argArray;
    String loggerName;
    long timeStamp;
    String message;
    Throwable throwable;
    Level level;
    SubstituteLogger logger;
    Marker marker;

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    @Override
    public Throwable getThrowable() {
        return this.throwable;
    }

    @Override
    public String getLoggerName() {
        return this.loggerName;
    }

    @Override
    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setLogger(SubstituteLogger logger) {
        this.logger = logger;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public Object[] getArgumentArray() {
        return this.argArray;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setArgumentArray(Object[] argArray) {
        this.argArray = argArray;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Marker getMarker() {
        return this.marker;
    }

    public SubstituteLogger getLogger() {
        return this.logger;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getThreadName() {
        return this.threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Level getLevel() {
        return this.level;
    }
}
