/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.Map;
import org.jetbrains.annotations.ApiStatus;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

@ApiStatus.NonExtendable
public static interface OptionState.Versioned
extends OptionState {
    public OptionState.Versioned at(int var1);

    public Map<Integer, OptionState> childStates();
}
