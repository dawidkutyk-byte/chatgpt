/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.client.utils.DateUtils$DateFormatHolder
 *  org.apache.http.util.Args
 */
package org.apache.http.client.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.util.Args;

/*
 * Exception performing whole class analysis ignored.
 */
public final class DateUtils {
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";
    public static final String PATTERN_RFC1036 = "EEE, dd-MMM-yy HH:mm:ss zzz";
    private static final Date DEFAULT_TWO_DIGIT_YEAR_START;
    private static final String[] DEFAULT_PATTERNS;
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    public static final TimeZone GMT;

    public static String formatDate(Date date) {
        return DateUtils.formatDate(date, "EEE, dd MMM yyyy HH:mm:ss zzz");
    }

    public static String formatDate(Date date, String pattern) {
        Args.notNull((Object)date, (String)"Date");
        Args.notNull((Object)pattern, (String)"Pattern");
        SimpleDateFormat formatter = DateFormatHolder.formatFor((String)pattern);
        return formatter.format(date);
    }

    public static void clearThreadLocal() {
        DateFormatHolder.clearThreadLocal();
    }

    public static Date parseDate(String dateValue, String[] dateFormats) {
        return DateUtils.parseDate(dateValue, dateFormats, null);
    }

    static {
        DEFAULT_PATTERNS = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};
        GMT = TimeZone.getTimeZone("GMT");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(GMT);
        calendar.set(2000, 0, 1, 0, 0, 0);
        calendar.set(14, 0);
        DEFAULT_TWO_DIGIT_YEAR_START = calendar.getTime();
    }

    public static Date parseDate(String dateValue) {
        return DateUtils.parseDate(dateValue, null, null);
    }

    public static Date parseDate(String dateValue, String[] dateFormats, Date startDate) {
        Args.notNull((Object)dateValue, (String)"Date value");
        String[] localDateFormats = dateFormats != null ? dateFormats : DEFAULT_PATTERNS;
        Date localStartDate = startDate != null ? startDate : DEFAULT_TWO_DIGIT_YEAR_START;
        String v = dateValue;
        if (v.length() > 1 && v.startsWith("'") && v.endsWith("'")) {
            v = v.substring(1, v.length() - 1);
        }
        String[] arr$ = localDateFormats;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String dateFormat = arr$[i$];
            SimpleDateFormat dateParser = DateFormatHolder.formatFor((String)dateFormat);
            dateParser.set2DigitYearStart(localStartDate);
            ParsePosition pos = new ParsePosition(0);
            Date result = dateParser.parse(v, pos);
            if (pos.getIndex() != 0) {
                return result;
            }
            ++i$;
        }
        return null;
    }

    private DateUtils() {
    }
}
