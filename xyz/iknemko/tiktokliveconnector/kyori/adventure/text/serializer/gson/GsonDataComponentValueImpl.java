/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValue
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson;

import com.google.gson.JsonElement;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonDataComponentValue;

class GsonDataComponentValueImpl
implements GsonDataComponentValue {
    private final JsonElement element;

    GsonDataComponentValueImpl(@NotNull JsonElement element) {
        this.element = element;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"element", (Object)this.element));
    }

    @NotNull
    public JsonElement element() {
        return this.element;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        GsonDataComponentValueImpl that = (GsonDataComponentValueImpl)other;
        return Objects.equals(this.element, that.element);
    }

    public int hashCode() {
        return Objects.hashCode(this.element);
    }
}
