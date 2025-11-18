/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

public class NOPServiceProvider
implements SLF4JServiceProvider {
    private ILoggerFactory loggerFactory = new NOPLoggerFactory();
    private MDCAdapter mdcAdapter;
    private IMarkerFactory markerFactory = new BasicMarkerFactory();
    public static String REQUESTED_API_VERSION = "1.8.99";

    @Override
    public void initialize() {
    }

    @Override
    public ILoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return this.markerFactory;
    }

    public NOPServiceProvider() {
        this.mdcAdapter = new NOPMDCAdapter();
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return this.mdcAdapter;
    }

    public String getRequesteApiVersion() {
        return REQUESTED_API_VERSION;
    }
}
