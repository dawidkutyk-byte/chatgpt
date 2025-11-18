/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j;

import java.io.Closeable;
import org.slf4j.MDC;

public static class MDC.MDCCloseable
implements Closeable {
    private final String key;

    @Override
    public void close() {
        MDC.remove(this.key);
    }

    private MDC.MDCCloseable(String key) {
        this.key = key;
    }
}
