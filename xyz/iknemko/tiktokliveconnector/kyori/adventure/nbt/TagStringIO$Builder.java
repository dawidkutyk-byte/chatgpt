/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringIO
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.TagStringIO;

public static class TagStringIO.Builder {
    private boolean acceptLegacy = true;
    private boolean emitLegacy = false;
    private String indent = "";

    static /* synthetic */ boolean access$000(TagStringIO.Builder x0) {
        return x0.acceptLegacy;
    }

    @NotNull
    public TagStringIO.Builder acceptLegacy(boolean legacy) {
        this.acceptLegacy = legacy;
        return this;
    }

    @NotNull
    public TagStringIO.Builder indent(int spaces) {
        if (spaces == 0) {
            this.indent = "";
        } else {
            if (this.indent.length() <= 0 || this.indent.charAt(0) == ' ') {
                if (spaces == this.indent.length()) return this;
            }
            char[] indent = new char[spaces];
            Arrays.fill(indent, ' ');
            this.indent = String.copyValueOf(indent);
        }
        return this;
    }

    @NotNull
    public TagStringIO.Builder emitLegacy(boolean legacy) {
        this.emitLegacy = legacy;
        return this;
    }

    @NotNull
    public TagStringIO.Builder indentTab(int tabs) {
        if (tabs == 0) {
            this.indent = "";
        } else {
            if (this.indent.length() <= 0 || this.indent.charAt(0) == '\t') {
                if (tabs == this.indent.length()) return this;
            }
            char[] indent = new char[tabs];
            Arrays.fill(indent, '\t');
            this.indent = String.copyValueOf(indent);
        }
        return this;
    }

    static /* synthetic */ boolean access$100(TagStringIO.Builder x0) {
        return x0.emitLegacy;
    }

    static /* synthetic */ String access$200(TagStringIO.Builder x0) {
        return x0.indent;
    }

    TagStringIO.Builder() {
    }

    @NotNull
    public TagStringIO build() {
        return new TagStringIO(this, null);
    }
}
