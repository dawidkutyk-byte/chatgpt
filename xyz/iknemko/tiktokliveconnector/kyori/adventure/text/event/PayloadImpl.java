/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent$Payload
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import net.kyori.examination.Examinable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.ClickEvent;

abstract class PayloadImpl
implements ClickEvent.Payload {
    public String toString() {
        return Internals.toString((Examinable)this);
    }

    PayloadImpl() {
    }
}
