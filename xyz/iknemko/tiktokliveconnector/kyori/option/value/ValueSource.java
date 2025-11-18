/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSources$EnvironmentVariable
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSources$SystemProperty
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSources;

@FunctionalInterface
public interface ValueSource {
    public static ValueSource environmentVariable() {
        return ValueSource.environmentVariable("");
    }

    public static ValueSource systemProperty() {
        return ValueSource.systemProperty("");
    }

    public <T> @7\u015aCz T value(Option<T> var1);

    public static ValueSource environmentVariable(String prefix) {
        return new ValueSources.EnvironmentVariable(prefix);
    }

    public static ValueSource systemProperty(String prefix) {
        return new ValueSources.SystemProperty(prefix);
    }
}
