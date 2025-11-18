/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Consts
 *  org.apache.http.Header
 *  org.apache.http.HeaderElement
 *  org.apache.http.HttpEntity
 *  org.apache.http.NameValuePair
 *  org.apache.http.ParseException
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.message.BasicHeaderValueFormatter
 *  org.apache.http.message.BasicHeaderValueParser
 *  org.apache.http.message.BasicNameValuePair
 *  org.apache.http.message.ParserCursor
 *  org.apache.http.util.Args
 *  org.apache.http.util.CharArrayBuffer
 *  org.apache.http.util.TextUtils
 */
package org.apache.http.entity;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.TextUtils;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public final class ContentType
implements Serializable {
    public static final ContentType IMAGE_BMP;
    public static final ContentType IMAGE_SVG;
    private final String mimeType;
    public static final ContentType IMAGE_GIF;
    public static final ContentType DEFAULT_TEXT;
    private static final long serialVersionUID = -7768694718232371896L;
    public static final ContentType IMAGE_TIFF;
    public static final ContentType IMAGE_PNG;
    public static final ContentType APPLICATION_SOAP_XML;
    public static final ContentType IMAGE_JPEG;
    public static final ContentType TEXT_XML;
    public static final ContentType APPLICATION_XML;
    public static final ContentType APPLICATION_XHTML_XML;
    public static final ContentType APPLICATION_OCTET_STREAM;
    public static final ContentType TEXT_HTML;
    public static final ContentType MULTIPART_FORM_DATA;
    public static final ContentType APPLICATION_JSON;
    public static final ContentType APPLICATION_ATOM_XML;
    public static final ContentType TEXT_PLAIN;
    private final Charset charset;
    public static final ContentType APPLICATION_FORM_URLENCODED;
    private final NameValuePair[] params;
    public static final ContentType IMAGE_WEBP;
    public static final ContentType APPLICATION_SVG_XML;
    public static final ContentType DEFAULT_BINARY;
    public static final ContentType WILDCARD;
    private static final Map<String, ContentType> CONTENT_TYPE_MAP;

    private static ContentType create(String mimeType, NameValuePair[] params, boolean strict) {
        Charset charset = null;
        for (NameValuePair param : params) {
            if (!param.getName().equalsIgnoreCase("charset")) continue;
            String s = param.getValue();
            if (TextUtils.isBlank((CharSequence)s)) return new ContentType(mimeType, charset, params != null && params.length > 0 ? params : null);
            try {
                charset = Charset.forName(s);
                break;
            }
            catch (UnsupportedCharsetException ex) {
                if (!strict) return new ContentType(mimeType, charset, params != null && params.length > 0 ? params : null);
                throw ex;
            }
        }
        return new ContentType(mimeType, charset, params != null && params.length > 0 ? params : null);
    }

    public static ContentType getByMimeType(String mimeType) {
        if (mimeType != null) return CONTENT_TYPE_MAP.get(mimeType);
        return null;
    }

    private static ContentType create(HeaderElement helem, boolean strict) {
        return ContentType.create(helem.getName(), helem.getParameters(), strict);
    }

    public static ContentType getLenientOrDefault(HttpEntity entity) throws ParseException, UnsupportedCharsetException {
        ContentType contentType = ContentType.get(entity);
        return contentType != null ? contentType : DEFAULT_TEXT;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public static ContentType create(String mimeType, Charset charset) {
        String normalizedMimeType = ((String)Args.notBlank((CharSequence)mimeType, (String)"MIME type")).toLowerCase(Locale.ROOT);
        Args.check((boolean)ContentType.valid(normalizedMimeType), (String)"MIME type may not contain reserved characters");
        return new ContentType(normalizedMimeType, charset);
    }

    public static ContentType create(String mimeType, NameValuePair ... params) throws UnsupportedCharsetException {
        String type = ((String)Args.notBlank((CharSequence)mimeType, (String)"MIME type")).toLowerCase(Locale.ROOT);
        Args.check((boolean)ContentType.valid(type), (String)"MIME type may not contain reserved characters");
        return ContentType.create(mimeType, params, true);
    }

    public static ContentType getLenient(HttpEntity entity) {
        if (entity == null) {
            return null;
        }
        Header header = entity.getContentType();
        if (header == null) return null;
        try {
            HeaderElement[] elements = header.getElements();
            if (elements.length <= 0) return null;
            return ContentType.create(elements[0], false);
        }
        catch (ParseException ex) {
            return null;
        }
    }

    public Charset getCharset() {
        return this.charset;
    }

    ContentType(String mimeType, Charset charset, NameValuePair[] params) {
        this.mimeType = mimeType;
        this.charset = charset;
        this.params = params;
    }

    public static ContentType parse(String s) throws UnsupportedCharsetException, ParseException {
        Args.notNull((Object)s, (String)"Content type");
        CharArrayBuffer buf = new CharArrayBuffer(s.length());
        buf.append(s);
        ParserCursor cursor = new ParserCursor(0, s.length());
        HeaderElement[] elements = BasicHeaderValueParser.INSTANCE.parseElements(buf, cursor);
        if (elements.length <= 0) throw new ParseException("Invalid content type: " + s);
        return ContentType.create(elements[0], true);
    }

    public ContentType withCharset(Charset charset) {
        return ContentType.create(this.getMimeType(), charset);
    }

    public static ContentType getOrDefault(HttpEntity entity) throws UnsupportedCharsetException, ParseException {
        ContentType contentType = ContentType.get(entity);
        return contentType != null ? contentType : DEFAULT_TEXT;
    }

    ContentType(String mimeType, Charset charset) {
        this.mimeType = mimeType;
        this.charset = charset;
        this.params = null;
    }

    public static ContentType get(HttpEntity entity) throws ParseException, UnsupportedCharsetException {
        if (entity == null) {
            return null;
        }
        Header header = entity.getContentType();
        if (header == null) return null;
        HeaderElement[] elements = header.getElements();
        if (elements.length <= 0) return null;
        return ContentType.create(elements[0], true);
    }

    public ContentType withParameters(NameValuePair ... params) throws UnsupportedCharsetException {
        if (params.length == 0) {
            return this;
        }
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<String, String>();
        if (this.params != null) {
            for (NameValuePair param : this.params) {
                paramMap.put(param.getName(), param.getValue());
            }
        }
        for (NameValuePair param : params) {
            paramMap.put(param.getName(), param.getValue());
        }
        ArrayList<BasicNameValuePair> newParams = new ArrayList<BasicNameValuePair>(paramMap.size() + 1);
        if (this.charset != null && !paramMap.containsKey("charset")) {
            newParams.add(new BasicNameValuePair("charset", this.charset.name()));
        }
        Iterator i$ = paramMap.entrySet().iterator();
        while (i$.hasNext()) {
            Map.Entry entry = i$.next();
            newParams.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
        }
        return ContentType.create(this.getMimeType(), newParams.toArray(new NameValuePair[newParams.size()]), true);
    }

    public static ContentType create(String mimeType, String charset) throws UnsupportedCharsetException {
        return ContentType.create(mimeType, !TextUtils.isBlank((CharSequence)charset) ? Charset.forName(charset) : null);
    }

    public String getParameter(String name) {
        Args.notEmpty((CharSequence)name, (String)"Parameter name");
        if (this.params == null) {
            return null;
        }
        NameValuePair[] arr$ = this.params;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            NameValuePair param = arr$[i$];
            if (param.getName().equalsIgnoreCase(name)) {
                return param.getValue();
            }
            ++i$;
        }
        return null;
    }

    private static boolean valid(String s) {
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch == '\"') return false;
            if (ch == ',') return false;
            if (ch == ';') {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static ContentType create(String mimeType) {
        return ContentType.create(mimeType, (Charset)null);
    }

    public ContentType withCharset(String charset) {
        return ContentType.create(this.getMimeType(), charset);
    }

    static {
        APPLICATION_ATOM_XML = ContentType.create("application/atom+xml", Consts.ISO_8859_1);
        APPLICATION_FORM_URLENCODED = ContentType.create("application/x-www-form-urlencoded", Consts.ISO_8859_1);
        APPLICATION_JSON = ContentType.create("application/json", Consts.UTF_8);
        APPLICATION_OCTET_STREAM = ContentType.create("application/octet-stream", (Charset)null);
        APPLICATION_SOAP_XML = ContentType.create("application/soap+xml", Consts.UTF_8);
        APPLICATION_SVG_XML = ContentType.create("application/svg+xml", Consts.ISO_8859_1);
        APPLICATION_XHTML_XML = ContentType.create("application/xhtml+xml", Consts.ISO_8859_1);
        APPLICATION_XML = ContentType.create("application/xml", Consts.ISO_8859_1);
        IMAGE_BMP = ContentType.create("image/bmp");
        IMAGE_GIF = ContentType.create("image/gif");
        IMAGE_JPEG = ContentType.create("image/jpeg");
        IMAGE_PNG = ContentType.create("image/png");
        IMAGE_SVG = ContentType.create("image/svg+xml");
        IMAGE_TIFF = ContentType.create("image/tiff");
        IMAGE_WEBP = ContentType.create("image/webp");
        MULTIPART_FORM_DATA = ContentType.create("multipart/form-data", Consts.ISO_8859_1);
        TEXT_HTML = ContentType.create("text/html", Consts.ISO_8859_1);
        TEXT_PLAIN = ContentType.create("text/plain", Consts.ISO_8859_1);
        TEXT_XML = ContentType.create("text/xml", Consts.ISO_8859_1);
        WILDCARD = ContentType.create("*/*", (Charset)null);
        ContentType[] contentTypes = new ContentType[]{APPLICATION_ATOM_XML, APPLICATION_FORM_URLENCODED, APPLICATION_JSON, APPLICATION_SVG_XML, APPLICATION_XHTML_XML, APPLICATION_XML, IMAGE_BMP, IMAGE_GIF, IMAGE_JPEG, IMAGE_PNG, IMAGE_SVG, IMAGE_TIFF, IMAGE_WEBP, MULTIPART_FORM_DATA, TEXT_HTML, TEXT_PLAIN, TEXT_XML};
        HashMap<String, ContentType> map = new HashMap<String, ContentType>();
        ContentType[] arr$ = contentTypes;
        int len$ = arr$.length;
        int i$ = 0;
        while (true) {
            if (i$ >= len$) {
                CONTENT_TYPE_MAP = Collections.unmodifiableMap(map);
                DEFAULT_TEXT = TEXT_PLAIN;
                DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
                return;
            }
            ContentType contentType = arr$[i$];
            map.put(contentType.getMimeType(), contentType);
            ++i$;
        }
    }

    public String toString() {
        CharArrayBuffer buf = new CharArrayBuffer(64);
        buf.append(this.mimeType);
        if (this.params != null) {
            buf.append("; ");
            BasicHeaderValueFormatter.INSTANCE.formatParameters(buf, this.params, false);
        } else {
            if (this.charset == null) return buf.toString();
            buf.append("; charset=");
            buf.append(this.charset.name());
        }
        return buf.toString();
    }
}
