/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser;

import java.util.Arrays;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;

@ApiStatus.Internal
public class ParsingExceptionImpl
extends ParsingException {
    private final String originalText;
    private static final long serialVersionUID = 2507190809441787202L;
    private Token @NotNull [] tokens;

    private String arrow() {
        @NotNull Token[] ts = this.tokens();
        char[] chars = new char[ts[ts.length - 1].endIndex()];
        int i = 0;
        Token[] tokenArray = ts;
        int n = tokenArray.length;
        int n2 = 0;
        while (n2 < n) {
            Token t = tokenArray[n2];
            Arrays.fill(chars, i, t.startIndex(), ' ');
            chars[t.startIndex()] = 94;
            if (Math.abs(t.startIndex() - t.endIndex()) > 1) {
                Arrays.fill(chars, t.startIndex() + 1, t.endIndex() - 1, '~');
            }
            chars[t.endIndex() - 1] = 94;
            i = t.endIndex();
            ++n2;
        }
        return new String(chars);
    }

    public ParsingExceptionImpl(String message, @Nullable String originalText, Token ... tokens) {
        super(message, null, true, false);
        this.tokens = tokens;
        this.originalText = originalText;
    }

    public String getMessage() {
        String arrowInfo = this.tokens().length != 0 ? "\n\t" + this.arrow() : "";
        String messageInfo = this.originalText() != null ? "\n\t" + this.originalText() + arrowInfo : "";
        return super.getMessage() + messageInfo;
    }

    public int startIndex() {
        if (this.tokens.length != 0) return this.tokens[0].startIndex();
        return -1;
    }

    @NotNull
    public @NotNull Token @NotNull [] tokens() {
        return this.tokens;
    }

    public ParsingExceptionImpl(String message, @Nullable String originalText, @Nullable Throwable cause, boolean withStackTrace, Token ... tokens) {
        super(message, cause, true, withStackTrace);
        this.tokens = tokens;
        this.originalText = originalText;
    }

    @Nullable
    public String detailMessage() {
        return super.getMessage();
    }

    public void tokens(@NotNull @NotNull Token @NotNull [] tokens) {
        this.tokens = tokens;
    }

    public int endIndex() {
        if (this.tokens.length != 0) return this.tokens[this.tokens.length - 1].endIndex();
        return -1;
    }

    @Nullable
    public String originalText() {
        return this.originalText;
    }
}
