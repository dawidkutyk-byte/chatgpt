/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag;

import java.util.Locale;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.NonExtendable
public static interface Tag.Argument {
    @NotNull
    default public OptionalInt asInt() {
        try {
            return OptionalInt.of(Integer.parseInt(this.value()));
        }
        catch (NumberFormatException ex) {
            return OptionalInt.empty();
        }
    }

    @NotNull
    default public OptionalDouble asDouble() {
        try {
            return OptionalDouble.of(Double.parseDouble(this.value()));
        }
        catch (NumberFormatException ex) {
            return OptionalDouble.empty();
        }
    }

    @NotNull
    default public String lowerValue() {
        return this.value().toLowerCase(Locale.ROOT);
    }

    @NotNull
    public String value();

    default public boolean isFalse() {
        return "false".equals(this.value()) || "off".equals(this.value());
    }

    default public boolean isTrue() {
        return "true".equals(this.value()) || "on".equals(this.value());
    }
}
