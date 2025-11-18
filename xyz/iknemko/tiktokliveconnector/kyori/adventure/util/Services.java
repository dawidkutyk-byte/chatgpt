/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services$Fallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services0
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services0;

public final class Services {
    private static final boolean SERVICE_LOAD_FAILURES_ARE_FATAL = Boolean.TRUE.equals(AdventureProperties.SERVICE_LOAD_FAILURES_ARE_FATAL.value());

    @NotNull
    public static <P> Optional<P> serviceWithFallback(@NotNull Class<P> type) {
        ServiceLoader loader = Services0.loader(type);
        Iterator it = loader.iterator();
        Object firstFallback = null;
        while (it.hasNext()) {
            Object instance;
            try {
                instance = it.next();
            }
            catch (Throwable t) {
                if (!SERVICE_LOAD_FAILURES_ARE_FATAL) continue;
                throw new IllegalStateException("Encountered an exception loading service " + type, t);
            }
            if (!(instance instanceof Fallback)) return Optional.of(instance);
            if (firstFallback != null) continue;
            firstFallback = instance;
        }
        return Optional.ofNullable(firstFallback);
    }

    public static <P> Set<P> services(Class<? extends P> clazz) {
        ServiceLoader loader = Services0.loader(clazz);
        HashSet providers = new HashSet();
        Iterator it = loader.iterator();
        while (it.hasNext()) {
            Object instance;
            try {
                instance = it.next();
            }
            catch (ServiceConfigurationError ex) {
                if (!SERVICE_LOAD_FAILURES_ARE_FATAL) continue;
                throw new IllegalStateException("Encountered an exception loading a provider for " + clazz + ": ", ex);
            }
            providers.add(instance);
        }
        return Collections.unmodifiableSet(providers);
    }

    private Services() {
    }

    @NotNull
    public static <P> Optional<P> service(@NotNull Class<P> type) {
        Object instance;
        ServiceLoader loader = Services0.loader(type);
        Iterator it = loader.iterator();
        while (true) {
            if (!it.hasNext()) return Optional.empty();
            try {
                instance = it.next();
            }
            catch (Throwable t) {
                if (!SERVICE_LOAD_FAILURES_ARE_FATAL) continue;
                throw new IllegalStateException("Encountered an exception loading service " + type, t);
            }
            break;
        }
        if (!it.hasNext()) return Optional.of(instance);
        throw new IllegalStateException("Expected to find one service " + type + ", found multiple");
    }
}
