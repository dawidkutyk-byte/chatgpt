/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Merge
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.AbstractComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer;

import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BuildableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.AbstractComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator;

public abstract class TranslatableComponentRenderer<C>
extends AbstractComponentRenderer<C> {
    private static final Set<Style.Merge> MERGES;

    @NotNull
    protected Component renderTranslatableInner(@NotNull TranslatableComponent component, @NotNull C context) {
        @Nullable MessageFormat format = this.translate(component.key(), component.fallback(), context);
        if (format == null) {
            return this.optionallyRenderChildrenAndStyle((Component)component, context);
        }
        List args = component.arguments();
        TextComponent.Builder builder = Component.text();
        this.mergeStyle((Component)component, builder, context);
        if (args.isEmpty()) {
            builder.content(format.format(null, new StringBuffer(), null).toString());
            return this.optionallyRenderChildrenAppendAndBuild(component.children(), builder, context);
        }
        Object[] nulls = new Object[args.size()];
        StringBuffer sb = format.format(nulls, new StringBuffer(), (FieldPosition)null);
        AttributedCharacterIterator it = format.formatToCharacterIterator(nulls);
        while (it.getIndex() < it.getEndIndex()) {
            int end = it.getRunLimit();
            Integer index = (Integer)it.getAttribute(MessageFormat.Field.ARGUMENT);
            if (index != null) {
                TranslationArgument arg = (TranslationArgument)args.get(index);
                builder.append(arg.asComponent());
            } else {
                builder.append((Component)Component.text((String)sb.substring(it.getIndex(), end)));
            }
            it.setIndex(end);
        }
        return this.optionallyRenderChildrenAppendAndBuild(component.children(), builder, context);
    }

    @NotNull
    protected Component renderText(@NotNull TextComponent component, @NotNull C context) {
        TextComponent.Builder builder = Component.text().content(component.content());
        return this.mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
    }

    @NotNull
    protected Component renderScore(@NotNull ScoreComponent component, @NotNull C context) {
        ScoreComponent.Builder builder = Component.score().name(component.name()).objective(component.objective()).value(component.value());
        return this.mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
    }

    static {
        EnumSet<Style.Merge> merges = EnumSet.allOf(Style.Merge.class);
        merges.remove(Style.Merge.EVENTS);
        MERGES = Collections.unmodifiableSet(merges);
    }

    @Nullable
    protected MessageFormat translate(@NotNull String key, @NotNull C context) {
        return null;
    }

    @NotNull
    protected Component renderBlockNbt(@NotNull BlockNBTComponent component, @NotNull C context) {
        BlockNBTComponent.Builder builder = this.nbt(context, Component.blockNBT(), component).pos(component.pos());
        return this.mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
    }

    @NotNull
    protected Component renderKeybind(@NotNull KeybindComponent component, @NotNull C context) {
        KeybindComponent.Builder builder = Component.keybind().keybind(component.keybind());
        return this.mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
    }

    protected <O extends BuildableComponent<O, B>, B extends ComponentBuilder<O, B>> O mergeStyleAndOptionallyDeepRender(Component component, B builder, C context) {
        this.mergeStyle(component, builder, context);
        return this.optionallyRenderChildrenAppendAndBuild(component.children(), builder, context);
    }

    protected <O extends BuildableComponent<O, B>, B extends ComponentBuilder<O, B>> O optionallyRenderChildrenAppendAndBuild(List<Component> children, B builder, C context) {
        if (children.isEmpty()) return (O)builder.build();
        children.forEach(child -> builder.append(this.render((Component)child, context)));
        return (O)builder.build();
    }

    @NotNull
    protected Component renderEntityNbt(@NotNull EntityNBTComponent component, @NotNull C context) {
        EntityNBTComponent.Builder builder = this.nbt(context, Component.entityNBT(), component).selector(component.selector());
        return this.mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
    }

    @Nullable
    protected MessageFormat translate(@NotNull String key, @Nullable String fallback, @NotNull C context) {
        return this.translate(key, context);
    }

    @NotNull
    protected Component renderSelector(@NotNull SelectorComponent component, @NotNull C context) {
        SelectorComponent.Builder builder = Component.selector().pattern(component.pattern());
        return this.mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
    }

    @NotNull
    public static TranslatableComponentRenderer<Locale> usingTranslationSource(@NotNull Translator source) {
        Objects.requireNonNull(source, "source");
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    protected Component renderStorageNbt(@NotNull StorageNBTComponent component, @NotNull C context) {
        StorageNBTComponent.Builder builder = this.nbt(context, Component.storageNBT(), component).storage(component.storage());
        return this.mergeStyleAndOptionallyDeepRender((Component)component, builder, context);
    }

    @NotNull
    protected Component renderTranslatable(@NotNull TranslatableComponent component, @NotNull C context) {
        List arguments = component.arguments();
        List children = component.children();
        if (arguments.isEmpty()) {
            if (children.isEmpty()) return this.renderTranslatableInner(component, context);
        }
        TranslatableComponent.Builder builder = (TranslatableComponent.Builder)component.toBuilder();
        if (!arguments.isEmpty()) {
            ArrayList<TranslationArgument> translatedArguments = new ArrayList<TranslationArgument>(arguments);
            for (int i = 0; i < translatedArguments.size(); ++i) {
                TranslationArgument arg = (TranslationArgument)translatedArguments.get(i);
                if (!(arg.value() instanceof Component) || arg.value() instanceof VirtualComponent) continue;
                translatedArguments.set(i, TranslationArgument.component((ComponentLike)this.render((Component)arg.value(), context)));
            }
            builder.arguments(translatedArguments);
        }
        component = (TranslatableComponent)builder.build();
        return this.renderTranslatableInner(component, context);
    }

    protected Component optionallyRenderChildrenAndStyle(Component component, C context) {
        List children;
        @Nullable HoverEvent hoverEvent = component.hoverEvent();
        if (hoverEvent != null) {
            component = component.hoverEvent((HoverEventSource)hoverEvent.withRenderedValue((ComponentRenderer)this, context));
        }
        if ((children = component.children()).isEmpty()) {
            return component;
        }
        ArrayList rendered = new ArrayList(children.size());
        children.forEach(child -> rendered.add(this.render((Component)child, context)));
        return component.children(rendered);
    }

    protected <B extends ComponentBuilder<?, ?>> void mergeStyle(Component component, B builder, C context) {
        builder.mergeStyle(component, MERGES);
        builder.clickEvent(component.clickEvent());
        @Nullable HoverEvent hoverEvent = component.hoverEvent();
        if (hoverEvent == null) return;
        builder.hoverEvent((HoverEventSource)hoverEvent.withRenderedValue((ComponentRenderer)this, context));
    }

    protected <O extends NBTComponent<O, B>, B extends NBTComponentBuilder<O, B>> B nbt(@NotNull C context, B builder, O oldComponent) {
        builder.nbtPath(oldComponent.nbtPath()).interpret(oldComponent.interpret());
        @Nullable Component separator = oldComponent.separator();
        if (separator == null) return builder;
        builder.separator((ComponentLike)this.render(separator, context));
        return builder;
    }
}
