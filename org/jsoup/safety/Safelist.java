/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Element
 *  org.jsoup.safety.Safelist$AttributeKey
 *  org.jsoup.safety.Safelist$AttributeValue
 *  org.jsoup.safety.Safelist$Protocol
 *  org.jsoup.safety.Safelist$TagName
 */
package org.jsoup.safety;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;

/*
 * Exception performing whole class analysis ignored.
 */
public class Safelist {
    private final Map<TagName, Map<AttributeKey, AttributeValue>> enforcedAttributes;
    private final Set<TagName> tagNames = new HashSet<TagName>();
    private boolean preserveRelativeLinks = false;
    private final Map<TagName, Set<AttributeKey>> attributes = new HashMap<TagName, Set<AttributeKey>>();
    private static final String All = ":all";
    private final Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols;

    public static Safelist simpleText() {
        return new Safelist().addTags("b", "em", "i", "strong", "u");
    }

    public static Safelist none() {
        return new Safelist();
    }

    public Safelist removeAttributes(String tag, String ... attributes) {
        Validate.notEmpty((String)tag);
        Validate.notNull((Object)attributes);
        Validate.isTrue((attributes.length > 0 ? 1 : 0) != 0, (String)"No attribute names supplied.");
        TagName tagName = TagName.valueOf((String)tag);
        HashSet<AttributeKey> attributeSet = new HashSet<AttributeKey>();
        for (String key : attributes) {
            Validate.notEmpty((String)key);
            attributeSet.add(AttributeKey.valueOf((String)key));
        }
        if (this.tagNames.contains(tagName) && this.attributes.containsKey(tagName)) {
            Set<AttributeKey> currentSet = this.attributes.get(tagName);
            currentSet.removeAll(attributeSet);
            if (currentSet.isEmpty()) {
                this.attributes.remove(tagName);
            }
        }
        if (!tag.equals(":all")) return this;
        Iterator<Map.Entry<TagName, Set<AttributeKey>>> it = this.attributes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<TagName, Set<AttributeKey>> entry = it.next();
            Set<AttributeKey> currentSet = entry.getValue();
            currentSet.removeAll(attributeSet);
            if (!currentSet.isEmpty()) continue;
            it.remove();
        }
        return this;
    }

    public Safelist addAttributes(String tag, String ... attributes) {
        Validate.notEmpty((String)tag);
        Validate.notNull((Object)attributes);
        Validate.isTrue((attributes.length > 0 ? 1 : 0) != 0, (String)"No attribute names supplied.");
        this.addTags(tag);
        TagName tagName = TagName.valueOf((String)tag);
        HashSet<AttributeKey> attributeSet = new HashSet<AttributeKey>();
        for (String key : attributes) {
            Validate.notEmpty((String)key);
            attributeSet.add(AttributeKey.valueOf((String)key));
        }
        if (this.attributes.containsKey(tagName)) {
            Set<AttributeKey> currentSet = this.attributes.get(tagName);
            currentSet.addAll(attributeSet);
        } else {
            this.attributes.put(tagName, attributeSet);
        }
        return this;
    }

    public static Safelist basicWithImages() {
        return Safelist.basic().addTags("img").addAttributes("img", "align", "alt", "height", "src", "title", "width").addProtocols("img", "src", "http", "https");
    }

    public Safelist addEnforcedAttribute(String tag, String attribute, String value) {
        Validate.notEmpty((String)tag);
        Validate.notEmpty((String)attribute);
        Validate.notEmpty((String)value);
        TagName tagName = TagName.valueOf((String)tag);
        this.tagNames.add(tagName);
        AttributeKey attrKey = AttributeKey.valueOf((String)attribute);
        AttributeValue attrVal = AttributeValue.valueOf((String)value);
        if (this.enforcedAttributes.containsKey(tagName)) {
            this.enforcedAttributes.get(tagName).put(attrKey, attrVal);
        } else {
            HashMap<AttributeKey, AttributeValue> attrMap = new HashMap<AttributeKey, AttributeValue>();
            attrMap.put(attrKey, attrVal);
            this.enforcedAttributes.put(tagName, attrMap);
        }
        return this;
    }

    public boolean isSafeTag(String tag) {
        return this.tagNames.contains(TagName.valueOf((String)tag));
    }

    public static Safelist basic() {
        return new Safelist().addTags("a", "b", "blockquote", "br", "cite", "code", "dd", "dl", "dt", "em", "i", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong", "sub", "sup", "u", "ul").addAttributes("a", "href").addAttributes("blockquote", "cite").addAttributes("q", "cite").addProtocols("a", "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addEnforcedAttribute("a", "rel", "nofollow");
    }

    public Safelist removeProtocols(String tag, String attribute, String ... removeProtocols) {
        Validate.notEmpty((String)tag);
        Validate.notEmpty((String)attribute);
        Validate.notNull((Object)removeProtocols);
        TagName tagName = TagName.valueOf((String)tag);
        AttributeKey attr = AttributeKey.valueOf((String)attribute);
        Validate.isTrue((boolean)this.protocols.containsKey(tagName), (String)"Cannot remove a protocol that is not set.");
        Map<AttributeKey, Set<Protocol>> tagProtocols = this.protocols.get(tagName);
        Validate.isTrue((boolean)tagProtocols.containsKey(attr), (String)"Cannot remove a protocol that is not set.");
        Set<Protocol> attrProtocols = tagProtocols.get(attr);
        String[] stringArray = removeProtocols;
        int n = stringArray.length;
        int n2 = 0;
        while (true) {
            if (n2 >= n) {
                if (!attrProtocols.isEmpty()) return this;
                tagProtocols.remove(attr);
                if (!tagProtocols.isEmpty()) return this;
                this.protocols.remove(tagName);
                return this;
            }
            String protocol = stringArray[n2];
            Validate.notEmpty((String)protocol);
            attrProtocols.remove(Protocol.valueOf((String)protocol));
            ++n2;
        }
    }

    public Safelist addTags(String ... tags) {
        Validate.notNull((Object)tags);
        String[] stringArray = tags;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String tagName = stringArray[n2];
            Validate.notEmpty((String)tagName);
            Validate.isFalse((boolean)tagName.equalsIgnoreCase("noscript"), (String)"noscript is unsupported in Safelists, due to incompatibilities between parsers with and without script-mode enabled");
            this.tagNames.add(TagName.valueOf((String)tagName));
            ++n2;
        }
        return this;
    }

    public Safelist removeTags(String ... tags) {
        Validate.notNull((Object)tags);
        String[] stringArray = tags;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String tag = stringArray[n2];
            Validate.notEmpty((String)tag);
            TagName tagName = TagName.valueOf((String)tag);
            if (this.tagNames.remove(tagName)) {
                this.attributes.remove(tagName);
                this.enforcedAttributes.remove(tagName);
                this.protocols.remove(tagName);
            }
            ++n2;
        }
        return this;
    }

    public Safelist(Safelist copy) {
        this();
        this.tagNames.addAll(copy.tagNames);
        for (Map.Entry<TagName, Set<AttributeKey>> entry : copy.attributes.entrySet()) {
            this.attributes.put(entry.getKey(), new HashSet(entry.getValue()));
        }
        for (Map.Entry<TagName, Object> entry : copy.enforcedAttributes.entrySet()) {
            this.enforcedAttributes.put(entry.getKey(), new HashMap((Map)entry.getValue()));
        }
        Iterator<Map.Entry<TagName, Object>> iterator = copy.protocols.entrySet().iterator();
        while (true) {
            if (!iterator.hasNext()) {
                this.preserveRelativeLinks = copy.preserveRelativeLinks;
                return;
            }
            Map.Entry<TagName, Object> entry = iterator.next();
            HashMap attributeProtocolsCopy = new HashMap();
            for (Map.Entry attributeProtocols : ((Map)entry.getValue()).entrySet()) {
                attributeProtocolsCopy.put((AttributeKey)attributeProtocols.getKey(), new HashSet((Collection)attributeProtocols.getValue()));
            }
            this.protocols.put(entry.getKey(), attributeProtocolsCopy);
        }
    }

    public Safelist preserveRelativeLinks(boolean preserve) {
        this.preserveRelativeLinks = preserve;
        return this;
    }

    public Safelist() {
        this.enforcedAttributes = new HashMap<TagName, Map<AttributeKey, AttributeValue>>();
        this.protocols = new HashMap<TagName, Map<AttributeKey, Set<Protocol>>>();
    }

    public static Safelist relaxed() {
        return new Safelist().addTags("a", "b", "blockquote", "br", "caption", "cite", "code", "col", "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6", "i", "img", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong", "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u", "ul").addAttributes("a", "href", "title").addAttributes("blockquote", "cite").addAttributes("col", "span", "width").addAttributes("colgroup", "span", "width").addAttributes("img", "align", "alt", "height", "src", "title", "width").addAttributes("ol", "start", "type").addAttributes("q", "cite").addAttributes("table", "summary", "width").addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width").addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width").addAttributes("ul", "type").addProtocols("a", "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addProtocols("img", "src", "http", "https").addProtocols("q", "cite", "http", "https");
    }

    public Safelist removeEnforcedAttribute(String tag, String attribute) {
        Validate.notEmpty((String)tag);
        Validate.notEmpty((String)attribute);
        TagName tagName = TagName.valueOf((String)tag);
        if (!this.tagNames.contains(tagName)) return this;
        if (!this.enforcedAttributes.containsKey(tagName)) return this;
        AttributeKey attrKey = AttributeKey.valueOf((String)attribute);
        Map<AttributeKey, AttributeValue> attrMap = this.enforcedAttributes.get(tagName);
        attrMap.remove(attrKey);
        if (!attrMap.isEmpty()) return this;
        this.enforcedAttributes.remove(tagName);
        return this;
    }

    public Attributes getEnforcedAttributes(String tagName) {
        Attributes attrs = new Attributes();
        TagName tag = TagName.valueOf((String)tagName);
        if (!this.enforcedAttributes.containsKey(tag)) return attrs;
        Map<AttributeKey, AttributeValue> keyVals = this.enforcedAttributes.get(tag);
        Iterator<Map.Entry<AttributeKey, AttributeValue>> iterator = keyVals.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<AttributeKey, AttributeValue> entry = iterator.next();
            attrs.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return attrs;
    }

    private boolean isValidAnchor(String value) {
        return value.startsWith("#") && !value.matches(".*\\s.*");
    }

    public boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
        String attrKey;
        Attributes expect;
        TagName tag = TagName.valueOf((String)tagName);
        AttributeKey key = AttributeKey.valueOf((String)attr.getKey());
        Set<AttributeKey> okSet = this.attributes.get(tag);
        if (okSet != null && okSet.contains(key)) {
            if (!this.protocols.containsKey(tag)) return true;
            Map<AttributeKey, Set<Protocol>> attrProts = this.protocols.get(tag);
            return !attrProts.containsKey(key) || this.testValidProtocol(el, attr, attrProts.get(key));
        }
        Map<AttributeKey, AttributeValue> enforcedSet = this.enforcedAttributes.get(tag);
        if (enforcedSet == null || !(expect = this.getEnforcedAttributes(tagName)).hasKeyIgnoreCase(attrKey = attr.getKey())) return !tagName.equals(":all") && this.isSafeAttribute(":all", el, attr);
        return expect.getIgnoreCase(attrKey).equals(attr.getValue());
    }

    public Safelist addProtocols(String tag, String attribute, String ... protocols) {
        Set<Protocol> protSet;
        Map<Object, Object> attrMap;
        Validate.notEmpty((String)tag);
        Validate.notEmpty((String)attribute);
        Validate.notNull((Object)protocols);
        TagName tagName = TagName.valueOf((String)tag);
        AttributeKey attrKey = AttributeKey.valueOf((String)attribute);
        if (this.protocols.containsKey(tagName)) {
            attrMap = this.protocols.get(tagName);
        } else {
            attrMap = new HashMap();
            this.protocols.put(tagName, attrMap);
        }
        if (attrMap.containsKey(attrKey)) {
            protSet = (Set)attrMap.get(attrKey);
        } else {
            protSet = new HashSet();
            attrMap.put(attrKey, protSet);
        }
        String[] stringArray = protocols;
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String protocol = stringArray[n2];
            Validate.notEmpty((String)protocol);
            Protocol prot = Protocol.valueOf((String)protocol);
            protSet.add(prot);
            ++n2;
        }
        return this;
    }

    private boolean testValidProtocol(Element el, Attribute attr, Set<Protocol> protocols) {
        String value = el.absUrl(attr.getKey());
        if (value.length() == 0) {
            value = attr.getValue();
        }
        if (!this.preserveRelativeLinks) {
            attr.setValue(value);
        }
        Iterator<Protocol> iterator = protocols.iterator();
        while (iterator.hasNext()) {
            Protocol protocol = iterator.next();
            String prot = protocol.toString();
            if (prot.equals("#")) {
                if (!this.isValidAnchor(value)) continue;
                return true;
            }
            prot = prot + ":";
            if (Normalizer.lowerCase((String)value).startsWith(prot)) return true;
        }
        return false;
    }
}
