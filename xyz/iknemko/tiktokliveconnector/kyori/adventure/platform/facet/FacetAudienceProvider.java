/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.AudienceProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers$Type
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.identity.Identity;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.permission.PermissionChecker;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.AudienceProvider;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetPointers;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointered;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.renderer.ComponentRenderer;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.TriState;

@ApiStatus.Internal
public abstract class FacetAudienceProvider<V, A extends FacetAudience<V>>
implements AudienceProvider,
ForwardingAudience {
    protected final Map<V, A> viewers;
    protected final ComponentRenderer<Pointered> componentRenderer;
    private final Map<UUID, A> players;
    private final Audience player;
    private final Set<A> consoles;
    private final Audience console;
    private A empty;
    private volatile boolean closed;
    protected static final Locale DEFAULT_LOCALE = Locale.US;

    @NotNull
    public Iterable<? extends Audience> audiences() {
        return this.viewers.values();
    }

    public void removeViewer(@NotNull V viewer) {
        FacetAudience audience = (FacetAudience)this.viewers.remove(viewer);
        if (audience == null) {
            return;
        }
        FacetPointers.Type type = (FacetPointers.Type)audience.getOrDefault(FacetPointers.TYPE, (Object)FacetPointers.Type.OTHER);
        if (type == FacetPointers.Type.PLAYER) {
            @Nullable UUID id = (UUID)audience.getOrDefault(Identity.UUID, null);
            if (id != null) {
                this.players.remove(id);
            }
        } else if (type == FacetPointers.Type.CONSOLE) {
            this.consoles.remove(audience);
        }
        audience.close();
    }

    @NotNull
    public Audience all() {
        return this;
    }

    static /* synthetic */ Set access$000(FacetAudienceProvider x0) {
        return x0.consoles;
    }

    @NotNull
    public Audience world(@NotNull Key world) {
        return this.filterPointers(pointers -> world.equals(pointers.getOrDefault(FacetPointers.WORLD, null)));
    }

    protected FacetAudienceProvider(@NotNull ComponentRenderer<Pointered> componentRenderer) {
        this.componentRenderer = Objects.requireNonNull(componentRenderer, "component renderer");
        this.viewers = new ConcurrentHashMap<V, A>();
        this.players = new ConcurrentHashMap<UUID, A>();
        this.consoles = new CopyOnWriteArraySet<A>();
        this.console = new /* Unavailable Anonymous Inner Class!! */;
        this.player = Audience.audience(this.players.values());
        this.closed = false;
    }

    public void close() {
        this.closed = true;
        Iterator<V> iterator = this.viewers.keySet().iterator();
        while (iterator.hasNext()) {
            V viewer = iterator.next();
            this.removeViewer(viewer);
        }
    }

    @NotNull
    public Audience filter(@NotNull Predicate<V> predicate) {
        return Audience.audience(FacetAudienceProvider.filter(this.viewers.entrySet(), entry -> predicate.test(entry.getKey()), Map.Entry::getValue));
    }

    @NotNull
    private static <T, V> Iterable<V> filter(@NotNull Iterable<T> input, @NotNull Predicate<T> filter, @NotNull Function<T, V> transformer) {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @NotNull
    protected abstract A createAudience(@NotNull Collection<V> var1);

    public void refreshViewer(@NotNull V viewer) {
        FacetAudience audience = (FacetAudience)this.viewers.get(viewer);
        if (audience == null) return;
        audience.refresh();
    }

    @NotNull
    public Audience player(@NotNull UUID playerId) {
        return (Audience)this.players.getOrDefault(playerId, this.empty());
    }

    @NotNull
    public Audience server(@NotNull String serverName) {
        return this.filterPointers(pointers -> serverName.equals(pointers.getOrDefault(FacetPointers.SERVER, null)));
    }

    @NotNull
    private A empty() {
        if (this.empty != null) return this.empty;
        this.empty = this.createAudience(Collections.emptyList());
        return this.empty;
    }

    @NotNull
    public Audience permission(@NotNull String permission) {
        return this.filterPointers(pointers -> pointers.get(PermissionChecker.POINTER).orElse(PermissionChecker.always((TriState)TriState.FALSE)).test(permission));
    }

    @NotNull
    public Audience players() {
        return this.player;
    }

    public void addViewer(@NotNull V viewer) {
        if (this.closed) {
            return;
        }
        FacetAudience audience = this.viewers.computeIfAbsent((FacetAudience)Objects.requireNonNull(viewer, "viewer"), (Function<FacetAudience, A>)((Function<Object, FacetAudience>)v -> this.createAudience(Collections.singletonList(v))));
        FacetPointers.Type type = (FacetPointers.Type)audience.getOrDefault(FacetPointers.TYPE, (Object)FacetPointers.Type.OTHER);
        if (type == FacetPointers.Type.PLAYER) {
            @Nullable UUID id = (UUID)audience.getOrDefault(Identity.UUID, null);
            if (id == null) return;
            this.players.putIfAbsent(id, audience);
        } else {
            if (type != FacetPointers.Type.CONSOLE) return;
            this.consoles.add(audience);
        }
    }

    @NotNull
    private Audience filterPointers(@NotNull Predicate<Pointered> predicate) {
        return Audience.audience(FacetAudienceProvider.filter(this.viewers.entrySet(), entry -> predicate.test((Pointered)entry.getValue()), Map.Entry::getValue));
    }

    @NotNull
    public Audience console() {
        return this.console;
    }
}
