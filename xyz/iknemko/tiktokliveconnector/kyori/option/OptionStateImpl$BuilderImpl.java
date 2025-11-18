/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl$VersionedImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource;

/*
 * Exception performing whole class analysis ignored.
 */
static final class OptionStateImpl.BuilderImpl
implements OptionState.Builder {
    private final IdentityHashMap<Option<?>, Object> values = new IdentityHashMap();
    private final OptionSchema schema;

    public OptionState build() {
        if (!this.values.isEmpty()) return new OptionStateImpl(this.schema, this.values);
        return this.schema.emptyState();
    }

    public OptionState.Builder values(ValueSource source) {
        Iterator iterator = this.schema.knownOptions().iterator();
        while (iterator.hasNext()) {
            Option opt = (Option)iterator.next();
            Object value = source.value(opt);
            if (value == null) continue;
            this.values.put(opt, value);
        }
        return this;
    }

    public <V> OptionState.Builder value(Option<V> option, @7\u015aCz V value) {
        if (!this.schema.has(Objects.requireNonNull(option, "option"))) {
            throw new IllegalStateException("Option '" + option.id() + "' was not present in active schema");
        }
        if (value == null) {
            this.values.remove(option);
        } else {
            this.values.put(option, value);
        }
        return this;
    }

    OptionStateImpl.BuilderImpl(OptionSchema schema) {
        this.schema = schema;
    }

    public OptionState.Builder values(OptionState existing) {
        if (existing instanceof OptionStateImpl) {
            this.putAll(OptionStateImpl.access$000((OptionStateImpl)((OptionStateImpl)existing)));
        } else {
            if (!(existing instanceof OptionStateImpl.VersionedImpl)) throw new IllegalArgumentException("existing set " + existing + " is of an unknown implementation type");
            this.putAll(OptionStateImpl.access$000((OptionStateImpl)((OptionStateImpl)OptionStateImpl.VersionedImpl.access$100((OptionStateImpl.VersionedImpl)((OptionStateImpl.VersionedImpl)existing)))));
        }
        return this;
    }

    private void putAll(Map<Option<?>, Object> values) {
        Iterator<Map.Entry<Option<?>, Object>> iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Option<?>, Object> entry = iterator.next();
            if (!this.schema.has(entry.getKey())) {
                throw new IllegalStateException("Option '" + entry.getKey().id() + "' was not present in active schema");
            }
            this.values.put(entry.getKey(), entry.getValue());
        }
    }
}
