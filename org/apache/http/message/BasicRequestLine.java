/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.RequestLine
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.message.BasicLineFormatter
 *  org.apache.http.util.Args
 */
package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.message.BasicLineFormatter;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class BasicRequestLine
implements Cloneable,
RequestLine,
Serializable {
    private final ProtocolVersion protoversion;
    private final String method;
    private final String uri;
    private static final long serialVersionUID = 2810581718468737193L;

    public ProtocolVersion getProtocolVersion() {
        return this.protoversion;
    }

    public BasicRequestLine(String method, String uri, ProtocolVersion version) {
        this.method = (String)Args.notNull((Object)method, (String)"Method");
        this.uri = (String)Args.notNull((Object)uri, (String)"URI");
        this.protoversion = (ProtocolVersion)Args.notNull((Object)version, (String)"Version");
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatRequestLine(null, (RequestLine)this).toString();
    }

    public String getUri() {
        return this.uri;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getMethod() {
        return this.method;
    }
}
