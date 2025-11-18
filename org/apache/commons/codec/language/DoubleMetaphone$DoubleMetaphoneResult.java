/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

public class DoubleMetaphone.DoubleMetaphoneResult {
    private final StringBuilder alternate;
    private final StringBuilder primary;
    private final int maxLength;

    public DoubleMetaphone.DoubleMetaphoneResult(int maxLength) {
        this.primary = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
        this.alternate = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
        this.maxLength = maxLength;
    }

    public boolean isComplete() {
        return this.primary.length() >= this.maxLength && this.alternate.length() >= this.maxLength;
    }

    public String getPrimary() {
        return this.primary.toString();
    }

    public String getAlternate() {
        return this.alternate.toString();
    }

    public void append(char primary, char alternate) {
        this.appendPrimary(primary);
        this.appendAlternate(alternate);
    }

    public void appendPrimary(char value) {
        if (this.primary.length() >= this.maxLength) return;
        this.primary.append(value);
    }

    public void append(String value) {
        this.appendPrimary(value);
        this.appendAlternate(value);
    }

    public void append(String primary, String alternate) {
        this.appendPrimary(primary);
        this.appendAlternate(alternate);
    }

    public void appendAlternate(String value) {
        int addChars = this.maxLength - this.alternate.length();
        if (value.length() <= addChars) {
            this.alternate.append(value);
        } else {
            this.alternate.append(value.substring(0, addChars));
        }
    }

    public void appendPrimary(String value) {
        int addChars = this.maxLength - this.primary.length();
        if (value.length() <= addChars) {
            this.primary.append(value);
        } else {
            this.primary.append(value.substring(0, addChars));
        }
    }

    public void appendAlternate(char value) {
        if (this.alternate.length() >= this.maxLength) return;
        this.alternate.append(value);
    }

    public void append(char value) {
        this.appendPrimary(value);
        this.appendAlternate(value);
    }
}
