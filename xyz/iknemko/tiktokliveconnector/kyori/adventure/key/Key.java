/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyPattern
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyPattern$Namespace
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyPattern$Value
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Namespaced
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.key;

import java.util.Comparator;
import java.util.OptionalInt;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyPattern;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Namespaced;

public interface Key
extends Comparable<Key>,
Examinable,
Namespaced,
Keyed {
    public static final String MINECRAFT_NAMESPACE = "minecraft";
    public static final char DEFAULT_SEPARATOR = ':';

    @NotNull
    public static Key key(@KeyPattern @NotNull String string) {
        return Key.key(string, ':');
    }

    @NotNull
    public static Comparator<? super Key> comparator() {
        return KeyImpl.COMPARATOR;
    }

    @NotNull
    public static Key key(@NotNull String string, char character) {
        int index = string.indexOf(character);
        String namespace = index >= 1 ? string.substring(0, index) : MINECRAFT_NAMESPACE;
        String value = index >= 0 ? string.substring(index + 1) : string;
        return Key.key(namespace, value);
    }

    @NotNull
    public static OptionalInt checkValue(@NotNull String value) {
        int i = 0;
        int length = value.length();
        while (i < length) {
            if (!Key.allowedInValue(value.charAt(i))) {
                return OptionalInt.of(i);
            }
            ++i;
        }
        return OptionalInt.empty();
    }

    @NotNull
    @KeyPattern.Namespace
    public String namespace();

    @NotNull
    public String asString();

    @NotNull
    public static Key key(@KeyPattern.Namespace @NotNull String namespace, @KeyPattern.Value @NotNull String value) {
        return new KeyImpl(namespace, value);
    }

    public static boolean parseableNamespace(@NotNull String namespace) {
        return !Key.checkNamespace(namespace).isPresent();
    }

    @NotNull
    default public String asMinimalString() {
        if (!this.namespace().equals(MINECRAFT_NAMESPACE)) return this.asString();
        return this.value();
    }

    @NotNull
    public static Key key(@NotNull Namespaced namespaced, @KeyPattern.Value @NotNull String value) {
        return Key.key(namespaced.namespace(), value);
    }

    @Override
    default public int compareTo(@NotNull Key that) {
        return Key.comparator().compare(this, that);
    }

    @NotNull
    public static OptionalInt checkNamespace(@NotNull String namespace) {
        int i = 0;
        int length = namespace.length();
        while (i < length) {
            if (!Key.allowedInNamespace(namespace.charAt(i))) {
                return OptionalInt.of(i);
            }
            ++i;
        }
        return OptionalInt.empty();
    }

    public static boolean allowedInNamespace(char character) {
        return KeyImpl.allowedInNamespace((char)character);
    }

    public static boolean parseable(@Nullable String string) {
        if (string == null) {
            return false;
        }
        int index = string.indexOf(58);
        String namespace = index >= 1 ? string.substring(0, index) : MINECRAFT_NAMESPACE;
        String value = index >= 0 ? string.substring(index + 1) : string;
        return Key.parseableNamespace(namespace) && Key.parseableValue(value);
    }

    @NotNull
    @KeyPattern.Value
    public String value();

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"namespace", (String)this.namespace()), ExaminableProperty.of((String)"value", (String)this.value()));
    }

    @NotNull
    default public Key key() {
        return this;
    }

    public static boolean allowedInValue(char character) {
        return KeyImpl.allowedInValue((char)character);
    }

    public static boolean parseableValue(@NotNull String value) {
        return !Key.checkValue(value).isPresent();
    }
}
