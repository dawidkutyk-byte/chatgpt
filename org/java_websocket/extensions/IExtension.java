/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.exceptions.InvalidDataException
 *  org.java_websocket.framing.Framedata
 */
package org.java_websocket.extensions;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;

public interface IExtension {
    public String getProvidedExtensionAsServer();

    public void decodeFrame(Framedata var1) throws InvalidDataException;

    public void isFrameValid(Framedata var1) throws InvalidDataException;

    public void reset();

    public IExtension copyInstance();

    public boolean acceptProvidedExtensionAsServer(String var1);

    public boolean acceptProvidedExtensionAsClient(String var1);

    public void encodeFrame(Framedata var1);

    public String getProvidedExtensionAsClient();

    public String toString();
}
