/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecorationAndState
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecorationAndStateImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.StyleBuilderApplicable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecorationAndState;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecorationAndStateImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Index;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

/*
 * Exception performing whole class analysis ignored.
 */
public enum TextDecoration implements StyleBuilderApplicable,
TextFormat
{
    OBFUSCATED("obfuscated"),
    BOLD("bold"),
    STRIKETHROUGH("strikethrough"),
    UNDERLINED("underlined"),
    ITALIC("italic");

    public static final Index<String, TextDecoration> NAMES;
    private final String name;

    public void styleApply(// Could not load outer class - annotation placement on inner may be incorrect
     @NotNull Style.Builder style) {
        style.decorate(this);
    }

    static {
        NAMES = Index.create(TextDecoration.class, constant -> constant.name);
    }

    @NotNull
    public final TextDecorationAndState withState(boolean state) {
        return new TextDecorationAndStateImpl(this, State.byBoolean((boolean)state));
    }

    @Deprecated
    @NotNull
    public final TextDecorationAndState as(@NotNull State state) {
        return this.withState(state);
    }

    @NotNull
    public final TextDecorationAndState withState(@NotNull TriState state) {
        return new TextDecorationAndStateImpl(this, State.byTriState((TriState)state));
    }

    @NotNull
    public String toString() {
        return this.name;
    }

    private TextDecoration(String name) {
        this.name = name;
    }

    @NotNull
    public final TextDecorationAndState withState(@NotNull State state) {
        return new TextDecorationAndStateImpl(this, state);
    }

    @Deprecated
    @NotNull
    public final TextDecorationAndState as(boolean state) {
        return this.withState(state);
    }
}
