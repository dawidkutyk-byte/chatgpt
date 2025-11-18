/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

import java.io.ObjectStreamException;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class NamedLoggerBase
implements Logger,
Serializable {
    protected String name;
    private static final long serialVersionUID = 7535258609338176893L;

    protected Object readResolve() throws ObjectStreamException {
        return LoggerFactory.getLogger(this.getName());
    }

    NamedLoggerBase() {
    }

    @Override
    public String getName() {
        return this.name;
    }
}
