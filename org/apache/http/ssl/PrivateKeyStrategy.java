/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ssl.PrivateKeyDetails
 */
package org.apache.http.ssl;

import java.net.Socket;
import java.util.Map;
import org.apache.http.ssl.PrivateKeyDetails;

public interface PrivateKeyStrategy {
    public String chooseAlias(Map<String, PrivateKeyDetails> var1, Socket var2);
}
