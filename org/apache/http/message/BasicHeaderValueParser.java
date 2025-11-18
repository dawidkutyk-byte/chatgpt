/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HeaderElement
 *  org.apache.http.NameValuePair
 *  org.apache.http.ParseException
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.message.BasicHeaderElement
 *  org.apache.http.message.BasicNameValuePair
 *  org.apache.http.message.HeaderValueParser
 *  org.apache.http.message.ParserCursor
 *  org.apache.http.message.TokenParser
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.message;

import java.util.ArrayList;
import java.util.BitSet;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.HeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.message.TokenParser;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class BasicHeaderValueParser
implements HeaderValueParser {
    private static final BitSet TOKEN_DELIMS;
    private static final char PARAM_DELIMITER = ';';
    public static final BasicHeaderValueParser INSTANCE;
    private static final char ELEM_DELIMITER = ',';
    private final TokenParser tokenParser = TokenParser.INSTANCE;
    private static final BitSet VALUE_DELIMS;
    @Deprecated
    public static final BasicHeaderValueParser DEFAULT;

    public static HeaderElement parseHeaderElement(String value, HeaderValueParser parser) throws ParseException {
        Args.notNull((Object)value, (String)"Value");
        CharArrayBuffer buffer = new CharArrayBuffer(value.length());
        buffer.append(value);
        ParserCursor cursor = new ParserCursor(0, value.length());
        return (parser != null ? parser : INSTANCE).parseHeaderElement(buffer, cursor);
    }

    public static NameValuePair[] parseParameters(String value, HeaderValueParser parser) throws ParseException {
        Args.notNull((Object)value, (String)"Value");
        CharArrayBuffer buffer = new CharArrayBuffer(value.length());
        buffer.append(value);
        ParserCursor cursor = new ParserCursor(0, value.length());
        return (parser != null ? parser : INSTANCE).parseParameters(buffer, cursor);
    }

    public NameValuePair parseNameValuePair(CharArrayBuffer buffer, ParserCursor cursor) {
        Args.notNull((Object)buffer, (String)"Char array buffer");
        Args.notNull((Object)cursor, (String)"Parser cursor");
        String name = this.tokenParser.parseToken(buffer, cursor, TOKEN_DELIMS);
        if (cursor.atEnd()) {
            return new BasicNameValuePair(name, null);
        }
        char delim = buffer.charAt(cursor.getPos());
        cursor.updatePos(cursor.getPos() + 1);
        if (delim != '=') {
            return this.createNameValuePair(name, null);
        }
        String value = this.tokenParser.parseValue(buffer, cursor, VALUE_DELIMS);
        if (cursor.atEnd()) return this.createNameValuePair(name, value);
        cursor.updatePos(cursor.getPos() + 1);
        return this.createNameValuePair(name, value);
    }

    protected HeaderElement createHeaderElement(String name, String value, NameValuePair[] params) {
        return new BasicHeaderElement(name, value, params);
    }

    static {
        DEFAULT = new BasicHeaderValueParser();
        INSTANCE = new BasicHeaderValueParser();
        TOKEN_DELIMS = TokenParser.INIT_BITSET((int[])new int[]{61, 59, 44});
        VALUE_DELIMS = TokenParser.INIT_BITSET((int[])new int[]{59, 44});
    }

    public HeaderElement parseHeaderElement(CharArrayBuffer buffer, ParserCursor cursor) {
        Args.notNull((Object)buffer, (String)"Char array buffer");
        Args.notNull((Object)cursor, (String)"Parser cursor");
        NameValuePair nvp = this.parseNameValuePair(buffer, cursor);
        NameValuePair[] params = null;
        if (cursor.atEnd()) return this.createHeaderElement(nvp.getName(), nvp.getValue(), params);
        char ch = buffer.charAt(cursor.getPos() - 1);
        if (ch == ',') return this.createHeaderElement(nvp.getName(), nvp.getValue(), params);
        params = this.parseParameters(buffer, cursor);
        return this.createHeaderElement(nvp.getName(), nvp.getValue(), params);
    }

    @Deprecated
    public NameValuePair parseNameValuePair(CharArrayBuffer buffer, ParserCursor cursor, char[] delimiters) {
        Args.notNull((Object)buffer, (String)"Char array buffer");
        Args.notNull((Object)cursor, (String)"Parser cursor");
        BitSet delimSet = new BitSet();
        if (delimiters != null) {
            for (char delimiter : delimiters) {
                delimSet.set(delimiter);
            }
        }
        delimSet.set(61);
        String name = this.tokenParser.parseToken(buffer, cursor, delimSet);
        if (cursor.atEnd()) {
            return new BasicNameValuePair(name, null);
        }
        char delim = buffer.charAt(cursor.getPos());
        cursor.updatePos(cursor.getPos() + 1);
        if (delim != '=') {
            return this.createNameValuePair(name, null);
        }
        delimSet.clear(61);
        String value = this.tokenParser.parseValue(buffer, cursor, delimSet);
        if (cursor.atEnd()) return this.createNameValuePair(name, value);
        cursor.updatePos(cursor.getPos() + 1);
        return this.createNameValuePair(name, value);
    }

    protected NameValuePair createNameValuePair(String name, String value) {
        return new BasicNameValuePair(name, value);
    }

    public static HeaderElement[] parseElements(String value, HeaderValueParser parser) throws ParseException {
        Args.notNull((Object)value, (String)"Value");
        CharArrayBuffer buffer = new CharArrayBuffer(value.length());
        buffer.append(value);
        ParserCursor cursor = new ParserCursor(0, value.length());
        return (parser != null ? parser : INSTANCE).parseElements(buffer, cursor);
    }

    public NameValuePair[] parseParameters(CharArrayBuffer buffer, ParserCursor cursor) {
        char ch;
        Args.notNull((Object)buffer, (String)"Char array buffer");
        Args.notNull((Object)cursor, (String)"Parser cursor");
        this.tokenParser.skipWhiteSpace(buffer, cursor);
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        do {
            if (cursor.atEnd()) return params.toArray(new NameValuePair[params.size()]);
            NameValuePair param = this.parseNameValuePair(buffer, cursor);
            params.add(param);
        } while ((ch = buffer.charAt(cursor.getPos() - 1)) != ',');
        return params.toArray(new NameValuePair[params.size()]);
    }

    public static NameValuePair parseNameValuePair(String value, HeaderValueParser parser) throws ParseException {
        Args.notNull((Object)value, (String)"Value");
        CharArrayBuffer buffer = new CharArrayBuffer(value.length());
        buffer.append(value);
        ParserCursor cursor = new ParserCursor(0, value.length());
        return (parser != null ? parser : INSTANCE).parseNameValuePair(buffer, cursor);
    }

    public HeaderElement[] parseElements(CharArrayBuffer buffer, ParserCursor cursor) {
        Args.notNull((Object)buffer, (String)"Char array buffer");
        Args.notNull((Object)cursor, (String)"Parser cursor");
        ArrayList<HeaderElement> elements = new ArrayList<HeaderElement>();
        while (!cursor.atEnd()) {
            HeaderElement element = this.parseHeaderElement(buffer, cursor);
            if (element.getName().isEmpty() && element.getValue() == null) continue;
            elements.add(element);
        }
        return elements.toArray(new HeaderElement[elements.size()]);
    }
}
