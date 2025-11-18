/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackInternals
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.jetbrains.annotations.CheckReturnValue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickCallbackInternals;

@FunctionalInterface
public interface ClickCallback<T extends Audience> {
    public static final Duration DEFAULT_LIFETIME = Duration.ofHours(12L);
    public static final int UNLIMITED_USES = -1;

    @Contract(pure=true)
    @CheckReturnValue
    @NotNull
    default public ClickCallback<T> requiringPermission(@NotNull String permission) {
        return this.requiringPermission(permission, null);
    }

    @CheckReturnValue
    @Contract(pure=true)
    @NotNull
    public static <W extends Audience, N extends W> ClickCallback<W> widen(@NotNull ClickCallback<N> original, @NotNull Class<N> type) {
        return ClickCallback.widen(original, type, null);
    }

    @Contract(pure=true)
    @NotNull
    @CheckReturnValue
    default public ClickCallback<T> requiringPermission(@NotNull String permission, @Nullable Consumer<? super Audience> otherwise) {
        return this.filter(audience -> ((PermissionChecker)audience.getOrDefault(PermissionChecker.POINTER, (Object)ClickCallbackInternals.ALWAYS_FALSE)).test(permission), otherwise);
    }

    @NotNull
    @Contract(pure=true)
    @CheckReturnValue
    default public ClickCallback<T> filter(@NotNull Predicate<T> filter) {
        return this.filter(filter, null);
    }

    public void accept(@NotNull T var1);

    @NotNull
    @CheckReturnValue
    @Contract(pure=true)
    public static <W extends Audience, N extends W> ClickCallback<W> widen(@NotNull ClickCallback<N> original, @NotNull Class<N> type, @Nullable Consumer<? super Audience> otherwise) {
        return audience -> {
            if (type.isInstance(audience)) {
                original.accept((Audience)type.cast(audience));
            } else {
                if (otherwise == null) return;
                otherwise.accept(audience);
            }
        };
    }

    @NotNull
    @Contract(pure=true)
    @CheckReturnValue
    default public ClickCallback<T> filter(@NotNull Predicate<T> filter, @Nullable Consumer<? super Audience> otherwise) {
        return audience -> {
            if (filter.test(audience)) {
                this.accept(audience);
            } else {
                if (otherwise == null) return;
                otherwise.accept(audience);
            }
        };
    }
}
