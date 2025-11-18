/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.Obsolete
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.util.PublicSuffixMatcher
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.cookie.CookieSpec
 *  org.apache.http.cookie.CookieSpecProvider
 *  org.apache.http.impl.cookie.BasicCommentHandler
 *  org.apache.http.impl.cookie.BasicMaxAgeHandler
 *  org.apache.http.impl.cookie.BasicPathHandler
 *  org.apache.http.impl.cookie.BasicSecureHandler
 *  org.apache.http.impl.cookie.PublicSuffixDomainFilter
 *  org.apache.http.impl.cookie.RFC2109DomainHandler
 *  org.apache.http.impl.cookie.RFC2109Spec
 *  org.apache.http.impl.cookie.RFC2109VersionHandler
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.cookie;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.Obsolete;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.cookie.BasicCommentHandler;
import org.apache.http.impl.cookie.BasicMaxAgeHandler;
import org.apache.http.impl.cookie.BasicPathHandler;
import org.apache.http.impl.cookie.BasicSecureHandler;
import org.apache.http.impl.cookie.PublicSuffixDomainFilter;
import org.apache.http.impl.cookie.RFC2109DomainHandler;
import org.apache.http.impl.cookie.RFC2109Spec;
import org.apache.http.impl.cookie.RFC2109VersionHandler;
import org.apache.http.protocol.HttpContext;

@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
@Obsolete
public class RFC2109SpecProvider
implements CookieSpecProvider {
    private final PublicSuffixMatcher publicSuffixMatcher;
    private final boolean oneHeader;
    private volatile CookieSpec cookieSpec;

    public RFC2109SpecProvider() {
        this(null, false);
    }

    public RFC2109SpecProvider(PublicSuffixMatcher publicSuffixMatcher) {
        this(publicSuffixMatcher, false);
    }

    public RFC2109SpecProvider(PublicSuffixMatcher publicSuffixMatcher, boolean oneHeader) {
        this.oneHeader = oneHeader;
        this.publicSuffixMatcher = publicSuffixMatcher;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CookieSpec create(HttpContext context) {
        if (this.cookieSpec != null) return this.cookieSpec;
        RFC2109SpecProvider rFC2109SpecProvider = this;
        synchronized (rFC2109SpecProvider) {
            if (this.cookieSpec != null) return this.cookieSpec;
            this.cookieSpec = new RFC2109Spec(this.oneHeader, new CommonCookieAttributeHandler[]{new RFC2109VersionHandler(), new BasicPathHandler(), PublicSuffixDomainFilter.decorate((CommonCookieAttributeHandler)new RFC2109DomainHandler(), (PublicSuffixMatcher)this.publicSuffixMatcher), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler()});
        }
        return this.cookieSpec;
    }
}
