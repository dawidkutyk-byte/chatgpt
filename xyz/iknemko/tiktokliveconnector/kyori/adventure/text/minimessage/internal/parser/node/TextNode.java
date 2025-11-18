/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ValueNode
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenParser;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ValueNode;

public final class TextNode
extends ValueNode {
    public TextNode(@Nullable ElementNode parent, @NotNull Token token, @NotNull String sourceMessage) {
        super(parent, token, sourceMessage, TokenParser.unescape((String)sourceMessage, (int)token.startIndex(), (int)token.endIndex(), TextNode::isEscape));
    }

    String valueName() {
        return "TextNode";
    }

    private static boolean isEscape(int escape) {
        return escape == 60 || escape == 92;
    }
}
