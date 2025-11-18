/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageSerializer$Collector
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage;

import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.MiniMessageSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.ClaimConsumer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.SerializableResolver;

final class MiniMessageSerializer {
    private MiniMessageSerializer() {
    }

    @NotNull
    static String serialize(@NotNull Component component, @NotNull SerializableResolver resolver, boolean strict) {
        StringBuilder sb = new StringBuilder();
        Collector emitter = new Collector(resolver, strict, sb);
        emitter.mark();
        MiniMessageSerializer.visit(component, emitter, resolver, true);
        if (strict) {
            emitter.popAll();
        } else {
            emitter.completeTag();
        }
        return sb.toString();
    }

    private static void visit(@NotNull Component component, Collector emitter, SerializableResolver resolver, boolean lastChild) {
        resolver.handle(component, (ClaimConsumer)emitter);
        Component childSource = emitter.flushClaims(component);
        if (childSource == null) {
            childSource = component;
        }
        Iterator it = childSource.children().iterator();
        while (true) {
            if (!it.hasNext()) {
                if (lastChild) return;
                emitter.popToMark();
                return;
            }
            emitter.mark();
            MiniMessageSerializer.visit((Component)it.next(), emitter, resolver, lastChild && !it.hasNext());
        }
    }
}
