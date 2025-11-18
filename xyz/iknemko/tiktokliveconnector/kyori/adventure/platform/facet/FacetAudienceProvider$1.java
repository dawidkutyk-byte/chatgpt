/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudience
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudienceProvider
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet;

import org.jetbrains.annotations.NotNull;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.Audience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.audience.ForwardingAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudience;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.platform.facet.FacetAudienceProvider;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.pointer.Pointers;

/*
 * Exception performing whole class analysis ignored.
 */
class FacetAudienceProvider.1
implements ForwardingAudience {
    @NotNull
    public Iterable<? extends Audience> audiences() {
        return FacetAudienceProvider.access$000((FacetAudienceProvider)FacetAudienceProvider.this);
    }

    @NotNull
    public Pointers pointers() {
        if (FacetAudienceProvider.access$000((FacetAudienceProvider)FacetAudienceProvider.this).size() != 1) return Pointers.empty();
        return ((FacetAudience)FacetAudienceProvider.access$000((FacetAudienceProvider)FacetAudienceProvider.this).iterator().next()).pointers();
    }

    FacetAudienceProvider.1() {
    }
}
