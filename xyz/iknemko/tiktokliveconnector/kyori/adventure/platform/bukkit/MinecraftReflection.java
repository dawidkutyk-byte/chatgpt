/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.bukkit;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class MinecraftReflection {
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final String PREFIX_NMS = "net.minecraft.server";
    @Nullable
    private static final String VERSION;
    private static final String PREFIX_CRAFTBUKKIT = "org.bukkit.craftbukkit";
    private static final String CRAFT_SERVER = "CraftServer";
    private static final String PREFIX_MC = "net.minecraft.";

    @Nullable
    public static Object findEnum(@Nullable Class<?> enumClass, @NotNull String enumName, int enumFallbackOrdinal) {
        if (enumClass == null) return null;
        if (!Enum.class.isAssignableFrom(enumClass)) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass.asSubclass(Enum.class), enumName);
        }
        catch (IllegalArgumentException e) {
            ?[] constants = enumClass.getEnumConstants();
            if (constants.length <= enumFallbackOrdinal) return null;
            return constants[enumFallbackOrdinal];
        }
    }

    public static boolean hasField(@Nullable Class<?> holderClass, Class<?> type, String ... names) {
        if (holderClass == null) {
            return false;
        }
        String[] stringArray = names;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String name = stringArray[n2];
            try {
                Field field = holderClass.getDeclaredField(name);
                if (field.getType() == type) {
                    return true;
                }
            }
            catch (NoSuchFieldException noSuchFieldException) {
                // empty catch block
            }
            ++n2;
        }
        return false;
    }

    public static MethodHandle searchMethod(@Nullable Class<?> holderClass, @Nullable Integer modifier, @Nullable String @NotNull [] methodNames, @Nullable Class<?> returnClass, Class<?> ... parameterClasses) {
        if (holderClass == null) return null;
        if (returnClass == null) {
            return null;
        }
        for (Class<?> parameterClass : parameterClasses) {
            if (parameterClass != null) continue;
            return null;
        }
        for (String methodName : methodNames) {
            if (methodName == null) continue;
            try {
                if (modifier == null) return LOOKUP.findVirtual(holderClass, methodName, MethodType.methodType(returnClass, parameterClasses));
                if (!Modifier.isStatic(modifier)) return LOOKUP.findVirtual(holderClass, methodName, MethodType.methodType(returnClass, parameterClasses));
                return LOOKUP.findStatic(holderClass, methodName, MethodType.methodType(returnClass, parameterClasses));
            }
            catch (IllegalAccessException | NoSuchMethodException reflectiveOperationException) {
                // empty catch block
            }
        }
        Object[] objectArray = holderClass.getDeclaredMethods();
        int n = objectArray.length;
        int n2 = 0;
        while (n2 < n) {
            Object method = objectArray[n2];
            if (modifier != null && (((Method)method).getModifiers() & modifier) != 0 && Arrays.equals(((Method)method).getParameterTypes(), parameterClasses)) {
                try {
                    if (!Modifier.isStatic(modifier)) return LOOKUP.findVirtual(holderClass, ((Method)method).getName(), MethodType.methodType(returnClass, parameterClasses));
                    return LOOKUP.findStatic(holderClass, ((Method)method).getName(), MethodType.methodType(returnClass, parameterClasses));
                }
                catch (IllegalAccessException | NoSuchMethodException reflectiveOperationException) {
                    // empty catch block
                }
            }
            ++n2;
        }
        return null;
    }

    @Nullable
    public static MethodHandle findStaticMethod(@Nullable Class<?> holderClass, String methodNames, @Nullable Class<?> returnClass, Class<?> ... parameterClasses) {
        return MinecraftReflection.findStaticMethod(holderClass, new String[]{methodNames}, returnClass, parameterClasses);
    }

    @NotNull
    public static Class<?> needNmsClass(@NotNull String className) {
        return Objects.requireNonNull(MinecraftReflection.findNmsClass(className), "Could not find net.minecraft.server class " + className);
    }

    @NotNull
    public static Field needField(@NotNull Class<?> holderClass, @NotNull String fieldName) throws NoSuchFieldException {
        Field field = holderClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    public static boolean isCraftBukkit() {
        return VERSION != null;
    }

    public static MethodHandle searchMethod(@Nullable Class<?> holderClass, @Nullable Integer modifier, String methodName, @Nullable Class<?> returnClass, Class<?> ... parameterClasses) {
        return MinecraftReflection.searchMethod(holderClass, modifier, new String[]{methodName}, returnClass, parameterClasses);
    }

    @NotNull
    public static Class<?> needClass(String ... className) {
        return Objects.requireNonNull(MinecraftReflection.findClass(className), "Could not find class from candidates" + Arrays.toString(className));
    }

    @Nullable
    public static Field findField(@Nullable Class<?> holderClass, String ... fieldName) {
        return MinecraftReflection.findField(holderClass, null, fieldName);
    }

    @Nullable
    public static MethodHandle findConstructor(@Nullable Class<?> holderClass, Class<?> ... parameterClasses) {
        if (holderClass == null) {
            return null;
        }
        for (Class<?> parameterClass : parameterClasses) {
            if (parameterClass != null) continue;
            return null;
        }
        try {
            return LOOKUP.findConstructor(holderClass, MethodType.methodType(Void.TYPE, parameterClasses));
        }
        catch (IllegalAccessException | NoSuchMethodException e) {
            return null;
        }
    }

    public static @NotNull MethodHandles.Lookup lookup() {
        return LOOKUP;
    }

    @Nullable
    public static Object findEnum(@Nullable Class<?> enumClass, @NotNull String enumName) {
        return MinecraftReflection.findEnum(enumClass, enumName, Integer.MAX_VALUE);
    }

    @Nullable
    public static String findNmsClassName(@NotNull String className) {
        return MinecraftReflection.isCraftBukkit() ? PREFIX_NMS + VERSION + className : null;
    }

    public static boolean hasMethod(@Nullable Class<?> holderClass, String methodName, Class<?> ... parameterClasses) {
        return MinecraftReflection.hasMethod(holderClass, new String[]{methodName}, parameterClasses);
    }

    @Nullable
    public static Class<?> findClass(String ... classNames) {
        String[] stringArray = classNames;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String clazz = stringArray[n2];
            if (clazz != null) {
                try {
                    Class<?> classObj = Class.forName(clazz);
                    return classObj;
                }
                catch (ClassNotFoundException classNotFoundException) {
                    // empty catch block
                }
            }
            ++n2;
        }
        return null;
    }

    @NotNull
    public static Class<?> needCraftClass(@NotNull String className) {
        return Objects.requireNonNull(MinecraftReflection.findCraftClass(className), "Could not find org.bukkit.craftbukkit class " + className);
    }

    public static boolean hasMethod(@Nullable Class<?> holderClass, String[] methodNames, Class<?> ... parameterClasses) {
        if (holderClass == null) {
            return false;
        }
        for (Class<?> parameterClass : parameterClasses) {
            if (parameterClass != null) continue;
            return false;
        }
        Object[] objectArray = methodNames;
        int n = objectArray.length;
        int n2 = 0;
        while (n2 < n) {
            Object methodName = objectArray[n2];
            try {
                holderClass.getMethod((String)methodName, parameterClasses);
                return true;
            }
            catch (NoSuchMethodException noSuchMethodException) {
                ++n2;
            }
        }
        return false;
    }

    private MinecraftReflection() {
    }

    @Nullable
    public static Class<?> findCraftClass(@NotNull String className) {
        String craftClassName = MinecraftReflection.findCraftClassName(className);
        if (craftClassName != null) return MinecraftReflection.findClass(craftClassName);
        return null;
    }

    @Nullable
    public static MethodHandle findGetterOf(@Nullable Field field) {
        if (field == null) {
            return null;
        }
        try {
            return LOOKUP.unreflectGetter(field);
        }
        catch (IllegalAccessException e) {
            return null;
        }
    }

    @Nullable
    public static Class<?> findMcClass(String ... classNames) {
        String[] stringArray = classNames;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            Class<?> candidate;
            String clazz = stringArray[n2];
            String nmsClassName = MinecraftReflection.findMcClassName(clazz);
            if (nmsClassName != null && (candidate = MinecraftReflection.findClass(nmsClassName)) != null) {
                return candidate;
            }
            ++n2;
        }
        return null;
    }

    @Nullable
    public static String findMcClassName(@NotNull String className) {
        return MinecraftReflection.isCraftBukkit() ? PREFIX_MC + className : null;
    }

    @Nullable
    public static String findCraftClassName(@NotNull String className) {
        return MinecraftReflection.isCraftBukkit() ? PREFIX_CRAFTBUKKIT + VERSION + className : null;
    }

    public static boolean hasClass(String ... classNames) {
        return MinecraftReflection.findClass(classNames) != null;
    }

    @Nullable
    public static <T> Class<? extends T> findCraftClass(@NotNull String className, @NotNull Class<T> superClass) {
        Class<?> craftClass = MinecraftReflection.findCraftClass(className);
        if (craftClass == null) return null;
        if (Objects.requireNonNull(superClass, "superClass").isAssignableFrom(craftClass)) return craftClass.asSubclass(superClass);
        return null;
    }

    @Nullable
    public static MethodHandle findMethod(@Nullable Class<?> holderClass, String methodName, @Nullable Class<?> returnClass, Class<?> ... parameterClasses) {
        return MinecraftReflection.findMethod(holderClass, new String[]{methodName}, returnClass, parameterClasses);
    }

    @Nullable
    public static MethodHandle findStaticMethod(@Nullable Class<?> holderClass, String[] methodNames, @Nullable Class<?> returnClass, Class<?> ... parameterClasses) {
        if (holderClass == null) return null;
        if (returnClass == null) {
            return null;
        }
        for (Class<?> parameterClass : parameterClasses) {
            if (parameterClass != null) continue;
            return null;
        }
        Object[] objectArray = methodNames;
        int n = objectArray.length;
        int n2 = 0;
        while (n2 < n) {
            Object methodName = objectArray[n2];
            try {
                return LOOKUP.findStatic(holderClass, (String)methodName, MethodType.methodType(returnClass, parameterClasses));
            }
            catch (IllegalAccessException | NoSuchMethodException reflectiveOperationException) {
                ++n2;
            }
        }
        return null;
    }

    static {
        Class<?> serverClass = Bukkit.getServer().getClass();
        if (!serverClass.getSimpleName().equals(CRAFT_SERVER)) {
            VERSION = null;
        } else if (serverClass.getName().equals("org.bukkit.craftbukkit.CraftServer")) {
            VERSION = ".";
        } else {
            String name = serverClass.getName();
            name = name.substring(PREFIX_CRAFTBUKKIT.length());
            VERSION = name = name.substring(0, name.length() - CRAFT_SERVER.length());
        }
    }

    @Nullable
    public static MethodHandle findMethod(@Nullable Class<?> holderClass, @Nullable String @NotNull [] methodNames, @Nullable Class<?> returnClass, Class<?> ... parameterClasses) {
        if (holderClass == null) return null;
        if (returnClass == null) {
            return null;
        }
        for (Class<?> parameterClass : parameterClasses) {
            if (parameterClass != null) continue;
            return null;
        }
        Object[] objectArray = methodNames;
        int n = objectArray.length;
        int n2 = 0;
        while (n2 < n) {
            Object methodName = objectArray[n2];
            if (methodName != null) {
                try {
                    return LOOKUP.findVirtual(holderClass, (String)methodName, MethodType.methodType(returnClass, parameterClasses));
                }
                catch (IllegalAccessException | NoSuchMethodException reflectiveOperationException) {
                    // empty catch block
                }
            }
            ++n2;
        }
        return null;
    }

    @Nullable
    public static Class<?> findNmsClass(@NotNull String className) {
        String nmsClassName = MinecraftReflection.findNmsClassName(className);
        if (nmsClassName != null) return MinecraftReflection.findClass(nmsClassName);
        return null;
    }

    @NotNull
    public static Class<?> needMcClass(String ... className) {
        return Objects.requireNonNull(MinecraftReflection.findMcClass(className), "Could not find net.minecraft class from candidates" + Arrays.toString(className));
    }

    @Nullable
    public static MethodHandle findSetterOf(@Nullable Field field) {
        if (field == null) {
            return null;
        }
        try {
            return LOOKUP.unreflectSetter(field);
        }
        catch (IllegalAccessException e) {
            return null;
        }
    }

    @Nullable
    public static Field findField(@Nullable Class<?> holderClass, @Nullable Class<?> expectedType, String ... fieldNames) {
        if (holderClass == null) {
            return null;
        }
        String[] stringArray = fieldNames;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            block4: {
                Field field;
                String fieldName = stringArray[n2];
                try {
                    field = holderClass.getDeclaredField(fieldName);
                }
                catch (NoSuchFieldException ex) {
                    break block4;
                }
                field.setAccessible(true);
                if (expectedType == null) return field;
                if (expectedType.isAssignableFrom(field.getType())) return field;
            }
            ++n2;
        }
        return null;
    }
}
