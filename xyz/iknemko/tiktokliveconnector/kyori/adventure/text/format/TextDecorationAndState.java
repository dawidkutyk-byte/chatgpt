/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

@ApiStatus.NonExtendable
public interface TextDecorationAndState
extends StyleBuilderApplicable,
Examinable {
    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"decoration", (Object)this.decoration()), ExaminableProperty.of((String)"state", (Object)this.state()));
    }

    default public void styleApply(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Style.Builder style) {
        style.decoration(this.decoration(), this.state());
    }

    @NotNull
    public TextDecoration decoration();

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state();
}
