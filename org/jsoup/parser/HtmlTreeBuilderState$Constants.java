/*
 * Decompiled with CFR 0.152.
 */
package org.jsoup.parser;

static final class HtmlTreeBuilderState.Constants {
    static final String[] InTableFoster;
    static final String[] DdDt;
    static final String[] InHeadNoscriptIgnore;
    static final String[] InSelectTableEnd;
    static final String[] AfterHeadBody;
    static final String[] InForeignToHtml;
    static final String[] InTableToHead;
    static final String[] BeforeHtmlToHead;
    static final String[] InRowMissing;
    static final String[] InHeadEnd;
    static final String[] InBodyStartPClosers;
    static final String[] InTableBodyExit;
    static final String[] InBodyStartInputAttribs;
    static final String[] InTableEndErr;
    static final String[] InSelectEnd;
    static final String[] InBodyStartLiBreakers;
    static final String[] InTemplateToHead;
    static final String[] InBodyEndClosers;
    static final String[] Headings;
    static final String[] InTemplateToTable;
    static final String[] InBodyStartApplets;
    static final String[] InTableBodyEndIgnore;
    static final String[] InBodyStartDrop;
    static final String[] InTableEndIgnore;
    static final String[] InCellCol;
    static final String[] InCaptionIgnore;
    static final String[] InCellTable;
    static final String[] InTableAddBody;
    static final String[] InRowIgnore;
    static final String[] InHeadRaw;
    static final String[] InBodyStartToHead;
    static final String[] InBodyEndOtherErrors;
    static final String[] InHeadNoScriptHead;
    static final String[] InHeadEmpty;
    static final String[] InBodyStartMedia;
    static final String[] InCellNames;
    static final String[] InBodyEndAdoptionFormatters;
    static final String[] InCellBody;
    static final String[] InBodyEndTableFosters;
    static final String[] InTableToBody;

    HtmlTreeBuilderState.Constants() {
    }

    static {
        InHeadEmpty = new String[]{"base", "basefont", "bgsound", "command", "link"};
        InHeadRaw = new String[]{"noframes", "style"};
        InHeadEnd = new String[]{"body", "br", "html"};
        AfterHeadBody = new String[]{"body", "br", "html"};
        BeforeHtmlToHead = new String[]{"body", "br", "head", "html"};
        InHeadNoScriptHead = new String[]{"basefont", "bgsound", "link", "meta", "noframes", "style"};
        InBodyStartToHead = new String[]{"base", "basefont", "bgsound", "command", "link", "meta", "noframes", "script", "style", "template", "title"};
        InBodyStartPClosers = new String[]{"address", "article", "aside", "blockquote", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", "ol", "p", "section", "summary", "ul"};
        Headings = new String[]{"h1", "h2", "h3", "h4", "h5", "h6"};
        InBodyStartLiBreakers = new String[]{"address", "div", "p"};
        DdDt = new String[]{"dd", "dt"};
        InBodyStartApplets = new String[]{"applet", "marquee", "object"};
        InBodyStartMedia = new String[]{"param", "source", "track"};
        InBodyStartInputAttribs = new String[]{"action", "name", "prompt"};
        InBodyStartDrop = new String[]{"caption", "col", "colgroup", "frame", "head", "tbody", "td", "tfoot", "th", "thead", "tr"};
        InBodyEndClosers = new String[]{"address", "article", "aside", "blockquote", "button", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", "ol", "pre", "section", "summary", "ul"};
        InBodyEndOtherErrors = new String[]{"body", "dd", "dt", "html", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc", "tbody", "td", "tfoot", "th", "thead", "tr"};
        InBodyEndAdoptionFormatters = new String[]{"a", "b", "big", "code", "em", "font", "i", "nobr", "s", "small", "strike", "strong", "tt", "u"};
        InBodyEndTableFosters = new String[]{"table", "tbody", "tfoot", "thead", "tr"};
        InTableToBody = new String[]{"tbody", "tfoot", "thead"};
        InTableAddBody = new String[]{"td", "th", "tr"};
        InTableToHead = new String[]{"script", "style", "template"};
        InCellNames = new String[]{"td", "th"};
        InCellBody = new String[]{"body", "caption", "col", "colgroup", "html"};
        InCellTable = new String[]{"table", "tbody", "tfoot", "thead", "tr"};
        InCellCol = new String[]{"caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"};
        InTableEndErr = new String[]{"body", "caption", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};
        InTableFoster = new String[]{"table", "tbody", "tfoot", "thead", "tr"};
        InTableBodyExit = new String[]{"caption", "col", "colgroup", "tbody", "tfoot", "thead"};
        InTableBodyEndIgnore = new String[]{"body", "caption", "col", "colgroup", "html", "td", "th", "tr"};
        InRowMissing = new String[]{"caption", "col", "colgroup", "tbody", "tfoot", "thead", "tr"};
        InRowIgnore = new String[]{"body", "caption", "col", "colgroup", "html", "td", "th"};
        InSelectEnd = new String[]{"input", "keygen", "textarea"};
        InSelectTableEnd = new String[]{"caption", "table", "tbody", "td", "tfoot", "th", "thead", "tr"};
        InTableEndIgnore = new String[]{"tbody", "tfoot", "thead"};
        InHeadNoscriptIgnore = new String[]{"head", "noscript"};
        InCaptionIgnore = new String[]{"body", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"};
        InTemplateToHead = new String[]{"base", "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "template", "title"};
        InTemplateToTable = new String[]{"caption", "colgroup", "tbody", "tfoot", "thead"};
        InForeignToHtml = new String[]{"b", "big", "blockquote", "body", "br", "center", "code", "dd", "div", "dl", "dt", "em", "embed", "h1", "h2", "h3", "h4", "h5", "h6", "head", "hr", "i", "img", "li", "listing", "menu", "meta", "nobr", "ol", "p", "pre", "ruby", "s", "small", "span", "strike", "strong", "sub", "sup", "table", "tt", "u", "ul", "var"};
    }
}
