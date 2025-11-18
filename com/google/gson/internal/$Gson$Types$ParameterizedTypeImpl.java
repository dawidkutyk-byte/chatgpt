/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.$Gson$Types;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

private static final class $Gson$Types.ParameterizedTypeImpl
implements Serializable,
ParameterizedType {
    private static final long serialVersionUID = 0L;
    private final Type rawType;
    private final Type[] typeArguments;
    private final Type ownerType;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(30 * (this.typeArguments.length + 1));
        stringBuilder.append($Gson$Types.typeToString(this.rawType));
        if (this.typeArguments.length == 0) {
            return stringBuilder.toString();
        }
        stringBuilder.append("<").append($Gson$Types.typeToString(this.typeArguments[0]));
        int i = 1;
        while (i < this.typeArguments.length) {
            stringBuilder.append(", ").append($Gson$Types.typeToString(this.typeArguments[i]));
            ++i;
        }
        return stringBuilder.append(">").toString();
    }

    @Override
    public Type getOwnerType() {
        return this.ownerType;
    }

    public boolean equals(Object other) {
        return other instanceof ParameterizedType && $Gson$Types.equals(this, (ParameterizedType)other);
    }

    public int hashCode() {
        return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ $Gson$Types.hashCodeOrZero((Object)this.ownerType);
    }

    public $Gson$Types.ParameterizedTypeImpl(Type ownerType, Type rawType, Type ... typeArguments) {
        if (rawType instanceof Class) {
            Class rawTypeAsClass = (Class)rawType;
            boolean isStaticOrTopLevelClass = Modifier.isStatic(rawTypeAsClass.getModifiers()) || rawTypeAsClass.getEnclosingClass() == null;
            $Gson$Preconditions.checkArgument(ownerType != null || isStaticOrTopLevelClass);
        }
        this.ownerType = ownerType == null ? null : $Gson$Types.canonicalize(ownerType);
        this.rawType = $Gson$Types.canonicalize(rawType);
        this.typeArguments = (Type[])typeArguments.clone();
        int t = 0;
        while (t < this.typeArguments.length) {
            $Gson$Preconditions.checkNotNull(this.typeArguments[t]);
            $Gson$Types.checkNotPrimitive(this.typeArguments[t]);
            this.typeArguments[t] = $Gson$Types.canonicalize(this.typeArguments[t]);
            ++t;
        }
    }

    @Override
    public Type getRawType() {
        return this.rawType;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return (Type[])this.typeArguments.clone();
    }
}
