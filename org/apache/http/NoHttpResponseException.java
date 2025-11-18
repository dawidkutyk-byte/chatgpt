/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpException
 */
package org.apache.http;

import java.io.IOException;
import org.apache.http.HttpException;

public class NoHttpResponseException
extends IOException {
    private static final long serialVersionUID = -7658940387386078766L;

    public NoHttpResponseException(String message) {
        super(HttpException.clean((String)message));
    }
}
