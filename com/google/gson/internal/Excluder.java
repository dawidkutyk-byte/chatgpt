/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  9a4f
 *  Ek5b
 *  mCRa
 */
package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Excluder
implements TypeAdapterFactory,
Cloneable {
    public static final Excluder DEFAULT = new Excluder();
    private double version = -1.0;
    private List<ExclusionStrategy> deserializationStrategies;
    private static final double IGNORE_VERSIONS = -1.0;
    private int modifiers = 136;
    private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
    private boolean requireExpose;
    private boolean serializeInnerClasses = true;

    private boolean isValidVersion(Ek5b since, mCRa until) {
        return this.isValidSince(since) && this.isValidUntil(until);
    }

    public Excluder disableInnerClassSerialization() {
        Object result = this.clone();
        ((Excluder)result).serializeInnerClasses = false;
        return result;
    }

    private boolean isAnonymousOrLocal(Class<?> clazz) {
        return !Enum.class.isAssignableFrom(clazz) && (clazz.isAnonymousClass() || clazz.isLocalClass());
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Object result = this.clone();
        ((Excluder)result).requireExpose = true;
        return result;
    }

    public Excluder withModifiers(int ... modifiers) {
        Object result = this.clone();
        ((Excluder)result).modifiers = 0;
        int[] nArray = modifiers;
        int n = nArray.length;
        int n2 = 0;
        while (n2 < n) {
            int modifier = nArray[n2];
            ((Excluder)result).modifiers |= modifier;
            ++n2;
        }
        return result;
    }

    public Excluder() {
        this.deserializationStrategies = Collections.emptyList();
    }

    private boolean isInnerClass(Class<?> clazz) {
        return clazz.isMemberClass() && !this.isStatic(clazz);
    }

    private boolean isStatic(Class<?> clazz) {
        return (clazz.getModifiers() & 8) != 0;
    }

    public boolean excludeField(Field field, boolean serialize) {
        ExclusionStrategy exclusionStrategy;
        if ((this.modifiers & field.getModifiers()) != 0) {
            return true;
        }
        if (this.version != -1.0 && !this.isValidVersion(field.getAnnotation(Ek5b.class), field.getAnnotation(mCRa.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.requireExpose) {
            9a4f annotation = field.getAnnotation(9a4f.class);
            if (annotation == null) return true;
            if (serialize ? !annotation.serialize() : !annotation.deserialize()) {
                return true;
            }
        }
        if (!this.serializeInnerClasses && this.isInnerClass(field.getType())) {
            return true;
        }
        if (this.isAnonymousOrLocal(field.getType())) {
            return true;
        }
        List<ExclusionStrategy> list = serialize ? this.serializationStrategies : this.deserializationStrategies;
        if (list.isEmpty()) return false;
        FieldAttributes fieldAttributes = new FieldAttributes(field);
        Iterator<ExclusionStrategy> iterator = list.iterator();
        do {
            if (!iterator.hasNext()) return false;
        } while (!(exclusionStrategy = iterator.next()).shouldSkipField(fieldAttributes));
        return true;
    }

    public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy, boolean serialization, boolean deserialization) {
        Object result = this.clone();
        if (serialization) {
            ((Excluder)result).serializationStrategies = new ArrayList<ExclusionStrategy>(this.serializationStrategies);
            ((Excluder)result).serializationStrategies.add(exclusionStrategy);
        }
        if (!deserialization) return result;
        ((Excluder)result).deserializationStrategies = new ArrayList<ExclusionStrategy>(this.deserializationStrategies);
        ((Excluder)result).deserializationStrategies.add(exclusionStrategy);
        return result;
    }

    private boolean isValidUntil(mCRa annotation) {
        if (annotation == null) return true;
        double annotationVersion = annotation.value();
        if (!(annotationVersion <= this.version)) return true;
        return false;
    }

    public Excluder withVersion(double ignoreVersionsAfter) {
        Object result = this.clone();
        ((Excluder)result).version = ignoreVersionsAfter;
        return result;
    }

    private boolean isValidSince(Ek5b annotation) {
        if (annotation == null) return true;
        double annotationVersion = annotation.value();
        if (!(annotationVersion > this.version)) return true;
        return false;
    }

    protected Excluder clone() {
        try {
            return (Excluder)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError((Object)e);
        }
    }

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
        Class<T> rawType = type.getRawType();
        final boolean skipSerialize = this.excludeClass(rawType, true);
        final boolean skipDeserialize = this.excludeClass(rawType, false);
        if (skipSerialize) return new TypeAdapter<T>(){
            private volatile TypeAdapter<T> delegate;

            @Override
            public T read(JsonReader in) throws IOException {
                if (!skipDeserialize) return this.delegate().read(in);
                in.skipValue();
                return null;
            }

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                if (skipSerialize) {
                    out.nullValue();
                    return;
                }
                this.delegate().write(out, value);
            }

            private TypeAdapter<T> delegate() {
                TypeAdapter d = this.delegate;
                return d != null ? d : (this.delegate = gson.getDelegateAdapter(Excluder.this, type));
            }
        };
        if (skipDeserialize) return new /* invalid duplicate definition of identical inner class */;
        return null;
    }

    public boolean excludeClass(Class<?> clazz, boolean serialize) {
        ExclusionStrategy exclusionStrategy;
        if (this.version != -1.0 && !this.isValidVersion(clazz.getAnnotation(Ek5b.class), clazz.getAnnotation(mCRa.class))) {
            return true;
        }
        if (!this.serializeInnerClasses && this.isInnerClass(clazz)) {
            return true;
        }
        if (this.isAnonymousOrLocal(clazz)) {
            return true;
        }
        List<ExclusionStrategy> list = serialize ? this.serializationStrategies : this.deserializationStrategies;
        Iterator<ExclusionStrategy> iterator = list.iterator();
        do {
            if (!iterator.hasNext()) return false;
        } while (!(exclusionStrategy = iterator.next()).shouldSkipClass(clazz));
        return true;
    }
}
