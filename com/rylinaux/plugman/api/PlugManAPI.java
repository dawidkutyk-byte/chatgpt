/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.rylinaux.plugman.PlugMan
 *  com.rylinaux.plugman.api.GentleUnload
 */
package com.rylinaux.plugman.api;

import com.rylinaux.plugman.PlugMan;
import com.rylinaux.plugman.api.GentleUnload;
import java.util.HashMap;

public class PlugManAPI {
    protected static final HashMap<Object, GentleUnload> gentleUnloads = new HashMap();
    private static Class pluginClass = null;

    public static boolean iDoNotWantToBeUnOrReloaded(String plugin) {
        if (PlugMan.getInstance() == null) {
            return false;
        }
        if (PlugMan.getInstance().getIgnoredPlugins() == null) {
            return false;
        }
        PlugMan.getInstance().getIgnoredPlugins().add(plugin);
        return true;
    }

    public static boolean pleaseAddMeToGentleUnload(Object plugin, GentleUnload gentleUnload) {
        if (plugin == null) {
            return false;
        }
        if (gentleUnload == null) {
            return false;
        }
        if (pluginClass == null) {
            try {
                pluginClass = Class.forName("org.bukkit.plugin.Plugin");
            }
            catch (ClassNotFoundException e) {
                try {
                    pluginClass = Class.forName("net.md_5.bungee.api.plugin.Plugin");
                }
                catch (ClassNotFoundException ex) {
                    ex.addSuppressed(e);
                    ex.printStackTrace();
                    return false;
                }
            }
        }
        if (gentleUnloads.containsKey(plugin)) {
            return false;
        }
        if (!pluginClass.isInstance(plugin)) {
            return false;
        }
        gentleUnloads.put(plugin, gentleUnload);
        return true;
    }

    public static HashMap<Object, GentleUnload> getGentleUnloads() {
        return new HashMap<Object, GentleUnload>(gentleUnloads);
    }
}
