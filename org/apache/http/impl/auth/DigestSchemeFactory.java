/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.auth.AuthScheme
 *  org.apache.http.auth.AuthSchemeFactory
 *  org.apache.http.auth.AuthSchemeProvider
 *  org.apache.http.impl.auth.DigestScheme
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.auth;

import java.nio.charset.Charset;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class DigestSchemeFactory
implements AuthSchemeFactory,
AuthSchemeProvider {
    private final Charset charset;

    public AuthScheme create(HttpContext context) {
        return new DigestScheme(this.charset);
    }

    public AuthScheme newInstance(HttpParams params) {
        return new DigestScheme();
    }

    public DigestSchemeFactory() {
        this(null);
    }

    public DigestSchemeFactory(Charset charset) {
        this.charset = charset;
    }
}
