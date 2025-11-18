/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.util.DomainType
 *  org.apache.http.util.Args
 */
package org.apache.http.conn.util;

import java.util.Collections;
import java.util.List;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.util.DomainType;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public final class PublicSuffixList {
    private final DomainType type;
    private final List<String> exceptions;
    private final List<String> rules;

    public List<String> getRules() {
        return this.rules;
    }

    public PublicSuffixList(DomainType type, List<String> rules, List<String> exceptions) {
        this.type = (DomainType)Args.notNull((Object)type, (String)"Domain type");
        this.rules = Collections.unmodifiableList((List)Args.notNull(rules, (String)"Domain suffix rules"));
        this.exceptions = Collections.unmodifiableList(exceptions != null ? exceptions : Collections.emptyList());
    }

    public DomainType getType() {
        return this.type;
    }

    public PublicSuffixList(List<String> rules, List<String> exceptions) {
        this(DomainType.UNKNOWN, rules, exceptions);
    }

    public List<String> getExceptions() {
        return this.exceptions;
    }
}
