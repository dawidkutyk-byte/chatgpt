/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;

class BasicMDCAdapter.1
extends InheritableThreadLocal<Map<String, String>> {
    BasicMDCAdapter.1() {
    }

    @Override
    protected Map<String, String> childValue(Map<String, String> parentValue) {
        if (parentValue != null) return new HashMap<String, String>(parentValue);
        return null;
    }
}
