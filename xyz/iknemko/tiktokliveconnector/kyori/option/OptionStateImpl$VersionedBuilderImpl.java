/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Versioned
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$VersionedBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl$VersionedImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Consumer;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl;

/*
 * Exception performing whole class analysis ignored.
 */
static final class OptionStateImpl.VersionedBuilderImpl
implements OptionState.VersionedBuilder {
    private final Map<Integer, OptionStateImpl.BuilderImpl> builders = new TreeMap<Integer, OptionStateImpl.BuilderImpl>();
    private final OptionSchema schema;

    OptionStateImpl.VersionedBuilderImpl(OptionSchema schema) {
        this.schema = schema;
    }

    public OptionState.Versioned build() {
        if (this.builders.isEmpty()) {
            return new OptionStateImpl.VersionedImpl(this.schema, Collections.emptySortedMap(), 0, this.schema.emptyState());
        }
        TreeMap<Integer, OptionState> built = new TreeMap<Integer, OptionState>();
        Iterator<Map.Entry<Integer, OptionStateImpl.BuilderImpl>> iterator = this.builders.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, OptionStateImpl.BuilderImpl> entry = iterator.next();
            built.put(entry.getKey(), entry.getValue().build());
        }
        return new OptionStateImpl.VersionedImpl(this.schema, built, ((Integer)built.lastKey()).intValue(), OptionStateImpl.VersionedImpl.flattened((OptionSchema)this.schema, built, (int)((Integer)built.lastKey())));
    }

    public OptionState.VersionedBuilder version(int version, Consumer<OptionState.Builder> versionBuilder) {
        Objects.requireNonNull(versionBuilder, "versionBuilder").accept((OptionState.Builder)this.builders.computeIfAbsent(version, $ -> new OptionStateImpl.BuilderImpl(this.schema)));
        return this;
    }
}
