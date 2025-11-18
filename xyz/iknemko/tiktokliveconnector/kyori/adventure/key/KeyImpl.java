/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.key;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;

final class KeyImpl
implements Key {
    @RegExp
    static final String VALUE_PATTERN = "[a-z0-9_\\-./]+";
    private final String value;
    @RegExp
    static final String NAMESPACE_PATTERN = "[a-z0-9_\\-.]+";
    static final Comparator<? super Key> COMPARATOR = Comparator.comparing(Key::value).thenComparing(Key::namespace);
    private final String namespace;

    @NotNull
    public String asString() {
        return KeyImpl.asString(this.namespace, this.value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        Key that = (Key)other;
        if (other instanceof Key) return Objects.equals(this.namespace, that.namespace()) && Objects.equals(this.value, that.value());
        return false;
    }

    private static void checkError(String name, String namespace, String value, OptionalInt index) {
        if (!index.isPresent()) return;
        int indexValue = index.getAsInt();
        char character = value.charAt(indexValue);
        throw new InvalidKeyException(namespace, value, String.format("Non [a-z0-9_.-] character in %s of Key[%s] at index %d ('%s', bytes: %s)", name, KeyImpl.asString(namespace, value), indexValue, Character.valueOf(character), Arrays.toString(String.valueOf(character).getBytes(StandardCharsets.UTF_8))));
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"namespace", (String)this.namespace), ExaminableProperty.of((String)"value", (String)this.value));
    }

    static boolean allowedInValue(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= '0' && character <= '9' || character == '.' || character == '/';
    }

    @NotNull
    public String toString() {
        return this.asString();
    }

    @NotNull
    public String namespace() {
        return this.namespace;
    }

    @NotNull
    private static String asString(@NotNull String namespace, @NotNull String value) {
        return namespace + ':' + value;
    }

    public int compareTo(@NotNull Key that) {
        return super.compareTo(that);
    }

    static boolean allowedInNamespace(char character) {
        return character == '_' || character == '-' || character >= 'a' && character <= 'z' || character >= '0' && character <= '9' || character == '.';
    }

    @NotNull
    public String value() {
        return this.value;
    }

    KeyImpl(@NotNull String namespace, @NotNull String value) {
        KeyImpl.checkError("namespace", namespace, value, Key.checkNamespace((String)namespace));
        KeyImpl.checkError("value", namespace, value, Key.checkValue((String)value));
        this.namespace = Objects.requireNonNull(namespace, "namespace");
        this.value = Objects.requireNonNull(value, "value");
    }

    public int hashCode() {
        int result = this.namespace.hashCode();
        result = 31 * result + this.value.hashCode();
        return result;
    }
}
