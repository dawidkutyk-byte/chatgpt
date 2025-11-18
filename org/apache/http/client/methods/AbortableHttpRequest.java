/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.conn.ClientConnectionRequest
 *  org.apache.http.conn.ConnectionReleaseTrigger
 */
package org.apache.http.client.methods;

import java.io.IOException;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;

@Deprecated
public interface AbortableHttpRequest {
    public void abort();

    public void setReleaseTrigger(ConnectionReleaseTrigger var1) throws IOException;

    public void setConnectionRequest(ClientConnectionRequest var1) throws IOException;
}
