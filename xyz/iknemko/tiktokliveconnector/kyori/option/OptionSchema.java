/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.option.Option
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema$Mutable
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchemaImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchemaImpl$Instances
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchemaImpl$MutableImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.option.OptionState$VersionedBuilder
 */
package xyz.iknemko.tiktokliveconnector.kyori.option;

import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.ApiStatus;
import xyz.iknemko.tiktokliveconnector.kyori.option.Option;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchema;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionSchemaImpl;
import xyz.iknemko.tiktokliveconnector.kyori.option.OptionState;

@ApiStatus.NonExtendable
public interface OptionSchema {
    public OptionState.VersionedBuilder versionedStateBuilder();

    public static Mutable childSchema(OptionSchema schema) {
        OptionSchemaImpl impl = schema instanceof OptionSchemaImpl.MutableImpl ? (OptionSchemaImpl)((Mutable)schema).frozenView() : (OptionSchemaImpl)schema;
        return new OptionSchemaImpl.MutableImpl(new OptionSchemaImpl(Objects.requireNonNull(impl, "impl")));
    }

    public static Mutable emptySchema() {
        return new OptionSchemaImpl.MutableImpl(new OptionSchemaImpl(null));
    }

    public static Mutable globalSchema() {
        return OptionSchemaImpl.Instances.GLOBAL;
    }

    public Set<Option<?>> knownOptions();

    public OptionState emptyState();

    public boolean has(Option<?> var1);

    public OptionState.Builder stateBuilder();
}
