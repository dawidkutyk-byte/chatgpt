/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  fr.mrmicky.fastboard.FastReflection$PacketConstructor
 *  org.bukkit.Bukkit
 */
package fr.mrmicky.fastboard;

import fr.mrmicky.fastboard.FastReflection;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Predicate;
import org.bukkit.Bukkit;

public final class FastReflection {
    private static final MethodType VOID_METHOD_TYPE;
    private static final String NMS_PACKAGE;
    private static volatile Object theUnsafe;
    private static final String OBC_PACKAGE;
    private static final boolean MOJANG_MAPPINGS;
    private static final boolean NMS_REPACKAGED;
    private static final String NM_PACKAGE = "net.minecraft";

    static Optional<MethodHandle> optionalConstructor(Class<?> declaringClass, MethodHandles.Lookup lookup, MethodType type) throws IllegalAccessException {
        try {
            return Optional.of(lookup.findConstructor(declaringClass, type));
        }
        catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }

    public static String obcClassName(String className) {
        return OBC_PACKAGE + '.' + className;
    }

    public static Class<?> obcClass(String className) throws ClassNotFoundException {
        return Class.forName(FastReflection.obcClassName(className));
    }

    private FastReflection() {
        throw new UnsupportedOperationException();
    }

    public static Object enumValueOf(Class<?> enumClass, String enumName) {
        return Enum.valueOf(enumClass.asSubclass(Enum.class), enumName);
    }

    static Class<?> innerClass(Class<?> parentClass, Predicate<Class<?>> classPredicate) throws ClassNotFoundException {
        Class<?>[] classArray = parentClass.getDeclaredClasses();
        int n = classArray.length;
        int n2 = 0;
        while (n2 < n) {
            Class<?> innerClass = classArray[n2];
            if (classPredicate.test(innerClass)) {
                return innerClass;
            }
            ++n2;
        }
        throw new ClassNotFoundException("No class in " + parentClass.getCanonicalName() + " matches the predicate.");
    }

    public static Optional<Class<?>> optionalClass(String className) {
        try {
            return Optional.of(Class.forName(className));
        }
        catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    public static Optional<Class<?>> nmsOptionalClass(String post1_17package, String className) {
        return FastReflection.optionalClass(FastReflection.nmsClassName(post1_17package, className));
    }

    public static Object enumValueOf(Class<?> enumClass, String enumName, int fallbackOrdinal) {
        try {
            return FastReflection.enumValueOf(enumClass, enumName);
        }
        catch (IllegalArgumentException e) {
            ?[] constants = enumClass.getEnumConstants();
            if (constants.length <= fallbackOrdinal) throw e;
            return constants[fallbackOrdinal];
        }
    }

    public static Class<?> nmsClass(String post1_17package, String className) throws ClassNotFoundException {
        return Class.forName(FastReflection.nmsClassName(post1_17package, className));
    }

    public static String nmsClassName(String post1_17package, String className) {
        if (!NMS_REPACKAGED) return NMS_PACKAGE + '.' + className;
        String classPackage = post1_17package == null ? NM_PACKAGE : "net.minecraft." + post1_17package;
        return classPackage + '.' + className;
    }

    public static Optional<Class<?>> nmsOptionalClass(String post1_17package, String spigotClass, String mojangClass) {
        return FastReflection.optionalClass(FastReflection.nmsClassName(post1_17package, MOJANG_MAPPINGS ? mojangClass : spigotClass));
    }

    public static boolean isRepackaged() {
        return NMS_REPACKAGED;
    }

    static {
        OBC_PACKAGE = Bukkit.getServer().getClass().getPackage().getName();
        NMS_PACKAGE = OBC_PACKAGE.replace("org.bukkit.craftbukkit", "net.minecraft.server");
        VOID_METHOD_TYPE = MethodType.methodType(Void.TYPE);
        NMS_REPACKAGED = FastReflection.optionalClass("net.minecraft.network.protocol.Packet").isPresent();
        MOJANG_MAPPINGS = FastReflection.optionalClass("net.minecraft.network.chat.Component").isPresent();
    }

    public static Class<?> nmsClass(String post1_17package, String spigotClass, String mojangClass) throws ClassNotFoundException {
        return FastReflection.nmsClass(post1_17package, MOJANG_MAPPINGS ? mojangClass : spigotClass);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled unnecessary exception pruning
     * Converted monitor instructions to comments
     */
    public static PacketConstructor findPacketConstructor(Class<?> packetClass, MethodHandles.Lookup lookup) throws Exception {
        try {
            MethodHandle constructor2 = lookup.findConstructor(packetClass, VOID_METHOD_TYPE);
            return (PacketConstructor)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, invoke(), ()Ljava/lang/Object;)((MethodHandle)constructor2);
        }
        catch (IllegalAccessException | NoSuchMethodException constructor2) {
            if (theUnsafe == null) {
                Class<FastReflection> constructor2 = FastReflection.class;
                // MONITORENTER : fr.mrmicky.fastboard.FastReflection.class
                if (theUnsafe == null) {
                    Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
                    Field theUnsafeField = unsafeClass.getDeclaredField("theUnsafe");
                    theUnsafeField.setAccessible(true);
                    theUnsafe = theUnsafeField.get(null);
                }
                // MONITOREXIT : constructor2
            }
            MethodType allocateMethodType = MethodType.methodType(Object.class, Class.class);
            MethodHandle allocateMethod = lookup.findVirtual(theUnsafe.getClass(), "allocateInstance", allocateMethodType);
            return () -> allocateMethod.invoke(theUnsafe, packetClass);
        }
    }

    public static Optional<Class<?>> obcOptionalClass(String className) {
        return FastReflection.optionalClass(FastReflection.obcClassName(className));
    }
}
