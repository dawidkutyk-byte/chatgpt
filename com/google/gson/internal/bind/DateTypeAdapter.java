/*
 * Decompiled with CFR 0.152.
 */
package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

public final class DateTypeAdapter
extends TypeAdapter<Date> {
    private final DateFormat localFormat;
    public static final TypeAdapterFactory FACTORY = new /* Unavailable Anonymous Inner Class!! */;
    private final DateFormat enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);

    public DateTypeAdapter() {
        this.localFormat = DateFormat.getDateTimeInstance(2, 2);
    }

    @Override
    public synchronized void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        String dateFormatAsString = this.enUsFormat.format(value);
        out.value(dateFormatAsString);
    }

    private synchronized Date deserializeToDate(String json) {
        try {
            return this.localFormat.parse(json);
        }
        catch (ParseException parseException) {
            try {
                return this.enUsFormat.parse(json);
            }
            catch (ParseException parseException2) {
                try {
                    return ISO8601Utils.parse(json, new ParsePosition(0));
                }
                catch (ParseException e) {
                    throw new JsonSyntaxException(json, e);
                }
            }
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        if (in.peek() != JsonToken.NULL) return this.deserializeToDate(in.nextString());
        in.nextNull();
        return null;
    }
}
