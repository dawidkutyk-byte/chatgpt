/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.SerializationException
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.Entities$1
 *  org.jsoup.nodes.Entities$CoreCharset
 *  org.jsoup.nodes.Entities$EscapeMode
 *  org.jsoup.parser.CharacterReader
 *  org.jsoup.parser.Parser
 */
package org.jsoup.nodes;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Parser;

/*
 * Exception performing whole class analysis ignored.
 */
public class Entities {
    private static // Could not load outer class - annotation placement on inner may be incorrect
     @7\u015aCz Document.OutputSettings DefaultOutput;
    static final int codepointRadix = 36;
    private static final int empty = -1;
    private static final char[] codeDelims;
    private static final HashMap<String, String> multipoints;
    private static final String emptyName = "";

    public static boolean isNamedEntity(String name) {
        return EscapeMode.extended.codepointForName(name) != -1;
    }

    public static String unescape(String string) {
        return Entities.unescape(string, false);
    }

    private static boolean canEncode(CoreCharset charset, char c, CharsetEncoder fallback) {
        switch (1.$SwitchMap$org$jsoup$nodes$Entities$CoreCharset[charset.ordinal()]) {
            case 1: {
                return c < '\u0080';
            }
            case 2: {
                return true;
            }
        }
        return fallback.canEncode(c);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void load(EscapeMode e, String pointsData, int size) {
        EscapeMode.access$102((EscapeMode)e, (String[])new String[size]);
        EscapeMode.access$202((EscapeMode)e, (int[])new int[size]);
        EscapeMode.access$302((EscapeMode)e, (int[])new int[size]);
        EscapeMode.access$402((EscapeMode)e, (String[])new String[size]);
        int i = 0;
        try (CharacterReader reader = new CharacterReader(pointsData);){
            while (!reader.isEmpty()) {
                int cp2;
                String name = reader.consumeTo('=');
                reader.advance();
                int cp1 = Integer.parseInt(reader.consumeToAny(codeDelims), 36);
                char codeDelim = reader.current();
                reader.advance();
                if (codeDelim == ',') {
                    cp2 = Integer.parseInt(reader.consumeTo(';'), 36);
                    reader.advance();
                } else {
                    cp2 = -1;
                }
                String indexS = reader.consumeTo('&');
                int index = Integer.parseInt(indexS, 36);
                reader.advance();
                EscapeMode.access$100((EscapeMode)e)[i] = name;
                EscapeMode.access$200((EscapeMode)e)[i] = cp1;
                EscapeMode.access$300((EscapeMode)e)[index] = cp1;
                EscapeMode.access$400((EscapeMode)e)[index] = name;
                if (cp2 != -1) {
                    multipoints.put(name, new String(new int[]{cp1, cp2}, 0, 2));
                }
                ++i;
            }
            Validate.isTrue((i == size ? 1 : 0) != 0, (String)"Unexpected count of entities loaded");
        }
    }

    public static String escape(String string) {
        if (DefaultOutput != null) return Entities.escape(string, DefaultOutput);
        DefaultOutput = new Document.OutputSettings();
        return Entities.escape(string, DefaultOutput);
    }

    /*
     * Unable to fully structure code
     */
    static void escape(Appendable accum, String string, Document.OutputSettings out, boolean inAttribute, boolean normaliseWhite, boolean stripLeadingWhite, boolean trimTrailing) throws IOException {
        lastWasWhite = false;
        reachedNonWhite = false;
        escapeMode = out.escapeMode();
        encoder = out.encoder();
        coreCharset = out.coreCharset;
        length = string.length();
        skipped = false;
        offset = 0;
        while (offset < length) {
            codePoint = string.codePointAt(offset);
            if (!normaliseWhite) ** GOTO lbl27
            if (StringUtil.isWhitespace((int)codePoint)) {
                if (!(stripLeadingWhite && !reachedNonWhite || lastWasWhite)) {
                    if (trimTrailing) {
                        skipped = true;
                    } else {
                        accum.append(' ');
                        lastWasWhite = true;
                    }
                }
            } else {
                lastWasWhite = false;
                reachedNonWhite = true;
                if (skipped) {
                    accum.append(' ');
                    skipped = false;
                }
lbl27:
                // 4 sources

                if (codePoint < 65536) {
                    c = (char)codePoint;
                    switch (c) {
                        case '&': {
                            accum.append("&amp;");
                            break;
                        }
                        case '\u00a0': {
                            if (escapeMode != EscapeMode.xhtml) {
                                accum.append("&nbsp;");
                                break;
                            }
                            accum.append("&#xa0;");
                            break;
                        }
                        case '<': {
                            if (!inAttribute || escapeMode == EscapeMode.xhtml || out.syntax() == Document.OutputSettings.Syntax.xml) {
                                accum.append("&lt;");
                                break;
                            }
                            accum.append(c);
                            break;
                        }
                        case '>': {
                            if (!inAttribute) {
                                accum.append("&gt;");
                                break;
                            }
                            accum.append(c);
                            break;
                        }
                        case '\"': {
                            if (inAttribute) {
                                accum.append("&quot;");
                                break;
                            }
                            accum.append(c);
                            break;
                        }
                        case '\t': 
                        case '\n': 
                        case '\r': {
                            accum.append(c);
                            break;
                        }
                        default: {
                            if (c < ' ' || !Entities.canEncode(coreCharset, c, encoder)) {
                                Entities.appendEncoded(accum, escapeMode, codePoint);
                                break;
                            }
                            accum.append(c);
                            break;
                        }
                    }
                } else {
                    c = new String(Character.toChars(codePoint));
                    if (encoder.canEncode(c)) {
                        accum.append(c);
                    } else {
                        Entities.appendEncoded(accum, escapeMode, codePoint);
                    }
                }
            }
            offset += Character.charCount(codePoint);
        }
    }

    static /* synthetic */ void access$000(EscapeMode x0, String x1, int x2) {
        Entities.load(x0, x1, x2);
    }

    public static int codepointsForName(String name, int[] codepoints) {
        String val = multipoints.get(name);
        if (val != null) {
            codepoints[0] = val.codePointAt(0);
            codepoints[1] = val.codePointAt(1);
            return 2;
        }
        int codepoint = EscapeMode.extended.codepointForName(name);
        if (codepoint == -1) return 0;
        codepoints[0] = codepoint;
        return 1;
    }

    private static void appendEncoded(Appendable accum, EscapeMode escapeMode, int codePoint) throws IOException {
        String name = escapeMode.nameForCodepoint(codePoint);
        if (!"".equals(name)) {
            accum.append('&').append(name).append(';');
        } else {
            accum.append("&#x").append(Integer.toHexString(codePoint)).append(';');
        }
    }

    static {
        codeDelims = new char[]{',', ';'};
        multipoints = new HashMap();
    }

    public static String getByName(String name) {
        String val = multipoints.get(name);
        if (val != null) {
            return val;
        }
        int codepoint = EscapeMode.extended.codepointForName(name);
        if (codepoint == -1) return "";
        return new String(new int[]{codepoint}, 0, 1);
    }

    private Entities() {
    }

    static String unescape(String string, boolean strict) {
        return Parser.unescapeEntities((String)string, (boolean)strict);
    }

    public static boolean isBaseNamedEntity(String name) {
        return EscapeMode.base.codepointForName(name) != -1;
    }

    public static String escape(String string, Document.OutputSettings out) {
        if (string == null) {
            return "";
        }
        StringBuilder accum = StringUtil.borrowBuilder();
        try {
            Entities.escape(accum, string, out, false, false, false, false);
        }
        catch (IOException e) {
            throw new SerializationException((Throwable)e);
        }
        return StringUtil.releaseBuilder((StringBuilder)accum);
    }
}
