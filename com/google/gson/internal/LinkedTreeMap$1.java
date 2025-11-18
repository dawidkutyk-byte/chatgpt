/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal;

import java.util.Comparator;

static final class LinkedTreeMap.1
implements Comparator<Comparable> {
    @Override
    public int compare(Comparable a, Comparable b) {
        return a.compareTo(b);
    }

    LinkedTreeMap.1() {
    }
}
