/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.logging;

public class LogConfigurationException
extends RuntimeException {
    protected Throwable cause = null;
    private static final long serialVersionUID = 8486587136871052495L;

    public LogConfigurationException(Throwable cause) {
        this(cause == null ? null : cause.toString(), cause);
    }

    public LogConfigurationException(String message) {
        super(message);
    }

    public LogConfigurationException() {
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }

    public LogConfigurationException(String message, Throwable cause) {
        super(new StringBuffer().append(message).append(" (Caused by ").append(cause).append(")").toString());
        this.cause = cause;
    }
}
