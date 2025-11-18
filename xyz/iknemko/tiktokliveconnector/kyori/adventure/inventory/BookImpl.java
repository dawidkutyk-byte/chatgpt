/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.examination.Examinable
 *  net.kyori.examination.ExaminableProperty
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.internal.Internals;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.inventory.Book;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;

final class BookImpl
implements Book {
    private final List<Component> pages;
    private final Component author;
    private final Component title;

    @NotNull
    public Component title() {
        return this.title;
    }

    @NotNull
    public Component author() {
        return this.author;
    }

    public int hashCode() {
        int result = this.title.hashCode();
        result = 31 * result + this.author.hashCode();
        result = 31 * result + this.pages.hashCode();
        return result;
    }

    @NotNull
    public Book title(@NotNull Component title) {
        return new BookImpl(Objects.requireNonNull(title, "title"), this.author, this.pages);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookImpl)) {
            return false;
        }
        BookImpl that = (BookImpl)o;
        return this.title.equals(that.title) && this.author.equals(that.author) && this.pages.equals(that.pages);
    }

    BookImpl(@NotNull Component title, @NotNull Component author, @NotNull List<Component> pages) {
        this.title = Objects.requireNonNull(title, "title");
        this.author = Objects.requireNonNull(author, "author");
        this.pages = Collections.unmodifiableList(Objects.requireNonNull(pages, "pages"));
    }

    public String toString() {
        return Internals.toString((Examinable)this);
    }

    @NotNull
    public Book author(@NotNull Component author) {
        return new BookImpl(this.title, Objects.requireNonNull(author, "author"), this.pages);
    }

    @NotNull
    public List<Component> pages() {
        return this.pages;
    }

    @NotNull
    public Book pages(@NotNull List<Component> pages) {
        return new BookImpl(this.title, this.author, new ArrayList<Component>((Collection)Objects.requireNonNull(pages, "pages")));
    }

    @NotNull
    public Stream<? extends ExaminableProperty> examinableProperties() {
        return Stream.of(ExaminableProperty.of((String)"title", (Object)this.title), ExaminableProperty.of((String)"author", (Object)this.author), ExaminableProperty.of((String)"pages", this.pages));
    }
}
