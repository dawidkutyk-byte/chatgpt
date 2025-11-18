/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventurePropertiesImpl$PropertyImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.properties.AdventurePropertiesImpl;

final class AdventurePropertiesImpl {
    private static final String FILESYSTEM_FILE_NAME = "adventure.properties";
    private static final Properties PROPERTIES = new Properties();
    private static final String FILESYSTEM_DIRECTORY_NAME = "config";

    private static void print(Throwable ex) {
        ex.printStackTrace();
    }

    private AdventurePropertiesImpl() {
    }

    static {
        Path path = Optional.ofNullable(System.getProperty(AdventurePropertiesImpl.systemPropertyName(FILESYSTEM_DIRECTORY_NAME))).map(x$0 -> Paths.get(x$0, new String[0])).orElseGet(() -> Paths.get(FILESYSTEM_DIRECTORY_NAME, FILESYSTEM_FILE_NAME));
        if (!Files.isRegularFile(path, new LinkOption[0])) return;
        try (InputStream is = Files.newInputStream(path, new OpenOption[0]);){
            PROPERTIES.load(is);
        }
        catch (IOException e) {
            AdventurePropertiesImpl.print(e);
        }
    }

    static /* synthetic */ Properties access$000() {
        return PROPERTIES;
    }

    @VisibleForTesting
    @NotNull
    static String systemPropertyName(String name) {
        return String.join((CharSequence)".", "net", "kyori", "adventure", name);
    }

    static <T> // Could not load outer class - annotation placement on inner may be incorrect
     @NotNull AdventureProperties.Property<T> property(@NotNull String name, @NotNull Function<String, T> parser, @Nullable T defaultValue) {
        return new PropertyImpl(name, parser, defaultValue);
    }
}
