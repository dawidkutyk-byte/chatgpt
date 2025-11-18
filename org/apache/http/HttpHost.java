/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.util.Args
 *  org.apache.http.util.LangUtils
 */
package org.apache.http;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Locale;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;
import org.apache.http.util.LangUtils;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public final class HttpHost
implements Cloneable,
Serializable {
    public static final String DEFAULT_SCHEME_NAME = "http";
    protected final int port;
    protected final String schemeName;
    protected final InetAddress address;
    protected final String hostname;
    private static final long serialVersionUID = -7529410654042457626L;
    protected final String lcHostname;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toURI() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.schemeName);
        buffer.append("://");
        buffer.append(this.hostname);
        if (this.port == -1) return buffer.toString();
        buffer.append(':');
        buffer.append(Integer.toString(this.port));
        return buffer.toString();
    }

    public HttpHost(HttpHost httphost) {
        Args.notNull((Object)httphost, (String)"HTTP host");
        this.hostname = httphost.hostname;
        this.lcHostname = httphost.lcHostname;
        this.schemeName = httphost.schemeName;
        this.port = httphost.port;
        this.address = httphost.address;
    }

    public HttpHost(InetAddress address, int port, String scheme) {
        this((InetAddress)Args.notNull((Object)address, (String)"Inet address"), address.getHostName(), port, scheme);
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public String getSchemeName() {
        return this.schemeName;
    }

    public HttpHost(String hostname) {
        this(hostname, -1, null);
    }

    public int getPort() {
        return this.port;
    }

    public String toHostString() {
        if (this.port == -1) return this.hostname;
        StringBuilder buffer = new StringBuilder(this.hostname.length() + 6);
        buffer.append(this.hostname);
        buffer.append(":");
        buffer.append(Integer.toString(this.port));
        return buffer.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpHost)) return false;
        HttpHost that = (HttpHost)obj;
        return this.lcHostname.equals(that.lcHostname) && this.port == that.port && this.schemeName.equals(that.schemeName) && (this.address == null ? that.address == null : this.address.equals(that.address));
    }

    public HttpHost(String hostname, int port) {
        this(hostname, port, null);
    }

    public static HttpHost create(String s) {
        Args.containsNoBlanks((CharSequence)s, (String)"HTTP Host");
        String text = s;
        String scheme = null;
        int schemeIdx = text.indexOf("://");
        if (schemeIdx > 0) {
            scheme = text.substring(0, schemeIdx);
            text = text.substring(schemeIdx + 3);
        }
        int port = -1;
        int portIdx = text.lastIndexOf(":");
        if (portIdx <= 0) return new HttpHost(text, port, scheme);
        try {
            port = Integer.parseInt(text.substring(portIdx + 1));
        }
        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid HTTP host: " + text);
        }
        text = text.substring(0, portIdx);
        return new HttpHost(text, port, scheme);
    }

    public HttpHost(InetAddress address, int port) {
        this(address, port, null);
    }

    public String toString() {
        return this.toURI();
    }

    public int hashCode() {
        int hash = 17;
        hash = LangUtils.hashCode((int)hash, (Object)this.lcHostname);
        hash = LangUtils.hashCode((int)hash, (int)this.port);
        hash = LangUtils.hashCode((int)hash, (Object)this.schemeName);
        if (this.address == null) return hash;
        hash = LangUtils.hashCode((int)hash, (Object)this.address);
        return hash;
    }

    public HttpHost(String hostname, int port, String scheme) {
        this.hostname = (String)Args.containsNoBlanks((CharSequence)hostname, (String)"Host name");
        this.lcHostname = hostname.toLowerCase(Locale.ROOT);
        this.schemeName = scheme != null ? scheme.toLowerCase(Locale.ROOT) : DEFAULT_SCHEME_NAME;
        this.port = port;
        this.address = null;
    }

    public HttpHost(InetAddress address) {
        this(address, -1, null);
    }

    public String getHostName() {
        return this.hostname;
    }

    public HttpHost(InetAddress address, String hostname, int port, String scheme) {
        this.address = (InetAddress)Args.notNull((Object)address, (String)"Inet address");
        this.hostname = (String)Args.notNull((Object)hostname, (String)"Hostname");
        this.lcHostname = this.hostname.toLowerCase(Locale.ROOT);
        this.schemeName = scheme != null ? scheme.toLowerCase(Locale.ROOT) : DEFAULT_SCHEME_NAME;
        this.port = port;
    }
}
