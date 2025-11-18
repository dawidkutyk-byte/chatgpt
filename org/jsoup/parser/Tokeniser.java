/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Entities
 *  org.jsoup.parser.CharacterReader
 *  org.jsoup.parser.ParseError
 *  org.jsoup.parser.ParseErrorList
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$Character
 *  org.jsoup.parser.Token$Comment
 *  org.jsoup.parser.Token$Doctype
 *  org.jsoup.parser.Token$EndTag
 *  org.jsoup.parser.Token$StartTag
 *  org.jsoup.parser.Token$Tag
 *  org.jsoup.parser.Token$TokenType
 *  org.jsoup.parser.Tokeniser$1
 *  org.jsoup.parser.TokeniserState
 *  org.jsoup.parser.TreeBuilder
 */
package org.jsoup.parser;

import java.util.Arrays;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.ParseError;
import org.jsoup.parser.ParseErrorList;
import org.jsoup.parser.Token;
import org.jsoup.parser.Tokeniser;
import org.jsoup.parser.TokeniserState;
import org.jsoup.parser.TreeBuilder;

final class Tokeniser {
    static final char replacementChar = '\ufffd';
    private static final char[] notCharRefCharsSorted = new char[]{'\t', '\n', '\r', '\f', ' ', '<', '&'};
    final Token.Character charPending;
    private final ParseErrorList errors;
    private final StringBuilder charsBuilder;
    static final int win1252ExtensionsStart = 128;
    final Token.Doctype doctypePending;
    private @7\u015aCz String charsString = null;
    private final CharacterReader reader;
    final Token.StartTag startPending;
    static final int[] win1252Extensions = new int[]{8364, 129, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, 352, 8249, 338, 141, 381, 143, 144, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, 157, 382, 376};
    private TokeniserState state = TokeniserState.Data;
    private @7\u015aCz Token emitPending = null;
    private int charStartPos = -1;
    final Token.EndTag endPending;
    private @7\u015aCz String lastStartCloseSeq;
    Token.Tag tagPending;
    final Token.Comment commentPending;
    private static final int Unset = -1;
    private boolean isEmitPending = false;
    private @7\u015aCz String lastStartTag;
    private int markupStartPos;
    private final int[] codepointHolder;
    final StringBuilder dataBuffer;
    private final int[] multipointHolder;

    void createBogusCommentPending() {
        this.commentPending.reset();
        this.commentPending.bogus = true;
    }

    void emit(int[] codepoints) {
        this.emit(new String(codepoints, 0, codepoints.length));
    }

    static {
        Arrays.sort(notCharRefCharsSorted);
    }

    TokeniserState getState() {
        return this.state;
    }

    Token read() {
        while (!this.isEmitPending) {
            this.state.read(this, this.reader);
        }
        StringBuilder cb = this.charsBuilder;
        if (cb.length() != 0) {
            String str = cb.toString();
            cb.delete(0, cb.length());
            Token.Character token = this.charPending.data(str);
            this.charsString = null;
            return token;
        }
        if (this.charsString != null) {
            Token.Character token = this.charPending.data(this.charsString);
            this.charsString = null;
            return token;
        }
        this.isEmitPending = false;
        if ($assertionsDisabled) return this.emitPending;
        if (this.emitPending != null) return this.emitPending;
        throw new AssertionError();
    }

    @7\u015aCz String appropriateEndTagName() {
        return this.lastStartTag;
    }

