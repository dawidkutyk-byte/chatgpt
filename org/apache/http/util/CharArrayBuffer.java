/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.protocol.HTTP
 *  org.apache.http.util.Args
 *  org.apache.http.util.ByteArrayBuffer
 */
package org.apache.http.util;

import java.io.Serializable;
import java.nio.CharBuffer;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.ByteArrayBuffer;

public final class CharArrayBuffer
implements CharSequence,
Serializable {
    private int len;
    private char[] buffer;
    private static final long serialVersionUID = -6208952725094867135L;

    public void append(byte[] b, int off, int len) {
        if (b == null) {
            return;
        }
        if (off < 0) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (off > b.length) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (len < 0) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (off + len < 0) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (off + len > b.length) {
            throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        }
        if (len == 0) {
            return;
        }
        int oldlen = this.len;
        int newlen = oldlen + len;
        if (newlen > this.buffer.length) {
            this.expand(newlen);
        }
        int i1 = off;
        int i2 = oldlen;
        while (true) {
            if (i2 >= newlen) {
                this.len = newlen;
                return;
            }
            this.buffer[i2] = (char)(b[i1] & 0xFF);
            ++i1;
            ++i2;
        }
    }

    public CharArrayBuffer(int capacity) {
        Args.notNegative((int)capacity, (String)"Buffer capacity");
        this.buffer = new char[capacity];
    }

    @Override
    public char charAt(int i) {
        return this.buffer[i];
    }

    public void append(CharArrayBuffer b) {
        if (b == null) {
            return;
        }
        this.append(b.buffer, 0, b.len);
    }

    public void append(ByteArrayBuffer b, int off, int len) {
        if (b == null) {
            return;
        }
        this.append(b.buffer(), off, len);
    }

    @Override
    public String toString() {
        return new String(this.buffer, 0, this.len);
    }

    @Override
    public int length() {
        return this.len;
    }

    private void expand(int newlen) {
        char[] newbuffer = new char[Math.max(this.buffer.length << 1, newlen)];
        System.arraycopy(this.buffer, 0, newbuffer, 0, this.len);
        this.buffer = newbuffer;
    }

    public String substringTrimmed(int beginIndex, int endIndex) {
        int beginIndex0;
        if (beginIndex < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + beginIndex);
        }
        if (endIndex > this.len) {
            throw new IndexOutOfBoundsException("endIndex: " + endIndex + " > length: " + this.len);
        }
        if (beginIndex > endIndex) {
            throw new IndexOutOfBoundsException("beginIndex: " + beginIndex + " > endIndex: " + endIndex);
        }
        int endIndex0 = endIndex;
        for (beginIndex0 = beginIndex; beginIndex0 < endIndex && HTTP.isWhitespace((char)this.buffer[beginIndex0]); ++beginIndex0) {
        }
        while (endIndex0 > beginIndex0) {
            if (!HTTP.isWhitespace((char)this.buffer[endIndex0 - 1])) return new String(this.buffer, beginIndex0, endIndex0 - beginIndex0);
            --endIndex0;
        }
        return new String(this.buffer, beginIndex0, endIndex0 - beginIndex0);
    }

    public int capacity() {
        return this.buffer.length;
    }

    public int indexOf(int ch, int from, int to) {
        int endIndex;
        int beginIndex = from;
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if ((endIndex = to) > this.len) {
            endIndex = this.len;
        }
        if (beginIndex > endIndex) {
            return -1;
        }
        int i = beginIndex;
        while (i < endIndex) {
            if (this.buffer[i] == ch) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public int indexOf(int ch) {
        return this.indexOf(ch, 0, this.len);
    }

    @Override
    public boolean isEmpty() {
        return this.len == 0;
    }

    public void clear() {
        this.len = 0;
    }

    public void append(char ch) {
        int newlen = this.len + 1;
        if (newlen > this.buffer.length) {
            this.expand(newlen);
        }
        this.buffer[this.len] = ch;
        this.len = newlen;
    }

    public char[] buffer() {
        return this.buffer;
    }

    public void append(CharArrayBuffer b, int off, int len) {
        if (b == null) {
            return;
        }
        this.append(b.buffer, off, len);
    }

    public boolean isFull() {
        return this.len == this.buffer.length;
    }

    public void append(char[] b, int off, int len) {
        if (b == null) {
            return;
        }
        if (off < 0) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (off > b.length) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (len < 0) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (off + len < 0) throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        if (off + len > b.length) {
            throw new IndexOutOfBoundsException("off: " + off + " len: " + len + " b.length: " + b.length);
        }
        if (len == 0) {
            return;
        }
        int newlen = this.len + len;
        if (newlen > this.buffer.length) {
            this.expand(newlen);
        }
        System.arraycopy(b, off, this.buffer, this.len, len);
        this.len = newlen;
    }

    public void setLength(int len) {
        if (len < 0) throw new IndexOutOfBoundsException("len: " + len + " < 0 or > buffer len: " + this.buffer.length);
        if (len > this.buffer.length) {
            throw new IndexOutOfBoundsException("len: " + len + " < 0 or > buffer len: " + this.buffer.length);
        }
        this.len = len;
    }

    public void ensureCapacity(int required) {
        if (required <= 0) {
            return;
        }
        int available = this.buffer.length - this.len;
        if (required <= available) return;
        this.expand(this.len + required);
    }

    public void append(String str) {
        String s = str != null ? str : "null";
        int strlen = s.length();
        int newlen = this.len + strlen;
        if (newlen > this.buffer.length) {
            this.expand(newlen);
        }
        s.getChars(0, strlen, this.buffer, this.len);
        this.len = newlen;
    }

    @Override
    public CharSequence subSequence(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + beginIndex);
        }
        if (endIndex > this.len) {
            throw new IndexOutOfBoundsException("endIndex: " + endIndex + " > length: " + this.len);
        }
        if (beginIndex <= endIndex) return CharBuffer.wrap(this.buffer, beginIndex, endIndex);
        throw new IndexOutOfBoundsException("beginIndex: " + beginIndex + " > endIndex: " + endIndex);
    }

    public char[] toCharArray() {
        char[] b = new char[this.len];
        if (this.len <= 0) return b;
        System.arraycopy(this.buffer, 0, b, 0, this.len);
        return b;
    }

    public String substring(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + beginIndex);
        }
        if (endIndex > this.len) {
            throw new IndexOutOfBoundsException("endIndex: " + endIndex + " > length: " + this.len);
        }
        if (beginIndex <= endIndex) return new String(this.buffer, beginIndex, endIndex - beginIndex);
        throw new IndexOutOfBoundsException("beginIndex: " + beginIndex + " > endIndex: " + endIndex);
    }

    public void append(Object obj) {
        this.append(String.valueOf(obj));
    }
}
