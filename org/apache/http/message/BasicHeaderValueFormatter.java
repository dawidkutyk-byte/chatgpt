/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HeaderElement
 *  org.apache.http.NameValuePair
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.message.HeaderValueFormatter
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 */
package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.message.HeaderValueFormatter;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class BasicHeaderValueFormatter
implements HeaderValueFormatter {
    @Deprecated
    public static final BasicHeaderValueFormatter DEFAULT = new BasicHeaderValueFormatter();
    public static final String UNSAFE_CHARS = "\"\\";
    public static final BasicHeaderValueFormatter INSTANCE = new BasicHeaderValueFormatter();
    public static final String SEPARATORS = " ;,:@()<>\\\"/[]?={}\t";

    public static String formatNameValuePair(NameValuePair nvp, boolean quote, HeaderValueFormatter formatter) {
        return (formatter != null ? formatter : INSTANCE).formatNameValuePair(null, nvp, quote).toString();
    }

    protected int estimateElementsLen(HeaderElement[] elems) {
        if (elems == null) return 0;
        if (elems.length < 1) {
            return 0;
        }
        int result = (elems.length - 1) * 2;
        HeaderElement[] arr$ = elems;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            HeaderElement elem = arr$[i$];
            result += this.estimateHeaderElementLen(elem);
            ++i$;
        }
        return result;
    }

    public CharArrayBuffer formatElements(CharArrayBuffer charBuffer, HeaderElement[] elems, boolean quote) {
        Args.notNull((Object)elems, (String)"Header element array");
        int len = this.estimateElementsLen(elems);
        CharArrayBuffer buffer = charBuffer;
        if (buffer == null) {
            buffer = new CharArrayBuffer(len);
        } else {
            buffer.ensureCapacity(len);
        }
        int i = 0;
        while (i < elems.length) {
            if (i > 0) {
                buffer.append(", ");
            }
            this.formatHeaderElement(buffer, elems[i], quote);
            ++i;
        }
        return buffer;
    }

    protected void doFormatValue(CharArrayBuffer buffer, String value, boolean quote) {
        int i;
        boolean quoteFlag = quote;
        if (!quoteFlag) {
            for (i = 0; i < value.length() && !quoteFlag; ++i) {
                quoteFlag = this.isSeparator(value.charAt(i));
            }
        }
        if (quoteFlag) {
            buffer.append('\"');
        }
        i = 0;
        while (true) {
            if (i >= value.length()) {
                if (!quoteFlag) return;
                buffer.append('\"');
                return;
            }
            char ch = value.charAt(i);
            if (this.isUnsafe(ch)) {
                buffer.append('\\');
            }
            buffer.append(ch);
            ++i;
        }
    }

    protected boolean isUnsafe(char ch) {
        return UNSAFE_CHARS.indexOf(ch) >= 0;
    }

    protected int estimateParametersLen(NameValuePair[] nvps) {
        if (nvps == null) return 0;
        if (nvps.length < 1) {
            return 0;
        }
        int result = (nvps.length - 1) * 2;
        NameValuePair[] arr$ = nvps;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            NameValuePair nvp = arr$[i$];
            result += this.estimateNameValuePairLen(nvp);
            ++i$;
        }
        return result;
    }

    protected int estimateNameValuePairLen(NameValuePair nvp) {
        if (nvp == null) {
            return 0;
        }
        int result = nvp.getName().length();
        String value = nvp.getValue();
        if (value == null) return result;
        result += 3 + value.length();
        return result;
    }

    public CharArrayBuffer formatParameters(CharArrayBuffer charBuffer, NameValuePair[] nvps, boolean quote) {
        Args.notNull((Object)nvps, (String)"Header parameter array");
        int len = this.estimateParametersLen(nvps);
        CharArrayBuffer buffer = charBuffer;
        if (buffer == null) {
            buffer = new CharArrayBuffer(len);
        } else {
            buffer.ensureCapacity(len);
        }
        int i = 0;
        while (i < nvps.length) {
            if (i > 0) {
                buffer.append("; ");
            }
            this.formatNameValuePair(buffer, nvps[i], quote);
            ++i;
        }
        return buffer;
    }

    public static String formatParameters(NameValuePair[] nvps, boolean quote, HeaderValueFormatter formatter) {
        return (formatter != null ? formatter : INSTANCE).formatParameters(null, nvps, quote).toString();
    }

    protected boolean isSeparator(char ch) {
        return SEPARATORS.indexOf(ch) >= 0;
    }

    public CharArrayBuffer formatHeaderElement(CharArrayBuffer charBuffer, HeaderElement elem, boolean quote) {
        int parcnt;
        Args.notNull((Object)elem, (String)"Header element");
        int len = this.estimateHeaderElementLen(elem);
        CharArrayBuffer buffer = charBuffer;
        if (buffer == null) {
            buffer = new CharArrayBuffer(len);
        } else {
            buffer.ensureCapacity(len);
        }
        buffer.append(elem.getName());
        String value = elem.getValue();
        if (value != null) {
            buffer.append('=');
            this.doFormatValue(buffer, value, quote);
        }
        if ((parcnt = elem.getParameterCount()) <= 0) return buffer;
        int i = 0;
        while (i < parcnt) {
            buffer.append("; ");
            this.formatNameValuePair(buffer, elem.getParameter(i), quote);
            ++i;
        }
        return buffer;
    }

    public static String formatElements(HeaderElement[] elems, boolean quote, HeaderValueFormatter formatter) {
        return (formatter != null ? formatter : INSTANCE).formatElements(null, elems, quote).toString();
    }

    public static String formatHeaderElement(HeaderElement elem, boolean quote, HeaderValueFormatter formatter) {
        return (formatter != null ? formatter : INSTANCE).formatHeaderElement(null, elem, quote).toString();
    }

    public CharArrayBuffer formatNameValuePair(CharArrayBuffer charBuffer, NameValuePair nvp, boolean quote) {
        Args.notNull((Object)nvp, (String)"Name / value pair");
        int len = this.estimateNameValuePairLen(nvp);
        CharArrayBuffer buffer = charBuffer;
        if (buffer == null) {
            buffer = new CharArrayBuffer(len);
        } else {
            buffer.ensureCapacity(len);
        }
        buffer.append(nvp.getName());
        String value = nvp.getValue();
        if (value == null) return buffer;
        buffer.append('=');
        this.doFormatValue(buffer, value, quote);
        return buffer;
    }

    protected int estimateHeaderElementLen(HeaderElement elem) {
        int parcnt;
        if (elem == null) {
            return 0;
        }
        int result = elem.getName().length();
        String value = elem.getValue();
        if (value != null) {
            result += 3 + value.length();
        }
        if ((parcnt = elem.getParameterCount()) <= 0) return result;
        int i = 0;
        while (i < parcnt) {
            result += 2 + this.estimateNameValuePairLen(elem.getParameter(i));
            ++i;
        }
        return result;
    }
}
