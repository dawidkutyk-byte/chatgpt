/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource;

static final class ValueSources.SystemProperty
implements ValueSource {
    private static final String SYSTEM_PROPERTY_SEPARATOR = ".";
    private final String prefix;
    private static final Pattern SYSTEM_PROP_SUBST_PATTERN = Pattern.compile("[:/]");

    public <T> @7\u015aCz T value(Option<T> option) {
        StringBuffer buf = new StringBuffer(option.id().length() + this.prefix.length());
        buf.append(this.prefix);
        Matcher match = SYSTEM_PROP_SUBST_PATTERN.matcher(option.id());
        while (true) {
            if (!match.find()) {
                match.appendTail(buf);
                String value = System.getProperty(buf.toString());
                if (value != null) return (T)option.valueType().parse(value);
                return null;
            }
            match.appendReplacement(buf, SYSTEM_PROPERTY_SEPARATOR);
        }
    }

    ValueSources.SystemProperty(String prefix) {
        this.prefix = prefix.isEmpty() ? "" : prefix + SYSTEM_PROPERTY_SEPARATOR;
    }
}
