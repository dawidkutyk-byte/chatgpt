/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

public final class Util {
    private static boolean SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED = false;
    private static ClassContextSecurityManager SECURITY_MANAGER;

    private static ClassContextSecurityManager safeCreateSecurityManager() {
        try {
            return new ClassContextSecurityManager();
        }
        catch (SecurityException sm) {
            return null;
        }
    }

    private static ClassContextSecurityManager getSecurityManager() {
        if (SECURITY_MANAGER != null) {
            return SECURITY_MANAGER;
        }
        if (SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED) {
            return null;
        }
        SECURITY_MANAGER = Util.safeCreateSecurityManager();
        SECURITY_MANAGER_CREATION_ALREADY_ATTEMPTED = true;
        return SECURITY_MANAGER;
    }

    public static final void report(String msg, Throwable t) {
        System.err.println(msg);
        System.err.println("Reported exception:");
        t.printStackTrace();
    }

    public static String safeGetSystemProperty(String key) {
        if (key == null) {
            throw new IllegalArgumentException("null input");
        }
        String result = null;
        try {
            result = System.getProperty(key);
        }
        catch (SecurityException securityException) {
            // empty catch block
        }
        return result;
    }

    private Util() {
    }

    public static final void report(String msg) {
        System.err.println("SLF4J: " + msg);
    }

    public static boolean safeGetBooleanSystemProperty(String key) {
        String value = Util.safeGetSystemProperty(key);
        if (value != null) return value.equalsIgnoreCase("true");
        return false;
    }

    public static Class<?> getCallingClass() {
        int i;
        ClassContextSecurityManager securityManager = Util.getSecurityManager();
        if (securityManager == null) {
            return null;
        }
        Class<?>[] trace = securityManager.getClassContext();
        String thisClassName = Util.class.getName();
        for (i = 0; i < trace.length && !thisClassName.equals(trace[i].getName()); ++i) {
        }
        if (i >= trace.length) throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
        if (i + 2 < trace.length) return trace[i + 2];
        throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
    }

    private static final class ClassContextSecurityManager
    extends SecurityManager {
        private ClassContextSecurityManager() {
        }

        @Override
        protected Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }
}
