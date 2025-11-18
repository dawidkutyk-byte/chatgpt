/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.$Gson$Types;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

private static final class $Gson$Types.WildcardTypeImpl
implements Serializable,
WildcardType {
    private final Type upperBound;
    private final Type lowerBound;
    private static final long serialVersionUID = 0L;

    public boolean equals(Object other) {
        return other instanceof WildcardType && $Gson$Types.equals(this, (WildcardType)other);
    }

    public int hashCode() {
        return (this.lowerBound != null ? 31 + this.lowerBound.hashCode() : 1) ^ 31 + this.upperBound.hashCode();
    }

    public String toString() {
        if (this.lowerBound != null) {
            return "? super " + $Gson$Types.typeToString(this.lowerBound);
        }
        if (this.upperBound != Object.class) return "? extends " + $Gson$Types.typeToString(this.upperBound);
        return "?";
    }

    public $Gson$Types.WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
        $Gson$Preconditions.checkArgument(lowerBounds.length <= 1);
        $Gson$Preconditions.checkArgument(upperBounds.length == 1);
        if (lowerBounds.length == 1) {
            $Gson$Preconditions.checkNotNull(lowerBounds[0]);
            $Gson$Types.checkNotPrimitive(lowerBounds[0]);
            $Gson$Preconditions.checkArgument(upperBounds[0] == Object.class);
            this.lowerBound = $Gson$Types.canonicalize(lowerBounds[0]);
            this.upperBound = Object.class;
        } else {
            $Gson$Preconditions.checkNotNull(upperBounds[0]);
            $Gson$Types.checkNotPrimitive(upperBounds[0]);
            this.lowerBound = null;
            this.upperBound = $Gson$Types.canonicalize(upperBounds[0]);
        }
    }

    @Override
    public Type[] getUpperBounds() {
        return new Type[]{this.upperBound};
    }

    @Override
    public Type[] getLowerBounds() {
        Type[] typeArray;
        if (this.lowerBound != null) {
            Type[] typeArray2 = new Type[1];
            typeArray = typeArray2;
            typeArray2[0] = this.lowerBound;
        } else {
            typeArray = EMPTY_TYPE_ARRAY;
        }
        return typeArray;
    }
}
