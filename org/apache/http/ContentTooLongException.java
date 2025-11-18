/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpException
 */
package org.apache.http;

import java.io.IOException;
import org.apache.http.HttpException;

public class ContentTooLongException
extends IOException {
    private static final long serialVersionUID = -924287689552495383L;

    public ContentTooLongException(String format, Object ... args) {
        super(HttpException.clean((String)String.format(format, args)));
    }

    public ContentTooLongException(String message) {
        super(message);
    }
}
