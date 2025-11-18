/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema$Mutable
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$VersionedBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueType
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.Objects;
import java.util.Set;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionImpl;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueType;

final class OptionSchemaImpl.MutableImpl
implements OptionSchema.Mutable {
    public Option<String> stringOption(String id, @7\u015aCz String defaultValue) {
        return this.register(id, ValueType.stringType(), defaultValue);
    }

    public <E extends Enum<E>> Option<E> enumOption(String id, Class<E> enumClazz, @7\u015aCz E defaultValue) {
        return this.register(id, ValueType.enumType(enumClazz), defaultValue);
    }

    public Option<Boolean> booleanOption(String id, boolean defaultValue) {
        return this.register(id, ValueType.booleanType(), defaultValue);
    }

    public String toString() {
        return "MutableImpl{schema=" + OptionSchemaImpl.this + "}";
    }

    public OptionState emptyState() {
        return OptionSchemaImpl.this.emptyState();
    }

    public boolean has(Option<?> option) {
        return OptionSchemaImpl.this.has(option);
    }

    public OptionState.VersionedBuilder versionedStateBuilder() {
        return OptionSchemaImpl.this.versionedStateBuilder();
    }

    public Option<Integer> intOption(String id, int defaultValue) {
        return this.register(id, ValueType.integerType(), defaultValue);
    }

    public Set<Option<?>> knownOptions() {
        return OptionSchemaImpl.this.knownOptions();
    }

    public Option<Double> doubleOption(String id, double defaultValue) {
        return this.register(id, ValueType.doubleType(), defaultValue);
    }

    public OptionSchema frozenView() {
        return OptionSchemaImpl.this;
    }

    public OptionState.Builder stateBuilder() {
        return OptionSchemaImpl.this.stateBuilder();
    }

    OptionSchemaImpl.MutableImpl() {
    }

    <T> Option<T> register(String id, ValueType<T> type, @7\u015aCz T defaultValue) {
        OptionImpl ret = new OptionImpl(Objects.requireNonNull(id, "id"), Objects.requireNonNull(type, "type"), defaultValue);
        if (OptionSchemaImpl.this.options.putIfAbsent(id, ret) == null) return ret;
        throw new IllegalStateException("Key " + id + " has already been used. Option keys must be unique within a schema.");
    }
}
