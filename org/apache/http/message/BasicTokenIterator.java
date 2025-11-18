/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HeaderIterator
 *  org.apache.http.ParseException
 *  org.apache.http.TokenIterator
 *  org.apache.http.util.Args
 */
package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.HeaderIterator;
import org.apache.http.ParseException;
import org.apache.http.TokenIterator;
import org.apache.http.util.Args;

public class BasicTokenIterator
implements TokenIterator {
    protected String currentHeader;
    public static final String HTTP_SEPARATORS = " ,;=()<>@:\\\"/[]?{}\t";
    protected String currentToken;
    protected final HeaderIterator headerIt;
    protected int searchPos;

    public final Object next() throws NoSuchElementException, ParseException {
        return this.nextToken();
    }

    protected int findTokenStart(int pos) {
        int from = Args.notNegative((int)pos, (String)"Search position");
        boolean found = false;
        while (!(found || this.currentHeader == null)) {
            int to = this.currentHeader.length();
            while (!found && from < to) {
                char ch = this.currentHeader.charAt(from);
                if (this.isTokenSeparator(ch) || this.isWhitespace(ch)) {
                    ++from;
                    continue;
                }
                if (!this.isTokenChar(this.currentHeader.charAt(from))) throw new ParseException("Invalid character before token (pos " + from + "): " + this.currentHeader);
                found = true;
            }
            if (found) continue;
            if (this.headerIt.hasNext()) {
                this.currentHeader = this.headerIt.nextHeader().getValue();
                from = 0;
                continue;
            }
            this.currentHeader = null;
        }
        return found ? from : -1;
    }

    protected int findTokenEnd(int from) {
        Args.notNegative((int)from, (String)"Search position");
        int to = this.currentHeader.length();
        int end = from + 1;
        while (end < to) {
            if (!this.isTokenChar(this.currentHeader.charAt(end))) return end;
            ++end;
        }
        return end;
    }

    protected int findNext(int pos) throws ParseException {
        int from = pos;
        if (from < 0) {
            if (!this.headerIt.hasNext()) {
                return -1;
            }
            this.currentHeader = this.headerIt.nextHeader().getValue();
            from = 0;
        } else {
            from = this.findTokenSeparator(from);
        }
        int start = this.findTokenStart(from);
        if (start < 0) {
            this.currentToken = null;
            return -1;
        }
        int end = this.findTokenEnd(start);
        this.currentToken = this.createToken(this.currentHeader, start, end);
        return end;
    }

    protected int findTokenSeparator(int pos) {
        int from = Args.notNegative((int)pos, (String)"Search position");
        boolean found = false;
        int to = this.currentHeader.length();
        while (!found) {
            if (from >= to) return from;
            char ch = this.currentHeader.charAt(from);
            if (this.isTokenSeparator(ch)) {
                found = true;
                continue;
            }
            if (!this.isWhitespace(ch)) {
                if (!this.isTokenChar(ch)) throw new ParseException("Invalid character after token (pos " + from + "): " + this.currentHeader);
                throw new ParseException("Tokens without separator (pos " + from + "): " + this.currentHeader);
            }
            ++from;
        }
        return from;
    }

    public boolean hasNext() {
        return this.currentToken != null;
    }

    protected boolean isTokenChar(char ch) {
        if (Character.isLetterOrDigit(ch)) {
            return true;
        }
        if (Character.isISOControl(ch)) {
            return false;
        }
        if (!this.isHttpSeparator(ch)) return true;
        return false;
    }

    public final void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Removing tokens is not supported.");
    }

    public String nextToken() throws NoSuchElementException, ParseException {
        if (this.currentToken == null) {
            throw new NoSuchElementException("Iteration already finished.");
        }
        String result = this.currentToken;
        this.searchPos = this.findNext(this.searchPos);
        return result;
    }

    protected boolean isHttpSeparator(char ch) {
        return HTTP_SEPARATORS.indexOf(ch) >= 0;
    }

    protected boolean isWhitespace(char ch) {
        return ch == '\t' || Character.isSpaceChar(ch);
    }

    public BasicTokenIterator(HeaderIterator headerIterator) {
        this.headerIt = (HeaderIterator)Args.notNull((Object)headerIterator, (String)"Header iterator");
        this.searchPos = this.findNext(-1);
    }

    protected boolean isTokenSeparator(char ch) {
        return ch == ',';
    }

    protected String createToken(String value, int start, int end) {
        return value.substring(start, end);
    }
}
