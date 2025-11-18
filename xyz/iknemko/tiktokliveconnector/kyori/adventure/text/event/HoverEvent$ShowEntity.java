/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

public static final class HoverEvent.ShowEntity
implements Examinable {
    private final UUID id;
    private final Component name;
    private final Key type;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"type", (Object)this.type), ExaminableProperty.of((String)"id", (Object)this.id), ExaminableProperty.of((String)"name", (Object)this.name));
    }

    @NotNull
    public HoverEvent.ShowEntity type(@NotNull Keyed type) {
        return this.type(Objects.requireNonNull(type, "type").key());
    }

    @NotNull
    public static HoverEvent.ShowEntity showEntity(@NotNull Keyed type, @NotNull UUID id, @Nullable Component name) {
        return new HoverEvent.ShowEntity(Objects.requireNonNull(type, "type").key(), Objects.requireNonNull(id, "id"), name);
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static HoverEvent.ShowEntity of(@NotNull Key type, @NotNull UUID id) {
        return HoverEvent.ShowEntity.of(type, id, null);
    }

    @NotNull
    public HoverEvent.ShowEntity type(@NotNull Key type) {
        if (!Objects.requireNonNull(type, "type").equals(this.type)) return new HoverEvent.ShowEntity(type, this.id, this.name);
        return this;
    }

    @NotNull
    public HoverEvent.ShowEntity id(@NotNull UUID id) {
        if (!Objects.requireNonNull(id).equals(this.id)) return new HoverEvent.ShowEntity(this.type, id, this.name);
        return this;
    }

    @NotNull
    public static HoverEvent.ShowEntity showEntity(@NotNull Keyed type, @NotNull UUID id) {
        return HoverEvent.ShowEntity.showEntity(type, id, null);
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public static HoverEvent.ShowEntity showEntity(@NotNull Key type, @NotNull UUID id, @Nullable Component name) {
        return new HoverEvent.ShowEntity(Objects.requireNonNull(type, "type"), Objects.requireNonNull(id, "id"), name);
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static HoverEvent.ShowEntity of(@NotNull Key type, @NotNull UUID id, @Nullable Component name) {
        return new HoverEvent.ShowEntity(Objects.requireNonNull(type, "type"), Objects.requireNonNull(id, "id"), name);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        HoverEvent.ShowEntity that = (HoverEvent.ShowEntity)other;
        return this.type.equals(that.type) && this.id.equals(that.id) && Objects.equals(this.name, that.name);
    }

    @Deprecated
    @NotNull
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    public static HoverEvent.ShowEntity of(@NotNull Keyed type, @NotNull UUID id) {
        return HoverEvent.ShowEntity.of(type, id, null);
    }

    private HoverEvent.ShowEntity(@NotNull Key type, @NotNull UUID id, @Nullable Component name) {
        this.type = type;
        this.id = id;
        this.name = name;
    }

    @NotNull
    public UUID id() {
        return this.id;
    }

    public int hashCode() {
        int result = this.type.hashCode();
        result = 31 * result + this.id.hashCode();
        result = 31 * result + Objects.hashCode(this.name);
        return result;
    }

    @NotNull
    public Key type() {
        return this.type;
    }

    static /* synthetic */ Component access$100(HoverEvent.ShowEntity x0) {
        return x0.name;
    }

    @NotNull
    public static HoverEvent.ShowEntity showEntity(@NotNull Key type, @NotNull UUID id) {
        return HoverEvent.ShowEntity.showEntity(type, id, null);
    }

    @Nullable
    public Component name() {
        return this.name;
    }

    @NotNull
    public HoverEvent.ShowEntity name(@Nullable Component name) {
        if (!Objects.equals(name, this.name)) return new HoverEvent.ShowEntity(this.type, this.id, name);
        return this;
    }

    @Deprecated
    @ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
    @NotNull
    public static HoverEvent.ShowEntity of(@NotNull Keyed type, @NotNull UUID id, @Nullable Component name) {
        return new HoverEvent.ShowEntity(Objects.requireNonNull(type, "type").key(), Objects.requireNonNull(id, "id"), name);
    }
}
