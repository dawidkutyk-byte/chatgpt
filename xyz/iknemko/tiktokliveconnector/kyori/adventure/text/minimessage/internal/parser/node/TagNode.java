/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.ParsingExceptionImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagPart
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.ParsingExceptionImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ElementNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagPart;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;

public final class TagNode
extends ElementNode {
    private final List<TagPart> parts;
    @Nullable
    private Tag tag = null;

    @NotNull
    public List<TagPart> parts() {
        return this.parts;
    }

    public void tag(@NotNull Tag tag) {
        this.tag = tag;
    }

    @NotNull
    public Tag tag() {
        return Objects.requireNonNull(this.tag, "no tag set");
    }

    @NotNull
    public StringBuilder buildToString(@NotNull StringBuilder sb, int indent) {
        char[] in = this.ident(indent);
        sb.append(in).append("TagNode(");
        int size = this.parts.size();
        for (int i = 0; i < size; ++i) {
            TagPart part = this.parts.get(i);
            sb.append('\'').append(part.value()).append('\'');
            if (i == size - 1) continue;
            sb.append(", ");
        }
        sb.append(") {\n");
        Iterator iterator = this.children().iterator();
        while (true) {
            if (!iterator.hasNext()) {
                sb.append(in).append("}\n");
                return sb;
            }
            ElementNode child = (ElementNode)iterator.next();
            child.buildToString(sb, indent + 1);
        }
    }

    @NotNull
    private static List<TagPart> genParts(@NotNull Token token, @NotNull String sourceMessage, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull TokenParser.TagProvider tagProvider) {
        ArrayList<TagPart> parts = new ArrayList<TagPart>();
        if (token.childTokens() == null) return parts;
        Iterator iterator = token.childTokens().iterator();
        while (iterator.hasNext()) {
            Token childToken = (Token)iterator.next();
            parts.add(new TagPart(sourceMessage, childToken, tagProvider));
        }
        return parts;
    }

    @NotNull
    public Token token() {
        return Objects.requireNonNull(super.token(), "token is not set");
    }

    @NotNull
    public String name() {
        return this.parts.get(0).value();
    }

    public TagNode(@NotNull ElementNode parent, @NotNull Token token, @NotNull String sourceMessage, // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull TokenParser.TagProvider tagProvider) {
        super(parent, token, sourceMessage);
        this.parts = TagNode.genParts(token, sourceMessage, tagProvider);
        if (!this.parts.isEmpty()) return;
        throw new ParsingExceptionImpl("Tag has no parts? " + (Object)((Object)this), this.sourceMessage(), new Token[]{this.token()});
    }
}
