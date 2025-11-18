/*
 * Decompiled with CFR 0.152.
 */
package com.rylinaux.plugman.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {
    public static String consolidateStrings(String[] args, int start) {
        if (start < 0) throw new IllegalArgumentException("Argument index out of bounds: " + start + "/" + args.length);
        if (start <= args.length) return Stream.of(args).skip(start).collect(Collectors.joining(" "));
        throw new IllegalArgumentException("Argument index out of bounds: " + start + "/" + args.length);
    }
}
