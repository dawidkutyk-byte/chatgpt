/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueType
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueType;

abstract class ValueTypeImpl<T>
implements ValueType<T> {
    private final Class<T> type;

    public Class<T> type() {
        return this.type;
    }

    ValueTypeImpl(Class<T> type) {
        this.type = type;
    }

    static IllegalArgumentException doNotKnowHowToTurn(String input, Class<?> expected, @7\u015aCz String message) {
        throw new IllegalArgumentException("Do not know how to turn value '" + input + "' into a " + expected.getName() + (message == null ? "" : ": " + message));
    }
}
