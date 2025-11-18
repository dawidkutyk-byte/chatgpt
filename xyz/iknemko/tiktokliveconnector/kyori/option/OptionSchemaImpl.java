/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  7\u015aCz
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$VersionedBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl$VersionedBuilderImpl
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionStateImpl;

final class OptionSchemaImpl
implements OptionSchema {
    final ConcurrentMap<String, Option<?>> options = new ConcurrentHashMap();
    final OptionState emptyState;

    public OptionState emptyState() {
        return this.emptyState;
    }

    OptionSchemaImpl(@7\u015aCz OptionSchemaImpl parent) {
        if (parent != null) {
            this.options.putAll(parent.options);
        }
        this.emptyState = new OptionStateImpl((OptionSchema)this, new IdentityHashMap());
    }

    public String toString() {
        return "OptionSchemaImpl{options=" + this.options + '}';
    }

    public OptionState.Builder stateBuilder() {
        return new OptionStateImpl.BuilderImpl((OptionSchema)this);
    }

    public boolean has(Option<?> option) {
        Option own = (Option)this.options.get(option.id());
        return own != null && own.equals(option);
    }

    public OptionState.VersionedBuilder versionedStateBuilder() {
        return new OptionStateImpl.VersionedBuilderImpl((OptionSchema)this);
    }

    public Set<Option<?>> knownOptions() {
        return Collections.unmodifiableSet(new HashSet(this.options.values()));
    }
}
