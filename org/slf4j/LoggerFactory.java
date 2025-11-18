/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.helpers.NOPServiceProvider
 */
package org.slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.event.SubstituteLoggingEvent;
import org.slf4j.helpers.NOPServiceProvider;
import org.slf4j.helpers.SubstituteLogger;
import org.slf4j.helpers.SubstituteServiceProvider;
import org.slf4j.helpers.Util;
import org.slf4j.spi.SLF4JServiceProvider;

public final class LoggerFactory {
    static final String NO_STATICLOGGERBINDER_URL = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
    static final String MULTIPLE_BINDINGS_URL = "http://www.slf4j.org/codes.html#multiple_bindings";
    static final NOPServiceProvider NOP_FALLBACK_FACTORY;
    static final int UNINITIALIZED = 0;
    static boolean DETECT_LOGGER_NAME_MISMATCH;
    static final String UNSUCCESSFUL_INIT_URL = "http://www.slf4j.org/codes.html#unsuccessfulInit";
    static final String DETECT_LOGGER_NAME_MISMATCH_PROPERTY = "slf4j.detectLoggerNameMismatch";
    private static String STATIC_LOGGER_BINDER_PATH;
    static volatile SLF4JServiceProvider PROVIDER;
    static final String NO_PROVIDERS_URL = "http://www.slf4j.org/codes.html#noProviders";
    static final String LOGGER_NAME_MISMATCH_URL = "http://www.slf4j.org/codes.html#loggerNameMismatch";
    static final String JAVA_VENDOR_PROPERTY = "java.vendor.url";
    private static final String[] API_COMPATIBILITY_LIST;
    static final String SUBSTITUTE_LOGGER_URL = "http://www.slf4j.org/codes.html#substituteLogger";
    static final String NULL_LF_URL = "http://www.slf4j.org/codes.html#null_LF";
    static final String CODES_PREFIX = "http://www.slf4j.org/codes.html";
    static final int FAILED_INITIALIZATION = 2;
    static final String REPLAY_URL = "http://www.slf4j.org/codes.html#replay";
    static final String VERSION_MISMATCH = "http://www.slf4j.org/codes.html#version_mismatch";
    static final SubstituteServiceProvider SUBST_PROVIDER;
    static final int ONGOING_INITIALIZATION = 1;
    static final String IGNORED_BINDINGS_URL = "http://www.slf4j.org/codes.html#ignoredBindings";
    static volatile int INITIALIZATION_STATE;
    static final int NOP_FALLBACK_INITIALIZATION = 4;
    static final int SUCCESSFUL_INITIALIZATION = 3;
    static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also http://www.slf4j.org/codes.html#unsuccessfulInit";

    private static void replaySingleEvent(SubstituteLoggingEvent event) {
        if (event == null) {
            return;
        }
        SubstituteLogger substLogger = event.getLogger();
        String loggerName = substLogger.getName();
        if (substLogger.isDelegateNull()) {
            throw new IllegalStateException("Delegate logger cannot be null at this state.");
        }
        if (substLogger.isDelegateNOP()) return;
        if (substLogger.isDelegateEventAware()) {
            substLogger.log(event);
        } else {
            Util.report(loggerName);
        }
    }

    static {
        INITIALIZATION_STATE = 0;
        SUBST_PROVIDER = new SubstituteServiceProvider();
        NOP_FALLBACK_FACTORY = new NOPServiceProvider();
        DETECT_LOGGER_NAME_MISMATCH = Util.safeGetBooleanSystemProperty(DETECT_LOGGER_NAME_MISMATCH_PROPERTY);
        API_COMPATIBILITY_LIST = new String[]{"1.8", "1.7"};
        STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
    }

    private static void emitSubstitutionWarning() {
        Util.report("The following set of substitute loggers may have been accessed");
        Util.report("during the initialization phase. Logging calls during this");
        Util.report("phase were not honored. However, subsequent logging calls to these");
        Util.report("loggers will work as normally expected.");
        Util.report("See also http://www.slf4j.org/codes.html#substituteLogger");
    }

