/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.cookie.SetCookie2
 *  org.apache.http.impl.cookie.BasicClientCookie
 */
package org.apache.http.impl.cookie;

import java.util.Date;
import org.apache.http.cookie.SetCookie2;
import org.apache.http.impl.cookie.BasicClientCookie;

public class BasicClientCookie2
extends BasicClientCookie
implements SetCookie2 {
    private int[] ports;
    private boolean discard;
    private String commentURL;
    private static final long serialVersionUID = -7744598295706617057L;

    public boolean isPersistent() {
        return !this.discard && super.isPersistent();
    }

    public boolean isExpired(Date date) {
        return this.discard || super.isExpired(date);
    }

    public void setPorts(int[] ports) {
        this.ports = ports;
    }

    public String getCommentURL() {
        return this.commentURL;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 clone = (BasicClientCookie2)((Object)super.clone());
        if (this.ports == null) return clone;
        clone.ports = (int[])this.ports.clone();
        return clone;
    }

    public BasicClientCookie2(String name, String value) {
        super(name, value);
    }

    public int[] getPorts() {
        return this.ports;
    }

    public void setCommentURL(String commentURL) {
        this.commentURL = commentURL;
    }

    public void setDiscard(boolean discard) {
        this.discard = discard;
    }
}
