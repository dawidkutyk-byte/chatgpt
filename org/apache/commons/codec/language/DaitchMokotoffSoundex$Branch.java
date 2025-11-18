/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

private static final class DaitchMokotoffSoundex.Branch {
    private final StringBuilder builder = new StringBuilder();
    private String cachedString = null;
    private String lastReplacement = null;

    public int hashCode() {
        return this.toString().hashCode();
    }

    public void processNextReplacement(String replacement, boolean forceAppend) {
        boolean append;
        boolean bl = append = this.lastReplacement == null || !this.lastReplacement.endsWith(replacement) || forceAppend;
        if (append && this.builder.length() < 6) {
            this.builder.append(replacement);
            if (this.builder.length() > 6) {
                this.builder.delete(6, this.builder.length());
            }
            this.cachedString = null;
        }
        this.lastReplacement = replacement;
    }

    public String toString() {
        if (this.cachedString != null) return this.cachedString;
        this.cachedString = this.builder.toString();
        return this.cachedString;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof DaitchMokotoffSoundex.Branch) return this.toString().equals(((DaitchMokotoffSoundex.Branch)other).toString());
        return false;
    }

    public DaitchMokotoffSoundex.Branch createBranch() {
        DaitchMokotoffSoundex.Branch branch = new DaitchMokotoffSoundex.Branch();
        branch.builder.append(this.toString());
        branch.lastReplacement = this.lastReplacement;
        return branch;
    }

    private DaitchMokotoffSoundex.Branch() {
    }

    public void finish() {
        while (this.builder.length() < 6) {
            this.builder.append('0');
            this.cachedString = null;
        }
    }
}
