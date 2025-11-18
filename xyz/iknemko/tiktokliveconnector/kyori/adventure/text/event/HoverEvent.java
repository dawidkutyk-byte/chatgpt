/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$Action
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowItem
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEventSource;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;

/*
 * Exception performing whole class analysis ignored.
 */
public final class HoverEvent<V>
implements Examinable,
HoverEventSource<V>,
StyleBuilderApplicable {
    private final Action<V> action;
    private final V value;

    @NotNull
    public static HoverEvent<Component> showText(@NotNull Component text) {
        return new HoverEvent<Component>(Action.SHOW_TEXT, text);
    }

    @NotNull
    public static HoverEvent<Component> showText(@NotNull ComponentLike text) {
        return HoverEvent.showText(text.asComponent());
    }

    @Deprecated
    @NotNull
    public static HoverEvent<ShowItem> showItem(@NotNull Keyed item, @Range(from=0L, to=0x7FFFFFFFL) int count, @Nullable BinaryTagHolder nbt) {
        return HoverEvent.showItem(ShowItem.showItem((Keyed)item, (int)count, (BinaryTagHolder)nbt));
    }

    @NotNull
    public static HoverEvent<ShowItem> showItem(@NotNull ShowItem item) {
        return new HoverEvent<ShowItem>(Action.SHOW_ITEM, item);
    }

    @NotNull
    public static HoverEvent<ShowEntity> showEntity(@NotNull Key type, @NotNull UUID id, @Nullable Component name) {
        return HoverEvent.showEntity(ShowEntity.of((Key)type, (UUID)id, (Component)name));
    }

    @Deprecated
    @NotNull
    public static HoverEvent<ShowItem> showItem(@NotNull Key item, @Range(from=0L, to=0x7FFFFFFFL) int count, @Nullable BinaryTagHolder nbt) {
        return HoverEvent.showItem(ShowItem.showItem((Key)item, (int)count, (BinaryTagHolder)nbt));
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"action", this.action), ExaminableProperty.of((String)"value", this.value));
    }

    @NotNull
    public HoverEvent<V> asHoverEvent() {
        return this;
    }

    @NotNull
    public static HoverEvent<ShowItem> showItem(@NotNull Keyed item, @Range(from=0L, to=0x7FFFFFFFL) int count, @NotNull Map<Key, ? extends DataComponentValue> dataComponents) {
        return HoverEvent.showItem(ShowItem.showItem((Keyed)item, (int)count, dataComponents));
    }

    @NotNull
    public V value() {
        return this.value;
    }

    public int hashCode() {
        int result = this.action.hashCode();
        result = 31 * result + this.value.hashCode();
        return result;
    }

    @NotNull
    public Action<V> action() {
        return this.action;
    }

    @NotNull
    public static HoverEvent<ShowEntity> showEntity(@NotNull Keyed type, @NotNull UUID id) {
        return HoverEvent.showEntity(type, id, null);
    }

    @Deprecated
    @NotNull
    public static HoverEvent<String> showAchievement(@NotNull String value) {
        return new HoverEvent<String>(Action.SHOW_ACHIEVEMENT, value);
    }

    @NotNull
    public static HoverEvent<ShowItem> showItem(@NotNull Keyed item, @Range(from=0L, to=0x7FFFFFFFL) int count) {
        return HoverEvent.showItem(item, count, Collections.emptyMap());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        HoverEvent that = (HoverEvent)other;
        return this.action == that.action && this.value.equals(that.value);
    }

    private HoverEvent(@NotNull Action<V> action, @NotNull V value) {
        this.action = Objects.requireNonNull(action, "action");
        this.value = Objects.requireNonNull(value, "value");
    }

    @NotNull
    public HoverEvent<V> value(@NotNull V value) {
        return new HoverEvent<V>(this.action, value);
    }

    @NotNull
    public HoverEvent<V> asHoverEvent(@NotNull UnaryOperator<V> op) {
        if (op != UnaryOperator.identity()) return new HoverEvent<V>(this.action, op.apply(this.value));
        return this;
    }

    public void styleApply(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Style.Builder style) {
        style.hoverEvent((HoverEventSource)this);
    }

    @NotNull
    public static <V> HoverEvent<V> hoverEvent(@NotNull Action<V> action, @NotNull V value) {
        return new HoverEvent<V>(action, value);
    }

    @NotNull
    public static HoverEvent<ShowEntity> showEntity(@NotNull Key type, @NotNull UUID id) {
        return HoverEvent.showEntity(type, id, null);
    }

    @NotNull
    public static HoverEvent<ShowEntity> showEntity(@NotNull ShowEntity entity) {
        return new HoverEvent<ShowEntity>(Action.SHOW_ENTITY, entity);
    }

    @NotNull
    public static HoverEvent<ShowEntity> showEntity(@NotNull Keyed type, @NotNull UUID id, @Nullable Component name) {
        return HoverEvent.showEntity(ShowEntity.of((Keyed)type, (UUID)id, (Component)name));
    }

    @NotNull
    public <C> HoverEvent<V> withRenderedValue(@NotNull ComponentRenderer<C> renderer, @NotNull C context) {
        V oldValue = this.value;
        Object newValue = Action.access$000(this.action).render(renderer, context, oldValue);
        if (newValue == oldValue) return this;
        return new HoverEvent<Object>(this.action, newValue);
    }

    @NotNull
    public static HoverEvent<ShowItem> showItem(@NotNull Key item, @Range(from=0L, to=0x7FFFFFFFL) int count) {
        return HoverEvent.showItem((Keyed)item, count, Collections.emptyMap());
    }
}
