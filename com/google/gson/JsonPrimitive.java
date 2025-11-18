/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.JsonElement;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class JsonPrimitive
extends JsonElement {
    private Object value;
    private static final Class<?>[] PRIMITIVE_TYPES = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};

    public JsonPrimitive(String string) {
        this.setValue(string);
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    @Override
    public BigDecimal getAsBigDecimal() {
        return this.value instanceof BigDecimal ? (BigDecimal)this.value : new BigDecimal(this.value.toString());
    }

    Boolean getAsBooleanWrapper() {
        return (Boolean)this.value;
    }

    public JsonPrimitive(Boolean bool) {
        this.setValue(bool);
    }

    public boolean equals(Object obj) {
        double b;
        if (this == obj) {
            return true;
        }
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        JsonPrimitive other = (JsonPrimitive)obj;
        if (this.value == null) {
            return other.value == null;
        }
        if (JsonPrimitive.isIntegral(this) && JsonPrimitive.isIntegral(other)) {
            return this.getAsNumber().longValue() == other.getAsNumber().longValue();
        }
        if (!(this.value instanceof Number)) return this.value.equals(other.value);
        if (!(other.value instanceof Number)) return this.value.equals(other.value);
        double a = this.getAsNumber().doubleValue();
        return a == (b = other.getAsNumber().doubleValue()) || Double.isNaN(a) && Double.isNaN(b);
    }

    public int hashCode() {
        if (this.value == null) {
            return 31;
        }
        if (JsonPrimitive.isIntegral(this)) {
            long value = this.getAsNumber().longValue();
            return (int)(value ^ value >>> 32);
        }
        if (!(this.value instanceof Number)) return this.value.hashCode();
        long value = Double.doubleToLongBits(this.getAsNumber().doubleValue());
        return (int)(value ^ value >>> 32);
    }

    @Override
    public int getAsInt() {
        return this.isNumber() ? this.getAsNumber().intValue() : Integer.parseInt(this.getAsString());
    }

    @Override
    public char getAsCharacter() {
        return this.getAsString().charAt(0);
    }

    @Override
    JsonPrimitive deepCopy() {
        return this;
    }

    void setValue(Object primitive) {
        if (primitive instanceof Character) {
            char c = ((Character)primitive).charValue();
            this.value = String.valueOf(c);
        } else {
            $Gson$Preconditions.checkArgument(primitive instanceof Number || JsonPrimitive.isPrimitiveOrString(primitive));
            this.value = primitive;
        }
    }

    @Override
    public float getAsFloat() {
        return this.isNumber() ? this.getAsNumber().floatValue() : Float.parseFloat(this.getAsString());
    }

    @Override
    public BigInteger getAsBigInteger() {
        return this.value instanceof BigInteger ? (BigInteger)this.value : new BigInteger(this.value.toString());
    }

    @Override
    public short getAsShort() {
        return this.isNumber() ? this.getAsNumber().shortValue() : Short.parseShort(this.getAsString());
    }

    private static boolean isPrimitiveOrString(Object target) {
        if (target instanceof String) {
            return true;
        }
        Class<?> classOfPrimitive = target.getClass();
        Class<?>[] classArray = PRIMITIVE_TYPES;
        int n = classArray.length;
        int n2 = 0;
        while (n2 < n) {
            Class<?> standardPrimitive = classArray[n2];
            if (standardPrimitive.isAssignableFrom(classOfPrimitive)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    JsonPrimitive(Object primitive) {
        this.setValue(primitive);
    }

    @Override
    public double getAsDouble() {
        return this.isNumber() ? this.getAsNumber().doubleValue() : Double.parseDouble(this.getAsString());
    }

    @Override
    public Number getAsNumber() {
        return this.value instanceof String ? new LazilyParsedNumber((String)this.value) : (Number)this.value;
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    @Override
    public byte getAsByte() {
        return this.isNumber() ? this.getAsNumber().byteValue() : Byte.parseByte(this.getAsString());
    }

    public JsonPrimitive(Character c) {
        this.setValue(c);
    }

    @Override
    public boolean getAsBoolean() {
        if (!this.isBoolean()) return Boolean.parseBoolean(this.getAsString());
        return this.getAsBooleanWrapper();
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    @Override
    public String getAsString() {
        if (this.isNumber()) {
            return this.getAsNumber().toString();
        }
        if (!this.isBoolean()) return (String)this.value;
        return this.getAsBooleanWrapper().toString();
    }

    private static boolean isIntegral(JsonPrimitive primitive) {
        if (!(primitive.value instanceof Number)) return false;
        Number number = (Number)primitive.value;
        return number instanceof BigInteger || number instanceof Long || number instanceof Integer || number instanceof Short || number instanceof Byte;
    }

    public JsonPrimitive(Number number) {
        this.setValue(number);
    }

    @Override
    public long getAsLong() {
        return this.isNumber() ? this.getAsNumber().longValue() : Long.parseLong(this.getAsString());
    }
}
