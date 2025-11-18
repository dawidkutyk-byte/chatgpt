/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.rylinaux.plugman.api.GentleUnload
 *  com.rylinaux.plugman.api.PlugManAPI
 *  com.rylinaux.plugman.pluginmanager.PluginManager
 *  com.rylinaux.plugman.util.BukkitCommandWrap
 *  com.rylinaux.plugman.util.BukkitCommandWrapUseless
 *  com.rylinaux.plugman.util.StringUtil
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandMap
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.command.SimpleCommandMap
 *  org.bukkit.plugin.InvalidDescriptionException
 *  org.bukkit.plugin.InvalidPluginException
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.plugin.java.JavaPlugin
 *  zNb\u015b
 */
package com.rylinaux.plugman.pluginmanager;

import com.rylinaux.plugman.api.GentleUnload;
import com.rylinaux.plugman.api.PlugManAPI;
import com.rylinaux.plugman.pluginmanager.PluginManager;
import com.rylinaux.plugman.util.BukkitCommandWrap;
import com.rylinaux.plugman.util.BukkitCommandWrapUseless;
import com.rylinaux.plugman.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPluginManager
implements PluginManager {
    private final Field pluginClassLoaderPlugin;
    private String nmsVersion = null;
    private Field knownCommandsField;
    private final Class<?> pluginClassLoader;
    private Field commandMapField;

    public String getPluginVersion(String name) {
        Plugin plugin = this.getPluginByName(name);
        if (plugin == null) return null;
        if (plugin.getDescription() == null) return null;
        return plugin.getDescription().getVersion();
    }

    public BukkitPluginManager() {
        try {
            this.pluginClassLoader = Class.forName("org.bukkit.plugin.java.PluginClassLoader");
            this.pluginClassLoaderPlugin = this.pluginClassLoader.getDeclaredField("plugin");
            this.pluginClassLoaderPlugin.setAccessible(true);
        }
        catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPaperPlugin(Plugin plugin) {
        return false;
    }

    public List<String> getDisabledPluginNames(boolean fullName) {
        ArrayList<String> plugins = new ArrayList<String>();
        Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
        int n = pluginArray.length;
        int n2 = 0;
        while (n2 < n) {
            Plugin plugin = pluginArray[n2];
            if (!plugin.isEnabled()) {
                plugins.add(fullName ? plugin.getDescription().getFullName() : plugin.getName());
            }
            ++n2;
        }
        return plugins;
    }

    private boolean fetchKnownCommandsField() {
        if (this.knownCommandsField != null) {
            return true;
        }
        try {
            this.knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
            this.knownCommandsField.setAccessible(true);
            return true;
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void reload(Plugin plugin) {
        if (plugin == null) return;
        this.unload(plugin);
        this.load(plugin);
    }

    public void disableAll() {
        Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
        int n = pluginArray.length;
        int n2 = 0;
        while (n2 < n) {
            Plugin plugin = pluginArray[n2];
            if (!this.isIgnored(plugin) && !this.isPaperPlugin(plugin)) {
                this.disable(plugin);
            }
            ++n2;
        }
    }

    public String getUsages(Plugin plugin) {
        String parsedCommands = this.getCommandsFromPlugin(plugin).stream().map(s -> {
            String[] parts = ((String)s.getKey()).split(":");
            return parts.length == 1 ? parts[0] : parts[1];
        }).collect(Collectors.joining(", "));
        if (!parsedCommands.isEmpty()) return parsedCommands;
        return "No commands registered.";
    }

    public List<String> getPluginNames(boolean fullName) {
        ArrayList<String> plugins = new ArrayList<String>();
        Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
        int n = pluginArray.length;
        int n2 = 0;
        while (n2 < n) {
            Plugin plugin = pluginArray[n2];
            plugins.add(fullName ? plugin.getDescription().getFullName() : plugin.getName());
            ++n2;
        }
        return plugins;
    }

    public synchronized String unload(Plugin plugin) {
        ClassLoader cl;
        String name = plugin.getName();
        if (PlugManAPI.getGentleUnloads().containsKey(plugin)) {
            GentleUnload gentleUnload = (GentleUnload)PlugManAPI.getGentleUnloads().get(plugin);
            if (!gentleUnload.askingForGentleUnload()) {
                return name + "did not want to unload";
            }
        } else {
            if (!(zNb\u015b.Y\u017btq.getBukkitCommandWrap() instanceof BukkitCommandWrapUseless)) {
                this.unloadCommands(plugin);
            }
            org.bukkit.plugin.PluginManager pluginManager = Bukkit.getPluginManager();
            SimpleCommandMap commandMap = null;
            List plugins = null;
            Map names = null;
            Map commands = null;
            Map listeners = null;
            boolean reloadlisteners = true;
            if (pluginManager != null) {
                pluginManager.disablePlugin(plugin);
                try {
                    Field pluginsField = Bukkit.getPluginManager().getClass().getDeclaredField("plugins");
                    pluginsField.setAccessible(true);
                    plugins = (List)pluginsField.get(pluginManager);
                    Field lookupNamesField = Bukkit.getPluginManager().getClass().getDeclaredField("lookupNames");
                    lookupNamesField.setAccessible(true);
                    names = (Map)lookupNamesField.get(pluginManager);
                    try {
                        Field field = Bukkit.getPluginManager().getClass().getDeclaredField("listeners");
                        field.setAccessible(true);
                        listeners = (Map)field.get(pluginManager);
                    }
                    catch (Exception exception) {
                        reloadlisteners = false;
                    }
                    Field field = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
                    field.setAccessible(true);
                    commandMap = (SimpleCommandMap)field.get(pluginManager);
                    Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
                    knownCommandsField.setAccessible(true);
                    commands = (Map)knownCommandsField.get(commandMap);
                }
                catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                    return "";
                }
            }
            pluginManager.disablePlugin(plugin);
            if (listeners != null && reloadlisteners) {
                for (SortedSet set : listeners.values()) {
                    set.removeIf(value -> value.getPlugin() == plugin);
                }
            }
            if (commandMap != null) {
                HashMap<String, Command> modifiedKnownCommands = new HashMap<String, Command>(commands);
                for (Map.Entry<String, Command> entry : new HashMap(commands).entrySet()) {
                    if (entry.getValue() instanceof PluginCommand) {
                        PluginCommand c = (PluginCommand)entry.getValue();
                        if (c.getPlugin() != plugin) continue;
                        c.unregister(commandMap);
                        modifiedKnownCommands.remove(entry.getKey());
                        continue;
                    }
                    try {
                        this.unregisterNonPluginCommands(plugin, (CommandMap)commandMap, (Map<String, Command>)modifiedKnownCommands, entry);
                    }
                    catch (IllegalStateException e) {
                        if (!e.getMessage().equalsIgnoreCase("zip file closed")) continue;
                        entry.getValue().unregister(commandMap);
                        modifiedKnownCommands.remove(entry.getKey());
                    }
                }
                this.setKnownCommands(modifiedKnownCommands);
            }
            if (plugins != null) {
                plugins.remove(plugin);
            }
            if (names != null) {
                names.remove(name);
            }
        }
        if ((cl = plugin.getClass().getClassLoader()) instanceof URLClassLoader) {
            try {
                Field pluginField = cl.getClass().getDeclaredField("plugin");
                pluginField.setAccessible(true);
                pluginField.set(cl, null);
                Field pluginInitField = cl.getClass().getDeclaredField("pluginInit");
                pluginInitField.setAccessible(true);
                pluginInitField.set(cl, null);
            }
            catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
                Logger.getLogger(BukkitPluginManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ((URLClassLoader)cl).close();
            }
            catch (IOException ex) {
                Logger.getLogger(BukkitPluginManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.gc();
        return "";
    }

    public List<String> getEnabledPluginNames(boolean fullName) {
        ArrayList<String> plugins = new ArrayList<String>();
        Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
        int n = pluginArray.length;
        int n2 = 0;
        while (n2 < n) {
            Plugin plugin = pluginArray[n2];
            if (plugin.isEnabled()) {
                plugins.add(fullName ? plugin.getDescription().getFullName() : plugin.getName());
            }
            ++n2;
        }
        return plugins;
    }

    public boolean isIgnored(Plugin plugin) {
        return this.isIgnored(plugin.getName());
    }

    private String load(Plugin plugin) {
        return this.load(plugin.getName());
    }

    public String getFormattedName(Plugin plugin, boolean includeVersions) {
        ChatColor color = plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED;
        String pluginName = color + plugin.getName();
        if (!includeVersions) return pluginName;
        pluginName = pluginName + " (" + plugin.getDescription().getVersion() + ")";
        return pluginName;
    }

    public boolean isIgnored(String plugin) {
        return false;
    }

    public void disable(Plugin plugin) {
        if (plugin == null) return;
        if (!plugin.isEnabled()) return;
        Bukkit.getPluginManager().disablePlugin(plugin);
    }

    public List<String> findByCommand(String command) {
        ArrayList<String> plugins = new ArrayList<String>();
        Iterator<Map.Entry<String, Command>> iterator = this.getKnownCommands().entrySet().iterator();
        while (iterator.hasNext()) {
            String[] parts;
            Map.Entry<String, Command> s = iterator.next();
            ClassLoader cl = s.getValue().getClass().getClassLoader();
            if (cl.getClass() != this.pluginClassLoader) {
                Plugin plugin;
                parts = s.getKey().split(":");
                if (parts.length != 2 || !parts[1].equalsIgnoreCase(command) || (plugin = (Plugin)Arrays.stream(Bukkit.getPluginManager().getPlugins()).filter(pl -> pl.getName().equalsIgnoreCase(parts[0])).findFirst().orElse(null)) == null) continue;
                plugins.add(plugin.getName());
                continue;
            }
            try {
                JavaPlugin plugin;
                parts = s.getKey().split(":");
                String cmd = parts[parts.length - 1];
                if (!cmd.equalsIgnoreCase(command) || plugins.contains((plugin = (JavaPlugin)this.pluginClassLoaderPlugin.get(cl)).getName())) continue;
                plugins.add(plugin.getName());
            }
            catch (IllegalAccessException illegalAccessException) {}
        }
        return plugins;
    }

    public Plugin getPluginByName(String[] args, int start) {
        return this.getPluginByName(StringUtil.consolidateStrings((String[])args, (int)start));
    }

    public void reloadAll() {
        Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
        int n = pluginArray.length;
        int n2 = 0;
        while (n2 < n) {
            Plugin plugin = pluginArray[n2];
            if (!this.isIgnored(plugin) && !this.isPaperPlugin(plugin)) {
                this.reload(plugin);
            }
            ++n2;
        }
    }

    private List<Map.Entry<String, Command>> getCommandsFromPlugin(Plugin plugin) {
        Map<String, Command> knownCommands = this.getKnownCommands();
        return knownCommands.entrySet().stream().filter(s -> {
            if (((String)s.getKey()).contains(":")) {
                return ((String)s.getKey()).split(":")[0].equalsIgnoreCase(plugin.getName());
            }
            ClassLoader cl = ((Command)s.getValue()).getClass().getClassLoader();
            try {
                return cl.getClass() == this.pluginClassLoader && this.pluginClassLoaderPlugin.get(cl) == plugin;
            }
            catch (IllegalAccessException e) {
                return false;
            }
        }).collect(Collectors.toList());
    }

    public Plugin getPluginByName(String name) {
        Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
        int n = pluginArray.length;
        int n2 = 0;
        while (n2 < n) {
            Plugin plugin = pluginArray[n2];
            if (name.equalsIgnoreCase(plugin.getName())) {
                return plugin;
            }
            ++n2;
        }
        return null;
    }

    public void enableAll() {
        Plugin[] pluginArray = Bukkit.getPluginManager().getPlugins();
        int n = pluginArray.length;
        int n2 = 0;
        while (n2 < n) {
            Plugin plugin = pluginArray[n2];
            if (!this.isIgnored(plugin) && !this.isPaperPlugin(plugin)) {
                this.enable(plugin);
            }
            ++n2;
        }
    }

    protected void loadCommands(Plugin plugin) {
        List<Map.Entry<String, Command>> commands = this.getCommandsFromPlugin(plugin);
        Iterator<Map.Entry<String, Command>> iterator = commands.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                zNb\u015b.Y\u017btq.getBukkitCommandWrap().sync();
                return;
            }
            Map.Entry<String, Command> entry = iterator.next();
            String alias = entry.getKey();
            Command command = entry.getValue();
            zNb\u015b.Y\u017btq.getBukkitCommandWrap().wrap(command, alias);
        }
    }

    public String load(String name) {
        Plugin target;
        File pluginDir = new File("plugins");
        if (!pluginDir.isDirectory()) {
            return "";
        }
        File pluginFile = new File(pluginDir, name + ".jar");
        if (!pluginFile.isFile()) {
            for (File f : pluginDir.listFiles()) {
                if (!f.getName().endsWith(".jar")) continue;
                try {
                    PluginDescriptionFile desc = zNb\u015b.Y\u017btq.getPluginLoader().getPluginDescription(f);
                    if (!desc.getName().equalsIgnoreCase(name)) continue;
                    pluginFile = f;
                    break;
                }
                catch (InvalidDescriptionException e) {
                    return "";
                }
            }
        }
        try {
            target = Bukkit.getPluginManager().loadPlugin(pluginFile);
        }
        catch (InvalidDescriptionException e) {
            e.printStackTrace();
            return "";
        }
        catch (InvalidPluginException e) {
            e.printStackTrace();
            return "";
        }
        target.onLoad();
        Bukkit.getPluginManager().enablePlugin(target);
        if (zNb\u015b.Y\u017btq.getBukkitCommandWrap() instanceof BukkitCommandWrapUseless) return "";
        Plugin finalTarget = target;
        Bukkit.getScheduler().runTaskLater((Plugin)zNb\u015b.Y\u017btq, () -> this.loadCommands(finalTarget), 10L);
        zNb\u015b.Y\u017btq.getFilePluginMap().put(pluginFile.getName(), target.getName());
        return "";
    }

    protected synchronized void unloadCommands(Plugin plugin) {
        String alias;
        Map<String, Command> knownCommands = this.getKnownCommands();
        List<Map.Entry<String, Command>> commands = this.getCommandsFromPlugin(plugin);
        for (Map.Entry<String, Command> entry : commands) {
            alias = entry.getKey();
            zNb\u015b.Y\u017btq.getBukkitCommandWrap().unwrap(alias);
        }
        Iterator<Map.Entry<String, Object>> iterator = knownCommands.entrySet().stream().filter(stringCommandEntry -> Plugin.class.isAssignableFrom(((Command)stringCommandEntry.getValue()).getClass())).filter(stringCommandEntry -> {
            Field pluginField = Arrays.stream(((Command)stringCommandEntry.getValue()).getClass().getDeclaredFields()).filter(field -> Plugin.class.isAssignableFrom(field.getType())).findFirst().orElse(null);
            if (pluginField == null) return false;
            try {
                Plugin owningPlugin = (Plugin)pluginField.get(stringCommandEntry.getValue());
                return owningPlugin.getName().equalsIgnoreCase(plugin.getName());
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList()).iterator();
        while (true) {
            if (!iterator.hasNext()) {
                zNb\u015b.Y\u017btq.getBukkitCommandWrap().sync();
                return;
            }
            Map.Entry<String, Object> entry = iterator.next();
            alias = entry.getKey();
            zNb\u015b.Y\u017btq.getBukkitCommandWrap().unwrap(alias);
        }
    }

    public String getFormattedName(Plugin plugin) {
        return this.getFormattedName(plugin, false);
    }

    public void enable(Plugin plugin) {
        if (plugin == null) return;
        if (plugin.isEnabled()) return;
        Bukkit.getPluginManager().enablePlugin(plugin);
    }

    private boolean fetchCommandMapField() {
        if (this.commandMapField != null) {
            return true;
        }
        try {
            this.commandMapField = Class.forName(BukkitCommandWrap.getCraftBukkitPrefix((String)"CraftServer")).getDeclaredField("commandMap");
            this.commandMapField.setAccessible(true);
            return true;
        }
        catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void unregisterNonPluginCommands(Plugin plugin, CommandMap commandMap, Map<String, Command> commands, Map.Entry<String, Command> entry) {
        Field pluginField = Arrays.stream(entry.getValue().getClass().getDeclaredFields()).filter(field -> Plugin.class.isAssignableFrom(field.getType())).findFirst().orElse(null);
        if (pluginField == null) {
            return;
        }
        try {
            pluginField.setAccessible(true);
            Plugin owningPlugin = (Plugin)pluginField.get(entry.getValue());
            if (!owningPlugin.getName().equalsIgnoreCase(plugin.getName())) return;
            entry.getValue().unregister(commandMap);
            commands.remove(entry.getKey());
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setKnownCommands(Map<String, Command> knownCommands) {
        SimpleCommandMap commandMap;
        if (!this.fetchCommandMapField()) {
            throw new RuntimeException("Cannot find command map");
        }
        try {
            commandMap = (SimpleCommandMap)this.commandMapField.get(Bukkit.getServer());
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (!this.fetchKnownCommandsField()) {
            throw new RuntimeException("Unable to find known commands");
        }
        try {
            this.knownCommandsField.set(commandMap, knownCommands);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Command> getKnownCommands() {
        Map knownCommands;
        SimpleCommandMap commandMap;
        if (!this.fetchCommandMapField()) {
            throw new RuntimeException("Cannot find command map");
        }
        try {
            commandMap = (SimpleCommandMap)this.commandMapField.get(Bukkit.getServer());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (!this.fetchKnownCommandsField()) {
            throw new RuntimeException("Unable to find known commands");
        }
        try {
            knownCommands = (Map)this.knownCommandsField.get(commandMap);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return knownCommands;
    }
}
