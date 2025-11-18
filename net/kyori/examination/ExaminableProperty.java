/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examiner
 */
package net.kyori.examination;

import net.kyori.examination.Examiner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ExaminableProperty {
    @NotNull
    public static ExaminableProperty of(@NotNull String name, double[] value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, boolean value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, float[] value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, short value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, @Nullable String value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, boolean[] value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public abstract String name();

    @NotNull
    public static ExaminableProperty of(@NotNull String name, long value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    public String toString() {
        return "ExaminableProperty{" + this.name() + "}";
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, int[] value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, double value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, float value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, long[] value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public abstract <R> R examine(@NotNull Examiner<? extends R> var1);

    @NotNull
    public static ExaminableProperty of(@NotNull String name, char[] value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    private ExaminableProperty() {
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, short[] value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, char value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, @Nullable Object value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, byte value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, byte[] value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    public static ExaminableProperty of(@NotNull String name, int value) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }
}
