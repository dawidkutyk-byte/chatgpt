/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  net.kyori.examination.Examiner
 */
package net.kyori.examination;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.Examiner;
import org.jetbrains.annotations.NotNull;

public interface Examinable {
    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.empty();
    }

    @NotNull
    default public <R> R examine(@NotNull Examiner<R> examiner) {
        return (R)examiner.examine(this);
    }

    @NotNull
    default public String examinableName() {
        return this.getClass().getSimpleName();
    }
}
