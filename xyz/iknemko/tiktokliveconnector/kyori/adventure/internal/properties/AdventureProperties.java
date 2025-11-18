/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties$Property
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventurePropertiesImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties;

import java.util.function.Function;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventurePropertiesImpl;

@ApiStatus.Internal
public final class AdventureProperties {
    public static final Property<String> DEFAULT_TRANSLATION_LOCALE;
    public static final Property<Boolean> DEBUG;
    public static final Property<Boolean> TEXT_WARN_WHEN_LEGACY_FORMATTING_DETECTED;
    public static final Property<Boolean> SERVICE_LOAD_FAILURES_ARE_FATAL;

    static {
        DEBUG = AdventureProperties.property("debug", Boolean::parseBoolean, false);
        DEFAULT_TRANSLATION_LOCALE = AdventureProperties.property("defaultTranslationLocale", Function.identity(), null);
        SERVICE_LOAD_FAILURES_ARE_FATAL = AdventureProperties.property("serviceLoadFailuresAreFatal", Boolean::parseBoolean, Boolean.TRUE);
        TEXT_WARN_WHEN_LEGACY_FORMATTING_DETECTED = AdventureProperties.property("text.warnWhenLegacyFormattingDetected", Boolean::parseBoolean, Boolean.FALSE);
    }

    @NotNull
    public static <T> Property<T> property(@NotNull String name, @NotNull Function<String, T> parser, @Nullable T defaultValue) {
        return AdventurePropertiesImpl.property((String)name, parser, defaultValue);
    }

    private AdventureProperties() {
    }
}
