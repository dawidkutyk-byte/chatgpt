/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Header
 *  org.apache.http.HeaderElement
 *  org.apache.http.ParseException
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.message.BasicHeaderValueParser
 *  org.apache.http.message.BasicLineFormatter
 *  org.apache.http.util.Args
 */
package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.BasicLineFormatter;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class BasicHeader
implements Cloneable,
Serializable,
Header {
    private final String value;
    private static final HeaderElement[] EMPTY_HEADER_ELEMENTS = new HeaderElement[0];
    private final String name;
    private static final long serialVersionUID = -5427236326487562174L;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return BasicLineFormatter.INSTANCE.formatHeader(null, (Header)this).toString();
    }

    public String getName() {
        return this.name;
    }

    public BasicHeader(String name, String value) {
        this.name = (String)Args.notNull((Object)name, (String)"Name");
        this.value = value;
    }

    public HeaderElement[] getElements() throws ParseException {
        if (this.getValue() == null) return EMPTY_HEADER_ELEMENTS;
        return BasicHeaderValueParser.parseElements((String)this.getValue(), null);
    }

    public String getValue() {
        return this.value;
    }
}
