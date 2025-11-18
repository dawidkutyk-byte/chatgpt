/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess$Book_1_20_5
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitAccess;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

static final class CraftBukkitFacet.Book_1_20_5
extends CraftBukkitFacet.PacketFacet<Player>
implements Facet.Book<Player, Object, ItemStack> {
    CraftBukkitFacet.Book_1_20_5() {
    }

    @Nullable
    public ItemStack createBook(@NotNull String title, @NotNull String author, @NotNull Iterable<Object> pages) {
        try {
            ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
            ArrayList<Object> pageList = new ArrayList<Object>();
            Iterator<Object> iterator = pages.iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    Object bookContent = CraftBukkitAccess.Book_1_20_5.NEW_BOOK_CONTENT.invoke(CraftBukkitAccess.Book_1_20_5.CREATE_FILTERABLE.invoke(title), author, 0, pageList, true);
                    Object stack = CraftBukkitAccess.Book_1_20_5.CRAFT_ITEMSTACK_NMS_COPY.invoke(item);
                    CraftBukkitAccess.Book_1_20_5.MC_ITEMSTACK_SET.invoke(stack, CraftBukkitAccess.Book_1_20_5.WRITTEN_BOOK_COMPONENT_TYPE, bookContent);
                    return CraftBukkitAccess.Book_1_20_5.CRAFT_ITEMSTACK_CRAFT_MIRROR.invoke(stack);
                }
                Object page = iterator.next();
                pageList.add(CraftBukkitAccess.Book_1_20_5.CREATE_FILTERABLE.invoke(page));
            }
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to apply written_book_content component to ItemStack", (Object[])new Object[0]);
            return null;
        }
    }

    public boolean isSupported() {
        return super.isSupported() && CraftBukkitAccess.Book_1_20_5.isSupported();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void openBook(@NotNull Player viewer, @NotNull ItemStack book) {
        PlayerInventory inventory = viewer.getInventory();
        ItemStack current = inventory.getItemInHand();
        try {
            inventory.setItemInHand(book);
            this.sendMessage((CommandSender)viewer, CraftBukkitAccess.Book_1_20_5.NEW_PACKET_OPEN_BOOK.invoke(CraftBukkitAccess.Book_1_20_5.HAND_MAIN));
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to send openBook packet: %s", (Object[])new Object[]{book});
        }
        finally {
            inventory.setItemInHand(current);
        }
    }
}
