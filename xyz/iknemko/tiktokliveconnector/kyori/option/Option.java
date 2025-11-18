/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueType
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import org.jetbrains.annotations.ApiStatus;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueType;

@ApiStatus.NonExtendable
public interface Option<V> {
    public String id();

    @Deprecated
    default public Class<V> type() {
        return this.valueType().type();
    }

    @Deprecated
    public static Option<Boolean> booleanOption(String id, boolean defaultValue) {
        return OptionSchema.globalSchema().booleanOption(id, defaultValue);
    }

    public @7\u015aCz V defaultValue();

    public ValueType<V> valueType();

    @Deprecated
    public static <E extends Enum<E>> Option<E> enumOption(String id, Class<E> enumClazz, E defaultValue) {
        return OptionSchema.globalSchema().enumOption(id, enumClazz, defaultValue);
    }
}
