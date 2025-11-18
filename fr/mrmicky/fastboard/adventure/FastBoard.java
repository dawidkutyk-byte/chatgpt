/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  fr.mrmicky.fastboard.FastBoardBase
 *  fr.mrmicky.fastboard.FastBoardBase$TeamMode
 *  fr.mrmicky.fastboard.FastReflection
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 */
package fr.mrmicky.fastboard.adventure;

import fr.mrmicky.fastboard.FastBoardBase;
import fr.mrmicky.fastboard.FastReflection;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import org.bukkit.entity.Player;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class FastBoard
extends FastBoardBase<Component> {
    private static final boolean ADVENTURE_SUPPORT = FastReflection.optionalClass((String)"io.papermc.paper.adventure.PaperAdventure").isPresent();
    private static final MethodHandle COMPONENT_METHOD;
    private static final Object EMPTY_COMPONENT;

    protected void sendLineChange(int score) throws Throwable {
        Component line = (Component)this.getLineByScore(score);
        this.sendTeamPacket(score, FastBoardBase.TeamMode.UPDATE, line, null);
    }

    public FastBoard(Player player) {
        super(player);
    }

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            if (ADVENTURE_SUPPORT) {
                Class<?> paperAdventure = Class.forName("io.papermc.paper.adventure.PaperAdventure");
                Method method = paperAdventure.getDeclaredMethod("asVanilla", Component.class);
                COMPONENT_METHOD = lookup.unreflect(method);
                EMPTY_COMPONENT = COMPONENT_METHOD.invoke(Component.empty());
            } else {
                Class craftChatMessageClass = FastReflection.obcClass((String)"util.CraftChatMessage");
                COMPONENT_METHOD = lookup.unreflect(craftChatMessageClass.getMethod("fromString", String.class));
                EMPTY_COMPONENT = Array.get(COMPONENT_METHOD.invoke(""), 0);
            }
        }
        catch (Throwable t) {
            throw new ExceptionInInitializerError(t);
        }
    }

    protected Object toMinecraftComponent(Component component) throws Throwable {
        if (component == null) {
            return EMPTY_COMPONENT;
        }
        if (ADVENTURE_SUPPORT) return COMPONENT_METHOD.invoke(component);
        String legacy = this.serializeLine(component);
        return Array.get(COMPONENT_METHOD.invoke(legacy), 0);
    }

    protected String serializeLine(Component value) {
        return LegacyComponentSerializer.legacySection().serialize(value);
    }

    protected Component emptyLine() {
        return Component.empty();
    }
}
