/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMapImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMap;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.InheritanceAwareMapImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static final class InheritanceAwareMapImpl.BuilderImpl<C, V>
implements InheritanceAwareMap.Builder<C, V> {
    private boolean strict;
    private final Map<Class<? extends C>, V> values = new LinkedHashMap<Class<? extends C>, V>();

    @NotNull
    public InheritanceAwareMap.Builder<C, V> put(@NotNull Class<? extends C> clazz, @NotNull V value) {
        if (this.strict) {
            InheritanceAwareMapImpl.access$000(clazz, this.values);
        }
        this.values.put(Objects.requireNonNull(clazz, "clazz"), Objects.requireNonNull(value, "value"));
        return this;
    }

    @NotNull
    public InheritanceAwareMap.Builder<C, V> strict(boolean strict) {
        if (strict && !this.strict) {
            for (Class<? extends C> clazz : this.values.keySet()) {
                InheritanceAwareMapImpl.access$000(clazz, this.values);
            }
        }
        this.strict = strict;
        return this;
    }

    @NotNull
    public InheritanceAwareMap<C, V> build() {
        return new InheritanceAwareMapImpl(this.strict, Collections.unmodifiableMap(new LinkedHashMap<Class<? extends C>, V>(this.values)));
    }

    @NotNull
    public InheritanceAwareMap.Builder<C, V> putAll(@NotNull InheritanceAwareMap<? extends C, ? extends V> map) {
        InheritanceAwareMapImpl impl = (InheritanceAwareMapImpl)map;
        if (!(!this.strict || this.values.isEmpty() && InheritanceAwareMapImpl.access$100((InheritanceAwareMapImpl)impl))) {
            Iterator iterator = InheritanceAwareMapImpl.access$200((InheritanceAwareMapImpl)impl).entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = iterator.next();
                InheritanceAwareMapImpl.access$000((Class)((Class)entry.getKey()), this.values);
                this.values.put((Class)entry.getKey(), entry.getValue());
            }
            return this;
        }
        this.values.putAll(InheritanceAwareMapImpl.access$200((InheritanceAwareMapImpl)impl));
        return this;
    }

    @NotNull
    public InheritanceAwareMap.Builder<C, V> remove(@NotNull Class<? extends C> clazz) {
        this.values.remove(Objects.requireNonNull(clazz, "clazz"));
        return this;
    }

    InheritanceAwareMapImpl.BuilderImpl() {
    }
}
