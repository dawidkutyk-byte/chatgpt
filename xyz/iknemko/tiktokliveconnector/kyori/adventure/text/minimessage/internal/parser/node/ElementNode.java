/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TextNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.TokenType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TextNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node;

public class ElementNode
implements Node {
    private final List<ElementNode> children = new ArrayList<ElementNode>();
    private final String sourceMessage;
    @Nullable
    private final Token token;
    @Nullable
    private final ElementNode parent;

    @NotNull
    public List<ElementNode> unsafeChildren() {
        return this.children;
    }

    @NotNull
    public String sourceMessage() {
        return this.sourceMessage;
    }

    char @NotNull [] ident(int indent) {
        char[] c = new char[indent * 2];
        Arrays.fill(c, ' ');
        return c;
    }

    ElementNode(@Nullable ElementNode parent, @Nullable Token token, @NotNull String sourceMessage) {
        this.parent = parent;
        this.token = token;
        this.sourceMessage = sourceMessage;
    }

    @Nullable
    public Token token() {
        return this.token;
    }

    @NotNull
    public String toString() {
        return this.buildToString(new StringBuilder(), 0).toString();
    }

    public void addChild(@NotNull ElementNode childNode) {
        int last = this.children.size() - 1;
        if (!(childNode instanceof TextNode) || this.children.isEmpty() || !(this.children.get(last) instanceof TextNode)) {
            this.children.add(childNode);
        } else {
            TextNode lastNode = (TextNode)this.children.remove(last);
            if (lastNode.token().endIndex() == childNode.token().startIndex()) {
                Token replace = new Token(lastNode.token().startIndex(), childNode.token().endIndex(), TokenType.TEXT);
                this.children.add((ElementNode)new TextNode(this, replace, lastNode.sourceMessage()));
            } else {
                this.children.add((ElementNode)lastNode);
                this.children.add(childNode);
            }
        }
    }

    @Nullable
    public ElementNode parent() {
        return this.parent;
    }

    @NotNull
    public List<ElementNode> children() {
        return Collections.unmodifiableList(this.children);
    }

    @NotNull
    public StringBuilder buildToString(@NotNull StringBuilder sb, int indent) {
        char[] in = this.ident(indent);
        sb.append(in).append("Node {\n");
        Iterator<ElementNode> iterator = this.children.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                sb.append(in).append("}\n");
                return sb;
            }
            ElementNode child = iterator.next();
            child.buildToString(sb, indent + 1);
        }
    }
}
