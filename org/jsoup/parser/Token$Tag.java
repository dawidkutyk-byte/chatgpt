/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Range
 *  org.jsoup.nodes.Range$AttributeRange
 *  org.jsoup.nodes.Range$Position
 *  org.jsoup.parser.CharacterReader
 *  org.jsoup.parser.ParseSettings
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$StartTag
 *  org.jsoup.parser.Token$TokenType
 *  org.jsoup.parser.TreeBuilder
 */
package org.jsoup.parser;

import java.util.HashMap;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Range;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Token;
import org.jsoup.parser.TreeBuilder;

static abstract class Token.Tag
extends Token {
    private boolean hasAttrValue = false;
    private @7\u015aCz String attrName;
    protected @7\u015aCz String normalName;
    int attrNameEnd;
    final TreeBuilder treeBuilder;
    private final StringBuilder attrNameSb = new StringBuilder();
    int attrValEnd;
    private static final int MaxAttributes = 512;
    protected @7\u015aCz String tagName;
    private final StringBuilder attrValueSb = new StringBuilder();
    private boolean hasEmptyAttrValue = false;
    int attrNameStart;
    private boolean hasAttrName = false;
    private @7\u015aCz String attrValue;
    boolean selfClosing = false;
    final boolean trackSource;
    int attrValStart;
    @7\u015aCz Attributes attributes;

    public abstract String toString();

    final void appendAttributeValue(char append, int startPos, int endPos) {
        this.ensureAttrValue(startPos, endPos);
        this.attrValueSb.append(append);
    }

    final String name() {
        Validate.isFalse((this.tagName == null || this.tagName.length() == 0 ? 1 : 0) != 0);
        return this.tagName;
    }

    private void ensureAttrValue(int startPos, int endPos) {
        this.hasAttrValue = true;
        if (this.attrValue != null) {
            this.attrValueSb.append(this.attrValue);
            this.attrValue = null;
        }
        if (!this.trackSource) return;
        this.attrValStart = this.attrValStart > -1 ? this.attrValStart : startPos;
        this.attrValEnd = endPos;
    }

    private void trackAttributeRange(String name) {
        if (!this.trackSource) return;
        if (!this.isStartTag()) return;
        Token.StartTag start = this.asStartTag();
        CharacterReader r = start.treeBuilder.reader;
        boolean preserve = start.treeBuilder.settings.preserveAttributeCase();
        assert (this.attributes != null);
        HashMap<String, Range.AttributeRange> attrRanges = (HashMap<String, Range.AttributeRange>)this.attributes.userData("jsoup.attrs");
        if (attrRanges == null) {
            attrRanges = new HashMap<String, Range.AttributeRange>();
            this.attributes.userData("jsoup.attrs", attrRanges);
        }
        if (!preserve) {
            name = Normalizer.lowerCase((String)name);
        }
        if (attrRanges.containsKey(name)) {
            return;
        }
        if (!this.hasAttrValue) {
            this.attrValStart = this.attrValEnd = this.attrNameEnd;
        }
        Range.AttributeRange range = new Range.AttributeRange(new Range(new Range.Position(this.attrNameStart, r.lineNumber(this.attrNameStart), r.columnNumber(this.attrNameStart)), new Range.Position(this.attrNameEnd, r.lineNumber(this.attrNameEnd), r.columnNumber(this.attrNameEnd))), new Range(new Range.Position(this.attrValStart, r.lineNumber(this.attrValStart), r.columnNumber(this.attrValStart)), new Range.Position(this.attrValEnd, r.lineNumber(this.attrValEnd), r.columnNumber(this.attrValEnd))));
        attrRanges.put(name, range);
    }

    final Token.Tag name(String name) {
        this.tagName = name;
        this.normalName = ParseSettings.normalName((String)this.tagName);
        return this;
    }

    final boolean isSelfClosing() {
        return this.selfClosing;
    }

    final void appendAttributeName(String append, int startPos, int endPos) {
        append = append.replace('\u0000', '\ufffd');
        this.ensureAttrName(startPos, endPos);
        if (this.attrNameSb.length() == 0) {
            this.attrName = append;
        } else {
            this.attrNameSb.append(append);
        }
    }

    final void finaliseTag() {
        if (!this.hasAttrName) return;
        this.newAttribute();
    }

    final void newAttribute() {
        if (this.attributes == null) {
            this.attributes = new Attributes();
        }
        if (this.hasAttrName && this.attributes.size() < 512) {
            String name = this.attrNameSb.length() > 0 ? this.attrNameSb.toString() : this.attrName;
            if ((name = name.trim()).length() > 0) {
                String value = this.hasAttrValue ? (this.attrValueSb.length() > 0 ? this.attrValueSb.toString() : this.attrValue) : (this.hasEmptyAttrValue ? "" : null);
                this.attributes.add(name, value);
                this.trackAttributeRange(name);
            }
        }
        this.resetPendingAttr();
    }

    Token.Tag(Token.TokenType type, TreeBuilder treeBuilder) {
        super(type, null);
        this.treeBuilder = treeBuilder;
        this.trackSource = treeBuilder.trackSourceRange;
    }

    final void appendTagName(char append) {
        this.appendTagName(String.valueOf(append));
    }

    final String toStringName() {
        return this.tagName != null ? this.tagName : "[unset]";
    }

    final void appendAttributeName(char append, int startPos, int endPos) {
        this.ensureAttrName(startPos, endPos);
        this.attrNameSb.append(append);
    }

    final void appendTagName(String append) {
        append = append.replace('\u0000', '\ufffd');
        this.tagName = this.tagName == null ? append : this.tagName.concat(append);
        this.normalName = ParseSettings.normalName((String)this.tagName);
    }

    Token.Tag reset() {
        super.reset();
        this.tagName = null;
        this.normalName = null;
        this.selfClosing = false;
        this.attributes = null;
        this.resetPendingAttr();
        return this;
    }

    final boolean hasAttributeIgnoreCase(String key) {
        return this.attributes != null && this.attributes.hasKeyIgnoreCase(key);
    }

    final boolean hasAttribute(String key) {
        return this.attributes != null && this.attributes.hasKey(key);
    }

    final boolean hasAttributes() {
        return this.attributes != null;
    }

    private void ensureAttrName(int startPos, int endPos) {
        this.hasAttrName = true;
        if (this.attrName != null) {
            this.attrNameSb.append(this.attrName);
            this.attrName = null;
        }
        if (!this.trackSource) return;
        this.attrNameStart = this.attrNameStart > -1 ? this.attrNameStart : startPos;
        this.attrNameEnd = endPos;
    }

    final void appendAttributeValue(String append, int startPos, int endPos) {
        this.ensureAttrValue(startPos, endPos);
        if (this.attrValueSb.length() == 0) {
            this.attrValue = append;
        } else {
            this.attrValueSb.append(append);
        }
    }

    final String normalName() {
        return this.normalName;
    }

    final void setEmptyAttributeValue() {
        this.hasEmptyAttrValue = true;
    }

    final void appendAttributeValue(int[] appendCodepoints, int startPos, int endPos) {
        this.ensureAttrValue(startPos, endPos);
        int[] nArray = appendCodepoints;
        int n = nArray.length;
        int n2 = 0;
        while (n2 < n) {
            int codepoint = nArray[n2];
            this.attrValueSb.appendCodePoint(codepoint);
            ++n2;
        }
    }

    private void resetPendingAttr() {
        Token.Tag.reset((StringBuilder)this.attrNameSb);
        this.attrName = null;
        this.hasAttrName = false;
        Token.Tag.reset((StringBuilder)this.attrValueSb);
        this.attrValue = null;
        this.hasEmptyAttrValue = false;
        this.hasAttrValue = false;
        if (!this.trackSource) return;
        this.attrValEnd = -1;
        this.attrValStart = -1;
        this.attrNameEnd = -1;
        this.attrNameStart = -1;
    }
}
