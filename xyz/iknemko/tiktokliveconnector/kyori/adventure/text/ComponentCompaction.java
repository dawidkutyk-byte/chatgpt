/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge$Strategy
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

final class ComponentCompaction {
    @VisibleForTesting
    static final boolean SIMPLIFY_STYLE_FOR_BLANK_COMPONENTS = false;

    private static boolean isBlank(Component component) {
        if (!ComponentCompaction.isText(component)) return false;
        TextComponent textComponent = (TextComponent)component;
        String content = textComponent.content();
        int i = 0;
        while (i < content.length()) {
            char c = content.charAt(i);
            if (c != ' ') {
                return false;
            }
            ++i;
        }
        return true;
    }

    private static boolean isText(Component component) {
        return component instanceof TextComponent && !(component instanceof VirtualComponent);
    }

    @NotNull
    private static Style simplifyStyleForBlank(@NotNull Style style, @Nullable Style parentStyle) {
        return style;
    }

    private ComponentCompaction() {
    }

    static Component compact(@NotNull Component self, @Nullable Style parentStyle) {
        Component child;
        int i;
        TextComponent textComponent;
        int childrenSize;
        List children = self.children();
        Component optimized = self.children(Collections.emptyList());
        if (parentStyle != null) {
            optimized = optimized.style(self.style().unmerge(parentStyle));
        }
        if ((childrenSize = children.size()) == 0) {
            if (!ComponentCompaction.isBlank(optimized)) return optimized;
            optimized = optimized.style(ComponentCompaction.simplifyStyleForBlank(optimized.style(), parentStyle));
            return optimized;
        }
        if (childrenSize == 1 && ComponentCompaction.isText(optimized) && (textComponent = (TextComponent)optimized).content().isEmpty()) {
            Component child2 = (Component)children.get(0);
            return child2.style(child2.style().merge(optimized.style(), Style.Merge.Strategy.IF_ABSENT_ON_TARGET)).compact();
        }
        Style childParentStyle = optimized.style();
        if (parentStyle != null) {
            childParentStyle = childParentStyle.merge(parentStyle, Style.Merge.Strategy.IF_ABSENT_ON_TARGET);
        }
        ArrayList<Object> childrenToAppend = new ArrayList<Object>(children.size());
        for (i = 0; i < children.size(); ++i) {
            TextComponent textComponent2;
            child = (Component)children.get(i);
            if ((child = ComponentCompaction.compact(child, childParentStyle)).children().isEmpty() && ComponentCompaction.isText(child) && (textComponent2 = (TextComponent)child).content().isEmpty()) continue;
            childrenToAppend.add(child);
        }
        if (ComponentCompaction.isText(optimized)) {
            while (!childrenToAppend.isEmpty()) {
                Component child3 = (Component)childrenToAppend.get(0);
                Style childStyle = child3.style().merge(childParentStyle, Style.Merge.Strategy.IF_ABSENT_ON_TARGET);
                if (!ComponentCompaction.isText(child3) || !Objects.equals(childStyle, childParentStyle)) break;
                optimized = ComponentCompaction.joinText((TextComponent)optimized, (TextComponent)child3);
                childrenToAppend.remove(0);
                childrenToAppend.addAll(0, child3.children());
            }
        }
        i = 0;
        while (true) {
            Style neighborStyle;
            Style childStyle;
            if (i + 1 >= childrenToAppend.size()) {
                if (!childrenToAppend.isEmpty()) return optimized.children(childrenToAppend);
                if (!ComponentCompaction.isBlank(optimized)) return optimized.children(childrenToAppend);
                optimized = optimized.style(ComponentCompaction.simplifyStyleForBlank(optimized.style(), parentStyle));
                return optimized.children(childrenToAppend);
            }
            child = (Component)childrenToAppend.get(i);
            Component neighbor = (Component)childrenToAppend.get(i + 1);
            if (child.children().isEmpty() && ComponentCompaction.isText(child) && ComponentCompaction.isText(neighbor) && (childStyle = child.style().merge(childParentStyle, Style.Merge.Strategy.IF_ABSENT_ON_TARGET)).equals(neighborStyle = neighbor.style().merge(childParentStyle, Style.Merge.Strategy.IF_ABSENT_ON_TARGET))) {
                TextComponent combined = ComponentCompaction.joinText((TextComponent)child, (TextComponent)neighbor);
                childrenToAppend.set(i, combined);
                childrenToAppend.remove(i + 1);
                continue;
            }
            ++i;
        }
    }

    private static TextComponent joinText(TextComponent one, TextComponent two) {
        return TextComponentImpl.create((List)two.children(), (Style)one.style(), (String)(one.content() + two.content()));
    }
}
