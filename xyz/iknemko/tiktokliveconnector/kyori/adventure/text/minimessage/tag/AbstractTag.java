/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag;

import net.kyori.examination.Examinable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Tag;

abstract class AbstractTag
implements Examinable,
Tag {
    AbstractTag() {
    }

    public final String toString() {
        return Internals.toString((Examinable)this);
    }
}
