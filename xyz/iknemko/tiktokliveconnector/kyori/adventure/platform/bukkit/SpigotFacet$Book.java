/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.chat.BaseComponent
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.BookMeta
 *  org.bukkit.inventory.meta.ItemMeta
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Book
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.Iterator;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.SpigotFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;

static final class SpigotFacet.Book
extends SpigotFacet.Message<Player>
implements Facet.Book<Player, BaseComponent[], ItemStack> {
    private static final boolean SUPPORTED = MinecraftReflection.hasMethod(Player.class, (String)"openBook", (Class[])new Class[]{ItemStack.class});

    public void openBook(@NotNull Player viewer, @NotNull ItemStack book) {
        viewer.openBook(book);
    }

    protected SpigotFacet.Book() {
        super(Player.class);
    }

    @NotNull
    public ItemStack createBook(@NotNull String title, @NotNull String author, @NotNull Iterable<BaseComponent[]> pages) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta meta = book.getItemMeta();
        if (!(meta instanceof BookMeta)) return book;
        BookMeta spigot = (BookMeta)meta;
        Iterator<BaseComponent[]> iterator = pages.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                spigot.setTitle(title);
                spigot.setAuthor(author);
                book.setItemMeta((ItemMeta)spigot);
                return book;
            }
            BaseComponent[] page = iterator.next();
            spigot.spigot().addPage((BaseComponent[][])new BaseComponent[][]{page});
        }
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }
}
