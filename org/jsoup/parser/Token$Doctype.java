/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.Token
 *  org.jsoup.parser.Token$TokenType
 */
package org.jsoup.parser;

import org.jsoup.parser.Token;

static final class Token.Doctype
extends Token {
    final StringBuilder systemIdentifier;
    final StringBuilder publicIdentifier;
    String pubSysKey = null;
    final StringBuilder name = new StringBuilder();
    boolean forceQuirks = false;

    public boolean isForceQuirks() {
        return this.forceQuirks;
    }

    Token reset() {
        super.reset();
        Token.Doctype.reset((StringBuilder)this.name);
        this.pubSysKey = null;
        Token.Doctype.reset((StringBuilder)this.publicIdentifier);
        Token.Doctype.reset((StringBuilder)this.systemIdentifier);
        this.forceQuirks = false;
        return this;
    }

    String getPubSysKey() {
        return this.pubSysKey;
    }

    String getName() {
        return this.name.toString();
    }

    String getPublicIdentifier() {
        return this.publicIdentifier.toString();
    }

    Token.Doctype() {
        super(Token.TokenType.Doctype, null);
        this.publicIdentifier = new StringBuilder();
        this.systemIdentifier = new StringBuilder();
    }

    public String getSystemIdentifier() {
        return this.systemIdentifier.toString();
    }

    public String toString() {
        return "<!doctype " + this.getName() + ">";
    }
}
