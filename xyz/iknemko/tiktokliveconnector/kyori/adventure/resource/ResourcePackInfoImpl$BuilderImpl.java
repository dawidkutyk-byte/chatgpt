/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.resource;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static final class ResourcePackInfoImpl.BuilderImpl
implements ResourcePackInfo.Builder {
    private String hash;
    private URI uri;
    private UUID id;

    @NotNull
    public ResourcePackInfo.Builder uri(@NotNull URI uri) {
        this.uri = Objects.requireNonNull(uri, "uri");
        if (this.id != null) return this;
        this.id = UUID.nameUUIDFromBytes(uri.toString().getBytes(StandardCharsets.UTF_8));
        return this;
    }

    @NotNull
    public ResourcePackInfo.Builder hash(@NotNull String hash) {
        this.hash = Objects.requireNonNull(hash, "hash");
        return this;
    }

    ResourcePackInfoImpl.BuilderImpl() {
    }

    @NotNull
    public ResourcePackInfo build() {
        return new ResourcePackInfoImpl(this.id, this.uri, this.hash);
    }

    @NotNull
    public CompletableFuture<ResourcePackInfo> computeHashAndBuild(@NotNull Executor executor) {
        return ResourcePackInfoImpl.computeHash((URI)Objects.requireNonNull(this.uri, "uri"), (Executor)executor).thenApply(hash -> {
            this.hash((String)hash);
            return this.build();
        });
    }

    @NotNull
    public ResourcePackInfo.Builder id(@NotNull UUID id) {
        this.id = Objects.requireNonNull(id, "id");
        return this;
    }
}
