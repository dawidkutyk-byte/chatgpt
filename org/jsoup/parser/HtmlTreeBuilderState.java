/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$StartTag
 *  org.jsoup.parser.TokeniserState
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Token;
import org.jsoup.parser.TokeniserState;

enum HtmlTreeBuilderState {
    Initial/* Unavailable Anonymous Inner Class!! */,
    BeforeHtml/* Unavailable Anonymous Inner Class!! */,
    BeforeHead/* Unavailable Anonymous Inner Class!! */,
    InHead/* Unavailable Anonymous Inner Class!! */,
    InHeadNoscript/* Unavailable Anonymous Inner Class!! */,
    AfterHead/* Unavailable Anonymous Inner Class!! */,
    InBody/* Unavailable Anonymous Inner Class!! */,
    Text/* Unavailable Anonymous Inner Class!! */,
    InTable/* Unavailable Anonymous Inner Class!! */,
    InTableText/* Unavailable Anonymous Inner Class!! */,
    InCaption/* Unavailable Anonymous Inner Class!! */,
    InColumnGroup/* Unavailable Anonymous Inner Class!! */,
    InTableBody/* Unavailable Anonymous Inner Class!! */,
    InRow/* Unavailable Anonymous Inner Class!! */,
    InCell/* Unavailable Anonymous Inner Class!! */,
    InSelect/* Unavailable Anonymous Inner Class!! */,
    InSelectInTable/* Unavailable Anonymous Inner Class!! */,
    InTemplate/* Unavailable Anonymous Inner Class!! */,
    AfterBody/* Unavailable Anonymous Inner Class!! */,
    InFrameset/* Unavailable Anonymous Inner Class!! */,
    AfterFrameset/* Unavailable Anonymous Inner Class!! */,
    AfterAfterBody/* Unavailable Anonymous Inner Class!! */,
    AfterAfterFrameset/* Unavailable Anonymous Inner Class!! */,
    ForeignContent/* Unavailable Anonymous Inner Class!! */;

    private static final String nullString;

    static /* synthetic */ void access$200(Token.StartTag x0, HtmlTreeBuilder x1) {
        HtmlTreeBuilderState.handleRcData(x0, x1);
    }

    static /* synthetic */ String access$400() {
        return nullString;
    }

    private static boolean isWhitespace(Token t) {
        if (!t.isCharacter()) return false;
        String data = t.asCharacter().getData();
        return StringUtil.isBlank((String)data);
    }

    static /* synthetic */ void access$300(Token.StartTag x0, HtmlTreeBuilder x1) {
        HtmlTreeBuilderState.handleRawtext(x0, x1);
    }

    private static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder tb) {
        tb.tokeniser.transition(TokeniserState.Rawtext);
        tb.markInsertionMode();
        tb.transition(Text);
        tb.insertElementFor(startTag);
    }

    abstract boolean process(Token var1, HtmlTreeBuilder var2);

    static /* synthetic */ boolean access$100(Token x0) {
        return HtmlTreeBuilderState.isWhitespace(x0);
    }

    static {
        nullString = String.valueOf('\u0000');
    }

    private static void handleRcData(Token.StartTag startTag, HtmlTreeBuilder tb) {
        tb.tokeniser.transition(TokeniserState.Rcdata);
        tb.markInsertionMode();
        tb.transition(Text);
        tb.insertElementFor(startTag);
    }
}
