/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.QuotingOverride
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.QuotingOverride;

public interface TokenEmitter {
    @NotNull
    public TokenEmitter argument(@NotNull String var1, @NotNull QuotingOverride var2);

    @NotNull
    public TokenEmitter pop();

    @NotNull
    public TokenEmitter selfClosingTag(@NotNull String var1);

    @NotNull
    public TokenEmitter tag(@NotNull String var1);

    @NotNull
    default public TokenEmitter arguments(String ... args) {
        String[] stringArray = args;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String arg = stringArray[n2];
            this.argument(arg);
            ++n2;
        }
        return this;
    }

    @NotNull
    public TokenEmitter argument(@NotNull Component var1);

    @NotNull
    public TokenEmitter argument(@NotNull String var1);

    @NotNull
    public TokenEmitter text(@NotNull String var1);
}
