/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfigurationImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfigurationImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.function.Function;
import java.util.function.Predicate;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfigurationImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

@ApiStatus.NonExtendable
public interface JoinConfiguration
extends Buildable<JoinConfiguration, Builder>,
Examinable {
    @Nullable
    public Component prefix();

    @NotNull
    public static JoinConfiguration noSeparators() {
        return JoinConfigurationImpl.NULL;
    }

    @Nullable
    public Component separator();

    @NotNull
    public static JoinConfiguration separators(@Nullable ComponentLike separator, @Nullable ComponentLike lastSeparator) {
        if (separator != null) return (JoinConfiguration)JoinConfiguration.builder().separator(separator).lastSeparator(lastSeparator).build();
        if (lastSeparator != null) return (JoinConfiguration)JoinConfiguration.builder().separator(separator).lastSeparator(lastSeparator).build();
        return JoinConfigurationImpl.NULL;
    }

    @Nullable
    public Component lastSeparator();

    @Nullable
    public Component suffix();

    @Nullable
    public Component lastSeparatorIfSerial();

    @NotNull
    public static Builder builder() {
        return new JoinConfigurationImpl.BuilderImpl();
    }

    @NotNull
    public Style parentStyle();

    @NotNull
    public Function<ComponentLike, Component> convertor();

    @NotNull
    public static JoinConfiguration newlines() {
        return JoinConfigurationImpl.STANDARD_NEW_LINES;
    }

    @NotNull
    public Predicate<ComponentLike> predicate();

    @NotNull
    public static JoinConfiguration separator(@Nullable ComponentLike separator) {
        if (separator != null) return (JoinConfiguration)JoinConfiguration.builder().separator(separator).build();
        return JoinConfigurationImpl.NULL;
    }

    @NotNull
    public static JoinConfiguration spaces() {
        return JoinConfigurationImpl.STANDARD_SPACES;
    }

    @NotNull
    public static JoinConfiguration arrayLike() {
        return JoinConfigurationImpl.STANDARD_ARRAY_LIKE;
    }

    @NotNull
    public static JoinConfiguration commas(boolean spaces) {
        return spaces ? JoinConfigurationImpl.STANDARD_COMMA_SPACE_SEPARATED : JoinConfigurationImpl.STANDARD_COMMA_SEPARATED;
    }
}
