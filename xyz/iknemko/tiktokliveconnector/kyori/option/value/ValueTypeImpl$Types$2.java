/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl;

class ValueTypeImpl.Types.2
extends ValueTypeImpl<Boolean> {
    ValueTypeImpl.Types.2(Class type) {
        super(type);
    }

    public Boolean parse(String plainValue) throws IllegalArgumentException {
        if (plainValue.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (!plainValue.equalsIgnoreCase("false")) throw ValueTypeImpl.Types.2.doNotKnowHowToTurn((String)plainValue, Boolean.class, null);
        return Boolean.FALSE;
    }
}
