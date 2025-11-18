/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.auth.Credentials
 */
package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.Credentials;
import org.ietf.jgss.GSSCredential;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class KerberosCredentials
implements Serializable,
Credentials {
    private final GSSCredential gssCredential;
    private static final long serialVersionUID = 487421613855550713L;

    public GSSCredential getGSSCredential() {
        return this.gssCredential;
    }

    public Principal getUserPrincipal() {
        return null;
    }

    public String getPassword() {
        return null;
    }

    public KerberosCredentials(GSSCredential gssCredential) {
        this.gssCredential = gssCredential;
    }
}
