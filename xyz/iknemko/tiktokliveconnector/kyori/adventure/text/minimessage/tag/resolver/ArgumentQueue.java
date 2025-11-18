/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver;

import java.util.function.Supplier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ApiStatus.NonExtendable
public interface ArgumentQueue {
    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Tag.Argument pop();

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Tag.Argument popOr(@NotNull String var1);

    public // Could not load outer class - annotation placement on inner may be incorrect
     @Nullable Tag.Argument peek();

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Tag.Argument popOr(@NotNull Supplier<String> var1);

    public boolean hasNext();

    public void reset();
}
