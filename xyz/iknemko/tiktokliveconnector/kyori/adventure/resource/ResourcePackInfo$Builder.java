/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.resource;

import java.net.URI;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike;

public static interface ResourcePackInfo.Builder
extends AbstractBuilder<ResourcePackInfo>,
ResourcePackInfoLike {
    @NotNull
    @Contract(value="_ -> this")
    public ResourcePackInfo.Builder uri(@NotNull URI var1);

    @NotNull
    default public ResourcePackInfo asResourcePackInfo() {
        return this.build();
    }

    @NotNull
    default public CompletableFuture<ResourcePackInfo> computeHashAndBuild() {
        return this.computeHashAndBuild(ForkJoinPool.commonPool());
    }

    @Contract(value="_ -> this")
    @NotNull
    public ResourcePackInfo.Builder id(@NotNull UUID var1);

    @NotNull
    public ResourcePackInfo build();

    @NotNull
    @Contract(value="_ -> this")
    public ResourcePackInfo.Builder hash(@NotNull String var1);

    @NotNull
    public CompletableFuture<ResourcePackInfo> computeHashAndBuild(@NotNull Executor var1);
}
