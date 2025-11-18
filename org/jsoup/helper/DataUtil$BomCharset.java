/*
 * Decompiled with CFR 0.152.
 */
package org.jsoup.helper;

private static class DataUtil.BomCharset {
    private final boolean offset;
    private final String charset;

    static /* synthetic */ boolean access$100(DataUtil.BomCharset x0) {
        return x0.offset;
    }

    static /* synthetic */ String access$000(DataUtil.BomCharset x0) {
        return x0.charset;
    }

    public DataUtil.BomCharset(String charset, boolean offset) {
        this.charset = charset;
        this.offset = offset;
    }
}
