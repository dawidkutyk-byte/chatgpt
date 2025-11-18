/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.cookie.ClientCookie
 *  org.apache.http.cookie.SetCookie
 *  org.apache.http.util.Args
 */
package org.apache.http.impl.cookie;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.SetCookie;
import org.apache.http.util.Args;

public class BasicClientCookie
implements Cloneable,
SetCookie,
Serializable,
ClientCookie {
    private Date creationDate;
    private static final long serialVersionUID = -3869795591041535538L;
    private Map<String, String> attribs;
    private String value;
    private String cookiePath;
    private String cookieComment;
    private final String name;
    private boolean isSecure;
    private Date cookieExpiryDate;
    private int cookieVersion;
    private String cookieDomain;

    public void setSecure(boolean secure) {
        this.isSecure = secure;
    }

    public boolean isSecure() {
        return this.isSecure;
    }

    public void setVersion(int version) {
        this.cookieVersion = version;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie clone = (BasicClientCookie)super.clone();
        clone.attribs = new HashMap<String, String>(this.attribs);
        return clone;
    }

    public String getCommentURL() {
        return null;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[version: ");
        buffer.append(Integer.toString(this.cookieVersion));
        buffer.append("]");
        buffer.append("[name: ");
        buffer.append(this.name);
        buffer.append("]");
        buffer.append("[value: ");
        buffer.append(this.value);
        buffer.append("]");
        buffer.append("[domain: ");
        buffer.append(this.cookieDomain);
        buffer.append("]");
        buffer.append("[path: ");
        buffer.append(this.cookiePath);
        buffer.append("]");
        buffer.append("[expiry: ");
        buffer.append(this.cookieExpiryDate);
        buffer.append("]");
        return buffer.toString();
    }

    public int[] getPorts() {
        return null;
    }

    public int getVersion() {
        return this.cookieVersion;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAttribute(String name) {
        return this.attribs.get(name);
    }

    public boolean isPersistent() {
        return null != this.cookieExpiryDate;
    }

    public BasicClientCookie(String name, String value) {
        Args.notNull((Object)name, (String)"Name");
        this.name = name;
        this.attribs = new HashMap<String, String>();
        this.value = value;
    }

    public Date getExpiryDate() {
        return this.cookieExpiryDate;
    }

    public String getName() {
        return this.name;
    }

    public void setDomain(String domain) {
        this.cookieDomain = domain != null ? domain.toLowerCase(Locale.ROOT) : null;
    }

    public String getComment() {
        return this.cookieComment;
    }

    public void setExpiryDate(Date expiryDate) {
        this.cookieExpiryDate = expiryDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setComment(String comment) {
        this.cookieComment = comment;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setPath(String path) {
        this.cookiePath = path;
    }

    public void setAttribute(String name, String value) {
        this.attribs.put(name, value);
    }

    public boolean containsAttribute(String name) {
        return this.attribs.containsKey(name);
    }

    public String getValue() {
        return this.value;
    }

    public boolean removeAttribute(String name) {
        return this.attribs.remove(name) != null;
    }

    public boolean isExpired(Date date) {
        Args.notNull((Object)date, (String)"Date");
        return this.cookieExpiryDate != null && this.cookieExpiryDate.getTime() <= date.getTime();
    }

    public String getDomain() {
        return this.cookieDomain;
    }

    public String getPath() {
        return this.cookiePath;
    }
}
