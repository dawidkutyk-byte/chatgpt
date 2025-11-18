/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.impl.auth.NTLMEngineException
 *  org.apache.http.impl.auth.NTLMEngineImpl
 *  org.apache.http.impl.auth.NTLMEngineImpl$NTLMMessage
 */
package org.apache.http.impl.auth;

import java.util.Locale;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.impl.auth.NTLMEngineImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static class NTLMEngineImpl.Type1Message
extends NTLMEngineImpl.NTLMMessage {
    private final byte[] domainBytes;
    private final byte[] hostBytes;
    private final int flags;

    protected void buildMessage() {
        int domainBytesLength = 0;
        if (this.domainBytes != null) {
            domainBytesLength = this.domainBytes.length;
        }
        int hostBytesLength = 0;
        if (this.hostBytes != null) {
            hostBytesLength = this.hostBytes.length;
        }
        int finalLength = 40 + hostBytesLength + domainBytesLength;
        this.prepareResponse(finalLength, 1);
        this.addULong(this.flags);
        this.addUShort(domainBytesLength);
        this.addUShort(domainBytesLength);
        this.addULong(hostBytesLength + 32 + 8);
        this.addUShort(hostBytesLength);
        this.addUShort(hostBytesLength);
        this.addULong(40);
        this.addUShort(261);
        this.addULong(2600);
        this.addUShort(3840);
        if (this.hostBytes != null) {
            this.addBytes(this.hostBytes);
        }
        if (this.domainBytes == null) return;
        this.addBytes(this.domainBytes);
    }

    private int getDefaultFlags() {
        return -1576500735;
    }

    NTLMEngineImpl.Type1Message(String domain, String host) throws NTLMEngineException {
        this(domain, host, null);
    }

    NTLMEngineImpl.Type1Message() {
        this.hostBytes = null;
        this.domainBytes = null;
        this.flags = this.getDefaultFlags();
    }

    NTLMEngineImpl.Type1Message(String domain, String host, Integer flags) throws NTLMEngineException {
        this.flags = flags == null ? this.getDefaultFlags() : flags.intValue();
        String unqualifiedHost = NTLMEngineImpl.access$2200((String)host);
        String unqualifiedDomain = NTLMEngineImpl.access$2300((String)domain);
        this.hostBytes = unqualifiedHost != null ? unqualifiedHost.getBytes(NTLMEngineImpl.access$2400()) : null;
        this.domainBytes = unqualifiedDomain != null ? unqualifiedDomain.toUpperCase(Locale.ROOT).getBytes(NTLMEngineImpl.access$2400()) : null;
    }
}
