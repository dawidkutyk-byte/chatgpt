/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  F8Jq
 *  ib4o
 */
package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory
implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final Excluder excluder;

    private List<String> getFieldNames(Field f) {
        ib4o annotation = f.getAnnotation(ib4o.class);
        if (annotation == null) {
            String name = this.fieldNamingPolicy.translateName(f);
            return Collections.singletonList(name);
        }
        String serializedName = annotation.value();
        String[] alternates = annotation.alternate();
        if (alternates.length == 0) {
            return Collections.singletonList(serializedName);
        }
        ArrayList<String> fieldNames = new ArrayList<String>(alternates.length + 1);
        fieldNames.add(serializedName);
        String[] stringArray = alternates;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String alternate = stringArray[n2];
            fieldNames.add(alternate);
            ++n2;
        }
        return fieldNames;
    }

    static boolean excludeField(Field f, boolean serialize, Excluder excluder) {
        return !excluder.excludeClass(f.getType(), serialize) && !excluder.excludeField(f, serialize);
    }

    public boolean excludeField(Field f, boolean serialize) {
        return ReflectiveTypeAdapterFactory.excludeField(f, serialize, this.excluder);
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> raw = type.getRawType();
        if (!Object.class.isAssignableFrom(raw)) {
            return null;
        }
        ObjectConstructor<T> constructor = this.constructorConstructor.get(type);
        return new Adapter(constructor, this.getBoundFields(gson, type, raw));
    }

    private Map<String, BoundField> getBoundFields(Gson context, TypeToken<?> type, Class<?> raw) {
        LinkedHashMap<String, BoundField> result = new LinkedHashMap<String, BoundField>();
        if (raw.isInterface()) {
            return result;
        }
        Type declaredType = type.getType();
        while (raw != Object.class) {
            Field[] fields;
            for (Field field : fields = raw.getDeclaredFields()) {
                boolean serialize = this.excludeField(field, true);
                boolean deserialize = this.excludeField(field, false);
                if (!serialize && !deserialize) continue;
                field.setAccessible(true);
                Type fieldType = $Gson$Types.resolve(type.getType(), raw, field.getGenericType());
                List<String> fieldNames = this.getFieldNames(field);
                BoundField previous = null;
                for (int i = 0; i < fieldNames.size(); ++i) {
                    String name = fieldNames.get(i);
                    if (i != 0) {
                        serialize = false;
                    }
                    BoundField boundField = this.createBoundField(context, field, name, TypeToken.get(fieldType), serialize, deserialize);
                    BoundField replaced = result.put(name, boundField);
                    if (previous != null) continue;
                    previous = replaced;
                }
                if (previous == null) continue;
                throw new IllegalArgumentException(declaredType + " declares multiple JSON fields named " + previous.name);
            }
            type = TypeToken.get($Gson$Types.resolve(type.getType(), raw, raw.getGenericSuperclass()));
            raw = type.getRawType();
        }
        return result;
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingPolicy, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingPolicy;
        this.excluder = excluder;
        this.jsonAdapterFactory = jsonAdapterFactory;
    }

    private BoundField createBoundField(Gson context, Field field, String name, TypeToken<?> fieldType, boolean serialize, boolean deserialize) {
        boolean jsonAdapterPresent;
        boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());
        F8Jq annotation = field.getAnnotation(F8Jq.class);
        TypeAdapter<?> mapped = null;
        if (annotation != null) {
            mapped = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, context, fieldType, annotation);
        }
        boolean bl = jsonAdapterPresent = mapped != null;
        if (mapped == null) {
            mapped = context.getAdapter(fieldType);
        }
        TypeAdapter<?> typeAdapter = mapped;
        return new TypeAdapter<T>(this, name, serialize, deserialize, field, jsonAdapterPresent, typeAdapter, context, fieldType, isPrimitive){

            @Override
            public T read(JsonReader in) throws IOException {
                in.skipValue();
                return null;
            }

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                out.nullValue();
            }

            public String toString() {
                return "AnonymousOrNonStaticLocalClassAdapter";
            }
        };
    }

    public static abstract class Adapter<T, A>
    extends TypeAdapter<T> {
        private final FieldsData fieldsData;

        Adapter(FieldsData fieldsData) {
            this.fieldsData = fieldsData;
        }

        @Override
        public void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            try {
                for (BoundField boundField : this.fieldsData.serializedFields) {
                    boundField.write(out, value);
                }
            }
            catch (IllegalAccessException e) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
            }
            out.endObject();
        }

        @Override
        public T read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            A accumulator = this.createAccumulator();
            Map<String, BoundField> deserializedFields = this.fieldsData.deserializedFields;
            try {
                in.beginObject();
                while (in.hasNext()) {
                    String name = in.nextName();
                    BoundField field = deserializedFields.get(name);
                    if (field == null) {
                        in.skipValue();
                        continue;
                    }
                    this.readField(accumulator, in, field);
                }
            }
            catch (IllegalStateException e) {
                throw new JsonSyntaxException(e);
            }
            catch (IllegalAccessException e) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
            }
            in.endObject();
            return this.finalize(accumulator);
        }

        abstract A createAccumulator();

        abstract void readField(A var1, JsonReader var2, BoundField var3) throws IllegalAccessException, IOException;

        abstract T finalize(A var1);
    }

    static abstract class BoundField {
        final String serializedName;
        final Field field;
        final String fieldName;

        protected BoundField(String serializedName, Field field) {
            this.serializedName = serializedName;
            this.field = field;
            this.fieldName = field.getName();
        }

        abstract void write(JsonWriter var1, Object var2) throws IOException, IllegalAccessException;

        abstract void readIntoArray(JsonReader var1, int var2, Object[] var3) throws IOException, JsonParseException;

        abstract void readIntoField(JsonReader var1, Object var2) throws IOException, IllegalAccessException;
    }
}
