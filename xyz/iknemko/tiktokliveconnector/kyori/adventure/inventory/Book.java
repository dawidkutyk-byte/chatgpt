/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.BookImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.BookImpl$BuilderImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.kyori.examination.Examinable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.BookImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.util.Buildable;

@ApiStatus.NonExtendable
public interface Book
extends Buildable<Book, Builder>,
Examinable {
    @NotNull
    public Component author();

    @NotNull
    public static Book book(@NotNull Component title, @NotNull Component author, Component ... pages) {
        return Book.book(title, author, Arrays.asList(pages));
    }

    @NotNull
    @Contract(value="_ -> new", pure=true)
    public Book author(@NotNull Component var1);

    @NotNull
    public static Builder builder() {
        return new BookImpl.BuilderImpl();
    }

    @NotNull
    public Component title();

    @Contract(value="_ -> new", pure=true)
    @NotNull
    public Book pages(@NotNull List<Component> var1);

    @NotNull
    public static Book book(@NotNull Component title, @NotNull Component author, @NotNull Collection<Component> pages) {
        return new BookImpl(title, author, new ArrayList<Component>(pages));
    }

    @NotNull
    public @Unmodifiable List<Component> pages();

    @NotNull
    default public Builder toBuilder() {
        return Book.builder().title(this.title()).author(this.author()).pages(this.pages());
    }

    @Contract(value="_ -> new", pure=true)
    @NotNull
    default public Book pages(Component ... pages) {
        return this.pages(Arrays.asList(pages));
    }

    @NotNull
    @Contract(value="_ -> new", pure=true)
    public Book title(@NotNull Component var1);
}
