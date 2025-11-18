/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl;

class ValueTypeImpl.Types.4
extends ValueTypeImpl<Double> {
    public Double parse(String plainValue) throws IllegalArgumentException {
        try {
            return Double.parseDouble(plainValue);
        }
        catch (NumberFormatException ex) {
            throw ValueTypeImpl.Types.4.doNotKnowHowToTurn((String)plainValue, Double.class, (String)ex.getMessage());
        }
    }

    ValueTypeImpl.Types.4(Class type) {
        super(type);
    }
}
