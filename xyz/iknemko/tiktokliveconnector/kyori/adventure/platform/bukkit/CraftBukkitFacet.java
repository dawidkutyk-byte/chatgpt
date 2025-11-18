/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.UUID;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

class CraftBukkitFacet<V extends CommandSender>
extends FacetBase<V> {
    @Nullable
    static final MethodHandle CRAFT_PLAYER_GET_HANDLE;
    @Nullable
    private static final Object TITLE_ACTION_RESET;
    private static final MethodHandle CONSTRUCTOR_TITLE_MESSAGE;
    @Nullable
    private static final MethodHandle ENTITY_PLAYER_GET_CONNECTION;
    @Nullable
    private static final Object TITLE_ACTION_TITLE;
    @Nullable
    private static final Object MESSAGE_TYPE_CHAT;
    @Nullable
    private static final Object TITLE_ACTION_ACTIONBAR;
    @Nullable
    private static final MethodHandle LEGACY_CHAT_PACKET_CONSTRUCTOR;
    @Nullable
    private static final Class<?> CLASS_TITLE_ACTION;
    @Nullable
    private static final Class<?> CLASS_MESSAGE_TYPE;
    @Nullable
    private static final MethodHandle CONSTRUCTOR_TITLE_TIMES;
    @Nullable
    private static final Object MESSAGE_TYPE_ACTIONBAR;
    @Nullable
    private static final Object TITLE_ACTION_CLEAR;
    @Nullable
    private static final Object TITLE_ACTION_SUBTITLE;
    private static final Class<?> CLASS_CRAFT_ENTITY;
    private static final Class<?> CLASS_NMS_ENTITY;
    @Nullable
    private static final MethodHandle PLAYER_CONNECTION_SEND_PACKET;
    private static final MethodHandle CRAFT_ENTITY_GET_HANDLE;
    private static final boolean SUPPORTED;
    @Nullable
    private static final Class<?> CLASS_CHAT_COMPONENT;
    @Nullable
    static final Class<? extends Player> CLASS_CRAFT_PLAYER;
    @Nullable
    private static final MethodHandle CHAT_PACKET_CONSTRUCTOR;
    @Nullable
    private static final Class<?> CLASS_TITLE_PACKET;
    @Nullable
    private static final Object MESSAGE_TYPE_SYSTEM;

    static /* synthetic */ Object access$600() {
        return TITLE_ACTION_ACTIONBAR;
    }

    static /* synthetic */ Class access$1100() {
        return CLASS_NMS_ENTITY;
    }

    static /* synthetic */ Class access$500() {
        return CLASS_CHAT_COMPONENT;
    }

    static /* synthetic */ MethodHandle access$000() {
        return ENTITY_PLAYER_GET_CONNECTION;
    }

    static /* synthetic */ Object access$1300() {
        return TITLE_ACTION_TITLE;
    }

    static /* synthetic */ Object access$1400() {
        return TITLE_ACTION_SUBTITLE;
    }

    static /* synthetic */ Object access$1500() {
        return TITLE_ACTION_CLEAR;
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }

    static /* synthetic */ MethodHandle access$100() {
        return PLAYER_CONNECTION_SEND_PACKET;
    }

    static /* synthetic */ Class access$900() {
        return CLASS_CRAFT_ENTITY;
    }

    protected CraftBukkitFacet(@Nullable Class<? extends V> viewerClass) {
        super(viewerClass);
    }

    static /* synthetic */ MethodHandle access$800() {
        return LEGACY_CHAT_PACKET_CONSTRUCTOR;
    }

    static /* synthetic */ Object access$400() {
        return MESSAGE_TYPE_SYSTEM;
    }

    static {
        CLASS_NMS_ENTITY = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"Entity"), MinecraftReflection.findMcClassName((String)"world.entity.Entity")});
        CLASS_CRAFT_ENTITY = MinecraftReflection.findCraftClass((String)"entity.CraftEntity");
        CRAFT_ENTITY_GET_HANDLE = MinecraftReflection.findMethod(CLASS_CRAFT_ENTITY, (String)"getHandle", CLASS_NMS_ENTITY, (Class[])new Class[0]);
        CLASS_CRAFT_PLAYER = MinecraftReflection.findCraftClass((String)"entity.CraftPlayer", Player.class);
        Class craftPlayerClass = MinecraftReflection.findCraftClass((String)"entity.CraftPlayer");
        Class packetClass = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"Packet"), MinecraftReflection.findMcClassName((String)"network.protocol.Packet")});
        MethodHandle craftPlayerGetHandle = null;
        MethodHandle entityPlayerGetConnection = null;
        MethodHandle playerConnectionSendPacket = null;
        if (craftPlayerClass != null && packetClass != null) {
            try {
                Method getHandleMethod = craftPlayerClass.getMethod("getHandle", new Class[0]);
                Class<?> entityPlayerClass = getHandleMethod.getReturnType();
                craftPlayerGetHandle = MinecraftReflection.lookup().unreflect(getHandleMethod);
                Field playerConnectionField = MinecraftReflection.findField(entityPlayerClass, (String[])new String[]{"playerConnection", "connection"});
                Class<?> playerConnectionClass = null;
                if (playerConnectionField != null) {
                    entityPlayerGetConnection = MinecraftReflection.lookup().unreflectGetter(playerConnectionField);
                    playerConnectionClass = playerConnectionField.getType();
                } else {
                    Class serverGamePacketListenerImpl = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PlayerConnection"), MinecraftReflection.findMcClassName((String)"server.network.PlayerConnection"), MinecraftReflection.findMcClassName((String)"server.network.ServerGamePacketListenerImpl")});
                    for (Field field : entityPlayerClass.getDeclaredFields()) {
                        int modifiers = field.getModifiers();
                        if (!Modifier.isPublic(modifiers) || Modifier.isFinal(modifiers) || serverGamePacketListenerImpl != null && !field.getType().equals(serverGamePacketListenerImpl)) continue;
                        entityPlayerGetConnection = MinecraftReflection.lookup().unreflectGetter(field);
                        playerConnectionClass = field.getType();
                    }
                }
                Class<?> serverCommonPacketListenerImpl = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findMcClassName((String)"server.network.ServerCommonPacketListenerImpl")});
                if (serverCommonPacketListenerImpl != null) {
                    playerConnectionClass = serverCommonPacketListenerImpl;
                }
                playerConnectionSendPacket = MinecraftReflection.searchMethod((Class)playerConnectionClass, (Integer)1, (String[])new String[]{"sendPacket", "send"}, Void.TYPE, (Class[])new Class[]{packetClass});
            }
            catch (Throwable error) {
                Knob.logError((Throwable)error, (String)"Failed to initialize CraftBukkit sendPacket", (Object[])new Object[0]);
            }
        }
        CRAFT_PLAYER_GET_HANDLE = craftPlayerGetHandle;
        ENTITY_PLAYER_GET_CONNECTION = entityPlayerGetConnection;
        PLAYER_CONNECTION_SEND_PACKET = playerConnectionSendPacket;
        SUPPORTED = Knob.isEnabled((String)"craftbukkit", (boolean)true) && MinecraftComponentSerializer.isSupported() && CRAFT_PLAYER_GET_HANDLE != null && ENTITY_PLAYER_GET_CONNECTION != null && PLAYER_CONNECTION_SEND_PACKET != null;
        CLASS_CHAT_COMPONENT = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"IChatBaseComponent"), MinecraftReflection.findMcClassName((String)"network.chat.IChatBaseComponent"), MinecraftReflection.findMcClassName((String)"network.chat.Component")});
        CLASS_MESSAGE_TYPE = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"ChatMessageType"), MinecraftReflection.findMcClassName((String)"network.chat.ChatMessageType"), MinecraftReflection.findMcClassName((String)"network.chat.ChatType")});
        if (CLASS_MESSAGE_TYPE != null && !CLASS_MESSAGE_TYPE.isEnum()) {
            MESSAGE_TYPE_CHAT = 0;
            MESSAGE_TYPE_SYSTEM = 1;
            MESSAGE_TYPE_ACTIONBAR = 2;
        } else {
            MESSAGE_TYPE_CHAT = MinecraftReflection.findEnum(CLASS_MESSAGE_TYPE, (String)"CHAT", (int)0);
            MESSAGE_TYPE_SYSTEM = MinecraftReflection.findEnum(CLASS_MESSAGE_TYPE, (String)"SYSTEM", (int)1);
            MESSAGE_TYPE_ACTIONBAR = MinecraftReflection.findEnum(CLASS_MESSAGE_TYPE, (String)"GAME_INFO", (int)2);
        }
        MethodHandle legacyChatPacketConstructor = null;
        MethodHandle chatPacketConstructor = null;
        try {
            if (CLASS_CHAT_COMPONENT != null) {
                Class chatPacketClass = MinecraftReflection.needClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PacketPlayOutChat"), MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutChat"), MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundChatPacket"), MinecraftReflection.findMcClassName((String)"network.protocol.game.ClientboundSystemChatPacket")});
                if (MESSAGE_TYPE_CHAT == Integer.valueOf(0)) {
                    chatPacketConstructor = MinecraftReflection.findConstructor((Class)chatPacketClass, (Class[])new Class[]{CLASS_CHAT_COMPONENT, Boolean.TYPE});
                }
                if (chatPacketConstructor == null) {
                    chatPacketConstructor = MinecraftReflection.findConstructor((Class)chatPacketClass, (Class[])new Class[]{CLASS_CHAT_COMPONENT, Integer.TYPE});
                }
                if (chatPacketConstructor == null) {
                    chatPacketConstructor = MinecraftReflection.findConstructor((Class)chatPacketClass, (Class[])new Class[]{CLASS_CHAT_COMPONENT});
                }
                if (chatPacketConstructor == null) {
                    if (CLASS_MESSAGE_TYPE != null) {
                        chatPacketConstructor = MinecraftReflection.findConstructor((Class)chatPacketClass, (Class[])new Class[]{CLASS_CHAT_COMPONENT, CLASS_MESSAGE_TYPE, UUID.class});
                    }
                } else if (MESSAGE_TYPE_CHAT == Integer.valueOf(0)) {
                    if (chatPacketConstructor.type().parameterType(1).equals(Boolean.TYPE)) {
                        chatPacketConstructor = MethodHandles.insertArguments(chatPacketConstructor, 1, Boolean.FALSE);
                        chatPacketConstructor = MethodHandles.dropArguments(chatPacketConstructor, 1, new Class[]{Integer.class, UUID.class});
                    } else {
                        chatPacketConstructor = MethodHandles.dropArguments(chatPacketConstructor, 2, new Class[]{UUID.class});
                    }
                } else {
                    chatPacketConstructor = MethodHandles.dropArguments(chatPacketConstructor, 1, new Class[]{CLASS_MESSAGE_TYPE == null ? Object.class : CLASS_MESSAGE_TYPE, UUID.class});
                }
                if ((legacyChatPacketConstructor = MinecraftReflection.findConstructor((Class)chatPacketClass, (Class[])new Class[]{CLASS_CHAT_COMPONENT, Byte.TYPE})) == null) {
                    legacyChatPacketConstructor = MinecraftReflection.findConstructor((Class)chatPacketClass, (Class[])new Class[]{CLASS_CHAT_COMPONENT, Integer.TYPE});
                }
            }
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to initialize ClientboundChatPacket constructor", (Object[])new Object[0]);
        }
        CHAT_PACKET_CONSTRUCTOR = chatPacketConstructor;
        LEGACY_CHAT_PACKET_CONSTRUCTOR = legacyChatPacketConstructor;
        CLASS_TITLE_PACKET = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PacketPlayOutTitle"), MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutTitle")});
        CLASS_TITLE_ACTION = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"PacketPlayOutTitle$EnumTitleAction"), MinecraftReflection.findMcClassName((String)"network.protocol.game.PacketPlayOutTitle$EnumTitleAction")});
        CONSTRUCTOR_TITLE_MESSAGE = MinecraftReflection.findConstructor(CLASS_TITLE_PACKET, (Class[])new Class[]{CLASS_TITLE_ACTION, CLASS_CHAT_COMPONENT});
        CONSTRUCTOR_TITLE_TIMES = MinecraftReflection.findConstructor(CLASS_TITLE_PACKET, (Class[])new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE});
        TITLE_ACTION_TITLE = MinecraftReflection.findEnum(CLASS_TITLE_ACTION, (String)"TITLE", (int)0);
        TITLE_ACTION_SUBTITLE = MinecraftReflection.findEnum(CLASS_TITLE_ACTION, (String)"SUBTITLE", (int)1);
        TITLE_ACTION_ACTIONBAR = MinecraftReflection.findEnum(CLASS_TITLE_ACTION, (String)"ACTIONBAR");
        TITLE_ACTION_CLEAR = MinecraftReflection.findEnum(CLASS_TITLE_ACTION, (String)"CLEAR");
        TITLE_ACTION_RESET = MinecraftReflection.findEnum(CLASS_TITLE_ACTION, (String)"RESET");
    }

    static /* synthetic */ MethodHandle access$700() {
        return CONSTRUCTOR_TITLE_MESSAGE;
    }

    static /* synthetic */ Object access$1600() {
        return TITLE_ACTION_RESET;
    }

    static /* synthetic */ Object access$300() {
        return MESSAGE_TYPE_CHAT;
    }

    static /* synthetic */ MethodHandle access$200() {
        return CHAT_PACKET_CONSTRUCTOR;
    }

    static /* synthetic */ MethodHandle access$1000() {
        return CRAFT_ENTITY_GET_HANDLE;
    }

    static /* synthetic */ MethodHandle access$1200() {
        return CONSTRUCTOR_TITLE_TIMES;
    }
}
