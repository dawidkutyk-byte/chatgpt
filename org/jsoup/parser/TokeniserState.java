/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.CharacterReader
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$EOF
 *  org.jsoup.parser.Tokeniser
 */
package org.jsoup.parser;

import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Token;
import org.jsoup.parser.Tokeniser;

enum TokeniserState {
    Data/* Unavailable Anonymous Inner Class!! */,
    CharacterReferenceInData/* Unavailable Anonymous Inner Class!! */,
    Rcdata/* Unavailable Anonymous Inner Class!! */,
    CharacterReferenceInRcdata/* Unavailable Anonymous Inner Class!! */,
    Rawtext/* Unavailable Anonymous Inner Class!! */,
    ScriptData/* Unavailable Anonymous Inner Class!! */,
    PLAINTEXT/* Unavailable Anonymous Inner Class!! */,
    TagOpen/* Unavailable Anonymous Inner Class!! */,
    EndTagOpen/* Unavailable Anonymous Inner Class!! */,
    TagName/* Unavailable Anonymous Inner Class!! */,
    RcdataLessthanSign/* Unavailable Anonymous Inner Class!! */,
    RCDATAEndTagOpen/* Unavailable Anonymous Inner Class!! */,
    RCDATAEndTagName/* Unavailable Anonymous Inner Class!! */,
    RawtextLessthanSign/* Unavailable Anonymous Inner Class!! */,
    RawtextEndTagOpen/* Unavailable Anonymous Inner Class!! */,
    RawtextEndTagName/* Unavailable Anonymous Inner Class!! */,
    ScriptDataLessthanSign/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEndTagOpen/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEndTagName/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEscapeStart/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEscapeStartDash/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEscaped/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEscapedDash/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEscapedDashDash/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEscapedLessthanSign/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEscapedEndTagOpen/* Unavailable Anonymous Inner Class!! */,
    ScriptDataEscapedEndTagName/* Unavailable Anonymous Inner Class!! */,
    ScriptDataDoubleEscapeStart/* Unavailable Anonymous Inner Class!! */,
    ScriptDataDoubleEscaped/* Unavailable Anonymous Inner Class!! */,
    ScriptDataDoubleEscapedDash/* Unavailable Anonymous Inner Class!! */,
    ScriptDataDoubleEscapedDashDash/* Unavailable Anonymous Inner Class!! */,
    ScriptDataDoubleEscapedLessthanSign/* Unavailable Anonymous Inner Class!! */,
    ScriptDataDoubleEscapeEnd/* Unavailable Anonymous Inner Class!! */,
    BeforeAttributeName/* Unavailable Anonymous Inner Class!! */,
    AttributeName/* Unavailable Anonymous Inner Class!! */,
    AfterAttributeName/* Unavailable Anonymous Inner Class!! */,
    BeforeAttributeValue/* Unavailable Anonymous Inner Class!! */,
    AttributeValue_doubleQuoted/* Unavailable Anonymous Inner Class!! */,
    AttributeValue_singleQuoted/* Unavailable Anonymous Inner Class!! */,
    AttributeValue_unquoted/* Unavailable Anonymous Inner Class!! */,
    AfterAttributeValue_quoted/* Unavailable Anonymous Inner Class!! */,
    SelfClosingStartTag/* Unavailable Anonymous Inner Class!! */,
    BogusComment/* Unavailable Anonymous Inner Class!! */,
    MarkupDeclarationOpen/* Unavailable Anonymous Inner Class!! */,
    CommentStart/* Unavailable Anonymous Inner Class!! */,
    CommentStartDash/* Unavailable Anonymous Inner Class!! */,
    Comment/* Unavailable Anonymous Inner Class!! */,
    CommentEndDash/* Unavailable Anonymous Inner Class!! */,
    CommentEnd/* Unavailable Anonymous Inner Class!! */,
    CommentEndBang/* Unavailable Anonymous Inner Class!! */,
    Doctype/* Unavailable Anonymous Inner Class!! */,
    BeforeDoctypeName/* Unavailable Anonymous Inner Class!! */,
    DoctypeName/* Unavailable Anonymous Inner Class!! */,
    AfterDoctypeName/* Unavailable Anonymous Inner Class!! */,
    AfterDoctypePublicKeyword/* Unavailable Anonymous Inner Class!! */,
    BeforeDoctypePublicIdentifier/* Unavailable Anonymous Inner Class!! */,
    DoctypePublicIdentifier_doubleQuoted/* Unavailable Anonymous Inner Class!! */,
    DoctypePublicIdentifier_singleQuoted/* Unavailable Anonymous Inner Class!! */,
    AfterDoctypePublicIdentifier/* Unavailable Anonymous Inner Class!! */,
    BetweenDoctypePublicAndSystemIdentifiers/* Unavailable Anonymous Inner Class!! */,
    AfterDoctypeSystemKeyword/* Unavailable Anonymous Inner Class!! */,
    BeforeDoctypeSystemIdentifier/* Unavailable Anonymous Inner Class!! */,
    DoctypeSystemIdentifier_doubleQuoted/* Unavailable Anonymous Inner Class!! */,
    DoctypeSystemIdentifier_singleQuoted/* Unavailable Anonymous Inner Class!! */,
    AfterDoctypeSystemIdentifier/* Unavailable Anonymous Inner Class!! */,
    BogusDoctype/* Unavailable Anonymous Inner Class!! */,
    CdataSection/* Unavailable Anonymous Inner Class!! */;

