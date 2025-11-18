/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  net.kyori.examination.Examiner
 *  net.kyori.examination.string.StringExaminer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyedValue
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.key;

import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.Examiner;
import net.kyori.examination.string.StringExaminer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.KeyedValue;

final class KeyedValueImpl<T>
implements Examinable,
KeyedValue<T> {
    private final T value;
    private final Key key;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"key", (Object)this.key), ExaminableProperty.of((String)"value", this.value));
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        KeyedValueImpl that = (KeyedValueImpl)other;
        return this.key.equals(that.key) && this.value.equals(that.value);
    }

    @NotNull
    public Key key() {
        return this.key;
    }

    public int hashCode() {
        int result = this.key.hashCode();
        result = 31 * result + this.value.hashCode();
        return result;
    }

    KeyedValueImpl(Key key, T value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return (String)this.examine((Examiner)StringExaminer.simpleEscaping());
    }

    @NotNull
    public T value() {
        return this.value;
    }
}
