/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

static final class TypeAdapters.27
extends TypeAdapter<Calendar> {
    private static final String MINUTE = "minute";
    private static final String SECOND = "second";
    private static final String DAY_OF_MONTH = "dayOfMonth";
    private static final String HOUR_OF_DAY = "hourOfDay";
    private static final String MONTH = "month";
    private static final String YEAR = "year";

    @Override
    public void write(JsonWriter out, Calendar value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginObject();
        out.name(YEAR);
        out.value(value.get(1));
        out.name(MONTH);
        out.value(value.get(2));
        out.name(DAY_OF_MONTH);
        out.value(value.get(5));
        out.name(HOUR_OF_DAY);
        out.value(value.get(11));
        out.name(MINUTE);
        out.value(value.get(12));
        out.name(SECOND);
        out.value(value.get(13));
        out.endObject();
    }

    TypeAdapters.27() {
    }

    @Override
    public Calendar read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        in.beginObject();
        int year = 0;
        int month = 0;
        int dayOfMonth = 0;
        int hourOfDay = 0;
        int minute = 0;
        int second = 0;
        while (true) {
            if (in.peek() == JsonToken.END_OBJECT) {
                in.endObject();
                return new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
            }
            String name = in.nextName();
            int value = in.nextInt();
            if (YEAR.equals(name)) {
                year = value;
                continue;
            }
            if (MONTH.equals(name)) {
                month = value;
                continue;
            }
            if (DAY_OF_MONTH.equals(name)) {
                dayOfMonth = value;
                continue;
            }
            if (HOUR_OF_DAY.equals(name)) {
                hourOfDay = value;
                continue;
            }
            if (MINUTE.equals(name)) {
                minute = value;
                continue;
            }
            if (!SECOND.equals(name)) continue;
            second = value;
        }
    }
}
