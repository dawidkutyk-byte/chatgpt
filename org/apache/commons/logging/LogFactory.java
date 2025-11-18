/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogConfigurationException
 *  org.apache.commons.logging.LogFactory$4
 *  org.apache.commons.logging.LogFactory$5
 */
package org.apache.commons.logging;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.AccessController;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

public abstract class LogFactory {
    private static PrintStream diagnosticsStream;
    protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
    public static final String DIAGNOSTICS_DEST_PROPERTY = "org.apache.commons.logging.diagnostics.dest";
    public static final String FACTORY_PROPERTY = "org.apache.commons.logging.LogFactory";
    protected static Hashtable factories;
    public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "org.apache.commons.logging.LogFactory.HashtableImpl";
    protected static volatile LogFactory nullClassLoaderFactory;
    static /* synthetic */ Class class$org$apache$commons$logging$LogFactory;
    private static final String WEAK_HASHTABLE_CLASSNAME = "org.apache.commons.logging.impl.WeakHashtable";
    public static final String FACTORY_PROPERTIES = "commons-logging.properties";
    public static final String TCCL_KEY = "use_tccl";
    private static final ClassLoader thisClassLoader;
    private static final String diagnosticPrefix;
    public static final String PRIORITY_KEY = "priority";
    public static final String FACTORY_DEFAULT = "org.apache.commons.logging.impl.LogFactoryImpl";

    protected static boolean isDiagnosticsEnabled() {
        return diagnosticsStream != null;
    }

    private static InputStream getResourceAsStream(ClassLoader loader, String name) {
        return (InputStream)AccessController.doPrivileged(new /* Unavailable Anonymous Inner Class!! */);
    }

    public abstract void setAttribute(String var1, Object var2);

    private static boolean implementsLogFactory(Class logFactoryClass) {
        boolean implementsLogFactory = false;
        if (logFactoryClass == null) return implementsLogFactory;
        try {
            ClassLoader logFactoryClassLoader = logFactoryClass.getClassLoader();
            if (logFactoryClassLoader == null) {
                LogFactory.logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
            } else {
                LogFactory.logHierarchy("[CUSTOM LOG FACTORY] ", logFactoryClassLoader);
                Class<?> factoryFromCustomLoader = Class.forName(FACTORY_PROPERTY, false, logFactoryClassLoader);
                implementsLogFactory = factoryFromCustomLoader.isAssignableFrom(logFactoryClass);
                if (implementsLogFactory) {
                    LogFactory.logDiagnostic(new StringBuffer().append("[CUSTOM LOG FACTORY] ").append(logFactoryClass.getName()).append(" implements LogFactory but was loaded by an incompatible classloader.").toString());
                } else {
                    LogFactory.logDiagnostic(new StringBuffer().append("[CUSTOM LOG FACTORY] ").append(logFactoryClass.getName()).append(" does not implement LogFactory.").toString());
                }
            }
        }
        catch (SecurityException e) {
            LogFactory.logDiagnostic(new StringBuffer().append("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ").append(e.getMessage()).toString());
        }
        catch (LinkageError e) {
            LogFactory.logDiagnostic(new StringBuffer().append("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ").append(e.getMessage()).toString());
        }
        catch (ClassNotFoundException e) {
            LogFactory.logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
        }
        return implementsLogFactory;
    }

    protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader) {
        return LogFactory.newFactory(factoryClass, classLoader, null);
    }

    public static String objectId(Object o) {
        if (o != null) return new StringBuffer().append(o.getClass().getName()).append("@").append(System.identityHashCode(o)).toString();
        return "null";
    }

    private static String getSystemProperty(String key, String def) throws SecurityException {
        return (String)AccessController.doPrivileged(new /* Unavailable Anonymous Inner Class!! */);
    }

    private static final Hashtable createFactoryStore() {
        Hashtable result;
        block6: {
            String storeImplementationClass;
            result = null;
            try {
                storeImplementationClass = LogFactory.getSystemProperty(HASHTABLE_IMPLEMENTATION_PROPERTY, null);
            }
            catch (SecurityException ex) {
                storeImplementationClass = null;
            }
            if (storeImplementationClass == null) {
                storeImplementationClass = WEAK_HASHTABLE_CLASSNAME;
            }
            try {
                Class<?> implementationClass = Class.forName(storeImplementationClass);
                result = (Hashtable)implementationClass.newInstance();
            }
            catch (Throwable t) {
                LogFactory.handleThrowable(t);
                if (WEAK_HASHTABLE_CLASSNAME.equals(storeImplementationClass)) break block6;
                if (LogFactory.isDiagnosticsEnabled()) {
                    LogFactory.logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
                }
                System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
            }
        }
        if (result != null) return result;
        result = new Hashtable();
        return result;
    }

    public abstract String[] getAttributeNames();

    private static ClassLoader getContextClassLoaderInternal() throws LogConfigurationException {
        return (ClassLoader)AccessController.doPrivileged(new /* Unavailable Anonymous Inner Class!! */);
    }

    private static final Properties getConfigurationFile(ClassLoader classLoader, String fileName) {
        URL propsUrl;
        Properties props;
        block11: {
            props = null;
            double priority = 0.0;
            propsUrl = null;
            try {
                Enumeration urls = LogFactory.getResources(classLoader, fileName);
                if (urls == null) {
                    return null;
                }
                while (urls.hasMoreElements()) {
                    URL url = (URL)urls.nextElement();
                    Properties newProps = LogFactory.getProperties(url);
                    if (newProps == null) continue;
                    if (props == null) {
                        propsUrl = url;
                        props = newProps;
                        String priorityStr = props.getProperty(PRIORITY_KEY);
                        priority = 0.0;
                        if (priorityStr != null) {
                            priority = Double.parseDouble(priorityStr);
                        }
                        if (!LogFactory.isDiagnosticsEnabled()) continue;
                        LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file found at '").append(url).append("'").append(" with priority ").append(priority).toString());
                        continue;
                    }
                    String newPriorityStr = newProps.getProperty(PRIORITY_KEY);
                    double newPriority = 0.0;
                    if (newPriorityStr != null) {
                        newPriority = Double.parseDouble(newPriorityStr);
                    }
                    if (newPriority > priority) {
                        if (LogFactory.isDiagnosticsEnabled()) {
                            LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file at '").append(url).append("'").append(" with priority ").append(newPriority).append(" overrides file at '").append(propsUrl).append("'").append(" with priority ").append(priority).toString());
                        }
                        propsUrl = url;
                        props = newProps;
                        priority = newPriority;
                        continue;
                    }
                    if (!LogFactory.isDiagnosticsEnabled()) continue;
                    LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file at '").append(url).append("'").append(" with priority ").append(newPriority).append(" does not override file at '").append(propsUrl).append("'").append(" with priority ").append(priority).toString());
                }
            }
            catch (SecurityException e) {
                if (!LogFactory.isDiagnosticsEnabled()) break block11;
                LogFactory.logDiagnostic("SecurityException thrown while trying to find/read config files.");
            }
        }
        if (!LogFactory.isDiagnosticsEnabled()) return props;
        if (props == null) {
            LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] No properties file of name '").append(fileName).append("' found.").toString());
        } else {
            LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file of name '").append(fileName).append("' found at '").append(propsUrl).append('\"').toString());
        }
        return props;
    }

    private static String trim(String src) {
        if (src != null) return src.trim();
        return null;
    }

    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
        return LogFactory.directGetContextClassLoader();
    }

    public abstract void release();

    private static void logHierarchy(String prefix, ClassLoader classLoader) {
        StringBuffer buf;
        block8: {
            ClassLoader systemClassLoader;
            if (!LogFactory.isDiagnosticsEnabled()) {
                return;
            }
            if (classLoader != null) {
                String classLoaderString = classLoader.toString();
                LogFactory.logDiagnostic(new StringBuffer().append(prefix).append(LogFactory.objectId(classLoader)).append(" == '").append(classLoaderString).append("'").toString());
            }
            try {
                systemClassLoader = ClassLoader.getSystemClassLoader();
            }
            catch (SecurityException ex) {
                LogFactory.logDiagnostic(new StringBuffer().append(prefix).append("Security forbids determining the system classloader.").toString());
                return;
            }
            if (classLoader == null) return;
            buf = new StringBuffer(new StringBuffer().append(prefix).append("ClassLoader tree:").toString());
            do {
                buf.append(LogFactory.objectId(classLoader));
                if (classLoader == systemClassLoader) {
                    buf.append(" (SYSTEM) ");
                }
                try {
                    classLoader = classLoader.getParent();
                }
                catch (SecurityException ex) {
                    buf.append(" --> SECRET");
                    break block8;
                }
                buf.append(" --> ");
            } while (classLoader != null);
            buf.append("BOOT");
        }
        LogFactory.logDiagnostic(buf.toString());
    }

    protected static ClassLoader directGetContextClassLoader() throws LogConfigurationException {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        catch (SecurityException securityException) {
            // empty catch block
        }
        return classLoader;
    }

    private static PrintStream initDiagnostics() {
        String dest;
        try {
            dest = LogFactory.getSystemProperty(DIAGNOSTICS_DEST_PROPERTY, null);
            if (dest == null) {
                return null;
            }
        }
        catch (SecurityException ex) {
            return null;
        }
        if (dest.equals("STDOUT")) {
            return System.out;
        }
        if (dest.equals("STDERR")) {
            return System.err;
        }
        try {
            FileOutputStream fos = new FileOutputStream(dest, true);
            return new PrintStream(fos);
        }
        catch (IOException ex) {
            return null;
        }
    }

    protected static Object createFactory(String factoryClass, ClassLoader classLoader) {
        Class<?> logFactoryClass = null;
        try {
            block15: {
                if (classLoader != null) {
                    try {
                        logFactoryClass = classLoader.loadClass(factoryClass);
                        if ((class$org$apache$commons$logging$LogFactory == null ? (class$org$apache$commons$logging$LogFactory = LogFactory.class$(FACTORY_PROPERTY)) : class$org$apache$commons$logging$LogFactory).isAssignableFrom(logFactoryClass)) {
                            if (!LogFactory.isDiagnosticsEnabled()) return (LogFactory)logFactoryClass.newInstance();
                            LogFactory.logDiagnostic(new StringBuffer().append("Loaded class ").append(logFactoryClass.getName()).append(" from classloader ").append(LogFactory.objectId(classLoader)).toString());
                        } else {
                            if (!LogFactory.isDiagnosticsEnabled()) return (LogFactory)logFactoryClass.newInstance();
                            LogFactory.logDiagnostic(new StringBuffer().append("Factory class ").append(logFactoryClass.getName()).append(" loaded from classloader ").append(LogFactory.objectId(logFactoryClass.getClassLoader())).append(" does not extend '").append((class$org$apache$commons$logging$LogFactory == null ? (class$org$apache$commons$logging$LogFactory = LogFactory.class$(FACTORY_PROPERTY)) : class$org$apache$commons$logging$LogFactory).getName()).append("' as loaded by this classloader.").toString());
                            LogFactory.logHierarchy("[BAD CL TREE] ", classLoader);
                        }
                        return (LogFactory)logFactoryClass.newInstance();
                    }
                    catch (ClassNotFoundException ex) {
                        if (classLoader == thisClassLoader) {
                            if (!LogFactory.isDiagnosticsEnabled()) throw ex;
                            LogFactory.logDiagnostic(new StringBuffer().append("Unable to locate any class called '").append(factoryClass).append("' via classloader ").append(LogFactory.objectId(classLoader)).toString());
                            throw ex;
                        }
                    }
                    catch (NoClassDefFoundError e) {
                        if (classLoader == thisClassLoader) {
                            if (!LogFactory.isDiagnosticsEnabled()) throw e;
                            LogFactory.logDiagnostic(new StringBuffer().append("Class '").append(factoryClass).append("' cannot be loaded").append(" via classloader ").append(LogFactory.objectId(classLoader)).append(" - it depends on some other class that cannot be found.").toString());
                            throw e;
                        }
                    }
                    catch (ClassCastException e) {
                        if (classLoader != thisClassLoader) break block15;
                        boolean implementsLogFactory = LogFactory.implementsLogFactory(logFactoryClass);
                        StringBuffer msg = new StringBuffer();
                        msg.append("The application has specified that a custom LogFactory implementation ");
                        msg.append("should be used but Class '");
                        msg.append(factoryClass);
                        msg.append("' cannot be converted to '");
                        msg.append((class$org$apache$commons$logging$LogFactory == null ? (class$org$apache$commons$logging$LogFactory = LogFactory.class$(FACTORY_PROPERTY)) : class$org$apache$commons$logging$LogFactory).getName());
                        msg.append("'. ");
                        if (implementsLogFactory) {
                            msg.append("The conflict is caused by the presence of multiple LogFactory classes ");
                            msg.append("in incompatible classloaders. ");
                            msg.append("Background can be found in http://commons.apache.org/logging/tech.html. ");
                            msg.append("If you have not explicitly specified a custom LogFactory then it is likely ");
                            msg.append("that the container has set one without your knowledge. ");
                            msg.append("In this case, consider using the commons-logging-adapters.jar file or ");
                            msg.append("specifying the standard LogFactory from the command line. ");
                        } else {
                            msg.append("Please check the custom implementation. ");
                        }
                        msg.append("Help can be found @http://commons.apache.org/logging/troubleshooting.html.");
                        if (!LogFactory.isDiagnosticsEnabled()) throw new ClassCastException(msg.toString());
                        LogFactory.logDiagnostic(msg.toString());
                        throw new ClassCastException(msg.toString());
                    }
                }
            }
            if (LogFactory.isDiagnosticsEnabled()) {
                LogFactory.logDiagnostic(new StringBuffer().append("Unable to load factory class via classloader ").append(LogFactory.objectId(classLoader)).append(" - trying the classloader associated with this LogFactory.").toString());
            }
            logFactoryClass = Class.forName(factoryClass);
            return (LogFactory)logFactoryClass.newInstance();
        }
        catch (Exception e) {
            if (LogFactory.isDiagnosticsEnabled()) {
                LogFactory.logDiagnostic("Unable to create LogFactory instance.");
            }
            if (logFactoryClass == null) return new LogConfigurationException((Throwable)e);
            if ((class$org$apache$commons$logging$LogFactory == null ? (class$org$apache$commons$logging$LogFactory = LogFactory.class$(FACTORY_PROPERTY)) : class$org$apache$commons$logging$LogFactory).isAssignableFrom(logFactoryClass)) return new LogConfigurationException((Throwable)e);
            return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", (Throwable)e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void release(ClassLoader classLoader) {
        Hashtable factories;
        if (LogFactory.isDiagnosticsEnabled()) {
            LogFactory.logDiagnostic(new StringBuffer().append("Releasing factory for classloader ").append(LogFactory.objectId(classLoader)).toString());
        }
        Hashtable hashtable = factories = LogFactory.factories;
        synchronized (hashtable) {
            if (classLoader == null) {
                if (nullClassLoaderFactory == null) return;
                nullClassLoaderFactory.release();
                nullClassLoaderFactory = null;
            } else {
                LogFactory factory = (LogFactory)factories.get(classLoader);
                if (factory == null) return;
                factory.release();
                factories.remove(classLoader);
            }
        }
    }

    private static LogFactory getCachedFactory(ClassLoader contextClassLoader) {
        if (contextClassLoader != null) return (LogFactory)factories.get(contextClassLoader);
        return nullClassLoaderFactory;
    }

    private static final void logDiagnostic(String msg) {
        if (diagnosticsStream == null) return;
        diagnosticsStream.print(diagnosticPrefix);
        diagnosticsStream.println(msg);
        diagnosticsStream.flush();
    }

    public abstract Log getInstance(String var1) throws LogConfigurationException;

    protected static void handleThrowable(Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath)t;
        }
        if (!(t instanceof VirtualMachineError)) return;
        throw (VirtualMachineError)t;
    }

    public static LogFactory getFactory() throws LogConfigurationException {
        String factoryClass;
        ClassLoader baseClassLoader;
        Properties props;
        LogFactory factory;
        ClassLoader contextClassLoader;
        block35: {
            String useTCCLStr;
            contextClassLoader = LogFactory.getContextClassLoaderInternal();
            if (contextClassLoader == null && LogFactory.isDiagnosticsEnabled()) {
                LogFactory.logDiagnostic("Context classloader is null.");
            }
            if ((factory = LogFactory.getCachedFactory(contextClassLoader)) != null) {
                return factory;
            }
            if (LogFactory.isDiagnosticsEnabled()) {
                LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] LogFactory implementation requested for the first time for context classloader ").append(LogFactory.objectId(contextClassLoader)).toString());
                LogFactory.logHierarchy("[LOOKUP] ", contextClassLoader);
            }
            props = LogFactory.getConfigurationFile(contextClassLoader, FACTORY_PROPERTIES);
            baseClassLoader = contextClassLoader;
            if (props != null && (useTCCLStr = props.getProperty(TCCL_KEY)) != null && !Boolean.valueOf(useTCCLStr).booleanValue()) {
                baseClassLoader = thisClassLoader;
            }
            if (LogFactory.isDiagnosticsEnabled()) {
                LogFactory.logDiagnostic("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
            }
            try {
                factoryClass = LogFactory.getSystemProperty(FACTORY_PROPERTY, null);
                if (factoryClass != null) {
                    if (LogFactory.isDiagnosticsEnabled()) {
                        LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] Creating an instance of LogFactory class '").append(factoryClass).append("' as specified by system property ").append(FACTORY_PROPERTY).toString());
                    }
                    factory = LogFactory.newFactory(factoryClass, baseClassLoader, contextClassLoader);
                } else if (LogFactory.isDiagnosticsEnabled()) {
                    LogFactory.logDiagnostic("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
                }
            }
            catch (SecurityException e) {
                if (LogFactory.isDiagnosticsEnabled()) {
                    LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [").append(LogFactory.trim(e.getMessage())).append("]. Trying alternative implementations...").toString());
                }
            }
            catch (RuntimeException e) {
                if (!LogFactory.isDiagnosticsEnabled()) throw e;
                LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [").append(LogFactory.trim(e.getMessage())).append("] as specified by a system property.").toString());
                throw e;
            }
            if (factory == null) {
                if (LogFactory.isDiagnosticsEnabled()) {
                    LogFactory.logDiagnostic("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
                }
                try {
                    InputStream is = LogFactory.getResourceAsStream(contextClassLoader, SERVICE_ID);
                    if (is != null) {
                        BufferedReader rd;
                        try {
                            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        }
                        catch (UnsupportedEncodingException e) {
                            rd = new BufferedReader(new InputStreamReader(is));
                        }
                        String factoryClassName = rd.readLine();
                        rd.close();
                        if (factoryClassName != null && !"".equals(factoryClassName)) {
                            if (LogFactory.isDiagnosticsEnabled()) {
                                LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP]  Creating an instance of LogFactory class ").append(factoryClassName).append(" as specified by file '").append(SERVICE_ID).append("' which was present in the path of the context classloader.").toString());
                            }
                            factory = LogFactory.newFactory(factoryClassName, baseClassLoader, contextClassLoader);
                        }
                        break block35;
                    }
                    if (LogFactory.isDiagnosticsEnabled()) {
                        LogFactory.logDiagnostic("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
                    }
                }
                catch (Exception ex) {
                    if (!LogFactory.isDiagnosticsEnabled()) break block35;
                    LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [").append(LogFactory.trim(ex.getMessage())).append("]. Trying alternative implementations...").toString());
                }
            }
        }
        if (factory == null) {
            if (props != null) {
                if (LogFactory.isDiagnosticsEnabled()) {
                    LogFactory.logDiagnostic("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
                }
                if ((factoryClass = props.getProperty(FACTORY_PROPERTY)) != null) {
                    if (LogFactory.isDiagnosticsEnabled()) {
                        LogFactory.logDiagnostic(new StringBuffer().append("[LOOKUP] Properties file specifies LogFactory subclass '").append(factoryClass).append("'").toString());
                    }
                    factory = LogFactory.newFactory(factoryClass, baseClassLoader, contextClassLoader);
                } else if (LogFactory.isDiagnosticsEnabled()) {
                    LogFactory.logDiagnostic("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
                }
            } else if (LogFactory.isDiagnosticsEnabled()) {
                LogFactory.logDiagnostic("[LOOKUP] No properties file available to determine LogFactory subclass from..");
            }
        }
        if (factory == null) {
            if (LogFactory.isDiagnosticsEnabled()) {
                LogFactory.logDiagnostic("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader).");
            }
            factory = LogFactory.newFactory(FACTORY_DEFAULT, thisClassLoader, contextClassLoader);
        }
        if (factory == null) return factory;
        LogFactory.cacheFactory(contextClassLoader, factory);
        if (props == null) return factory;
        Enumeration<?> names = props.propertyNames();
        while (names.hasMoreElements()) {
            String name = (String)names.nextElement();
            String value = props.getProperty(name);
            factory.setAttribute(name, value);
        }
        return factory;
    }

    private static void cacheFactory(ClassLoader classLoader, LogFactory factory) {
        if (factory == null) return;
        if (classLoader == null) {
            nullClassLoaderFactory = factory;
        } else {
            factories.put(classLoader, factory);
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    protected LogFactory() {
    }

    public abstract Object getAttribute(String var1);

    private static Enumeration getResources(ClassLoader loader, String name) {
        4 action = new /* Unavailable Anonymous Inner Class!! */;
        Object result = AccessController.doPrivileged(action);
        return (Enumeration)result;
    }

    public static Log getLog(Class clazz) throws LogConfigurationException {
        return LogFactory.getFactory().getInstance(clazz);
    }

    static /* synthetic */ void access$000(String x0) {
        LogFactory.logDiagnostic(x0);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void releaseAll() {
        Hashtable factories;
        if (LogFactory.isDiagnosticsEnabled()) {
            LogFactory.logDiagnostic("Releasing factory for all classloaders.");
        }
        Hashtable hashtable = factories = LogFactory.factories;
        synchronized (hashtable) {
            Enumeration elements = factories.elements();
            while (elements.hasMoreElements()) {
                LogFactory element = (LogFactory)elements.nextElement();
                element.release();
            }
            factories.clear();
            if (nullClassLoaderFactory == null) return;
            nullClassLoaderFactory.release();
            nullClassLoaderFactory = null;
        }
    }

    public abstract Log getInstance(Class var1) throws LogConfigurationException;

    public abstract void removeAttribute(String var1);

    protected static LogFactory newFactory(String factoryClass, ClassLoader classLoader, ClassLoader contextClassLoader) throws LogConfigurationException {
        Object result = AccessController.doPrivileged(new /* Unavailable Anonymous Inner Class!! */);
        if (result instanceof LogConfigurationException) {
            LogConfigurationException ex = (LogConfigurationException)result;
            if (!LogFactory.isDiagnosticsEnabled()) throw ex;
            LogFactory.logDiagnostic(new StringBuffer().append("An error occurred while loading the factory class:").append(ex.getMessage()).toString());
            throw ex;
        }
        if (!LogFactory.isDiagnosticsEnabled()) return (LogFactory)result;
        LogFactory.logDiagnostic(new StringBuffer().append("Created object ").append(LogFactory.objectId(result)).append(" to manage classloader ").append(LogFactory.objectId(contextClassLoader)).toString());
        return (LogFactory)result;
    }

    protected static final void logRawDiagnostic(String msg) {
        if (diagnosticsStream == null) return;
        diagnosticsStream.println(msg);
        diagnosticsStream.flush();
    }

    public static Log getLog(String name) throws LogConfigurationException {
        return LogFactory.getFactory().getInstance(name);
    }

    private static Properties getProperties(URL url) {
        5 action = new /* Unavailable Anonymous Inner Class!! */;
        return (Properties)AccessController.doPrivileged(action);
    }

    static {
        String classLoaderName;
        diagnosticsStream = null;
        factories = null;
        nullClassLoaderFactory = null;
        thisClassLoader = LogFactory.getClassLoader(class$org$apache$commons$logging$LogFactory == null ? (class$org$apache$commons$logging$LogFactory = LogFactory.class$(FACTORY_PROPERTY)) : class$org$apache$commons$logging$LogFactory);
        try {
            ClassLoader classLoader = thisClassLoader;
            classLoaderName = thisClassLoader == null ? "BOOTLOADER" : LogFactory.objectId(classLoader);
        }
        catch (SecurityException e) {
            classLoaderName = "UNKNOWN";
        }
        diagnosticPrefix = new StringBuffer().append("[LogFactory from ").append(classLoaderName).append("] ").toString();
        diagnosticsStream = LogFactory.initDiagnostics();
        LogFactory.logClassLoaderEnvironment(class$org$apache$commons$logging$LogFactory == null ? (class$org$apache$commons$logging$LogFactory = LogFactory.class$(FACTORY_PROPERTY)) : class$org$apache$commons$logging$LogFactory);
        factories = LogFactory.createFactoryStore();
        if (!LogFactory.isDiagnosticsEnabled()) return;
        LogFactory.logDiagnostic("BOOTSTRAP COMPLETED");
    }

    private static void logClassLoaderEnvironment(Class clazz) {
        ClassLoader classLoader;
        if (!LogFactory.isDiagnosticsEnabled()) {
            return;
        }
        try {
            LogFactory.logDiagnostic(new StringBuffer().append("[ENV] Extension directories (java.ext.dir): ").append(System.getProperty("java.ext.dir")).toString());
            LogFactory.logDiagnostic(new StringBuffer().append("[ENV] Application classpath (java.class.path): ").append(System.getProperty("java.class.path")).toString());
        }
        catch (SecurityException ex) {
            LogFactory.logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
        }
        String className = clazz.getName();
        try {
            classLoader = LogFactory.getClassLoader(clazz);
        }
        catch (SecurityException ex) {
            LogFactory.logDiagnostic(new StringBuffer().append("[ENV] Security forbids determining the classloader for ").append(className).toString());
            return;
        }
        LogFactory.logDiagnostic(new StringBuffer().append("[ENV] Class ").append(className).append(" was loaded via classloader ").append(LogFactory.objectId(classLoader)).toString());
        LogFactory.logHierarchy(new StringBuffer().append("[ENV] Ancestry of classloader which loaded ").append(className).append(" is ").toString(), classLoader);
    }

    protected static ClassLoader getClassLoader(Class clazz) {
        try {
            return clazz.getClassLoader();
        }
        catch (SecurityException ex) {
            if (!LogFactory.isDiagnosticsEnabled()) throw ex;
            LogFactory.logDiagnostic(new StringBuffer().append("Unable to get classloader for class '").append(clazz).append("' due to security restrictions - ").append(ex.getMessage()).toString());
            throw ex;
        }
    }
}
