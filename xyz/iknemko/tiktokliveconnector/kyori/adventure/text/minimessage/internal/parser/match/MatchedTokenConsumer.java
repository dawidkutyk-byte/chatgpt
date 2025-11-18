/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.match;

import org.jetbrains.annotations.MustBeInvokedByOverriders;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType;

public abstract class MatchedTokenConsumer<T> {
    private int lastIndex = -1;
    protected final String input;

    @MustBeInvokedByOverriders
    public void accept(int start, int end, @NotNull TokenType tokenType) {
        this.lastIndex = end;
    }

    public final int lastEndIndex() {
        return this.lastIndex;
    }

    public abstract @UnknownNullability T result();

    public MatchedTokenConsumer(@NotNull String input) {
        this.input = input;
    }
}
