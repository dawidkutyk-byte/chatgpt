/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.auth.AuthScheme
 *  org.apache.http.auth.AuthSchemeFactory
 *  org.apache.http.auth.AuthSchemeProvider
 *  org.apache.http.impl.auth.SPNegoScheme
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.auth;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.impl.auth.SPNegoScheme;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class SPNegoSchemeFactory
implements AuthSchemeProvider,
AuthSchemeFactory {
    private final boolean stripPort;
    private final boolean useCanonicalHostname;

    public AuthScheme create(HttpContext context) {
        return new SPNegoScheme(this.stripPort, this.useCanonicalHostname);
    }

    public boolean isUseCanonicalHostname() {
        return this.useCanonicalHostname;
    }

    public boolean isStripPort() {
        return this.stripPort;
    }

    public SPNegoSchemeFactory(boolean stripPort) {
        this.stripPort = stripPort;
        this.useCanonicalHostname = true;
    }

    public SPNegoSchemeFactory() {
        this(true, true);
    }

    public SPNegoSchemeFactory(boolean stripPort, boolean useCanonicalHostname) {
        this.stripPort = stripPort;
        this.useCanonicalHostname = useCanonicalHostname;
    }

    public AuthScheme newInstance(HttpParams params) {
        return new SPNegoScheme(this.stripPort, this.useCanonicalHostname);
    }
}
