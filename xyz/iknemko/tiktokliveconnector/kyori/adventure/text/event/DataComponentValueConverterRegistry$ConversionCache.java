/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry$Conversion
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry$RegisteredConversion
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry;

/*
 * Exception performing whole class analysis ignored.
 */
static final class DataComponentValueConverterRegistry.ConversionCache {
    private static final Map<Class<?>, Set<DataComponentValueConverterRegistry.RegisteredConversion>> CONVERSIONS;
    private static final ConcurrentMap<Class<?>, ConcurrentMap<Class<?>, DataComponentValueConverterRegistry.RegisteredConversion>> CACHE;

    static DataComponentValueConverterRegistry.RegisteredConversion compute(Class<?> src, Class<?> dst) {
        Class sourcePtr;
        ArrayDeque sourceTypes = new ArrayDeque();
        sourceTypes.add(src);
        while ((sourcePtr = (Class)sourceTypes.poll()) != null) {
            Set<DataComponentValueConverterRegistry.RegisteredConversion> conversions = CONVERSIONS.get(sourcePtr);
            if (conversions != null) {
                DataComponentValueConverterRegistry.RegisteredConversion nearest = null;
                for (DataComponentValueConverterRegistry.RegisteredConversion potential : conversions) {
                    Class potentialDst = potential.conversion.destination();
                    if (dst.equals(potentialDst)) {
                        return potential;
                    }
                    if (!dst.isAssignableFrom(potentialDst) || nearest != null && !potentialDst.isAssignableFrom(nearest.conversion.destination())) continue;
                    nearest = potential;
                }
                if (nearest != null) {
                    return nearest;
                }
            }
            DataComponentValueConverterRegistry.ConversionCache.addSupertypes(sourcePtr, sourceTypes);
        }
        return DataComponentValueConverterRegistry.RegisteredConversion.NONE;
    }

    private static Map<Class<?>, Set<DataComponentValueConverterRegistry.RegisteredConversion>> collectConversions() {
        ConcurrentHashMap<Class, Set> collected = new ConcurrentHashMap<Class, Set>();
        for (DataComponentValueConverterRegistry.Provider provider : DataComponentValueConverterRegistry.access$000()) {
            @NotNull Key id = Objects.requireNonNull(provider.id(), () -> "ID of provider " + provider + " is null");
            for (DataComponentValueConverterRegistry.Conversion conv : provider.conversions()) {
                collected.computeIfAbsent(conv.source(), $ -> ConcurrentHashMap.newKeySet()).add(new DataComponentValueConverterRegistry.RegisteredConversion(id, conv));
            }
        }
        Iterator<Object> iterator = collected.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            entry.setValue(Collections.unmodifiableSet((Set)entry.getValue()));
        }
        return new ConcurrentHashMap(collected);
    }

    static {
        CACHE = new ConcurrentHashMap();
        CONVERSIONS = DataComponentValueConverterRegistry.ConversionCache.collectConversions();
    }

    @Nullable
    static DataComponentValueConverterRegistry.RegisteredConversion converter(Class<? extends DataComponentValue> src, Class<? extends DataComponentValue> dst) {
        DataComponentValueConverterRegistry.RegisteredConversion result = CACHE.computeIfAbsent(src, $ -> new ConcurrentHashMap()).computeIfAbsent(dst, $$ -> DataComponentValueConverterRegistry.ConversionCache.compute(src, dst));
        if (result != DataComponentValueConverterRegistry.RegisteredConversion.NONE) return result;
        return null;
    }

    private static void addSupertypes(Class<?> clazz, Deque<Class<?>> queue) {
        if (clazz.getSuperclass() != null) {
            queue.add(clazz.getSuperclass());
        }
        queue.addAll(Arrays.asList(clazz.getInterfaces()));
    }

    DataComponentValueConverterRegistry.ConversionCache() {
    }
}
