/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.codec.language;

import java.util.Comparator;
import org.apache.commons.codec.language.DaitchMokotoffSoundex;

static final class DaitchMokotoffSoundex.1
implements Comparator<DaitchMokotoffSoundex.Rule> {
    DaitchMokotoffSoundex.1() {
    }

    @Override
    public int compare(DaitchMokotoffSoundex.Rule rule1, DaitchMokotoffSoundex.Rule rule2) {
        return rule2.getPatternLength() - rule1.getPatternLength();
    }
}
