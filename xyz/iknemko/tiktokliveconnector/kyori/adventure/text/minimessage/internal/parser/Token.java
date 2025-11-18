/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType;

public final class Token
implements Examinable {
    private final TokenType type;
    private List<Token> childTokens = null;
    private final int endIndex;
    private final int startIndex;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"startIndex", (int)this.startIndex), ExaminableProperty.of((String)"endIndex", (int)this.endIndex), ExaminableProperty.of((String)"type", (Object)this.type));
    }

    public Token(int startIndex, int endIndex, TokenType type) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.type = type;
    }

    public int hashCode() {
        return Objects.hash(this.startIndex, this.endIndex, this.type);
    }

    public List<Token> childTokens() {
        return this.childTokens;
    }

    public CharSequence get(CharSequence message) {
        return message.subSequence(this.startIndex, this.endIndex);
    }

    public TokenType type() {
        return this.type;
    }

    public int startIndex() {
        return this.startIndex;
    }

    public int endIndex() {
        return this.endIndex;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    public void childTokens(List<Token> childTokens) {
        this.childTokens = childTokens;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Token)) {
            return false;
        }
        Token that = (Token)other;
        return this.startIndex == that.startIndex && this.endIndex == that.endIndex && this.type == that.type;
    }
}
