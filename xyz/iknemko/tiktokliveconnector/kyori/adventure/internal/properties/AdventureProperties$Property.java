/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.NonExtendable
@ApiStatus.Internal
public static interface AdventureProperties.Property<T> {
    @Nullable
    public T value();
}
