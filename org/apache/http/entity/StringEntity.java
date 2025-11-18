/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.entity.AbstractHttpEntity
 *  org.apache.http.entity.ContentType
 *  org.apache.http.protocol.HTTP
 *  org.apache.http.util.Args
 */
package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;

public class StringEntity
extends AbstractHttpEntity
implements Cloneable {
    protected final byte[] content;

    @Deprecated
    public StringEntity(String string, String mimeType, String charset) throws UnsupportedEncodingException {
        Args.notNull((Object)string, (String)"Source string");
        String mt = mimeType != null ? mimeType : "text/plain";
        String cs = charset != null ? charset : "ISO-8859-1";
        this.content = string.getBytes(cs);
        this.setContentType(mt + "; charset=" + cs);
    }

    public InputStream getContent() throws IOException {
        return new ByteArrayInputStream(this.content);
    }

    public StringEntity(String string, Charset charset) {
        this(string, ContentType.create((String)ContentType.TEXT_PLAIN.getMimeType(), (Charset)charset));
    }

    public boolean isStreaming() {
        return false;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public StringEntity(String string) throws UnsupportedEncodingException {
        this(string, ContentType.DEFAULT_TEXT);
    }

    public boolean isRepeatable() {
        return true;
    }

    public long getContentLength() {
        return this.content.length;
    }

    public StringEntity(String string, ContentType contentType) throws UnsupportedCharsetException {
        Charset charset;
        Args.notNull((Object)string, (String)"Source string");
        Charset charset2 = charset = contentType != null ? contentType.getCharset() : null;
        if (charset == null) {
            charset = HTTP.DEF_CONTENT_CHARSET;
        }
        this.content = string.getBytes(charset);
        if (contentType == null) return;
        this.setContentType(contentType.toString());
    }

    public StringEntity(String string, String charset) throws UnsupportedCharsetException {
        this(string, ContentType.create((String)ContentType.TEXT_PLAIN.getMimeType(), (String)charset));
    }

    public void writeTo(OutputStream outStream) throws IOException {
        Args.notNull((Object)outStream, (String)"Output stream");
        outStream.write(this.content);
        outStream.flush();
    }
}
