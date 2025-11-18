/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HeaderElement
 *  org.apache.http.NameValuePair
 *  org.apache.http.util.Args
 *  org.apache.http.util.LangUtils
 */
package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.util.Args;
import org.apache.http.util.LangUtils;

public class BasicHeaderElement
implements HeaderElement,
Cloneable {
    private final String value;
    private final String name;
    private final NameValuePair[] parameters;

    public BasicHeaderElement(String name, String value, NameValuePair[] parameters) {
        this.name = (String)Args.notNull((Object)name, (String)"Name");
        this.value = value;
        this.parameters = parameters != null ? parameters : new NameValuePair[0];
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.name);
        if (this.value != null) {
            buffer.append("=");
            buffer.append(this.value);
        }
        NameValuePair[] arr$ = this.parameters;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            NameValuePair parameter = arr$[i$];
            buffer.append("; ");
            buffer.append(parameter);
            ++i$;
        }
        return buffer.toString();
    }

    public NameValuePair[] getParameters() {
        return (NameValuePair[])this.parameters.clone();
    }

    public int getParameterCount() {
        return this.parameters.length;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        int hash = 17;
        hash = LangUtils.hashCode((int)hash, (Object)this.name);
        hash = LangUtils.hashCode((int)hash, (Object)this.value);
        NameValuePair[] arr$ = this.parameters;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            NameValuePair parameter = arr$[i$];
            hash = LangUtils.hashCode((int)hash, (Object)parameter);
            ++i$;
        }
        return hash;
    }

    public BasicHeaderElement(String name, String value) {
        this(name, value, null);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public NameValuePair getParameterByName(String name) {
        Args.notNull((Object)name, (String)"Name");
        NameValuePair found = null;
        NameValuePair[] arr$ = this.parameters;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            NameValuePair current = arr$[i$];
            if (current.getName().equalsIgnoreCase(name)) {
                found = current;
                return found;
            }
            ++i$;
        }
        return found;
    }

    public NameValuePair getParameter(int index) {
        return this.parameters[index];
    }

    public String getValue() {
        return this.value;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof HeaderElement)) return false;
        BasicHeaderElement that = (BasicHeaderElement)object;
        return this.name.equals(that.name) && LangUtils.equals((Object)this.value, (Object)that.value) && LangUtils.equals((Object[])this.parameters, (Object[])that.parameters);
    }
}
