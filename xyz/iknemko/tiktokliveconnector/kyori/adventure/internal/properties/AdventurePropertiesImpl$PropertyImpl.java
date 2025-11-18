/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties$Property
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventurePropertiesImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties;

import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventureProperties;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventurePropertiesImpl;

/*
 * Exception performing whole class analysis ignored.
 */
private static final class AdventurePropertiesImpl.PropertyImpl<T>
implements AdventureProperties.Property<T> {
    @Nullable
    private final T defaultValue;
    private final String name;
    private final Function<String, T> parser;
    private boolean valueCalculated;
    @Nullable
    private T value;

    @Nullable
    public T value() {
        if (this.valueCalculated) return this.value;
        String property = AdventurePropertiesImpl.systemPropertyName((String)this.name);
        String value = System.getProperty(property, AdventurePropertiesImpl.access$000().getProperty(this.name));
        if (value != null) {
            this.value = this.parser.apply(value);
        }
        if (this.value == null) {
            this.value = this.defaultValue;
        }
        this.valueCalculated = true;
        return this.value;
    }

    public boolean equals(@Nullable Object that) {
        return this == that;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    AdventurePropertiesImpl.PropertyImpl(@NotNull String name, @NotNull Function<String, T> parser, @Nullable T defaultValue) {
        this.name = name;
        this.parser = parser;
        this.defaultValue = defaultValue;
    }
}
