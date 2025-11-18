/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.AbstractBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag;

@Debug.Renderer(text="\"\\\"\" + this.value + \"\\\"\"", hasChildren="false")
final class StringBinaryTagImpl
extends AbstractBinaryTag
implements StringBinaryTag {
    private final String value;

    public int hashCode() {
        return this.value.hashCode();
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (String)this.value));
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (((Object)((Object)this)).getClass() != other.getClass()) {
            return false;
        }
        StringBinaryTagImpl that = (StringBinaryTagImpl)((Object)other);
        return this.value.equals(that.value);
    }

    @NotNull
    public String value() {
        return this.value;
    }

    StringBinaryTagImpl(String value) {
        this.value = value;
    }
}