    private static final void bind() {
        try {
            List<SLF4JServiceProvider> providersList = LoggerFactory.findServiceProviders();
            LoggerFactory.reportMultipleBindingAmbiguity(providersList);
            if (providersList != null && !providersList.isEmpty()) {
                PROVIDER = providersList.get(0);
                PROVIDER.initialize();
                INITIALIZATION_STATE = 3;
                LoggerFactory.reportActualBinding(providersList);
                LoggerFactory.fixSubstituteLoggers();
                LoggerFactory.replayEvents();
                SUBST_PROVIDER.getSubstituteLoggerFactory().clear();
            } else {
                INITIALIZATION_STATE = 4;
                Util.report("No SLF4J providers were found.");
                Util.report("Defaulting to no-operation (NOP) logger implementation");
                Util.report("See http://www.slf4j.org/codes.html#noProviders for further details.");
                Set<URL> staticLoggerBinderPathSet = LoggerFactory.findPossibleStaticLoggerBinderPathSet();
                LoggerFactory.reportIgnoredStaticLoggerBinders(staticLoggerBinderPathSet);
            }
        }
        catch (Exception e) {
            LoggerFactory.failedBinding(e);
            throw new IllegalStateException("Unexpected initialization failure", e);
        }
    }

    private static void reportIgnoredStaticLoggerBinders(Set<URL> staticLoggerBinderPathSet) {
        if (staticLoggerBinderPathSet.isEmpty()) {
            return;
        }
        Util.report("Class path contains SLF4J bindings targeting slf4j-api versions prior to 1.8.");
        Iterator<URL> i$ = staticLoggerBinderPathSet.iterator();
        while (true) {
            if (!i$.hasNext()) {
                Util.report("See http://www.slf4j.org/codes.html#ignoredBindings for an explanation.");
                return;
            }
            URL path = i$.next();
            Util.report("Ignoring binding found at [" + path + "]");
        }
    }

    public static Logger getLogger(String name) {
        ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
        return iLoggerFactory.getLogger(name);
    }

    private static List<SLF4JServiceProvider> findServiceProviders() {
        ServiceLoader<SLF4JServiceProvider> serviceLoader = ServiceLoader.load(SLF4JServiceProvider.class);
        ArrayList<SLF4JServiceProvider> providerList = new ArrayList<SLF4JServiceProvider>();
        Iterator<SLF4JServiceProvider> i$ = serviceLoader.iterator();
        while (i$.hasNext()) {
            SLF4JServiceProvider provider = i$.next();
            providerList.add(provider);
        }
        return providerList;
    }

