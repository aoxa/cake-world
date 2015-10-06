package com.zuppelli.living.docs;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.livingdocumentation.dotdiagram.DotGraph;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDiagramMojo extends AbstractMojo
{
    @Parameter( property = "diagram.name", defaultValue = "${project.name}" )
    private String projectName;

    @Parameter( property = "diagram.prefix", required = true )
    private String prefix;

    @Parameter( property = "diagram.showDeprecated", defaultValue = "true" )
    private Boolean showDeprecated;

    @Component
    private MavenProject project;

    private final Map<String, Object> content = new HashMap<String, Object>();

    public final void generateDiagram() throws Exception
    {
        final DotGraph graph = new DotGraph( projectName, "LR" );
        final ClassPath classPath = ClassPath.from( getClassLoader( this.getProject() ) );

        final String prefix = getPrefix();
        final ImmutableSet<ClassPath.ClassInfo> allClasses = classPath.getTopLevelClassesRecursive( prefix );

        final DotGraph.Digraph digraph = graph.getDigraph();
        digraph.setOptions( "rankdir=LR" );

        populatePackageInfoMap( allClasses );

        populatePackageClusters( allClasses, digraph );

        populateNonPackageNodes( allClasses, digraph );

        populateAssociations( allClasses, digraph );

        String title = this.getProjectName() + " Living Diagram";

        this.content.put( "content", graph.render().trim() );
        this.content.put( "title", title );
        Template template = initializeFreeMarker();
        Writer writer = new PrintWriter( title.replace( " ", "_" ) + ".html" );
        try
        {
            template.process( this.content, writer );
        } finally
        {
            writer.close();
        }
    }

    protected void populatePackageInfoMap( ImmutableSet<ClassPath.ClassInfo> allClasses ) {;};

    protected abstract void populatePackageClusters( ImmutableSet<ClassPath.ClassInfo> allClasses, final DotGraph.Digraph digraph );

    protected abstract void populateNonPackageNodes( ImmutableSet<ClassPath.ClassInfo> allClasses, final DotGraph.Digraph digraph );

    protected abstract void populateAssociations( ImmutableSet<ClassPath.ClassInfo> allClasses, final DotGraph.Digraph digraph );

    public static ClassLoader getClassLoader( MavenProject project ) throws MalformedURLException, DependencyResolutionRequiredException
    {
        List<String> classPathElements = compileClassPathElements( project );
        List<URL> classpathElementUrls = new ArrayList<URL>( classPathElements.size() );
        for ( String classPathElement : classPathElements )
        {
            classpathElementUrls.add( new File( classPathElement ).toURI().toURL() );
        }
        return new URLClassLoader(
                classpathElementUrls.toArray( new URL[classpathElementUrls.size()] ),
                Thread.currentThread().getContextClassLoader()
        );
    }

    private Template initializeFreeMarker() throws IOException
    {
        final Configuration configuration = new Configuration( Configuration.VERSION_2_3_21 );
        configuration.setClassForTemplateLoading( this.getClass(), "/templates" );

        configuration.setDefaultEncoding( "UTF-8" );
        configuration.setTemplateExceptionHandler( TemplateExceptionHandler.RETHROW_HANDLER );
        return configuration.getTemplate( "ld-template.ftl" );
    }

    private static List<String> compileClassPathElements( MavenProject project ) throws DependencyResolutionRequiredException
    {
        return new ArrayList<String>( project.getCompileClasspathElements() );
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix( String prefix )
    {
        this.prefix = prefix;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName( String projectName )
    {
        this.projectName = projectName;
    }

    public MavenProject getProject()
    {
        return project;
    }

    public void setProject( MavenProject project )
    {
        this.project = project;
    }

    public Boolean getShowDeprecated()
    {
        return showDeprecated;
    }

    public void setShowDeprecated( Boolean showDeprecated )
    {
        this.showDeprecated = showDeprecated;
    }
}
