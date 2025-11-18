/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser$FirstPassState
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser$SecondPassState
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType;

/*
 * Exception performing whole class analysis ignored.
 */
static class TokenParser.1 {
    static final /* synthetic */ int[] $SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenParser$FirstPassState;
    static final /* synthetic */ int[] $SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType;
    static final /* synthetic */ int[] $SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenParser$SecondPassState;

    static {
        $SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType = new int[TokenType.values().length];
        try {
            TokenParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType[TokenType.TEXT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TokenParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType[TokenType.OPEN_TAG.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TokenParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType[TokenType.OPEN_CLOSE_TAG.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TokenParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType[TokenType.CLOSE_TAG.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        $SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenParser$SecondPassState = new int[TokenParser.SecondPassState.values().length];
        try {
            TokenParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenParser$SecondPassState[TokenParser.SecondPassState.NORMAL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TokenParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenParser$SecondPassState[TokenParser.SecondPassState.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        $SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenParser$FirstPassState = new int[TokenParser.FirstPassState.values().length];
        try {
            TokenParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenParser$FirstPassState[TokenParser.FirstPassState.NORMAL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TokenParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenParser$FirstPassState[TokenParser.FirstPassState.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TokenParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenParser$FirstPassState[TokenParser.FirstPassState.TAG.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
