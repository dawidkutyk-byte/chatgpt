/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.config.MessageConstraints
 */
package org.apache.http.config;

import org.apache.http.config.MessageConstraints;

public static class MessageConstraints.Builder {
    private int maxLineLength = -1;
    private int maxHeaderCount = -1;

    public MessageConstraints.Builder setMaxHeaderCount(int maxHeaderCount) {
        this.maxHeaderCount = maxHeaderCount;
        return this;
    }

    public MessageConstraints build() {
        return new MessageConstraints(this.maxLineLength, this.maxHeaderCount);
    }

    MessageConstraints.Builder() {
    }

    public MessageConstraints.Builder setMaxLineLength(int maxLineLength) {
        this.maxLineLength = maxLineLength;
        return this;
    }
}
