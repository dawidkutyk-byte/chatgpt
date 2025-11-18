/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl;

class ValueTypeImpl.Types.1
extends ValueTypeImpl<String> {
    ValueTypeImpl.Types.1(Class type) {
        super(type);
    }

    public String parse(String plainValue) throws IllegalArgumentException {
        return plainValue;
    }
}
