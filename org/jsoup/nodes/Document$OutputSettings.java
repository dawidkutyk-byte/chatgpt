/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.DataUtil
 *  org.jsoup.helper.Validate
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.Entities$CoreCharset
 *  org.jsoup.nodes.Entities$EscapeMode
 */
package org.jsoup.nodes;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;

public static class Document.OutputSettings
implements Cloneable {
    private Entities.EscapeMode escapeMode = Entities.EscapeMode.base;
    private boolean prettyPrint = true;
    private int indentAmount = 1;
    private Syntax syntax;
    Entities.CoreCharset coreCharset;
    private final ThreadLocal<CharsetEncoder> encoderThreadLocal = new ThreadLocal();
    private Charset charset;
    private boolean outline = false;
    private int maxPaddingWidth = 30;

    public boolean outline() {
        return this.outline;
    }

    public Document.OutputSettings charset(String charset) {
        this.charset(Charset.forName(charset));
        return this;
    }

    public Document.OutputSettings() {
        this.syntax = Syntax.html;
        this.charset(DataUtil.UTF_8);
    }

    public Document.OutputSettings clone() {
        Document.OutputSettings clone;
        try {
            clone = (Document.OutputSettings)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        clone.charset(this.charset.name());
        clone.escapeMode = Entities.EscapeMode.valueOf((String)this.escapeMode.name());
        return clone;
    }

    public Document.OutputSettings prettyPrint(boolean pretty) {
        this.prettyPrint = pretty;
        return this;
    }

    public Document.OutputSettings syntax(Syntax syntax) {
        this.syntax = syntax;
        if (syntax != Syntax.xml) return this;
        this.escapeMode(Entities.EscapeMode.xhtml);
        return this;
    }

    public Document.OutputSettings outline(boolean outlineMode) {
        this.outline = outlineMode;
        return this;
    }

    public int indentAmount() {
        return this.indentAmount;
    }

    public Document.OutputSettings maxPaddingWidth(int maxPaddingWidth) {
        Validate.isTrue((maxPaddingWidth >= -1 ? 1 : 0) != 0);
        this.maxPaddingWidth = maxPaddingWidth;
        return this;
    }

    CharsetEncoder encoder() {
        CharsetEncoder encoder = this.encoderThreadLocal.get();
        return encoder != null ? encoder : this.prepareEncoder();
    }

    CharsetEncoder prepareEncoder() {
        CharsetEncoder encoder = this.charset.newEncoder();
        this.encoderThreadLocal.set(encoder);
        return encoder;
    }

    public Document.OutputSettings indentAmount(int indentAmount) {
        Validate.isTrue((indentAmount >= 0 ? 1 : 0) != 0);
        this.indentAmount = indentAmount;
        return this;
    }

    public Entities.EscapeMode escapeMode() {
        return this.escapeMode;
    }

    public Syntax syntax() {
        return this.syntax;
    }

    public Document.OutputSettings charset(Charset charset) {
        this.charset = charset;
        this.coreCharset = Entities.CoreCharset.byName((String)charset.name());
        return this;
    }

    public Charset charset() {
        return this.charset;
    }

    public int maxPaddingWidth() {
        return this.maxPaddingWidth;
    }

    public boolean prettyPrint() {
        return this.prettyPrint;
    }

    public Document.OutputSettings escapeMode(Entities.EscapeMode escapeMode) {
        this.escapeMode = escapeMode;
        return this;
    }
}
