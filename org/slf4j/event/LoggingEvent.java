/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.event;

import org.slf4j.Marker;
import org.slf4j.event.Level;

public interface LoggingEvent {
    public Throwable getThrowable();

    public Object[] getArgumentArray();

    public Marker getMarker();

    public String getMessage();

    public Level getLevel();

    public String getLoggerName();

    public long getTimeStamp();

    public String getThreadName();
}
