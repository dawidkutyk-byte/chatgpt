/*
 * Decompiled with CFR 0.152.
 */
package org.intellij.lang.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NonNls;

@Target(value={ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Documented
@Retention(value=RetentionPolicy.SOURCE)
public @interface MagicConstant {
    public long[] intValues() default {};

    public Class<?> flagsFromClass() default void.class;

    public long[] flags() default {};

    @NonNls
    public String[] stringValues() default {};

    public Class<?> valuesFromClass() default void.class;
}
