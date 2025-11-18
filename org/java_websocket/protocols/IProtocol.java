/*
 * Decompiled with CFR 0.152.
 */
package org.java_websocket.protocols;

public interface IProtocol {
    public boolean acceptProvidedProtocol(String var1);

    public IProtocol copyInstance();

    public String getProvidedProtocol();

    public String toString();
}
