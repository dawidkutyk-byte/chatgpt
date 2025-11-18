/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiences
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiences$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiencesImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.GlobalTranslator
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.graph.MutableGraph;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiences;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.BukkitAudiencesImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.translation.GlobalTranslator;

/*
 * Exception performing whole class analysis ignored.
 */
static final class BukkitAudiencesImpl.Builder
implements BukkitAudiences.Builder {
    private ComponentRenderer<Pointered> componentRenderer;
    @NotNull
    private final Plugin plugin;

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull BukkitAudiences.Builder partition(@NotNull Function<Pointered, ?> partitionFunction) {
        Objects.requireNonNull(partitionFunction, "partitionFunction");
        return this;
    }

    private void softDepend(@NotNull String pluginName) {
        PluginDescriptionFile file = this.plugin.getDescription();
        if (file.getName().equals(pluginName)) {
            return;
        }
        try {
            Field softDepend = MinecraftReflection.needField(file.getClass(), (String)"softDepend");
            List dependencies = (List)softDepend.get(file);
            if (!dependencies.contains(pluginName)) {
                ImmutableCollection newList = ((ImmutableList.Builder)((ImmutableList.Builder)ImmutableList.builder().addAll((Iterable)dependencies)).add(pluginName)).build();
                softDepend.set(file, newList);
            }
        }
        catch (Throwable error) {
            Knob.logError((Throwable)error, (String)"Failed to inject softDepend in plugin.yml: %s %s", (Object[])new Object[]{this.plugin, pluginName});
        }
        try {
            PluginManager manager = this.plugin.getServer().getPluginManager();
            Field dependencyGraphField = MinecraftReflection.needField(manager.getClass(), (String)"dependencyGraph");
            MutableGraph graph = (MutableGraph)dependencyGraphField.get(manager);
            graph.putEdge(file.getName(), pluginName);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @NotNull
    public BukkitAudiencesImpl.Builder componentRenderer(@NotNull ComponentRenderer<Pointered> componentRenderer) {
        this.componentRenderer = Objects.requireNonNull(componentRenderer, "component renderer");
        return this;
    }

    @NotNull
    public BukkitAudiences build() {
        return BukkitAudiencesImpl.access$000().computeIfAbsent(this.plugin.getName(), name -> {
            this.softDepend("ViaVersion");
            return new BukkitAudiencesImpl(this.plugin, this.componentRenderer);
        });
    }

    BukkitAudiencesImpl.Builder(@NotNull Plugin plugin) {
        this.plugin = Objects.requireNonNull(plugin, "plugin");
        this.componentRenderer(ptr -> (Locale)ptr.getOrDefault(Identity.LOCALE, (Object)BukkitAudiencesImpl.access$100()), (ComponentRenderer)GlobalTranslator.renderer());
    }
}
