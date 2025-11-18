/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.UncheckedIOException
 *  org.jsoup.helper.Validate
 */
package org.jsoup.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import org.jsoup.UncheckedIOException;
import org.jsoup.helper.Validate;

public final class CharacterReader {
    static final int readAheadLimit = 24576;
    private int lastIcIndex;
    static final int maxBufferLen = 32768;
    private String[] stringCache = new String[512];
    private int bufLength;
    private int readerPos;
    private static final int maxStringCacheLen = 12;
    private int bufSplitPoint;
    private static final int stringCacheSize = 512;
    private Reader reader;
    private static final int minReadAheadLen = 1024;
    private char[] charBuf;
    private int bufMark = -1;
    private @7\u015aCz String lastIcSeq;
    private int bufPos;
    private @7\u015aCz ArrayList<Integer> newlinePositions = null;
    private int lineNumberOffset = 1;
    static final char EOF = '\uffff';
    private boolean readFully;

    String consumeAttributeQuoted(boolean single) {
        int pos;
        int start = pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        block5: while (pos < remaining) {
            switch (val[pos]) {
                case '\u0000': 
                case '&': {
                    break block5;
                }
                case '\'': {
                    if (!single) break;
                    break block5;
                }
                case '\"': {
                    if (!single) break block5;
                }
            }
            ++pos;
        }
        this.bufPos = pos;
        return pos > start ? CharacterReader.cacheString(this.charBuf, this.stringCache, start, pos - start) : "";
    }

