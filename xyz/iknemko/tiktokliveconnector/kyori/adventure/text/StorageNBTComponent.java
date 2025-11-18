/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.key.Key;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.StorageNBTComponent;

public interface StorageNBTComponent
extends NBTComponent<StorageNBTComponent, Builder>,
ScopedComponent<StorageNBTComponent> {
    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(Stream.of(ExaminableProperty.of((String)"storage", (Object)this.storage())), super.examinableProperties());
    }

    @NotNull
    public Key storage();

    @NotNull
    @Contract(pure=true)
    public StorageNBTComponent storage(@NotNull Key var1);
}
