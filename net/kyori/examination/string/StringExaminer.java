/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.AbstractExaminer
 *  net.kyori.examination.string.StringExaminer$Instances
 *  net.kyori.examination.string.Strings
 */
package net.kyori.examination.string;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import net.kyori.examination.AbstractExaminer;
import net.kyori.examination.string.StringExaminer;
import net.kyori.examination.string.Strings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StringExaminer
extends AbstractExaminer<String> {
    private static final Collector<CharSequence, ?, String> COMMA_CURLY;
    private static final Collector<CharSequence, ?, String> COMMA_SQUARE;
    private static final Function<String, String> DEFAULT_ESCAPER;
    private final Function<String, String> escaper;

    @NotNull
    protected String stream(@NotNull DoubleStream stream) {
        return stream.mapToObj(this::examine).collect(COMMA_SQUARE);
    }

    @NotNull
    protected String stream(@NotNull LongStream stream) {
        return stream.mapToObj(l -> this.examine(l)).collect(COMMA_SQUARE);
    }

    static {
        DEFAULT_ESCAPER = string -> string.replace("\"", "\\\"").replace("\\", "\\\\").replace("\b", "\\b").replace("\f", "\\f").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
        COMMA_CURLY = Collectors.joining(", ", "{", "}");
        COMMA_SQUARE = Collectors.joining(", ", "[", "]");
    }

    static /* synthetic */ Function access$000() {
        return DEFAULT_ESCAPER;
    }

    @NotNull
    protected <K, V> String map(@NotNull Map<K, V> map, @NotNull Stream<Map.Entry<String, String>> entries) {
        return entries.map(entry -> (String)entry.getKey() + '=' + (String)entry.getValue()).collect(COMMA_CURLY);
    }

    @NotNull
    public static StringExaminer simpleEscaping() {
        return Instances.SIMPLE_ESCAPING;
    }

    @NotNull
    public String examine(short value) {
        return String.valueOf(value);
    }

    @NotNull
    protected String stream(@NotNull IntStream stream) {
        return stream.mapToObj(n -> this.examine(n)).collect(COMMA_SQUARE);
    }

    @NotNull
    public String examine(@Nullable String value) {
        if (value != null) return Strings.wrapIn((String)this.escaper.apply(value), (char)'\"');
        return this.nil();
    }

    public StringExaminer(@NotNull Function<String, String> escaper) {
        this.escaper = escaper;
    }

    @NotNull
    public String examine(float value) {
        return Strings.withSuffix((String)String.valueOf(value), (char)'f');
    }

    @NotNull
    protected String nil() {
        return "null";
    }

    @NotNull
    protected <E> String array(E @NotNull [] array, @NotNull Stream<String> elements) {
        return elements.collect(COMMA_SQUARE);
    }

    @NotNull
    protected <T> String stream(@NotNull Stream<T> stream) {
        return stream.map(arg_0 -> ((StringExaminer)this).examine(arg_0)).collect(COMMA_SQUARE);
    }

    @NotNull
    protected String examinable(@NotNull String name, @NotNull Stream<Map.Entry<String, String>> properties) {
        return name + properties.map(property -> (String)property.getKey() + '=' + (String)property.getValue()).collect(COMMA_CURLY);
    }

    @NotNull
    public String examine(double value) {
        return Strings.withSuffix((String)String.valueOf(value), (char)'d');
    }

    @NotNull
    public String examine(byte value) {
        return String.valueOf(value);
    }

    @NotNull
    public String examine(int value) {
        return String.valueOf(value);
    }

    @NotNull
    protected String array(int length, IntFunction<String> value) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (true) {
            if (i >= length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(value.apply(i));
            if (i + 1 < length) {
                sb.append(", ");
            }
            ++i;
        }
    }

    @NotNull
    protected String scalar(@NotNull Object value) {
        return String.valueOf(value);
    }

    @NotNull
    public String examine(long value) {
        return String.valueOf(value);
    }

    @NotNull
    protected <E> String collection(@NotNull Collection<E> collection, @NotNull Stream<String> elements) {
        return elements.collect(COMMA_SQUARE);
    }

    @NotNull
    public String examine(boolean value) {
        return String.valueOf(value);
    }

    @NotNull
    public String examine(char value) {
        return Strings.wrapIn((String)this.escaper.apply(String.valueOf(value)), (char)'\'');
    }
}
