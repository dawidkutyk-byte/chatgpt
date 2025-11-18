/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentIteratorFlag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowEntity
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Deque;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentIteratorFlag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;

@FunctionalInterface
@ApiStatus.NonExtendable
public interface ComponentIteratorType {
    public static final ComponentIteratorType BREADTH_FIRST;
    public static final ComponentIteratorType DEPTH_FIRST;

    static {
        DEPTH_FIRST = (component, deque, flags) -> {
            HoverEvent hoverEvent;
            int i;
            if (flags.contains(ComponentIteratorFlag.INCLUDE_TRANSLATABLE_COMPONENT_ARGUMENTS) && component instanceof TranslatableComponent) {
                TranslatableComponent translatable = (TranslatableComponent)component;
                List args = translatable.arguments();
                for (i = args.size() - 1; i >= 0; --i) {
                    deque.addFirst(((ComponentLike)args.get(i)).asComponent());
                }
            }
            if ((hoverEvent = component.hoverEvent()) != null) {
                HoverEvent.Action action = hoverEvent.action();
                if (flags.contains(ComponentIteratorFlag.INCLUDE_HOVER_SHOW_ENTITY_NAME) && action == HoverEvent.Action.SHOW_ENTITY) {
                    deque.addFirst(((HoverEvent.ShowEntity)hoverEvent.value()).name());
                } else if (flags.contains(ComponentIteratorFlag.INCLUDE_HOVER_SHOW_TEXT_COMPONENT) && action == HoverEvent.Action.SHOW_TEXT) {
                    deque.addFirst((Component)hoverEvent.value());
                }
            }
            List children = component.children();
            i = children.size() - 1;
            while (i >= 0) {
                deque.addFirst((Component)children.get(i));
                --i;
            }
        };
        BREADTH_FIRST = (component, deque, flags) -> {
            HoverEvent hoverEvent;
            if (flags.contains(ComponentIteratorFlag.INCLUDE_TRANSLATABLE_COMPONENT_ARGUMENTS) && component instanceof TranslatableComponent) {
                for (TranslationArgument argument : ((TranslatableComponent)component).arguments()) {
                    deque.add(argument.asComponent());
                }
            }
            if ((hoverEvent = component.hoverEvent()) != null) {
                HoverEvent.Action action = hoverEvent.action();
                if (flags.contains(ComponentIteratorFlag.INCLUDE_HOVER_SHOW_ENTITY_NAME) && action == HoverEvent.Action.SHOW_ENTITY) {
                    deque.addLast(((HoverEvent.ShowEntity)hoverEvent.value()).name());
                } else if (flags.contains(ComponentIteratorFlag.INCLUDE_HOVER_SHOW_TEXT_COMPONENT) && action == HoverEvent.Action.SHOW_TEXT) {
                    deque.addLast((Component)hoverEvent.value());
                }
            }
            deque.addAll(component.children());
        };
    }

    public void populate(@NotNull Component var1, @NotNull Deque<Component> var2, @NotNull Set<ComponentIteratorFlag> var3);
}
