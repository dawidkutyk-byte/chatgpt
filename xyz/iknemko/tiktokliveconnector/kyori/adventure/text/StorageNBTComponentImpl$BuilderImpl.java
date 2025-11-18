/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractNBTComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractNBTComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static class StorageNBTComponentImpl.BuilderImpl
extends AbstractNBTComponentBuilder<StorageNBTComponent, StorageNBTComponent.Builder>
implements StorageNBTComponent.Builder {
    @Nullable
    private Key storage;

    StorageNBTComponentImpl.BuilderImpl(@NotNull StorageNBTComponent component) {
        super((NBTComponent)component);
        this.storage = component.storage();
    }

    StorageNBTComponentImpl.BuilderImpl() {
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull StorageNBTComponent.Builder storage(@NotNull Key storage) {
        this.storage = Objects.requireNonNull(storage, "storage");
        return this;
    }

    @NotNull
    public StorageNBTComponent build() {
        if (this.nbtPath == null) {
            throw new IllegalStateException("nbt path must be set");
        }
        if (this.storage != null) return StorageNBTComponentImpl.create((List)this.children, (Style)this.buildStyle(), (String)this.nbtPath, (boolean)this.interpret, (ComponentLike)this.separator, (Key)this.storage);
        throw new IllegalStateException("storage must be set");
    }
}
