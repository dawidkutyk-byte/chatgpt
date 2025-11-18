/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ShadyPines
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.Objects;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.HSVLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.ShadyPines;

final class HSVLikeImpl
implements HSVLike {
    private final float h;
    private final float v;
    private final float s;

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public float h() {
        return this.h;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HSVLikeImpl)) {
            return false;
        }
        HSVLikeImpl that = (HSVLikeImpl)other;
        return ShadyPines.equals((float)that.h, (float)this.h) && ShadyPines.equals((float)that.s, (float)this.s) && ShadyPines.equals((float)that.v, (float)this.v);
    }

    public float s() {
        return this.s;
    }

    public int hashCode() {
        return Objects.hash(Float.valueOf(this.h), Float.valueOf(this.s), Float.valueOf(this.v));
    }

    public float v() {
        return this.v;
    }

    HSVLikeImpl(float h, float s, float v) {
        HSVLikeImpl.requireInsideRange(h, "h");
        HSVLikeImpl.requireInsideRange(s, "s");
        HSVLikeImpl.requireInsideRange(v, "v");
        this.h = h;
        this.s = s;
        this.v = v;
    }

    private static void requireInsideRange(float number, String name) throws IllegalArgumentException {
        if (number < 0.0f) throw new IllegalArgumentException(name + " (" + number + ") is not inside the required range: [0,1]");
        if (!(1.0f < number)) return;
        throw new IllegalArgumentException(name + " (" + number + ") is not inside the required range: [0,1]");
    }
}
