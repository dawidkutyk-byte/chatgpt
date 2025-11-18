/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.annotation.ThreadingBehavior
 */
package org.apache.http.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.http.annotation.ThreadingBehavior;

@Retention(value=RetentionPolicy.CLASS)
@Target(value={ElementType.TYPE})
@Documented
public @interface Contract {
    public ThreadingBehavior threading() default ThreadingBehavior.UNSAFE;
}
