/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.auth.AuthScheme
 *  org.apache.http.auth.AuthSchemeFactory
 *  org.apache.http.impl.auth.NegotiateScheme
 *  org.apache.http.impl.auth.SpnegoTokenGenerator
 *  org.apache.http.params.HttpParams
 */
package org.apache.http.impl.auth;

import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.impl.auth.NegotiateScheme;
import org.apache.http.impl.auth.SpnegoTokenGenerator;
import org.apache.http.params.HttpParams;

@Deprecated
public class NegotiateSchemeFactory
implements AuthSchemeFactory {
    private final SpnegoTokenGenerator spengoGenerator;
    private final boolean stripPort;

    public AuthScheme newInstance(HttpParams params) {
        return new NegotiateScheme(this.spengoGenerator, this.stripPort);
    }

    public SpnegoTokenGenerator getSpengoGenerator() {
        return this.spengoGenerator;
    }

    public NegotiateSchemeFactory() {
        this(null, false);
    }

    public NegotiateSchemeFactory(SpnegoTokenGenerator spengoGenerator) {
        this(spengoGenerator, false);
    }

    public boolean isStripPort() {
        return this.stripPort;
    }

    public NegotiateSchemeFactory(SpnegoTokenGenerator spengoGenerator, boolean stripPort) {
        this.spengoGenerator = spengoGenerator;
        this.stripPort = stripPort;
    }
}
