/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.rylinaux.plugman.PlugMan
 *  com.rylinaux.plugman.api.GentleUnload
 *  com.rylinaux.plugman.api.PlugManAPI
 *  com.rylinaux.plugman.pluginmanager.BukkitPluginManager
 *  com.rylinaux.plugman.pluginmanager.PaperPluginManager
 *  com.rylinaux.plugman.util.BukkitCommandWrapUseless
 *  org.bukkit.Bukkit
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandMap
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.command.SimpleCommandMap
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 *  zNb\u015b
 */
package com.rylinaux.plugman.pluginmanager;

import com.rylinaux.plugman.PlugMan;
import com.rylinaux.plugman.api.GentleUnload;
import com.rylinaux.plugman.api.PlugManAPI;
import com.rylinaux.plugman.pluginmanager.BukkitPluginManager;
import com.rylinaux.plugman.pluginmanager.PaperPluginManager;
import com.rylinaux.plugman.util.BukkitCommandWrapUseless;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ModernPaperPluginManager
extends PaperPluginManager {
    public String unload(Plugin plugin) {
        String name = plugin.getName();
        if (PlugManAPI.getGentleUnloads().containsKey(plugin)) {
            GentleUnload gentleUnload = (GentleUnload)PlugManAPI.getGentleUnloads().get(plugin);
            if (!gentleUnload.askingForGentleUnload()) {
                return name + "did not want to unload";
            }
        } else {
            Map commands;
            SimpleCommandMap commandMap;
            Map names;
            List plugins;
            List pluginList;
            Map lookupNames;
            PluginManager pluginManager = Bukkit.getPluginManager();
            Map listeners = null;
            boolean reloadlisteners = true;
            pluginManager.disablePlugin(plugin);
            try {
                Class<?> paper = Class.forName("io.papermc.paper.plugin.manager.PaperPluginManagerImpl");
                Object object = paper.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                Field instanceManagerField = object.getClass().getDeclaredField("instanceManager");
                instanceManagerField.setAccessible(true);
                Object instanceManager = instanceManagerField.get(object);
                Field lookupNamesField = instanceManager.getClass().getDeclaredField("lookupNames");
                lookupNamesField.setAccessible(true);
                lookupNames = (Map)lookupNamesField.get(instanceManager);
                Field pluginsField = instanceManager.getClass().getDeclaredField("plugins");
                pluginsField.setAccessible(true);
                pluginList = (List)pluginsField.get(instanceManager);
                pluginsField = Bukkit.getPluginManager().getClass().getDeclaredField("plugins");
                pluginsField.setAccessible(true);
                plugins = (List)pluginsField.get(pluginManager);
                lookupNamesField = Bukkit.getPluginManager().getClass().getDeclaredField("lookupNames");
                lookupNamesField.setAccessible(true);
                names = (Map)lookupNamesField.get(pluginManager);
                try {
                    Field listenersField = Bukkit.getPluginManager().getClass().getDeclaredField("listeners");
                    listenersField.setAccessible(true);
                    listeners = (Map)listenersField.get(pluginManager);
                }
                catch (Exception e) {
                    reloadlisteners = false;
                }
                Field commandMapField = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
                commandMapField.setAccessible(true);
                commandMap = (SimpleCommandMap)commandMapField.get(pluginManager);
                Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
                knownCommandsField.setAccessible(true);
                commands = (Map)knownCommandsField.get(commandMap);
            }
            catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                return "";
            }
            if (listeners != null && reloadlisteners) {
                for (SortedSet sortedSet : listeners.values()) {
                    sortedSet.removeIf(value -> value.getPlugin() == plugin);
                }
            }
            if (commandMap != null) {
                for (Map.Entry entry : new HashSet(commands.entrySet())) {
                    if (entry.getValue() instanceof PluginCommand) {
                        PluginCommand c = (PluginCommand)entry.getValue();
                        if (!c.getPlugin().equals(plugin)) continue;
                        c.unregister((CommandMap)commandMap);
                        commands.remove(entry.getKey());
                        continue;
                    }
                    try {
                        Field pluginField = Arrays.stream(((Command)entry.getValue()).getClass().getDeclaredFields()).filter(field -> Plugin.class.isAssignableFrom(field.getType())).findFirst().orElse(null);
                        if (pluginField == null) continue;
                        try {
                            pluginField.setAccessible(true);
                            Plugin owningPlugin = (Plugin)pluginField.get(entry.getValue());
                            if (!owningPlugin.getName().equalsIgnoreCase(plugin.getName())) continue;
                            ((Command)entry.getValue()).unregister((CommandMap)commandMap);
                            commands.remove(entry.getKey());
                        }
                        catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    catch (IllegalStateException e) {
                        if (!e.getMessage().equalsIgnoreCase("zip file closed")) continue;
                        if (PlugMan.getInstance().isNotifyOnBrokenCommandRemoval()) {
                            Logger.getLogger(PaperPluginManager.class.getName()).info("Removing broken command '" + ((Command)entry.getValue()).getName() + "'!");
                        }
                        ((Command)entry.getValue()).unregister((CommandMap)commandMap);
                        commands.remove(entry.getKey());
                    }
                }
            }
            lookupNames.remove(plugin.getName().toLowerCase());
            pluginList.remove(plugin);
            if (plugins != null) {
                plugins.remove(plugin);
            }
            if (names != null) {
                names.remove(name);
            }
        }
        if (!(zNb\u015b.Y\u017btq.getBukkitCommandWrap() instanceof BukkitCommandWrapUseless)) {
            this.unloadCommands(plugin);
        }
        this.closeClassLoader(plugin);
        System.gc();
        return "";
    }

    public ModernPaperPluginManager(BukkitPluginManager bukkitPluginManager) {
        super(bukkitPluginManager);
    }
}
