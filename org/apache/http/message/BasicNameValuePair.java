/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.NameValuePair
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.util.Args
 *  org.apache.http.util.LangUtils
 */
package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;
import org.apache.http.util.LangUtils;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class BasicNameValuePair
implements Serializable,
NameValuePair,
Cloneable {
    private static final long serialVersionUID = -6437800749411518984L;
    private final String value;
    private final String name;

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        int hash = 17;
        hash = LangUtils.hashCode((int)hash, (Object)this.name);
        hash = LangUtils.hashCode((int)hash, (Object)this.value);
        return hash;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof NameValuePair)) return false;
        BasicNameValuePair that = (BasicNameValuePair)object;
        return this.name.equals(that.name) && LangUtils.equals((Object)this.value, (Object)that.value);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getValue() {
        return this.value;
    }

    public BasicNameValuePair(String name, String value) {
        this.name = (String)Args.notNull((Object)name, (String)"Name");
        this.value = value;
    }

    public String toString() {
        if (this.value == null) {
            return this.name;
        }
        int len = this.name.length() + 1 + this.value.length();
        StringBuilder buffer = new StringBuilder(len);
        buffer.append(this.name);
        buffer.append("=");
        buffer.append(this.value);
        return buffer.toString();
    }
}
