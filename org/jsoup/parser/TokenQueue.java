/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 */
package org.jsoup.parser;

import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;

public class TokenQueue {
    private static final String[] ElementSelectorChars = new String[]{"*|", "|", "_", "-"};
    private static final String[] CssIdentifierChars = new String[]{"-", "_"};
    private String queue;
    private static final char ESC = '\\';
    private int pos = 0;

    public String chompTo(String seq) {
        String data = this.consumeTo(seq);
        this.matchChomp(seq);
        return data;
    }

    public String consumeTo(String seq) {
        int offset = this.queue.indexOf(seq, this.pos);
        if (offset == -1) return this.remainder();
        String consumed = this.queue.substring(this.pos, offset);
        this.pos += consumed.length();
        return consumed;
    }

    private String consumeEscapedCssIdentifier(String ... matches) {
        int start = this.pos;
        boolean escaped = false;
        while (!this.isEmpty()) {
            if (this.queue.charAt(this.pos) == '\\' && this.remainingLength() > 1) {
                escaped = true;
                this.pos += 2;
                continue;
            }
            if (!this.matchesCssIdentifier(matches)) break;
            ++this.pos;
        }
        String consumed = this.queue.substring(start, this.pos);
        return escaped ? TokenQueue.unescape(consumed) : consumed;
    }

    public String consumeWord() {
        int start = this.pos;
        while (this.matchesWord()) {
            ++this.pos;
        }
        return this.queue.substring(start, this.pos);
    }

    public boolean matchesWord() {
        return !this.isEmpty() && Character.isLetterOrDigit(this.queue.charAt(this.pos));
    }

    public boolean consumeWhitespace() {
        boolean seen = false;
        while (this.matchesWhitespace()) {
            ++this.pos;
            seen = true;
        }
        return seen;
    }

    public void addFirst(String seq) {
        this.queue = seq + this.queue.substring(this.pos);
        this.pos = 0;
    }

    public char consume() {
        return this.queue.charAt(this.pos++);
    }

    public void consume(String seq) {
        if (!this.matches(seq)) {
            throw new IllegalStateException("Queue did not match expected sequence");
        }
        int len = seq.length();
        if (len > this.remainingLength()) {
            throw new IllegalStateException("Queue not long enough to consume sequence");
        }
        this.pos += len;
    }

    public boolean matchesAny(char ... seq) {
        if (this.isEmpty()) {
            return false;
        }
        char[] cArray = seq;
        int n = cArray.length;
        int n2 = 0;
        while (n2 < n) {
            char c = cArray[n2];
            if (this.queue.charAt(this.pos) == c) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    public String consumeElementSelector() {
        return this.consumeEscapedCssIdentifier(ElementSelectorChars);
    }

    public String remainder() {
        String remainder = this.queue.substring(this.pos);
        this.pos = this.queue.length();
        return remainder;
    }

    public static String unescape(String in) {
        StringBuilder out = StringUtil.borrowBuilder();
        char last = '\u0000';
        char[] cArray = in.toCharArray();
        int n = cArray.length;
        int n2 = 0;
        while (n2 < n) {
            char c = cArray[n2];
            if (c == '\\') {
                if (last == '\\') {
                    out.append(c);
                    c = '\u0000';
                }
            } else {
                out.append(c);
            }
            last = c;
            ++n2;
        }
        return StringUtil.releaseBuilder((StringBuilder)out);
    }

    private int remainingLength() {
        return this.queue.length() - this.pos;
    }

    public String consumeToAny(String ... seq) {
        int start = this.pos;
        while (!this.isEmpty()) {
            if (this.matchesAny(seq)) return this.queue.substring(start, this.pos);
            ++this.pos;
        }
        return this.queue.substring(start, this.pos);
    }

    public String chompBalanced(char open, char close) {
        int start = -1;
        int end = -1;
        int depth = 0;
        int last = 0;
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;
        boolean inRegexQE = false;
        while (!this.isEmpty()) {
            block14: {
                char c;
                block15: {
                    block12: {
                        block13: {
                            c = this.consume();
                            if (last == 92) break block12;
                            if (c == '\'' && c != open && !inDoubleQuote) {
                                inSingleQuote = !inSingleQuote;
                            } else if (c == '\"' && c != open && !inSingleQuote) {
                                boolean bl = inDoubleQuote = !inDoubleQuote;
                            }
                            if (!inSingleQuote && !inDoubleQuote && !inRegexQE) break block13;
                            last = c;
                            break block14;
                        }
                        if (c == open) {
                            ++depth;
                            if (start == -1) {
                                start = this.pos;
                            }
                        } else if (c == close) {
                            --depth;
                        }
                        break block15;
                    }
                    if (c == 'Q') {
                        inRegexQE = true;
                    } else if (c == 'E') {
                        inRegexQE = false;
                    }
                }
                if (depth > 0 && last != 0) {
                    end = this.pos;
                }
                last = c;
            }
            if (depth > 0) continue;
        }
        String out = end >= 0 ? this.queue.substring(start, end) : "";
        if (depth <= 0) return out;
        Validate.fail((String)("Did not find balanced marker at '" + out + "'"));
        return out;
    }

    public static String escapeCssIdentifier(String in) {
        StringBuilder out = StringUtil.borrowBuilder();
        TokenQueue q = new TokenQueue(in);
        while (!q.isEmpty()) {
            if (q.matchesCssIdentifier(ElementSelectorChars)) {
                out.append(q.consume());
                continue;
            }
            out.append('\\').append(q.consume());
        }
        return StringUtil.releaseBuilder((StringBuilder)out);
    }

    private boolean matchesCssIdentifier(String ... matches) {
        return this.matchesWord() || this.matchesAny(matches);
    }

    public boolean matchChomp(String seq) {
        if (!this.matches(seq)) return false;
        this.pos += seq.length();
        return true;
    }

    public boolean isEmpty() {
        return this.remainingLength() == 0;
    }

    public String consumeCssIdentifier() {
        return this.consumeEscapedCssIdentifier(CssIdentifierChars);
    }

    public TokenQueue(String data) {
        Validate.notNull((Object)data);
        this.queue = data;
    }

    public boolean matches(String seq) {
        return this.queue.regionMatches(true, this.pos, seq, 0, seq.length());
    }

    public String consumeToIgnoreCase(String seq) {
        int start = this.pos;
        String first = seq.substring(0, 1);
        boolean canScan = first.toLowerCase().equals(first.toUpperCase());
        while (!this.isEmpty()) {
            if (this.matches(seq)) {
                return this.queue.substring(start, this.pos);
            }
            if (canScan) {
                int skip = this.queue.indexOf(first, this.pos) - this.pos;
                if (skip == 0) {
                    ++this.pos;
                    continue;
                }
                if (skip < 0) {
                    this.pos = this.queue.length();
                    continue;
                }
                this.pos += skip;
                continue;
            }
            ++this.pos;
        }
        return this.queue.substring(start, this.pos);
    }

    public void advance() {
        if (this.isEmpty()) return;
        ++this.pos;
    }

    public String toString() {
        return this.queue.substring(this.pos);
    }

    public String chompToIgnoreCase(String seq) {
        String data = this.consumeToIgnoreCase(seq);
        this.matchChomp(seq);
        return data;
    }

    public boolean matchesAny(String ... seq) {
        String[] stringArray = seq;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String s = stringArray[n2];
            if (this.matches(s)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    public boolean matchesWhitespace() {
        return !this.isEmpty() && StringUtil.isWhitespace((int)this.queue.charAt(this.pos));
    }
}
