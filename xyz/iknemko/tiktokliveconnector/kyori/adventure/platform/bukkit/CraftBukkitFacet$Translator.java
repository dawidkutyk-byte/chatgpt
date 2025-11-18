/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Server
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetComponentFlattener$Translator
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetComponentFlattener;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

static final class CraftBukkitFacet.Translator
extends FacetBase<Server>
implements FacetComponentFlattener.Translator<Server> {
    private static final MethodHandle LANGUAGE_GET_OR_DEFAULT;
    private static final MethodHandle LANGUAGE_GET_INSTANCE;
    private static final Class<?> CLASS_LANGUAGE;

    private static MethodHandle unreflectUnchecked(Method m) {
        try {
            m.setAccessible(true);
            return MinecraftReflection.lookup().unreflect(m);
        }
        catch (IllegalAccessException ex) {
            return null;
        }
    }

    CraftBukkitFacet.Translator() {
        super(Server.class);
    }

    public boolean isSupported() {
        return super.isSupported() && LANGUAGE_GET_INSTANCE != null && LANGUAGE_GET_OR_DEFAULT != null;
    }

    @NotNull
    public String valueOrDefault(@NotNull Server game, @NotNull String key) {
        try {
            return LANGUAGE_GET_OR_DEFAULT.invoke(LANGUAGE_GET_INSTANCE.invoke(), key);
        }
        catch (Throwable ex) {
            Knob.logError((Throwable)ex, (String)"Failed to transate key '%s'", (Object[])new Object[]{key});
            return key;
        }
    }

    static {
        CLASS_LANGUAGE = MinecraftReflection.findClass((String[])new String[]{MinecraftReflection.findNmsClassName((String)"LocaleLanguage"), MinecraftReflection.findMcClassName((String)"locale.LocaleLanguage"), MinecraftReflection.findMcClassName((String)"locale.Language")});
        if (CLASS_LANGUAGE == null) {
            LANGUAGE_GET_INSTANCE = null;
            LANGUAGE_GET_OR_DEFAULT = null;
        } else {
            LANGUAGE_GET_INSTANCE = Arrays.stream(CLASS_LANGUAGE.getDeclaredMethods()).filter(m -> Modifier.isStatic(m.getModifiers()) && !Modifier.isPrivate(m.getModifiers()) && m.getReturnType().equals(CLASS_LANGUAGE) && m.getParameterCount() == 0).findFirst().map(CraftBukkitFacet.Translator::unreflectUnchecked).orElse(null);
            LANGUAGE_GET_OR_DEFAULT = Arrays.stream(CLASS_LANGUAGE.getDeclaredMethods()).filter(m -> !Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers()) && m.getParameterCount() == 1 && m.getParameterTypes()[0] == String.class && m.getReturnType().equals(String.class)).findFirst().map(CraftBukkitFacet.Translator::unreflectUnchecked).orElse(null);
        }
    }
}
