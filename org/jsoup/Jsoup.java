/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.Connection
 *  org.jsoup.helper.DataUtil
 *  org.jsoup.helper.HttpConnection
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.parser.Parser
 *  org.jsoup.safety.Cleaner
 *  org.jsoup.safety.Safelist
 */
package org.jsoup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.jsoup.Connection;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Safelist;

public class Jsoup {
    public static Document parseBodyFragment(String bodyHtml, String baseUri) {
        return Parser.parseBodyFragment((String)bodyHtml, (String)baseUri);
    }

    public static String clean(String bodyHtml, String baseUri, Safelist safelist, Document.OutputSettings outputSettings) {
        Document dirty = Jsoup.parseBodyFragment(bodyHtml, baseUri);
        Cleaner cleaner = new Cleaner(safelist);
        Document clean = cleaner.clean(dirty);
        clean.outputSettings(outputSettings);
        return clean.body().html();
    }

    public static Document parse(String html, Parser parser) {
        return parser.parseInput(html, "");
    }

    private Jsoup() {
    }

    public static Document parse(InputStream in, @7\u015aCz String charsetName, String baseUri) throws IOException {
        return DataUtil.load((InputStream)in, (String)charsetName, (String)baseUri);
    }

    public static Document parse(File file, @7\u015aCz String charsetName, String baseUri, Parser parser) throws IOException {
        return DataUtil.load((File)file, (String)charsetName, (String)baseUri, (Parser)parser);
    }

    public static Document parse(String html, String baseUri, Parser parser) {
        return parser.parseInput(html, baseUri);
    }

    public static Document parse(URL url, int timeoutMillis) throws IOException {
        Connection con = HttpConnection.connect((URL)url);
        con.timeout(timeoutMillis);
        return con.get();
    }

    public static Document parse(String html) {
        return Parser.parse((String)html, (String)"");
    }

    public static Connection newSession() {
        return new HttpConnection();
    }

    public static String clean(String bodyHtml, String baseUri, Safelist safelist) {
        Document dirty = Jsoup.parseBodyFragment(bodyHtml, baseUri);
        Cleaner cleaner = new Cleaner(safelist);
        Document clean = cleaner.clean(dirty);
        return clean.body().html();
    }

    public static Document parse(File file, @7\u015aCz String charsetName) throws IOException {
        return DataUtil.load((File)file, (String)charsetName, (String)file.getAbsolutePath());
    }

    public static Document parse(InputStream in, @7\u015aCz String charsetName, String baseUri, Parser parser) throws IOException {
        return DataUtil.load((InputStream)in, (String)charsetName, (String)baseUri, (Parser)parser);
    }

    public static String clean(String bodyHtml, Safelist safelist) {
        return Jsoup.clean(bodyHtml, "", safelist);
    }

    public static Connection connect(String url) {
        return HttpConnection.connect((String)url);
    }

    public static Document parse(File file) throws IOException {
        return DataUtil.load((File)file, null, (String)file.getAbsolutePath());
    }

    public static Document parseBodyFragment(String bodyHtml) {
        return Parser.parseBodyFragment((String)bodyHtml, (String)"");
    }

    public static boolean isValid(String bodyHtml, Safelist safelist) {
        return new Cleaner(safelist).isValidBodyHtml(bodyHtml);
    }

    public static Document parse(File file, @7\u015aCz String charsetName, String baseUri) throws IOException {
        return DataUtil.load((File)file, (String)charsetName, (String)baseUri);
    }

    public static Document parse(String html, String baseUri) {
        return Parser.parse((String)html, (String)baseUri);
    }
}
