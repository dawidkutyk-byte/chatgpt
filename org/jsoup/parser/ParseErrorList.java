/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.parser.ParseError
 */
package org.jsoup.parser;

import java.util.ArrayList;
import org.jsoup.parser.ParseError;

public class ParseErrorList
extends ArrayList<ParseError> {
    private final int maxSize;
    private static final int INITIAL_CAPACITY = 16;
    private final int initialCapacity;

    ParseErrorList(ParseErrorList copy) {
        this(copy.initialCapacity, copy.maxSize);
    }

    boolean canAddError() {
        return this.size() < this.maxSize;
    }

    public static ParseErrorList tracking(int maxSize) {
        return new ParseErrorList(16, maxSize);
    }

    int getMaxSize() {
        return this.maxSize;
    }

    ParseErrorList(int initialCapacity, int maxSize) {
        super(initialCapacity);
        this.initialCapacity = initialCapacity;
        this.maxSize = maxSize;
    }

    @Override
    public Object clone() {
        return super.clone();
    }

    public static ParseErrorList noTracking() {
        return new ParseErrorList(0, 0);
    }
}
