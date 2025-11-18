/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.Command
 *  org.bukkit.plugin.Plugin
 */
package com.rylinaux.plugman.pluginmanager;

import java.util.List;
import java.util.Map;
import org.bukkit.command.Command;
import org.bukkit.plugin.Plugin;

public interface PluginManager {
    public void disableAll();

    public List<String> getDisabledPluginNames(boolean var1);

    public String unload(Plugin var1);

    public void enableAll();

    public void disable(Plugin var1);

    public boolean isPaperPlugin(Plugin var1);

    public void enable(Plugin var1);

    public String getFormattedName(Plugin var1);

    public List<String> getPluginNames(boolean var1);

    public List<String> getEnabledPluginNames(boolean var1);

    public Map<String, Command> getKnownCommands();

    public List<String> findByCommand(String var1);

    public void reload(Plugin var1);

    public void setKnownCommands(Map<String, Command> var1);

    public Plugin getPluginByName(String[] var1, int var2);

    public String getUsages(Plugin var1);

    public boolean isIgnored(String var1);

    public String getPluginVersion(String var1);

    public String getFormattedName(Plugin var1, boolean var2);

    public boolean isIgnored(Plugin var1);

    public Plugin getPluginByName(String var1);

    public String load(String var1);

    public void reloadAll();
}
