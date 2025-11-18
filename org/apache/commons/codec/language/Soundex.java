/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.language.SoundexUtils;

public class Soundex
implements StringEncoder {
    private final boolean specialCaseHW;
    public static final Soundex US_ENGLISH;
    private final char[] soundexMapping;
    public static final Soundex US_ENGLISH_GENEALOGY;
    private static final char[] US_ENGLISH_MAPPING;
    public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";
    public static final char SILENT_MARKER = '-';
    @Deprecated
    private int maxLength = 4;
    public static final Soundex US_ENGLISH_SIMPLIFIED;

    public Soundex(char[] mapping) {
        this.soundexMapping = new char[mapping.length];
        System.arraycopy(mapping, 0, this.soundexMapping, 0, mapping.length);
        this.specialCaseHW = !this.hasMarker(this.soundexMapping);
    }

    private boolean hasMarker(char[] mapping) {
        char[] cArray = mapping;
        int n = cArray.length;
        int n2 = 0;
        while (n2 < n) {
            char ch = cArray[n2];
            if (ch == '-') {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) return this.soundex((String)obj);
        throw new EncoderException("Parameter supplied to Soundex encode is not of type java.lang.String");
    }

    public Soundex(String mapping, boolean specialCaseHW) {
        this.soundexMapping = mapping.toCharArray();
        this.specialCaseHW = specialCaseHW;
    }

    private char map(char ch) {
        int index = ch - 65;
        if (index < 0) throw new IllegalArgumentException("The character is not mapped: " + ch + " (index=" + index + ")");
        if (index < this.soundexMapping.length) return this.soundexMapping[index];
        throw new IllegalArgumentException("The character is not mapped: " + ch + " (index=" + index + ")");
    }

    public Soundex(String mapping) {
        this.soundexMapping = mapping.toCharArray();
        this.specialCaseHW = !this.hasMarker(this.soundexMapping);
    }

    public int difference(String s1, String s2) throws EncoderException {
        return SoundexUtils.difference(this, s1, s2);
    }

    static {
        US_ENGLISH_MAPPING = US_ENGLISH_MAPPING_STRING.toCharArray();
        US_ENGLISH = new Soundex();
        US_ENGLISH_SIMPLIFIED = new Soundex(US_ENGLISH_MAPPING_STRING, false);
        US_ENGLISH_GENEALOGY = new Soundex("-123-12--22455-12623-1-2-2");
    }

    public Soundex() {
        this.soundexMapping = US_ENGLISH_MAPPING;
        this.specialCaseHW = true;
    }

    public String soundex(String str) {
        if (str == null) {
            return null;
        }
        if ((str = SoundexUtils.clean(str)).length() == 0) {
            return str;
        }
        char[] out = new char[]{'0', '0', '0', '0'};
        int count = 0;
        char first = str.charAt(0);
        out[count++] = first;
        char lastDigit = this.map(first);
        int i = 1;
        while (i < str.length()) {
            char digit;
            if (count >= out.length) return new String(out);
            char ch = str.charAt(i);
            if ((!this.specialCaseHW || ch != 'H' && ch != 'W') && (digit = this.map(ch)) != '-') {
                if (digit != '0' && digit != lastDigit) {
                    out[count++] = digit;
                }
                lastDigit = digit;
            }
            ++i;
        }
        return new String(out);
    }

    @Deprecated
    public int getMaxLength() {
        return this.maxLength;
    }

    @Deprecated
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String encode(String str) {
        return this.soundex(str);
    }
}
