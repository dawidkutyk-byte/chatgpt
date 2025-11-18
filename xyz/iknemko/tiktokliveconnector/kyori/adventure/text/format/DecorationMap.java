/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap$EntrySet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap$KeySet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap$Values
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

@Unmodifiable
final class DecorationMap
extends AbstractMap<TextDecoration, TextDecoration.State>
implements Examinable {
    static final TextDecoration[] DECORATIONS = TextDecoration.values();
    private static final int MAP_SIZE;
    private volatile Values values = null;
    static final DecorationMap EMPTY;
    private final int bitSet;
    private static final TextDecoration.State[] EMPTY_STATE_ARRAY;
    private volatile EntrySet entrySet = null;
    private static final KeySet KEY_SET;
    private static final TextDecoration.State[] STATES;

    static /* synthetic */ TextDecoration.State[] access$200() {
        return EMPTY_STATE_ARRAY;
    }

    private DecorationMap(int bitSet) {
        this.bitSet = bitSet;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private static int offset(TextDecoration decoration) {
        return 1 << decoration.ordinal() * 2;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Arrays.stream(DECORATIONS).map(decoration -> ExaminableProperty.of((String)decoration.toString(), (Object)this.get(decoration)));
    }

    static /* synthetic */ KeySet access$000() {
        return KEY_SET;
    }

    @Override
    public boolean containsKey(Object key) {
        return key instanceof TextDecoration;
    }

    static {
        STATES = TextDecoration.State.values();
        MAP_SIZE = DECORATIONS.length;
        EMPTY_STATE_ARRAY = new TextDecoration.State[0];
        EMPTY = new DecorationMap(0);
        KEY_SET = new KeySet();
    }

    static DecorationMap merge(Map<TextDecoration, TextDecoration.State> first, Map<TextDecoration, TextDecoration.State> second) {
        int bitSet = 0;
        TextDecoration[] textDecorationArray = DECORATIONS;
        int n = textDecorationArray.length;
        int n2 = 0;
        while (n2 < n) {
            TextDecoration decoration = textDecorationArray[n2];
            bitSet |= first.getOrDefault(decoration, second.getOrDefault(decoration, TextDecoration.State.NOT_SET)).ordinal() * DecorationMap.offset(decoration);
            ++n2;
        }
        return DecorationMap.withBitSet(bitSet);
    }

    @Override
    public TextDecoration.State get(Object o) {
        if (!(o instanceof TextDecoration)) return null;
        return STATES[this.bitSet >> ((TextDecoration)o).ordinal() * 2 & 3];
    }

    static /* synthetic */ int access$100() {
        return MAP_SIZE;
    }

    private static DecorationMap withBitSet(int bitSet) {
        return bitSet == 0 ? EMPTY : new DecorationMap(bitSet);
    }

    @Override
    public int hashCode() {
        return this.bitSet;
    }

    @Override
    @NotNull
    public Set<TextDecoration> keySet() {
        return KEY_SET;
    }

    @NotNull
    public DecorationMap with(@NotNull TextDecoration decoration, // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull TextDecoration.State state) {
        Objects.requireNonNull(state, "state");
        Objects.requireNonNull(decoration, "decoration");
        int offset = DecorationMap.offset(decoration);
        return DecorationMap.withBitSet(this.bitSet & ~(3 * offset) | state.ordinal() * offset);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Collection<TextDecoration.State> values() {
        if (this.values != null) return this.values;
        DecorationMap decorationMap = this;
        synchronized (decorationMap) {
            if (this.values != null) return this.values;
            this.values = new Values(this);
        }
        return this.values;
    }

    @Override
    public int size() {
        return MAP_SIZE;
    }

    static DecorationMap fromMap(Map<TextDecoration, TextDecoration.State> decorationMap) {
        if (decorationMap instanceof DecorationMap) {
            return (DecorationMap)decorationMap;
        }
        int bitSet = 0;
        TextDecoration[] textDecorationArray = DECORATIONS;
        int n = textDecorationArray.length;
        int n2 = 0;
        while (n2 < n) {
            TextDecoration decoration = textDecorationArray[n2];
            bitSet |= decorationMap.getOrDefault(decoration, TextDecoration.State.NOT_SET).ordinal() * DecorationMap.offset(decoration);
            ++n2;
        }
        return DecorationMap.withBitSet(bitSet);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) return false;
        if (other.getClass() == DecorationMap.class) return this.bitSet == ((DecorationMap)other).bitSet;
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @NotNull
    public Set<Map.Entry<TextDecoration, TextDecoration.State>> entrySet() {
        if (this.entrySet != null) return this.entrySet;
        DecorationMap decorationMap = this;
        synchronized (decorationMap) {
            if (this.entrySet != null) return this.entrySet;
            this.entrySet = new EntrySet(this);
        }
        return this.entrySet;
    }
}
