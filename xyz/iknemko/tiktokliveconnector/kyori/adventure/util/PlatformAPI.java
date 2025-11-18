/*
 * Decompiled with CFR 0.152.
 */
package xyz.iknemko.tiktokliveconnector.kyori.adventure.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.ApiStatus;

@Target(value={ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PACKAGE, ElementType.ANNOTATION_TYPE})
@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@ApiStatus.Internal
public @interface PlatformAPI {
}
