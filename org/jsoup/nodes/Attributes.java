/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  org.jsoup.SerializationException
 *  org.jsoup.helper.Validate
 *  org.jsoup.internal.Normalizer
 *  org.jsoup.internal.StringUtil
 *  org.jsoup.nodes.Attribute
 *  org.jsoup.nodes.Attributes$Dataset
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Document$OutputSettings
 *  org.jsoup.nodes.Document$OutputSettings$Syntax
 *  org.jsoup.nodes.Range$AttributeRange
 *  org.jsoup.parser.ParseSettings
 */
package org.jsoup.nodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Range;
import org.jsoup.parser.ParseSettings;

public class Attributes
implements Iterable<Attribute>,
Cloneable {
    Object[] vals;
    protected static final String dataPrefix = "data-";
    static final char InternalPrefix = '/';
    private static final int GrowthFactor = 2;
    private static final String EmptyString = "";
    private static final int InitialCapacity = 3;
    String[] keys = new String[3];
    static final int NotFound = -1;
    private int size = 0;

    public void addAll(Attributes incoming) {
        if (incoming.size() == 0) {
            return;
        }
        this.checkCapacity(this.size + incoming.size);
        boolean needsPut = this.size != 0;
        Iterator<Attribute> iterator = incoming.iterator();
        while (iterator.hasNext()) {
            Attribute attr = iterator.next();
            if (needsPut) {
                this.put(attr);
                continue;
            }
            this.add(attr.getKey(), attr.getValue());
        }
    }

    public int deduplicate(ParseSettings settings) {
        if (this.isEmpty()) {
            return 0;
        }
        boolean preserve = settings.preserveAttributeCase();
        int dupes = 0;
        int i = 0;
        while (i < this.keys.length) {
            for (int j = i + 1; j < this.keys.length && this.keys[j] != null; ++j) {
                if ((!preserve || !this.keys[i].equals(this.keys[j])) && (preserve || !this.keys[i].equalsIgnoreCase(this.keys[j]))) continue;
                ++dupes;
                this.remove(j);
                --j;
            }
            ++i;
        }
        return dupes;
    }

    public int size() {
        return this.size;
    }

    int indexOfKey(String key) {
        Validate.notNull((Object)key);
        int i = 0;
        while (i < this.size) {
            if (key.equals(this.keys[i])) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    final void html(Appendable accum, Document.OutputSettings out) throws IOException {
        int sz = this.size;
        int i = 0;
        while (i < sz) {
            String key;
            if (!Attributes.isInternalKey(this.keys[i]) && (key = Attribute.getValidKey((String)this.keys[i], (Document.OutputSettings.Syntax)out.syntax())) != null) {
                Attribute.htmlNoValidate((String)key, (String)((String)this.vals[i]), (Appendable)accum.append(' '), (Document.OutputSettings)out);
            }
            ++i;
        }
    }

    private void remove(int index) {
        Validate.isFalse((index >= this.size ? 1 : 0) != 0);
        int shifted = this.size - index - 1;
        if (shifted > 0) {
            System.arraycopy(this.keys, index + 1, this.keys, index, shifted);
            System.arraycopy(this.vals, index + 1, this.vals, index, shifted);
        }
        --this.size;
        this.keys[this.size] = null;
        this.vals[this.size] = null;
    }

    public void remove(String key) {
        int i = this.indexOfKey(key);
        if (i == -1) return;
        this.remove(i);
    }

    private void addObject(String key, @7\u015aCz Object value) {
        this.checkCapacity(this.size + 1);
        this.keys[this.size] = key;
        this.vals[this.size] = value;
        ++this.size;
    }

    public Attributes() {
        this.vals = new Object[3];
    }

    private void checkCapacity(int minNewSize) {
        int newCap;
        Validate.isTrue((minNewSize >= this.size ? 1 : 0) != 0);
        int curCap = this.keys.length;
        if (curCap >= minNewSize) {
            return;
        }
        int n = newCap = curCap >= 3 ? this.size * 2 : 3;
        if (minNewSize > newCap) {
            newCap = minNewSize;
        }
        this.keys = Arrays.copyOf(this.keys, newCap);
        this.vals = Arrays.copyOf(this.vals, newCap);
    }

    public @7\u015aCz Object userData(String key) {
        Validate.notNull((Object)key);
        if (!this.hasKey("/jsoup.userdata")) {
            return null;
        }
        Map<String, Object> userData = this.userData();
        return userData.get(key);
    }

    Map<String, Object> userData() {
        HashMap<String, Object> userData;
        int i = this.indexOfKey("/jsoup.userdata");
        if (i == -1) {
            userData = new HashMap();
            this.addObject("/jsoup.userdata", userData);
        } else {
            userData = (Map)this.vals[i];
        }
        return userData;
    }

    public int hashCode() {
        int result = this.size;
        result = 31 * result + Arrays.hashCode(this.keys);
        result = 31 * result + Arrays.hashCode(this.vals);
        return result;
    }

    public Attributes put(String key, boolean value) {
        if (value) {
            this.putIgnoreCase(key, null);
        } else {
            this.remove(key);
        }
        return this;
    }

    public Range.AttributeRange sourceRange(String key) {
        if (!this.hasKey(key)) {
            return Range.AttributeRange.UntrackedAttr;
        }
        Map<String, Range.AttributeRange> ranges = this.getRanges();
        if (ranges != null) Range.AttributeRange range;
        return (range = ranges.get(key)) != null ? range : Range.AttributeRange.UntrackedAttr;
        return Range.AttributeRange.UntrackedAttr;
    }

    public boolean hasDeclaredValueForKey(String key) {
        int i = this.indexOfKey(key);
        return i != -1 && this.vals[i] != null;
    }

    private int indexOfKeyIgnoreCase(String key) {
        Validate.notNull((Object)key);
        int i = 0;
        while (i < this.size) {
            if (key.equalsIgnoreCase(this.keys[i])) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    public boolean equals(@7\u015aCz Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) return false;
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Attributes that = (Attributes)o;
        if (this.size != that.size) {
            return false;
        }
        int i = 0;
        while (i < this.size) {
            String key = this.keys[i];
            int thatI = that.indexOfKey(key);
            if (thatI == -1) {
                return false;
            }
            Object val = this.vals[i];
            Object thatVal = that.vals[thatI];
            if (val == null ? thatVal != null : !val.equals(thatVal)) {
                return false;
            }
            ++i;
        }
        return true;
    }

    @7\u015aCz Map<String, // Could not load outer class - annotation placement on inner may be incorrect
    Range.AttributeRange> getRanges() {
        return (Map)this.userData("jsoup.attrs");
    }

    public Attributes add(String key, @7\u015aCz String value) {
        this.addObject(key, value);
        return this;
    }

    public void normalize() {
        int i = 0;
        while (i < this.size) {
            if (!Attributes.isInternalKey(this.keys[i])) {
                this.keys[i] = Normalizer.lowerCase((String)this.keys[i]);
            }
            ++i;
        }
    }

    public Attributes userData(String key, Object value) {
        Validate.notNull((Object)key);
        this.userData().put(key, value);
        return this;
    }

    @Override
    public Iterator<Attribute> iterator() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    public Attributes put(String key, @7\u015aCz String value) {
        Validate.notNull((Object)key);
        int i = this.indexOfKey(key);
        if (i != -1) {
            this.vals[i] = value;
        } else {
            this.add(key, value);
        }
        return this;
    }

    public boolean hasKeyIgnoreCase(String key) {
        return this.indexOfKeyIgnoreCase(key) != -1;
    }

    public void removeIgnoreCase(String key) {
        int i = this.indexOfKeyIgnoreCase(key);
        if (i == -1) return;
        this.remove(i);
    }

    public List<Attribute> asList() {
        ArrayList<Attribute> list = new ArrayList<Attribute>(this.size);
        int i = 0;
        while (i < this.size) {
            if (!Attributes.isInternalKey(this.keys[i])) {
                Attribute attr = new Attribute(this.keys[i], (String)this.vals[i], this);
                list.add(attr);
            }
            ++i;
        }
        return Collections.unmodifiableList(list);
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        return this.html();
    }

    static String internalKey(String key) {
        return '/' + key;
    }

    private static String dataKey(String key) {
        return dataPrefix + key;
    }

    static /* synthetic */ void access$100(Attributes x0, int x1) {
        x0.remove(x1);
    }

    public Attribute attribute(String key) {
        int i = this.indexOfKey(key);
        return i == -1 ? null : new Attribute(key, Attributes.checkNotNull(this.vals[i]), this);
    }

    static /* synthetic */ String access$400(String x0) {
        return Attributes.dataKey(x0);
    }

    static /* synthetic */ int access$000(Attributes x0) {
        return x0.size;
    }

    public boolean hasDeclaredValueForKeyIgnoreCase(String key) {
        int i = this.indexOfKeyIgnoreCase(key);
        return i != -1 && this.vals[i] != null;
    }

    public String html() {
        StringBuilder sb = StringUtil.borrowBuilder();
        try {
            this.html(sb, new Document(EmptyString).outputSettings());
        }
        catch (IOException e) {
            throw new SerializationException((Throwable)e);
        }
        return StringUtil.releaseBuilder((StringBuilder)sb);
    }

    void putIgnoreCase(String key, @7\u015aCz String value) {
        int i = this.indexOfKeyIgnoreCase(key);
        if (i != -1) {
            this.vals[i] = value;
            if (this.keys[i].equals(key)) return;
            this.keys[i] = key;
        } else {
            this.add(key, value);
        }
    }

    public String getIgnoreCase(String key) {
        int i = this.indexOfKeyIgnoreCase(key);
        return i == -1 ? EmptyString : Attributes.checkNotNull(this.vals[i]);
    }

    static String checkNotNull(@7\u015aCz Object val) {
        return val == null ? EmptyString : (String)val;
    }

    static boolean isInternalKey(String key) {
        return key != null && key.length() > 1 && key.charAt(0) == '/';
    }

    public Map<String, String> dataset() {
        return new Dataset(this, null);
    }

    public String get(String key) {
        int i = this.indexOfKey(key);
        return i == -1 ? EmptyString : Attributes.checkNotNull(this.vals[i]);
    }

    public Attributes clone() {
        Attributes clone;
        try {
            clone = (Attributes)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        clone.size = this.size;
        clone.keys = Arrays.copyOf(this.keys, this.size);
        clone.vals = Arrays.copyOf(this.vals, this.size);
        return clone;
    }

    public boolean hasKey(String key) {
        return this.indexOfKey(key) != -1;
    }

    public Attributes put(Attribute attribute) {
        Validate.notNull((Object)attribute);
        this.put(attribute.getKey(), attribute.getValue());
        attribute.parent = this;
        return this;
    }
}
