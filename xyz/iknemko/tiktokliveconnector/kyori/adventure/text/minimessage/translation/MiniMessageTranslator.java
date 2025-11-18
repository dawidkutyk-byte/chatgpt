/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.ArgumentTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.LocalePointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslatorArgument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslatorTarget
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslationArgumentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.ArgumentTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.LocalePointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslatorArgument;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.translation.MiniMessageTranslatorTarget;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.Translator;

public abstract class MiniMessageTranslator
implements Translator {
    private final MiniMessage miniMessage;

    @Nullable
    public final MessageFormat translate(@NotNull String key, @NotNull Locale locale) {
        return null;
    }

    @Nullable
    protected abstract String getMiniMessageString(@NotNull String var1, @NotNull Locale var2);

    public MiniMessageTranslator() {
        this(MiniMessage.miniMessage());
    }

    public MiniMessageTranslator(@NotNull MiniMessage miniMessage) {
        this.miniMessage = Objects.requireNonNull(miniMessage, "miniMessage");
    }

    @Nullable
    public final Component translate(@NotNull TranslatableComponent component, @NotNull Locale locale) {
        Component resultingComponent;
        String miniMessageString = this.getMiniMessageString(component.key(), locale);
        if (miniMessageString == null) {
            return null;
        }
        LocalePointered target = new LocalePointered(locale);
        List translationArguments = component.arguments();
        if (translationArguments.isEmpty()) {
            resultingComponent = this.miniMessage.deserialize(miniMessageString, (Pointered)target);
        } else {
            TagResolver.Builder tagResolverBuilder = TagResolver.builder();
            ArrayList<Tag> indexedArguments = new ArrayList<Tag>(translationArguments.size());
            boolean targetAlreadyOverridden = false;
            for (TranslationArgument argument : translationArguments) {
                Object value = argument.value();
                if (value instanceof VirtualComponent) {
                    VirtualComponentRenderer renderer = ((VirtualComponent)value).renderer();
                    if (renderer instanceof MiniMessageTranslatorTarget) {
                        if (targetAlreadyOverridden) {
                            throw new IllegalArgumentException("Multiple Argument.target() translation arguments have been set!");
                        }
                        target = ((MiniMessageTranslatorTarget)renderer).pointered();
                        targetAlreadyOverridden = true;
                        continue;
                    }
                    if (renderer instanceof MiniMessageTranslatorArgument) {
                        Tag tag;
                        MiniMessageTranslatorArgument translatorArgument = (MiniMessageTranslatorArgument)renderer;
                        Object data = translatorArgument.data();
                        if (data instanceof TranslationArgumentLike) {
                            tag = Tag.selfClosingInserting((ComponentLike)((TranslationArgumentLike)data));
                            tagResolverBuilder.tag(translatorArgument.name(), tag);
                            indexedArguments.add(tag);
                            continue;
                        }
                        if (data instanceof Tag) {
                            tag = (Tag)data;
                            tagResolverBuilder.tag(translatorArgument.name(), tag);
                            indexedArguments.add(tag);
                            continue;
                        }
                        if (!(data instanceof TagResolver)) throw new IllegalArgumentException("Unknown translator argument type: " + data.getClass());
                        tagResolverBuilder.resolvers(new TagResolver[]{(TagResolver)data});
                    }
                }
                indexedArguments.add(Tag.selfClosingInserting((ComponentLike)argument));
            }
            resultingComponent = this.miniMessage.deserialize(miniMessageString, (Pointered)target, (TagResolver)new ArgumentTag(indexedArguments, tagResolverBuilder.build()));
        }
        Style style = component.style();
        if (style.isEmpty()) return resultingComponent.append(component.children());
        resultingComponent = resultingComponent.applyFallbackStyle(style);
        return resultingComponent.append(component.children());
    }
}
