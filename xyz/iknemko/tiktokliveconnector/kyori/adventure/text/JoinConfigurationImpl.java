/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfigurationImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ComponentLike;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfiguration;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.JoinConfigurationImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.TextComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.format.Style;

/*
 * Exception performing whole class analysis ignored.
 */
final class JoinConfigurationImpl
implements JoinConfiguration {
    private final Component lastSeparator;
    private final Function<ComponentLike, Component> convertor;
    private final Component prefix;
    private final Component suffix;
    static final JoinConfigurationImpl NULL;
    private final Predicate<ComponentLike> predicate;
    private final Component separator;
    static final JoinConfiguration STANDARD_NEW_LINES;
    static final Predicate<ComponentLike> DEFAULT_PREDICATE;
    static final JoinConfiguration STANDARD_COMMA_SPACE_SEPARATED;
    static final Function<ComponentLike, Component> DEFAULT_CONVERTOR;
    static final JoinConfiguration STANDARD_SPACES;
    static final JoinConfiguration STANDARD_COMMA_SEPARATED;
    private final Component lastSeparatorIfSerial;
    static final JoinConfiguration STANDARD_ARRAY_LIKE;
    private final Style rootStyle;

    @Nullable
    public Component separator() {
        return this.separator;
    }

    static /* synthetic */ Style access$1600(JoinConfigurationImpl x0) {
        return x0.rootStyle;
    }

    @NotNull
    public Style parentStyle() {
        return this.rootStyle;
    }

    static /* synthetic */ Component access$1400(JoinConfigurationImpl x0) {
        return x0.lastSeparatorIfSerial;
    }

    static /* synthetic */ Component access$1200(JoinConfigurationImpl x0) {
        return x0.suffix;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"prefix", (Object)this.prefix), ExaminableProperty.of((String)"suffix", (Object)this.suffix), ExaminableProperty.of((String)"separator", (Object)this.separator), ExaminableProperty.of((String)"lastSeparator", (Object)this.lastSeparator), ExaminableProperty.of((String)"lastSeparatorIfSerial", (Object)this.lastSeparatorIfSerial), ExaminableProperty.of((String)"convertor", this.convertor), ExaminableProperty.of((String)"predicate", this.predicate), ExaminableProperty.of((String)"rootStyle", (Object)this.rootStyle));
    }

    static /* synthetic */ Component access$1000(JoinConfigurationImpl x0) {
        return x0.lastSeparator;
    }

    static {
        DEFAULT_CONVERTOR = ComponentLike::asComponent;
        DEFAULT_PREDICATE = componentLike -> true;
        NULL = new JoinConfigurationImpl();
        STANDARD_NEW_LINES = JoinConfiguration.separator((ComponentLike)Component.newline());
        STANDARD_SPACES = JoinConfiguration.separator((ComponentLike)Component.space());
        STANDARD_COMMA_SEPARATED = JoinConfiguration.separator((ComponentLike)Component.text((String)","));
        STANDARD_COMMA_SPACE_SEPARATED = JoinConfiguration.separator((ComponentLike)Component.text((String)", "));
        STANDARD_ARRAY_LIKE = (JoinConfiguration)JoinConfiguration.builder().separator((ComponentLike)Component.text((String)", ")).prefix((ComponentLike)Component.text((String)"[")).suffix((ComponentLike)Component.text((String)"]")).build();
    }

    static /* synthetic */ Function access$1300(JoinConfigurationImpl x0) {
        return x0.convertor;
    }

    private JoinConfigurationImpl(@NotNull BuilderImpl builder) {
        this.prefix = ComponentLike.unbox((ComponentLike)BuilderImpl.access$000((BuilderImpl)builder));
        this.suffix = ComponentLike.unbox((ComponentLike)BuilderImpl.access$100((BuilderImpl)builder));
        this.separator = ComponentLike.unbox((ComponentLike)BuilderImpl.access$200((BuilderImpl)builder));
        this.lastSeparator = ComponentLike.unbox((ComponentLike)BuilderImpl.access$300((BuilderImpl)builder));
        this.lastSeparatorIfSerial = ComponentLike.unbox((ComponentLike)BuilderImpl.access$400((BuilderImpl)builder));
        this.convertor = BuilderImpl.access$500((BuilderImpl)builder);
        this.predicate = BuilderImpl.access$600((BuilderImpl)builder);
        this.rootStyle = BuilderImpl.access$700((BuilderImpl)builder);
    }

    @NotNull
    static Component singleElementJoin(@NotNull JoinConfiguration config, @Nullable ComponentLike component) {
        boolean hasRootStyle;
        Component prefix = config.prefix();
        Component suffix = config.suffix();
        Function convertor = config.convertor();
        Predicate predicate = config.predicate();
        Style rootStyle = config.parentStyle();
        boolean bl = hasRootStyle = rootStyle != Style.empty();
        if (prefix == null && suffix == null) {
            Object result = component == null || !predicate.test(component) ? Component.empty() : (Component)convertor.apply(component);
            return hasRootStyle ? ((TextComponent.Builder)((TextComponent.Builder)Component.text().style(rootStyle)).append((Component)result)).build() : result;
        }
        TextComponent.Builder builder = Component.text();
        if (prefix != null) {
            builder.append(prefix);
        }
        if (component != null && predicate.test(component)) {
            builder.append((Component)convertor.apply(component));
        }
        if (suffix == null) return hasRootStyle ? ((TextComponent.Builder)((TextComponent.Builder)Component.text().style(rootStyle)).append((ComponentBuilder)builder)).build() : builder.build();
        builder.append(suffix);
        return hasRootStyle ? ((TextComponent.Builder)((TextComponent.Builder)Component.text().style(rootStyle)).append((ComponentBuilder)builder)).build() : builder.build();
    }

    private JoinConfigurationImpl() {
        this.prefix = null;
        this.suffix = null;
        this.separator = null;
        this.lastSeparator = null;
        this.lastSeparatorIfSerial = null;
        this.convertor = DEFAULT_CONVERTOR;
        this.predicate = DEFAULT_PREDICATE;
        this.rootStyle = Style.empty();
    }

    static /* synthetic */ Component access$1100(JoinConfigurationImpl x0) {
        return x0.prefix;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Predicate<ComponentLike> predicate() {
        return this.predicate;
    }

    @NotNull
    public Function<ComponentLike, Component> convertor() {
        return this.convertor;
    }

    @Nullable
    public Component suffix() {
        return this.suffix;
    }

    public // Could not load outer class - annotation placement on inner may be incorrect
    @NotNull JoinConfiguration.Builder toBuilder() {
        return new BuilderImpl(this, null);
    }

    @Nullable
    public Component lastSeparator() {
        return this.lastSeparator;
    }

    @Contract(pure=true)
    @NotNull
    static Component join(@NotNull JoinConfiguration config, @NotNull Iterable<? extends ComponentLike> components) {
        TextComponent.Builder builder;
        Objects.requireNonNull(config, "config");
        Objects.requireNonNull(components, "components");
        Iterator<? extends ComponentLike> it = components.iterator();
        if (!it.hasNext()) {
            return JoinConfigurationImpl.singleElementJoin(config, null);
        }
        ComponentLike component = Objects.requireNonNull(it.next(), "Null elements in \"components\" are not allowed");
        int componentsSeen = 0;
        if (!it.hasNext()) {
            return JoinConfigurationImpl.singleElementJoin(config, component);
        }
        Component prefix = config.prefix();
        Component suffix = config.suffix();
        Function convertor = config.convertor();
        Predicate predicate = config.predicate();
        Style rootStyle = config.parentStyle();
        boolean hasRootStyle = rootStyle != Style.empty();
        Component separator = config.separator();
        boolean hasSeparator = separator != null;
        TextComponent.Builder builder2 = builder = hasRootStyle ? (TextComponent.Builder)Component.text().style(rootStyle) : Component.text();
        if (prefix != null) {
            builder.append(prefix);
        }
        while (component != null) {
            if (!predicate.test(component)) {
                if (!it.hasNext()) break;
                component = it.next();
                continue;
            }
            builder.append(Objects.requireNonNull((Component)convertor.apply(component), "Null output from \"convertor\" is not allowed"));
            ++componentsSeen;
            if (!it.hasNext()) {
                component = null;
                continue;
            }
            component = Objects.requireNonNull(it.next(), "Null elements in \"components\" are not allowed");
            if (it.hasNext()) {
                if (!hasSeparator) continue;
                builder.append(separator);
                continue;
            }
            Component lastSeparator = null;
            if (componentsSeen > 1) {
                lastSeparator = config.lastSeparatorIfSerial();
            }
            if (lastSeparator == null) {
                lastSeparator = config.lastSeparator();
            }
            if (lastSeparator == null) {
                lastSeparator = config.separator();
            }
            if (lastSeparator == null) continue;
            builder.append(lastSeparator);
        }
        if (suffix == null) return builder.build();
        builder.append(suffix);
        return builder.build();
    }

    @Nullable
    public Component lastSeparatorIfSerial() {
        return this.lastSeparatorIfSerial;
    }

    static /* synthetic */ Predicate access$1500(JoinConfigurationImpl x0) {
        return x0.predicate;
    }

    static /* synthetic */ Component access$900(JoinConfigurationImpl x0) {
        return x0.separator;
    }

    @Nullable
    public Component prefix() {
        return this.prefix;
    }
}
