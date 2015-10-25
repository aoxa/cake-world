package com.zuppelli.living.docs;

import com.thoughtworks.qdox.JavaProjectBuilder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Mojo( name = "glossary" )
public class GlossaryMojo extends AbstractMojo {
    @Parameter( property = "glossary.package", required = true )
    private String sourcepath = "";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
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
            System.out.println(e);
        } finally
        {
            if ( null != doclet )
            {
                doclet.end();
            }
        }
    }
}
