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

static class ExaminableProperty.8
extends ExaminableProperty {
    final /* synthetic */ char[] val$value;
    final /* synthetic */ String val$name;

    @NotNull
    public <R> R examine(@NotNull Examiner<? extends R> examiner) {
        return (R)examiner.examine(this.val$value);
    }

    ExaminableProperty.8(String string, char[] cArray) {
        this.val$name = string;
        this.val$value = cArray;
        super(null);
    }

    @NotNull
    public String name() {
        return this.val$name;
    }
}
