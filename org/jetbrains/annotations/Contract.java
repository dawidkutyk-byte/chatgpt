/*
 * Decompiled with CFR 0.152.
 */
package org.jetbrains.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NonNls;

@Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR})
@Documented
@Retention(value=RetentionPolicy.CLASS)
public @interface Contract {
    @NonNls
    public String value() default "";

    public boolean pure() default false;

    @NonNls
    public String mutates() default "";
}
