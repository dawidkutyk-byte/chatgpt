/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.auth.params.AuthPNames
 *  org.apache.http.client.params.ClientPNames
 *  org.apache.http.conn.params.ConnConnectionPNames
 *  org.apache.http.conn.params.ConnManagerPNames
 *  org.apache.http.conn.params.ConnRoutePNames
 *  org.apache.http.cookie.params.CookieSpecPNames
 *  org.apache.http.params.CoreConnectionPNames
 *  org.apache.http.params.CoreProtocolPNames
 */
package org.apache.http.client.params;

import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.params.ConnConnectionPNames;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;

@Deprecated
public interface AllClientPNames
extends CookieSpecPNames,
ConnManagerPNames,
ConnConnectionPNames,
ConnRoutePNames,
CoreProtocolPNames,
AuthPNames,
CoreConnectionPNames,
ClientPNames {
}
