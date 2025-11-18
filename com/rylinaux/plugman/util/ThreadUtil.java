/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.rylinaux.plugman.PlugMan
 *  org.bukkit.Bukkit
 *  org.bukkit.plugin.Plugin
 */
package com.rylinaux.plugman.util;

import com.rylinaux.plugman.PlugMan;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ThreadUtil {
    public static void async(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)PlugMan.getInstance(), runnable);
    }

    public static void sync(Runnable runnable) {
        Bukkit.getScheduler().runTask((Plugin)PlugMan.getInstance(), runnable);
    }
}
