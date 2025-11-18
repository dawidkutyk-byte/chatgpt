/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState$1
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

public enum TriState {
    NOT_SET,
    FALSE,
    TRUE;


    @Nullable
    public Boolean toBoolean() {
        switch (1.$SwitchMap$net$kyori$adventure$util$TriState[this.ordinal()]) {
            case 1: {
                return Boolean.TRUE;
            }
            case 2: {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    @NotNull
    public static TriState byBoolean(@Nullable Boolean value) {
        return value == null ? NOT_SET : TriState.byBoolean((boolean)value);
    }

    public boolean toBooleanOrElse(boolean other) {
        switch (1.$SwitchMap$net$kyori$adventure$util$TriState[this.ordinal()]) {
            case 1: {
                return true;
            }
            case 2: {
                return false;
            }
        }
        return other;
    }

    @NotNull
    public static TriState byBoolean(boolean value) {
        return value ? TRUE : FALSE;
    }

    public boolean toBooleanOrElseGet(@NotNull BooleanSupplier supplier) {
        switch (1.$SwitchMap$net$kyori$adventure$util$TriState[this.ordinal()]) {
            case 1: {
                return true;
            }
            case 2: {
                return false;
            }
        }
        return supplier.getAsBoolean();
    }
}
