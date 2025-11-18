/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.MonkeyBars
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.resource;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackCallback;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfoLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackRequest;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.MonkeyBars;

final class ResourcePackRequestImpl
implements ResourcePackRequest {
    private final boolean replace;
    private final boolean required;
    private final List<ResourcePackInfo> packs;
    @Nullable
    private final Component prompt;
    private final ResourcePackCallback cb;

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        ResourcePackRequestImpl that = (ResourcePackRequestImpl)other;
        return this.replace == that.replace && Objects.equals(this.packs, that.packs) && Objects.equals(this.cb, that.cb) && this.required == that.required && Objects.equals(this.prompt, that.prompt);
    }

    ResourcePackRequestImpl(List<ResourcePackInfo> packs, ResourcePackCallback cb, boolean replace, boolean required, @Nullable Component prompt) {
        this.packs = packs;
        this.cb = cb;
        this.replace = replace;
        this.required = required;
        this.prompt = prompt;
    }

    @NotNull
    public ResourcePackCallback callback() {
        return this.cb;
    }

    public boolean replace() {
        return this.replace;
    }

    public int hashCode() {
        return Objects.hash(this.packs, this.cb, this.replace, this.required, this.prompt);
    }

    @Nullable
    public Component prompt() {
        return this.prompt;
    }

    @NotNull
    public ResourcePackRequest replace(boolean replace) {
        if (replace != this.replace) return new ResourcePackRequestImpl(this.packs, this.cb, replace, this.required, this.prompt);
        return this;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"packs", this.packs), ExaminableProperty.of((String)"callback", (Object)this.cb), ExaminableProperty.of((String)"replace", (boolean)this.replace), ExaminableProperty.of((String)"required", (boolean)this.required), ExaminableProperty.of((String)"prompt", (Object)this.prompt));
    }

    public boolean required() {
        return this.required;
    }

    @NotNull
    public List<ResourcePackInfo> packs() {
        return this.packs;
    }

    @NotNull
    public ResourcePackRequest packs(@NotNull Iterable<? extends ResourcePackInfoLike> packs) {
        if (!this.packs.equals(packs)) return new ResourcePackRequestImpl(MonkeyBars.toUnmodifiableList(ResourcePackInfoLike::asResourcePackInfo, packs), this.cb, this.replace, this.required, this.prompt);
        return this;
    }

    @NotNull
    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public ResourcePackRequest callback(@NotNull ResourcePackCallback cb) {
        if (cb != this.cb) return new ResourcePackRequestImpl(this.packs, Objects.requireNonNull(cb, "cb"), this.replace, this.required, this.prompt);
        return this;
    }
}
