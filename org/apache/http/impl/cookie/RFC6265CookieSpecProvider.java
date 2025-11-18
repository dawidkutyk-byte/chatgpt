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
 *  org.apache.http.impl.cookie.BasicDomainHandler
 *  org.apache.http.impl.cookie.BasicExpiresHandler
 *  org.apache.http.impl.cookie.BasicMaxAgeHandler
 *  org.apache.http.impl.cookie.BasicPathHandler
 *  org.apache.http.impl.cookie.BasicSecureHandler
 *  org.apache.http.impl.cookie.LaxExpiresHandler
 *  org.apache.http.impl.cookie.LaxMaxAgeHandler
 *  org.apache.http.impl.cookie.PublicSuffixDomainFilter
 *  org.apache.http.impl.cookie.RFC6265CookieSpecProvider$2
 *  org.apache.http.impl.cookie.RFC6265CookieSpecProvider$CompatibilityLevel
 *  org.apache.http.impl.cookie.RFC6265LaxSpec
 *  org.apache.http.impl.cookie.RFC6265StrictSpec
 *  org.apache.http.protocol.HttpContext
 */
package org.apache.http.impl.cookie;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.cookie.BasicDomainHandler;
import org.apache.http.impl.cookie.BasicExpiresHandler;
import org.apache.http.impl.cookie.BasicMaxAgeHandler;
import org.apache.http.impl.cookie.BasicPathHandler;
import org.apache.http.impl.cookie.BasicSecureHandler;
import org.apache.http.impl.cookie.LaxExpiresHandler;
import org.apache.http.impl.cookie.LaxMaxAgeHandler;
import org.apache.http.impl.cookie.PublicSuffixDomainFilter;
import org.apache.http.impl.cookie.RFC6265CookieSpecProvider;
import org.apache.http.impl.cookie.RFC6265LaxSpec;
import org.apache.http.impl.cookie.RFC6265StrictSpec;
import org.apache.http.protocol.HttpContext;

@Contract(threading=ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public class RFC6265CookieSpecProvider
implements CookieSpecProvider {
    private final CompatibilityLevel compatibilityLevel;
    private final PublicSuffixMatcher publicSuffixMatcher;
    private volatile CookieSpec cookieSpec;

    public RFC6265CookieSpecProvider(PublicSuffixMatcher publicSuffixMatcher) {
        this(CompatibilityLevel.RELAXED, publicSuffixMatcher);
    }

    public RFC6265CookieSpecProvider() {
        this(CompatibilityLevel.RELAXED, null);
    }

    public RFC6265CookieSpecProvider(CompatibilityLevel compatibilityLevel, PublicSuffixMatcher publicSuffixMatcher) {
        this.compatibilityLevel = compatibilityLevel != null ? compatibilityLevel : CompatibilityLevel.RELAXED;
        this.publicSuffixMatcher = publicSuffixMatcher;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public CookieSpec create(HttpContext context) {
        if (this.cookieSpec != null) return this.cookieSpec;
        RFC6265CookieSpecProvider rFC6265CookieSpecProvider = this;
        synchronized (rFC6265CookieSpecProvider) {
            if (this.cookieSpec != null) return this.cookieSpec;
            switch (2.$SwitchMap$org$apache$http$impl$cookie$RFC6265CookieSpecProvider$CompatibilityLevel[this.compatibilityLevel.ordinal()]) {
                case 1: {
                    this.cookieSpec = new RFC6265StrictSpec(new CommonCookieAttributeHandler[]{new BasicPathHandler(), PublicSuffixDomainFilter.decorate((CommonCookieAttributeHandler)new BasicDomainHandler(), (PublicSuffixMatcher)this.publicSuffixMatcher), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicExpiresHandler(RFC6265StrictSpec.DATE_PATTERNS)});
                    break;
                }
                case 2: {
                    this.cookieSpec = new RFC6265LaxSpec(new CommonCookieAttributeHandler[]{new /* Unavailable Anonymous Inner Class!! */, PublicSuffixDomainFilter.decorate((CommonCookieAttributeHandler)new BasicDomainHandler(), (PublicSuffixMatcher)this.publicSuffixMatcher), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicExpiresHandler(RFC6265StrictSpec.DATE_PATTERNS)});
                    break;
                }
                default: {
                    this.cookieSpec = new RFC6265LaxSpec(new CommonCookieAttributeHandler[]{new BasicPathHandler(), PublicSuffixDomainFilter.decorate((CommonCookieAttributeHandler)new BasicDomainHandler(), (PublicSuffixMatcher)this.publicSuffixMatcher), new LaxMaxAgeHandler(), new BasicSecureHandler(), new LaxExpiresHandler()});
                }
            }
        }
        return this.cookieSpec;
    }
}