    private static final String replacementStr;
    static final char[] attributeNameCharsSorted;
    static final char nullChar = '\u0000';
    static final char[] attributeValueUnquoted;
    private static final char eof = '\uffff';
    private static final char replacementChar = '\ufffd';

    private static void readCharRef(Tokeniser t, TokeniserState advance) {
        int[] c = t.consumeCharacterReference(null, false);
        if (c == null) {
            t.emit('&');
        } else {
            t.emit(c);
        }
        t.transition(advance);
    }

    static /* synthetic */ void access$100(Tokeniser x0, TokeniserState x1) {
        TokeniserState.readCharRef(x0, x1);
    }

    private static void readRawData(Tokeniser t, CharacterReader r, TokeniserState current, TokeniserState advance) {
        switch (r.current()) {
            case '<': {
                t.advanceTransition(advance);
                break;
            }
            case '\u0000': {
                t.error(current);
                r.advance();
                t.emit('\ufffd');
                break;
            }
            case '\uffff': {
                t.emit((Token)new Token.EOF());
                break;
            }
            default: {
                String data = r.consumeRawData();
                t.emit(data);
            }
        }
    }

    static /* synthetic */ void access$500(Tokeniser x0, CharacterReader x1, TokeniserState x2) {
        TokeniserState.handleDataEndTag(x0, x1, x2);
    }

    abstract void read(Tokeniser var1, CharacterReader var2);

    static /* synthetic */ void access$200(Tokeniser x0, CharacterReader x1, TokeniserState x2, TokeniserState x3) {
        TokeniserState.readRawData(x0, x1, x2, x3);
    }

    static /* synthetic */ void access$400(Tokeniser x0, CharacterReader x1, TokeniserState x2, TokeniserState x3) {
        TokeniserState.readEndTag(x0, x1, x2, x3);
    }

    static {
        attributeNameCharsSorted = new char[]{'\t', '\n', '\f', '\r', ' ', '\"', '\'', '/', '<', '=', '>'};
        attributeValueUnquoted = new char[]{'\u0000', '\t', '\n', '\f', '\r', ' ', '\"', '&', '\'', '<', '=', '>', '`'};
        replacementStr = String.valueOf('\ufffd');
    }

    static /* synthetic */ void access$600(Tokeniser x0, CharacterReader x1, TokeniserState x2, TokeniserState x3) {
        TokeniserState.handleDataDoubleEscapeTag(x0, x1, x2, x3);
    }

    private static void handleDataEndTag(Tokeniser t, CharacterReader r, TokeniserState elseTransition) {
        if (r.matchesLetter()) {
            String name = r.consumeLetterSequence();
            t.tagPending.appendTagName(name);
            t.dataBuffer.append(name);
            return;
        }
        boolean needsExitTransition = false;
        if (t.isAppropriateEndTagToken() && !r.isEmpty()) {
            char c = r.consume();
            switch (c) {
                case '\t': 
                case '\n': 
                case '\f': 
                case '\r': 
                case ' ': {
                    t.transition(BeforeAttributeName);
                    break;
                }
                case '/': {
                    t.transition(SelfClosingStartTag);
                    break;
                }
                case '>': {
                    t.emitTagPending();
                    t.transition(Data);
                    break;
                }
                default: {
                    t.dataBuffer.append(c);
                    needsExitTransition = true;
                    break;
                }
            }
        } else {
            needsExitTransition = true;
        }
        if (!needsExitTransition) return;
        t.emit("</");
        t.emit(t.dataBuffer);
        t.transition(elseTransition);
    }

    static /* synthetic */ String access$300() {
        return replacementStr;
    }

    private static void readEndTag(Tokeniser t, CharacterReader r, TokeniserState a, TokeniserState b) {
        if (r.matchesAsciiAlpha()) {
            t.createTagPending(false);
            t.transition(a);
        } else {
            t.emit("</");
            t.transition(b);
        }
    }

    private static void handleDataDoubleEscapeTag(Tokeniser t, CharacterReader r, TokeniserState primary, TokeniserState fallback) {
        if (r.matchesLetter()) {
            String name = r.consumeLetterSequence();
            t.dataBuffer.append(name);
            t.emit(name);
            return;
        }
        char c = r.consume();
        switch (c) {
            case '\t': 
            case '\n': 
            case '\f': 
            case '\r': 
            case ' ': 
            case '/': 
            case '>': {
                if (t.dataBuffer.toString().equals("script")) {
                    t.transition(primary);
                } else {
                    t.transition(fallback);
                }
                t.emit(c);
                break;
            }
            default: {
                r.unconsume();
                t.transition(fallback);
            }
        }
    }
}
