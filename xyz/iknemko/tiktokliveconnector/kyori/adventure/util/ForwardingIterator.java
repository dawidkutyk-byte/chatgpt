/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public final class ForwardingIterator<T>
implements Iterable<T> {
    private final Supplier<Iterator<T>> iterator;
    private final Supplier<Spliterator<T>> spliterator;

    @Override
    @NotNull
    public Spliterator<T> spliterator() {
        return this.spliterator.get();
    }

    @Override
    @NotNull
    public Iterator<T> iterator() {
        return this.iterator.get();
    }

    public ForwardingIterator(@NotNull Supplier<Iterator<T>> iterator, @NotNull Supplier<Spliterator<T>> spliterator) {
        this.iterator = Objects.requireNonNull(iterator, "iterator");
        this.spliterator = Objects.requireNonNull(spliterator, "spliterator");
    }
}
