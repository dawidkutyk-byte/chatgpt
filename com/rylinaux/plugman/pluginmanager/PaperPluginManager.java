/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.rylinaux.plugman.api.GentleUnload
 *  com.rylinaux.plugman.api.PlugManAPI
 *  com.rylinaux.plugman.pluginmanager.BukkitPluginManager
 *  com.rylinaux.plugman.pluginmanager.PluginManager
 *  com.rylinaux.plugman.util.BukkitCommandWrapUseless
 *  com.rylinaux.plugman.util.StringUtil
 *  com.tcoded.folialib.FoliaLib
 *  io.papermc.paper.plugin.configuration.PluginMeta
 *  org.bukkit.Bukkit
 *  org.bukkit.command.Command
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
import com.rylinaux.plugman.pluginmanager.BukkitPluginManager;
import com.rylinaux.plugman.pluginmanager.PluginManager;
import com.rylinaux.plugman.util.BukkitCommandWrapUseless;
import com.rylinaux.plugman.util.StringUtil;
import com.tcoded.folialib.FoliaLib;
import io.papermc.paper.plugin.configuration.PluginMeta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.error.YAMLException;

public class PaperPluginManager
implements PluginManager {
    private final BukkitPluginManager _bukkitPluginManager;

    protected void closeClassLoader(Plugin plugin) {
        ClassLoader classLoader = plugin.getClass().getClassLoader();
        if (!(classLoader instanceof URLClassLoader)) {
            return;
        }
        try {
            Field pluginField = classLoader.getClass().getDeclaredField("plugin");
            pluginField.setAccessible(true);
            pluginField.set(classLoader, null);
            Field pluginInitField = classLoader.getClass().getDeclaredField("pluginInit");
            pluginInitField.setAccessible(true);
            pluginInitField.set(classLoader, null);
        }
        catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(PaperPluginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ((URLClassLoader)classLoader).close();
        }
        catch (IOException ex) {
            Logger.getLogger(PaperPluginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPluginVersion(String name) {
        return this._bukkitPluginManager.getPluginVersion(name);
    }

    public String getUsages(Plugin plugin) {
        return this._bukkitPluginManager.getUsages(plugin);
    }

    public Plugin getPluginByName(String[] args, int start) {
        return this.getPluginByName(StringUtil.consolidateStrings((String[])args, (int)start));
    }

    public PluginDescriptionFile getPluginDescription(File file) throws InvalidDescriptionException {
        if (file == null) {
            throw new InvalidDescriptionException("File cannot be null");
        }
        JarFile jar = null;
        InputStream stream = null;
        try {
            jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("plugin.yml");
            if (entry == null) {
                throw new InvalidDescriptionException((Throwable)new FileNotFoundException("Jar does not contain plugin.yml"));
            }
            stream = jar.getInputStream(entry);
            PluginDescriptionFile pluginDescriptionFile = new PluginDescriptionFile(stream);
            return pluginDescriptionFile;
        }
        catch (IOException | YAMLException ex) {
            throw new InvalidDescriptionException((Throwable)ex);
        }
        finally {
            if (jar != null) {
                try {
                    jar.close();
                }
                catch (IOException iOException) {}
            }
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException iOException) {}
            }
        }
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

    public Plugin getPluginByName(String name) {
        return this._bukkitPluginManager.getPluginByName(name);
    }

    public List<String> getPluginNames(boolean fullName) {
        return this._bukkitPluginManager.getPluginNames(fullName);
    }

    public String getFormattedName(Plugin plugin) {
        return this.getFormattedName(plugin, false);
    }

    public String getFormattedName(Plugin plugin, boolean includeVersions) {
        return this._bukkitPluginManager.getFormattedName(plugin, includeVersions);
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

    public void setKnownCommands(Map<String, Command> knownCommands) {
        this._bukkitPluginManager.setKnownCommands(knownCommands);
    }

    public boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        }
        catch (Throwable ignored) {
            return false;
        }
    }

    public boolean isIgnored(Plugin plugin) {
        return this.isIgnored(plugin.getName());
    }

    public void reload(Plugin plugin) {
        if (plugin == null) return;
        this.unload(plugin);
        this.load(plugin);
    }

    public boolean isPaperPlugin(Plugin plugin) {
        try {
            Field instanceField = Class.forName("io.papermc.paper.plugin.entrypoint.LaunchEntryPointHandler").getField("INSTANCE");
            instanceField.setAccessible(true);
            Object instance = instanceField.get(null);
            Method getMethod = Arrays.stream(instance.getClass().getDeclaredMethods()).filter(method -> method.getName().equals("get")).findFirst().orElse(null);
            if (getMethod == null) {
                return false;
            }
            Field pluginField = Class.forName("io.papermc.paper.plugin.entrypoint.Entrypoint").getDeclaredField("PLUGIN");
            Object providerStorage = getMethod.invoke(instance, pluginField.get(null));
            if (providerStorage == null) {
                return false;
            }
            Method getRegisteredProvidersMethod = providerStorage.getClass().getMethod("getRegisteredProviders", new Class[0]);
            List providers = (List)getRegisteredProvidersMethod.invoke(providerStorage, new Object[0]);
            Iterator iterator = providers.iterator();
            while (iterator.hasNext()) {
                Object provider = iterator.next();
                try {
                    Method getMetaMethod = provider.getClass().getMethod("getMeta", new Class[0]);
                    PluginMeta configuration = (PluginMeta)getMetaMethod.invoke(provider, new Object[0]);
                    if (configuration.getName().equalsIgnoreCase(plugin.getName())) return Class.forName("io.papermc.paper.plugin.provider.type.paper.PaperPluginParent$PaperServerPluginProvider").isAssignableFrom(provider.getClass());
                }
                catch (Throwable ignored) {
                    return false;
                }
            }
            return false;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public Map<String, Command> getKnownCommands() {
        return this._bukkitPluginManager.getKnownCommands();
    }

    public List<String> getEnabledPluginNames(boolean fullName) {
        return this._bukkitPluginManager.getEnabledPluginNames(fullName);
    }

    private String load(Plugin plugin) {
        return this.load(plugin.getName());
    }

    protected void unloadCommands(Plugin target) {
        this._bukkitPluginManager.unloadCommands(target);
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

    public String unload(Plugin plugin) {
        String name = plugin.getName();
        if (PlugManAPI.getGentleUnloads().containsKey(plugin)) {
            GentleUnload gentleUnload = (GentleUnload)PlugManAPI.getGentleUnloads().get(plugin);
            if (!gentleUnload.askingForGentleUnload()) {
                return name + "did not want to unload";
            }
        } else {
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
                for (Object set : listeners.values()) {
                    set.removeIf(value -> value.getPlugin() == plugin);
                }
            }
            if (commandMap != null) {
                HashMap<String, Command> modifiedKnownCommands = new HashMap<String, Command>(commands);
                for (Map.Entry entry : new HashMap(commands).entrySet()) {
                    if (entry.getValue() instanceof PluginCommand) {
                        PluginCommand c = (PluginCommand)entry.getValue();
                        if (c.getPlugin() != plugin) continue;
                        c.unregister(commandMap);
                        modifiedKnownCommands.remove(entry.getKey());
                        continue;
                    }
                    try {
                        this._bukkitPluginManager.unregisterNonPluginCommands(plugin, commandMap, modifiedKnownCommands, entry);
                    }
                    catch (IllegalStateException e) {
                        if (!e.getMessage().equalsIgnoreCase("zip file closed")) continue;
                        ((Command)entry.getValue()).unregister(commandMap);
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
        this.closeClassLoader(plugin);
        try {
            Class<?> paper = Class.forName("io.papermc.paper.plugin.manager.PaperPluginManagerImpl");
            Object paperPluginManagerImpl = paper.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            Field instanceManagerField = paperPluginManagerImpl.getClass().getDeclaredField("instanceManager");
            instanceManagerField.setAccessible(true);
            Object instanceManager = instanceManagerField.get(paperPluginManagerImpl);
            Field lookupNamesField = instanceManager.getClass().getDeclaredField("lookupNames");
            lookupNamesField.setAccessible(true);
            Map lookupNames = (Map)lookupNamesField.get(instanceManager);
            Method disableMethod = instanceManager.getClass().getMethod("disablePlugin", Plugin.class);
            disableMethod.setAccessible(true);
            disableMethod.invoke(instanceManager, plugin);
            lookupNames.remove(plugin.getName().toLowerCase());
            Field pluginListField = instanceManager.getClass().getDeclaredField("plugins");
            pluginListField.setAccessible(true);
            List pluginList = (List)pluginListField.get(instanceManager);
            pluginList.remove(plugin);
        }
        catch (Exception exception) {
            // empty catch block
        }
        System.gc();
        return "";
    }

    public List<String> getDisabledPluginNames(boolean fullName) {
        return this._bukkitPluginManager.getDisabledPluginNames(fullName);
    }

    public List<String> findByCommand(String command) {
        return this._bukkitPluginManager.findByCommand(command);
    }

    public void disable(Plugin plugin) {
        this._bukkitPluginManager.disable(plugin);
    }

    private void loadCommands(Plugin target) {
        this._bukkitPluginManager.loadCommands(target);
    }

    public String load(String name) {
        Plugin target = null;
        boolean paperLoaded = false;
        File pluginDir = new File("plugins");
        if (!pluginDir.isDirectory()) {
            return "";
        }
        File pluginFile = new File(pluginDir, name + ".jar");
        if (!pluginFile.isFile()) {
            for (File f : pluginDir.listFiles()) {
                if (!f.getName().endsWith(".jar")) continue;
                try {
                    PluginDescriptionFile desc = this.getPluginDescription(f);
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
            Class<?> paper = Class.forName("io.papermc.paper.plugin.manager.PaperPluginManagerImpl");
            Object paperPluginManagerImpl = paper.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            Field instanceManagerF = paperPluginManagerImpl.getClass().getDeclaredField("instanceManager");
            instanceManagerF.setAccessible(true);
            Object instanceManager = instanceManagerF.get(paperPluginManagerImpl);
            Method loadMethod = instanceManager.getClass().getMethod("loadPlugin", Path.class);
            loadMethod.setAccessible(true);
            target = (Plugin)loadMethod.invoke(instanceManager, pluginFile.toPath());
            Method enableMethod = instanceManager.getClass().getMethod("enablePlugin", Plugin.class);
            enableMethod.setAccessible(true);
            enableMethod.invoke(instanceManager, target);
            paperLoaded = true;
        }
        catch (Exception paper) {
            // empty catch block
        }
        if (!paperLoaded) {
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
        }
        if (zNb\u015b.Y\u017btq.getBukkitCommandWrap() instanceof BukkitCommandWrapUseless) return "";
        Plugin finalTarget = target;
        if (this.isFolia()) {
            FoliaLib foliaLib = new FoliaLib((JavaPlugin)zNb\u015b.Y\u017btq);
            foliaLib.getImpl().runLater(() -> this.loadCommands(finalTarget), 500L, TimeUnit.MILLISECONDS);
        } else {
            Bukkit.getScheduler().runTaskLater((Plugin)zNb\u015b.Y\u017btq, () -> this.loadCommands(finalTarget), 10L);
        }
        zNb\u015b.Y\u017btq.getFilePluginMap().put(pluginFile.getName(), target.getName());
        return "";
    }

    public PaperPluginManager(BukkitPluginManager bukkitPluginManager) {
        this._bukkitPluginManager = bukkitPluginManager;
        try {
            Class<?> pluginClassLoader = Class.forName("org.bukkit.plugin.java.PluginClassLoader");
            Field pluginClassLoaderPlugin = pluginClassLoader.getDeclaredField("plugin");
            pluginClassLoaderPlugin.setAccessible(true);
        }
        catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public void enable(Plugin plugin) {
        this._bukkitPluginManager.enable(plugin);
    }

    public boolean isIgnored(String plugin) {
        return this._bukkitPluginManager.isIgnored(plugin);
    }
}
