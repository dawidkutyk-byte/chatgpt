/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.util.PublicSuffixMatcher
 *  org.apache.http.cookie.CommonCookieAttributeHandler
 *  org.apache.http.cookie.CookieSpec
 *  org.apache.http.cookie.CookieSpecProvider
 *  org.apache.http.impl.cookie.BasicCommentHandler
 *  org.apache.http.impl.cookie.BasicDomainHandler
 *  org.apache.http.impl.cookie.BasicExpiresHandler
 *  org.apache.http.impl.cookie.BasicMaxAgeHandler
 *  org.apache.http.impl.cookie.BasicPathHandler
 *  org.apache.http.impl.cookie.BasicSecureHandler
 *  org.apache.http.impl.cookie.DefaultCookieSpec
 *  org.apache.http.impl.cookie.DefaultCookieSpecProvider$CompatibilityLevel
 *  org.apache.http.impl.cookie.NetscapeDraftSpec
 *  org.apache.http.impl.cookie.PublicSuffixDomainFilter
 *  org.apache.http.impl.cookie.RFC2109DomainHandler
 *  org.apache.http.impl.cookie.RFC2109Spec
 *  org.apache.http.impl.cookie.RFC2109VersionHandler
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
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.cookie.BasicCommentHandler;
import org.apache.http.impl.cookie.BasicDomainHandler;
import org.apache.http.impl.cookie.BasicExpiresHandler;
import org.apache.http.impl.cookie.BasicMaxAgeHandler;
import org.apache.http.impl.cookie.BasicPathHandler;
import org.apache.http.impl.cookie.BasicSecureHandler;
import org.apache.http.impl.cookie.DefaultCookieSpec;
import org.apache.http.impl.cookie.DefaultCookieSpecProvider;
import org.apache.http.impl.cookie.NetscapeDraftSpec;
import org.apache.http.impl.cookie.PublicSuffixDomainFilter;
import org.apache.http.impl.cookie.RFC2109DomainHandler;
import org.apache.http.impl.cookie.RFC2109Spec;
import org.apache.http.impl.cookie.RFC2109VersionHandler;
import org.apache.http.impl.cookie.RFC2965CommentUrlAttributeHandler;
import org.apache.http.impl.cookie.RFC2965DiscardAttributeHandler;
import org.apache.http.impl.cookie.RFC2965DomainAttributeHandler;
import org.apache.http.impl.cookie.RFC2965PortAttributeHandler;
import org.apache.http.impl.cookie.RFC2965Spec;
import org.apache.http.impl.cookie.RFC2965VersionAttributeHandler;
import org.apache.http.protocol.HttpContext;

@Contract(threading=ThreadingBehavior.IMMUTABLE)
public class DefaultCookieSpecProvider
implements CookieSpecProvider {
    private final String[] datepatterns;
    private final boolean oneHeader;
    private final PublicSuffixMatcher publicSuffixMatcher;
    private volatile CookieSpec cookieSpec;
    private final CompatibilityLevel compatibilityLevel;

    public DefaultCookieSpecProvider(PublicSuffixMatcher publicSuffixMatcher) {
        this(CompatibilityLevel.DEFAULT, publicSuffixMatcher, null, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CookieSpec create(HttpContext context) {
        if (this.cookieSpec != null) return this.cookieSpec;
        DefaultCookieSpecProvider defaultCookieSpecProvider = this;
        synchronized (defaultCookieSpecProvider) {
            String[] stringArray;
            if (this.cookieSpec != null) return this.cookieSpec;
            RFC2965Spec strict = new RFC2965Spec(this.oneHeader, new CommonCookieAttributeHandler[]{new RFC2965VersionAttributeHandler(), new BasicPathHandler(), PublicSuffixDomainFilter.decorate((CommonCookieAttributeHandler)new RFC2965DomainAttributeHandler(), (PublicSuffixMatcher)this.publicSuffixMatcher), new RFC2965PortAttributeHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler(), new RFC2965CommentUrlAttributeHandler(), new RFC2965DiscardAttributeHandler()});
            RFC2109Spec obsoleteStrict = new RFC2109Spec(this.oneHeader, new CommonCookieAttributeHandler[]{new RFC2109VersionHandler(), new BasicPathHandler(), PublicSuffixDomainFilter.decorate((CommonCookieAttributeHandler)new RFC2109DomainHandler(), (PublicSuffixMatcher)this.publicSuffixMatcher), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler()});
            CommonCookieAttributeHandler[] commonCookieAttributeHandlerArray = new CommonCookieAttributeHandler[5];
            commonCookieAttributeHandlerArray[0] = PublicSuffixDomainFilter.decorate((CommonCookieAttributeHandler)new BasicDomainHandler(), (PublicSuffixMatcher)this.publicSuffixMatcher);
            commonCookieAttributeHandlerArray[1] = this.compatibilityLevel == CompatibilityLevel.IE_MEDIUM_SECURITY ? new /* Unavailable Anonymous Inner Class!! */ : new BasicPathHandler();
            commonCookieAttributeHandlerArray[2] = new BasicSecureHandler();
            commonCookieAttributeHandlerArray[3] = new BasicCommentHandler();
            if (this.datepatterns != null) {
                stringArray = (String[])this.datepatterns.clone();
            } else {
                String[] stringArray2 = new String[1];
                stringArray = stringArray2;
                stringArray2[0] = "EEE, dd-MMM-yy HH:mm:ss z";
            }
            commonCookieAttributeHandlerArray[4] = new BasicExpiresHandler(stringArray);
            NetscapeDraftSpec netscapeDraft = new NetscapeDraftSpec(commonCookieAttributeHandlerArray);
            this.cookieSpec = new DefaultCookieSpec(strict, obsoleteStrict, netscapeDraft);
        }
        return this.cookieSpec;
    }

    public DefaultCookieSpecProvider() {
        this(CompatibilityLevel.DEFAULT, null, null, false);
    }

    public DefaultCookieSpecProvider(CompatibilityLevel compatibilityLevel, PublicSuffixMatcher publicSuffixMatcher, String[] datepatterns, boolean oneHeader) {
        this.compatibilityLevel = compatibilityLevel != null ? compatibilityLevel : CompatibilityLevel.DEFAULT;
        this.publicSuffixMatcher = publicSuffixMatcher;
        this.datepatterns = datepatterns;
        this.oneHeader = oneHeader;
    }

    public DefaultCookieSpecProvider(CompatibilityLevel compatibilityLevel, PublicSuffixMatcher publicSuffixMatcher) {
        this(compatibilityLevel, publicSuffixMatcher, null, false);
    }
}
