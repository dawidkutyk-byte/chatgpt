/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.helper.DataUtil$BomCharset
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.ControllableInputStream
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Comment
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.nodes.XmlDeclaration
 *  org.jsoup.parser.Parser
 *  org.jsoup.select.Elements
 */
package org.jsoup.helper;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.Validate;
import org.jsoup.internal.ControllableInputStream;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/*
 * Exception performing whole class analysis ignored.
 */
public final class DataUtil {
    private static final int firstReadBufferSize = 5120;
    private static final char[] mimeBoundaryChars;
    private static final Pattern charsetPattern;
    public static final Charset UTF_8;
    static final String defaultCharsetName;
    static final int boundaryLength = 32;

    static String mimeBoundary() {
        StringBuilder mime = StringUtil.borrowBuilder();
        Random rand = new Random();
        int i = 0;
        while (i < 32) {
            mime.append(mimeBoundaryChars[rand.nextInt(mimeBoundaryChars.length)]);
            ++i;
        }
        return StringUtil.releaseBuilder((StringBuilder)mime);
    }

    private static // Could not load outer class - annotation placement on inner may be incorrect
    @7\u015aCz DataUtil.BomCharset detectCharsetFromBom(ByteBuffer byteData) {
        ByteBuffer buffer = byteData;
        ((Buffer)buffer).mark();
        byte[] bom = new byte[4];
        if (byteData.remaining() >= bom.length) {
            byteData.get(bom);
            ((Buffer)buffer).rewind();
        }
        if (bom[0] == 0 && bom[1] == 0 && bom[2] == -2) {
            if (bom[3] == -1) return new BomCharset("UTF-32", false);
        }
        if (bom[0] == -1 && bom[1] == -2 && bom[2] == 0 && bom[3] == 0) {
            return new BomCharset("UTF-32", false);
        }
        if (bom[0] == -2) {
            if (bom[1] == -1) return new BomCharset("UTF-16", false);
        }
        if (bom[0] == -1 && bom[1] == -2) {
            return new BomCharset("UTF-16", false);
        }
        if (bom[0] != -17) return null;
        if (bom[1] != -69) return null;
        if (bom[2] != -65) return null;
        return new BomCharset("UTF-8", true);
    }

    public static Document load(File file, @7\u015aCz String charsetName, String baseUri) throws IOException {
        return DataUtil.load(file, charsetName, baseUri, Parser.htmlParser());
    }

    static ByteBuffer emptyByteBuffer() {
        return ByteBuffer.allocate(0);
    }

    private static @7\u015aCz String validateCharset(@7\u015aCz String cs) {
        if (cs == null) return null;
        if (cs.length() == 0) {
            return null;
        }
        cs = cs.trim().replaceAll("[\"']", "");
        try {
            if (Charset.isSupported(cs)) {
                return cs;
            }
            if (!Charset.isSupported(cs = cs.toUpperCase(Locale.ENGLISH))) return null;
            return cs;
        }
        catch (IllegalCharsetNameException illegalCharsetNameException) {
            // empty catch block
        }
        return null;
    }

