/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.text;

import java.util.stream.Stream;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.EntityNBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.NBTComponent;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.ScopedComponent;

public interface EntityNBTComponent
extends NBTComponent<EntityNBTComponent, Builder>,
ScopedComponent<EntityNBTComponent> {
    @NotNull
    public String selector();

    @NotNull
    @Contract(pure=true)
    public EntityNBTComponent selector(@NotNull String var1);

    @NotNull
    default public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.concat(Stream.of(ExaminableProperty.of((String)"selector", (String)this.selector())), super.examinableProperties());
    }
}
