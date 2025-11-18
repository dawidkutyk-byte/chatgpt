/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;

final class TranslationArgumentImpl
implements TranslationArgument {
    private static final Component TRUE = Component.text((String)"true");
    private static final Component FALSE = Component.text((String)"false");
    private final Object value;

    @NotNull
    public Component asComponent() {
        if (this.value instanceof Component) {
            return (Component)this.value;
        }
        if (!(this.value instanceof Boolean)) return Component.text((String)String.valueOf(this.value));
        return (Boolean)this.value != false ? TRUE : FALSE;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (Object)this.value));
    }

    public int hashCode() {
        return Objects.hash(this.value);
    }

    TranslationArgumentImpl(Object value) {
        this.value = value;
    }

    @NotNull
    public Object value() {
        return this.value;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        TranslationArgumentImpl that = (TranslationArgumentImpl)other;
        return Objects.equals(this.value, that.value);
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }
}
