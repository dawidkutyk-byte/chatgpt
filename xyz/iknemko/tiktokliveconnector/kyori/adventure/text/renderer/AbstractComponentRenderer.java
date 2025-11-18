/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.BlockNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.KeybindComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScoreComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.SelectorComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TranslatableComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.VirtualComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;

public abstract class AbstractComponentRenderer<C>
implements ComponentRenderer<C> {
    @NotNull
    protected abstract Component renderEntityNbt(@NotNull EntityNBTComponent var1, @NotNull C var2);

    @NotNull
    protected abstract Component renderText(@NotNull TextComponent var1, @NotNull C var2);

    @NotNull
    protected abstract Component renderBlockNbt(@NotNull BlockNBTComponent var1, @NotNull C var2);

    @NotNull
    protected abstract Component renderTranslatable(@NotNull TranslatableComponent var1, @NotNull C var2);

    @NotNull
    protected abstract Component renderSelector(@NotNull SelectorComponent var1, @NotNull C var2);

    @NotNull
    protected Component renderVirtual(@NotNull VirtualComponent component, @NotNull C context) {
        return component;
    }

    @NotNull
    protected abstract Component renderKeybind(@NotNull KeybindComponent var1, @NotNull C var2);

    @NotNull
    public Component render(@NotNull Component component, @NotNull C context) {
        if (component instanceof VirtualComponent) {
            component = this.renderVirtual((VirtualComponent)component, context);
        }
        if (component instanceof TextComponent) {
            return this.renderText((TextComponent)component, context);
        }
        if (component instanceof TranslatableComponent) {
            return this.renderTranslatable((TranslatableComponent)component, context);
        }
        if (component instanceof KeybindComponent) {
            return this.renderKeybind((KeybindComponent)component, context);
        }
        if (component instanceof ScoreComponent) {
            return this.renderScore((ScoreComponent)component, context);
        }
        if (component instanceof SelectorComponent) {
            return this.renderSelector((SelectorComponent)component, context);
        }
        if (!(component instanceof NBTComponent)) return component;
        if (component instanceof BlockNBTComponent) {
            return this.renderBlockNbt((BlockNBTComponent)component, context);
        }
        if (component instanceof EntityNBTComponent) {
            return this.renderEntityNbt((EntityNBTComponent)component, context);
        }
        if (!(component instanceof StorageNBTComponent)) return component;
        return this.renderStorageNbt((StorageNBTComponent)component, context);
    }

    @NotNull
    protected abstract Component renderStorageNbt(@NotNull StorageNBTComponent var1, @NotNull C var2);

    @NotNull
    protected abstract Component renderScore(@NotNull ScoreComponent var1, @NotNull C var2);
}
