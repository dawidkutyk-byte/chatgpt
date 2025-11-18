/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ArgumentQueueImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.ParsingExceptionImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagPart
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag$Argument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ArgumentQueueImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.ParsingExceptionImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.Token;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.parser.node.TagPart;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

class ContextImpl
implements Context {
    @Nullable
    private final Pointered target;
    private String message;
    private final TagResolver tagResolver;
    private final MiniMessage miniMessage;
    private final boolean strict;
    private static final Token[] EMPTY_TOKEN_ARRAY = new Token[0];
    private final Consumer<String> debugOutput;
    private final UnaryOperator<Component> postProcessor;
    private final boolean emitVirtuals;
    private final UnaryOperator<String> preProcessor;

    @NotNull
    public Pointered targetOrThrow() {
        if (this.target != null) return this.target;
        throw this.newException("A target is required for this deserialization attempt");
    }

    void message(@NotNull String message) {
        this.message = message;
    }

    @NotNull
    private Component deserializeWithOptionalTarget(@NotNull String message, @NotNull TagResolver tagResolver) {
        if (this.target == null) return this.miniMessage.deserialize(message, tagResolver);
        return this.miniMessage.deserialize(message, this.target, tagResolver);
    }

    public UnaryOperator<Component> postProcessor() {
        return this.postProcessor;
    }

    @NotNull
    public Component deserialize(@NotNull String message, TagResolver ... resolvers) {
        Objects.requireNonNull(message, "message");
        TagResolver combinedResolver = TagResolver.builder().resolver(this.tagResolver).resolvers(resolvers).build();
        return this.deserializeWithOptionalTarget(message, combinedResolver);
    }

    @NotNull
    public Component deserialize(@NotNull String message) {
        return this.deserializeWithOptionalTarget(Objects.requireNonNull(message, "message"), this.tagResolver);
    }

    public UnaryOperator<String> preProcessor() {
        return this.preProcessor;
    }

    @NotNull
    public Component deserialize(@NotNull String message, @NotNull TagResolver resolver) {
        Objects.requireNonNull(message, "message");
        TagResolver combinedResolver = TagResolver.builder().resolver(this.tagResolver).resolver(resolver).build();
        return this.deserializeWithOptionalTarget(message, combinedResolver);
    }

    public boolean strict() {
        return this.strict;
    }

    public boolean emitVirtuals() {
        return this.emitVirtuals;
    }

    public Consumer<String> debugOutput() {
        return this.debugOutput;
    }

    @Nullable
    public Pointered target() {
        return this.target;
    }

    @NotNull
    public ParsingException newException(@NotNull String message, @NotNull ArgumentQueue tags) {
        return new ParsingExceptionImpl(message, this.message, null, false, ContextImpl.tagsToTokens(((ArgumentQueueImpl)tags).args));
    }

    @NotNull
    public <T extends Pointered> T targetAsType(@NotNull Class<T> targetClass) {
        if (!Objects.requireNonNull(targetClass, "targetClass").isInstance(this.target)) throw this.newException("A target with type " + targetClass.getSimpleName() + " is required for this deserialization attempt");
        return (T)((Pointered)targetClass.cast(this.target));
    }

    @NotNull
    public ParsingException newException(@NotNull String message, @Nullable Throwable cause, @NotNull ArgumentQueue tags) {
        return new ParsingExceptionImpl(message, this.message, cause, false, ContextImpl.tagsToTokens(((ArgumentQueueImpl)tags).args));
    }

    @NotNull
    public String message() {
        return this.message;
    }

    @NotNull
    public ParsingException newException(@NotNull String message) {
        return new ParsingExceptionImpl(message, this.message, null, false, EMPTY_TOKEN_ARRAY);
    }

    private static Token[] tagsToTokens(List<? extends Tag.Argument> tags) {
        Token[] tokens = new Token[tags.size()];
        int i = 0;
        int length = tokens.length;
        while (i < length) {
            tokens[i] = ((TagPart)tags.get(i)).token();
            ++i;
        }
        return tokens;
    }

    ContextImpl(boolean strict, boolean emitVirtuals, Consumer<String> debugOutput, String message, MiniMessage miniMessage, @Nullable Pointered target, @Nullable TagResolver extraTags, @Nullable UnaryOperator<String> preProcessor, @Nullable UnaryOperator<Component> postProcessor) {
        this.strict = strict;
        this.emitVirtuals = emitVirtuals;
        this.debugOutput = debugOutput;
        this.message = message;
        this.miniMessage = miniMessage;
        this.target = target;
        this.tagResolver = extraTags == null ? TagResolver.empty() : extraTags;
        this.preProcessor = preProcessor == null ? UnaryOperator.identity() : preProcessor;
        this.postProcessor = postProcessor == null ? UnaryOperator.identity() : postProcessor;
    }

    @NotNull
    public TagResolver extraTags() {
        return this.tagResolver;
    }
}
