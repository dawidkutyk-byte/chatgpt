/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue$TagSerializable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent$ShowItem
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag$ActionHandler
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.InvalidKeyException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Keyed;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.api.BinaryTagHolder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.HoverEvent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.Context;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.ParsingException;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal.serializer.TokenEmitter;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.standard.HoverTag;

/*
 * Exception performing whole class analysis ignored.
 */
static final class HoverTag.ShowItem
implements HoverTag.ActionHandler<HoverEvent.ShowItem> {
    private static final HoverTag.ShowItem INSTANCE = new HoverTag.ShowItem();

    public // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowItem parse(@NotNull ArgumentQueue args, @NotNull Context ctx) throws ParsingException {
        try {
            Key key = Key.key((String)args.popOr("Show item hover needs at least an item ID").value());
            int count = args.hasNext() ? args.pop().asInt().orElseThrow(() -> ctx.newException("The count argument was not a valid integer")) : 1;
            if (!args.hasNext()) return HoverEvent.ShowItem.showItem((Key)key, (int)count);
            String value = args.peek().value();
            if (value.startsWith("{")) {
                args.pop();
                return HoverTag.ShowItem.legacyShowItem(key, count, value);
            }
            HashMap<Key, BinaryTagHolder> datas = new HashMap<Key, BinaryTagHolder>();
            while (args.hasNext()) {
                Key dataKey = Key.key((String)args.pop().value());
                String dataVal = args.popOr("a value was expected for key " + dataKey).value();
                datas.put(dataKey, BinaryTagHolder.binaryTagHolder((String)dataVal));
            }
            return HoverEvent.ShowItem.showItem((Keyed)key, (int)count, datas);
        }
        catch (NumberFormatException | InvalidKeyException ex) {
            throw ctx.newException("Exception parsing show_item hover", ex, args);
        }
    }

    private static // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull HoverEvent.ShowItem legacyShowItem(Key id, int count, String value) {
        return HoverEvent.ShowItem.showItem((Key)id, (int)count, (BinaryTagHolder)BinaryTagHolder.binaryTagHolder((String)value));
    }

    public void emit(HoverEvent.ShowItem event, TokenEmitter emit) {
        emit.argument(HoverTag.compactAsString((Key)event.item()));
        if (event.count() == 1 && !HoverTag.ShowItem.hasLegacy(event)) {
            if (event.dataComponents().isEmpty()) return;
        }
        emit.argument(Integer.toString(event.count()));
        if (HoverTag.ShowItem.hasLegacy(event)) {
            HoverTag.ShowItem.emitLegacyHover(event, emit);
            return;
        }
        Iterator iterator = event.dataComponentsAs(DataComponentValue.TagSerializable.class).entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            emit.argument(((Key)entry.getKey()).asMinimalString());
            emit.argument(((DataComponentValue.TagSerializable)entry.getValue()).asBinaryTag().string());
        }
    }

    static /* synthetic */ HoverTag.ShowItem access$100() {
        return INSTANCE;
    }

    private HoverTag.ShowItem() {
    }

    static void emitLegacyHover(HoverEvent.ShowItem event, TokenEmitter emit) {
        if (event.nbt() == null) return;
        emit.argument(event.nbt().string());
    }

    static boolean hasLegacy(HoverEvent.ShowItem event) {
        return event.nbt() != null;
    }
}
