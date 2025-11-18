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

static class ExaminableProperty.3
extends ExaminableProperty {
    final /* synthetic */ boolean val$value;
    final /* synthetic */ String val$name;

    ExaminableProperty.3(String string, boolean bl) {
        this.val$name = string;
        this.val$value = bl;
        super(null);
    }

    @NotNull
    public String name() {
        return this.val$name;
    }

    @NotNull
    public <R> R examine(@NotNull Examiner<? extends R> examiner) {
        return (R)examiner.examine(this.val$value);
    }
}
