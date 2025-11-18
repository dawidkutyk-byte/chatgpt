/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.Connection
 *  org.jsoup.Connection$KeyVal
 *  org.jsoup.Connection$Method
 *  org.jsoup.Jsoup
 *  org.jsoup.helper.HttpConnection$KeyVal
 *  org.jsoup.helper.Validate
 *  org.jsoup.nodes.Attributes
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Element
 *  org.jsoup.nodes.Node
 *  org.jsoup.parser.Tag
 *  org.jsoup.select.Elements
 */
package org.jsoup.nodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class FormElement
extends Element {
    private final Elements elements = new Elements();

    public FormElement addElement(Element element) {
        this.elements.add((Object)element);
        return this;
    }

    public Elements elements() {
        return this.elements;
    }

    public FormElement(Tag tag, String baseUri, Attributes attributes) {
        super(tag, baseUri, attributes);
    }

    protected void removeChild(Node out) {
        super.removeChild(out);
        this.elements.remove((Object)out);
    }

    public Connection submit() {
        String action = this.hasAttr("action") ? this.absUrl("action") : this.baseUri();
        Validate.notEmpty((String)action, (String)"Could not determine a form action URL for submit. Ensure you set a base URI when parsing.");
        Connection.Method method = this.attr("method").equalsIgnoreCase("POST") ? Connection.Method.POST : Connection.Method.GET;
        Document owner = this.ownerDocument();
        Connection connection = owner != null ? owner.connection().newRequest() : Jsoup.newSession();
        return connection.url(action).data(this.formData()).method(method);
    }

    public List<Connection.KeyVal> formData() {
        ArrayList<Connection.KeyVal> data = new ArrayList<Connection.KeyVal>();
        Iterator iterator = this.elements.iterator();
        while (iterator.hasNext()) {
            String type;
            String name;
            Element el = (Element)iterator.next();
            if (!el.tag().isFormSubmittable() || el.hasAttr("disabled") || (name = el.attr("name")).length() == 0 || (type = el.attr("type")).equalsIgnoreCase("button") || type.equalsIgnoreCase("image")) continue;
            if (el.nameIs("select")) {
                Element option;
                Elements options = el.select("option[selected]");
                boolean set = false;
                for (Element option2 : options) {
                    data.add((Connection.KeyVal)HttpConnection.KeyVal.create((String)name, (String)option2.val()));
                    set = true;
                }
                if (set || (option = el.selectFirst("option")) == null) continue;
                data.add((Connection.KeyVal)HttpConnection.KeyVal.create((String)name, (String)option.val()));
                continue;
            }
            if ("checkbox".equalsIgnoreCase(type) || "radio".equalsIgnoreCase(type)) {
                if (!el.hasAttr("checked")) continue;
                String val = el.val().length() > 0 ? el.val() : "on";
                data.add((Connection.KeyVal)HttpConnection.KeyVal.create((String)name, (String)val));
                continue;
            }
            data.add((Connection.KeyVal)HttpConnection.KeyVal.create((String)name, (String)el.val()));
        }
        return data;
    }

    public FormElement clone() {
        return (FormElement)super.clone();
    }
}
