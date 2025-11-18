/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.reflect;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.$Gson$Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken<T> {
    final Type \u01427ao;
    final Class<? super T> V\u01438b;
    final int \u0106r9L;

    @Deprecated
    public boolean isAssignableFrom(TypeToken<?> token) {
        return this.isAssignableFrom(token.getType());
    }

    public final Class<? super T> getRawType() {
        return this.V\u01438b;
    }

    public final int hashCode() {
        return this.\u0106r9L;
    }

    public static TypeToken<?> get(Type type) {
        return new TypeToken(type);
    }

    public final String toString() {
        return $Gson$Types.typeToString(this.\u01427ao);
    }

    private static boolean isAssignableFrom(Type from, ParameterizedType to, Map<String, Type> typeVarMap) {
        Type[] tArgs;
        if (from == null) {
            return false;
        }
        if (to.equals(from)) {
            return true;
        }
        Class<?> clazz = $Gson$Types.getRawType(from);
        ParameterizedType ptype = null;
        if (from instanceof ParameterizedType) {
            ptype = (ParameterizedType)from;
        }
        if (ptype != null) {
            tArgs = ptype.getActualTypeArguments();
            TypeVariable<Class<?>>[] tParams = clazz.getTypeParameters();
            for (int i = 0; i < tArgs.length; ++i) {
                Type arg = tArgs[i];
                TypeVariable<Class<?>> var = tParams[i];
                while (arg instanceof TypeVariable) {
                    TypeVariable v = (TypeVariable)arg;
                    arg = typeVarMap.get(v.getName());
                }
                typeVarMap.put(var.getName(), arg);
            }
            if (TypeToken.typeEquals(ptype, to, typeVarMap)) {
                return true;
            }
        }
        tArgs = clazz.getGenericInterfaces();
        int n = tArgs.length;
        int n2 = 0;
        while (true) {
            if (n2 >= n) {
                Type sType = clazz.getGenericSuperclass();
                return TypeToken.isAssignableFrom(sType, to, new HashMap<String, Type>(typeVarMap));
            }
            Type itype = tArgs[n2];
            if (TypeToken.isAssignableFrom(itype, to, new HashMap<String, Type>(typeVarMap))) {
                return true;
            }
            ++n2;
        }
    }

    private static AssertionError buildUnexpectedTypeError(Type token, Class<?> ... expected) {
        StringBuilder exceptionMessage = new StringBuilder("Unexpected type. Expected one of: ");
        Class<?>[] classArray = expected;
        int n = classArray.length;
        int n2 = 0;
        while (true) {
            if (n2 >= n) {
                exceptionMessage.append("but got: ").append(token.getClass().getName()).append(", for type token: ").append(token.toString()).append('.');
                return new AssertionError((Object)exceptionMessage.toString());
            }
            Class<?> clazz = classArray[n2];
            exceptionMessage.append(clazz.getName()).append(", ");
            ++n2;
        }
    }

    TypeToken(Type type) {
        this.\u01427ao = $Gson$Types.canonicalize($Gson$Preconditions.checkNotNull(type));
        this.V\u01438b = $Gson$Types.getRawType(this.\u01427ao);
        this.\u0106r9L = this.\u01427ao.hashCode();
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType)superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public final Type getType() {
        return this.\u01427ao;
    }

    private static boolean matches(Type from, Type to, Map<String, Type> typeMap) {
        return to.equals(from) || from instanceof TypeVariable && to.equals(typeMap.get(((TypeVariable)from).getName()));
    }

    @Deprecated
    public boolean isAssignableFrom(Type from) {
        if (from == null) {
            return false;
        }
        if (this.\u01427ao.equals(from)) {
            return true;
        }
        if (this.\u01427ao instanceof Class) {
            return this.V\u01438b.isAssignableFrom($Gson$Types.getRawType(from));
        }
        if (this.\u01427ao instanceof ParameterizedType) {
            return TypeToken.isAssignableFrom(from, (ParameterizedType)this.\u01427ao, new HashMap<String, Type>());
        }
        if (this.\u01427ao instanceof GenericArrayType) return this.V\u01438b.isAssignableFrom($Gson$Types.getRawType(from)) && TypeToken.isAssignableFrom(from, (GenericArrayType)this.\u01427ao);
        throw TypeToken.buildUnexpectedTypeError(this.\u01427ao, Class.class, ParameterizedType.class, GenericArrayType.class);
    }

    protected TypeToken() {
        this.\u01427ao = TypeToken.getSuperclassTypeParameter(this.getClass());
        this.V\u01438b = $Gson$Types.getRawType(this.\u01427ao);
        this.\u0106r9L = this.\u01427ao.hashCode();
    }

    private static boolean typeEquals(ParameterizedType from, ParameterizedType to, Map<String, Type> typeVarMap) {
        if (!from.getRawType().equals(to.getRawType())) return false;
        Type[] fromArgs = from.getActualTypeArguments();
        Type[] toArgs = to.getActualTypeArguments();
        int i = 0;
        while (i < fromArgs.length) {
            if (!TypeToken.matches(fromArgs[i], toArgs[i], typeVarMap)) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public final boolean equals(Object o) {
        return o instanceof TypeToken && $Gson$Types.equals(this.\u01427ao, ((TypeToken)o).\u01427ao);
    }

    @Deprecated
    public boolean isAssignableFrom(Class<?> cls) {
        return this.isAssignableFrom((Type)cls);
    }

    private static boolean isAssignableFrom(Type from, GenericArrayType to) {
        Type toGenericComponentType = to.getGenericComponentType();
        if (!(toGenericComponentType instanceof ParameterizedType)) return true;
        Type t = from;
        if (from instanceof GenericArrayType) {
            t = ((GenericArrayType)from).getGenericComponentType();
        } else {
            if (!(from instanceof Class)) return TypeToken.isAssignableFrom(t, (ParameterizedType)toGenericComponentType, new HashMap<String, Type>());
            Class<?> classType = (Class<?>)from;
            while (classType.isArray()) {
                classType = classType.getComponentType();
            }
            t = classType;
        }
        return TypeToken.isAssignableFrom(t, (ParameterizedType)toGenericComponentType, new HashMap<String, Type>());
    }

    public static TypeToken<?> getArray(Type componentType) {
        return new TypeToken($Gson$Types.arrayOf(componentType));
    }

    public static TypeToken<?> getParameterized(Type rawType, Type ... typeArguments) {
        return new TypeToken($Gson$Types.newParameterizedTypeWithOwner(null, rawType, typeArguments));
    }

    public static <T> TypeToken<T> get(Class<T> type) {
        return new TypeToken<T>(type);
    }
}
