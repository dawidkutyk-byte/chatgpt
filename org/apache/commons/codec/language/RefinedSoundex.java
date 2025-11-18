/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.language.SoundexUtils;

public class RefinedSoundex
implements StringEncoder {
    public static final String US_ENGLISH_MAPPING_STRING = "01360240043788015936020505";
    public static final RefinedSoundex US_ENGLISH;
    private final char[] soundexMapping;
    private static final char[] US_ENGLISH_MAPPING;

    public String soundex(String str) {
        if (str == null) {
            return null;
        }
        if ((str = SoundexUtils.clean(str)).length() == 0) {
            return str;
        }
        StringBuilder sBuf = new StringBuilder();
        sBuf.append(str.charAt(0));
        char last = '*';
        int i = 0;
        while (i < str.length()) {
            char current = this.getMappingCode(str.charAt(i));
            if (current != last) {
                if (current != '\u0000') {
                    sBuf.append(current);
                }
                last = current;
            }
            ++i;
        }
        return sBuf.toString();
    }

    public int difference(String s1, String s2) throws EncoderException {
        return SoundexUtils.difference(this, s1, s2);
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) return this.soundex((String)obj);
        throw new EncoderException("Parameter supplied to RefinedSoundex encode is not of type java.lang.String");
    }

    public RefinedSoundex(char[] mapping) {
        this.soundexMapping = new char[mapping.length];
        System.arraycopy(mapping, 0, this.soundexMapping, 0, mapping.length);
    }

    public RefinedSoundex() {
        this.soundexMapping = US_ENGLISH_MAPPING;
    }

    public RefinedSoundex(String mapping) {
        this.soundexMapping = mapping.toCharArray();
    }

    char getMappingCode(char c) {
        if (Character.isLetter(c)) return this.soundexMapping[Character.toUpperCase(c) - 65];
        return '\u0000';
    }

    @Override
    public String encode(String str) {
        return this.soundex(str);
    }

    static {
        US_ENGLISH_MAPPING = US_ENGLISH_MAPPING_STRING.toCharArray();
        US_ENGLISH = new RefinedSoundex();
    }
}
