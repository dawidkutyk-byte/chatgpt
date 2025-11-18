/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable$Builder
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory;

import java.util.Collection;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.builder.AbstractBuilder;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

public static interface Book.Builder
extends AbstractBuilder<Book>,
Buildable.Builder<Book> {
    @NotNull
    @Contract(value="_ -> this")
    public Book.Builder pages(@NotNull Collection<Component> var1);

    @NotNull
    @Contract(value="_ -> this")
    public Book.Builder title(@NotNull Component var1);

    @Contract(value="_ -> this")
    @NotNull
    public Book.Builder pages(Component ... var1);

    @NotNull
    @Contract(value="_ -> this")
    public Book.Builder addPage(@NotNull Component var1);

    @NotNull
    public Book build();

    @Contract(value="_ -> this")
    @NotNull
    public Book.Builder author(@NotNull Component var1);
}
