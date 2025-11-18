/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.resource;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public static interface ResourcePackRequest.Builder
extends AbstractBuilder<ResourcePackRequest>,
ResourcePackRequestLike {
    @Contract(value="_ -> this")
    @NotNull
    public ResourcePackRequest.Builder callback(@NotNull ResourcePackCallback var1);

    @NotNull
    @Contract(value="_ -> this")
    public ResourcePackRequest.Builder packs(@NotNull Iterable<? extends ResourcePackInfoLike> var1);

    @NotNull
    @Contract(value="_, _ -> this")
    public ResourcePackRequest.Builder packs(@NotNull ResourcePackInfoLike var1, ResourcePackInfoLike ... var2);

    @Contract(value="_ -> this")
    @NotNull
    public ResourcePackRequest.Builder required(boolean var1);

    @NotNull
    default public ResourcePackRequest asResourcePackRequest() {
        return (ResourcePackRequest)this.build();
    }

    @Contract(value="_ -> this")
    @NotNull
    public ResourcePackRequest.Builder prompt(@Nullable Component var1);

    @Contract(value="_ -> this")
    @NotNull
    public ResourcePackRequest.Builder replace(boolean var1);
}
