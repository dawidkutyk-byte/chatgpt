/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.PatternReplacementResult
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementRenderer$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge$Strategy
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.PatternReplacementResult;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;

final class TextReplacementRenderer
implements ComponentRenderer<State> {
    static final TextReplacementRenderer INSTANCE = new TextReplacementRenderer();

    @NotNull
    public Component render(@NotNull Component component, @NotNull State state) {
        Component modified;
        ArrayList<TextComponent> children;
        Style oldStyle;
        int oldChildrenSize;
        List oldChildren;
        boolean prevFirstMatch;
        block32: {
            int size;
            ArrayList<TranslationArgument> newArgs;
            List args;
            block33: {
                int replacedUntil;
                Matcher matcher;
                String content;
                block31: {
                    block30: {
                        if (!state.running) {
                            return component;
                        }
                        prevFirstMatch = state.firstMatch;
                        state.firstMatch = true;
                        oldChildren = component.children();
                        oldChildrenSize = oldChildren.size();
                        oldStyle = component.style();
                        children = null;
                        modified = component;
                        if (!(component instanceof TextComponent)) break block30;
                        content = ((TextComponent)component).content();
                        matcher = state.pattern.matcher(content);
                        replacedUntil = 0;
                        break block31;
                    }
                    if (!(modified instanceof TranslatableComponent)) break block32;
                    args = ((TranslatableComponent)modified).arguments();
                    newArgs = null;
                    size = args.size();
                    break block33;
                }
                while (matcher.find()) {
                    PatternReplacementResult result;
                    if ((result = state.continuer.shouldReplace((MatchResult)matcher, ++state.matchCount, state.replaceCount)) == PatternReplacementResult.CONTINUE) continue;
                    if (result == PatternReplacementResult.STOP) {
                        state.running = false;
                        break;
                    }
                    if (matcher.start() == 0) {
                        if (matcher.end() == content.length()) {
                            ComponentLike replacement = (ComponentLike)state.replacement.apply(matcher, (TextComponent.Builder)Component.text().content(matcher.group()).style(component.style()));
                            Object object = modified = replacement == null ? Component.empty() : replacement.asComponent();
                            if (modified.style().hoverEvent() != null) {
                                oldStyle = oldStyle.hoverEvent(null);
                            }
                            modified = modified.style(modified.style().merge(component.style(), Style.Merge.Strategy.IF_ABSENT_ON_TARGET));
                            if (children == null) {
                                children = new ArrayList(oldChildrenSize + modified.children().size());
                                children.addAll(modified.children());
                            }
                        } else {
                            modified = Component.text((String)"", (Style)component.style());
                            ComponentLike child = (ComponentLike)state.replacement.apply(matcher, Component.text().content(matcher.group()));
                            if (child != null) {
                                if (children == null) {
                                    children = new ArrayList(oldChildrenSize + 1);
                                }
                                children.add((TextComponent)child.asComponent());
                            }
                        }
                    } else {
                        if (children == null) {
                            children = new ArrayList(oldChildrenSize + 2);
                        }
                        if (state.firstMatch) {
                            modified = ((TextComponent)component).content(content.substring(0, matcher.start()));
                        } else if (replacedUntil < matcher.start()) {
                            children.add(Component.text((String)content.substring(replacedUntil, matcher.start())));
                        }
                        ComponentLike builder = (ComponentLike)state.replacement.apply(matcher, Component.text().content(matcher.group()));
                        if (builder != null) {
                            children.add((TextComponent)builder.asComponent());
                        }
                    }
                    ++state.replaceCount;
                    state.firstMatch = false;
                    replacedUntil = matcher.end();
                }
                if (replacedUntil < content.length() && replacedUntil > 0) {
                    if (children == null) {
                        children = new ArrayList<TextComponent>(oldChildrenSize);
                    }
                    children.add(Component.text((String)content.substring(replacedUntil)));
                }
                break block32;
            }
            for (int i = 0; i < size; ++i) {
                TranslationArgument replaced;
                TranslationArgument original = (TranslationArgument)args.get(i);
                TranslationArgument translationArgument = replaced = original.value() instanceof Component ? TranslationArgument.component((ComponentLike)this.render((Component)original.value(), state)) : original;
                if (replaced != original && newArgs == null) {
                    newArgs = new ArrayList<TranslationArgument>(size);
                    if (i > 0) {
                        newArgs.addAll(args.subList(0, i));
                    }
                }
                if (newArgs == null) continue;
                newArgs.add(replaced);
            }
            if (newArgs != null) {
                modified = ((TranslatableComponent)modified).arguments(newArgs);
            }
        }
        if (state.running) {
            HoverEvent rendered;
            HoverEvent event;
            if (state.replaceInsideHoverEvents && (event = oldStyle.hoverEvent()) != null && event != (rendered = event.withRenderedValue((ComponentRenderer)this, (Object)state))) {
                modified = modified.style(s -> s.hoverEvent((HoverEventSource)rendered));
            }
            boolean first = true;
            for (int i = 0; i < oldChildrenSize; ++i) {
                Component child = (Component)oldChildren.get(i);
                Component replaced = this.render(child, state);
                if (replaced != child) {
                    if (children == null) {
                        children = new ArrayList(oldChildrenSize);
                    }
                    if (first) {
                        children.addAll(oldChildren.subList(0, i));
                    }
                    first = false;
                }
                if (children == null) continue;
                children.add((TextComponent)replaced);
                first = false;
            }
        } else if (children != null) {
            children.addAll(oldChildren);
        }
        state.firstMatch = prevFirstMatch;
        if (children == null) return modified;
        return modified.children((List)children);
    }

    private TextReplacementRenderer() {
    }
}
