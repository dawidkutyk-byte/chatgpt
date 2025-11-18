/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.NamedTextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

public final class LegacyFormat
implements Examinable {
    private final boolean reset;
    @Nullable
    private final TextDecoration decoration;
    static final LegacyFormat RESET = new LegacyFormat(true);
    @Nullable
    private final NamedTextColor color;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"color", (Object)this.color), ExaminableProperty.of((String)"decoration", (Object)this.decoration), ExaminableProperty.of((String)"reset", (boolean)this.reset));
    }

    public int hashCode() {
        int result = Objects.hashCode(this.color);
        result = 31 * result + Objects.hashCode(this.decoration);
        result = 31 * result + Boolean.hashCode(this.reset);
        return result;
    }

    public boolean reset() {
        return this.reset;
    }

    private LegacyFormat(boolean reset) {
        this.color = null;
        this.decoration = null;
        this.reset = reset;
    }

    @Nullable
    public TextColor color() {
        return this.color;
    }

    LegacyFormat(@Nullable TextDecoration decoration) {
        this.color = null;
        this.decoration = decoration;
        this.reset = false;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        LegacyFormat that = (LegacyFormat)other;
        return this.color == that.color && this.decoration == that.decoration && this.reset == that.reset;
    }

    @Nullable
    public TextDecoration decoration() {
        return this.decoration;
    }

    LegacyFormat(@Nullable NamedTextColor color) {
        this.color = color;
        this.decoration = null;
        this.reset = false;
    }
}
