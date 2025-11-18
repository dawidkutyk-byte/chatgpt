/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.MonkeyBars
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.resource;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequestImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.MonkeyBars;

static final class ResourcePackRequestImpl.BuilderImpl
implements ResourcePackRequest.Builder {
    private boolean replace;
    private List<ResourcePackInfo> packs;
    @Nullable
    private Component prompt;
    private boolean required;
    private ResourcePackCallback cb;

    @NotNull
    public ResourcePackRequest.Builder packs(@NotNull ResourcePackInfoLike first, ResourcePackInfoLike ... others) {
        this.packs = MonkeyBars.nonEmptyArrayToList(ResourcePackInfoLike::asResourcePackInfo, (Object)first, (Object[])others);
        return this;
    }

    @NotNull
    public ResourcePackRequest.Builder prompt(@Nullable Component prompt) {
        this.prompt = prompt;
        return this;
    }

    @NotNull
    public ResourcePackRequest.Builder replace(boolean replace) {
        this.replace = replace;
        return this;
    }

    ResourcePackRequestImpl.BuilderImpl(@NotNull ResourcePackRequest req) {
        this.packs = req.packs();
        this.cb = req.callback();
        this.replace = req.replace();
        this.required = req.required();
        this.prompt = req.prompt();
    }

    @NotNull
    public ResourcePackRequest.Builder required(boolean required) {
        this.required = required;
        return this;
    }

    @NotNull
    public ResourcePackRequest.Builder packs(@NotNull Iterable<? extends ResourcePackInfoLike> packs) {
        this.packs = MonkeyBars.toUnmodifiableList(ResourcePackInfoLike::asResourcePackInfo, packs);
        return this;
    }

    @NotNull
    public ResourcePackRequest.Builder callback(@NotNull ResourcePackCallback cb) {
        this.cb = Objects.requireNonNull(cb, "cb");
        return this;
    }

    @NotNull
    public ResourcePackRequest build() {
        return new ResourcePackRequestImpl(this.packs, this.cb, this.replace, this.required, this.prompt);
    }

    ResourcePackRequestImpl.BuilderImpl() {
        this.packs = Collections.emptyList();
        this.cb = ResourcePackCallback.noOp();
        this.replace = false;
    }
}
