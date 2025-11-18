/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray
extends JsonElement
implements Iterable<JsonElement> {
    private final List<JsonElement> elements = new ArrayList<JsonElement>();

    @Override
    public short getAsShort() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsShort();
    }

    @Override
    public byte getAsByte() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsByte();
    }

    @Override
    public float getAsFloat() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsFloat();
    }

    @Override
    public String getAsString() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsString();
    }

    public void add(JsonElement element) {
        if (element == null) {
            element = JsonNull.INSTANCE;
        }
        this.elements.add(element);
    }

    @Override
    public char getAsCharacter() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsCharacter();
    }

    public void addAll(JsonArray array) {
        this.elements.addAll(array.elements);
    }

    public int hashCode() {
        return this.elements.hashCode();
    }

    @Override
    public Iterator<JsonElement> iterator() {
        return this.elements.iterator();
    }

    @Override
    public boolean getAsBoolean() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsBoolean();
    }

    @Override
    public long getAsLong() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsLong();
    }

    public boolean remove(JsonElement element) {
        return this.elements.remove(element);
    }

    @Override
    JsonArray deepCopy() {
        JsonArray result = new JsonArray();
        Iterator<JsonElement> iterator = this.elements.iterator();
        while (iterator.hasNext()) {
            JsonElement element = iterator.next();
            result.add(element.deepCopy());
        }
        return result;
    }

    @Override
    public Number getAsNumber() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsNumber();
    }

    @Override
    public double getAsDouble() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsDouble();
    }

    @Override
    public BigInteger getAsBigInteger() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsBigInteger();
    }

    public void add(String string) {
        this.elements.add(string == null ? JsonNull.INSTANCE : new JsonPrimitive(string));
    }

    @Override
    public int getAsInt() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsInt();
    }

    public boolean contains(JsonElement element) {
        return this.elements.contains(element);
    }

    public JsonElement set(int index, JsonElement element) {
        return this.elements.set(index, element);
    }

    public void add(Number number) {
        this.elements.add(number == null ? JsonNull.INSTANCE : new JsonPrimitive(number));
    }

    @Override
    public BigDecimal getAsBigDecimal() {
        if (this.elements.size() != 1) throw new IllegalStateException();
        return this.elements.get(0).getAsBigDecimal();
    }

    public int size() {
        return this.elements.size();
    }

    public void add(Boolean bool) {
        this.elements.add(bool == null ? JsonNull.INSTANCE : new JsonPrimitive(bool));
    }

    public JsonElement remove(int index) {
        return this.elements.remove(index);
    }

    public boolean equals(Object o) {
        return o == this || o instanceof JsonArray && ((JsonArray)o).elements.equals(this.elements);
    }

    public void add(Character character) {
        this.elements.add(character == null ? JsonNull.INSTANCE : new JsonPrimitive(character));
    }

    public JsonElement get(int i) {
        return this.elements.get(i);
    }
}
