/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl$EnumType
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl$Types
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import java.util.Objects;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl;

public interface ValueType<T> {
    public static <E extends Enum<E>> ValueType<E> enumType(Class<E> enumClazz) {
        return new ValueTypeImpl.EnumType(Objects.requireNonNull(enumClazz, "enumClazz"));
    }

    public static ValueType<Double> doubleType() {
        return ValueTypeImpl.Types.DOUBLE;
    }

    public T parse(String var1) throws IllegalArgumentException;

    public static ValueType<String> stringType() {
        return ValueTypeImpl.Types.STRING;
    }

    public static ValueType<Integer> integerType() {
        return ValueTypeImpl.Types.INT;
    }

    public static ValueType<Boolean> booleanType() {
        return ValueTypeImpl.Types.BOOLEAN;
    }

    public Class<T> type();
}
