/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

public final class MonkeyBars {
    @NotNull
    public static <I, O> List<O> toUnmodifiableList(@NotNull Function<I, O> mapper, @NotNull Iterable<? extends I> source) {
        ArrayList<O> ret = source instanceof Collection ? new ArrayList<O>(((Collection)source).size()) : new ArrayList();
        Iterator<I> iterator = source.iterator();
        while (iterator.hasNext()) {
            I el = iterator.next();
            ret.add(Objects.requireNonNull(mapper.apply(Objects.requireNonNull(el, "source[?]")), "mapper(source[?])"));
        }
        return Collections.unmodifiableList(ret);
    }

    private MonkeyBars() {
    }

    @SafeVarargs
    @NotNull
    public static <E extends Enum<E>> Set<E> enumSet(Class<E> type, E ... constants) {
        EnumSet<E> set = EnumSet.noneOf(type);
        Collections.addAll(set, constants);
        return Collections.unmodifiableSet(set);
    }

    @SafeVarargs
    @NotNull
    public static <I, O> List<O> nonEmptyArrayToList(@NotNull Function<I, O> mapper, @NotNull I first, I ... others) {
        ArrayList<O> ret = new ArrayList<O>(others.length + 1);
        ret.add(mapper.apply(first));
        I[] IArray = others;
        int n = IArray.length;
        int n2 = 0;
        while (n2 < n) {
            I other = IArray[n2];
            ret.add(Objects.requireNonNull(mapper.apply(Objects.requireNonNull(other, "source[?]")), "mapper(source[?])"));
            ++n2;
        }
        return Collections.unmodifiableList(ret);
    }

    @NotNull
    public static <T> List<T> addOne(@NotNull List<T> oldList, T newElement) {
        if (oldList.isEmpty()) {
            return Collections.singletonList(newElement);
        }
        ArrayList<T> newList = new ArrayList<T>(oldList.size() + 1);
        newList.addAll(oldList);
        newList.add(newElement);
        return Collections.unmodifiableList(newList);
    }
}
