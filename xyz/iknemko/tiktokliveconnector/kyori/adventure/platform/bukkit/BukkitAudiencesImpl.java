/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.block.Block
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.command.ProxiedCommandSender
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.plugin.Plugin
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiences
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiencesImpl$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudienceProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.ProxiedCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiences;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiencesImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudienceProvider;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.flattener.ComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;

final class BukkitAudiencesImpl
extends FacetAudienceProvider<CommandSender, BukkitAudience>
implements BukkitAudiences,
Listener {
    private static final Map<String, BukkitAudiences> INSTANCES;
    private final Plugin plugin;

    static BukkitAudiences instanceFor(@NotNull Plugin plugin) {
        return BukkitAudiencesImpl.builder(plugin).build();
    }

    public void close() {
        INSTANCES.remove(this.plugin.getName());
        super.close();
    }

    static Builder builder(@NotNull Plugin plugin) {
        return new Builder(plugin);
    }

    @NotNull
    public Audience sender(@NotNull CommandSender sender) {
        if (sender instanceof Player) {
            return this.player((Player)sender);
        }
        if (sender instanceof ConsoleCommandSender) {
            return this.console();
        }
        if (sender instanceof ProxiedCommandSender) {
            return this.sender(((ProxiedCommandSender)sender).getCallee());
        }
        if (sender instanceof Entity) return Audience.empty();
        if (!(sender instanceof Block)) return this.createAudience(Collections.singletonList(sender));
        return Audience.empty();
    }

    static /* synthetic */ Locale access$100() {
        return DEFAULT_LOCALE;
    }

    @NotNull
    public Audience player(@NotNull Player player) {
        return super.player(player.getUniqueId());
    }

    private <T extends Event> void registerEvent(@NotNull Class<T> type, @NotNull EventPriority priority, @NotNull Consumer<T> callback) {
        Objects.requireNonNull(callback, "callback");
        this.plugin.getServer().getPluginManager().registerEvent(type, (Listener)this, priority, (listener, event) -> callback.accept(event), this.plugin, true);
    }

    @NotNull
    protected BukkitAudience createAudience(@NotNull Collection<CommandSender> viewers) {
        return new BukkitAudience(this.plugin, (FacetAudienceProvider)this, viewers);
    }

    BukkitAudiencesImpl(@NotNull Plugin plugin, @NotNull ComponentRenderer<Pointered> componentRenderer) {
        super(componentRenderer);
        this.plugin = Objects.requireNonNull(plugin, "plugin");
        ConsoleCommandSender console = this.plugin.getServer().getConsoleSender();
        this.addViewer(console);
        Iterator iterator = this.plugin.getServer().getOnlinePlayers().iterator();
        while (true) {
            if (!iterator.hasNext()) {
                this.registerEvent(PlayerJoinEvent.class, EventPriority.LOWEST, event -> this.addViewer(event.getPlayer()));
                this.registerEvent(PlayerQuitEvent.class, EventPriority.MONITOR, event -> this.removeViewer(event.getPlayer()));
                return;
            }
            Player player = (Player)iterator.next();
            this.addViewer(player);
        }
    }

    static /* synthetic */ Map access$000() {
        return INSTANCES;
    }

    @NotNull
    public ComponentFlattener flattener() {
        return BukkitComponentSerializer.FLATTENER;
    }

    static {
        Knob.OUT = message -> Bukkit.getLogger().log(Level.INFO, (String)message);
        Knob.ERR = (message, error) -> Bukkit.getLogger().log(Level.WARNING, (String)message, (Throwable)error);
        INSTANCES = Collections.synchronizedMap(new HashMap(4));
    }
}
