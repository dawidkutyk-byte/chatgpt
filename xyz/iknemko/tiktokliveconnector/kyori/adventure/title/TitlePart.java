/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component
 *  xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title$Times
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.title;

import org.jetbrains.annotations.ApiStatus;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.text.Component;
import xyz.iknemko.tiktokliveconnector.kyori.adventure.title.Title;

@ApiStatus.NonExtendable
public interface TitlePart<T> {
    public static final TitlePart<Title.Times> TIMES;
    public static final TitlePart<Component> TITLE;
    public static final TitlePart<Component> SUBTITLE;

    static {
        TITLE = new /* Unavailable Anonymous Inner Class!! */;
        SUBTITLE = new /* Unavailable Anonymous Inner Class!! */;
        TIMES = new /* Unavailable Anonymous Inner Class!! */;
    }
}
