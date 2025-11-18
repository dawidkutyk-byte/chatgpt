/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.resource;

import java.util.List;
import java.util.Objects;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public interface ResourcePackRequest
extends ResourcePackRequestLike,
Examinable {
    @Nullable
    public Component prompt();

    @NotNull
    public static Builder resourcePackRequest(@NotNull ResourcePackRequest existing) {
        return new ResourcePackRequestImpl.BuilderImpl(Objects.requireNonNull(existing, "existing"));
    }

    @NotNull
    public ResourcePackRequest packs(@NotNull Iterable<? extends ResourcePackInfoLike> var1);

    @NotNull
    public ResourcePackCallback callback();

    @NotNull
    public ResourcePackRequest callback(@NotNull ResourcePackCallback var1);

    @NotNull
    public static Builder resourcePackRequest() {
        return new ResourcePackRequestImpl.BuilderImpl();
    }

    @NotNull
    default public ResourcePackRequest asResourcePackRequest() {
        return this;
    }

    @NotNull
    public List<ResourcePackInfo> packs();

    @NotNull
    public static ResourcePackRequest addingRequest(@NotNull ResourcePackInfoLike first, ResourcePackInfoLike ... others) {
        return (ResourcePackRequest)ResourcePackRequest.resourcePackRequest().packs(first, others).replace(false).build();
    }

    public boolean replace();

    public boolean required();

    @NotNull
    public ResourcePackRequest replace(boolean var1);
}
