/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessage;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

/*
 * Exception performing whole class analysis ignored.
 */
static final class MiniMessageImpl.BuilderImpl
implements MiniMessage.Builder {
    private UnaryOperator<Component> postProcessor;
    private boolean emitVirtuals = true;
    private TagResolver tagResolver = TagResolver.standard();
    private boolean strict = false;
    private Consumer<String> debug = null;
    private UnaryOperator<String> preProcessor;

    MiniMessageImpl.BuilderImpl(MiniMessageImpl serializer) {
        this();
        this.tagResolver = serializer.parser.tagResolver;
        this.strict = MiniMessageImpl.access$100((MiniMessageImpl)serializer);
        this.debug = MiniMessageImpl.access$200((MiniMessageImpl)serializer);
        this.postProcessor = MiniMessageImpl.access$300((MiniMessageImpl)serializer);
        this.preProcessor = MiniMessageImpl.access$400((MiniMessageImpl)serializer);
    }

    @NotNull
    public MiniMessage build() {
        return new MiniMessageImpl(this.tagResolver, this.strict, this.emitVirtuals, this.debug, this.preProcessor, this.postProcessor);
    }

    @NotNull
    public MiniMessage.Builder postProcessor(@NotNull UnaryOperator<Component> postProcessor) {
        this.postProcessor = Objects.requireNonNull(postProcessor, "postProcessor");
        return this;
    }

    @NotNull
    public MiniMessage.Builder tags(@NotNull TagResolver tags) {
        this.tagResolver = Objects.requireNonNull(tags, "tags");
        return this;
    }

    MiniMessageImpl.BuilderImpl() {
        this.postProcessor = MiniMessageImpl.DEFAULT_COMPACTING_METHOD;
        this.preProcessor = MiniMessageImpl.DEFAULT_NO_OP;
        MiniMessageImpl.BUILDER.accept(this);
    }

    @NotNull
    public MiniMessage.Builder emitVirtuals(boolean emitVirtuals) {
        this.emitVirtuals = emitVirtuals;
        return this;
    }

    @NotNull
    public MiniMessage.Builder debug(@Nullable Consumer<String> debugOutput) {
        this.debug = debugOutput;
        return this;
    }

    @NotNull
    public MiniMessage.Builder strict(boolean strict) {
        this.strict = strict;
        return this;
    }

    @NotNull
    public MiniMessage.Builder preProcessor(@NotNull UnaryOperator<String> preProcessor) {
        this.preProcessor = Objects.requireNonNull(preProcessor, "preProcessor");
        return this;
    }

    @NotNull
    public MiniMessage.Builder editTags(@NotNull Consumer<TagResolver.Builder> adder) {
        Objects.requireNonNull(adder, "adder");
        TagResolver.Builder builder = TagResolver.builder().resolver(this.tagResolver);
        adder.accept(builder);
        this.tagResolver = builder.build();
        return this;
    }
}
