/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.RemovedDataComponentValueImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import net.kyori.examination.Examinable;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.RemovedDataComponentValueImpl;

public interface DataComponentValue
extends Examinable {
    public static // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull DataComponentValue.Removed removed() {
        return RemovedDataComponentValueImpl.REMOVED;
    }
}
