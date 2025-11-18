/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource
 */
package xyz.iknemko.tiktokliveconnector.kyori.option.value;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.value.ValueSource;

static final class ValueSources.EnvironmentVariable
implements ValueSource {
    private static final Pattern ENVIRONMENT_SUBST_PATTERN = Pattern.compile("[:\\-/]");
    private static final String ENVIRONMENT_VAR_SEPARATOR = "_";
    private final String prefix;

    public <T> @7\u015aCz T value(Option<T> option) {
        StringBuffer buf = new StringBuffer(option.id().length() + this.prefix.length());
        buf.append(this.prefix);
        Matcher match = ENVIRONMENT_SUBST_PATTERN.matcher(option.id());
        while (true) {
            if (!match.find()) {
                match.appendTail(buf);
                String value = System.getenv(buf.toString().toUpperCase(Locale.ROOT));
                if (value != null) return (T)option.valueType().parse(value);
                return null;
            }
            match.appendReplacement(buf, ENVIRONMENT_VAR_SEPARATOR);
        }
    }

    ValueSources.EnvironmentVariable(String prefix) {
        this.prefix = prefix.isEmpty() ? "" : prefix.toUpperCase(Locale.ROOT) + ENVIRONMENT_VAR_SEPARATOR;
    }
}
