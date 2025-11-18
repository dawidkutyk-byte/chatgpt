/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueTypeImpl;

static final class ValueTypeImpl.EnumType<E extends Enum<E>>
extends ValueTypeImpl<E> {
    private final Map<String, E> values = new HashMap<String, E>();

    public E parse(String plainValue) throws IllegalArgumentException {
        Enum result = (Enum)this.values.get(plainValue.toLowerCase(Locale.ROOT));
        if (result != null) return (E)result;
        throw ValueTypeImpl.EnumType.doNotKnowHowToTurn((String)plainValue, (Class)this.type(), null);
    }

    ValueTypeImpl.EnumType(Class<E> type) {
        super(type);
        Enum[] enumArray = (Enum[])type.getEnumConstants();
        int n = enumArray.length;
        int n2 = 0;
        while (n2 < n) {
            Enum entry = enumArray[n2];
            this.values.put(entry.name().toLowerCase(Locale.ROOT), entry);
            ++n2;
        }
    }
}
