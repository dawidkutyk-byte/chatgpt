/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.Vector
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$BossBarBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$Chat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$CommandSenderPointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$ConsoleCommandSenderPointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$PlayerPointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$SoundWithCategory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$TabList
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet$ViaHook
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$ActionBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$ActionBarLegacy
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$ActionBar_1_17
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$Book1_13
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$BookPost1_13
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$BookPre1_13
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$Book_1_20_5
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$BossBar$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$BossBarWither$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$Chat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$Chat1_19_3
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$EntitySound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$EntitySound_1_19_3
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$TabList
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$Title
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$Title_1_17
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.PaperFacet$TabList
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$ActionBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$BossBar$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Chat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$EntitySound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Pointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Sound
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$TabList
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Title
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudienceProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$ActionBar
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$ActionBarTitle
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$BossBar$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$BossBar$Builder1_9_To_1_15
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$Chat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$TabList
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet$Title
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import com.viaversion.viaversion.api.connection.UserConnection;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.bossbar.BossBar;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.PaperFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudienceProvider;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion.ViaFacet;

final class BukkitAudience
extends FacetAudience<CommandSender> {
    private static final Collection<Facet.Pointers<? extends CommandSender>> POINTERS;
    @NotNull
    private final Plugin plugin;
    private static final Function<Player, UserConnection> VIA;
    static final ThreadLocal<Plugin> PLUGIN;
    private static final Collection<Facet.Book<Player, ?, ?>> BOOK;
    private static final Collection<Facet.Title<Player, ?, ?, ?>> TITLE;
    private static final Collection<Facet.Sound<Player, Vector>> SOUND;
    private static final Collection<Facet.EntitySound<Player, Object>> ENTITY_SOUND;
    private static final Collection<Facet.ActionBar<Player, ?>> ACTION_BAR;
    private static final Collection<Facet.BossBar.Builder<Player, ?>> BOSS_BAR;
    private static final Collection<Facet.TabList<Player, ?>> TAB_LIST;
    private static final Collection<Facet.Chat<? extends CommandSender, ?>> CHAT;

    static {
        PLUGIN = new ThreadLocal();
        VIA = new BukkitFacet.ViaHook();
        CHAT = Facet.of((Supplier[])new Supplier[]{() -> new ViaFacet.Chat(Player.class, VIA), () -> new CraftBukkitFacet.Chat1_19_3(), () -> new CraftBukkitFacet.Chat(), () -> new BukkitFacet.Chat()});
        ACTION_BAR = Facet.of((Supplier[])new Supplier[]{() -> new ViaFacet.ActionBarTitle(Player.class, VIA), () -> new ViaFacet.ActionBar(Player.class, VIA), () -> new CraftBukkitFacet.ActionBar_1_17(), () -> new CraftBukkitFacet.ActionBar(), () -> new CraftBukkitFacet.ActionBarLegacy()});
        TITLE = Facet.of((Supplier[])new Supplier[]{() -> new ViaFacet.Title(Player.class, VIA), () -> new CraftBukkitFacet.Title_1_17(), () -> new CraftBukkitFacet.Title()});
        SOUND = Facet.of((Supplier[])new Supplier[]{() -> new BukkitFacet.SoundWithCategory(), () -> new BukkitFacet.Sound()});
        ENTITY_SOUND = Facet.of((Supplier[])new Supplier[]{() -> new CraftBukkitFacet.EntitySound_1_19_3(), () -> new CraftBukkitFacet.EntitySound()});
        BOOK = Facet.of((Supplier[])new Supplier[]{() -> new CraftBukkitFacet.Book_1_20_5(), () -> new CraftBukkitFacet.BookPost1_13(), () -> new CraftBukkitFacet.Book1_13(), () -> new CraftBukkitFacet.BookPre1_13()});
        BOSS_BAR = Facet.of((Supplier[])new Supplier[]{() -> new ViaFacet.BossBar.Builder(Player.class, VIA), () -> new ViaFacet.BossBar.Builder1_9_To_1_15(Player.class, VIA), () -> new CraftBukkitFacet.BossBar.Builder(), () -> new BukkitFacet.BossBarBuilder(), () -> new CraftBukkitFacet.BossBarWither.Builder()});
        TAB_LIST = Facet.of((Supplier[])new Supplier[]{() -> new ViaFacet.TabList(Player.class, VIA), () -> new PaperFacet.TabList(), () -> new CraftBukkitFacet.TabList(), () -> new BukkitFacet.TabList()});
        POINTERS = Facet.of((Supplier[])new Supplier[]{() -> new BukkitFacet.CommandSenderPointers(), () -> new BukkitFacet.ConsoleCommandSenderPointers(), () -> new BukkitFacet.PlayerPointers()});
    }

    BukkitAudience(@NotNull Plugin plugin, FacetAudienceProvider<?, ?> provider, @NotNull Collection<CommandSender> viewers) {
        super(provider, viewers, CHAT, ACTION_BAR, TITLE, SOUND, ENTITY_SOUND, BOOK, BOSS_BAR, TAB_LIST, POINTERS);
        this.plugin = plugin;
    }

    public void showBossBar(@NotNull BossBar bar) {
        PLUGIN.set(this.plugin);
        super.showBossBar(bar);
        PLUGIN.set(null);
    }
}
