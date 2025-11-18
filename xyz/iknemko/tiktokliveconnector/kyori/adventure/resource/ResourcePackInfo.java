/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.resource;

import java.net.URI;
import java.util.UUID;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike;

public interface ResourcePackInfo
extends ResourcePackInfoLike,
Examinable {
    @NotNull
    public URI uri();

    @NotNull
    public UUID id();

    @NotNull
    public static Builder resourcePackInfo() {
        return new ResourcePackInfoImpl.BuilderImpl();
    }

    @NotNull
    public String hash();

    @NotNull
    default public ResourcePackInfo asResourcePackInfo() {
        return this;
    }

    @NotNull
    public static ResourcePackInfo resourcePackInfo(@NotNull UUID id, @NotNull URI uri, @NotNull String hash) {
        return new ResourcePackInfoImpl(id, uri, hash);
    }
}
