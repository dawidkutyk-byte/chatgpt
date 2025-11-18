/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Knob {
    private static final Set<Object> UNSUPPORTED;
    public static volatile Consumer<String> OUT;
    private static final String NAMESPACE;
    public static final boolean DEBUG;
    public static volatile BiConsumer<String, Throwable> ERR;

    public static void logMessage(@NotNull String format, Object ... arguments) {
        if (!DEBUG) return;
        OUT.accept(String.format(format, arguments));
    }

    public static void logError(@Nullable Throwable error, @NotNull String format, Object ... arguments) {
        if (!DEBUG) return;
        ERR.accept(String.format(format, arguments), error);
    }

    public static boolean isEnabled(@NotNull String key, boolean defaultValue) {
        return System.getProperty(NAMESPACE + "." + key, Boolean.toString(defaultValue)).equalsIgnoreCase("true");
    }

    public static void logUnsupported(@NotNull Object facet, @NotNull Object value) {
        if (!DEBUG) return;
        if (!UNSUPPORTED.add(value)) return;
        OUT.accept(String.format("Unsupported value '%s' for facet: %s", value, facet));
    }

    static {
        NAMESPACE = "net.kyo".concat("ri.adventure");
        DEBUG = Knob.isEnabled("debug", false);
        UNSUPPORTED = new CopyOnWriteArraySet<Object>();
        OUT = System.out::println;
        ERR = (message, err) -> {
            System.err.println((String)message);
            if (err == null) return;
            err.printStackTrace(System.err);
        };
    }

    private Knob() {
    }
}
