/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.RootCommandNode
 *  org.bukkit.Bukkit
 *  org.bukkit.command.Command
 *  org.bukkit.entity.Player
 */
package com.rylinaux.plugman.util;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitCommandWrap {
    private Field vanillaCommandDispatcherField;
    private Method getServerMethod;
    private Field bField;
    private Method aMethod;
    private Constructor bukkitcommandWrapperConstructor;
    private String nmsVersion;
    private Class minecraftServerClass;
    private final boolean nmsVersioning;
    private Method registerMethod;
    private Method syncCommandsMethod;
    private Method removeCommandMethod;
    private Method getCommandDispatcherMethod;

    public BukkitCommandWrap() {
        String prefix = BukkitCommandWrap.getCraftBukkitPrefix();
        String[] packageParts = prefix.split("\\.");
        String versionPart = packageParts[packageParts.length - 1];
        if (versionPart.startsWith("v1_")) {
            this.nmsVersion = versionPart;
        }
        boolean nmsVers = true;
        try {
            Class.forName("net.minecraft.server.MinecraftServer");
            nmsVers = false;
        }
        catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        this.nmsVersioning = nmsVers;
    }

    public void wrap(Command command, String alias) {
        if (this.nmsVersion == null) {
            return;
        }
        if (!this.resolveMinecraftServerClass()) {
            return;
        }
        if (!this.resolveGetServerMethod()) {
            return;
        }
        Object minecraftServer = this.getServerInstance();
        if (!this.resolveVanillaCommandDispatcherField()) {
            return;
        }
        Object commandDispatcher = this.getCommandDispatcher(minecraftServer);
        if (commandDispatcher == null) {
            return;
        }
        if (!this.resolveBField()) {
            return;
        }
        if (!this.resolveAMethod(commandDispatcher)) {
            return;
        }
        if (!this.resolveBukkitCmdWrapperConstructor()) {
            return;
        }
        Object commandWrapper = this.getCommandWrapper(command);
        if (commandWrapper == null) {
            return;
        }
        Object aInstance = this.getAInstance(commandDispatcher);
        if (aInstance == null) {
            return;
        }
        if (!this.resolveRegisterCommandMethod()) {
            return;
        }
        try {
            this.registerMethod.invoke(commandWrapper, aInstance, alias);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private String getNetMinecraftServerPrefix(String nmsClassName) {
        if (!this.nmsVersioning) return "net.minecraft.server." + nmsClassName;
        return "net.minecraft.server." + this.nmsVersion + "." + nmsClassName;
    }

    private boolean resolveBField() {
        if (this.bField != null) {
            return true;
        }
        try {
            this.bField = Class.forName(this.getNetMinecraftServerPrefix("CommandDispatcher")).getDeclaredField("b");
            this.bField.setAccessible(true);
            return true;
        }
        catch (ClassNotFoundException | NoSuchFieldException e) {
            try {
                Class<?> clazz = Class.forName("net.minecraft.commands.CommandDispatcher");
                Field gField = clazz.getDeclaredField("g");
                this.bField = gField.getType() == CommandDispatcher.class ? gField : clazz.getDeclaredField("h");
                this.bField.setAccessible(true);
                return true;
            }
            catch (ClassNotFoundException | NoSuchFieldException ex) {
                ex.addSuppressed(e);
                e.printStackTrace();
                return false;
            }
        }
    }

    @NotNull
    public static String getCraftBukkitPrefix(String cbClassName) {
        return BukkitCommandWrap.getCraftBukkitPrefix() + "." + cbClassName;
    }

    private boolean resolveRemoveCommandMethod() {
        if (this.removeCommandMethod != null) return true;
        try {
            try {
                this.removeCommandMethod = RootCommandNode.class.getDeclaredMethod("removeCommand", String.class);
            }
            catch (NoSuchMethodError | NoSuchMethodException ex) {
                this.removeCommandMethod = CommandNode.class.getDeclaredMethod("removeCommand", String.class);
            }
            return true;
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Object getAInstance(Object commandDispatcher) {
        try {
            return this.aMethod.invoke(commandDispatcher, new Object[0]);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    private CommandDispatcher getDispatcher(Object commandDispatcher) {
        try {
            return (CommandDispatcher)this.bField.get(commandDispatcher);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    private Object getCommandWrapper(Command command) {
        try {
            return this.bukkitcommandWrapperConstructor.newInstance(Bukkit.getServer(), command);
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sync() {
        if (!this.resolveSyncCommandsMethod()) {
            return;
        }
        try {
            this.syncCommandsMethod.invoke((Object)Bukkit.getServer(), new Object[0]);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (Bukkit.getOnlinePlayers().isEmpty()) {
            return;
        }
        Bukkit.getOnlinePlayers().forEach(Player::updateCommands);
    }

    public void unwrap(String command) {
        if (this.nmsVersion == null) {
            return;
        }
        if (!this.resolveMinecraftServerClass()) {
            return;
        }
        if (!this.resolveGetServerMethod()) {
            return;
        }
        Object server = this.getServerInstance();
        if (!this.resolveVanillaCommandDispatcherField()) {
            return;
        }
        Object commandDispatcher = this.getCommandDispatcher(server);
        if (!this.resolveBField()) {
            return;
        }
        CommandDispatcher b = this.getDispatcher(commandDispatcher);
        if (b == null) {
            return;
        }
        if (!this.resolveRemoveCommandMethod()) {
            return;
        }
        try {
            this.removeCommandMethod.invoke((Object)b.getRoot(), command);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private boolean resolveBukkitCmdWrapperConstructor() {
        if (this.bukkitcommandWrapperConstructor != null) {
            return true;
        }
        try {
            this.bukkitcommandWrapperConstructor = Class.forName(BukkitCommandWrap.getCraftBukkitPrefix("command.BukkitCommandWrapper")).getDeclaredConstructor(Class.forName("org.bukkit.craftbukkit." + this.nmsVersion + ".CraftServer"), Command.class);
            this.bukkitcommandWrapperConstructor.setAccessible(true);
            return true;
        }
        catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean resolveRegisterCommandMethod() {
        if (this.registerMethod != null) {
            return true;
        }
        try {
            this.registerMethod = Class.forName(BukkitCommandWrap.getCraftBukkitPrefix("command.BukkitCommandWrapper")).getMethod("register", CommandDispatcher.class, String.class);
            this.registerMethod.setAccessible(true);
            return true;
        }
        catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Nullable
    private Object getCommandDispatcher(Object server) {
        try {
            return this.vanillaCommandDispatcherField.get(server);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean resolveVanillaCommandDispatcherField() {
        if (this.vanillaCommandDispatcherField != null) {
            return true;
        }
        try {
            this.vanillaCommandDispatcherField = this.minecraftServerClass.getDeclaredField("vanillaCommandDispatcher");
            this.vanillaCommandDispatcherField.setAccessible(true);
            return true;
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }

    @NotNull
    private static String getCraftBukkitPrefix() {
        return Bukkit.getServer().getClass().getPackage().getName();
    }

    private boolean resolveSyncCommandsMethod() {
        if (this.syncCommandsMethod != null) {
            return true;
        }
        try {
            this.syncCommandsMethod = Class.forName(BukkitCommandWrap.getCraftBukkitPrefix("CraftServer")).getDeclaredMethod("syncCommands", new Class[0]);
            this.syncCommandsMethod.setAccessible(true);
            return true;
        }
        catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Object getServerInstance() {
        try {
            return this.getServerMethod.invoke((Object)this.minecraftServerClass, new Object[0]);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean resolveGetServerMethod() {
        if (this.getServerMethod != null) {
            return true;
        }
        try {
            this.getServerMethod = this.minecraftServerClass.getMethod("getServer", new Class[0]);
            this.getServerMethod.setAccessible(true);
            return true;
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean resolveAMethod(Object commandDispatcher) {
        if (this.aMethod != null) {
            return true;
        }
        try {
            this.aMethod = commandDispatcher.getClass().getDeclaredMethod("a", new Class[0]);
            this.aMethod.setAccessible(true);
            return true;
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean resolveMinecraftServerClass() {
        if (this.minecraftServerClass != null) {
            return true;
        }
        try {
            this.minecraftServerClass = Class.forName(this.getNetMinecraftServerPrefix("MinecraftServer"));
            return true;
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
