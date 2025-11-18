/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.AbstractTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.AbstractTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting;

final class CallbackStylingTagImpl
extends AbstractTag
implements Inserting {
    private final Consumer<Style.Builder> styles;

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"styles", this.styles));
    }

    public int hashCode() {
        return Objects.hash(this.styles);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CallbackStylingTagImpl)) {
            return false;
        }
        CallbackStylingTagImpl that = (CallbackStylingTagImpl)((Object)other);
        return Objects.equals(this.styles, that.styles);
    }

    @NotNull
    public Component value() {
        return Component.text((String)"", (Style)Style.style(this.styles));
    }

    CallbackStylingTagImpl(Consumer<Style.Builder> styles) {
        this.styles = styles;
    }
}
