/*
 * Decompiled with CFR 0.152.
 */
package org.jetbrains.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NonNls;

@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.CLASS)
public static @interface Debug.Renderer {
    @Language(value="JAVA", prefix="class Renderer{Object[] $childrenArray(){return ", suffix=";}}")
    @NonNls
    public String childrenArray() default "";

    @Language(value="JAVA", prefix="class Renderer{String $text(){return ", suffix=";}}")
    @NonNls
    public String text() default "";

    @Language(value="JAVA", prefix="class Renderer{boolean $hasChildren(){return ", suffix=";}}")
    @NonNls
    public String hasChildren() default "";
}
