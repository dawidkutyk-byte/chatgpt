/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.FormattedHeader
 *  org.apache.http.Header
 *  org.apache.http.HeaderElement
 *  org.apache.http.HeaderElementIterator
 *  org.apache.http.HeaderIterator
 *  org.apache.http.message.BasicHeaderValueParser
 *  org.apache.http.message.HeaderValueParser
 *  org.apache.http.message.ParserCursor
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.HeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

public class BasicHeaderElementIterator
implements HeaderElementIterator {
    private final HeaderValueParser parser;
    private HeaderElement currentElement = null;
    private CharArrayBuffer buffer = null;
    private final HeaderIterator headerIt;
    private ParserCursor cursor = null;

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Remove not supported");
    }

    private void bufferHeaderValue() {
        block1: {
            Header h;
            String value;
            this.cursor = null;
            this.buffer = null;
            do {
                if (!this.headerIt.hasNext()) return;
                h = this.headerIt.nextHeader();
                if (!(h instanceof FormattedHeader)) continue;
                this.buffer = ((FormattedHeader)h).getBuffer();
                this.cursor = new ParserCursor(0, this.buffer.length());
                this.cursor.updatePos(((FormattedHeader)h).getValuePos());
                break block1;
            } while ((value = h.getValue()) == null);
            this.buffer = new CharArrayBuffer(value.length());
            this.buffer.append(value);
            this.cursor = new ParserCursor(0, this.buffer.length());
        }
    }

    public boolean hasNext() {
        if (this.currentElement != null) return this.currentElement != null;
        this.parseNextElement();
        return this.currentElement != null;
    }

    public BasicHeaderElementIterator(HeaderIterator headerIterator, HeaderValueParser parser) {
        this.headerIt = (HeaderIterator)Args.notNull((Object)headerIterator, (String)"Header iterator");
        this.parser = (HeaderValueParser)Args.notNull((Object)parser, (String)"Parser");
    }

    public final Object next() throws NoSuchElementException {
        return this.nextElement();
    }

    private void parseNextElement() {
        while (true) {
            if (!this.headerIt.hasNext()) {
                if (this.cursor == null) return;
            }
            if (this.cursor == null || this.cursor.atEnd()) {
                this.bufferHeaderValue();
            }
            if (this.cursor == null) continue;
            while (!this.cursor.atEnd()) {
                HeaderElement e = this.parser.parseHeaderElement(this.buffer, this.cursor);
                if (e.getName().isEmpty() && e.getValue() == null) continue;
                this.currentElement = e;
                return;
            }
            if (!this.cursor.atEnd()) continue;
            this.cursor = null;
            this.buffer = null;
        }
    }

    public HeaderElement nextElement() throws NoSuchElementException {
        if (this.currentElement == null) {
            this.parseNextElement();
        }
        if (this.currentElement == null) {
            throw new NoSuchElementException("No more header elements available");
        }
        HeaderElement element = this.currentElement;
        this.currentElement = null;
        return element;
    }

    public BasicHeaderElementIterator(HeaderIterator headerIterator) {
        this(headerIterator, (HeaderValueParser)BasicHeaderValueParser.INSTANCE);
    }
}
