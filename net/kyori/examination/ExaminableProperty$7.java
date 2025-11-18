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

static class ExaminableProperty.7
extends ExaminableProperty {
    final /* synthetic */ String val$name;
    final /* synthetic */ char val$value;

    @NotNull
    public <R> R examine(@NotNull Examiner<? extends R> examiner) {
        return (R)examiner.examine(this.val$value);
    }

    ExaminableProperty.7(String string, char c) {
        this.val$name = string;
        this.val$value = c;
        super(null);
    }

    @NotNull
    public String name() {
        return this.val$name;
    }
}
