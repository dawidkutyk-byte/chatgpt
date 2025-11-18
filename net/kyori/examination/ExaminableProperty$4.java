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

static class ExaminableProperty.4
extends ExaminableProperty {
    final /* synthetic */ boolean[] val$value;
    final /* synthetic */ String val$name;

    ExaminableProperty.4(String string, boolean[] blArray) {
        this.val$name = string;
        this.val$value = blArray;
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
