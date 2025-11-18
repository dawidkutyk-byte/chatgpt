/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpParamsNames
 */
package org.apache.http.params;

import java.util.Set;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpParamsNames;

@Deprecated
public abstract class AbstractHttpParams
implements HttpParamsNames,
HttpParams {
    public boolean isParameterTrue(String name) {
        return this.getBooleanParameter(name, false);
    }

    public boolean isParameterFalse(String name) {
        return !this.getBooleanParameter(name, false);
    }

    public Set<String> getNames() {
        throw new UnsupportedOperationException();
    }

    public double getDoubleParameter(String name, double defaultValue) {
        Object param = this.getParameter(name);
        if (param != null) return (Double)param;
        return defaultValue;
    }

    public HttpParams setLongParameter(String name, long value) {
        this.setParameter(name, value);
        return this;
    }

    protected AbstractHttpParams() {
    }

    public int getIntParameter(String name, int defaultValue) {
        Object param = this.getParameter(name);
        if (param != null) return (Integer)param;
        return defaultValue;
    }

    public HttpParams setDoubleParameter(String name, double value) {
        this.setParameter(name, value);
        return this;
    }

    public boolean getBooleanParameter(String name, boolean defaultValue) {
        Object param = this.getParameter(name);
        if (param != null) return (Boolean)param;
        return defaultValue;
    }

    public long getLongParameter(String name, long defaultValue) {
        Object param = this.getParameter(name);
        if (param != null) return (Long)param;
        return defaultValue;
    }

    public HttpParams setIntParameter(String name, int value) {
        this.setParameter(name, value);
        return this;
    }

    public HttpParams setBooleanParameter(String name, boolean value) {
        this.setParameter(name, value ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }
}
