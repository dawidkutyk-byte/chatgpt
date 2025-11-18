/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.Emitable;

public interface ClaimConsumer {
    public boolean componentClaimed();

    public boolean component(@NotNull Emitable var1);

    public boolean styleClaimed(@NotNull String var1);

    public void style(@NotNull String var1, @NotNull Emitable var2);
}
