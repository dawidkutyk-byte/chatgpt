/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action$Renderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowItem
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;

public static final class HoverEvent.Action<V> {
    private final Renderer<V> renderer;
    public static final HoverEvent.Action<HoverEvent.ShowEntity> SHOW_ENTITY;
    private final Class<V> type;
    public static final HoverEvent.Action<HoverEvent.ShowItem> SHOW_ITEM;
    private final boolean readable;
    @Deprecated
    public static final HoverEvent.Action<String> SHOW_ACHIEVEMENT;
    private final String name;
    public static final Index<String, HoverEvent.Action<?>> NAMES;
    public static final HoverEvent.Action<Component> SHOW_TEXT;

    static /* synthetic */ Renderer access$000(HoverEvent.Action x0) {
        return x0.renderer;
    }

    @NotNull
    public String toString() {
        return this.name;
    }

    HoverEvent.Action(String name, Class<V> type, boolean readable, Renderer<V> renderer) {
        this.name = name;
        this.type = type;
        this.readable = readable;
        this.renderer = renderer;
    }

    static {
        SHOW_TEXT = new HoverEvent.Action<Component>("show_text", Component.class, true, (Renderer<Component>)new /* Unavailable Anonymous Inner Class!! */);
        SHOW_ITEM = new HoverEvent.Action<HoverEvent.ShowItem>("show_item", HoverEvent.ShowItem.class, true, (Renderer<HoverEvent.ShowItem>)new /* Unavailable Anonymous Inner Class!! */);
        SHOW_ENTITY = new HoverEvent.Action<HoverEvent.ShowEntity>("show_entity", HoverEvent.ShowEntity.class, true, (Renderer<HoverEvent.ShowEntity>)new /* Unavailable Anonymous Inner Class!! */);
        SHOW_ACHIEVEMENT = new HoverEvent.Action<String>("show_achievement", String.class, true, (Renderer<String>)new /* Unavailable Anonymous Inner Class!! */);
        NAMES = Index.create(constant -> constant.name, (Object[])new HoverEvent.Action[]{SHOW_TEXT, SHOW_ITEM, SHOW_ENTITY, SHOW_ACHIEVEMENT});
    }

    @NotNull
    public Class<V> type() {
        return this.type;
    }

    public boolean readable() {
        return this.readable;
    }
}
