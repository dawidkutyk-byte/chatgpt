/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Consts
 *  org.apache.http.config.ConnectionConfig
 *  org.apache.http.config.MessageConstraints
 */
package org.apache.http.config;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import org.apache.http.Consts;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;

public static class ConnectionConfig.Builder {
    private CodingErrorAction malformedInputAction;
    private int fragmentSizeHint = -1;
    private Charset charset;
    private int bufferSize;
    private CodingErrorAction unmappableInputAction;
    private MessageConstraints messageConstraints;

    public ConnectionConfig.Builder setFragmentSizeHint(int fragmentSizeHint) {
        this.fragmentSizeHint = fragmentSizeHint;
        return this;
    }

    public ConnectionConfig build() {
        Charset cs = this.charset;
        if (cs == null && (this.malformedInputAction != null || this.unmappableInputAction != null)) {
            cs = Consts.ASCII;
        }
        int bufSize = this.bufferSize > 0 ? this.bufferSize : 8192;
        int fragmentHintSize = this.fragmentSizeHint >= 0 ? this.fragmentSizeHint : bufSize;
        return new ConnectionConfig(bufSize, fragmentHintSize, cs, this.malformedInputAction, this.unmappableInputAction, this.messageConstraints);
    }

    public ConnectionConfig.Builder setUnmappableInputAction(CodingErrorAction unmappableInputAction) {
        this.unmappableInputAction = unmappableInputAction;
        if (unmappableInputAction == null) return this;
        if (this.charset != null) return this;
        this.charset = Consts.ASCII;
        return this;
    }

    public ConnectionConfig.Builder setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public ConnectionConfig.Builder setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

    ConnectionConfig.Builder() {
    }

    public ConnectionConfig.Builder setMessageConstraints(MessageConstraints messageConstraints) {
        this.messageConstraints = messageConstraints;
        return this;
    }

    public ConnectionConfig.Builder setMalformedInputAction(CodingErrorAction malformedInputAction) {
        this.malformedInputAction = malformedInputAction;
        if (malformedInputAction == null) return this;
        if (this.charset != null) return this;
        this.charset = Consts.ASCII;
        return this;
    }
}
