/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Nag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Nag;

final class LegacyFormattingDetected
extends Nag {
    private static final long serialVersionUID = -947793022628807411L;

    LegacyFormattingDetected(Component component) {
        super("Legacy formatting codes have been detected in a component - this is unsupported behaviour. Please refer to the Adventure documentation (https://docs.advntr.dev) for more information. Component: " + component);
    }
}
