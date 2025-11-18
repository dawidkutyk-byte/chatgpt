/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.config.Registry
 *  org.apache.http.util.Args
 */
package org.apache.http.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.config.Registry;
import org.apache.http.util.Args;

public final class RegistryBuilder<I> {
    private final Map<String, I> items = new HashMap<String, I>();

    public String toString() {
        return this.items.toString();
    }

    public Registry<I> build() {
        return new Registry(this.items);
    }

    public static <I> RegistryBuilder<I> create() {
        return new RegistryBuilder<I>();
    }

    public RegistryBuilder<I> register(String id, I item) {
        Args.notEmpty((CharSequence)id, (String)"ID");
        Args.notNull(item, (String)"Item");
        this.items.put(id.toLowerCase(Locale.ROOT), item);
        return this;
    }

    RegistryBuilder() {
    }
}
