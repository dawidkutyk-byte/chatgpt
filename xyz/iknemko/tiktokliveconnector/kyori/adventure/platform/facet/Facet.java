/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Supplier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;

@ApiStatus.Internal
public interface Facet<V> {
    @Nullable
    public static <V, F extends Facet<V>> F of(@Nullable Collection<F> facets, @Nullable V viewer) {
        if (facets == null) return null;
        if (viewer == null) {
            return null;
        }
        Iterator<F> iterator = facets.iterator();
        while (iterator.hasNext()) {
            Facet facet = (Facet)iterator.next();
            try {
                if (facet.isApplicable(viewer)) {
                    Knob.logMessage((String)"Selected facet: %s for %s", (Object[])new Object[]{facet, viewer});
                    return (F)facet;
                }
                if (!Knob.DEBUG) continue;
                Knob.logMessage((String)"Not selecting %s for %s", (Object[])new Object[]{facet, viewer});
            }
            catch (ClassCastException error) {
                if (!Knob.DEBUG) continue;
                Knob.logMessage((String)"Exception while getting facet %s for %s: %s", (Object[])new Object[]{facet, viewer, error.getMessage()});
            }
        }
        return null;
    }

    default public boolean isSupported() {
        return true;
    }

    default public boolean isApplicable(@NotNull V viewer) {
        return true;
    }

    @SafeVarargs
    @NotNull
    public static <V, F extends Facet<? extends V>> Collection<F> of(Supplier<F> ... suppliers) {
        LinkedList<Facet> facets = new LinkedList<Facet>();
        Supplier<F>[] supplierArray = suppliers;
        int n = supplierArray.length;
        int n2 = 0;
        while (n2 < n) {
            block6: {
                Facet facet;
                Supplier<F> supplier = supplierArray[n2];
                try {
                    facet = (Facet)supplier.get();
                }
                catch (NoClassDefFoundError error) {
                    Knob.logMessage((String)"Skipped facet: %s", (Object[])new Object[]{supplier.getClass().getName()});
                    break block6;
                }
                catch (Throwable error) {
                    Knob.logError((Throwable)error, (String)"Failed facet: %s", (Object[])new Object[]{supplier});
                    break block6;
                }
                if (!facet.isSupported()) {
                    Knob.logMessage((String)"Skipped facet: %s", (Object[])new Object[]{facet});
                } else {
                    facets.add(facet);
                    Knob.logMessage((String)"Added facet: %s", (Object[])new Object[]{facet});
                }
            }
            ++n2;
        }
        return facets;
    }
}
