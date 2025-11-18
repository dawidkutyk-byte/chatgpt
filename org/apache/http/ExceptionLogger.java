/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http;

public interface ExceptionLogger {
    public static final ExceptionLogger STD_ERR;
    public static final ExceptionLogger NO_OP;

    public void log(Exception var1);

    static {
        NO_OP = new /* Unavailable Anonymous Inner Class!! */;
        STD_ERR = new /* Unavailable Anonymous Inner Class!! */;
    }
}
