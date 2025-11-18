/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.java_websocket.protocols.IProtocol
 */
package org.java_websocket.protocols;

import java.util.regex.Pattern;
import org.java_websocket.protocols.IProtocol;

public class Protocol
implements IProtocol {
    private final String providedProtocol;
    private static final Pattern patternComma;
    private static final Pattern patternSpace;

    public String getProvidedProtocol() {
        return this.providedProtocol;
    }

    public Protocol(String providedProtocol) {
        if (providedProtocol == null) {
            throw new IllegalArgumentException();
        }
        this.providedProtocol = providedProtocol;
    }

    public String toString() {
        return this.getProvidedProtocol();
    }

    public int hashCode() {
        return this.providedProtocol.hashCode();
    }

    public IProtocol copyInstance() {
        return new Protocol(this.getProvidedProtocol());
    }

    static {
        patternSpace = Pattern.compile(" ");
        patternComma = Pattern.compile(",");
    }

    public boolean acceptProvidedProtocol(String inputProtocolHeader) {
        String[] headers;
        if ("".equals(this.providedProtocol)) {
            return true;
        }
        String protocolHeader = patternSpace.matcher(inputProtocolHeader).replaceAll("");
        String[] stringArray = headers = patternComma.split(protocolHeader);
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String header = stringArray[n2];
            if (this.providedProtocol.equals(header)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) return false;
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Protocol protocol = (Protocol)o;
        return this.providedProtocol.equals(protocol.providedProtocol);
    }
}
