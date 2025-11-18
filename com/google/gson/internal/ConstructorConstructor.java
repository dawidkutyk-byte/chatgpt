/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class ConstructorConstructor {
    private final Map<Type, InstanceCreator<?>> instanceCreators;

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> instanceCreators) {
        this.instanceCreators = instanceCreators;
    }

    private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> rawType) {
        try {
            final Constructor<T> constructor = rawType.getDeclaredConstructor(new Class[0]);
            if (constructor.isAccessible()) return new ObjectConstructor<T>(this, constructor){

                @Override
                public T construct() {
                    throw new JsonIOException(constructor);
                }
            };
            constructor.setAccessible(true);
            return new /* invalid duplicate definition of identical inner class */;
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }

    public String toString() {
        return this.instanceCreators.toString();
    }

    private <T> ObjectConstructor<T> newDefaultImplementationConstructor(Type type, Class<? super T> rawType) {
        if (Collection.class.isAssignableFrom(rawType)) {
            if (SortedSet.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>(this){
                    final /* synthetic */ String val$message;
                    {
                        this.val$message = string;
                    }

                    @Override
                    public T construct() {
                        throw new JsonIOException(this.val$message);
                    }
                };
            }
            if (EnumSet.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>(this, type){
                    final /* synthetic */ Type val$type;
                    {
                        this.val$type = type;
                    }

                    @Override
                    public T construct() {
                        if (!(this.val$type instanceof ParameterizedType)) throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
                        Type elementType = ((ParameterizedType)this.val$type).getActualTypeArguments()[0];
                        if (!(elementType instanceof Class)) throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
                        EnumSet set = EnumSet.noneOf((Class)elementType);
                        return set;
                    }
                };
            }
            if (Set.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>(this){

                    @Override
                    public T construct() {
                        if (!(this instanceof ParameterizedType)) throw new JsonIOException("Invalid EnumMap type: " + this.toString());
                        Type elementType = ((ParameterizedType)this).getActualTypeArguments()[0];
                        if (!(elementType instanceof Class)) throw new JsonIOException("Invalid EnumMap type: " + this.toString());
                        EnumMap map = new EnumMap((Class)elementType);
                        return map;
                    }
                };
            }
            if (!Queue.class.isAssignableFrom(rawType)) return new ObjectConstructor<T>(this){

                @Override
                public T construct() {
                    throw new JsonIOException(this);
                }
            };
            return new ObjectConstructor<T>(this){

                @Override
                public T construct() {
                    throw new JsonIOException(this);
                }
            };
        }
        if (!Map.class.isAssignableFrom(rawType)) return null;
        if (ConcurrentNavigableMap.class.isAssignableFrom(rawType)) {
            return new ObjectConstructor<T>(this){

                @Override
                public T construct() {
                    try {
                        Object newInstance = this.newInstance(new Object[0]);
                        return newInstance;
                    }
                    catch (InstantiationException e) {
                        throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this) + "' with no args", e);
                    }
                    catch (InvocationTargetException e) {
                        throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this) + "' with no args", e.getCause());
                    }
                    catch (IllegalAccessException e) {
                        throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
                    }
                }
            };
        }
        if (ConcurrentMap.class.isAssignableFrom(rawType)) {
            return new ObjectConstructor<T>(this){

                @Override
                public T construct() {
                    return new TreeSet();
                }
            };
        }
        if (SortedMap.class.isAssignableFrom(rawType)) {
            return new ObjectConstructor<T>(this){

                @Override
                public T construct() {
                    return new LinkedHashSet();
                }
            };
        }
        if (!(type instanceof ParameterizedType)) return new ObjectConstructor<T>(this){

            @Override
            public T construct() {
                return new ArrayList();
            }
        };
        if (String.class.isAssignableFrom(TypeToken.get(((ParameterizedType)type).getActualTypeArguments()[0]).getRawType())) return new /* invalid duplicate definition of identical inner class */;
        return new ObjectConstructor<T>(this){

            @Override
            public T construct() {
                return new ArrayDeque();
            }
        };
    }

    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        final Type type = typeToken.getType();
        Class<T> rawType = typeToken.getRawType();
        final InstanceCreator<?> typeCreator = this.instanceCreators.get(type);
        if (typeCreator != null) {
            return new ObjectConstructor<T>(){

                @Override
                public T construct() {
                    return typeCreator.createInstance(type);
                }
            };
        }
        final InstanceCreator<?> rawTypeCreator = this.instanceCreators.get(rawType);
        if (rawTypeCreator != null) {
            return new ObjectConstructor<T>(){

                @Override
                public T construct() {
                    return rawTypeCreator.createInstance(type);
                }
            };
        }
        ObjectConstructor<T> defaultConstructor = this.newDefaultConstructor(rawType);
        if (defaultConstructor != null) {
            return defaultConstructor;
        }
        ObjectConstructor<T> defaultImplementation = this.newDefaultImplementationConstructor(type, rawType);
        if (defaultImplementation == null) return this.newUnsafeAllocator(type, rawType);
        return defaultImplementation;
    }

    private <T> ObjectConstructor<T> newUnsafeAllocator(Type type, Class<? super T> rawType) {
        return new ObjectConstructor<T>(this, rawType, type){

            @Override
            public T construct() {
                return new ConcurrentSkipListMap();
            }
        };
    }
}
