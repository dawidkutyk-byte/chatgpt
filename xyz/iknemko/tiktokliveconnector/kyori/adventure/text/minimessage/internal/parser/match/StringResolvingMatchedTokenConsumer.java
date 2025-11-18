/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser$TagProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.match.MatchedTokenConsumer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagPart
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.PreProcess
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.TagInternals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.match.MatchedTokenConsumer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagPart;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.PreProcess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;

public final class StringResolvingMatchedTokenConsumer
extends MatchedTokenConsumer<String> {
    private final TokenParser.TagProvider tagProvider;
    private final StringBuilder builder;

    public void accept(int start, int end, @NotNull TokenType tokenType) {
        super.accept(start, end, tokenType);
        if (tokenType != TokenType.OPEN_TAG) {
            this.builder.append(this.input, start, end);
        } else {
            String tag;
            String match = this.input.substring(start, end);
            String cleanup = this.input.substring(start + 1, end - 1);
            int index = cleanup.indexOf(58);
            String string = tag = index == -1 ? cleanup : cleanup.substring(0, index);
            if (TagInternals.sanitizeAndCheckValidTagName((String)tag)) {
                Tag replacement;
                List childs;
                List tokens = TokenParser.tokenize((String)match, (boolean)false);
                ArrayList<TagPart> parts = new ArrayList<TagPart>();
                List list = childs = tokens.isEmpty() ? null : ((Token)tokens.get(0)).childTokens();
                if (childs != null) {
                    for (int i = 1; i < childs.size(); ++i) {
                        parts.add(new TagPart(match, (Token)childs.get(i), this.tagProvider));
                    }
                }
                if ((replacement = this.tagProvider.resolve(TokenParser.TagProvider.sanitizePlaceholderName((String)tag), parts, (Token)tokens.get(0))) instanceof PreProcess) {
                    this.builder.append(Objects.requireNonNull(((PreProcess)replacement).value(), "PreProcess replacements cannot return null"));
                    return;
                }
            }
            this.builder.append(match);
        }
    }

    @NotNull
    public String result() {
        return this.builder.toString();
    }

    public StringResolvingMatchedTokenConsumer(@NotNull String input, @NotNull TokenParser.TagProvider tagProvider) {
        super(input);
        this.builder = new StringBuilder(input.length());
        this.tagProvider = tagProvider;
    }
}
