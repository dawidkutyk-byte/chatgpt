/*
 * Decompiled with CFR 0.152.
 */
package com.rylinaux.plugman.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class FlagUtil {
    public static boolean hasFlag(String[] args, char flag) {
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(args));
        Iterator it = list.iterator();
        do {
            if (!it.hasNext()) return false;
        } while (!((String)it.next()).equalsIgnoreCase("-" + flag));
        it.remove();
        args = list.toArray(args);
        return true;
    }
}
