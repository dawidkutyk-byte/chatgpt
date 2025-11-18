/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Versioned
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

static final class OptionStateImpl.VersionedImpl
implements OptionState.Versioned {
    private final SortedMap<Integer, OptionState> sets;
    private final int targetVersion;
    private final OptionState filtered;
    private final OptionSchema schema;

    public String toString() {
        return this.getClass().getSimpleName() + "{schema=" + this.schema + ", sets=" + this.sets + ", targetVersion=" + this.targetVersion + ", filtered=" + this.filtered + '}';
    }

    OptionStateImpl.VersionedImpl(OptionSchema schema, SortedMap<Integer, OptionState> sets, int targetVersion, OptionState filtered) {
        this.schema = schema;
        this.sets = sets;
        this.targetVersion = targetVersion;
        this.filtered = filtered;
    }

    public int hashCode() {
        return Objects.hash(this.schema, this.sets, this.targetVersion, this.filtered);
    }

    public Map<Integer, OptionState> childStates() {
        return Collections.unmodifiableSortedMap(this.sets.headMap(this.targetVersion + 1));
    }

    public boolean equals(@7\u015aCz Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) return false;
        if (this.getClass() != other.getClass()) {
            return false;
        }
        OptionStateImpl.VersionedImpl that = (OptionStateImpl.VersionedImpl)other;
        return this.targetVersion == that.targetVersion && Objects.equals(this.schema, that.schema) && Objects.equals(this.sets, that.sets) && Objects.equals(this.filtered, that.filtered);
    }

    public static OptionState flattened(OptionSchema schema, SortedMap<Integer, OptionState> versions, int targetVersion) {
        SortedMap<Integer, OptionState> applicable = versions.headMap(targetVersion + 1);
        OptionState.Builder builder = schema.stateBuilder();
        Iterator iterator = applicable.values().iterator();
        while (iterator.hasNext()) {
            OptionState child = (OptionState)iterator.next();
            builder.values(child);
        }
        return builder.build();
    }

    public <V> @7\u015aCz V value(Option<V> option) {
        return (V)this.filtered.value(option);
    }

    static /* synthetic */ OptionState access$100(OptionStateImpl.VersionedImpl x0) {
        return x0.filtered;
    }

    public OptionSchema schema() {
        return this.schema;
    }

    public OptionState.Versioned at(int version) {
        return new OptionStateImpl.VersionedImpl(this.schema, this.sets, version, OptionStateImpl.VersionedImpl.flattened(this.schema, this.sets, version));
    }

    public boolean has(Option<?> option) {
        return this.filtered.has(option);
    }
}
