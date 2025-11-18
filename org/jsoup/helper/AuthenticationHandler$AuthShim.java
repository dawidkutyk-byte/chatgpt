/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.AuthenticationHandler
 *  org.jsoup.helper.RequestAuthenticator
 */
package org.jsoup.helper;

import java.net.HttpURLConnection;
import org.jsoup.helper.AuthenticationHandler;
import org.jsoup.helper.RequestAuthenticator;

static interface AuthenticationHandler.AuthShim {
    public void enable(RequestAuthenticator var1, HttpURLConnection var2);

    public void remove();

    public @7\u015aCz AuthenticationHandler get(AuthenticationHandler var1);
}
