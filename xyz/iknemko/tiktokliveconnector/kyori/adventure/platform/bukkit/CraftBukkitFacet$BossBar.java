/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.function.Function;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

/*
 * Exception performing whole class analysis ignored.
 */
static final class CraftBukkitFacet.BossBar
extends BukkitFacet.BossBar {
    private static final Class<?> CLASS_CRAFT_BOSS_BAR = MinecraftReflection.findCraftClass((String)"boss.CraftBossBar");
    private static final MethodHandle CRAFT_BOSS_BAR_HANDLE;
    private static final Class<?> CLASS_BOSS_BAR_ACTION;
    private static final Object BOSS_BAR_ACTION_TITLE;
    private static final MethodHandle NMS_BOSS_BATTLE_SET_NAME;
    private static final MethodHandle NMS_BOSS_BATTLE_SEND_UPDATE;

    static /* synthetic */ MethodHandle access$2000() {
        return NMS_BOSS_BATTLE_SET_NAME;
    }

    private CraftBukkitFacet.BossBar(@NotNull Collection<Player> viewers) {
        super(viewers);
    }

    static /* synthetic */ MethodHandle access$2100() {
        return NMS_BOSS_BATTLE_SEND_UPDATE;
    }

    public void bossBarNameChanged(@NotNull BossBar bar, @NotNull Component oldName, @NotNull Component newName) {
        try {
            Object handle = CRAFT_BOSS_BAR_HANDLE.invoke(this.bar);
            Object text = MinecraftComponentSerializer.get().serialize(newName);
            NMS_BOSS_BATTLE_SET_NAME.invoke(handle, text);
            NMS_BOSS_BATTLE_SEND_UPDATE.invoke(handle, BOSS_BAR_ACTION_TITLE);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to set CraftBossBar name: %s %s", (Object[])new Object[]{this.bar, newName});
            super.bossBarNameChanged(bar, oldName, newName);
        }
    }

    static /* synthetic */ MethodHandle access$1900() {
        return CRAFT_BOSS_BAR_HANDLE;
    }

    static {
        Class<Function> classBossBarAction = null;
        Object bossBarActionTitle = null;
        classBossBarAction = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PacketPlayOutBoss$Action"), MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutBoss$Action"), MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundBossEventPacket$Operation")});
        if (classBossBarAction == null || !classBossBarAction.isEnum()) {
            classBossBarAction = null;
            Class packetClass = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PacketPlayOutBoss"), MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutBoss"), MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundBossEventPacket")});
            Class bossEventClass = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"BossBattle"), MinecraftReflection.findMcClassName((String)"world.BossBattle"), MinecraftReflection.findMcClassName((String)"world.BossEvent")});
            if (packetClass != null && bossEventClass != null) {
                try {
                    String methodName;
                    MethodType methodType = MethodType.methodType(packetClass, bossEventClass);
                    try {
                        packetClass.getDeclaredMethod("createUpdateNamePacket", bossEventClass);
                        methodName = "createUpdateNamePacket";
                    }
                    catch (NoSuchMethodException ignored) {
                        methodName = "c";
                    }
                    MethodHandle factoryMethod = MinecraftReflection.lookup().findStatic(packetClass, methodName, methodType);
                    bossBarActionTitle = LambdaMetafactory.metafactory(MinecraftReflection.lookup(), "apply", MethodType.methodType(Function.class), methodType.generic(), factoryMethod, methodType).getTarget().invoke();
                    classBossBarAction = Function.class;
                }
                catch (Throwable error) {
                    Knob.logError((Throwable)error, (String)"Failed to initialize CraftBossBar constructor", (Object[])new Object[0]);
                }
            }
        } else {
            bossBarActionTitle = MinecraftReflection.findEnum((Class)classBossBarAction, (String)"UPDATE_NAME", (int)3);
        }
        CLASS_BOSS_BAR_ACTION = classBossBarAction;
        BOSS_BAR_ACTION_TITLE = bossBarActionTitle;
        MethodHandle craftBossBarHandle = null;
        MethodHandle nmsBossBattleSetName = null;
        MethodHandle nmsBossBattleSendUpdate = null;
        if (CLASS_CRAFT_BOSS_BAR != null && CraftBukkitFacet.access$500() != null && BOSS_BAR_ACTION_TITLE != null) {
            try {
                Field craftBossBarHandleField = MinecraftReflection.needField(CLASS_CRAFT_BOSS_BAR, (String)"handle");
                craftBossBarHandle = MinecraftReflection.lookup().unreflectGetter(craftBossBarHandleField);
                Class<?> nmsBossBattleType = craftBossBarHandleField.getType();
                for (Field field : nmsBossBattleType.getFields()) {
                    if (!field.getType().equals(CraftBukkitFacet.access$500())) continue;
                    nmsBossBattleSetName = MinecraftReflection.lookup().unreflectSetter(field);
                    break;
                }
                nmsBossBattleSendUpdate = MinecraftReflection.findMethod(nmsBossBattleType, (String[])new String[]{"sendUpdate", "a", "broadcast"}, Void.TYPE, (Class[])new Class[]{CLASS_BOSS_BAR_ACTION});
            }
            catch (Throwable error) {
                Knob.logError((Throwable)error, (String)"Failed to initialize CraftBossBar constructor", (Object[])new Object[0]);
            }
        }
        CRAFT_BOSS_BAR_HANDLE = craftBossBarHandle;
        NMS_BOSS_BATTLE_SET_NAME = nmsBossBattleSetName;
        NMS_BOSS_BATTLE_SEND_UPDATE = nmsBossBattleSendUpdate;
    }

    static /* synthetic */ Class access$1800() {
        return CLASS_CRAFT_BOSS_BAR;
    }
}
