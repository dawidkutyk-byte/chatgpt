/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.event.SubstituteLoggingEvent;
import org.slf4j.helpers.SubstituteLogger;

public class SubstituteLoggerFactory
implements ILoggerFactory {
    final Map<String, SubstituteLogger> loggers = new ConcurrentHashMap<String, SubstituteLogger>();
    volatile boolean postInitialization = false;
    final LinkedBlockingQueue<SubstituteLoggingEvent> eventQueue = new LinkedBlockingQueue();

    public LinkedBlockingQueue<SubstituteLoggingEvent> getEventQueue() {
        return this.eventQueue;
    }

    @Override
    public synchronized Logger getLogger(String name) {
        SubstituteLogger logger = this.loggers.get(name);
        if (logger != null) return logger;
        logger = new SubstituteLogger(name, this.eventQueue, this.postInitialization);
        this.loggers.put(name, logger);
        return logger;
    }

    public List<String> getLoggerNames() {
        return new ArrayList<String>(this.loggers.keySet());
    }

    public void clear() {
        this.loggers.clear();
        this.eventQueue.clear();
    }

    public void postInitialization() {
        this.postInitialization = true;
    }

    public List<SubstituteLogger> getLoggers() {
        return new ArrayList<SubstituteLogger>(this.loggers.values());
    }
}
