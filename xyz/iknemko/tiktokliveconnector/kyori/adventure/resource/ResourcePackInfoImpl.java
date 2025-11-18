/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.resource.ResourcePackInfo;

final class ResourcePackInfoImpl
implements ResourcePackInfo {
    private final UUID id;
    private final String hash;
    private final URI uri;

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResourcePackInfoImpl)) {
            return false;
        }
        ResourcePackInfoImpl that = (ResourcePackInfoImpl)other;
        return this.id.equals(that.id) && this.uri.equals(that.uri) && this.hash.equals(that.hash);
    }

    @NotNull
    public URI uri() {
        return this.uri;
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"id", (Object)this.id), ExaminableProperty.of((String)"uri", (Object)this.uri), ExaminableProperty.of((String)"hash", (String)this.hash));
    }

    static CompletableFuture<String> computeHash(URI uri, Executor exec) {
        CompletableFuture<String> result = new CompletableFuture<String>();
        exec.execute(() -> {
            try {
                URL url = uri.toURL();
                URLConnection conn = url.openConnection();
                conn.addRequestProperty("User-Agent", "adventure/" + ResourcePackInfoImpl.class.getPackage().getSpecificationVersion() + " (pack-fetcher)");
                try (InputStream is = conn.getInputStream();){
                    int read;
                    MessageDigest digest = MessageDigest.getInstance("SHA-1");
                    byte[] buf = new byte[8192];
                    while ((read = is.read(buf)) != -1) {
                        digest.update(buf, 0, read);
                    }
                    result.complete(ResourcePackInfoImpl.bytesToString(digest.digest()));
                }
            }
            catch (IOException | NoSuchAlgorithmException ex) {
                result.completeExceptionally(ex);
            }
        });
        return result;
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public UUID id() {
        return this.id;
    }

    @NotNull
    public String hash() {
        return this.hash;
    }

    static String bytesToString(byte[] arr) {
        StringBuilder builder = new StringBuilder(arr.length * 2);
        Formatter fmt = new Formatter(builder, Locale.ROOT);
        int i = 0;
        while (i < arr.length) {
            fmt.format("%02x", arr[i] & 0xFF);
            ++i;
        }
        return builder.toString();
    }

    ResourcePackInfoImpl(@NotNull UUID id, @NotNull URI uri, @NotNull String hash) {
        this.id = Objects.requireNonNull(id, "id");
        this.uri = Objects.requireNonNull(uri, "uri");
        this.hash = Objects.requireNonNull(hash, "hash");
    }

    public int hashCode() {
        int result = this.id.hashCode();
        result = 31 * result + this.uri.hashCode();
        result = 31 * result + this.hash.hashCode();
        return result;
    }
}
