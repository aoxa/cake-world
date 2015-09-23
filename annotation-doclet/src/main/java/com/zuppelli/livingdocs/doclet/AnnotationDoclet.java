package com.zuppelli.livingdocs.doclet;

import com.sun.javadoc.*;
import com.sun.tools.doclets.formats.html.HtmlDoclet;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

/**
 * Doclet that generates a text file.
 */
public class AnnotationDoclet
{
    private final PrintWriter writer;

    public static int optionLength(String option)
    {
        System.out.println(option);
        return HtmlDoclet.optionLength(option);
    }

    public static LanguageVersion languageVersion()
    {
        return LanguageVersion.JAVA_1_5;
    }

    private AnnotationDoclet(PrintWriter writer)
    {
        this.writer = writer;
    }

    public AnnotationDoclet begin()
    {
        writer.println( "# " + "Glossary" );
        return this;
    }

    public void end()
    {
        writer.close();
    }

    public static boolean start(RootDoc root)
    {
        String sourcepath = "";
        for( String[] tag : root.options() ) {
            if( tag[0].equals( "-sourcepath" ) )
            {
                sourcepath = tag[1];
                break;
            }
        }

        AnnotationDoclet doclet = null;
        try
        {
            doclet = new AnnotationDoclet(new PrintWriter("glossary.txt")).begin();

            JavaProjectBuilder builder = new JavaProjectBuilder();
            // Adding all .java files in a source tree (recursively).
            builder.addSourceTree( new File( sourcepath ) );

            doclet.process( builder.getPackages() );
            // doclet.process(root);
        } catch (FileNotFoundException e)
        {
            //...
        } finally
        {
            if (null != doclet)
            {
                doclet.end();
            }
        }
        return true;
    }

    public void process( Collection<JavaPackage> packages ) {
        for ( JavaPackage pckge : packages ) {
            for (JavaClass clss : pckge.getClasses() ) {
                if (isBusinessMeaningful(clss.getAnnotations())) {
                    process(clss);
                }
            }
        }
    }

    public void process( JavaClass clss ) {
        writer.println( "" );
        writer.println("## *" + clss.getName() + "*");
        writer.println(clss.getComment());
        writer.println("");
        if (clss.isEnum())
        {
            for (JavaField field : clss.getEnumConstants())
            {
                printEnumConstant(field);
            }
            writer.println("");
            for (JavaMethod method : clss.getMethods( false ))
            {
                printMethod(method);
            }
        } else if (clss.isInterface())        {
            for ( JavaClass subClass : clss.getDerivedClasses() )
            {
                printSubClass(subClass);
            }
        } else
        {
            for (JavaField field : clss.getFields() )
            {
                printField(field);
            }
            for (JavaMethod method : clss.getMethods( false ))
            {
                printMethod(method);
            }
        }
    }

    protected boolean isBusinessMeaningful(List<JavaAnnotation> annotations )
    {
        for (JavaAnnotation annotation : annotations)
        {
            if (isBusinessMeaningful( annotation.getType() ))
            {
                return true;
            }
        }
        return false;
    }

    boolean isBusinessMeaningful( final JavaClass annotationClass )
    {
        return annotationClass.getClassNamePrefix().contains( "livingdocs" );
    }

    private void printField(JavaField field)
    {
    }

    private void printSubClass( JavaClass clss )
    {
        writer.println( "" );
        writer.println("   >> *" + clss.getName() + "*");
        writer.println("   " + clss.getComment());
    }

    private void printEnumConstant(JavaField field)
    {
    }

    private void printMethod(JavaMethod m)
    {
        if (!m.isPublic() || !hasComment(m))
        {
            return;
        }
        final String signature = m.getName() + m.getCallSignature() + ": " + m.getReturnType().getCanonicalName();
        writer.println("- " + signature + " " + m.getComment());
    }

    private boolean hasComment(JavaAnnotatedElement doc)
    {
        return null!= doc.getComment() && doc.getComment().trim().length() > 0;
    }
}
