/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit.MinecraftReflection;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

class PaperFacet<V extends CommandSender>
extends FacetBase<V> {
    private static final Class<?> NATIVE_GSON_COMPONENT_SERIALIZER_IMPL_CLASS;
    private static final boolean SUPPORTED;
    static final Class<?> NATIVE_COMPONENT_CLASS;
    private static final MethodHandle NATIVE_GSON_COMPONENT_SERIALIZER_GSON_GETTER;
    private static final MethodHandle NATIVE_GSON_COMPONENT_SERIALIZER_DESERIALIZE_METHOD;
    private static final Class<?> NATIVE_GSON_COMPONENT_SERIALIZER_CLASS;
    private static final MethodHandle PAPER_ADVENTURE_AS_VANILLA;

    static /* synthetic */ MethodHandle access$000() {
        return NATIVE_GSON_COMPONENT_SERIALIZER_GSON_GETTER;
    }

    @Nullable
    private static MethodHandle findNativeDeserializeMethod() {
        try {
            Method method = NATIVE_GSON_COMPONENT_SERIALIZER_IMPL_CLASS.getDeclaredMethod("deserialize", String.class);
            method.setAccessible(true);
            return MinecraftReflection.lookup().unreflect(method);
        }
        catch (IllegalAccessException | NoSuchMethodException | NullPointerException e) {
            return null;
        }
    }

    @Nullable
    private static MethodHandle findAsVanillaMethod() {
        try {
            Class paperAdventure = MinecraftReflection.findClass((String[])new String[]{"io.papermc.paper.adventure.PaperAdventure"});
            Method method = paperAdventure.getDeclaredMethod("asVanilla", NATIVE_COMPONENT_CLASS);
            return MinecraftReflection.lookup().unreflect(method);
        }
        catch (IllegalAccessException | NoSuchMethodException | NullPointerException e) {
            return null;
        }
    }

    static /* synthetic */ MethodHandle access$100() {
        return NATIVE_GSON_COMPONENT_SERIALIZER_DESERIALIZE_METHOD;
    }

    static {
        SUPPORTED = Knob.isEnabled((String)"paper", (boolean)true);
        NATIVE_COMPONENT_CLASS = MinecraftReflection.findClass((String[])new String[]{String.join((CharSequence)".", "net", "kyori", "adventure", "text", "Component")});
        PAPER_ADVENTURE_AS_VANILLA = PaperFacet.findAsVanillaMethod();
        NATIVE_GSON_COMPONENT_SERIALIZER_CLASS = MinecraftReflection.findClass((String[])new String[]{String.join((CharSequence)".", "net", "kyori", "adventure", "text", "serializer", "gson", "GsonComponentSerializer")});
        NATIVE_GSON_COMPONENT_SERIALIZER_IMPL_CLASS = MinecraftReflection.findClass((String[])new String[]{String.join((CharSequence)".", "net", "kyori", "adventure", "text", "serializer", "gson", "GsonComponentSerializerImpl")});
        NATIVE_GSON_COMPONENT_SERIALIZER_GSON_GETTER = MinecraftReflection.findStaticMethod(NATIVE_GSON_COMPONENT_SERIALIZER_CLASS, (String)"gson", NATIVE_GSON_COMPONENT_SERIALIZER_CLASS, (Class[])new Class[0]);
        NATIVE_GSON_COMPONENT_SERIALIZER_DESERIALIZE_METHOD = PaperFacet.findNativeDeserializeMethod();
    }

    static /* synthetic */ MethodHandle access$200() {
        return PAPER_ADVENTURE_AS_VANILLA;
    }

    protected PaperFacet(@Nullable Class<? extends V> viewerClass) {
        super(viewerClass);
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED;
    }
}
