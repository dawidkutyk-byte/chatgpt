/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.util.Args
 */
package org.apache.http.conn.ssl;

import org.apache.http.util.Args;

final class SubjectName {
    static final int IP = 7;
    private final int type;
    static final int DNS = 2;
    private final String value;

    static SubjectName IP(String value) {
        return new SubjectName(value, 7);
    }

    public String toString() {
        return this.value;
    }

    public int getType() {
        return this.type;
    }

    static SubjectName DNS(String value) {
        return new SubjectName(value, 2);
    }

    public String getValue() {
        return this.value;
    }

    SubjectName(String value, int type) {
        this.value = (String)Args.notNull((Object)value, (String)"Value");
        this.type = Args.positive((int)type, (String)"Type");
    }
}
