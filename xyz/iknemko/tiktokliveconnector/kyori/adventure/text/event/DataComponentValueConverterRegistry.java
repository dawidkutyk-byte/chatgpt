/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry$ConversionCache
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry$Provider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValue;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.event.DataComponentValueConverterRegistry;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Services;

/*
 * Exception performing whole class analysis ignored.
 */
public final class DataComponentValueConverterRegistry {
    private static final Set<Provider> PROVIDERS = Services.services(Provider.class);

    private DataComponentValueConverterRegistry() {
    }

    static /* synthetic */ Set access$000() {
        return PROVIDERS;
    }

    @NotNull
    public static <O extends DataComponentValue> O convert(@NotNull Class<O> target, @NotNull Key key, @NotNull DataComponentValue in) {
        if (target.isInstance(in)) {
            return (O)((DataComponentValue)target.cast(in));
        }
        // Could not load outer class - annotation placement on inner may be incorrect
        @Nullable DataComponentValueConverterRegistry.RegisteredConversion converter = ConversionCache.converter(in.getClass(), target);
        if (converter == null) {
            throw new IllegalArgumentException("There is no data holder converter registered to convert from a " + in.getClass() + " instance to a " + target + " (on field " + key + ")");
        }
        try {
            return (O)((DataComponentValue)converter.conversion.convert(key, (Object)in));
        }
        catch (Exception ex) {
            throw new IllegalStateException("Failed to convert data component value of type " + in.getClass() + " to type " + target + " due to an error in a converter provided by " + converter.provider.asString() + "!", ex);
        }
    }

    public static Set<Key> knownProviders() {
        return Collections.unmodifiableSet(PROVIDERS.stream().map(Provider::id).collect(Collectors.toSet()));
    }
}
