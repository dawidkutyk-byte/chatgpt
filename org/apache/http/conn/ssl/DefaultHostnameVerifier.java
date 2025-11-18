/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.ssl.DefaultHostnameVerifier$1
 *  org.apache.http.conn.ssl.DefaultHostnameVerifier$HostNameType
 *  org.apache.http.conn.ssl.SubjectName
 *  org.apache.http.conn.util.DnsUtils
 *  org.apache.http.conn.util.DomainType
 *  org.apache.http.conn.util.InetAddressUtils
 *  org.apache.http.conn.util.PublicSuffixMatcher
 */
package org.apache.http.conn.ssl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SubjectName;
import org.apache.http.conn.util.DnsUtils;
import org.apache.http.conn.util.DomainType;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.conn.util.PublicSuffixMatcher;

@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public final class DefaultHostnameVerifier
implements HostnameVerifier {
    private final Log log = LogFactory.getLog(this.getClass());
    private final PublicSuffixMatcher publicSuffixMatcher;

    public DefaultHostnameVerifier(PublicSuffixMatcher publicSuffixMatcher) {
        this.publicSuffixMatcher = publicSuffixMatcher;
    }

    static void matchDNSName(String host, List<SubjectName> subjectAlts, PublicSuffixMatcher publicSuffixMatcher) throws SSLException {
        String normalizedHost = DnsUtils.normalize((String)host);
        int i = 0;
        while (i < subjectAlts.size()) {
            String normalizedSubjectAlt;
            SubjectName subjectAlt = subjectAlts.get(i);
            if (subjectAlt.getType() == 2 && DefaultHostnameVerifier.matchIdentityStrict(normalizedHost, normalizedSubjectAlt = DnsUtils.normalize((String)subjectAlt.getValue()), publicSuffixMatcher)) {
                return;
            }
            ++i;
        }
        throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match any " + "of the subject alternative names: " + subjectAlts);
    }

    @Override
    public boolean verify(String host, SSLSession session) {
        try {
            Certificate[] certs = session.getPeerCertificates();
            X509Certificate x509 = (X509Certificate)certs[0];
            this.verify(host, x509);
            return true;
        }
        catch (SSLException ex) {
            if (!this.log.isDebugEnabled()) return false;
            this.log.debug((Object)ex.getMessage(), (Throwable)ex);
            return false;
        }
    }

    private static boolean matchIdentity(String host, String identity, PublicSuffixMatcher publicSuffixMatcher, DomainType domainType, boolean strict) {
        if (publicSuffixMatcher != null && host.contains(".") && !DefaultHostnameVerifier.matchDomainRoot(host, publicSuffixMatcher.getDomainRoot(identity, domainType))) {
            return false;
        }
        int asteriskIdx = identity.indexOf(42);
        if (asteriskIdx == -1) return host.equalsIgnoreCase(identity);
        String prefix = identity.substring(0, asteriskIdx);
        String suffix = identity.substring(asteriskIdx + 1);
        if (!prefix.isEmpty() && !host.startsWith(prefix)) {
            return false;
        }
        if (!suffix.isEmpty() && !host.endsWith(suffix)) {
            return false;
        }
        if (!strict) return true;
        String remainder = host.substring(prefix.length(), host.length() - suffix.length());
        if (!remainder.contains(".")) return true;
        return false;
    }

    static boolean matchIdentity(String host, String identity, PublicSuffixMatcher publicSuffixMatcher, DomainType domainType) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, publicSuffixMatcher, domainType, false);
    }

    static List<SubjectName> getSubjectAltNames(X509Certificate cert) {
        try {
            Collection<List<?>> entries = cert.getSubjectAlternativeNames();
            if (entries == null) {
                return Collections.emptyList();
            }
            ArrayList<SubjectName> result = new ArrayList<SubjectName>();
            Iterator<List<?>> i$ = entries.iterator();
            while (i$.hasNext()) {
                List<?> entry = i$.next();
                Integer type = entry.size() >= 2 ? (Integer)entry.get(0) : null;
                if (type == null || type != 2 && type != 7) continue;
                Object o = entry.get(1);
                if (o instanceof String) {
                    result.add(new SubjectName((String)o, type.intValue()));
                    continue;
                }
                if (o instanceof byte[]) continue;
            }
            return result;
        }
        catch (CertificateParsingException ignore) {
            return Collections.emptyList();
        }
    }

    static void matchCN(String host, String cn, PublicSuffixMatcher publicSuffixMatcher) throws SSLException {
        String normalizedCn;
        String normalizedHost = DnsUtils.normalize((String)host);
        if (DefaultHostnameVerifier.matchIdentityStrict(normalizedHost, normalizedCn = DnsUtils.normalize((String)cn), publicSuffixMatcher)) return;
        throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match " + "common name of the certificate subject: " + cn);
    }

    static boolean matchIdentity(String host, String identity) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, null, null, false);
    }

    static String normaliseAddress(String hostname) {
        if (hostname == null) {
            return hostname;
        }
        try {
            InetAddress inetAddress = InetAddress.getByName(hostname);
            return inetAddress.getHostAddress();
        }
        catch (UnknownHostException unexpected) {
            return hostname;
        }
    }

    static boolean matchIdentityStrict(String host, String identity) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, null, null, true);
    }

    static boolean matchIdentityStrict(String host, String identity, PublicSuffixMatcher publicSuffixMatcher) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, publicSuffixMatcher, null, true);
    }

    public DefaultHostnameVerifier() {
        this(null);
    }

    static void matchIPv6Address(String host, List<SubjectName> subjectAlts) throws SSLException {
        String normalisedHost = DefaultHostnameVerifier.normaliseAddress(host);
        int i = 0;
        while (i < subjectAlts.size()) {
            String normalizedSubjectAlt;
            SubjectName subjectAlt = subjectAlts.get(i);
            if (subjectAlt.getType() == 7 && normalisedHost.equals(normalizedSubjectAlt = DefaultHostnameVerifier.normaliseAddress(subjectAlt.getValue()))) {
                return;
            }
            ++i;
        }
        throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match any " + "of the subject alternative names: " + subjectAlts);
    }

    public void verify(String host, X509Certificate cert) throws SSLException {
        HostNameType hostType = DefaultHostnameVerifier.determineHostFormat(host);
        List<SubjectName> subjectAlts = DefaultHostnameVerifier.getSubjectAltNames(cert);
        if (subjectAlts != null && !subjectAlts.isEmpty()) {
            switch (1.$SwitchMap$org$apache$http$conn$ssl$DefaultHostnameVerifier$HostNameType[hostType.ordinal()]) {
                case 1: {
                    DefaultHostnameVerifier.matchIPAddress(host, subjectAlts);
                    break;
                }
                case 2: {
                    DefaultHostnameVerifier.matchIPv6Address(host, subjectAlts);
                    break;
                }
                default: {
                    DefaultHostnameVerifier.matchDNSName(host, subjectAlts, this.publicSuffixMatcher);
                    break;
                }
            }
        } else {
            X500Principal subjectPrincipal = cert.getSubjectX500Principal();
            String cn = DefaultHostnameVerifier.extractCN(subjectPrincipal.getName("RFC2253"));
            if (cn == null) {
                throw new SSLException("Certificate subject for <" + host + "> doesn't contain " + "a common name and does not have alternative names");
            }
            DefaultHostnameVerifier.matchCN(host, cn, this.publicSuffixMatcher);
        }
    }

    static void matchIPAddress(String host, List<SubjectName> subjectAlts) throws SSLException {
        int i = 0;
        while (i < subjectAlts.size()) {
            SubjectName subjectAlt = subjectAlts.get(i);
            if (subjectAlt.getType() == 7 && host.equals(subjectAlt.getValue())) {
                return;
            }
            ++i;
        }
        throw new SSLPeerUnverifiedException("Certificate for <" + host + "> doesn't match any " + "of the subject alternative names: " + subjectAlts);
    }

    static String extractCN(String subjectPrincipal) throws SSLException {
        if (subjectPrincipal == null) {
            return null;
        }
        try {
            LdapName subjectDN = new LdapName(subjectPrincipal);
            List<Rdn> rdns = subjectDN.getRdns();
            int i = rdns.size() - 1;
            while (i >= 0) {
                Rdn rds = rdns.get(i);
                Attributes attributes = rds.toAttributes();
                Attribute cn = attributes.get("cn");
                if (cn != null) {
                    try {
                        Object value = cn.get();
                        if (value != null) {
                            return value.toString();
                        }
                    }
                    catch (NoSuchElementException ignore) {
                    }
                    catch (NamingException ignore) {
                        // empty catch block
                    }
                }
                --i;
            }
            return null;
        }
        catch (InvalidNameException e) {
            throw new SSLException(subjectPrincipal + " is not a valid X500 distinguished name");
        }
    }

    static boolean matchIdentityStrict(String host, String identity, PublicSuffixMatcher publicSuffixMatcher, DomainType domainType) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, publicSuffixMatcher, domainType, true);
    }

    static boolean matchDomainRoot(String host, String domainRoot) {
        if (domainRoot != null) return host.endsWith(domainRoot) && (host.length() == domainRoot.length() || host.charAt(host.length() - domainRoot.length() - 1) == '.');
        return false;
    }

    static boolean matchIdentity(String host, String identity, PublicSuffixMatcher publicSuffixMatcher) {
        return DefaultHostnameVerifier.matchIdentity(host, identity, publicSuffixMatcher, null, false);
    }

    static HostNameType determineHostFormat(String host) {
        if (InetAddressUtils.isIPv4Address((String)host)) {
            return HostNameType.IPv4;
        }
        String s = host;
        if (s.startsWith("[") && s.endsWith("]")) {
            s = host.substring(1, host.length() - 1);
        }
        if (!InetAddressUtils.isIPv6Address((String)s)) return HostNameType.DNS;
        return HostNameType.IPv6;
    }
}
