/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.RequestAuthenticator$Context
 */
package org.jsoup.helper;

import java.net.PasswordAuthentication;
import org.jsoup.helper.RequestAuthenticator;

@FunctionalInterface
public interface RequestAuthenticator {
    public @7\u015aCz PasswordAuthentication authenticate(Context var1);
}
