/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap;

final class InheritanceAwareMapImpl<C, V>
implements InheritanceAwareMap<C, V> {
    private final boolean strict;
    static final InheritanceAwareMapImpl EMPTY;
    private final Map<Class<? extends C>, V> declaredValues;
    private final transient ConcurrentMap<Class<? extends C>, Object> cache = new ConcurrentHashMap<Class<? extends C>, Object>();
    private static final Object NONE;

    static {
        NONE = new Object();
        EMPTY = new InheritanceAwareMapImpl(false, Collections.emptyMap());
    }

    public boolean containsKey(@NotNull Class<? extends C> clazz) {
        return this.get(clazz) != null;
    }

    @Nullable
    public V get(@NotNull Class<? extends C> clazz) {
        Object ret = this.cache.computeIfAbsent(clazz, c -> {
            Map.Entry<Class<C>, V> entry;
            @Nullable V value = this.declaredValues.get(c);
            if (value != null) {
                return value;
            }
            Iterator<Map.Entry<Class<C>, V>> iterator = this.declaredValues.entrySet().iterator();
            do {
                if (!iterator.hasNext()) return NONE;
            } while (!(entry = iterator.next()).getKey().isAssignableFrom((Class<?>)c));
            return entry.getValue();
        });
        return (V)(ret == NONE ? null : ret);
    }

    @NotNull
    public InheritanceAwareMap<C, V> with(@NotNull Class<? extends C> clazz, @NotNull V value) {
        if (Objects.equals(this.declaredValues.get(clazz), value)) {
            return this;
        }
        if (this.strict) {
            InheritanceAwareMapImpl.validateNoneInHierarchy(clazz, this.declaredValues);
        }
        LinkedHashMap<Class<C>, V> newValues = new LinkedHashMap<Class<C>, V>(this.declaredValues);
        newValues.put(clazz, value);
        return new InheritanceAwareMapImpl<C, V>(this.strict, Collections.unmodifiableMap(newValues));
    }

    static /* synthetic */ void access$000(Class x0, Map x1) {
        InheritanceAwareMapImpl.validateNoneInHierarchy(x0, x1);
    }

    @NotNull
    public InheritanceAwareMap<C, V> without(@NotNull Class<? extends C> clazz) {
        if (!this.declaredValues.containsKey(clazz)) {
            return this;
        }
        LinkedHashMap<Class<C>, V> newValues = new LinkedHashMap<Class<C>, V>(this.declaredValues);
        newValues.remove(clazz);
        return new InheritanceAwareMapImpl<C, V>(this.strict, Collections.unmodifiableMap(newValues));
    }

    private static void testHierarchy(Class<?> existing, Class<?> beingRegistered) {
        if (existing.equals(beingRegistered)) return;
        if (existing.isAssignableFrom(beingRegistered)) throw new IllegalArgumentException("Conflict detected between already registered type " + existing + " and newly registered type " + beingRegistered + "! Types in a strict inheritance-aware map must not share a common hierarchy!");
        if (!beingRegistered.isAssignableFrom(existing)) return;
        throw new IllegalArgumentException("Conflict detected between already registered type " + existing + " and newly registered type " + beingRegistered + "! Types in a strict inheritance-aware map must not share a common hierarchy!");
    }

    InheritanceAwareMapImpl(boolean strict, Map<Class<? extends C>, V> declaredValues) {
        this.strict = strict;
        this.declaredValues = declaredValues;
    }

    static /* synthetic */ boolean access$100(InheritanceAwareMapImpl x0) {
        return x0.strict;
    }

    static /* synthetic */ Map access$200(InheritanceAwareMapImpl x0) {
        return x0.declaredValues;
    }

    private static void validateNoneInHierarchy(Class<?> beingRegistered, Map<? extends Class<?>, ?> entries) {
        Iterator<Class<?>> iterator = entries.keySet().iterator();
        while (iterator.hasNext()) {
            Class<?> clazz = iterator.next();
            InheritanceAwareMapImpl.testHierarchy(clazz, beingRegistered);
        }
    }
}
