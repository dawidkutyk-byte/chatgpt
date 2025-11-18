/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.internal;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.minimessage.tag.TagPattern;

@ApiStatus.Internal
public final class TagInternals {
    @RegExp
    public static final String TAG_NAME_REGEX = "[!?#]?[a-z0-9_-]*";
    private static final Pattern TAG_NAME_PATTERN = Pattern.compile("[!?#]?[a-z0-9_-]*");

    public static void assertValidTagName(@TagPattern @NotNull String tagName) {
        if (TAG_NAME_PATTERN.matcher(Objects.requireNonNull(tagName)).matches()) return;
        throw new IllegalArgumentException("Tag name must match pattern " + TAG_NAME_PATTERN.pattern() + ", was " + tagName);
    }

    public static void sanitizeAndAssertValidTagName(@TagPattern @NotNull String tagName) {
        TagInternals.assertValidTagName(Objects.requireNonNull(tagName).toLowerCase(Locale.ROOT));
    }

    private TagInternals() {
    }

    public static boolean sanitizeAndCheckValidTagName(@TagPattern @NotNull String tagName) {
        return TAG_NAME_PATTERN.matcher(Objects.requireNonNull(tagName).toLowerCase(Locale.ROOT)).matches();
    }
}
