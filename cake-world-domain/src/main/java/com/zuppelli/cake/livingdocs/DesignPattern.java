package com.zuppelli.cake.livingdocs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Un patron de diseño es una solucion conocida y probada para un problema recurrente.
 */
@Retention( RetentionPolicy.RUNTIME)
@Target( ElementType.ANNOTATION_TYPE )
public @interface DesignPattern
{
}
