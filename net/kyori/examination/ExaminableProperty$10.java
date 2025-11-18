/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  net.kyori.examination.Examiner
 */
package net.kyori.examination;

import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.Examiner;
import org.jetbrains.annotations.NotNull;

static class ExaminableProperty.10
extends ExaminableProperty {
    final /* synthetic */ double[] val$value;
    final /* synthetic */ String val$name;

    ExaminableProperty.10(String string, double[] dArray) {
        this.val$name = string;
        this.val$value = dArray;
        super(null);
    }

    @NotNull
    public <R> R examine(@NotNull Examiner<? extends R> examiner) {
        return (R)examiner.examine(this.val$value);
    }

    @NotNull
    public String name() {
        return this.val$name;
    }
}
