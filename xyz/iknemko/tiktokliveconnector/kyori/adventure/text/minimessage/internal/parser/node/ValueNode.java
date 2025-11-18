/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode;

public abstract class ValueNode
extends ElementNode {
    private final String value;

    @NotNull
    public Token token() {
        return Objects.requireNonNull(super.token(), "token is not set");
    }

    @NotNull
    public StringBuilder buildToString(@NotNull StringBuilder sb, int indent) {
        char[] in = this.ident(indent);
        sb.append(in).append(this.valueName()).append("('").append(this.value).append("')\n");
        return sb;
    }

    ValueNode(@Nullable ElementNode parent, @Nullable Token token, @NotNull String sourceMessage, @NotNull String value) {
        super(parent, token, sourceMessage);
        this.value = value;
    }

    @NotNull
    public String value() {
        return this.value;
    }

    abstract String valueName();
}
