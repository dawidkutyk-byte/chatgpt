/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.config.MessageConstraints$Builder
 *  org.apache.http.util.Args
 */
package org.apache.http.config;

import org.apache.http.config.MessageConstraints;
import org.apache.http.util.Args;

public class MessageConstraints
implements Cloneable {
    private final int maxLineLength;
    private final int maxHeaderCount;
    public static final MessageConstraints DEFAULT = new Builder().build();

    public int getMaxHeaderCount() {
        return this.maxHeaderCount;
    }

    public static Builder custom() {
        return new Builder();
    }

    public static Builder copy(MessageConstraints config) {
        Args.notNull((Object)config, (String)"Message constraints");
        return new Builder().setMaxHeaderCount(config.getMaxHeaderCount()).setMaxLineLength(config.getMaxLineLength());
    }

    protected MessageConstraints clone() throws CloneNotSupportedException {
        return (MessageConstraints)super.clone();
    }

    public static MessageConstraints lineLen(int max) {
        return new MessageConstraints(Args.notNegative((int)max, (String)"Max line length"), -1);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[maxLineLength=").append(this.maxLineLength).append(", maxHeaderCount=").append(this.maxHeaderCount).append("]");
        return builder.toString();
    }

    public int getMaxLineLength() {
        return this.maxLineLength;
    }

    MessageConstraints(int maxLineLength, int maxHeaderCount) {
        this.maxLineLength = maxLineLength;
        this.maxHeaderCount = maxHeaderCount;
    }
}
