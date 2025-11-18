/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractNBTComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponentImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.AbstractNBTComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static final class EntityNBTComponentImpl.BuilderImpl
extends AbstractNBTComponentBuilder<EntityNBTComponent, EntityNBTComponent.Builder>
implements EntityNBTComponent.Builder {
    @Nullable
    private String selector;

    @NotNull
    public EntityNBTComponent build() {
        if (this.nbtPath == null) {
            throw new IllegalStateException("nbt path must be set");
        }
        if (this.selector != null) return EntityNBTComponentImpl.create((List)this.children, (Style)this.buildStyle(), (String)this.nbtPath, (boolean)this.interpret, (ComponentLike)this.separator, (String)this.selector);
        throw new IllegalStateException("selector must be set");
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull EntityNBTComponent.Builder selector(@NotNull String selector) {
        this.selector = Objects.requireNonNull(selector, "selector");
        return this;
    }

    EntityNBTComponentImpl.BuilderImpl() {
    }

    EntityNBTComponentImpl.BuilderImpl(@NotNull EntityNBTComponent component) {
        super((NBTComponent)component);
        this.selector = component.selector();
    }
}
