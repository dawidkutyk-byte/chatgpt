/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.ServiceLoader;

final class Services0 {
    static <S> ServiceLoader<S> loader(Class<S> type) {
        return ServiceLoader.load(type, type.getClassLoader());
    }

    private Services0() {
    }
}
