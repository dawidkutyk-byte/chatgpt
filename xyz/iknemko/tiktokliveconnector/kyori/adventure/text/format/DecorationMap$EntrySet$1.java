/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration$State
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.DecorationMap;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextDecoration;

class DecorationMap.EntrySet.1
implements Iterator<Map.Entry<TextDecoration, TextDecoration.State>> {
    private final Iterator<TextDecoration> decorations = DecorationMap.access$000().iterator();
    private final Iterator<TextDecoration.State> states;

    @Override
    public boolean hasNext() {
        return this.decorations.hasNext() && this.states.hasNext();
    }

    @Override
    public Map.Entry<TextDecoration, TextDecoration.State> next() {
        if (!this.hasNext()) throw new NoSuchElementException();
        return new AbstractMap.SimpleImmutableEntry<TextDecoration, TextDecoration.State>(this.decorations.next(), this.states.next());
    }

    DecorationMap.EntrySet.1() {
        this.states = EntrySet.this.this$0.values().iterator();
    }
}
