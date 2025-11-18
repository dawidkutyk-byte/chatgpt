/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book$Builder
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.BookImpl
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.BookImpl;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

static final class BookImpl.BuilderImpl
implements Book.Builder {
    private Component title = Component.empty();
    private final List<Component> pages;
    private Component author = Component.empty();

    BookImpl.BuilderImpl() {
        this.pages = new ArrayList<Component>();
    }

    @NotNull
    public Book.Builder author(@NotNull Component author) {
        this.author = Objects.requireNonNull(author, "author");
        return this;
    }

    @NotNull
    public Book.Builder addPage(@NotNull Component page) {
        this.pages.add(Objects.requireNonNull(page, "page"));
        return this;
    }

    @NotNull
    public Book.Builder pages(@NotNull Collection<Component> pages) {
        this.pages.addAll(Objects.requireNonNull(pages, "pages"));
        return this;
    }

    @NotNull
    public Book build() {
        return new BookImpl(this.title, this.author, new ArrayList<Component>(this.pages));
    }

    @NotNull
    public Book.Builder pages(Component ... pages) {
        Collections.addAll(this.pages, pages);
        return this;
    }

    @NotNull
    public Book.Builder title(@NotNull Component title) {
        this.title = Objects.requireNonNull(title, "title");
        return this;
    }
}
