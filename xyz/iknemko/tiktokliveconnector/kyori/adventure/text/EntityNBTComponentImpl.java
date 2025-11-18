/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponentImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentImpl
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
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponentImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

final class EntityNBTComponentImpl
extends NBTComponentImpl<EntityNBTComponent, EntityNBTComponent.Builder>
implements EntityNBTComponent {
    private final String selector;

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.selector.hashCode();
        return result;
    }

    @NotNull
    public EntityNBTComponent interpret(boolean interpret) {
        if (this.interpret != interpret) return EntityNBTComponentImpl.create(this.children, this.style, this.nbtPath, interpret, (ComponentLike)this.separator, this.selector);
        return this;
    }

    EntityNBTComponentImpl(@NotNull List<Component> children, @NotNull Style style, String nbtPath, boolean interpret, @Nullable Component separator, String selector) {
        super(children, style, nbtPath, interpret, separator);
        this.selector = selector;
    }

    @NotNull
    public EntityNBTComponent separator(@Nullable ComponentLike separator) {
        return EntityNBTComponentImpl.create(this.children, this.style, this.nbtPath, this.interpret, separator, this.selector);
    }

    @NotNull
    public EntityNBTComponent selector(@NotNull String selector) {
        if (!Objects.equals(this.selector, selector)) return EntityNBTComponentImpl.create(this.children, this.style, this.nbtPath, this.interpret, (ComponentLike)this.separator, selector);
        return this;
    }

    static EntityNBTComponent create(@NotNull List<? extends ComponentLike> children, @NotNull Style style, String nbtPath, boolean interpret, @Nullable ComponentLike separator, String selector) {
        return new EntityNBTComponentImpl(ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY), Objects.requireNonNull(style, "style"), Objects.requireNonNull(nbtPath, "nbtPath"), interpret, ComponentLike.unbox((ComponentLike)separator), Objects.requireNonNull(selector, "selector"));
    }

    @NotNull
    public EntityNBTComponent nbtPath(@NotNull String nbtPath) {
        if (!Objects.equals(this.nbtPath, nbtPath)) return EntityNBTComponentImpl.create(this.children, this.style, nbtPath, this.interpret, (ComponentLike)this.separator, this.selector);
        return this;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityNBTComponent)) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        EntityNBTComponentImpl that = (EntityNBTComponentImpl)((Object)other);
        return Objects.equals(this.selector, that.selector());
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @Nullable
    public Component separator() {
        return this.separator;
    }

    @NotNull
    public EntityNBTComponent children(@NotNull List<? extends ComponentLike> children) {
        return EntityNBTComponentImpl.create(children, this.style, this.nbtPath, this.interpret, (ComponentLike)this.separator, this.selector);
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull EntityNBTComponent.Builder toBuilder() {
        return new BuilderImpl((EntityNBTComponent)this);
    }

    @NotNull
    public String selector() {
        return this.selector;
    }

    @NotNull
    public EntityNBTComponent style(@NotNull Style style) {
        return EntityNBTComponentImpl.create(this.children, style, this.nbtPath, this.interpret, (ComponentLike)this.separator, this.selector);
    }
}
