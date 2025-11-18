/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Wither
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.HandlerList
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.plugin.Plugin
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$FakeEntity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

/*
 * Exception performing whole class analysis ignored.
 */
static class CraftBukkitFacet.FakeEntity<E extends Entity>
extends CraftBukkitFacet.PacketFacet<Player>
implements Facet.FakeEntity<Player, Location>,
Listener {
    private static final MethodHandle CRAFT_WORLD_CREATE_ENTITY;
    private static final MethodHandle NMS_ENTITY_GET_DATA_WATCHER;
    private static final MethodHandle NMS_ENTITY_SET_LOCATION;
    private static final Class<?> CLASS_ENTITY_TELEPORT_PACKET;
    private final E entity;
    private final Object entityHandle;
    private static final Class<?> CLASS_WORLD_SERVER;
    private static final boolean SUPPORTED;
    private static final MethodHandle NEW_ENTITY_METADATA_PACKET;
    private static final Class<?> CLASS_WORLD;
    private static final MethodHandle NEW_SPAWN_LIVING_PACKET;
    private static final Class<?> CLASS_ENTITY_WITHER;
    private static final MethodHandle NEW_ENTITY_WITHER;
    private static final Class<? extends World> CLASS_CRAFT_WORLD;
    private static final MethodHandle CRAFT_WORLD_GET_HANDLE;
    private static final MethodHandle NMS_ENTITY_SET_INVISIBLE;
    private static final Class<?> CLASS_ENTITY_METADATA_PACKET;
    private static final MethodHandle NEW_ENTITY_DESTROY_PACKET;
    private static final MethodHandle DATA_WATCHER_WATCH;
    private static final Class<?> CLASS_DATA_WATCHER;
    private static final Class<?> CLASS_ENTITY_DESTROY_PACKET;
    private static final MethodHandle NMS_ENTITY_GET_BUKKIT_ENTITY;
    private static final Class<?> CLASS_NMS_LIVING_ENTITY;
    protected final Set<Player> viewers;
    private static final Class<?> CLASS_SPAWN_LIVING_PACKET;
    private static final MethodHandle NEW_ENTITY_TELEPORT_PACKET;

    @NotNull
    public Location createPosition(@NotNull Player viewer) {
        return viewer.getLocation();
    }

    public void invisible(boolean invisible) {
        if (NMS_ENTITY_SET_INVISIBLE == null) return;
        try {
            NMS_ENTITY_SET_INVISIBLE.invoke(this.entityHandle, invisible);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to change entity visibility: %s", (Object[])new Object[]{this.entity});
        }
    }

    @EventHandler(ignoreCancelled=false, priority=EventPriority.MONITOR)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player viewer = event.getPlayer();
        if (!this.viewers.contains(viewer)) return;
        this.teleport(viewer, this.createPosition(viewer));
    }

    static {
        CLASS_CRAFT_WORLD = MinecraftReflection.findCraftClass((String)"CraftWorld", World.class);
        CLASS_NMS_LIVING_ENTITY = MinecraftReflection.findNmsClass((String)"EntityLiving");
        CLASS_DATA_WATCHER = MinecraftReflection.findNmsClass((String)"DataWatcher");
        CRAFT_WORLD_CREATE_ENTITY = MinecraftReflection.findMethod(CLASS_CRAFT_WORLD, (String)"createEntity", (Class)CraftBukkitFacet.access$1100(), (Class[])new Class[]{Location.class, Class.class});
        NMS_ENTITY_GET_BUKKIT_ENTITY = MinecraftReflection.findMethod((Class)CraftBukkitFacet.access$1100(), (String)"getBukkitEntity", (Class)CraftBukkitFacet.access$900(), (Class[])new Class[0]);
        NMS_ENTITY_GET_DATA_WATCHER = MinecraftReflection.findMethod((Class)CraftBukkitFacet.access$1100(), (String)"getDataWatcher", CLASS_DATA_WATCHER, (Class[])new Class[0]);
        NMS_ENTITY_SET_LOCATION = MinecraftReflection.findMethod((Class)CraftBukkitFacet.access$1100(), (String)"setLocation", Void.TYPE, (Class[])new Class[]{Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
        NMS_ENTITY_SET_INVISIBLE = MinecraftReflection.findMethod((Class)CraftBukkitFacet.access$1100(), (String)"setInvisible", Void.TYPE, (Class[])new Class[]{Boolean.TYPE});
        DATA_WATCHER_WATCH = MinecraftReflection.findMethod(CLASS_DATA_WATCHER, (String)"watch", Void.TYPE, (Class[])new Class[]{Integer.TYPE, Object.class});
        CLASS_SPAWN_LIVING_PACKET = MinecraftReflection.findNmsClass((String)"PacketPlayOutSpawnEntityLiving");
        NEW_SPAWN_LIVING_PACKET = MinecraftReflection.findConstructor(CLASS_SPAWN_LIVING_PACKET, (Class[])new Class[]{CLASS_NMS_LIVING_ENTITY});
        CLASS_ENTITY_DESTROY_PACKET = MinecraftReflection.findNmsClass((String)"PacketPlayOutEntityDestroy");
        NEW_ENTITY_DESTROY_PACKET = MinecraftReflection.findConstructor(CLASS_ENTITY_DESTROY_PACKET, (Class[])new Class[]{int[].class});
        CLASS_ENTITY_METADATA_PACKET = MinecraftReflection.findNmsClass((String)"PacketPlayOutEntityMetadata");
        NEW_ENTITY_METADATA_PACKET = MinecraftReflection.findConstructor(CLASS_ENTITY_METADATA_PACKET, (Class[])new Class[]{Integer.TYPE, CLASS_DATA_WATCHER, Boolean.TYPE});
        CLASS_ENTITY_TELEPORT_PACKET = MinecraftReflection.findNmsClass((String)"PacketPlayOutEntityTeleport");
        NEW_ENTITY_TELEPORT_PACKET = MinecraftReflection.findConstructor(CLASS_ENTITY_TELEPORT_PACKET, (Class[])new Class[]{CraftBukkitFacet.access$1100()});
        CLASS_ENTITY_WITHER = MinecraftReflection.findNmsClass((String)"EntityWither");
        CLASS_WORLD = MinecraftReflection.findNmsClass((String)"World");
        CLASS_WORLD_SERVER = MinecraftReflection.findNmsClass((String)"WorldServer");
        CRAFT_WORLD_GET_HANDLE = MinecraftReflection.findMethod(CLASS_CRAFT_WORLD, (String)"getHandle", CLASS_WORLD_SERVER, (Class[])new Class[0]);
        NEW_ENTITY_WITHER = MinecraftReflection.findConstructor(CLASS_ENTITY_WITHER, (Class[])new Class[]{CLASS_WORLD});
        SUPPORTED = (CRAFT_WORLD_CREATE_ENTITY != null || NEW_ENTITY_WITHER != null && CRAFT_WORLD_GET_HANDLE != null) && CraftBukkitFacet.access$1000() != null && NMS_ENTITY_GET_BUKKIT_ENTITY != null && NMS_ENTITY_GET_DATA_WATCHER != null;
    }

    public boolean isSupported() {
        return super.isSupported() && this.entity != null && this.entityHandle != null;
    }

    public void name(@NotNull Component name) {
        this.entity.setCustomName(BukkitComponentSerializer.legacy().serialize(name));
        this.broadcastPacket(this.createMetadataPacket());
    }

    @Nullable
    public Object createDespawnPacket() {
        try {
            return NEW_ENTITY_DESTROY_PACKET.invoke(this.entity.getEntityId());
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to create despawn packet: %s", (Object[])new Object[]{this.entity});
            return null;
        }
    }

    public void close() {
        HandlerList.unregisterAll((Listener)this);
        Iterator iterator = new LinkedList<Player>(this.viewers).iterator();
        while (iterator.hasNext()) {
            Player viewer = (Player)iterator.next();
            this.teleport(viewer, null);
        }
    }

    public void metadata(int position, @NotNull Object data) {
        if (DATA_WATCHER_WATCH == null) return;
        try {
            Object dataWatcher = NMS_ENTITY_GET_DATA_WATCHER.invoke(this.entityHandle);
            DATA_WATCHER_WATCH.invoke(dataWatcher, position, data);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to set entity metadata: %s %s=%s", (Object[])new Object[]{this.entity, position, data});
        }
        this.broadcastPacket(this.createMetadataPacket());
    }

    public void broadcastPacket(@Nullable Object packet) {
        Iterator<Player> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            Player viewer = iterator.next();
            this.sendPacket(viewer, packet);
        }
    }

    public void teleport(@NotNull Player viewer, @Nullable Location position) {
        if (position == null) {
            this.viewers.remove(viewer);
            this.sendPacket(viewer, this.createDespawnPacket());
            return;
        }
        if (!this.viewers.contains(viewer)) {
            this.sendPacket(viewer, this.createSpawnPacket());
            this.viewers.add(viewer);
        }
        try {
            NMS_ENTITY_SET_LOCATION.invoke(this.entityHandle, position.getX(), position.getY(), position.getZ(), position.getPitch(), position.getYaw());
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to set entity location: %s %s", (Object[])new Object[]{this.entity, position});
        }
        this.sendPacket(viewer, this.createLocationPacket());
    }

    @NotNull
    public Location createPosition(double x, double y, double z) {
        return new Location(null, x, y, z);
    }

    protected CraftBukkitFacet.FakeEntity(@NotNull Class<E> entityClass, @NotNull Location location) {
        this((Plugin)BukkitAudience.PLUGIN.get(), entityClass, location);
    }

    @Deprecated
    public void health(float health) {
        if (!(this.entity instanceof Damageable)) return;
        Damageable entity = (Damageable)this.entity;
        entity.setHealth((double)health * (entity.getMaxHealth() - (double)0.1f) + (double)0.1f);
        this.broadcastPacket(this.createMetadataPacket());
    }

    @Nullable
    public Object createMetadataPacket() {
        try {
            Object dataWatcher = NMS_ENTITY_GET_DATA_WATCHER.invoke(this.entityHandle);
            return NEW_ENTITY_METADATA_PACKET.invoke(this.entity.getEntityId(), dataWatcher, false);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to create update metadata packet: %s", (Object[])new Object[]{this.entity});
            return null;
        }
    }

    @Nullable
    public Object createLocationPacket() {
        try {
            return NEW_ENTITY_TELEPORT_PACKET.invoke(this.entityHandle);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to create teleport packet: %s", (Object[])new Object[]{this.entity});
            return null;
        }
    }

    protected CraftBukkitFacet.FakeEntity(@NotNull Plugin plugin, @NotNull Class<E> entityClass, @NotNull Location location) {
        Entity entity = null;
        Object handle = null;
        if (SUPPORTED) {
            try {
                if (CRAFT_WORLD_CREATE_ENTITY != null) {
                    Object nmsEntity = CRAFT_WORLD_CREATE_ENTITY.invoke(location.getWorld(), location, entityClass);
                    entity = NMS_ENTITY_GET_BUKKIT_ENTITY.invoke(nmsEntity);
                } else if (Wither.class.isAssignableFrom(entityClass) && NEW_ENTITY_WITHER != null) {
                    Object nmsEntity = NEW_ENTITY_WITHER.invoke(CRAFT_WORLD_GET_HANDLE.invoke(location.getWorld()));
                    entity = NMS_ENTITY_GET_BUKKIT_ENTITY.invoke(nmsEntity);
                }
                if (CraftBukkitFacet.access$900().isInstance(entity)) {
                    handle = CraftBukkitFacet.access$1000().invoke(entity);
                }
            }
            catch (Throwable error) {
                Knob.logError((Throwable)error, (String)"Failed to create fake entity: %s", (Object[])new Object[]{entityClass.getSimpleName()});
            }
        }
        this.entity = entity;
        this.entityHandle = handle;
        this.viewers = new HashSet<Player>();
        if (!this.isSupported()) return;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, plugin);
    }

    @Nullable
    public Object createSpawnPacket() {
        if (!(this.entity instanceof LivingEntity)) return null;
        try {
            return NEW_SPAWN_LIVING_PACKET.invoke(this.entityHandle);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to create spawn packet: %s", (Object[])new Object[]{this.entity});
        }
        return null;
    }
}
