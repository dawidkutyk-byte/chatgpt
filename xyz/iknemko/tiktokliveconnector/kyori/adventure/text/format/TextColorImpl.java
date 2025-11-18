/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike;

@Debug.Renderer(text="asHexString()")
final class TextColorImpl
implements TextColor {
    private final int value;

    public String toString() {
        return this.asHexString();
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextColorImpl)) {
            return false;
        }
        TextColorImpl that = (TextColorImpl)other;
        return this.value == that.value;
    }

    static float distance(@NotNull HSVLike self, @NotNull HSVLike other) {
        float hueDistance = 3.0f * Math.min(Math.abs(self.h() - other.h()), 1.0f - Math.abs(self.h() - other.h()));
        float saturationDiff = self.s() - other.s();
        float valueDiff = self.v() - other.v();
        return hueDistance * hueDistance + saturationDiff * saturationDiff + valueDiff * valueDiff;
    }

    public int value() {
        return this.value;
    }

    TextColorImpl(int value) {
        this.value = value;
    }
}
