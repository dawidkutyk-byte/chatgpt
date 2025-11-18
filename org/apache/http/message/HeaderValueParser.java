/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HeaderElement
 *  org.apache.http.NameValuePair
 *  org.apache.http.ParseException
 *  org.apache.http.message.ParserCursor
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public interface HeaderValueParser {
    public NameValuePair parseNameValuePair(CharArrayBuffer var1, ParserCursor var2) throws ParseException;

    public NameValuePair[] parseParameters(CharArrayBuffer var1, ParserCursor var2) throws ParseException;

    public HeaderElement parseHeaderElement(CharArrayBuffer var1, ParserCursor var2) throws ParseException;

    public HeaderElement[] parseElements(CharArrayBuffer var1, ParserCursor var2) throws ParseException;
}