    public static Document load(InputStream in, @7\u015aCz String charsetName, String baseUri) throws IOException {
        return DataUtil.parseInputStream(in, charsetName, baseUri, Parser.htmlParser());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static Document parseInputStream(@7\u015aCz InputStream input, @7\u015aCz String charsetName, String baseUri, Parser parser) throws IOException {
        if (input == null) {
            return new Document(baseUri);
        }
        input = ControllableInputStream.wrap((InputStream)input, (int)32768, (int)0);
        Document doc = null;
        try {
            input.mark(32768);
            ByteBuffer firstBytes = DataUtil.readToByteBuffer(input, 5119);
            boolean fullyRead = input.read() == -1;
            input.reset();
            BomCharset bomCharset = DataUtil.detectCharsetFromBom(firstBytes);
            if (bomCharset != null) {
                charsetName = BomCharset.access$000((BomCharset)bomCharset);
            }
            if (charsetName == null) {
                try {
                    CharBuffer defaultDecoded = UTF_8.decode(firstBytes);
                    doc = defaultDecoded.hasArray() ? parser.parseInput((Reader)new CharArrayReader(defaultDecoded.array(), defaultDecoded.arrayOffset(), defaultDecoded.limit()), baseUri) : parser.parseInput(defaultDecoded.toString(), baseUri);
                }
                catch (UncheckedIOException e) {
                    throw e.getCause();
                }
                Elements metaElements = doc.select("meta[http-equiv=content-type], meta[charset]");
                String foundCharset = null;
                for (Element meta : metaElements) {
                    if (meta.hasAttr("http-equiv")) {
                        foundCharset = DataUtil.getCharsetFromContentType(meta.attr("content"));
                    }
                    if (foundCharset == null && meta.hasAttr("charset")) {
                        foundCharset = meta.attr("charset");
                    }
                    if (foundCharset == null) continue;
                }
                if (foundCharset == null && doc.childNodeSize() > 0) {
                    Comment comment;
                    Node first = doc.childNode(0);
                    XmlDeclaration decl = null;
                    if (first instanceof XmlDeclaration) {
                        decl = (XmlDeclaration)first;
                    } else if (first instanceof Comment && (comment = (Comment)first).isXmlDeclaration()) {
                        decl = comment.asXmlDeclaration();
                    }
                    if (decl != null && decl.name().equalsIgnoreCase("xml")) {
                        foundCharset = decl.attr("encoding");
                    }
                }
                if ((foundCharset = DataUtil.validateCharset(foundCharset)) != null && !foundCharset.equalsIgnoreCase(defaultCharsetName)) {
                    charsetName = foundCharset = foundCharset.trim().replaceAll("[\"']", "");
                    doc = null;
                } else if (!fullyRead) {
                    doc = null;
                }
            } else {
                Validate.notEmpty((String)charsetName, (String)"Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
            }
            if (doc != null) return doc;
            if (charsetName == null) {
                charsetName = defaultCharsetName;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName(charsetName)), 32768);){
                if (bomCharset != null && BomCharset.access$100((BomCharset)bomCharset)) {
                    long skipped = reader.skip(1L);
                    Validate.isTrue((skipped == 1L ? 1 : 0) != 0);
                }
                try {
                    doc = parser.parseInput((Reader)reader, baseUri);
                }
                catch (UncheckedIOException e) {
                    throw e.getCause();
                }
                Charset charset = charsetName.equals(defaultCharsetName) ? UTF_8 : Charset.forName(charsetName);
                doc.outputSettings().charset(charset);
                if (charset.canEncode()) return doc;
                doc.charset(UTF_8);
            }
        }
        finally {
            input.close();
        }
        return doc;
    }

    static {
        charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*(?:[\"'])?([^\\s,;\"']*)");
        UTF_8 = Charset.forName("UTF-8");
        defaultCharsetName = UTF_8.name();
        mimeBoundaryChars = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    static void crossStreams(InputStream in, OutputStream out) throws IOException {
        int len;
        byte[] buffer = new byte[32768];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
    }

    public static Document load(InputStream in, @7\u015aCz String charsetName, String baseUri, Parser parser) throws IOException {
        return DataUtil.parseInputStream(in, charsetName, baseUri, parser);
    }

    public static ByteBuffer readToByteBuffer(InputStream inStream, int maxSize) throws IOException {
        return ControllableInputStream.readToByteBuffer((InputStream)inStream, (int)maxSize);
    }

    private DataUtil() {
    }

    static @7\u015aCz String getCharsetFromContentType(@7\u015aCz String contentType) {
        if (contentType == null) {
            return null;
        }
        Matcher m = charsetPattern.matcher(contentType);
        if (!m.find()) return null;
        String charset = m.group(1).trim();
        charset = charset.replace("charset=", "");
        return DataUtil.validateCharset(charset);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Document load(File file, @7\u015aCz String charsetName, String baseUri, Parser parser) throws IOException {
        boolean zipped;
        InputStream stream = new FileInputStream(file);
        String name = Normalizer.lowerCase((String)file.getName());
        if (!name.endsWith(".gz")) {
            if (!name.endsWith(".z")) return DataUtil.parseInputStream(stream, charsetName, baseUri, parser);
        }
        try {
            zipped = ((InputStream)stream).read() == 31 && ((InputStream)stream).read() == 139;
        }
        finally {
            ((InputStream)stream).close();
        }
        stream = zipped ? new GZIPInputStream(new FileInputStream(file)) : new FileInputStream(file);
        return DataUtil.parseInputStream(stream, charsetName, baseUri, parser);
    }
}
