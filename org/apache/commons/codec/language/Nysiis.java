/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import java.util.regex.Pattern;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.language.SoundexUtils;

public class Nysiis
implements StringEncoder {
    private static final Pattern PAT_PH_PF;
    private static final char[] CHARS_N;
    private static final Pattern PAT_SCH;
    private static final Pattern PAT_EE_IE;
    private final boolean strict;
    private static final char[] CHARS_FF;
    private static final char[] CHARS_SSS;
    private static final Pattern PAT_DT_ETC;
    private static final char[] CHARS_C;
    private static final char[] CHARS_S;
    private static final Pattern PAT_KN;
    private static final char[] CHARS_A;
    private static final Pattern PAT_K;
    private static final char[] CHARS_G;
    private static final Pattern PAT_MAC;
    private static final char SPACE = ' ';
    private static final char[] CHARS_AF;
    private static final char[] CHARS_NN;
    private static final int TRUE_LENGTH = 6;

    public boolean isStrict() {
        return this.strict;
    }

    public Nysiis() {
        this(true);
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) return this.nysiis((String)obj);
        throw new EncoderException("Parameter supplied to Nysiis encode is not of type java.lang.String");
    }

    public Nysiis(boolean strict) {
        this.strict = strict;
    }

    private static boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    @Override
    public String encode(String str) {
        return this.nysiis(str);
    }

    private static char[] transcodeRemaining(char prev, char curr, char next, char aNext) {
        if (curr == 'E' && next == 'V') {
            return CHARS_AF;
        }
        if (Nysiis.isVowel(curr)) {
            return CHARS_A;
        }
        if (curr == 'Q') {
            return CHARS_G;
        }
        if (curr == 'Z') {
            return CHARS_S;
        }
        if (curr == 'M') {
            return CHARS_N;
        }
        if (curr == 'K') {
            if (next != 'N') return CHARS_C;
            return CHARS_NN;
        }
        if (curr == 'S' && next == 'C' && aNext == 'H') {
            return CHARS_SSS;
        }
        if (curr == 'P' && next == 'H') {
            return CHARS_FF;
        }
        if (!(curr != 'H' || Nysiis.isVowel(prev) && Nysiis.isVowel(next))) {
            return new char[]{prev};
        }
        if (curr != 'W' || !Nysiis.isVowel(prev)) return new char[]{curr};
        return new char[]{prev};
    }

    public String nysiis(String str) {
        if (str == null) {
            return null;
        }
        if ((str = SoundexUtils.clean(str)).length() == 0) {
            return str;
        }
        str = PAT_MAC.matcher(str).replaceFirst("MCC");
        str = PAT_KN.matcher(str).replaceFirst("NN");
        str = PAT_K.matcher(str).replaceFirst("C");
        str = PAT_PH_PF.matcher(str).replaceFirst("FF");
        str = PAT_SCH.matcher(str).replaceFirst("SSS");
        str = PAT_EE_IE.matcher(str).replaceFirst("Y");
        str = PAT_DT_ETC.matcher(str).replaceFirst("D");
        StringBuilder key = new StringBuilder(str.length());
        key.append(str.charAt(0));
        char[] chars = str.toCharArray();
        int len = chars.length;
        for (int i = 1; i < len; ++i) {
            char next = i < len - 1 ? chars[i + 1] : (char)' ';
            char aNext = i < len - 2 ? chars[i + 2] : (char)' ';
            char[] transcoded = Nysiis.transcodeRemaining(chars[i - 1], chars[i], next, aNext);
            System.arraycopy(transcoded, 0, chars, i, transcoded.length);
            if (chars[i] == chars[i - 1]) continue;
            key.append(chars[i]);
        }
        if (key.length() > 1) {
            char last2Char;
            char lastChar = key.charAt(key.length() - 1);
            if (lastChar == 'S') {
                key.deleteCharAt(key.length() - 1);
                lastChar = key.charAt(key.length() - 1);
            }
            if (key.length() > 2 && (last2Char = key.charAt(key.length() - 2)) == 'A' && lastChar == 'Y') {
                key.deleteCharAt(key.length() - 2);
            }
            if (lastChar == 'A') {
                key.deleteCharAt(key.length() - 1);
            }
        }
        String string = key.toString();
        return this.isStrict() ? string.substring(0, Math.min(6, string.length())) : string;
    }

    static {
        CHARS_A = new char[]{'A'};
        CHARS_AF = new char[]{'A', 'F'};
        CHARS_C = new char[]{'C'};
        CHARS_FF = new char[]{'F', 'F'};
        CHARS_G = new char[]{'G'};
        CHARS_N = new char[]{'N'};
        CHARS_NN = new char[]{'N', 'N'};
        CHARS_S = new char[]{'S'};
        CHARS_SSS = new char[]{'S', 'S', 'S'};
        PAT_MAC = Pattern.compile("^MAC");
        PAT_KN = Pattern.compile("^KN");
        PAT_K = Pattern.compile("^K");
        PAT_PH_PF = Pattern.compile("^(PH|PF)");
        PAT_SCH = Pattern.compile("^SCH");
        PAT_EE_IE = Pattern.compile("(EE|IE)$");
        PAT_DT_ETC = Pattern.compile("(DT|RT|RD|NT|ND)$");
    }
}
