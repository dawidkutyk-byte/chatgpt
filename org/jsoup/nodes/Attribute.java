/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.SerializationException
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.Entities
 *  org.jsoup.nodes.Range$AttributeRange
 */
package org.jsoup.nodes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.Range;

public class Attribute
implements Map.Entry<String, String>,
Cloneable {
    private String key;
    @7\u015aCz Attributes parent;
    private static final Pattern htmlKeyReplace;
    private @7\u015aCz String val;
    private static final String[] booleanAttributes;
    private static final Pattern xmlKeyReplace;
    private static final Pattern xmlKeyValid;
    private static final Pattern htmlKeyValid;

    public Attribute(String key, @7\u015aCz String value) {
        this(key, value, null);
    }

    protected final boolean shouldCollapseAttribute(Document.OutputSettings out) {
        return Attribute.shouldCollapseAttribute(this.key, this.val, out);
    }

    protected static void html(String key, @7\u015aCz String val, Appendable accum, Document.OutputSettings out) throws IOException {
        if ((key = Attribute.getValidKey(key, out.syntax())) == null) {
            return;
        }
        Attribute.htmlNoValidate(key, val, accum, out);
    }

    public Attribute clone() {
        try {
            return (Attribute)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public String html() {
        StringBuilder sb = StringUtil.borrowBuilder();
        try {
            this.html(sb, new Document("").outputSettings());
        }
        catch (IOException exception) {
            throw new SerializationException((Throwable)exception);
        }
        return StringUtil.releaseBuilder((StringBuilder)sb);
    }

    @Override
    public String getKey() {
        return this.key;
    }

    static void htmlNoValidate(String key, @7\u015aCz String val, Appendable accum, Document.OutputSettings out) throws IOException {
        accum.append(key);
        if (Attribute.shouldCollapseAttribute(key, val, out)) return;
        accum.append("=\"");
        Entities.escape((Appendable)accum, (String)Attributes.checkNotNull((Object)val), (Document.OutputSettings)out, (boolean)true, (boolean)false, (boolean)false, (boolean)false);
        accum.append('\"');
    }

    @Override
    public boolean equals(@7\u015aCz Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) return false;
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute)o;
        if (!(this.key != null ? !this.key.equals(attribute.key) : attribute.key != null)) return this.val != null ? this.val.equals(attribute.val) : attribute.val == null;
        return false;
    }

    public boolean hasDeclaredValue() {
        return this.val != null;
    }

    public String toString() {
        return this.html();
    }

    static {
        booleanAttributes = new String[]{"allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};
        xmlKeyValid = Pattern.compile("[a-zA-Z_:][-a-zA-Z0-9_:.]*");
        xmlKeyReplace = Pattern.compile("[^-a-zA-Z0-9_:.]");
        htmlKeyValid = Pattern.compile("[^\\x00-\\x1f\\x7f-\\x9f \"'/=]+");
        htmlKeyReplace = Pattern.compile("[\\x00-\\x1f\\x7f-\\x9f \"'/=]");
    }

    protected static boolean shouldCollapseAttribute(String key, @7\u015aCz String val, Document.OutputSettings out) {
        return out.syntax() == Document.OutputSettings.Syntax.html && (val == null || (val.isEmpty() || val.equalsIgnoreCase(key)) && Attribute.isBooleanAttribute(key));
    }

    @Override
    public String getValue() {
        return Attributes.checkNotNull((Object)this.val);
    }

    public static boolean isBooleanAttribute(String key) {
        return Arrays.binarySearch(booleanAttributes, Normalizer.lowerCase((String)key)) >= 0;
    }

    protected void html(Appendable accum, Document.OutputSettings out) throws IOException {
        Attribute.html(this.key, this.val, accum, out);
    }

    public static Attribute createFromEncoded(String unencodedKey, String encodedValue) {
        String value = Entities.unescape((String)encodedValue, (boolean)true);
        return new Attribute(unencodedKey, value, null);
    }

    protected static boolean isDataAttribute(String key) {
        return key.startsWith("data-") && key.length() > "data-".length();
    }

    public void setKey(String key) {
        int i;
        Validate.notNull((Object)key);
        key = key.trim();
        Validate.notEmpty((String)key);
        if (this.parent != null && (i = this.parent.indexOfKey(this.key)) != -1) {
            String oldKey = this.parent.keys[i];
            this.parent.keys[i] = key;
            Map ranges = this.parent.getRanges();
            if (ranges != null) {
                Range.AttributeRange range = (Range.AttributeRange)ranges.remove(oldKey);
                ranges.put(key, range);
            }
        }
        this.key = key;
    }

    public Range.AttributeRange sourceRange() {
        if (this.parent != null) return this.parent.sourceRange(this.key);
        return Range.AttributeRange.UntrackedAttr;
    }

    @Override
    public String setValue(@7\u015aCz String val) {
        int i;
        String oldVal = this.val;
        if (this.parent != null && (i = this.parent.indexOfKey(this.key)) != -1) {
            oldVal = this.parent.get(this.key);
            this.parent.vals[i] = val;
        }
        this.val = val;
        return Attributes.checkNotNull((Object)oldVal);
    }

    public Attribute(String key, @7\u015aCz String val, @7\u015aCz Attributes parent) {
        Validate.notNull((Object)key);
        key = key.trim();
        Validate.notEmpty((String)key);
        this.key = key;
        this.val = val;
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        int result = this.key != null ? this.key.hashCode() : 0;
        result = 31 * result + (this.val != null ? this.val.hashCode() : 0);
        return result;
    }

    protected boolean isDataAttribute() {
        return Attribute.isDataAttribute(this.key);
    }

    public static @7\u015aCz String getValidKey(String key, Document.OutputSettings.Syntax syntax) {
        if (syntax == Document.OutputSettings.Syntax.xml && !xmlKeyValid.matcher(key).matches()) {
            return xmlKeyValid.matcher(key = xmlKeyReplace.matcher(key).replaceAll("")).matches() ? key : null;
        }
        if (syntax != Document.OutputSettings.Syntax.html) return key;
        if (htmlKeyValid.matcher(key).matches()) return key;
        return htmlKeyValid.matcher(key = htmlKeyReplace.matcher(key).replaceAll("")).matches() ? key : null;
    }
}
