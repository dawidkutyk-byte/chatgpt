/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  net.kyori.examination.Examiner
 */
package net.kyori.examination;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.Examiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractExaminer<R>
implements Examiner<R> {
    @NotNull
    private <E> R array(E @NotNull [] array) {
        return (R)this.array(array, Arrays.stream(array).map(this::examine));
    }

    @NotNull
    private <E> R collection(@NotNull Collection<E> collection) {
        return (R)this.collection(collection, collection.stream().map(this::examine));
    }

    @NotNull
    public R examine(float @Nullable [] values) {
        if (values != null) return (R)this.array(values.length, (int index) -> this.examine(values[index]));
        return this.nil();
    }

    @NotNull
    public R examine(byte @Nullable [] values) {
        if (values != null) return (R)this.array(values.length, (int index) -> this.examine(values[index]));
        return this.nil();
    }

    @NotNull
    protected abstract R nil();

    @NotNull
    public R examine(char @Nullable [] values) {
        if (values != null) return (R)this.array(values.length, (int index) -> this.examine(values[index]));
        return this.nil();
    }

    @NotNull
    public R examine(long @Nullable [] values) {
        if (values != null) return (R)this.array(values.length, (int index) -> this.examine(values[index]));
        return this.nil();
    }

    @NotNull
    public R examine(@NotNull String name, @NotNull Stream<? extends ExaminableProperty> properties) {
        return this.examinable(name, properties.map((? super T property) -> new AbstractMap.SimpleImmutableEntry<String, Object>(property.name(), property.examine((Examiner)this))));
    }

    @NotNull
    protected abstract <T> R stream(@NotNull Stream<T> var1);

    @NotNull
    private <K, V> R map(@NotNull Map<K, V> map) {
        return this.map(map, map.entrySet().stream().map((? super T entry) -> new AbstractMap.SimpleImmutableEntry<R, R>(this.examine(entry.getKey()), this.examine(entry.getValue()))));
    }

    @NotNull
    public R examine(double @Nullable [] values) {
        if (values != null) return (R)this.array(values.length, (int index) -> this.examine(values[index]));
        return this.nil();
    }

    @NotNull
    protected abstract R stream(@NotNull LongStream var1);

    @NotNull
    protected abstract R stream(@NotNull IntStream var1);

    @NotNull
    protected abstract R array(int var1, IntFunction<R> var2);

    @NotNull
    protected abstract R examinable(@NotNull String var1, @NotNull Stream<Map.Entry<String, R>> var2);

    @NotNull
    public R examine(short @Nullable [] values) {
        if (values != null) return (R)this.array(values.length, (int index) -> this.examine(values[index]));
        return this.nil();
    }

    @NotNull
    protected abstract R scalar(@NotNull Object var1);

    @NotNull
    public R examine(int @Nullable [] values) {
        if (values != null) return (R)this.array(values.length, (int index) -> this.examine(values[index]));
        return this.nil();
    }

    @NotNull
    protected abstract R stream(@NotNull DoubleStream var1);

    @NotNull
    protected abstract <E> R array(E @NotNull [] var1, @NotNull Stream<R> var2);

    @NotNull
    protected abstract <E> R collection(@NotNull Collection<E> var1, @NotNull Stream<R> var2);

    @NotNull
    public R examine(boolean @Nullable [] values) {
        if (values != null) return (R)this.array(values.length, (int index) -> this.examine(values[index]));
        return this.nil();
    }

    @NotNull
    public R examine(@Nullable Object value) {
        if (value == null) {
            return this.nil();
        }
        if (value instanceof String) {
            return (R)this.examine((String)value);
        }
        if (value instanceof Examinable) {
            return (R)this.examine((Examinable)value);
        }
        if (value instanceof Collection) {
            return this.collection((Collection)value);
        }
        if (value instanceof Map) {
            return this.map((Map)value);
        }
        if (value.getClass().isArray()) {
            Class<?> type = value.getClass().getComponentType();
            if (!type.isPrimitive()) return this.array((Object[])value);
            if (type == Boolean.TYPE) {
                return this.examine((boolean[])value);
            }
            if (type == Byte.TYPE) {
                return this.examine((byte[])value);
            }
            if (type == Character.TYPE) {
                return this.examine((char[])value);
            }
            if (type == Double.TYPE) {
                return this.examine((double[])value);
            }
            if (type == Float.TYPE) {
                return this.examine((float[])value);
            }
            if (type == Integer.TYPE) {
                return this.examine((int[])value);
            }
            if (type == Long.TYPE) {
                return this.examine((long[])value);
            }
            if (type != Short.TYPE) return this.array((Object[])value);
            return this.examine((short[])value);
        }
        if (value instanceof Boolean) {
            return (R)this.examine((Boolean)value);
        }
        if (value instanceof Character) {
            return (R)this.examine(((Character)value).charValue());
        }
        if (value instanceof Number) {
            if (value instanceof Byte) {
                return (R)this.examine((Byte)value);
            }
            if (value instanceof Double) {
                return (R)this.examine((Double)value);
            }
            if (value instanceof Float) {
                return (R)this.examine(((Float)value).floatValue());
            }
            if (value instanceof Integer) {
                return (R)this.examine((Integer)value);
            }
            if (value instanceof Long) {
                return (R)this.examine((Long)value);
            }
            if (!(value instanceof Short)) return this.scalar(value);
            return (R)this.examine((Short)value);
        }
        if (!(value instanceof BaseStream)) return this.scalar(value);
        if (value instanceof Stream) {
            return this.stream((Stream)value);
        }
        if (value instanceof DoubleStream) {
            return this.stream((DoubleStream)value);
        }
        if (value instanceof IntStream) {
            return this.stream((IntStream)value);
        }
        if (!(value instanceof LongStream)) return this.scalar(value);
        return this.stream((LongStream)value);
    }

    @NotNull
    protected abstract <K, V> R map(@NotNull Map<K, V> var1, @NotNull Stream<Map.Entry<R, R>> var2);
}
