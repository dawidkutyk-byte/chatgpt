/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

final class StorageNBTComponentImpl
extends NBTComponentImpl<StorageNBTComponent, StorageNBTComponent.Builder>
implements StorageNBTComponent {
    private final Key storage;

    @NotNull
    public Key storage() {
        return this.storage;
    }

    @NotNull
    public StorageNBTComponent separator(@Nullable ComponentLike separator) {
        return StorageNBTComponentImpl.create(this.children, this.style, this.nbtPath, this.interpret, separator, this.storage);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StorageNBTComponent)) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        StorageNBTComponentImpl that = (StorageNBTComponentImpl)((Object)other);
        return Objects.equals(this.storage, that.storage());
    }

    @NotNull
    static StorageNBTComponent create(@NotNull List<? extends ComponentLike> children, @NotNull Style style, String nbtPath, boolean interpret, @Nullable ComponentLike separator, @NotNull Key storage) {
        return new StorageNBTComponentImpl(ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY), Objects.requireNonNull(style, "style"), Objects.requireNonNull(nbtPath, "nbtPath"), interpret, ComponentLike.unbox((ComponentLike)separator), Objects.requireNonNull(storage, "storage"));
    }

    @Nullable
    public Component separator() {
        return this.separator;
    }

    @NotNull
    public StorageNBTComponent storage(@NotNull Key storage) {
        if (!Objects.equals(this.storage, storage)) return StorageNBTComponentImpl.create(this.children, this.style, this.nbtPath, this.interpret, (ComponentLike)this.separator, storage);
        return this;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public StorageNBTComponent children(@NotNull List<? extends ComponentLike> children) {
        return StorageNBTComponentImpl.create(children, this.style, this.nbtPath, this.interpret, (ComponentLike)this.separator, this.storage);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.storage.hashCode();
        return result;
    }

    @NotNull
    public StorageNBTComponent interpret(boolean interpret) {
        if (this.interpret != interpret) return StorageNBTComponentImpl.create(this.children, this.style, this.nbtPath, interpret, (ComponentLike)this.separator, this.storage);
        return this;
    }

    StorageNBTComponentImpl(@NotNull List<Component> children, @NotNull Style style, String nbtPath, boolean interpret, @Nullable Component separator, Key storage) {
        super(children, style, nbtPath, interpret, separator);
        this.storage = storage;
    }

    @NotNull
    public StorageNBTComponent style(@NotNull Style style) {
        return StorageNBTComponentImpl.create(this.children, style, this.nbtPath, this.interpret, (ComponentLike)this.separator, this.storage);
    }

    @NotNull
    public StorageNBTComponent nbtPath(@NotNull String nbtPath) {
        if (!Objects.equals(this.nbtPath, nbtPath)) return StorageNBTComponentImpl.create(this.children, this.style, nbtPath, this.interpret, (ComponentLike)this.separator, this.storage);
        return this;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull StorageNBTComponent.Builder toBuilder() {
        return new BuilderImpl((StorageNBTComponent)this);
    }
}