    boolean matchesAny(char ... seq) {
        if (this.isEmpty()) {
            return false;
        }
        this.bufferUp();
        char c = this.charBuf[this.bufPos];
        char[] cArray = seq;
        int n = cArray.length;
        int n2 = 0;
        while (n2 < n) {
            char seek = cArray[n2];
            if (seek == c) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    boolean matches(char c) {
        return !this.isEmpty() && this.charBuf[this.bufPos] == c;
    }

    char consume() {
        this.bufferUp();
        char val = this.isEmptyNoBufferUp() ? (char)'\uffff' : this.charBuf[this.bufPos];
        ++this.bufPos;
        return val;
    }

    private static String cacheString(char[] charBuf, String[] stringCache, int start, int count) {
        if (count > 12) {
            return new String(charBuf, start, count);
        }
        if (count < 1) {
            return "";
        }
        int hash = 0;
        for (int i = 0; i < count; ++i) {
            hash = 31 * hash + charBuf[start + i];
        }
        int index = hash & 0x1FF;
        String cached = stringCache[index];
        if (cached != null && CharacterReader.rangeEquals(charBuf, start, count, cached)) {
            return cached;
        }
        stringCache[index] = cached = new String(charBuf, start, count);
        return cached;
    }

    boolean matchesDigit() {
        if (!this.isEmpty()) char c;
        return (c = this.charBuf[this.bufPos]) >= '0' && c <= '9';
        return false;
    }

    boolean matchConsume(String seq) {
        this.bufferUp();
        if (!this.matches(seq)) return false;
        this.bufPos += seq.length();
        return true;
    }

    String consumeTo(String seq) {
        int offset = this.nextIndexOf(seq);
        if (offset != -1) {
            String consumed = CharacterReader.cacheString(this.charBuf, this.stringCache, this.bufPos, offset);
            this.bufPos += offset;
            return consumed;
        }
        if (this.bufLength - this.bufPos < seq.length()) {
            return this.consumeToEnd();
        }
        int endPos = this.bufLength - seq.length() + 1;
        String consumed = CharacterReader.cacheString(this.charBuf, this.stringCache, this.bufPos, endPos - this.bufPos);
        this.bufPos = endPos;
        return consumed;
    }

    public boolean isTrackNewlines() {
        return this.newlinePositions != null;
    }

    public CharacterReader(String input) {
        this(new StringReader(input), input.length());
    }

    String consumeToEnd() {
        this.bufferUp();
        String data = CharacterReader.cacheString(this.charBuf, this.stringCache, this.bufPos, this.bufLength - this.bufPos);
        this.bufPos = this.bufLength;
        return data;
    }

    String consumeTagName() {
        int pos;
        this.bufferUp();
        int start = pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        block3: while (pos < remaining) {
            switch (val[pos]) {
                case '\t': 
                case '\n': 
                case '\f': 
                case '\r': 
                case ' ': 
                case '/': 
                case '<': 
                case '>': {
                    break block3;
                }
                default: {
                    ++pos;
                    continue block3;
                }
            }
        }
        this.bufPos = pos;
        return pos > start ? CharacterReader.cacheString(this.charBuf, this.stringCache, start, pos - start) : "";
    }

    boolean matches(String seq) {
        this.bufferUp();
        int scanLength = seq.length();
        if (scanLength > this.bufLength - this.bufPos) {
            return false;
        }
        int offset = 0;
        while (offset < scanLength) {
            if (seq.charAt(offset) != this.charBuf[this.bufPos + offset]) {
                return false;
            }
            ++offset;
        }
        return true;
    }

    private void scanBufferForNewlines() {
        if (!this.isTrackNewlines()) {
            return;
        }
        if (this.newlinePositions.size() > 0) {
            int index = this.lineNumIndex(this.readerPos);
            if (index == -1) {
                index = 0;
            }
            int linePos = this.newlinePositions.get(index);
            this.lineNumberOffset += index;
            this.newlinePositions.clear();
            this.newlinePositions.add(linePos);
        }
        int i = this.bufPos;
        while (i < this.bufLength) {
            if (this.charBuf[i] == '\n') {
                this.newlinePositions.add(1 + this.readerPos + i);
            }
            ++i;
        }
    }

    void rewindToMark() {
        if (this.bufMark == -1) {
            throw new UncheckedIOException(new IOException("Mark invalid"));
        }
        this.bufPos = this.bufMark;
        this.unmark();
    }

    boolean containsIgnoreCase(String seq) {
        if (seq.equals(this.lastIcSeq)) {
            if (this.lastIcIndex == -1) {
                return false;
            }
            if (this.lastIcIndex >= this.bufPos) {
                return true;
            }
        }
        this.lastIcSeq = seq;
        String loScan = seq.toLowerCase(Locale.ENGLISH);
        int lo = this.nextIndexOf(loScan);
        if (lo > -1) {
            this.lastIcIndex = this.bufPos + lo;
            return true;
        }
        String hiScan = seq.toUpperCase(Locale.ENGLISH);
        int hi = this.nextIndexOf(hiScan);
        boolean found = hi > -1;
        this.lastIcIndex = found ? this.bufPos + hi : -1;
        return found;
    }

    int lineNumber(int pos) {
        if (!this.isTrackNewlines()) {
            return 1;
        }
        int i = this.lineNumIndex(pos);
        if (i != -1) return i + this.lineNumberOffset + 1;
        return this.lineNumberOffset;
    }

    String consumeLetterSequence() {
        this.bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength) {
            char c = this.charBuf[this.bufPos];
            if (!(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')) {
                if (!Character.isLetter(c)) return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
            }
            ++this.bufPos;
        }
        return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    void unmark() {
        this.bufMark = -1;
    }

    boolean rangeEquals(int start, int count, String cached) {
        return CharacterReader.rangeEquals(this.charBuf, start, count, cached);
    }

    boolean matchesAsciiAlpha() {
        if (!this.isEmpty()) char c;
        return (c = this.charBuf[this.bufPos]) >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
        return false;
    }

    int nextIndexOf(char c) {
        this.bufferUp();
        int i = this.bufPos;
        while (i < this.bufLength) {
            if (c == this.charBuf[i]) {
                return i - this.bufPos;
            }
            ++i;
        }
        return -1;
    }

    static boolean rangeEquals(char[] charBuf, int start, int count, String cached) {
        if (count != cached.length()) return false;
        int i = start;
        int j = 0;
        do {
            if (count-- == 0) return true;
        } while (charBuf[i++] == cached.charAt(j++));
        return false;
    }

    public boolean isEmpty() {
        this.bufferUp();
        return this.bufPos >= this.bufLength;
    }

    int columnNumber(int pos) {
        if (!this.isTrackNewlines()) {
            return pos + 1;
        }
        int i = this.lineNumIndex(pos);
        if (i != -1) return pos - this.newlinePositions.get(i) + 1;
        return pos + 1;
    }

    public void trackNewlines(boolean track) {
        if (track && this.newlinePositions == null) {
            this.newlinePositions = new ArrayList(409);
            this.scanBufferForNewlines();
        } else {
            if (track) return;
            this.newlinePositions = null;
        }
    }

    public void advance() {
        ++this.bufPos;
    }

    public int pos() {
        return this.readerPos + this.bufPos;
    }

    void unconsume() {
        if (this.bufPos < 1) {
            throw new UncheckedIOException(new IOException("WTF: No buffer left to unconsume."));
        }
        --this.bufPos;
    }

    String consumeLetterThenDigitSequence() {
        char c;
        this.bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength && ((c = this.charBuf[this.bufPos]) >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || Character.isLetter(c))) {
            ++this.bufPos;
        }
        while (!this.isEmptyNoBufferUp()) {
            c = this.charBuf[this.bufPos];
            if (c < '0') return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
            if (c > '9') return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
            ++this.bufPos;
        }
        return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    String consumeDigitSequence() {
        this.bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength) {
            char c = this.charBuf[this.bufPos];
            if (c < '0') return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
            if (c > '9') return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
            ++this.bufPos;
        }
        return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    boolean readFully() {
        return this.readFully;
    }

    public void close() {
        if (this.reader == null) {
            return;
        }
        try {
            this.reader.close();
        }
        catch (IOException iOException) {
        }
        finally {
            this.reader = null;
            this.charBuf = null;
            this.stringCache = null;
        }
    }

    public char current() {
        this.bufferUp();
        return this.isEmptyNoBufferUp() ? (char)'\uffff' : this.charBuf[this.bufPos];
    }

    public int columnNumber() {
        return this.columnNumber(this.pos());
    }

    public CharacterReader(Reader input, int sz) {
        Validate.notNull((Object)input);
        Validate.isTrue((boolean)input.markSupported());
        this.reader = input;
        this.charBuf = new char[Math.min(sz, 32768)];
        this.bufferUp();
    }

    boolean matchesIgnoreCase(String seq) {
        this.bufferUp();
        int scanLength = seq.length();
        if (scanLength > this.bufLength - this.bufPos) {
            return false;
        }
        int offset = 0;
        while (offset < scanLength) {
            char upTarget;
            char upScan = Character.toUpperCase(seq.charAt(offset));
            if (upScan != (upTarget = Character.toUpperCase(this.charBuf[this.bufPos + offset]))) {
                return false;
            }
            ++offset;
        }
        return true;
    }

    private int lineNumIndex(int pos) {
        if (!this.isTrackNewlines()) {
            return 0;
        }
        int i = Collections.binarySearch(this.newlinePositions, pos);
        if (i >= -1) return i;
        i = Math.abs(i) - 2;
        return i;
    }

    public String consumeToAny(char ... chars) {
        int pos;
        this.bufferUp();
        int start = pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        int charLen = chars.length;
        block0: while (pos < remaining) {
            for (int i = 0; i < charLen; ++i) {
                if (val[pos] == chars[i]) break block0;
            }
            ++pos;
        }
        this.bufPos = pos;
        return pos > start ? CharacterReader.cacheString(this.charBuf, this.stringCache, start, pos - start) : "";
    }

    private boolean isEmptyNoBufferUp() {
        return this.bufPos >= this.bufLength;
    }

    boolean matchConsumeIgnoreCase(String seq) {
        if (!this.matchesIgnoreCase(seq)) return false;
        this.bufPos += seq.length();
        return true;
    }

    String consumeToAnySorted(char ... chars) {
        int pos;
        this.bufferUp();
        int start = pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        while (pos < remaining && Arrays.binarySearch(chars, val[pos]) < 0) {
            ++pos;
        }
        this.bufPos = pos;
        return this.bufPos > start ? CharacterReader.cacheString(this.charBuf, this.stringCache, start, pos - start) : "";
    }

    boolean matchesAnySorted(char[] seq) {
        this.bufferUp();
        return !this.isEmpty() && Arrays.binarySearch(seq, this.charBuf[this.bufPos]) >= 0;
    }

    String consumeHexSequence() {
        this.bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength) {
            char c = this.charBuf[this.bufPos];
            if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'F')) {
                if (c < 'a') return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
                if (c > 'f') return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
            }
            ++this.bufPos;
        }
        return CharacterReader.cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    void mark() {
        if (this.bufLength - this.bufPos < 1024) {
            this.bufSplitPoint = 0;
        }
        this.bufferUp();
        this.bufMark = this.bufPos;
    }

    int nextIndexOf(CharSequence seq) {
        this.bufferUp();
        char startChar = seq.charAt(0);
        int offset = this.bufPos;
        while (offset < this.bufLength) {
            int i;
            if (startChar != this.charBuf[offset]) {
                while (++offset < this.bufLength && startChar != this.charBuf[offset]) {
                }
            }
            int last = i + seq.length() - 1;
            if (offset < this.bufLength && last <= this.bufLength) {
                int j = 1;
                for (i = offset + 1; i < last && seq.charAt(j) == this.charBuf[i]; ++i, ++j) {
                }
                if (i == last) {
                    return offset - this.bufPos;
                }
            }
            ++offset;
        }
        return -1;
    }

    public int lineNumber() {
        return this.lineNumber(this.pos());
    }

    String consumeData() {
        int pos;
        int start = pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        block3: while (pos < remaining) {
            switch (val[pos]) {
                case '\u0000': 
                case '&': 
                case '<': {
                    break block3;
                }
                default: {
                    ++pos;
                    continue block3;
                }
            }
        }
        this.bufPos = pos;
        return pos > start ? CharacterReader.cacheString(this.charBuf, this.stringCache, start, pos - start) : "";
    }

    private void bufferUp() {
        int offset;
        int pos;
        if (this.readFully) return;
        if (this.bufPos < this.bufSplitPoint) {
            return;
        }
        if (this.bufMark != -1) {
            pos = this.bufMark;
            offset = this.bufPos - this.bufMark;
        } else {
            pos = this.bufPos;
            offset = 0;
        }
        try {
            int read;
            int thisRead;
            long skipped = this.reader.skip(pos);
            this.reader.mark(32768);
            for (read = 0; read <= 1024; read += thisRead) {
                thisRead = this.reader.read(this.charBuf, read, this.charBuf.length - read);
                if (thisRead == -1) {
                    this.readFully = true;
                }
                if (thisRead <= 0) break;
            }
            this.reader.reset();
            if (read > 0) {
                Validate.isTrue((skipped == (long)pos ? 1 : 0) != 0);
                this.bufLength = read;
                this.readerPos += pos;
                this.bufPos = offset;
                if (this.bufMark != -1) {
                    this.bufMark = 0;
                }
                this.bufSplitPoint = Math.min(this.bufLength, 24576);
            }
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        this.scanBufferForNewlines();
        this.lastIcSeq = null;
    }

    public String toString() {
        if (this.bufLength - this.bufPos >= 0) return new String(this.charBuf, this.bufPos, this.bufLength - this.bufPos);
        return "";
    }

    boolean matchesLetter() {
        if (!this.isEmpty()) char c;
        return (c = this.charBuf[this.bufPos]) >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || Character.isLetter(c);
        return false;
    }

    public CharacterReader(Reader input) {
        this(input, 32768);
    }

    String consumeRawData() {
        int pos;
        int start = pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        block3: while (pos < remaining) {
            switch (val[pos]) {
                case '\u0000': 
                case '<': {
                    break block3;
                }
                default: {
                    ++pos;
                    continue block3;
                }
            }
        }
        this.bufPos = pos;
        return pos > start ? CharacterReader.cacheString(this.charBuf, this.stringCache, start, pos - start) : "";
    }

    String posLineCol() {
        return this.lineNumber() + ":" + this.columnNumber();
    }

    public String consumeTo(char c) {
        int offset = this.nextIndexOf(c);
        if (offset == -1) return this.consumeToEnd();
        String consumed = CharacterReader.cacheString(this.charBuf, this.stringCache, this.bufPos, offset);
        this.bufPos += offset;
        return consumed;
    }
}
