/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.util.PublicSuffixList
 *  org.apache.http.conn.util.PublicSuffixListParser
 *  org.apache.http.impl.cookie.PublicSuffixFilter
 */
package org.apache.http.impl.cookie;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.util.PublicSuffixList;
import org.apache.http.impl.cookie.PublicSuffixFilter;

@Deprecated
@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class PublicSuffixListParser {
    private final org.apache.http.conn.util.PublicSuffixListParser parser;
    private final PublicSuffixFilter filter;

    PublicSuffixListParser(PublicSuffixFilter filter) {
        this.filter = filter;
        this.parser = new org.apache.http.conn.util.PublicSuffixListParser();
    }

    public void parse(Reader reader) throws IOException {
        PublicSuffixList suffixList = this.parser.parse(reader);
        this.filter.setPublicSuffixes((Collection)suffixList.getRules());
        this.filter.setExceptions((Collection)suffixList.getExceptions());
    }
}
