/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser$TagProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag$Argument
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;

public final class TagPart
implements Tag.Argument {
    private final Token token;
    private final String value;

    public String toString() {
        return this.value;
    }

    @NotNull
    public Token token() {
        return this.token;
    }

    @NotNull
    public static String unquoteAndEscape(@NotNull String text, int start, int end) {
        if (start == end) {
            return "";
        }
        int startIndex = start;
        int endIndex = end;
        char firstChar = text.charAt(startIndex);
        char lastChar = text.charAt(endIndex - 1);
        if (firstChar != '\'') {
            if (firstChar != '\"') return text.substring(startIndex, endIndex);
        }
        ++startIndex;
        if (lastChar == '\'' || lastChar == '\"') {
            --endIndex;
        }
        if (startIndex <= endIndex) return TokenParser.unescape((String)text, (int)startIndex, (int)endIndex, i -> i == firstChar || i == 92);
        return text.substring(start, end);
    }

    public TagPart(@NotNull String sourceMessage, @NotNull Token token, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TokenParser.TagProvider tagResolver) {
        String v = TagPart.unquoteAndEscape(sourceMessage, token.startIndex(), token.endIndex());
        this.value = v = TokenParser.resolvePreProcessTags((String)v, (TokenParser.TagProvider)tagResolver);
        this.token = token;
    }

    @NotNull
    public String value() {
        return this.value;
    }
}
