/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfigImpl$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.regex.Pattern;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfig;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextReplacementConfigImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public interface TextReplacementConfig
extends Buildable<TextReplacementConfig, Builder>,
Examinable {
    @NotNull
    public static Builder builder() {
        return new TextReplacementConfigImpl.Builder();
    }

    @NotNull
    public Pattern matchPattern();
}
