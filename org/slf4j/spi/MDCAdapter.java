/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.spi;

import java.util.Map;

public interface MDCAdapter {
    public Map<String, String> getCopyOfContextMap();

    public void clear();

    public void put(String var1, String var2);

    public void remove(String var1);

    public void setContextMap(Map<String, String> var1);

    public String get(String var1);
}
