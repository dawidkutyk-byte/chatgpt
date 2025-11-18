/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag$Argument
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;

final class ArgumentQueueImpl<T extends Tag.Argument>
implements ArgumentQueue {
    final List<T> args;
    private final Context context;
    private int ptr = 0;

    public boolean hasNext() {
        return this.ptr < this.args.size();
    }

    @NotNull
    public T popOr(@NotNull Supplier<String> errorMessage) {
        Objects.requireNonNull(errorMessage, "errorMessage");
        if (this.hasNext()) return (T)((Tag.Argument)this.args.get(this.ptr++));
        throw this.context.newException(Objects.requireNonNull(errorMessage.get(), "errorMessage.get()"), (ArgumentQueue)this);
    }

    public void reset() {
        this.ptr = 0;
    }

    ArgumentQueueImpl(Context context, List<T> args) {
        this.context = context;
        this.args = args;
    }

    public String toString() {
        return this.args.toString();
    }

    @NotNull
    public T popOr(@NotNull String errorMessage) {
        Objects.requireNonNull(errorMessage, "errorMessage");
        if (this.hasNext()) return (T)((Tag.Argument)this.args.get(this.ptr++));
        throw this.context.newException(errorMessage, (ArgumentQueue)this);
    }

    @Nullable
    public T peek() {
        return (T)(this.hasNext() ? (Tag.Argument)this.args.get(this.ptr) : null);
    }

    @NotNull
    public T pop() {
        if (this.hasNext()) return (T)((Tag.Argument)this.args.get(this.ptr++));
        throw this.context.newException("Missing argument for this tag!", (ArgumentQueue)this);
    }
}
