/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType;

static class MiniMessageParser.1 {
    static final /* synthetic */ int[] $SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType;

    static {
        $SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType = new int[TokenType.values().length];
        try {
            MiniMessageParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType[TokenType.TEXT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            MiniMessageParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType[TokenType.OPEN_TAG.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            MiniMessageParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType[TokenType.CLOSE_TAG.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            MiniMessageParser.1.$SwitchMap$net$kyori$adventure$text$minimessage$internal$parser$TokenType[TokenType.OPEN_CLOSE_TAG.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}
