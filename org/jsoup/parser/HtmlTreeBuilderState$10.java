/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.parser.HtmlTreeBuilder
 *  org.jsoup.parser.HtmlTreeBuilderState
 *  org.jsoup.parser.HtmlTreeBuilderState$Constants
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$Character
 *  org.jsoup.parser.Token$TokenType
 */
package org.jsoup.parser;

import org.jsoup.internal.StringUtil;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.HtmlTreeBuilderState;
import org.jsoup.parser.Token;

/*
 * Exception performing whole class analysis ignored.
 */
final class HtmlTreeBuilderState.10
extends HtmlTreeBuilderState {
    boolean process(Token t, HtmlTreeBuilder tb) {
        if (t.type == Token.TokenType.Character) {
            Token.Character c = t.asCharacter();
            if (c.getData().equals(HtmlTreeBuilderState.access$400())) {
                tb.error((HtmlTreeBuilderState)this);
                return false;
            }
            tb.addPendingTableCharacters(c);
            return true;
        }
        if (tb.getPendingTableCharacters().size() > 0) {
            Token og = tb.currentToken;
            for (Token.Character c : tb.getPendingTableCharacters()) {
                tb.currentToken = c;
                if (!HtmlTreeBuilderState.access$100((Token)c)) {
                    tb.error((HtmlTreeBuilderState)this);
                    if (StringUtil.inSorted((String)tb.currentElement().normalName(), (String[])HtmlTreeBuilderState.Constants.InTableFoster)) {
                        tb.setFosterInserts(true);
                        tb.process((Token)c, InBody);
                        tb.setFosterInserts(false);
                        continue;
                    }
                    tb.process((Token)c, InBody);
                    continue;
                }
                tb.insertCharacterNode(c);
            }
            tb.currentToken = og;
            tb.resetPendingTableCharacters();
        }
        tb.transition(tb.originalState());
        return tb.process(t);
    }

    HtmlTreeBuilderState.10() {
        super(string, n, null);
    }
}
