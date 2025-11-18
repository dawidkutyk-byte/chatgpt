/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.util.Args
 */
package org.apache.http;

import java.io.Serializable;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class ProtocolVersion
implements Cloneable,
Serializable {
    protected final int major;
    protected final String protocol;
    private static final long serialVersionUID = 8950662842175091068L;
    protected final int minor;

    public int compareToVersion(ProtocolVersion that) {
        Args.notNull((Object)that, (String)"Protocol version");
        Args.check((boolean)this.protocol.equals(that.protocol), (String)"Versions for different protocols cannot be compared: %s %s", (Object[])new Object[]{this, that});
        int delta = this.getMajor() - that.getMajor();
        if (delta != 0) return delta;
        delta = this.getMinor() - that.getMinor();
        return delta;
    }

    public final int hashCode() {
        return this.protocol.hashCode() ^ this.major * 100000 ^ this.minor;
    }

    public final int getMajor() {
        return this.major;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProtocolVersion)) {
            return false;
        }
        ProtocolVersion that = (ProtocolVersion)obj;
        return this.protocol.equals(that.protocol) && this.major == that.major && this.minor == that.minor;
    }

    public final int getMinor() {
        return this.minor;
    }

    public boolean isComparable(ProtocolVersion that) {
        return that != null && this.protocol.equals(that.protocol);
    }

    public ProtocolVersion(String protocol, int major, int minor) {
        this.protocol = (String)Args.notNull((Object)protocol, (String)"Protocol name");
        this.major = Args.notNegative((int)major, (String)"Protocol major version");
        this.minor = Args.notNegative((int)minor, (String)"Protocol minor version");
    }

    public ProtocolVersion forVersion(int major, int minor) {
        if (major != this.major) return new ProtocolVersion(this.protocol, major, minor);
        if (minor != this.minor) return new ProtocolVersion(this.protocol, major, minor);
        return this;
    }

    public final String getProtocol() {
        return this.protocol;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.protocol);
        buffer.append('/');
        buffer.append(Integer.toString(this.major));
        buffer.append('.');
        buffer.append(Integer.toString(this.minor));
        return buffer.toString();
    }

    public final boolean greaterEquals(ProtocolVersion version) {
        return this.isComparable(version) && this.compareToVersion(version) >= 0;
    }

    public final boolean lessEquals(ProtocolVersion version) {
        return this.isComparable(version) && this.compareToVersion(version) <= 0;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
