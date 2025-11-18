/*
 * Decompiled with CFR 0.152.
 */
package org.slf4j.helpers;

import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMDCAdapter;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

public class SubstituteServiceProvider
implements SLF4JServiceProvider {
    private MDCAdapter mdcAdapter;
    private SubstituteLoggerFactory loggerFactory = new SubstituteLoggerFactory();
    private IMarkerFactory markerFactory = new BasicMarkerFactory();

    @Override
    public void initialize() {
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return this.markerFactory;
    }

    @Override
    public ILoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    public SubstituteServiceProvider() {
        this.mdcAdapter = new BasicMDCAdapter();
    }

    public String getRequesteApiVersion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MDCAdapter getMDCAdapter() {
        return this.mdcAdapter;
    }

    public SubstituteLoggerFactory getSubstituteLoggerFactory() {
        return this.loggerFactory;
    }
}
