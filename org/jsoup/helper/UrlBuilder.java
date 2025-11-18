/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection$KeyVal
 *  org.jsoup.helper.DataUtil
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.StringUtil
 */
package org.jsoup.helper;

import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.jsoup.Connection;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;

final class UrlBuilder {
    @7\u015aCz StringBuilder q;
    URL u;

    UrlBuilder(URL inputUrl) {
        this.u = inputUrl;
        if (this.u.getQuery() == null) return;
        this.q = StringUtil.borrowBuilder().append(this.u.getQuery());
    }

    private static void appendToAscii(String s, boolean spaceAsPlus, StringBuilder sb) throws UnsupportedEncodingException {
        int i = 0;
        while (i < s.length()) {
            int c = s.codePointAt(i);
            if (c == 32) {
                sb.append(spaceAsPlus ? Character.valueOf('+') : "%20");
            } else if (c > 127) {
                sb.append(URLEncoder.encode(new String(Character.toChars(c)), DataUtil.UTF_8.name()));
                if (Character.charCount(c) == 2) {
                    ++i;
                }
            } else {
                sb.append((char)c);
            }
            ++i;
        }
    }

    private static String decodePart(String encoded) {
        try {
            return URLDecoder.decode(encoded, DataUtil.UTF_8.name());
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    URL build() {
        try {
            URI uri = new URI(this.u.getProtocol(), this.u.getUserInfo(), IDN.toASCII(UrlBuilder.decodePart(this.u.getHost())), this.u.getPort(), null, null, null);
            StringBuilder normUrl = StringUtil.borrowBuilder().append(uri.toASCIIString());
            UrlBuilder.appendToAscii(this.u.getPath(), false, normUrl);
            if (this.q != null) {
                normUrl.append('?');
                UrlBuilder.appendToAscii(StringUtil.releaseBuilder((StringBuilder)this.q), true, normUrl);
            }
            if (this.u.getRef() != null) {
                normUrl.append('#');
                UrlBuilder.appendToAscii(this.u.getRef(), false, normUrl);
            }
            this.u = new URL(StringUtil.releaseBuilder((StringBuilder)normUrl));
            return this.u;
        }
        catch (UnsupportedEncodingException | MalformedURLException | URISyntaxException e) {
            if ($assertionsDisabled) return this.u;
            if (Validate.assertFail((String)e.toString())) return this.u;
            throw new AssertionError();
        }
    }

    void appendKeyVal(Connection.KeyVal kv) throws UnsupportedEncodingException {
        if (this.q == null) {
            this.q = StringUtil.borrowBuilder();
        } else {
            this.q.append('&');
        }
        this.q.append(URLEncoder.encode(kv.key(), DataUtil.UTF_8.name())).append('=').append(URLEncoder.encode(kv.value(), DataUtil.UTF_8.name()));
    }
}
