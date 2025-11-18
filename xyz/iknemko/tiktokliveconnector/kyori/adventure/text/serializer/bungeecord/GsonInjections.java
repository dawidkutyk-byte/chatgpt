/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.bungeecord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Excluder;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

final class GsonInjections {
    public static Field field(@NotNull Class<?> klass, @NotNull String name) throws NoSuchFieldException {
        Field field = klass.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    private static int findExcluderIndex(@NotNull List<TypeAdapterFactory> factories) {
        int i = 0;
        int size = factories.size();
        while (i < size) {
            TypeAdapterFactory factory = factories.get(i);
            if (factory instanceof Excluder) {
                return i + 1;
            }
            ++i;
        }
        return 0;
    }

    private GsonInjections() {
    }

    public static boolean injectGson(@NotNull Gson existing, @NotNull Consumer<GsonBuilder> accepter) {
        try {
            Field factoriesField = GsonInjections.field(Gson.class, "factories");
            Field builderFactoriesField = GsonInjections.field(GsonBuilder.class, "factories");
            Field builderHierarchyFactoriesField = GsonInjections.field(GsonBuilder.class, "hierarchyFactories");
            GsonBuilder builder = new GsonBuilder();
            accepter.accept(builder);
            List existingFactories = (List)factoriesField.get(existing);
            ArrayList newFactories = new ArrayList();
            newFactories.addAll((List)builderFactoriesField.get(builder));
            Collections.reverse(newFactories);
            newFactories.addAll((List)builderHierarchyFactoriesField.get(builder));
            ArrayList<TypeAdapterFactory> modifiedFactories = new ArrayList<TypeAdapterFactory>(existingFactories);
            int index = GsonInjections.findExcluderIndex(modifiedFactories);
            Collections.reverse(newFactories);
            Iterator iterator = newFactories.iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    factoriesField.set(existing, modifiedFactories);
                    return true;
                }
                TypeAdapterFactory newFactory = (TypeAdapterFactory)iterator.next();
                modifiedFactories.add(index, newFactory);
            }
        }
        catch (IllegalAccessException | NoSuchFieldException ex) {
            return false;
        }
    }
}
