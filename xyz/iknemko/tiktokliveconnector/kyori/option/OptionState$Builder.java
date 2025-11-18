/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import org.jetbrains.annotations.ApiStatus;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource;

@ApiStatus.NonExtendable
public static interface OptionState.Builder {
    public OptionState build();

    public <V> OptionState.Builder value(Option<V> var1, @7\u015aCz V var2);

    public OptionState.Builder values(ValueSource var1);

    public OptionState.Builder values(OptionState var1);
}
