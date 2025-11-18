/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

static class TextDecoration.1 {
    static final /* synthetic */ int[] $SwitchMap$net$kyori$adventure$util$TriState;

    static {
        $SwitchMap$net$kyori$adventure$util$TriState = new int[TriState.values().length];
        try {
            TextDecoration.1.$SwitchMap$net$kyori$adventure$util$TriState[TriState.TRUE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TextDecoration.1.$SwitchMap$net$kyori$adventure$util$TriState[TriState.FALSE.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TextDecoration.1.$SwitchMap$net$kyori$adventure$util$TriState[TriState.NOT_SET.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
