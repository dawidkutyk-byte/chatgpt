/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.event;

public enum Level {
    ERROR(40, "ERROR"),
    WARN(30, "WARN"),
    INFO(20, "INFO"),
    DEBUG(10, "DEBUG"),
    TRACE(0, "TRACE");

    private String levelStr;
    private int levelInt;

    public String toString() {
        return this.levelStr;
    }

    public int toInt() {
        return this.levelInt;
    }

    private Level(int i, String s) {
        this.levelInt = i;
        this.levelStr = s;
    }
}
