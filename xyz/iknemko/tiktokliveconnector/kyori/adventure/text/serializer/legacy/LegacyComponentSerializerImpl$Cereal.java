/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$Cereal$StyleState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.FlattenerListener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl;

/*
 * Exception performing whole class analysis ignored.
 */
private final class LegacyComponentSerializerImpl.Cereal
implements FlattenerListener {
    @Nullable
    private TextFormat lastWritten;
    private final StyleState style;
    private final StringBuilder sb = new StringBuilder();
    private int head = -1;
    private StyleState[] styles;

    void append(@NotNull TextFormat format) {
        if (this.lastWritten != format) {
            this.sb.append(LegacyComponentSerializerImpl.access$400((LegacyComponentSerializerImpl)LegacyComponentSerializerImpl.this)).append(LegacyComponentSerializerImpl.access$300((LegacyComponentSerializerImpl)LegacyComponentSerializerImpl.this, (TextFormat)format));
        }
        this.lastWritten = format;
    }

    public void component(@NotNull String text) {
        if (text.isEmpty()) return;
        if (this.head < 0) {
            throw new IllegalStateException("No style has been pushed!");
        }
        this.styles[this.head].applyFormat();
        this.sb.append(text);
    }

    public void popStyle(@NotNull Style style) {
        if (this.head-- >= 0) return;
        throw new IllegalStateException("Tried to pop beyond what was pushed!");
    }

    public String toString() {
        return this.sb.toString();
    }

    public void pushStyle(@NotNull Style pushed) {
        StyleState state;
        int idx;
        if ((idx = ++this.head) >= this.styles.length) {
            this.styles = Arrays.copyOf(this.styles, this.styles.length * 2);
        }
        if ((state = this.styles[idx]) == null) {
            this.styles[idx] = state = new StyleState(this);
        }
        if (idx > 0) {
            state.set(this.styles[idx - 1]);
        } else {
            state.clear();
        }
        state.apply(pushed);
    }

    private LegacyComponentSerializerImpl.Cereal() {
        this.style = new StyleState(this);
        this.styles = new StyleState[8];
    }

    static /* synthetic */ TextFormat access$700(LegacyComponentSerializerImpl.Cereal x0) {
        return x0.lastWritten;
    }

    static /* synthetic */ StyleState access$600(LegacyComponentSerializerImpl.Cereal x0) {
        return x0.style;
    }
}
