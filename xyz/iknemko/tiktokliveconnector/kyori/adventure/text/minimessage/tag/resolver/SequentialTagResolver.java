/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

final class SequentialTagResolver
implements TagResolver,
SerializableResolver {
    final TagResolver[] resolvers;

    public boolean has(@NotNull String name) {
        TagResolver[] tagResolverArray = this.resolvers;
        int n = tagResolverArray.length;
        int n2 = 0;
        while (n2 < n) {
            TagResolver resolver = tagResolverArray[n2];
            if (resolver.has(name)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.resolvers);
    }

    public boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SequentialTagResolver)) {
            return false;
        }
        SequentialTagResolver that = (SequentialTagResolver)other;
        return Arrays.equals(this.resolvers, that.resolvers);
    }

    SequentialTagResolver(@NotNull @NotNull TagResolver @NotNull [] resolvers) {
        this.resolvers = resolvers;
    }

    public void handle(@NotNull Component serializable, @NotNull ClaimConsumer consumer) {
        TagResolver[] tagResolverArray = this.resolvers;
        int n = tagResolverArray.length;
        int n2 = 0;
        while (n2 < n) {
            TagResolver resolver = tagResolverArray[n2];
            if (resolver instanceof SerializableResolver) {
                ((SerializableResolver)resolver).handle(serializable, consumer);
            }
            ++n2;
        }
    }

    @Nullable
    public Tag resolve(@NotNull String name, @NotNull ArgumentQueue arguments, @NotNull Context ctx) throws ParsingException {
        ParsingException thrown = null;
        TagResolver[] tagResolverArray = this.resolvers;
        int n = tagResolverArray.length;
        int n2 = 0;
        while (true) {
            if (n2 >= n) {
                if (thrown == null) return null;
                throw thrown;
            }
            TagResolver resolver = tagResolverArray[n2];
            try {
                @Nullable Tag placeholder = resolver.resolve(name, arguments, ctx);
                if (placeholder != null) {
                    return placeholder;
                }
            }
            catch (ParsingException ex) {
                arguments.reset();
                if (thrown == null) {
                    thrown = ex;
                } else {
                    thrown.addSuppressed((Throwable)ex);
                }
            }
            catch (Exception ex) {
                arguments.reset();
                ParsingException err = ctx.newException("Exception thrown while parsing <" + name + ">", (Throwable)ex, arguments);
                if (thrown == null) {
                    thrown = err;
                }
                thrown.addSuppressed((Throwable)err);
            }
            ++n2;
        }
    }
}