    void emit(char c) {
        if (this.charsString == null) {
            this.charsString = String.valueOf(c);
        } else {
            if (this.charsBuilder.length() == 0) {
                this.charsBuilder.append(this.charsString);
            }
            this.charsBuilder.append(c);
        }
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    void emitDoctypePending() {
        this.emit((Token)this.doctypePending);
    }

    void emit(char[] chars) {
        this.emit(String.valueOf(chars));
    }

    String appropriateEndTagSeq() {
        if (this.lastStartCloseSeq != null) return this.lastStartCloseSeq;
        this.lastStartCloseSeq = "</" + this.lastStartTag;
        return this.lastStartCloseSeq;
    }

    void transition(TokeniserState newState) {
        switch (1.$SwitchMap$org$jsoup$parser$TokeniserState[newState.ordinal()]) {
            case 1: {
                this.markupStartPos = this.reader.pos();
                break;
            }
            case 2: {
                if (this.charStartPos != -1) break;
                this.charStartPos = this.reader.pos();
                break;
            }
        }
        this.state = newState;
    }

    Token.Tag createTagPending(boolean start) {
        this.tagPending = start ? this.startPending.reset() : this.endPending.reset();
        return this.tagPending;
    }

    @7\u015aCz int[] consumeCharacterReference(@7\u015aCz Character additionalAllowedCharacter, boolean inAttribute) {
        int numChars;
        boolean found;
        if (this.reader.isEmpty()) {
            return null;
        }
        if (additionalAllowedCharacter != null && additionalAllowedCharacter.charValue() == this.reader.current()) {
            return null;
        }
        if (this.reader.matchesAnySorted(notCharRefCharsSorted)) {
            return null;
        }
        int[] codeRef = this.codepointHolder;
        this.reader.mark();
        if (this.reader.matchConsume("#")) {
            String numRef;
            boolean isHexMode = this.reader.matchConsumeIgnoreCase("X");
            String string = numRef = isHexMode ? this.reader.consumeHexSequence() : this.reader.consumeDigitSequence();
            if (numRef.length() == 0) {
                this.characterReferenceError("numeric reference with no numerals", new Object[0]);
                this.reader.rewindToMark();
                return null;
            }
            this.reader.unmark();
            if (!this.reader.matchConsume(";")) {
                this.characterReferenceError("missing semicolon on [&#%s]", numRef);
            }
            int charval = -1;
            try {
                int base = isHexMode ? 16 : 10;
                charval = Integer.valueOf(numRef, base);
            }
            catch (NumberFormatException base) {
                // empty catch block
            }
            if (charval == -1 || charval > 0x10FFFF) {
                this.characterReferenceError("character [%s] outside of valid range", charval);
                codeRef[0] = 65533;
            } else {
                if (charval >= 128 && charval < 128 + win1252Extensions.length) {
                    this.characterReferenceError("character [%s] is not a valid unicode code point", charval);
                    charval = win1252Extensions[charval - 128];
                }
                codeRef[0] = charval;
            }
            return codeRef;
        }
        String nameRef = this.reader.consumeLetterThenDigitSequence();
        boolean looksLegit = this.reader.matches(';');
        boolean bl = found = Entities.isBaseNamedEntity((String)nameRef) || Entities.isNamedEntity((String)nameRef) && looksLegit;
        if (!found) {
            this.reader.rewindToMark();
            if (!looksLegit) return null;
            this.characterReferenceError("invalid named reference [%s]", nameRef);
            return null;
        }
        if (inAttribute && (this.reader.matchesLetter() || this.reader.matchesDigit() || this.reader.matchesAny(new char[]{'=', '-', '_'}))) {
            this.reader.rewindToMark();
            return null;
        }
        this.reader.unmark();
        if (!this.reader.matchConsume(";")) {
            this.characterReferenceError("missing semicolon on [&%s]", nameRef);
        }
        if ((numChars = Entities.codepointsForName((String)nameRef, (int[])this.multipointHolder)) == 1) {
            codeRef[0] = this.multipointHolder[0];
            return codeRef;
        }
        if (numChars == 2) {
            return this.multipointHolder;
        }
        Validate.fail((String)("Unexpected characters returned for " + nameRef));
        return this.multipointHolder;
    }

    void advanceTransition(TokeniserState newState) {
        this.transition(newState);
        this.reader.advance();
    }

    void createCommentPending() {
        this.commentPending.reset();
    }

    void createDoctypePending() {
        this.doctypePending.reset();
    }

    void emit(StringBuilder str) {
        if (this.charsString == null) {
            this.charsString = str.toString();
        } else {
            if (this.charsBuilder.length() == 0) {
                this.charsBuilder.append(this.charsString);
            }
            this.charsBuilder.append((CharSequence)str);
        }
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    static boolean currentNodeInHtmlNS() {
        return true;
    }

    private void characterReferenceError(String message, Object ... args) {
        if (!this.errors.canAddError()) return;
        this.errors.add((Object)new ParseError(this.reader, String.format("Invalid character reference: " + message, args)));
    }

    void emitCommentPending() {
        this.emit((Token)this.commentPending);
    }

    boolean isAppropriateEndTagToken() {
        return this.lastStartTag != null && this.tagPending.name().equalsIgnoreCase(this.lastStartTag);
    }

    String unescapeEntities(boolean inAttribute) {
        StringBuilder builder = StringUtil.borrowBuilder();
        while (!this.reader.isEmpty()) {
            builder.append(this.reader.consumeTo('&'));
            if (!this.reader.matches('&')) continue;
            this.reader.consume();
            int[] c = this.consumeCharacterReference(null, inAttribute);
            if (c == null || c.length == 0) {
                builder.append('&');
                continue;
            }
            builder.appendCodePoint(c[0]);
            if (c.length != 2) continue;
            builder.appendCodePoint(c[1]);
        }
        return StringUtil.releaseBuilder((StringBuilder)builder);
    }

    void emitTagPending() {
        this.tagPending.finaliseTag();
        this.emit((Token)this.tagPending);
    }

    void error(TokeniserState state) {
        if (!this.errors.canAddError()) return;
        this.errors.add((Object)new ParseError(this.reader, "Unexpected character '%s' in input state [%s]", new Object[]{Character.valueOf(this.reader.current()), state}));
    }

    void error(String errorMsg, Object ... args) {
        if (!this.errors.canAddError()) return;
        this.errors.add((Object)new ParseError(this.reader, errorMsg, args));
    }

    void createTempBuffer() {
        Token.reset((StringBuilder)this.dataBuffer);
    }

    void emit(Token token) {
        Validate.isFalse((boolean)this.isEmitPending);
        this.emitPending = token;
        this.isEmitPending = true;
        token.startPos(this.markupStartPos);
        token.endPos(this.reader.pos());
        this.charStartPos = -1;
        if (token.type == Token.TokenType.StartTag) {
            Token.StartTag startTag = (Token.StartTag)token;
            this.lastStartTag = startTag.tagName;
            this.lastStartCloseSeq = null;
        } else {
            if (token.type != Token.TokenType.EndTag) return;
            Token.EndTag endTag = (Token.EndTag)token;
            if (!endTag.hasAttributes()) return;
            this.error("Attributes incorrectly present on end tag [/%s]", endTag.normalName());
        }
    }

    void error(String errorMsg) {
        if (!this.errors.canAddError()) return;
        this.errors.add((Object)new ParseError(this.reader, errorMsg));
    }

    void eofError(TokeniserState state) {
        if (!this.errors.canAddError()) return;
        this.errors.add((Object)new ParseError(this.reader, "Unexpectedly reached end of file (EOF) in input state [%s]", new Object[]{state}));
    }

    void emit(String str) {
        if (this.charsString == null) {
            this.charsString = str;
        } else {
            if (this.charsBuilder.length() == 0) {
                this.charsBuilder.append(this.charsString);
            }
            this.charsBuilder.append(str);
        }
        this.charPending.startPos(this.charStartPos);
        this.charPending.endPos(this.reader.pos());
    }

    Tokeniser(TreeBuilder treeBuilder) {
        this.charsBuilder = new StringBuilder(1024);
        this.dataBuffer = new StringBuilder(1024);
        this.charPending = new Token.Character();
        this.doctypePending = new Token.Doctype();
        this.commentPending = new Token.Comment();
        this.codepointHolder = new int[1];
        this.multipointHolder = new int[2];
        this.startPending = new Token.StartTag(treeBuilder);
        this.tagPending = this.startPending;
        this.endPending = new Token.EndTag(treeBuilder);
        this.reader = treeBuilder.reader;
        this.errors = treeBuilder.parser.getErrors();
    }
}
