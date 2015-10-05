package com.zuppelli.livingdocs.doclet;

import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.formats.html.HtmlDoclet;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.*;
import com.zuppelli.livingdocs.render.JClass;
import com.zuppelli.livingdocs.render.JDerived;
import com.zuppelli.livingdocs.render.JMethod;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Doclet que genera una pagina usando la libreria qox.
 */
public class AnnotationHTMLDoclet
{
    private final PrintWriter writer;
    private final Template template;
    private final Map<String, Object> root;

    public static int optionLength( String option )
    {
        return HtmlDoclet.optionLength( option );
    }

    public static LanguageVersion languageVersion()
    {
        return LanguageVersion.JAVA_1_5;
    }

    private AnnotationHTMLDoclet( PrintWriter writer )
    {
        root = new HashMap<>(  );
        try
        {
            final Configuration configuration = new Configuration( Configuration.VERSION_2_3_21 );
            configuration.setClassForTemplateLoading( this.getClass(), "/templates" );

            configuration.setDefaultEncoding( "UTF-8" );
            configuration.setTemplateExceptionHandler( TemplateExceptionHandler.RETHROW_HANDLER );
            template = configuration.getTemplate( "glossario.ftl" );
            this.writer = writer;

        } catch ( IOException ex )
        {
            throw new RuntimeException( String.format( "Error initializing freemarker context: %s", ex.getMessage() ) );
        }
    }

    public AnnotationHTMLDoclet begin()
    {
        return this;
    }

    public void end()
    {
        try {
            template.process( root, writer );
        } catch ( TemplateException | IOException ex ) {
            System.out.println( "Error tratando de escribir con el template" );
        }
        finally {
            writer.close();
        }
    }

    public static boolean start( RootDoc root )
    {
        String sourcepath = "";
        for ( String[] tag : root.options() )
        {
            if ( tag[0].equals( "-sourcepath" ) )
            {
                sourcepath = tag[1];
                break;
            }
        }

        AnnotationHTMLDoclet doclet = null;
        try
        {
            doclet = new AnnotationHTMLDoclet( new PrintWriter( "glossary.html" ) ).begin();

            JavaProjectBuilder builder = new JavaProjectBuilder();
            // Adding all .java files in a source tree (recursively).
            builder.addSourceTree( new File( sourcepath ) );

            doclet.process( builder.getPackages() );

        } catch ( FileNotFoundException e )
        {
            //...
        } finally
        {
            if ( null != doclet )
            {
                doclet.end();
            }
        }
        return true;
    }

    public Map<String, Object> process( Collection<JavaPackage> packages )
    {
        List<JClass> classes = new ArrayList<>(  );

        for ( JavaPackage pckge : packages )
        {
            for ( JavaClass clss : pckge.getClasses() )
            {
                if ( isBusinessMeaningful( clss.getAnnotations() ) )
                {
                    classes.add( process( clss ) );
                }
            }
        }

        root.put( "classes", classes );
        return root;
    }

    public JClass process( JavaClass clss )
    {
        JClass current = new JClass();
        current.setName( clss.getName() );
        current.setComment( clss.getComment() );

        if ( clss.isEnum() )
        {
            for ( JavaField field : clss.getEnumConstants() )
            {
                printEnumConstant( field );
            }

            for ( JavaMethod method : clss.getMethods( false ) )
            {
                current.addMethod( printMethod( method ) );
            }
        } else if ( clss.isInterface() )
        {
            for ( JavaClass subClass : clss.getDerivedClasses() )
            {
                current.addDerived( printSubClass( subClass ) );
            }
        } else
        {
            for ( JavaField field : clss.getFields() )
            {
                printField( field );
            }
            for ( JavaMethod method : clss.getMethods( false ) )
            {
                current.addMethod( printMethod( method ) );
            }
        }
        return current;
    }

    protected boolean isBusinessMeaningful( List<JavaAnnotation> annotations )
    {
        for ( JavaAnnotation annotation : annotations )
        {
            if ( isBusinessMeaningful( annotation.getType() ) )
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

    private void printField( JavaField field )
    {
    }

    private JDerived printSubClass( JavaClass clss )
    {
        return new JDerived( clss.getName(), clss.getComment() );
    }

    private void printEnumConstant( JavaField field )
    {
    }

    private JMethod printMethod( JavaMethod m )
    {
        if ( !m.isPublic() || !hasComment( m ) )
        {
            return null;
        }
        return new JMethod( m.getName() + m.getCallSignature() + ": " + m.getReturnType().getCanonicalName(),
                m.getComment() );
    }

    private boolean hasComment( JavaAnnotatedElement doc )
    {
        return null != doc.getComment() && doc.getComment().trim().length() > 0;
    }
}
