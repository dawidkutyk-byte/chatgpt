/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection
 *  org.jsoup.Connection$KeyVal
 *  org.jsoup.Connection$Method
 *  org.jsoup.Connection$Request
 *  org.jsoup.Connection$Response
 *  org.jsoup.HttpStatusException
 *  org.jsoup.UncheckedIOException
 *  org.jsoup.UnsupportedMimeTypeException
 *  org.jsoup.helper.AuthenticationHandler
 *  org.jsoup.helper.CookieUtil
 *  org.jsoup.helper.DataUtil
 *  org.jsoup.helper.HttpConnection
 *  org.jsoup.helper.HttpConnection$Base
 *  org.jsoup.helper.HttpConnection$Request
 *  org.jsoup.helper.UrlBuilder
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.ControllableInputStream
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Document
 *  org.jsoup.parser.Parser
 *  org.jsoup.parser.TokenQueue
 */
package org.jsoup.helper;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.UncheckedIOException;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.helper.AuthenticationHandler;
import org.jsoup.helper.CookieUtil;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.UrlBuilder;
import org.jsoup.helper.Validate;
import org.jsoup.internal.ControllableInputStream;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TokenQueue;

/*
 * Exception performing whole class analysis ignored.
 */
public static class HttpConnection.Response
extends HttpConnection.Base<Connection.Response>
implements Connection.Response {
    private @7\u015aCz ByteBuffer byteData;
    private @7\u015aCz ControllableInputStream bodyStream;
    private final HttpConnection.Request req;
    private static final String LOCATION = "Location";
    private @7\u015aCz String charset;
    private static final Pattern xmlContentTypeRxp = Pattern.compile("(\\w+)/\\w*\\+?xml.*");
    private final String statusMessage;
    private @7\u015aCz HttpURLConnection conn;
    private boolean executed = false;
    private final @7\u015aCz String contentType;
    private final int statusCode;
    private int numRedirects = 0;
    private boolean inputStreamRead = false;
    private static final int MAX_REDIRECTS = 20;

    private void prepareByteData() {
        Validate.isTrue((boolean)this.executed, (String)"Request must be executed (with .execute(), .get(), or .post() before getting response body");
        if (this.bodyStream == null) return;
        if (this.byteData != null) return;
        Validate.isFalse((boolean)this.inputStreamRead, (String)"Request has already been read (with .parse())");
        try {
            this.byteData = DataUtil.readToByteBuffer((InputStream)this.bodyStream, (int)this.req.maxBodySize());
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        finally {
            this.inputStreamRead = true;
            this.safeClose();
        }
    }

    public Connection.Response bufferUp() {
        this.prepareByteData();
        return this;
    }

    static HttpConnection.Response execute(HttpConnection.Request req) throws IOException {
        return HttpConnection.Response.execute(req, null);
    }

    public Document parse() throws IOException {
        Validate.isTrue((boolean)this.executed, (String)"Request must be executed (with .execute(), .get(), or .post() before parsing response");
        Object stream = this.bodyStream;
        if (this.byteData != null) {
            stream = new ByteArrayInputStream(this.byteData.array());
            this.inputStreamRead = false;
        }
        Validate.isFalse((boolean)this.inputStreamRead, (String)"Input stream already read and parsed, cannot re-read.");
        Document doc = DataUtil.parseInputStream((InputStream)stream, (String)this.charset, (String)this.url.toExternalForm(), (Parser)this.req.parser());
        doc.connection((Connection)new HttpConnection(this.req, this, null));
        this.charset = doc.outputSettings().charset().name();
        this.inputStreamRead = true;
        this.safeClose();
        return doc;
    }

    private HttpConnection.Response(HttpURLConnection conn, HttpConnection.Request request, @7\u015aCz HttpConnection.Response previousResponse) throws IOException {
        super(null);
        this.conn = conn;
        this.req = request;
        this.method = Connection.Method.valueOf((String)conn.getRequestMethod());
        this.url = conn.getURL();
        this.statusCode = conn.getResponseCode();
        this.statusMessage = conn.getResponseMessage();
        this.contentType = conn.getContentType();
        LinkedHashMap<String, List<String>> resHeaders = HttpConnection.Response.createHeaderMap(conn);
        this.processResponseHeaders(resHeaders);
        CookieUtil.storeCookies((HttpConnection.Request)this.req, (URL)this.url, resHeaders);
        if (previousResponse == null) return;
        Iterator iterator = previousResponse.cookies().entrySet().iterator();
        while (true) {
            if (!iterator.hasNext()) {
                previousResponse.safeClose();
                this.numRedirects = previousResponse.numRedirects + 1;
                if (this.numRedirects < 20) return;
                throw new IOException(String.format("Too many redirects occurred trying to load URL %s", previousResponse.url()));
            }
            Map.Entry prevCookie = iterator.next();
            if (this.hasCookie((String)prevCookie.getKey())) continue;
            this.cookie((String)prevCookie.getKey(), (String)prevCookie.getValue());
        }
    }

    public String body() {
        this.prepareByteData();
        Validate.notNull((Object)this.byteData);
        String body = (this.charset == null ? DataUtil.UTF_8 : Charset.forName(this.charset)).decode(this.byteData).toString();
        ((Buffer)this.byteData).rewind();
        return body;
    }

    public String statusMessage() {
        return this.statusMessage;
    }

    public String charset() {
        return this.charset;
    }

    /*
     * Unable to fully structure code
     */
    void processResponseHeaders(Map<String, List<String>> resHeaders) {
        var2_2 = resHeaders.entrySet().iterator();
        block0: while (true) {
            if (var2_2.hasNext() == false) return;
            entry = var2_2.next();
            name = entry.getKey();
            if (name == null) continue;
            values = entry.getValue();
            if (name.equalsIgnoreCase("Set-Cookie")) {
                for (String value : values) {
                    if (value == null) continue;
                    cd = new TokenQueue(value);
                    cookieName = cd.chompTo("=").trim();
                    cookieVal = cd.consumeTo(";").trim();
                    if (cookieName.length() <= 0 || this.cookies.containsKey(cookieName)) continue;
                    this.cookie(cookieName, cookieVal);
                }
            }
            var6_6 = values.iterator();
            while (true) {
                if (var6_6.hasNext()) ** break;
                continue block0;
                value = var6_6.next();
                this.addHeader(name, HttpConnection.Response.fixHeaderEncoding(value));
            }
            break;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static HttpConnection.Response execute(HttpConnection.Request req, @7\u015aCz HttpConnection.Response previousResponse) throws IOException {
        boolean hasRequestBody;
        HttpConnection.Request request = req;
        synchronized (request) {
            Validate.isFalse((boolean)HttpConnection.Request.access$300((HttpConnection.Request)req), (String)"Multiple threads were detected trying to execute the same request concurrently. Make sure to use Connection#newRequest() and do not share an executing request between threads.");
            HttpConnection.Request.access$302((HttpConnection.Request)req, (boolean)true);
        }
        Validate.notNullParam((Object)req, (String)"req");
        URL url = req.url();
        Validate.notNull((Object)url, (String)"URL must be specified to connect");
        String protocol = url.getProtocol();
        if (!protocol.equals("http") && !protocol.equals("https")) {
            throw new MalformedURLException("Only http & https protocols supported");
        }
        boolean methodHasBody = req.method().hasBody();
        boolean bl = hasRequestBody = req.requestBody() != null;
        if (!methodHasBody) {
            Validate.isFalse((boolean)hasRequestBody, (String)("Cannot set a request body for HTTP method " + req.method()));
        }
        String mimeBoundary = null;
        if (req.data().size() > 0 && (!methodHasBody || hasRequestBody)) {
            HttpConnection.Response.serialiseRequestUrl((Connection.Request)req);
        } else if (methodHasBody) {
            mimeBoundary = HttpConnection.Response.setOutputContentType((Connection.Request)req);
        }
        long startTime = System.nanoTime();
        HttpURLConnection conn = HttpConnection.Response.createConnection(req);
        HttpConnection.Response res = null;
        try {
            conn.connect();
            if (conn.getDoOutput()) {
                try (OutputStream out = conn.getOutputStream();){
                    HttpConnection.Response.writePost((Connection.Request)req, out, mimeBoundary);
                }
            }
            int status = conn.getResponseCode();
            res = new HttpConnection.Response(conn, req, previousResponse);
            if (res.hasHeader("Location") && req.followRedirects()) {
                if (status != 307) {
                    req.method(Connection.Method.GET);
                    req.data().clear();
                    req.requestBody(null);
                    req.removeHeader("Content-Type");
                }
                String location = res.header("Location");
                Validate.notNull((Object)location);
                if (location.startsWith("http:/") && location.charAt(6) != '/') {
                    location = location.substring(6);
                }
                URL redir = StringUtil.resolve((URL)req.url(), (String)location);
                req.url(redir);
                HttpConnection.Request.access$302((HttpConnection.Request)req, (boolean)false);
                HttpConnection.Response response = HttpConnection.Response.execute(req, res);
                return response;
            }
            if (!(status >= 200 && status < 400 || req.ignoreHttpErrors())) {
                throw new HttpStatusException("HTTP error fetching URL", status, req.url().toString());
            }
            String contentType = res.contentType();
            if (!(contentType == null || req.ignoreContentType() || contentType.startsWith("text/") || xmlContentTypeRxp.matcher(contentType).matches())) {
                throw new UnsupportedMimeTypeException("Unhandled content type. Must be text/*, */xml, or */*+xml", contentType, req.url().toString());
            }
            if (contentType != null && xmlContentTypeRxp.matcher(contentType).matches() && !HttpConnection.Request.access$400((HttpConnection.Request)req)) {
                req.parser(Parser.xmlParser());
            }
            res.charset = DataUtil.getCharsetFromContentType((String)res.contentType);
            if (conn.getContentLength() != 0 && req.method() != Connection.Method.HEAD) {
                InputStream stream;
                InputStream inputStream = stream = conn.getErrorStream() != null ? conn.getErrorStream() : conn.getInputStream();
                if (res.hasHeaderWithValue("Content-Encoding", "gzip")) {
                    stream = new GZIPInputStream(stream);
                } else if (res.hasHeaderWithValue("Content-Encoding", "deflate")) {
                    stream = new InflaterInputStream(stream, new Inflater(true));
                }
                res.bodyStream = ControllableInputStream.wrap((InputStream)stream, (int)32768, (int)req.maxBodySize()).timeout(startTime, (long)req.timeout());
            } else {
                res.byteData = DataUtil.emptyByteBuffer();
            }
        }
        catch (IOException e) {
            if (res == null) throw e;
            super.safeClose();
            throw e;
        }
        finally {
            HttpConnection.Request.access$302((HttpConnection.Request)req, (boolean)false);
            if (HttpConnection.Request.access$500((HttpConnection.Request)req) != null) {
                AuthenticationHandler.handler.remove();
            }
        }
        res.executed = true;
        return res;
    }

    private static @7\u015aCz String setOutputContentType(Connection.Request req) {
        String contentType = req.header("Content-Type");
        String bound = null;
        if (contentType != null) {
            if (!contentType.contains("multipart/form-data")) return bound;
            if (contentType.contains("boundary")) return bound;
            bound = DataUtil.mimeBoundary();
            req.header("Content-Type", "multipart/form-data; boundary=" + bound);
        } else if (HttpConnection.access$800((Connection.Request)req)) {
            bound = DataUtil.mimeBoundary();
            req.header("Content-Type", "multipart/form-data; boundary=" + bound);
        } else {
            req.header("Content-Type", "application/x-www-form-urlencoded; charset=" + req.postDataCharset());
        }
        return bound;
    }

    public byte[] bodyAsBytes() {
        this.prepareByteData();
        Validate.notNull((Object)this.byteData);
        return this.byteData.array();
    }

    private void safeClose() {
        if (this.bodyStream != null) {
            try {
                this.bodyStream.close();
            }
            catch (IOException iOException) {
            }
            finally {
                this.bodyStream = null;
            }
        }
        if (this.conn == null) return;
        this.conn.disconnect();
        this.conn = null;
    }

    private static void serialiseRequestUrl(Connection.Request req) throws IOException {
        UrlBuilder in = new UrlBuilder(req.url());
        Iterator iterator = req.data().iterator();
        while (true) {
            if (!iterator.hasNext()) {
                req.url(in.build());
                req.data().clear();
                return;
            }
            Connection.KeyVal keyVal = (Connection.KeyVal)iterator.next();
            Validate.isFalse((boolean)keyVal.hasInputStream(), (String)"InputStream data not supported in URL query string.");
            in.appendKeyVal(keyVal);
        }
    }

    private static void writePost(Connection.Request req, OutputStream outputStream, @7\u015aCz String boundary) throws IOException {
        BufferedWriter w;
        block9: {
            Iterator iterator;
            boolean first;
            block10: {
                Iterator iterator2;
                block7: {
                    Collection data;
                    block8: {
                        block6: {
                            data = req.data();
                            w = new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName(req.postDataCharset())));
                            if (boundary == null) break block6;
                            iterator2 = data.iterator();
                            break block7;
                        }
                        String body = req.requestBody();
                        if (body == null) break block8;
                        w.write(body);
                        break block9;
                    }
                    first = true;
                    iterator = data.iterator();
                    break block10;
                }
                while (iterator2.hasNext()) {
                    Connection.KeyVal keyVal = (Connection.KeyVal)iterator2.next();
                    w.write("--");
                    w.write(boundary);
                    w.write("\r\n");
                    w.write("Content-Disposition: form-data; name=\"");
                    w.write(HttpConnection.access$900((String)keyVal.key()));
                    w.write("\"");
                    InputStream input = keyVal.inputStream();
                    if (input != null) {
                        w.write("; filename=\"");
                        w.write(HttpConnection.access$900((String)keyVal.value()));
                        w.write("\"\r\nContent-Type: ");
                        String contentType = keyVal.contentType();
                        w.write(contentType != null ? contentType : "application/octet-stream");
                        w.write("\r\n\r\n");
                        w.flush();
                        DataUtil.crossStreams((InputStream)input, (OutputStream)outputStream);
                        outputStream.flush();
                    } else {
                        w.write("\r\n\r\n");
                        w.write(keyVal.value());
                    }
                    w.write("\r\n");
                }
                w.write("--");
                w.write(boundary);
                w.write("--");
                break block9;
            }
            while (iterator.hasNext()) {
                Connection.KeyVal keyVal = (Connection.KeyVal)iterator.next();
                if (!first) {
                    w.append('&');
                } else {
                    first = false;
                }
                w.write(URLEncoder.encode(keyVal.key(), req.postDataCharset()));
                w.write(61);
                w.write(URLEncoder.encode(keyVal.value(), req.postDataCharset()));
            }
        }
        w.close();
    }

    HttpConnection.Response() {
        super(null);
        this.statusCode = 400;
        this.statusMessage = "Request not made";
        this.req = new HttpConnection.Request();
        this.contentType = null;
    }

    private static HttpURLConnection createConnection(HttpConnection.Request req) throws IOException {
        Proxy proxy = req.proxy();
        HttpURLConnection conn = (HttpURLConnection)(proxy == null ? req.url().openConnection() : req.url().openConnection(proxy));
        conn.setRequestMethod(req.method().name());
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(req.timeout());
        conn.setReadTimeout(req.timeout() / 2);
        if (req.sslSocketFactory() != null && conn instanceof HttpsURLConnection) {
            ((HttpsURLConnection)conn).setSSLSocketFactory(req.sslSocketFactory());
        }
        if (HttpConnection.Request.access$500((HttpConnection.Request)req) != null) {
            AuthenticationHandler.handler.enable(HttpConnection.Request.access$500((HttpConnection.Request)req), conn);
        }
        if (req.method().hasBody()) {
            conn.setDoOutput(true);
        }
        CookieUtil.applyCookiesToRequest((HttpConnection.Request)req, (HttpURLConnection)conn);
        Iterator iterator = req.multiHeaders().entrySet().iterator();
        block0: while (iterator.hasNext()) {
            Map.Entry header = iterator.next();
            Iterator iterator2 = ((List)header.getValue()).iterator();
            while (true) {
                if (!iterator2.hasNext()) continue block0;
                String value = (String)iterator2.next();
                conn.addRequestProperty((String)header.getKey(), value);
            }
            break;
        }
        return conn;
    }

    public HttpConnection.Response charset(String charset) {
        this.charset = charset;
        return this;
    }

    private static LinkedHashMap<String, List<String>> createHeaderMap(HttpURLConnection conn) {
        LinkedHashMap<String, List<String>> headers = new LinkedHashMap<String, List<String>>();
        int i = 0;
        while (true) {
            String key = conn.getHeaderFieldKey(i);
            String val = conn.getHeaderField(i);
            if (key == null && val == null) {
                return headers;
            }
            ++i;
            if (key == null || val == null) continue;
            if (headers.containsKey(key)) {
                headers.get(key).add(val);
                continue;
            }
            ArrayList<String> vals = new ArrayList<String>();
            vals.add(val);
            headers.put(key, vals);
        }
    }

    public String contentType() {
        return this.contentType;
    }

    private static boolean looksLikeUtf8(byte[] input) {
        int i = 0;
        if (input.length >= 3 && (input[0] & 0xFF) == 239 && (input[1] & 0xFF) == 187 && (input[2] & 0xFF) == 191) {
            i = 3;
        }
        boolean foundNonAscii = false;
        int j = input.length;
        while (i < j) {
            byte o = input[i];
            if ((o & 0x80) != 0) {
                int end;
                foundNonAscii = true;
                if ((o & 0xE0) == 192) {
                    end = i + 1;
                } else if ((o & 0xF0) == 224) {
                    end = i + 2;
                } else {
                    if ((o & 0xF8) != 240) return false;
                    end = i + 3;
                }
                if (end >= input.length) {
                    return false;
                }
                while (i < end) {
                    if (((o = input[++i]) & 0xC0) == 128) continue;
                    return false;
                }
            }
            ++i;
        }
        return foundNonAscii;
    }

    private static @7\u015aCz String fixHeaderEncoding(@7\u015aCz String val) {
        if (val == null) {
            return val;
        }
        byte[] bytes = val.getBytes(HttpConnection.access$700());
        if (!HttpConnection.Response.looksLikeUtf8(bytes)) return val;
        return new String(bytes, DataUtil.UTF_8);
    }

    public BufferedInputStream bodyStream() {
        Validate.isTrue((boolean)this.executed, (String)"Request must be executed (with .execute(), .get(), or .post() before getting response body");
        if (this.byteData != null) {
            return new BufferedInputStream(new ByteArrayInputStream(this.byteData.array()), 32768);
        }
        Validate.isFalse((boolean)this.inputStreamRead, (String)"Request has already been read");
        Validate.notNull((Object)this.bodyStream);
        this.inputStreamRead = true;
        return this.bodyStream.inputStream();
    }

    public int statusCode() {
        return this.statusCode;
    }
}
