/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil$StringJoiner
 */
package org.jsoup.internal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;

public final class StringUtil {
    static final String[] padding = new String[]{"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ", "           ", "            ", "             ", "              ", "               ", "                ", "                 ", "                  ", "                   ", "                    "};
    private static final Pattern validUriScheme;
    private static final int MaxCachedBuilderSize = 8192;
    private static final ThreadLocal<Stack<StringBuilder>> threadLocalBuilders;
    private static final Pattern controlChars;
    private static final int MaxIdleBuilders = 8;
    private static final Pattern extraDotSegmentsPattern;

    public static boolean isInvisibleChar(int c) {
        return c == 8203 || c == 173;
    }

    public static boolean isNumeric(String string) {
        if (string == null) return false;
        if (string.length() == 0) {
            return false;
        }
        int l = string.length();
        int i = 0;
        while (i < l) {
            if (!Character.isDigit(string.codePointAt(i))) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static StringBuilder borrowBuilder() {
        Stack<StringBuilder> builders = threadLocalBuilders.get();
        return builders.empty() ? new StringBuilder(8192) : builders.pop();
    }

    public static String padding(int width) {
        return StringUtil.padding(width, 30);
    }

    public static URL resolve(URL base, String relUrl) throws MalformedURLException {
        if ((relUrl = StringUtil.stripControlChars(relUrl)).startsWith("?")) {
            relUrl = base.getPath() + relUrl;
        }
        URL url = new URL(base, relUrl);
        String fixedFile = extraDotSegmentsPattern.matcher(url.getFile()).replaceFirst("/");
        if (url.getRef() == null) return new URL(url.getProtocol(), url.getHost(), url.getPort(), fixedFile);
        fixedFile = fixedFile + "#" + url.getRef();
        return new URL(url.getProtocol(), url.getHost(), url.getPort(), fixedFile);
    }

    private static String stripControlChars(String input) {
        return controlChars.matcher(input).replaceAll("");
    }

    public static String join(Iterator<?> strings, String sep) {
        if (!strings.hasNext()) {
            return "";
        }
        String start = strings.next().toString();
        if (!strings.hasNext()) {
            return start;
        }
        StringJoiner j = new StringJoiner(sep);
        j.add((Object)start);
        while (strings.hasNext()) {
            j.add(strings.next());
        }
        return j.complete();
    }

    public static boolean isWhitespace(int c) {
        return c == 32 || c == 9 || c == 10 || c == 12 || c == 13;
    }

    public static boolean isBlank(String string) {
        if (string == null) return true;
        if (string.length() == 0) {
            return true;
        }
        int l = string.length();
        int i = 0;
        while (i < l) {
            if (!StringUtil.isWhitespace(string.codePointAt(i))) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static String resolve(String baseUrl, String relUrl) {
        baseUrl = StringUtil.stripControlChars(baseUrl);
        relUrl = StringUtil.stripControlChars(relUrl);
        try {
            URL base;
            try {
                base = new URL(baseUrl);
            }
            catch (MalformedURLException e) {
                URL abs = new URL(relUrl);
                return abs.toExternalForm();
            }
            return StringUtil.resolve(base, relUrl).toExternalForm();
        }
        catch (MalformedURLException e) {
            return validUriScheme.matcher(relUrl).find() ? relUrl : "";
        }
    }

    public static boolean inSorted(String needle, String[] haystack) {
        return Arrays.binarySearch(haystack, needle) >= 0;
    }

    public static String releaseBuilder(StringBuilder sb) {
        Validate.notNull((Object)sb);
        String string = sb.toString();
        if (sb.length() > 8192) {
            sb = new StringBuilder(8192);
        } else {
            sb.delete(0, sb.length());
        }
        Stack<StringBuilder> builders = threadLocalBuilders.get();
        builders.push(sb);
        while (builders.size() > 8) {
            builders.pop();
        }
        return string;
    }

    public static String join(String[] strings, String sep) {
        return StringUtil.join(Arrays.asList(strings), sep);
    }

    public static boolean in(String needle, String ... haystack) {
        int len = haystack.length;
        int i = 0;
        while (i < len) {
            if (haystack[i].equals(needle)) {
                return true;
            }
            ++i;
        }
        return false;
    }

    public static boolean startsWithNewline(String string) {
        if (string == null) return false;
        if (string.length() != 0) return string.charAt(0) == '\n';
        return false;
    }

    public static boolean isActuallyWhitespace(int c) {
        return c == 32 || c == 9 || c == 10 || c == 12 || c == 13 || c == 160;
    }

    public static boolean isAscii(String string) {
        Validate.notNull((Object)string);
        int i = 0;
        while (i < string.length()) {
            char c = string.charAt(i);
            if (c > '\u007f') {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static String normaliseWhitespace(String string) {
        StringBuilder sb = StringUtil.borrowBuilder();
        StringUtil.appendNormalisedWhitespace(sb, string, false);
        return StringUtil.releaseBuilder(sb);
    }

    public static void appendNormalisedWhitespace(StringBuilder accum, String string, boolean stripLeading) {
        boolean lastWasWhite = false;
        boolean reachedNonWhite = false;
        int len = string.length();
        int i = 0;
        while (i < len) {
            int c = string.codePointAt(i);
            if (StringUtil.isActuallyWhitespace(c)) {
                if (!(stripLeading && !reachedNonWhite || lastWasWhite)) {
                    accum.append(' ');
                    lastWasWhite = true;
                }
            } else if (!StringUtil.isInvisibleChar(c)) {
                accum.appendCodePoint(c);
                lastWasWhite = false;
                reachedNonWhite = true;
            }
            i += Character.charCount(c);
        }
    }

    public static String padding(int width, int maxPaddingWidth) {
        Validate.isTrue((width >= 0 ? 1 : 0) != 0, (String)"width must be >= 0");
        Validate.isTrue((maxPaddingWidth >= -1 ? 1 : 0) != 0);
        if (maxPaddingWidth != -1) {
            width = Math.min(width, maxPaddingWidth);
        }
        if (width < padding.length) {
            return padding[width];
        }
        char[] out = new char[width];
        int i = 0;
        while (i < width) {
            out[i] = 32;
            ++i;
        }
        return String.valueOf(out);
    }

    static {
        extraDotSegmentsPattern = Pattern.compile("^/((\\.{1,2}/)+)");
        validUriScheme = Pattern.compile("^[a-zA-Z][a-zA-Z0-9+-.]*:");
        controlChars = Pattern.compile("[\\x00-\\x1f]*");
        threadLocalBuilders = ThreadLocal.withInitial(Stack::new);
    }

    public static String join(Collection<?> strings, String sep) {
        return StringUtil.join(strings.iterator(), sep);
    }
}
