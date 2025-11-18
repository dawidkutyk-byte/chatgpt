/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.rylinaux.plugman.pluginmanager.PluginManager
 *  org.bukkit.plugin.java.JavaPlugin
 */
package com.rylinaux.plugman;

import com.rylinaux.plugman.pluginmanager.PluginManager;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.plugin.java.JavaPlugin;

public class PlugMan
extends JavaPlugin {
    private final HashMap<String, String> fileHashMap;
    private Field lookupNamesField = null;
    private final HashMap<String, String> filePluginMap;
    private List<String> ignoredPlugins = null;
    private boolean notifyOnBrokenCommandRemoval;
    private static PlugMan instance = null;
    private PluginManager pluginManager;
    private final HashMap<String, Map.Entry<Long, Boolean>> resourceMap = new HashMap();

    public HashMap<String, String> getFilePluginMap() {
        return this.filePluginMap;
    }

    public HashMap<String, Map.Entry<Long, Boolean>> getResourceMap() {
        return this.resourceMap;
    }

    public List<String> getIgnoredPlugins() {
        return this.ignoredPlugins;
    }

    public PlugMan() {
        this.fileHashMap = new HashMap();
        this.filePluginMap = new HashMap();
    }

    private static InputStream getResourceStatic(String filename) {
        try {
            URL url = PlugMan.class.getClassLoader().getResource(filename);
            if (url == null) {
                return null;
            }
            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        }
        catch (IOException var4) {
            return null;
        }
    }

    public static PlugMan getInstance() {
        return instance;
    }

    public PluginManager getPluginUtil() {
        return this.pluginManager;
    }

    public boolean isNotifyOnBrokenCommandRemoval() {
        return this.notifyOnBrokenCommandRemoval;
    }
}
