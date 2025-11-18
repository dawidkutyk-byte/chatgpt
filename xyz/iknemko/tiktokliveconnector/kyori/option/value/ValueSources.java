/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSources$EnvironmentVariable
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSources$SystemProperty
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSources;

final class ValueSources {
    static final ValueSource ENVIRONMENT = new EnvironmentVariable("");
    static final ValueSource SYSTEM_PROPERTIES = new SystemProperty("");

    private ValueSources() {
    }
}
