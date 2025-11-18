/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.PatternReplacementResult
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.regex.MatchResult;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.PatternReplacementResult;

@FunctionalInterface
public static interface TextReplacementConfig.Condition {
    @NotNull
    public PatternReplacementResult shouldReplace(@NotNull MatchResult var1, int var2, int var3);
}
