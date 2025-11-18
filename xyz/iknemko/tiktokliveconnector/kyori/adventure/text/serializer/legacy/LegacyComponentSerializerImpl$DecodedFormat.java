/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl$FormatCodeType
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy;

import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.TextFormat;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.legacy.LegacyComponentSerializerImpl;

static final class LegacyComponentSerializerImpl.DecodedFormat {
    final TextFormat format;
    final LegacyComponentSerializerImpl.FormatCodeType encodedFormat;

    private LegacyComponentSerializerImpl.DecodedFormat(LegacyComponentSerializerImpl.FormatCodeType encodedFormat, TextFormat format) {
        if (format == null) {
            throw new IllegalStateException("No format found");
        }
        this.encodedFormat = encodedFormat;
        this.format = format;
    }
}
