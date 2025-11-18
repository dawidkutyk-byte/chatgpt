/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueType
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.Objects;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueType;

final class OptionImpl<V>
implements Option<V> {
    private final ValueType<V> type;
    private final @7\u015aCz V defaultValue;
    private final String id;

    public ValueType<V> valueType() {
        return this.type;
    }

    public boolean equals(@7\u015aCz Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        OptionImpl that = (OptionImpl)other;
        return Objects.equals(this.id, that.id) && Objects.equals(this.type, that.type);
    }

    OptionImpl(String id, ValueType<V> type, @7\u015aCz V defaultValue) {
        this.id = id;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public @7\u015aCz V defaultValue() {
        return this.defaultValue;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.type);
    }

    public String toString() {
        return this.getClass().getSimpleName() + "{id=" + this.id + ",type=" + this.type + ",defaultValue=" + this.defaultValue + '}';
    }

    public String id() {
        return this.id;
    }
}
