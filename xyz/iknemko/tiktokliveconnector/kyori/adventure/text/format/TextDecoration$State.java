/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$1
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

public static enum TextDecoration.State {
    NOT_SET("not_set"),
    FALSE("false"),
    TRUE("true");

    private final String name;

    public String toString() {
        return this.name;
    }

    @NotNull
    public static TextDecoration.State byTriState(@NotNull TriState flag) {
        Objects.requireNonNull(flag);
        switch (TextDecoration.1.$SwitchMap$net$kyori$adventure$util$TriState[flag.ordinal()]) {
            case 1: {
                return TRUE;
            }
            case 2: {
                return FALSE;
            }
            case 3: {
                return NOT_SET;
            }
        }
        throw new IllegalArgumentException("Unable to turn TriState: " + flag + " into a TextDecoration.State");
    }

    @NotNull
    public static TextDecoration.State byBoolean(boolean flag) {
        return flag ? TRUE : FALSE;
    }

    private TextDecoration.State(String name) {
        this.name = name;
    }

    @NotNull
    public static TextDecoration.State byBoolean(@Nullable Boolean flag) {
        return flag == null ? NOT_SET : TextDecoration.State.byBoolean((boolean)flag);
    }
}