    private static final void versionSanityCheck() {
        try {
            String requested = PROVIDER.getRequesteApiVersion();
            boolean match = false;
            for (String aAPI_COMPATIBILITY_LIST : API_COMPATIBILITY_LIST) {
                if (!requested.startsWith(aAPI_COMPATIBILITY_LIST)) continue;
                match = true;
            }
            if (match) return;
            Util.report("The requested version " + requested + " by your slf4j binding is not compatible with " + Arrays.asList(API_COMPATIBILITY_LIST).toString());
            Util.report("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
        }
        catch (NoSuchFieldError requested) {
        }
        catch (Throwable e) {
            Util.report("Unexpected problem occured during version sanity check", e);
        }
    }

    private static void emitReplayWarning(int eventCount) {
        Util.report("A number (" + eventCount + ") of logging calls during the initialization phase have been intercepted and are");
        Util.report("now being replayed. These are subject to the filtering rules of the underlying logging system.");
        Util.report("See also http://www.slf4j.org/codes.html#replay");
    }

    private static boolean isAmbiguousProviderList(List<SLF4JServiceProvider> providerList) {
        return providerList.size() > 1;
    }

    private static void emitReplayOrSubstituionWarning(SubstituteLoggingEvent event, int queueSize) {
        if (event.getLogger().isDelegateEventAware()) {
            LoggerFactory.emitReplayWarning(queueSize);
        } else {
            if (event.getLogger().isDelegateNOP()) return;
            LoggerFactory.emitSubstitutionWarning();
        }
    }

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        if (!DETECT_LOGGER_NAME_MISMATCH) return logger;
        Class<?> autoComputedCallingClass = Util.getCallingClass();
        if (autoComputedCallingClass == null) return logger;
        if (!LoggerFactory.nonMatchingClasses(clazz, autoComputedCallingClass)) return logger;
        Util.report(String.format("Detected logger name mismatch. Given name: \"%s\"; computed name: \"%s\".", logger.getName(), autoComputedCallingClass.getName()));
        Util.report("See http://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
        return logger;
    }

    private static void reportMultipleBindingAmbiguity(List<SLF4JServiceProvider> providerList) {
        if (!LoggerFactory.isAmbiguousProviderList(providerList)) return;
        Util.report("Class path contains multiple SLF4J providers.");
        Iterator<SLF4JServiceProvider> i$ = providerList.iterator();
        while (true) {
            if (!i$.hasNext()) {
                Util.report("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
                return;
            }
            SLF4JServiceProvider provider = i$.next();
            Util.report("Found provider [" + provider + "]");
        }
    }

    private static void replayEvents() {
        LinkedBlockingQueue<SubstituteLoggingEvent> queue = SUBST_PROVIDER.getSubstituteLoggerFactory().getEventQueue();
        int queueSize = queue.size();
        int count = 0;
        int maxDrain = 128;
        ArrayList eventList = new ArrayList(128);
        int numDrained;
        while ((numDrained = queue.drainTo(eventList, 128)) != 0) {
            for (SubstituteLoggingEvent event : eventList) {
                LoggerFactory.replaySingleEvent(event);
                if (count++ != 0) continue;
                LoggerFactory.emitReplayOrSubstituionWarning(event, queueSize);
            }
            eventList.clear();
        }
        return;
    }

    static void reset() {
        INITIALIZATION_STATE = 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void fixSubstituteLoggers() {
        SubstituteServiceProvider substituteServiceProvider = SUBST_PROVIDER;
        synchronized (substituteServiceProvider) {
            SUBST_PROVIDER.getSubstituteLoggerFactory().postInitialization();
            Iterator<SubstituteLogger> i$ = SUBST_PROVIDER.getSubstituteLoggerFactory().getLoggers().iterator();
            while (i$.hasNext()) {
                SubstituteLogger substLogger = i$.next();
                Logger logger = LoggerFactory.getLogger(substLogger.getName());
                substLogger.setDelegate(logger);
            }
            return;
        }
    }

    private LoggerFactory() {
    }

    public static ILoggerFactory getILoggerFactory() {
        return LoggerFactory.getProvider().getLoggerFactory();
    }

    static Set<URL> findPossibleStaticLoggerBinderPathSet() {
        LinkedHashSet<URL> staticLoggerBinderPathSet = new LinkedHashSet<URL>();
        try {
            ClassLoader loggerFactoryClassLoader = LoggerFactory.class.getClassLoader();
            Enumeration<URL> paths = loggerFactoryClassLoader == null ? ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH) : loggerFactoryClassLoader.getResources(STATIC_LOGGER_BINDER_PATH);
            while (paths.hasMoreElements()) {
                URL path = paths.nextElement();
                staticLoggerBinderPathSet.add(path);
            }
            return staticLoggerBinderPathSet;
        }
        catch (IOException ioe) {
            Util.report("Error getting resources from path", ioe);
        }
        return staticLoggerBinderPathSet;
    }

    private static final void performInitialization() {
        LoggerFactory.bind();
        if (INITIALIZATION_STATE != 3) return;
        LoggerFactory.versionSanityCheck();
    }

    private static void reportActualBinding(List<SLF4JServiceProvider> providerList) {
        if (providerList.isEmpty()) return;
        if (!LoggerFactory.isAmbiguousProviderList(providerList)) return;
        Util.report("Actual provider is of type [" + providerList.get(0) + "]");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled unnecessary exception pruning
     * Converted monitor instructions to comments
     */
    static SLF4JServiceProvider getProvider() {
        if (INITIALIZATION_STATE == 0) {
            Class<LoggerFactory> clazz = LoggerFactory.class;
            // MONITORENTER : org.slf4j.LoggerFactory.class
            if (INITIALIZATION_STATE == 0) {
                INITIALIZATION_STATE = 1;
                LoggerFactory.performInitialization();
            }
            // MONITOREXIT : clazz
        }
        switch (INITIALIZATION_STATE) {
            case 3: {
                return PROVIDER;
            }
            case 4: {
                return NOP_FALLBACK_FACTORY;
            }
            case 2: {
                throw new IllegalStateException(UNSUCCESSFUL_INIT_MSG);
            }
            case 1: {
                return SUBST_PROVIDER;
            }
        }
        throw new IllegalStateException("Unreachable code");
    }

    static void failedBinding(Throwable t) {
        INITIALIZATION_STATE = 2;
        Util.report("Failed to instantiate SLF4J LoggerFactory", t);
    }

    private static boolean nonMatchingClasses(Class<?> clazz, Class<?> autoComputedCallingClass) {
        return !autoComputedCallingClass.isAssignableFrom(clazz);
    }
}
