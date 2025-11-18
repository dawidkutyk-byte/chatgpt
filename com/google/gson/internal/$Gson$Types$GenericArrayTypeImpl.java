/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.$Gson$Types;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

private static final class $Gson$Types.GenericArrayTypeImpl
implements GenericArrayType,
Serializable {
    private static final long serialVersionUID = 0L;
    private final Type componentType;

    public boolean equals(Object o) {
        return o instanceof GenericArrayType && $Gson$Types.equals(this, (GenericArrayType)o);
    }

    public String toString() {
        return $Gson$Types.typeToString(this.componentType) + "[]";
    }

    public $Gson$Types.GenericArrayTypeImpl(Type componentType) {
        this.componentType = $Gson$Types.canonicalize(componentType);
    }

    @Override
    public Type getGenericComponentType() {
        return this.componentType;
    }

    public int hashCode() {
        return this.componentType.hashCode();
    }
}
