/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.match.MatchedTokenConsumer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.match.MatchedTokenConsumer;

public final class TokenListProducingMatchedTokenConsumer
extends MatchedTokenConsumer<List<Token>> {
    private List<Token> result = null;

    public void accept(int start, int end, @NotNull TokenType tokenType) {
        super.accept(start, end, tokenType);
        if (this.result == null) {
            this.result = new ArrayList<Token>();
        }
        this.result.add(new Token(start, end, tokenType));
    }

    public TokenListProducingMatchedTokenConsumer(@NotNull String input) {
        super(input);
    }

    @NotNull
    public List<Token> result() {
        return this.result == null ? Collections.emptyList() : this.result;
    }
}
