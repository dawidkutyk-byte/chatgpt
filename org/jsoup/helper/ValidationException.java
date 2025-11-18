/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jsoup.helper.Validate
 */
package org.jsoup.helper;

import java.util.ArrayList;
import org.jsoup.helper.Validate;

public class ValidationException
extends IllegalArgumentException {
    public static final String Validator = Validate.class.getName();

    @Override
    public synchronized Throwable fillInStackTrace() {
        super.fillInStackTrace();
        StackTraceElement[] stackTrace = this.getStackTrace();
        ArrayList<StackTraceElement> filteredTrace = new ArrayList<StackTraceElement>();
        StackTraceElement[] stackTraceElementArray = stackTrace;
        int n = stackTraceElementArray.length;
        int n2 = 0;
        while (true) {
            if (n2 >= n) {
                this.setStackTrace(filteredTrace.toArray(new StackTraceElement[0]));
                return this;
            }
            StackTraceElement trace = stackTraceElementArray[n2];
            if (!trace.getClassName().equals(Validator)) {
                filteredTrace.add(trace);
            }
            ++n2;
        }
    }

    public ValidationException(String msg) {
        super(msg);
    }
}
