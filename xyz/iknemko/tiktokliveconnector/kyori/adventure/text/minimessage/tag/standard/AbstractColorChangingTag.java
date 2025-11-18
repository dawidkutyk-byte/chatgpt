/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ValueNode
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Modifying
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.AbstractColorChangingTag$TagInfoHolder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.Collections;
import java.util.PrimitiveIterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.ValueNode;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Modifying;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.AbstractColorChangingTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tree.Node;

abstract class AbstractColorChangingTag
implements Modifying,
Examinable {
    private int size = 0;
    private boolean visited;
    private final boolean emitVirtuals;
    private static final ComponentFlattener LENGTH_CALCULATOR = (ComponentFlattener)ComponentFlattener.builder().mapper(TextComponent.class, TextComponent::content).unknownMapper(x -> "_").build();
    private int disableApplyingColorDepth = -1;

    protected abstract TextColor color();

    @NotNull
    public final String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public abstract Stream<? extends ExaminableProperty> examinableProperties();

    public final void visit(@NotNull Node current, int depth) {
        if (this.visited) {
            throw new IllegalStateException("Color changing tag instances cannot be re-used, return a new one for each resolve");
        }
        if (current instanceof ValueNode) {
            String value = ((ValueNode)current).value();
            this.size += value.codePointCount(0, value.length());
        } else {
            if (!(current instanceof TagNode)) return;
            TagNode tag = (TagNode)current;
            if (!(tag.tag() instanceof Inserting)) return;
            LENGTH_CALCULATOR.flatten(((Inserting)tag.tag()).value(), s -> this.size += s.codePointCount(0, s.length()));
        }
    }

    public final void postVisit() {
        this.visited = true;
        this.init();
    }

    public abstract int hashCode();

    protected abstract void advanceColor();

    @Nullable
    static Emitable claimComponent(Component comp) {
        if (!(comp instanceof VirtualComponent)) {
            return null;
        }
        VirtualComponentRenderer holder = ((VirtualComponent)comp).renderer();
        if (holder instanceof TagInfoHolder) return (TagInfoHolder)holder;
        return null;
    }

    AbstractColorChangingTag(Context ctx) {
        this.emitVirtuals = ctx.emitVirtuals();
    }

    public final Component apply(@NotNull Component current, int depth) {
        if (this.emitVirtuals && depth == 0) {
            return Component.virtual(Void.class, (VirtualComponentRenderer)new TagInfoHolder(this.preserveData(), current), (Style)current.style());
        }
        if (this.disableApplyingColorDepth != -1 && depth > this.disableApplyingColorDepth || current.style().color() != null) {
            if (this.disableApplyingColorDepth == -1 || depth < this.disableApplyingColorDepth) {
                this.disableApplyingColorDepth = depth;
            }
            if (!(current instanceof TextComponent)) return current.children(Collections.emptyList());
            this.skipColorForLengthOf(((TextComponent)current).content());
            return current.children(Collections.emptyList());
        }
        this.disableApplyingColorDepth = -1;
        if (current instanceof VirtualComponent) {
            this.skipColorForLengthOf(((VirtualComponent)current).content());
            return current.children(Collections.emptyList());
        }
        if (current instanceof TextComponent && ((TextComponent)current).content().length() > 0) {
            TextComponent textComponent = (TextComponent)current;
            String content = textComponent.content();
            TextComponent.Builder parent = Component.text();
            int[] holder = new int[1];
            PrimitiveIterator.OfInt it = content.codePoints().iterator();
            while (it.hasNext()) {
                holder[0] = it.nextInt();
                TextComponent comp = Component.text((String)new String(holder, 0, 1), (Style)current.style().color(this.color()));
                this.advanceColor();
                parent.append((Component)comp);
            }
            return parent.build();
        }
        if (current instanceof TextComponent) return Component.empty().mergeStyle(current);
        Component ret = current.children(Collections.emptyList()).colorIfAbsent(this.color());
        this.advanceColor();
        return ret;
    }

    public abstract boolean equals(@Nullable Object var1);

    protected abstract void init();

    private void skipColorForLengthOf(String content) {
        int len = content.codePointCount(0, content.length());
        int i = 0;
        while (i < len) {
            this.advanceColor();
            ++i;
        }
    }

    @NotNull
    protected abstract Consumer<TokenEmitter> preserveData();

    protected final int size() {
        return this.size;
    }
}
