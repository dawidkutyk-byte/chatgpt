/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Versioned
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.function.Consumer;
import org.jetbrains.annotations.ApiStatus;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

@ApiStatus.NonExtendable
public static interface OptionState.VersionedBuilder {
    public OptionState.VersionedBuilder version(int var1, Consumer<OptionState.Builder> var2);

    public OptionState.Versioned build();
}
