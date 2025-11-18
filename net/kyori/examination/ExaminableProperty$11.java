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

static class ExaminableProperty.11
extends ExaminableProperty {
    final /* synthetic */ String val$name;
    final /* synthetic */ float val$value;

    @NotNull
    public String name() {
        return this.val$name;
    }

    @NotNull
    public <R> R examine(@NotNull Examiner<? extends R> examiner) {
        return (R)examiner.examine(this.val$value);
    }

    ExaminableProperty.11(String string, float f) {
        this.val$name = string;
        this.val$value = f;
        super(null);
    }
}
