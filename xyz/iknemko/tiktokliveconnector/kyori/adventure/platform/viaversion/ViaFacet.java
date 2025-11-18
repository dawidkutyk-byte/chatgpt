/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.Via
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet$Message
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.viaversion;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Facet;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetBase;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.Knob;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class ViaFacet<V>
extends FacetBase<V>
implements Facet.Message<V, String> {
    private final Function<V, UserConnection> connectionFunction;
    private static final String PACKAGE = "com.viaversion.viaversion";
    private final int minProtocol;
    private static final boolean SUPPORTED;
    private static final int SUPPORTED_VIA_MAJOR_VERSION = 4;

    @Nullable
    public UserConnection findConnection(@NotNull V viewer) {
        return this.connectionFunction.apply(viewer);
    }

    @NotNull
    public String createMessage(@NotNull V viewer, @NotNull Component message) {
        int protocol = this.findProtocol(viewer);
        if (protocol < 713) return (String)GsonComponentSerializer.colorDownsamplingGson().serialize(message);
        return (String)GsonComponentSerializer.gson().serialize(message);
    }

    public boolean isSupported() {
        return super.isSupported() && SUPPORTED && this.connectionFunction != null && this.minProtocol >= 0;
    }

    public int findProtocol(@NotNull V viewer) {
        UserConnection connection = this.findConnection(viewer);
        if (connection == null) return -1;
        return connection.getProtocolInfo().getProtocolVersion();
    }

    public boolean isApplicable(@NotNull V viewer) {
        return super.isApplicable(viewer) && this.minProtocol > Via.getAPI().getServerVersion().lowestSupportedVersion() && this.findProtocol(viewer) >= this.minProtocol;
    }

    public ViaFacet(@NotNull Class<? extends V> viewerClass, @NotNull Function<V, UserConnection> connectionFunction, int minProtocol) {
        super(viewerClass);
        this.connectionFunction = connectionFunction;
        this.minProtocol = minProtocol;
    }

    static {
        boolean supported = false;
        try {
            Class.forName("com.viaversion.viaversion.api.ViaAPI").getDeclaredMethod("majorVersion", new Class[0]);
            supported = Via.getAPI().majorVersion() == 4;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        SUPPORTED = supported && Knob.isEnabled((String)"viaversion", (boolean)true);
    }
}
