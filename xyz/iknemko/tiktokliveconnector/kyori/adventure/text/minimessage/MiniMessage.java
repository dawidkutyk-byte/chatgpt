/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageImpl$Instances
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.ComponentSerializer;

public interface MiniMessage
extends ComponentSerializer<Component, Component, String> {
    @NotNull
    public String escapeTags(@NotNull String var1);

    @NotNull
    default public Component deserialize(@NotNull String input, @NotNull Pointered target, TagResolver ... tagResolvers) {
        return this.deserialize(input, target, TagResolver.resolver((TagResolver[])tagResolvers));
    }

    @NotNull
    public String stripTags(@NotNull String var1, @NotNull TagResolver var2);

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String var1, @NotNull Pointered var2, @NotNull TagResolver var3);

    @NotNull
    public Component deserialize(@NotNull String var1, @NotNull Pointered var2);

    @NotNull
    public String escapeTags(@NotNull String var1, @NotNull TagResolver var2);

    @NotNull
    default public Component deserialize(@NotNull String input, TagResolver ... tagResolvers) {
        return this.deserialize(input, TagResolver.resolver((TagResolver[])tagResolvers));
    }

    @NotNull
    public String stripTags(@NotNull String var1);

    @NotNull
    public TagResolver tags();

    @NotNull
    default public String stripTags(@NotNull String input, TagResolver ... tagResolvers) {
        return this.stripTags(input, TagResolver.resolver((TagResolver[])tagResolvers));
    }

    @NotNull
    default public String escapeTags(@NotNull String input, TagResolver ... tagResolvers) {
        return this.escapeTags(input, TagResolver.resolver((TagResolver[])tagResolvers));
    }

    @NotNull
    public Component deserialize(@NotNull String var1, @NotNull TagResolver var2);

    default public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String input, TagResolver ... tagResolvers) {
        return this.deserializeToTree(input, TagResolver.resolver((TagResolver[])tagResolvers));
    }

    public boolean strict();

    @NotNull
    public Component deserialize(@NotNull String var1, @NotNull Pointered var2, @NotNull TagResolver var3);

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String var1, @NotNull Pointered var2);

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String var1, @NotNull TagResolver var2);

    default public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String input, @NotNull Pointered target, TagResolver ... tagResolvers) {
        return this.deserializeToTree(input, target, TagResolver.resolver((TagResolver[])tagResolvers));
    }

    @NotNull
    public static MiniMessage miniMessage() {
        return MiniMessageImpl.Instances.INSTANCE;
    }

    public static Builder builder() {
        return new MiniMessageImpl.BuilderImpl();
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String var1);
}
