/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$VersionedBuilder
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import org.jetbrains.annotations.ApiStatus;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

@ApiStatus.NonExtendable
public interface OptionState {
    @Deprecated
    public static Builder optionState() {
        return OptionSchema.globalSchema().stateBuilder();
    }

    public <V> @7\u015aCz V value(Option<V> var1);

    public boolean has(Option<?> var1);

    public OptionSchema schema();

    @Deprecated
    public static VersionedBuilder versionedOptionState() {
        return OptionSchema.globalSchema().versionedStateBuilder();
    }

    @Deprecated
    public static OptionState emptyOptionState() {
        return OptionSchema.globalSchema().emptyState();
    }
}
