/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 */
package org.apache.http;

import org.apache.http.ProtocolVersion;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public final class HttpVersion
extends ProtocolVersion {
    public static final String HTTP = "HTTP";
    public static final HttpVersion HTTP_1_1;
    public static final HttpVersion HTTP_0_9;
    private static final long serialVersionUID = -5856653513894415344L;
    public static final HttpVersion HTTP_1_0;

    static {
        HTTP_0_9 = new HttpVersion(0, 9);
        HTTP_1_0 = new HttpVersion(1, 0);
        HTTP_1_1 = new HttpVersion(1, 1);
    }

    public ProtocolVersion forVersion(int major, int minor) {
        if (major == this.major && minor == this.minor) {
            return this;
        }
        if (major == 1) {
            if (minor == 0) {
                return HTTP_1_0;
            }
            if (minor == 1) {
                return HTTP_1_1;
            }
        }
        if (major != 0) return new HttpVersion(major, minor);
        if (minor != 9) return new HttpVersion(major, minor);
        return HTTP_0_9;
    }

    public HttpVersion(int major, int minor) {
        super(HTTP, major, minor);
    }
}
