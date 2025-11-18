/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$AbstractBook$TrustedByteArrayOutputStream
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet$PacketFacet
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagIO;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagType;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.BinaryTagTypes;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.CompoundBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.ListBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.nbt.StringBinaryTag;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.CraftBukkitFacet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

protected static abstract class CraftBukkitFacet.AbstractBook
extends CraftBukkitFacet.PacketFacet<Player>
implements Facet.Book<Player, Object, ItemStack> {
    private static final Material BOOK_TYPE = (Material)MinecraftReflection.findEnum(Material.class, (String)"WRITTEN_BOOK");
    private static final String BOOK_TITLE = "title";
    private static final ItemStack BOOK_STACK = BOOK_TYPE == null ? null : new ItemStack(BOOK_TYPE);
    private static final MethodHandle CRAFT_ITEMSTACK_NMS_COPY;
    protected static final int HAND_MAIN = 0;
    private static final Class<?> CLASS_NBT_IO;
    private static final Class<?> CLASS_MC_ITEMSTACK;
    private static final MethodHandle CRAFT_ITEMSTACK_CRAFT_MIRROR;
    private static final Class<?> CLASS_CRAFT_ITEMSTACK;
    private static final String BOOK_RESOLVED = "resolved";
    private static final String BOOK_AUTHOR = "author";
    private static final Class<?> CLASS_NBT_TAG_COMPOUND;
    private static final String BOOK_PAGES = "pages";
    private static final MethodHandle NBT_IO_DESERIALIZE;
    private static final MethodHandle MC_ITEMSTACK_SET_TAG;

    @NotNull
    private Object createTag(@NotNull CompoundBinaryTag tag) throws IOException {
        TrustedByteArrayOutputStream output = new TrustedByteArrayOutputStream(null);
        BinaryTagIO.writer().write(tag, (OutputStream)output);
        try (DataInputStream dis = new DataInputStream(output.toInputStream());){
            Object object = NBT_IO_DESERIALIZE.invoke(dis);
            return object;
        }
        catch (Throwable err) {
            throw new IOException(err);
        }
    }

    protected CraftBukkitFacet.AbstractBook() {
    }

    @NotNull
    public ItemStack createBook(@NotNull String title, @NotNull String author, @NotNull Iterable<Object> pages) {
        return this.applyTag(BOOK_STACK, CraftBukkitFacet.AbstractBook.tagFor(title, author, pages));
    }

    static {
        CLASS_NBT_TAG_COMPOUND = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"NBTTagCompound"), MinecraftReflection.findMcClassName((String)"nbt.CompoundTag"), MinecraftReflection.findMcClassName((String)"nbt.NBTTagCompound")});
        CLASS_NBT_IO = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"NBTCompressedStreamTools"), MinecraftReflection.findMcClassName((String)"nbt.NbtIo"), MinecraftReflection.findMcClassName((String)"nbt.NBTCompressedStreamTools")});
        MethodHandle nbtIoDeserialize = null;
        if (CLASS_NBT_IO != null) {
            for (Method method : CLASS_NBT_IO.getDeclaredMethods()) {
                Class<?> firstParam;
                if (!Modifier.isStatic(method.getModifiers()) || !method.getReturnType().equals(CLASS_NBT_TAG_COMPOUND) || method.getParameterCount() != 1 || !(firstParam = method.getParameterTypes()[0]).equals(DataInputStream.class) && !firstParam.equals(DataInput.class)) continue;
                try {
                    nbtIoDeserialize = MinecraftReflection.lookup().unreflect(method);
                }
                catch (IllegalAccessException illegalAccessException) {}
                break;
            }
        }
        NBT_IO_DESERIALIZE = nbtIoDeserialize;
        CLASS_CRAFT_ITEMSTACK = MinecraftReflection.findCraftClass((String)"inventory.CraftItemStack");
        CLASS_MC_ITEMSTACK = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"ItemStack"), MinecraftReflection.findMcClassName((String)"world.item.ItemStack")});
        MC_ITEMSTACK_SET_TAG = MinecraftReflection.searchMethod(CLASS_MC_ITEMSTACK, (Integer)1, (String)"setTag", Void.TYPE, (Class[])new Class[]{CLASS_NBT_TAG_COMPOUND});
        CRAFT_ITEMSTACK_NMS_COPY = MinecraftReflection.findStaticMethod(CLASS_CRAFT_ITEMSTACK, (String)"asNMSCopy", CLASS_MC_ITEMSTACK, (Class[])new Class[]{ItemStack.class});
        CRAFT_ITEMSTACK_CRAFT_MIRROR = MinecraftReflection.findStaticMethod(CLASS_CRAFT_ITEMSTACK, (String)"asCraftMirror", CLASS_CRAFT_ITEMSTACK, (Class[])new Class[]{CLASS_MC_ITEMSTACK});
    }

    private static CompoundBinaryTag tagFor(@NotNull String title, @NotNull String author, @NotNull Iterable<Object> pages) {
        ListBinaryTag.Builder builder = ListBinaryTag.builder((BinaryTagType)BinaryTagTypes.STRING);
        Iterator<Object> iterator = pages.iterator();
        while (iterator.hasNext()) {
            Object page = iterator.next();
            builder.add((BinaryTag)StringBinaryTag.of((String)((String)page)));
        }
        return ((CompoundBinaryTag.Builder)((CompoundBinaryTag.Builder)((CompoundBinaryTag.Builder)((CompoundBinaryTag.Builder)CompoundBinaryTag.builder().putString(BOOK_TITLE, title)).putString(BOOK_AUTHOR, author)).put(BOOK_PAGES, (BinaryTag)builder.build())).putByte(BOOK_RESOLVED, (byte)1)).build();
    }

    @NotNull
    public String createMessage(@NotNull Player viewer, @NotNull Component message) {
        return (String)BukkitComponentSerializer.gson().serialize(message);
    }

    protected abstract void sendOpenPacket(@NotNull Player var1) throws Throwable;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Deprecated
    public void openBook(@NotNull Player viewer, @NotNull ItemStack book) {
        PlayerInventory inventory = viewer.getInventory();
        ItemStack current = inventory.getItemInHand();
        try {
            inventory.setItemInHand(book);
            this.sendOpenPacket(viewer);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to send openBook packet: %s", (Object[])new Object[]{book});
        }
        finally {
            inventory.setItemInHand(current);
        }
    }

    public boolean isSupported() {
        return super.isSupported() && NBT_IO_DESERIALIZE != null && MC_ITEMSTACK_SET_TAG != null && CRAFT_ITEMSTACK_CRAFT_MIRROR != null && CRAFT_ITEMSTACK_NMS_COPY != null && BOOK_STACK != null;
    }

    private ItemStack applyTag(@NotNull ItemStack input, CompoundBinaryTag binTag) {
        if (CRAFT_ITEMSTACK_NMS_COPY == null) return input;
        if (MC_ITEMSTACK_SET_TAG == null) return input;
        if (CRAFT_ITEMSTACK_CRAFT_MIRROR == null) {
            return input;
        }
        try {
            Object stack = CRAFT_ITEMSTACK_NMS_COPY.invoke(input);
            Object tag = this.createTag(binTag);
            MC_ITEMSTACK_SET_TAG.invoke(stack, tag);
            return CRAFT_ITEMSTACK_CRAFT_MIRROR.invoke(stack);
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to apply NBT tag to ItemStack: %s %s", (Object[])new Object[]{input, binTag});
            return input;
        }
    }
}
