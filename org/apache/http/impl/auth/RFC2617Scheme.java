/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.Consts
 *  org.apache.http.HeaderElement
 *  org.apache.http.HttpRequest
 *  org.apache.http.auth.ChallengeState
 *  org.apache.http.auth.MalformedChallengeException
 *  org.apache.http.impl.auth.AuthSchemeBase
 *  org.apache.http.message.BasicHeaderValueParser
 *  org.apache.http.message.ParserCursor
 *  org.apache.http.util.CharArrayBuffer
 *  org.apache.http.util.CharsetUtils
 */
package org.apache.http.impl.auth;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.Consts;
import org.apache.http.HeaderElement;
import org.apache.http.HttpRequest;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.impl.auth.AuthSchemeBase;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.CharsetUtils;

public abstract class RFC2617Scheme
extends AuthSchemeBase
implements Serializable {
    private final Map<String, String> params = new HashMap<String, String>();
    private transient Charset credentialsCharset;
    private static final long serialVersionUID = -2845454858205884623L;

    protected Map<String, String> getParameters() {
        return this.params;
    }

    private void readObjectNoData() throws ObjectStreamException {
    }

    public Charset getCredentialsCharset() {
        return this.credentialsCharset != null ? this.credentialsCharset : Consts.ASCII;
    }

    public String getParameter(String name) {
        if (name != null) return this.params.get(name.toLowerCase(Locale.ROOT));
        return null;
    }

    @Deprecated
    public RFC2617Scheme(ChallengeState challengeState) {
        super(challengeState);
        this.credentialsCharset = Consts.ASCII;
    }

    public RFC2617Scheme(Charset credentialsCharset) {
        this.credentialsCharset = credentialsCharset != null ? credentialsCharset : Consts.ASCII;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(this.credentialsCharset.name());
        out.writeObject(this.challengeState);
    }

    public RFC2617Scheme() {
        this(Consts.ASCII);
    }

    public String getRealm() {
        return this.getParameter("realm");
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.credentialsCharset = CharsetUtils.get((String)in.readUTF());
        if (this.credentialsCharset == null) {
            this.credentialsCharset = Consts.ASCII;
        }
        this.challengeState = (ChallengeState)in.readObject();
    }

    protected void parseChallenge(CharArrayBuffer buffer, int pos, int len) throws MalformedChallengeException {
        BasicHeaderValueParser parser = BasicHeaderValueParser.INSTANCE;
        ParserCursor cursor = new ParserCursor(pos, buffer.length());
        HeaderElement[] elements = parser.parseElements(buffer, cursor);
        this.params.clear();
        HeaderElement[] arr$ = elements;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            HeaderElement element = arr$[i$];
            this.params.put(element.getName().toLowerCase(Locale.ROOT), element.getValue());
            ++i$;
        }
    }

    String getCredentialsCharset(HttpRequest request) {
        String charset = (String)request.getParams().getParameter("http.auth.credential-charset");
        if (charset != null) return charset;
        charset = this.getCredentialsCharset().name();
        return charset;
    }
}
