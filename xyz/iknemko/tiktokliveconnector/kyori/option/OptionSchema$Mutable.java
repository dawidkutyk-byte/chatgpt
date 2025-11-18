/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import org.jetbrains.annotations.ApiStatus;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;

@ApiStatus.NonExtendable
public static interface OptionSchema.Mutable
extends OptionSchema {
    public Option<Integer> intOption(String var1, int var2);

    public Option<Boolean> booleanOption(String var1, boolean var2);

    public <E extends Enum<E>> Option<E> enumOption(String var1, Class<E> var2, @7\u015aCz E var3);

    public Option<Double> doubleOption(String var1, double var2);

    public OptionSchema frozenView();

    public Option<String> stringOption(String var1, @7\u015aCz String var2);
}
