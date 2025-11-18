/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.ShadowColor;

final class ShadowColorImpl
implements Examinable,
ShadowColor {
    static final int NONE_VALUE = 0;
    private final int value;
    static final ShadowColorImpl NONE = new ShadowColorImpl(0);

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public boolean equals(@Nullable Object other) {
        if (!(other instanceof ShadowColorImpl)) {
            return false;
        }
        ShadowColorImpl that = (ShadowColorImpl)other;
        return this.value == that.value;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"value", (int)this.value));
    }

    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    public int value() {
        return this.value;
    }

    ShadowColorImpl(int value) {
        this.value = value;
    }
}
