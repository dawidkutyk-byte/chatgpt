/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$1
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$Cereal
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$Reset
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextColor;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl;

/*
 * Exception performing whole class analysis ignored.
 */
private final class LegacyComponentSerializerImpl.Cereal.StyleState {
    @Nullable
    private TextColor color;
    private final Set<TextDecoration> decorations = EnumSet.noneOf(TextDecoration.class);
    private boolean needsReset;

    void applyFormat() {
        boolean colorChanged;
        boolean bl = colorChanged = this.color != LegacyComponentSerializerImpl.Cereal.access$600((LegacyComponentSerializerImpl.Cereal)Cereal.this).color;
        if (this.needsReset) {
            if (!colorChanged) {
                Cereal.this.append((TextFormat)LegacyComponentSerializerImpl.Reset.INSTANCE);
            }
            this.needsReset = false;
        }
        if (colorChanged || LegacyComponentSerializerImpl.Cereal.access$700((LegacyComponentSerializerImpl.Cereal)Cereal.this) == LegacyComponentSerializerImpl.Reset.INSTANCE) {
            this.applyFullFormat();
            return;
        }
        if (!this.decorations.containsAll(LegacyComponentSerializerImpl.Cereal.access$600((LegacyComponentSerializerImpl.Cereal)Cereal.this).decorations)) {
            this.applyFullFormat();
            return;
        }
        Iterator<TextDecoration> iterator = this.decorations.iterator();
        while (iterator.hasNext()) {
            TextDecoration decoration = iterator.next();
            if (!LegacyComponentSerializerImpl.Cereal.access$600((LegacyComponentSerializerImpl.Cereal)Cereal.this).decorations.add(decoration)) continue;
            Cereal.this.append((TextFormat)decoration);
        }
    }

    public void clear() {
        this.color = null;
        this.decorations.clear();
    }

    LegacyComponentSerializerImpl.Cereal.StyleState() {
    }

    void set(@NotNull LegacyComponentSerializerImpl.Cereal.StyleState that) {
        this.color = that.color;
        this.decorations.clear();
        this.decorations.addAll(that.decorations);
    }

    void apply(@NotNull Style component) {
        TextColor color = component.color();
        if (color != null) {
            this.color = color;
        }
        int i = 0;
        int length = LegacyComponentSerializerImpl.access$500().length;
        while (i < length) {
            TextDecoration decoration = LegacyComponentSerializerImpl.access$500()[i];
            switch (LegacyComponentSerializerImpl.1.$SwitchMap$net$kyori$adventure$text$format$TextDecoration$State[component.decoration(decoration).ordinal()]) {
                case 1: {
                    this.decorations.add(decoration);
                    break;
                }
                case 2: {
                    if (!this.decorations.remove(decoration)) break;
                    this.needsReset = true;
                    break;
                }
            }
            ++i;
        }
    }

    private void applyFullFormat() {
        if (this.color != null) {
            Cereal.this.append((TextFormat)this.color);
        } else {
            Cereal.this.append((TextFormat)LegacyComponentSerializerImpl.Reset.INSTANCE);
        }
        LegacyComponentSerializerImpl.Cereal.access$600((LegacyComponentSerializerImpl.Cereal)Cereal.this).color = this.color;
        Iterator<TextDecoration> iterator = this.decorations.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                LegacyComponentSerializerImpl.Cereal.access$600((LegacyComponentSerializerImpl.Cereal)Cereal.this).decorations.clear();
                LegacyComponentSerializerImpl.Cereal.access$600((LegacyComponentSerializerImpl.Cereal)Cereal.this).decorations.addAll(this.decorations);
                return;
            }
            TextDecoration decoration = iterator.next();
            Cereal.this.append((TextFormat)decoration);
        }
    }
}
