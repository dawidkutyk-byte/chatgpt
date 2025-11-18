/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.AbstractTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag;

import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.AbstractTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.Inserting;

final class InsertingImpl
extends AbstractTag
implements Inserting {
    private final boolean allowsChildren;
    private final Component value;

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InsertingImpl)) {
            return false;
        }
        InsertingImpl that = (InsertingImpl)((Object)other);
        return this.allowsChildren == that.allowsChildren && Objects.equals(this.value, that.value);
    }

    InsertingImpl(boolean allowsChildren, Component value) {
        this.allowsChildren = allowsChildren;
        this.value = value;
    }

    public boolean allowsChildren() {
        return this.allowsChildren;
    }

    public int hashCode() {
        return Objects.hash(this.allowsChildren, this.value);
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"allowsChildren", (boolean)this.allowsChildren), ExaminableProperty.of((String)"value", (Object)this.value));
    }

    @NotNull
    public Component value() {
        return this.value;
    }
}
