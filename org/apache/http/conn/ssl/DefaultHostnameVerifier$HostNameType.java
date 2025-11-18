/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.conn.ssl;

static enum DefaultHostnameVerifier.HostNameType {
    IPv4(7),
    IPv6(7),
    DNS(2);

    final int subjectType;

    private DefaultHostnameVerifier.HostNameType(int subjectType) {
        this.subjectType = subjectType;
    }
}
