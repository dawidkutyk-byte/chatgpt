/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  net.kyori.examination.string.StringExaminer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.string.StringExaminer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion="5.0.0")
@Debug.Renderer(text="this.debuggerString()", childrenArray="this.children().toArray()", hasChildren="!this.children().isEmpty()")
public abstract class AbstractComponent
implements Component {
    protected final Style style;
    protected final List<Component> children;

    private String debuggerString() {
        Stream<ExaminableProperty> examinablePropertiesWithoutChildren = this.examinableProperties().filter(property -> !property.name().equals("children"));
        return (String)StringExaminer.simpleEscaping().examine(this.examinableName(), examinablePropertiesWithoutChildren);
    }

    public int hashCode() {
        int result = this.children.hashCode();
        result = 31 * result + this.style.hashCode();
        return result;
    }

    protected AbstractComponent(@NotNull List<? extends ComponentLike> children, @NotNull Style style) {
        this.children = ComponentLike.asComponents(children, (Predicate)IS_NOT_EMPTY);
        this.style = style;
    }

    @NotNull
    public final List<Component> children() {
        return this.children;
    }

    @NotNull
    public final Style style() {
        return this.style;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AbstractComponent)) {
            return false;
        }
        AbstractComponent that = (AbstractComponent)other;
        return Objects.equals(this.children, that.children) && Objects.equals(this.style, that.style);
    }

    public abstract String toString();
}
