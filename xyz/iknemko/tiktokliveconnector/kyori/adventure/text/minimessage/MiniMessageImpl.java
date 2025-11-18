/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ContextImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageParser
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ContextImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageParser;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services;

final class MiniMessageImpl
implements MiniMessage {
    static final UnaryOperator<String> DEFAULT_NO_OP;
    private final UnaryOperator<String> preProcessor;
    private final boolean emitVirtuals;
    static final UnaryOperator<Component> DEFAULT_COMPACTING_METHOD;
    private final UnaryOperator<Component> postProcessor;
    static final Consumer<MiniMessage.Builder> BUILDER;
    @Nullable
    private final Consumer<String> debugOutput;
    final MiniMessageParser parser;
    private final boolean strict;
    private static final Optional<MiniMessage.Provider> SERVICE;

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String input) {
        return this.parser.parseToTree(this.newContext(input, null, null));
    }

    @NotNull
    public String stripTags(@NotNull String input, @NotNull TagResolver tagResolver) {
        return this.parser.stripTokens(this.newContext(input, null, tagResolver));
    }

    static {
        SERVICE = Services.service(MiniMessage.Provider.class);
        BUILDER = SERVICE.map(MiniMessage.Provider::builder).orElseGet(() -> builder -> {});
        DEFAULT_NO_OP = UnaryOperator.identity();
        DEFAULT_COMPACTING_METHOD = Component::compact;
    }

    static /* synthetic */ UnaryOperator access$300(MiniMessageImpl x0) {
        return x0.postProcessor;
    }

    @NotNull
    private ContextImpl newContext(@NotNull String input, @Nullable Pointered target, @Nullable TagResolver resolver) {
        Objects.requireNonNull(input, "input");
        return new ContextImpl(this.strict, this.emitVirtuals, this.debugOutput, input, (MiniMessage)this, target, resolver, this.preProcessor, this.postProcessor);
    }

    @NotNull
    public String escapeTags(@NotNull String input, @NotNull TagResolver tagResolver) {
        return this.parser.escapeTokens(this.newContext(input, null, tagResolver));
    }

    static /* synthetic */ Consumer access$200(MiniMessageImpl x0) {
        return x0.debugOutput;
    }

    static /* synthetic */ Optional access$000() {
        return SERVICE;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String input, @NotNull Pointered target) {
        return this.parser.parseToTree(this.newContext(input, Objects.requireNonNull(target, "target"), null));
    }

    MiniMessageImpl(@NotNull TagResolver resolver, boolean strict, boolean emitVirtuals, @Nullable Consumer<String> debugOutput, @NotNull UnaryOperator<String> preProcessor, @NotNull UnaryOperator<Component> postProcessor) {
        this.parser = new MiniMessageParser(resolver);
        this.strict = strict;
        this.emitVirtuals = emitVirtuals;
        this.debugOutput = debugOutput;
        this.preProcessor = preProcessor;
        this.postProcessor = postProcessor;
    }

    @NotNull
    public TagResolver tags() {
        return this.parser.tagResolver;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String input, @NotNull Pointered target, @NotNull TagResolver tagResolver) {
        return this.parser.parseToTree(this.newContext(input, Objects.requireNonNull(target, "target"), Objects.requireNonNull(tagResolver, "tagResolver")));
    }

    static /* synthetic */ boolean access$100(MiniMessageImpl x0) {
        return x0.strict;
    }

    @NotNull
    public Component deserialize(@NotNull String input, @NotNull TagResolver tagResolver) {
        return this.parser.parseFormat(this.newContext(input, null, Objects.requireNonNull(tagResolver, "tagResolver")));
    }

    public boolean strict() {
        return this.strict;
    }

    @NotNull
    public Component deserialize(@NotNull String input, @NotNull Pointered target, @NotNull TagResolver tagResolver) {
        return this.parser.parseFormat(this.newContext(input, Objects.requireNonNull(target, "target"), Objects.requireNonNull(tagResolver, "tagResolver")));
    }

    @NotNull
    public String escapeTags(@NotNull String input) {
        return this.parser.escapeTokens(this.newContext(input, null, null));
    }

    private SerializableResolver serialResolver(@Nullable TagResolver extraResolver) {
        if (extraResolver == null) {
            if (!(this.parser.tagResolver instanceof SerializableResolver)) return (SerializableResolver)TagResolver.empty();
            return (SerializableResolver)this.parser.tagResolver;
        }
        TagResolver combined = TagResolver.resolver((TagResolver[])new TagResolver[]{this.parser.tagResolver, extraResolver});
        if (!(combined instanceof SerializableResolver)) return (SerializableResolver)TagResolver.empty();
        return (SerializableResolver)combined;
    }

    @NotNull
    public Component deserialize(@NotNull String input, @NotNull Pointered target) {
        return this.parser.parseFormat(this.newContext(input, Objects.requireNonNull(target, "target"), null));
    }

    @NotNull
    public Component deserialize(@NotNull String input) {
        return this.parser.parseFormat(this.newContext(input, null, null));
    }

    @NotNull
    public String serialize(@NotNull Component component) {
        return MiniMessageSerializer.serialize((Component)component, (SerializableResolver)this.serialResolver(null), (boolean)this.strict);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Node.Root deserializeToTree(@NotNull String input, @NotNull TagResolver tagResolver) {
        return this.parser.parseToTree(this.newContext(input, null, Objects.requireNonNull(tagResolver, "tagResolver")));
    }

    @NotNull
    public String stripTags(@NotNull String input) {
        return this.parser.stripTokens(this.newContext(input, null, null));
    }

    static /* synthetic */ UnaryOperator access$400(MiniMessageImpl x0) {
        return x0.preProcessor;
    }
}
