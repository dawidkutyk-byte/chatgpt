/*
 * Decompiled with CFR 0.152.
 */
package org.apache.http.client.utils;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

static final class DateUtils.DateFormatHolder {
    private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal();

    public static SimpleDateFormat formatFor(String pattern) {
        SimpleDateFormat format;
        Map<String, SimpleDateFormat> formats;
        SoftReference<Map<String, SimpleDateFormat>> ref = THREADLOCAL_FORMATS.get();
        Map<String, SimpleDateFormat> map = formats = ref == null ? null : ref.get();
        if (formats == null) {
            formats = new HashMap<String, SimpleDateFormat>();
            THREADLOCAL_FORMATS.set(new SoftReference<Map<String, SimpleDateFormat>>(formats));
        }
        if ((format = formats.get(pattern)) != null) return format;
        format = new SimpleDateFormat(pattern, Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        formats.put(pattern, format);
        return format;
    }

    public static void clearThreadLocal() {
        THREADLOCAL_FORMATS.remove();
    }

    DateUtils.DateFormatHolder() {
    }
}
