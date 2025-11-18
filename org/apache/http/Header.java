/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HeaderElement
 *  org.apache.http.NameValuePair
 *  org.apache.http.ParseException
 */
package org.apache.http;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;

public interface Header
extends NameValuePair {
    public HeaderElement[] getElements() throws ParseException;
}
