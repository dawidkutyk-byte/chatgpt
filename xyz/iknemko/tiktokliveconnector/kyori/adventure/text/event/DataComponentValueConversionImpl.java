/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry$Conversion
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry;

final class DataComponentValueConversionImpl<I, O>
implements DataComponentValueConverterRegistry.Conversion<I, O> {
    private final Class<O> destination;
    private final BiFunction<Key, I, O> conversion;
    private final Class<I> source;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"source", this.source), ExaminableProperty.of((String)"destination", this.destination), ExaminableProperty.of((String)"conversion", this.conversion));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        DataComponentValueConversionImpl that = (DataComponentValueConversionImpl)other;
        return Objects.equals(this.source, that.source) && Objects.equals(this.destination, that.destination) && Objects.equals(this.conversion, that.conversion);
    }

    @NotNull
    public O convert(@NotNull Key key, @NotNull I input) {
        return this.conversion.apply(Objects.requireNonNull(key, "key"), Objects.requireNonNull(input, "input"));
    }

    public int hashCode() {
        return Objects.hash(this.source, this.destination, this.conversion);
    }

    @NotNull
    public Class<I> source() {
        return this.source;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    DataComponentValueConversionImpl(@NotNull Class<I> source, @NotNull Class<O> destination, @NotNull BiFunction<Key, I, O> conversion) {
        this.source = source;
        this.destination = destination;
        this.conversion = conversion;
    }

    @NotNull
    public Class<O> destination() {
        return this.destination;
    }
}
