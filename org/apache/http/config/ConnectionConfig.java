/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.config.ConnectionConfig$Builder
 *  org.apache.http.config.MessageConstraints
 *  org.apache.http.util.Args
 */
package org.apache.http.config;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class ConnectionConfig
implements Cloneable {
    private final MessageConstraints messageConstraints;
    private final int bufferSize;
    private final int fragmentSizeHint;
    private final CodingErrorAction unmappableInputAction;
    private final CodingErrorAction malformedInputAction;
    public static final ConnectionConfig DEFAULT = new Builder().build();
    private final Charset charset;

    ConnectionConfig(int bufferSize, int fragmentSizeHint, Charset charset, CodingErrorAction malformedInputAction, CodingErrorAction unmappableInputAction, MessageConstraints messageConstraints) {
        this.bufferSize = bufferSize;
        this.fragmentSizeHint = fragmentSizeHint;
        this.charset = charset;
        this.malformedInputAction = malformedInputAction;
        this.unmappableInputAction = unmappableInputAction;
        this.messageConstraints = messageConstraints;
    }

    public static Builder custom() {
        return new Builder();
    }

    protected ConnectionConfig clone() throws CloneNotSupportedException {
        return (ConnectionConfig)super.clone();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[bufferSize=").append(this.bufferSize).append(", fragmentSizeHint=").append(this.fragmentSizeHint).append(", charset=").append(this.charset).append(", malformedInputAction=").append(this.malformedInputAction).append(", unmappableInputAction=").append(this.unmappableInputAction).append(", messageConstraints=").append(this.messageConstraints).append("]");
        return builder.toString();
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public int getFragmentSizeHint() {
        return this.fragmentSizeHint;
    }

    public static Builder copy(ConnectionConfig config) {
        Args.notNull((Object)config, (String)"Connection config");
        return new Builder().setBufferSize(config.getBufferSize()).setCharset(config.getCharset()).setFragmentSizeHint(config.getFragmentSizeHint()).setMalformedInputAction(config.getMalformedInputAction()).setUnmappableInputAction(config.getUnmappableInputAction()).setMessageConstraints(config.getMessageConstraints());
    }

    public Charset getCharset() {
        return this.charset;
    }

    public CodingErrorAction getMalformedInputAction() {
        return this.malformedInputAction;
    }

    public MessageConstraints getMessageConstraints() {
        return this.messageConstraints;
    }

    public CodingErrorAction getUnmappableInputAction() {
        return this.unmappableInputAction;
    }
}
