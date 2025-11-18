/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.Contract
 *  org.apache.http.annotation.ThreadingBehavior
 *  org.apache.http.conn.util.DnsUtils
 *  org.apache.http.conn.util.DomainType
 *  org.apache.http.conn.util.PublicSuffixList
 *  org.apache.http.util.Args
 */
package org.apache.http.conn.util;

import java.net.IDN;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.util.DnsUtils;
import org.apache.http.conn.util.DomainType;
import org.apache.http.conn.util.PublicSuffixList;
import org.apache.http.util.Args;

@Contract(threading=ThreadingBehavior.SAFE)
public final class PublicSuffixMatcher {
    private final Map<String, DomainType> exceptions;
    private final Map<String, DomainType> rules;

    public String getDomainRoot(String domain) {
        return this.getDomainRoot(domain, null);
    }

    public PublicSuffixMatcher(Collection<String> rules, Collection<String> exceptions) {
        this(DomainType.UNKNOWN, rules, exceptions);
    }

    private static boolean match(DomainType domainType, DomainType expectedType) {
        return domainType != null && (expectedType == null || domainType.equals((Object)expectedType));
    }

    /*
     * Unable to fully structure code
     */
    public PublicSuffixMatcher(Collection<PublicSuffixList> lists) {
        super();
        Args.notNull(lists, (String)"Domain suffix lists");
        this.rules = new ConcurrentHashMap<String, DomainType>();
        this.exceptions = new ConcurrentHashMap<String, DomainType>();
        i$ = lists.iterator();
        block0: while (true) {
            if (i$.hasNext() == false) return;
            list = i$.next();
            domainType = list.getType();
            rules = list.getRules();
            for (String rule : rules) {
                this.rules.put(rule, domainType);
            }
            exceptions = list.getExceptions();
            if (exceptions == null) continue;
            i$ = exceptions.iterator();
            while (true) {
                if (i$.hasNext()) ** break;
                continue block0;
                exception = (String)i$.next();
                this.exceptions.put(exception, domainType);
            }
            break;
        }
    }

    public boolean matches(String domain, DomainType expectedType) {
        if (domain == null) {
            return false;
        }
        String domainRoot = this.getDomainRoot(domain.startsWith(".") ? domain.substring(1) : domain, expectedType);
        return domainRoot == null;
    }

    public boolean matches(String domain) {
        return this.matches(domain, null);
    }

    public String getDomainRoot(String domain, DomainType expectedType) {
        String normalized;
        if (domain == null) {
            return null;
        }
        if (domain.startsWith(".")) {
            return null;
        }
        String segment = normalized = DnsUtils.normalize((String)domain);
        String result = null;
        while (true) {
            DomainType wildcardDomainRule;
            String nextSegment;
            if (segment == null) {
                if (expectedType == null) return result;
                if (expectedType != DomainType.UNKNOWN) return null;
                return result;
            }
            String key = IDN.toUnicode(segment);
            DomainType exceptionRule = PublicSuffixMatcher.findEntry(this.exceptions, key);
            if (PublicSuffixMatcher.match(exceptionRule, expectedType)) {
                return segment;
            }
            DomainType domainRule = PublicSuffixMatcher.findEntry(this.rules, key);
            if (PublicSuffixMatcher.match(domainRule, expectedType)) {
                if (domainRule != DomainType.PRIVATE) return result;
                return segment;
            }
            int nextdot = segment.indexOf(46);
            String string = nextSegment = nextdot != -1 ? segment.substring(nextdot + 1) : null;
            if (nextSegment != null && PublicSuffixMatcher.match(wildcardDomainRule = PublicSuffixMatcher.findEntry(this.rules, "*." + IDN.toUnicode(nextSegment)), expectedType)) {
                if (wildcardDomainRule != DomainType.PRIVATE) return result;
                return segment;
            }
            result = segment;
            segment = nextSegment;
        }
    }

    private static DomainType findEntry(Map<String, DomainType> map, String rule) {
        if (map != null) return map.get(rule);
        return null;
    }

    public PublicSuffixMatcher(DomainType domainType, Collection<String> rules, Collection<String> exceptions) {
        Args.notNull((Object)domainType, (String)"Domain type");
        Args.notNull(rules, (String)"Domain suffix rules");
        this.rules = new ConcurrentHashMap<String, DomainType>(rules.size());
        for (String rule : rules) {
            this.rules.put(rule, domainType);
        }
        this.exceptions = new ConcurrentHashMap<String, DomainType>();
        if (exceptions == null) return;
        Iterator<String> i$ = exceptions.iterator();
        while (i$.hasNext()) {
            String exception = i$.next();
            this.exceptions.put(exception, domainType);
        }
    }
}
