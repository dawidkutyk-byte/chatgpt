/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Index<K, V> {
    private final Map<V, K> valueToKey;
    private final Map<K, V> keyToValue;

    @NotNull
    public Set<K> keys() {
        return Collections.unmodifiableSet(this.keyToValue.keySet());
    }

    private Index(Map<K, V> keyToValue, Map<V, K> valueToKey) {
        this.keyToValue = keyToValue;
        this.valueToKey = valueToKey;
    }

    @NotNull
    private static <K, V> Index<K, V> create(List<V> values, IntFunction<Map<V, K>> valueToKeyFactory, @NotNull Function<? super V, ? extends K> keyFunction) {
        int length = values.size();
        HashMap<K, V> keyToValue = new HashMap<K, V>(length);
        Map<K, V> valueToKey = valueToKeyFactory.apply(length);
        int i = 0;
        while (i < length) {
            V value = values.get(i);
            K key = keyFunction.apply(value);
            if (keyToValue.putIfAbsent(key, value) != null) {
                throw new IllegalStateException(String.format("Key %s already mapped to value %s", key, keyToValue.get(key)));
            }
            if (valueToKey.putIfAbsent(value, key) != null) {
                throw new IllegalStateException(String.format("Value %s already mapped to key %s", value, valueToKey.get(value)));
            }
            ++i;
        }
        return new Index(Collections.unmodifiableMap(keyToValue), Collections.unmodifiableMap(valueToKey));
    }

    @SafeVarargs
    @NotNull
    public static <K, V extends Enum<V>> Index<K, V> create(Class<V> type, @NotNull Function<? super V, ? extends K> keyFunction, V ... values) {
        return Index.create(values, (int length) -> new EnumMap(type), keyFunction);
    }

    @NotNull
    public Map<K, V> keyToValue() {
        return Collections.unmodifiableMap(this.keyToValue);
    }

    @NotNull
    public static <K, V> Index<K, V> create(@NotNull Function<? super V, ? extends K> keyFunction, @NotNull List<V> constants) {
        return Index.create(constants, HashMap::new, keyFunction);
    }

    @NotNull
    public Set<V> values() {
        return Collections.unmodifiableSet(this.valueToKey.keySet());
    }

    @Nullable
    public K key(@NotNull V value) {
        return this.valueToKey.get(value);
    }

    @Nullable
    public V value(@NotNull K key) {
        return this.keyToValue.get(key);
    }

    @NotNull
    public V valueOrThrow(@NotNull K key) {
        V value = this.value(key);
        if (value != null) return value;
        throw new NoSuchElementException("There is no value for key " + key);
    }

    @NotNull
    public static <K, V extends Enum<V>> Index<K, V> create(Class<V> type, @NotNull Function<? super V, ? extends K> keyFunction) {
        return Index.create(type, keyFunction, (Enum[])((Enum[])type.getEnumConstants()));
    }

    @NotNull
    private static <K, V> Index<K, V> create(V[] values, IntFunction<Map<V, K>> valueToKeyFactory, @NotNull Function<? super V, ? extends K> keyFunction) {
        return Index.create(Arrays.asList(values), valueToKeyFactory, keyFunction);
    }

    @Contract(value="_, null -> null; _, !null -> !null")
    public V valueOr(@NotNull K key, @Nullable V defaultValue) {
        V value = this.value(key);
        return value == null ? defaultValue : value;
    }

    @SafeVarargs
    @NotNull
    public static <K, V> Index<K, V> create(@NotNull Function<? super V, ? extends K> keyFunction, V ... values) {
        return Index.create(values, HashMap::new, keyFunction);
    }

    @Contract(value="_, null -> null; _, !null -> !null")
    public K keyOr(@NotNull V value, @Nullable K defaultKey) {
        K key = this.key(value);
        return key == null ? defaultKey : key;
    }

    @NotNull
    public K keyOrThrow(@NotNull V value) {
        K key = this.key(value);
        if (key != null) return key;
        throw new NoSuchElementException("There is no key for value " + value);
    }

    @NotNull
    public Map<V, K> valueToKey() {
        return Collections.unmodifiableMap(this.valueToKey);
    }
}
