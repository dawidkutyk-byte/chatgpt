/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.Examiner
 *  net.kyori.examination.string.StringExaminer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.internal;

import net.kyori.examination.Examinable;
import net.kyori.examination.Examiner;
import net.kyori.examination.string.StringExaminer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public final class Internals {
    private Internals() {
    }

    @NotNull
    public static String toString(@NotNull Examinable examinable) {
        return (String)examinable.examine((Examiner)StringExaminer.simpleEscaping());
    }
}
