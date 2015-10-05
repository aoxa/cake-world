package com.zuppelli.livingdocs;

import java.lang.annotation.*;

@Retention( RetentionPolicy.RUNTIME)
@Documented
@Target( ElementType.PACKAGE)
public @interface DomainContext
{
    String name();
}
