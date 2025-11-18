/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl;

class ValueTypeImpl.Types.3
extends ValueTypeImpl<Integer> {
    public Integer parse(String plainValue) throws IllegalArgumentException {
        try {
            return Integer.decode(plainValue);
        }
        catch (NumberFormatException ex) {
            throw ValueTypeImpl.Types.3.doNotKnowHowToTurn((String)plainValue, Integer.class, (String)ex.getMessage());
        }
    }

    ValueTypeImpl.Types.3(Class type) {
        super(type);
    }
}
