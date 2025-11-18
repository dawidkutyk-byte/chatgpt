/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

static class FacetAudienceProvider.2
implements Iterable<V> {
    final /* synthetic */ Function val$transformer;
    final /* synthetic */ Iterable val$input;
    final /* synthetic */ Predicate val$filter;

    FacetAudienceProvider.2(Iterable iterable, Predicate predicate, Function function) {
        this.val$input = iterable;
        this.val$filter = predicate;
        this.val$transformer = function;
    }

    @Override
    @NotNull
    public Iterator<V> iterator() {
        return new /* Unavailable Anonymous Inner Class!! */;
    }

    @Override
    public void forEach(Consumer<? super V> action) {
        Iterator iterator = this.val$input.iterator();
        while (iterator.hasNext()) {
            Object each = iterator.next();
            if (!this.val$filter.test(each)) continue;
            action.accept(this.val$transformer.apply(each));
        }
    }
}
