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
 *  org.apache.http.impl.cookie.RFC2965CommentUrlAttributeHandler
 *  org.apache.http.impl.cookie.RFC2965DiscardAttributeHandler
 *  org.apache.http.impl.cookie.RFC2965DomainAttributeHandler
 *  org.apache.http.impl.cookie.RFC2965PortAttributeHandler
 *  org.apache.http.impl.cookie.RFC2965Spec
 *  org.apache.http.impl.cookie.RFC2965VersionAttributeHandler
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
import org.apache.http.impl.cookie.RFC2965CommentUrlAttributeHandler;
import org.apache.http.impl.cookie.RFC2965DiscardAttributeHandler;
import org.apache.http.impl.cookie.RFC2965DomainAttributeHandler;
import org.apache.http.impl.cookie.RFC2965PortAttributeHandler;
import org.apache.http.impl.cookie.RFC2965Spec;
import org.apache.http.impl.cookie.RFC2965VersionAttributeHandler;
import org.apache.http.protocol.HttpContext;

@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
@Obsolete
public class RFC2965SpecProvider
implements CookieSpecProvider {
    private volatile CookieSpec cookieSpec;
    private final boolean oneHeader;
    private final PublicSuffixMatcher publicSuffixMatcher;

    public RFC2965SpecProvider(PublicSuffixMatcher publicSuffixMatcher, boolean oneHeader) {
        this.oneHeader = oneHeader;
        this.publicSuffixMatcher = publicSuffixMatcher;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CookieSpec create(HttpContext context) {
        if (this.cookieSpec != null) return this.cookieSpec;
        RFC2965SpecProvider rFC2965SpecProvider = this;
        synchronized (rFC2965SpecProvider) {
            if (this.cookieSpec != null) return this.cookieSpec;
            this.cookieSpec = new RFC2965Spec(this.oneHeader, new CommonCookieAttributeHandler[]{new RFC2965VersionAttributeHandler(), new BasicPathHandler(), PublicSuffixDomainFilter.decorate((CommonCookieAttributeHandler)new RFC2965DomainAttributeHandler(), (PublicSuffixMatcher)this.publicSuffixMatcher), new RFC2965PortAttributeHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler(), new RFC2965CommentUrlAttributeHandler(), new RFC2965DiscardAttributeHandler()});
        }
        return this.cookieSpec;
    }

    public RFC2965SpecProvider(PublicSuffixMatcher publicSuffixMatcher) {
        this(publicSuffixMatcher, false);
    }

    public RFC2965SpecProvider() {
        this(null, false);
    }
}
