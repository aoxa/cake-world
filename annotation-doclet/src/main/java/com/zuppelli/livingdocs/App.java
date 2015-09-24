package com.zuppelli.livingdocs;

import com.sun.tools.javadoc.Main;
import com.zuppelli.livingdocs.doclet.AnnotationHTMLDoclet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int execute = execute("-doclet", AnnotationHTMLDoclet.class.getName(), AnnotationHTMLDoclet.class.getName() + ".java" );

    }

    private static int execute(String...params)
    {
        return Main.execute( params );
    }
}
