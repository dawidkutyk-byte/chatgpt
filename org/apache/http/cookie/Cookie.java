/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Obsolete
 */
package org.apache.http.cookie;

import java.util.Date;
import org.apache.http.annotation.Obsolete;

public interface Cookie {
    public String getDomain();

    @Obsolete
    public int[] getPorts();

    public Date getExpiryDate();

    @Obsolete
    public int getVersion();

    public String getName();

    public boolean isExpired(Date var1);

    public String getValue();

    @Obsolete
    public String getCommentURL();

    public boolean isSecure();

    public boolean isPersistent();

    public String getPath();

    @Obsolete
    public String getComment();
}
