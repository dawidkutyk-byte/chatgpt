/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.IdentityHashMap;
import java.util.Objects;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

final class OptionStateImpl
implements OptionState {
    private final IdentityHashMap<Option<?>, Object> values;
    private final OptionSchema schema;

    public boolean has(Option<?> option) {
        return this.values.containsKey(Objects.requireNonNull(option, "flag"));
    }

    public int hashCode() {
        return Objects.hash(this.values);
    }

    public boolean equals(@7\u015aCz Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        OptionStateImpl that = (OptionStateImpl)other;
        return Objects.equals(this.values, that.values);
    }

    public OptionSchema schema() {
        return this.schema;
    }

    public String toString() {
        return this.getClass().getSimpleName() + "{values=" + this.values + '}';
    }

    static /* synthetic */ IdentityHashMap access$000(OptionStateImpl x0) {
        return x0.values;
    }

    OptionStateImpl(OptionSchema schema, IdentityHashMap<Option<?>, Object> values) {
        this.schema = schema;
        this.values = new IdentityHashMap(values);
    }

    public <V> @7\u015aCz V value(Option<V> option) {
        Object value = option.valueType().type().cast(this.values.get(Objects.requireNonNull(option, "flag")));
        return (V)(value == null ? option.defaultValue() : value);
    }
}
