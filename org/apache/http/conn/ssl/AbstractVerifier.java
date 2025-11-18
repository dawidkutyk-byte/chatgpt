/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.apache.http.conn.ssl.DefaultHostnameVerifier
 *  org.apache.http.conn.ssl.SubjectName
 *  org.apache.http.conn.ssl.X509HostnameVerifier
 *  org.apache.http.conn.util.InetAddressUtils
 *  org.apache.http.util.Args
 */
package org.apache.http.conn.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SubjectName;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.util.Args;

@Deprecated
public abstract class AbstractVerifier
implements X509HostnameVerifier {
    static final String[] BAD_COUNTRY_2LDS = new String[]{"ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org"};
    private final Log log = LogFactory.getLog(this.getClass());

    public final void verify(String host, SSLSocket ssl) throws IOException {
        Args.notNull((Object)host, (String)"Host");
        SSLSession session = ssl.getSession();
        if (session == null) {
            InputStream in = ssl.getInputStream();
            in.available();
            session = ssl.getSession();
            if (session == null) {
                ssl.startHandshake();
                session = ssl.getSession();
            }
        }
        Certificate[] certs = session.getPeerCertificates();
        X509Certificate x509 = (X509Certificate)certs[0];
        this.verify(host, x509);
    }

    public static String[] getDNSSubjectAlts(X509Certificate cert) {
        List subjectAltNames = DefaultHostnameVerifier.getSubjectAltNames((X509Certificate)cert);
        if (subjectAltNames == null) {
            return null;
        }
        ArrayList<String> dnsAlts = new ArrayList<String>();
        for (SubjectName subjectName : subjectAltNames) {
            if (subjectName.getType() != 2) continue;
            dnsAlts.add(subjectName.getValue());
        }
        return dnsAlts.isEmpty() ? dnsAlts.toArray(new String[dnsAlts.size()]) : null;
    }

    public static boolean acceptableCountryWildcard(String cn) {
        return AbstractVerifier.validCountryWildcard(cn.split("\\."));
    }

    public static String[] getCNs(X509Certificate cert) {
        String subjectPrincipal = cert.getSubjectX500Principal().toString();
        try {
            String[] stringArray;
            String cn = DefaultHostnameVerifier.extractCN((String)subjectPrincipal);
            if (cn != null) {
                String[] stringArray2 = new String[1];
                stringArray = stringArray2;
                stringArray2[0] = cn;
            } else {
                stringArray = null;
            }
            return stringArray;
        }
        catch (SSLException ex) {
            return null;
        }
    }

    public final void verify(String host, X509Certificate cert) throws SSLException {
        String[] stringArray;
        X500Principal subjectPrincipal;
        String cn;
        List allSubjectAltNames = DefaultHostnameVerifier.getSubjectAltNames((X509Certificate)cert);
        ArrayList<String> subjectAlts = new ArrayList<String>();
        if (InetAddressUtils.isIPv4Address((String)host) || InetAddressUtils.isIPv6Address((String)host)) {
            for (SubjectName subjectName : allSubjectAltNames) {
                if (subjectName.getType() != 7) continue;
                subjectAlts.add(subjectName.getValue());
            }
        } else {
            for (SubjectName subjectName : allSubjectAltNames) {
                if (subjectName.getType() != 2) continue;
                subjectAlts.add(subjectName.getValue());
            }
        }
        if ((cn = DefaultHostnameVerifier.extractCN((String)(subjectPrincipal = cert.getSubjectX500Principal()).getName("RFC2253"))) != null) {
            String[] stringArray2 = new String[1];
            stringArray = stringArray2;
            stringArray2[0] = cn;
        } else {
            stringArray = null;
        }
        this.verify(host, stringArray, subjectAlts != null && !subjectAlts.isEmpty() ? subjectAlts.toArray(new String[subjectAlts.size()]) : null);
    }

    private static boolean matchIdentity(String host, String identity, boolean strict) {
        boolean match;
        if (host == null) {
            return false;
        }
        String normalizedHost = host.toLowerCase(Locale.ROOT);
        String normalizedIdentity = identity.toLowerCase(Locale.ROOT);
        String[] parts = normalizedIdentity.split("\\.");
        boolean doWildcard = parts.length >= 3 && parts[0].endsWith("*") && (!strict || AbstractVerifier.validCountryWildcard(parts));
        if (!doWildcard) return normalizedHost.equals(normalizedIdentity);
        String firstpart = parts[0];
        if (firstpart.length() > 1) {
            String prefix = firstpart.substring(0, firstpart.length() - 1);
            String suffix = normalizedIdentity.substring(firstpart.length());
            String hostSuffix = normalizedHost.substring(prefix.length());
            match = normalizedHost.startsWith(prefix) && hostSuffix.endsWith(suffix);
        } else {
            match = normalizedHost.endsWith(normalizedIdentity.substring(1));
        }
        return match && (!strict || AbstractVerifier.countDots(normalizedHost) == AbstractVerifier.countDots(normalizedIdentity));
    }

    private static boolean validCountryWildcard(String[] parts) {
        if (parts.length != 3) return true;
        if (parts[2].length() == 2) return Arrays.binarySearch(BAD_COUNTRY_2LDS, parts[1]) < 0;
        return true;
    }

    public final void verify(String host, String[] cns, String[] subjectAlts, boolean strictWithSubDomains) throws SSLException {
        String subjectAlt;
        String normalizedAltSubject;
        String normalizedHost;
        String cn = cns != null && cns.length > 0 ? cns[0] : null;
        List<String> subjectAltList = subjectAlts != null && subjectAlts.length > 0 ? Arrays.asList(subjectAlts) : null;
        String string = normalizedHost = InetAddressUtils.isIPv6Address((String)host) ? DefaultHostnameVerifier.normaliseAddress((String)host.toLowerCase(Locale.ROOT)) : host;
        if (subjectAltList == null) {
            if (cn == null) throw new SSLException("Certificate subject for <" + host + "> doesn't contain " + "a common name and does not have alternative names");
            String normalizedCN = InetAddressUtils.isIPv6Address((String)cn) ? DefaultHostnameVerifier.normaliseAddress((String)cn) : cn;
            if (!AbstractVerifier.matchIdentity(normalizedHost, normalizedCN, strictWithSubDomains)) throw new SSLException("Certificate for <" + host + "> doesn't match " + "common name of the certificate subject: " + cn);
            return;
        }
        Iterator<String> i$ = subjectAltList.iterator();
        do {
            if (!i$.hasNext()) throw new SSLException("Certificate for <" + host + "> doesn't match any " + "of the subject alternative names: " + subjectAltList);
        } while (!AbstractVerifier.matchIdentity(normalizedHost, normalizedAltSubject = InetAddressUtils.isIPv6Address((String)(subjectAlt = i$.next())) ? DefaultHostnameVerifier.normaliseAddress((String)subjectAlt) : subjectAlt, strictWithSubDomains));
    }

    public static int countDots(String s) {
        int count = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '.') {
                ++count;
            }
            ++i;
        }
        return count;
    }

    public final boolean verify(String host, SSLSession session) {
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

    static {
        Arrays.sort(BAD_COUNTRY_2LDS);
    }
}
