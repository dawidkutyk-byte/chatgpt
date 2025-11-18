/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.AbstractTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.PreProcess
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.AbstractTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.PreProcess;

final class PreProcessTagImpl
extends AbstractTag
implements PreProcess {
    private final String value;

    @NotNull
    public String value() {
        return this.value;
    }

    PreProcessTagImpl(String value) {
        this.value = value;
    }

    public int hashCode() {
        return Objects.hash(this.value);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PreProcessTagImpl)) {
            return false;
        }
        PreProcessTagImpl that = (PreProcessTagImpl)((Object)other);
        return Objects.equals(this.value, that.value);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (String)this.value));
    }
}
