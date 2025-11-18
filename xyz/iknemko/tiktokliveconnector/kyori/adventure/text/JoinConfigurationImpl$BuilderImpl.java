/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfigurationImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfigurationImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
static final class JoinConfigurationImpl.BuilderImpl
implements JoinConfiguration.Builder {
    private ComponentLike prefix;
    private Function<ComponentLike, Component> convertor;
    private Predicate<ComponentLike> predicate;
    private ComponentLike suffix;
    private Style rootStyle;
    private ComponentLike lastSeparator;
    private ComponentLike separator;
    private ComponentLike lastSeparatorIfSerial;

    @NotNull
    public JoinConfiguration.Builder predicate(@NotNull Predicate<ComponentLike> predicate) {
        this.predicate = Objects.requireNonNull(predicate, "predicate");
        return this;
    }

    @NotNull
    public JoinConfiguration build() {
        return new JoinConfigurationImpl(this, null);
    }

    @NotNull
    public JoinConfiguration.Builder prefix(@Nullable ComponentLike prefix) {
        this.prefix = prefix;
        return this;
    }

    static /* synthetic */ ComponentLike access$000(JoinConfigurationImpl.BuilderImpl x0) {
        return x0.prefix;
    }

    static /* synthetic */ ComponentLike access$400(JoinConfigurationImpl.BuilderImpl x0) {
        return x0.lastSeparatorIfSerial;
    }

    @NotNull
    public JoinConfiguration.Builder suffix(@Nullable ComponentLike suffix) {
        this.suffix = suffix;
        return this;
    }

    @NotNull
    public JoinConfiguration.Builder lastSeparatorIfSerial(@Nullable ComponentLike lastSeparatorIfSerial) {
        this.lastSeparatorIfSerial = lastSeparatorIfSerial;
        return this;
    }

    static /* synthetic */ ComponentLike access$300(JoinConfigurationImpl.BuilderImpl x0) {
        return x0.lastSeparator;
    }

    private JoinConfigurationImpl.BuilderImpl(@NotNull JoinConfigurationImpl joinConfig) {
        this.separator = JoinConfigurationImpl.access$900((JoinConfigurationImpl)joinConfig);
        this.lastSeparator = JoinConfigurationImpl.access$1000((JoinConfigurationImpl)joinConfig);
        this.prefix = JoinConfigurationImpl.access$1100((JoinConfigurationImpl)joinConfig);
        this.suffix = JoinConfigurationImpl.access$1200((JoinConfigurationImpl)joinConfig);
        this.convertor = JoinConfigurationImpl.access$1300((JoinConfigurationImpl)joinConfig);
        this.lastSeparatorIfSerial = JoinConfigurationImpl.access$1400((JoinConfigurationImpl)joinConfig);
        this.predicate = JoinConfigurationImpl.access$1500((JoinConfigurationImpl)joinConfig);
        this.rootStyle = JoinConfigurationImpl.access$1600((JoinConfigurationImpl)joinConfig);
    }

    static /* synthetic */ Predicate access$600(JoinConfigurationImpl.BuilderImpl x0) {
        return x0.predicate;
    }

    static /* synthetic */ Function access$500(JoinConfigurationImpl.BuilderImpl x0) {
        return x0.convertor;
    }

    JoinConfigurationImpl.BuilderImpl() {
        this(JoinConfigurationImpl.NULL);
    }

    @NotNull
    public JoinConfiguration.Builder separator(@Nullable ComponentLike separator) {
        this.separator = separator;
        return this;
    }

    static /* synthetic */ Style access$700(JoinConfigurationImpl.BuilderImpl x0) {
        return x0.rootStyle;
    }

    @NotNull
    public JoinConfiguration.Builder lastSeparator(@Nullable ComponentLike lastSeparator) {
        this.lastSeparator = lastSeparator;
        return this;
    }

    @NotNull
    public JoinConfiguration.Builder parentStyle(@NotNull Style parentStyle) {
        this.rootStyle = Objects.requireNonNull(parentStyle, "rootStyle");
        return this;
    }

    static /* synthetic */ ComponentLike access$200(JoinConfigurationImpl.BuilderImpl x0) {
        return x0.separator;
    }

    static /* synthetic */ ComponentLike access$100(JoinConfigurationImpl.BuilderImpl x0) {
        return x0.suffix;
    }

    @NotNull
    public JoinConfiguration.Builder convertor(@NotNull Function<ComponentLike, Component> convertor) {
        this.convertor = Objects.requireNonNull(convertor, "convertor");
        return this;
    }
}
