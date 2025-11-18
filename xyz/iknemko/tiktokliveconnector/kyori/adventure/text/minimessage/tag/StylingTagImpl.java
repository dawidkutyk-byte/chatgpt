/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.AbstractTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag;

import java.util.Arrays;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.AbstractTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting;

final class StylingTagImpl
extends AbstractTag
implements Inserting {
    private final StyleBuilderApplicable[] styles;

    @NotNull
    public Component value() {
        return Component.text((String)"", (Style)Style.style((StyleBuilderApplicable[])this.styles));
    }

    public int hashCode() {
        return 31 + Arrays.hashCode(this.styles);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"styles", (Object)this.styles));
    }

    StylingTagImpl(StyleBuilderApplicable[] styles) {
        this.styles = styles;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StylingTagImpl)) {
            return false;
        }
        StylingTagImpl that = (StylingTagImpl)((Object)other);
        return Arrays.equals(this.styles, that.styles);
    }
}
