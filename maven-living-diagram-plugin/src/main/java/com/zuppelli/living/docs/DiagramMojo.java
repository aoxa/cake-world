package com.zuppelli.living.docs;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.zuppelli.livingdocs.DomainContext;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.livingdocumentation.dotdiagram.DotGraph;
import org.livingdocumentation.dotdiagram.DotStyles;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * Original base mojo
 */
@Mojo( name = "old-diagram" )
@Deprecated
public class DiagramMojo
        extends AbstractMojo
{
    @Parameter( property = "diagram.name", defaultValue = "${project.name}" )
    private String projectName;

    @Parameter( property = "diagram.prefix", required = true )
    private String prefix;

    @Component
    private MavenProject project;

    private Map<String, Object> content = new HashMap<String, Object>();

    private File outputDirectory;
    private DotGraph graph;

    public void execute()
            throws MojoExecutionException
    {
        graph = new DotGraph( projectName, "LR" );
        try
        {
            this.generateDiagram();
        } catch ( Exception e )
        {
            throw new MojoExecutionException( e.getMessage() );
        }
    }

    private Map<String, String> packages = new HashMap<String, String>();

    public void generateDiagram() throws Exception
    {
        final ClassPath classPath = ClassPath.from( getClassLoader( this.getProject() ) );

        final String prefix = getPrefix();
        final ImmutableSet<ClassPath.ClassInfo> allClasses = classPath.getTopLevelClassesRecursive( prefix );

        final DotGraph.Digraph digraph = graph.getDigraph();
        digraph.setOptions( "rankdir=LR" );

        Stream<ClassPath.ClassInfo> pkFilter = allClasses.stream().filter( new PackageInfoPedicate() );

        pkFilter.forEach( new Consumer<ClassPath.ClassInfo>()
        {
            public void accept( ClassPath.ClassInfo ci )
            {
                Class clazz = ci.load();

                Annotation annotation = clazz.getAnnotation( DomainContext.class );
                if ( null != annotation )
                {
                    packages.put( clazz.getPackage().getName(), ( (DomainContext) annotation ).name() );
                }
            }
        } );

        Stream<ClassPath.ClassInfo> domain = allClasses.stream().filter( filter( prefix, "domain" ) );
        // Todo create cluster from bounded context
        final DotGraph.Cluster core = digraph.addCluster( "hexagon" );
        // Todo retireve label from bounded context
        core.setLabel( "Core Domain" );

        // add all domain model elements first
        domain.forEach( new Consumer<ClassPath.ClassInfo>()
        {
            public void accept( ClassPath.ClassInfo ci )
            {
                final Class clazz = ci.load();
                core.addNode( clazz.getName() ).setLabel( clazz.getSimpleName() ).setComment( clazz.getSimpleName() );
            }
        } );

        Stream<ClassPath.ClassInfo> infra = allClasses.stream().filter( filterNot( prefix, "domain" ) );
        infra.forEach( new Consumer<ClassPath.ClassInfo>()
        {
            public void accept( ClassPath.ClassInfo ci )
            {
                final Class clazz = ci.load();
                digraph.addNode( clazz.getName() ).setLabel( clazz.getSimpleName() ).setComment( clazz.getSimpleName() );
            }
        } );

        infra = allClasses.stream().filter( filterNot( prefix, "domain" ) );
        infra.forEach( new Consumer<ClassPath.ClassInfo>()
        {
            public void accept( ClassPath.ClassInfo ci )
            {
                final Class clazz = ci.load();
                // API
                for ( Field field : clazz.getDeclaredFields() )
                {
                    final Class<?> type = field.getType();
                    if ( !type.isPrimitive() )
                    {
                        digraph.addExistingAssociation( clazz.getName(), type.getName(), null, null,
                                DotStyles.ASSOCIATION_EDGE_STYLE );
                    }
                }

                // SPI
                for ( Class intf : clazz.getInterfaces() )
                {
                    digraph.addExistingAssociation( intf.getName(), clazz.getName(), null, null, DotStyles.IMPLEMENTS_EDGE_STYLE );
                }
            }
        } );

        // then wire them together
        domain = allClasses.stream().filter( filter( prefix, "domain" ) );
        domain.forEach( new Consumer<ClassPath.ClassInfo>()
        {
            public void accept( ClassPath.ClassInfo ci )
            {
                final Class clazz = ci.load();
                for ( Field field : clazz.getDeclaredFields() )
                {
                    final Class<?> type = field.getType();
                    if ( !type.isPrimitive() )
                    {
                        digraph.addExistingAssociation( clazz.getName(), type.getName(), null, null,
                                DotStyles.ASSOCIATION_EDGE_STYLE );
                    }
                }

                for ( Class intf : clazz.getInterfaces() )
                {
                    digraph.addExistingAssociation( intf.getName(), clazz.getName(), null, null, DotStyles.IMPLEMENTS_EDGE_STYLE );
                }
            }
        } );

        String title = "Living Diagram";
        final String content = graph.render().trim();

        this.content.put( "content", graph.render().trim() );
        this.content.put( "title", title );
        Template template = initializeFreeMarker();
        Writer writer = new PrintWriter( "diagrama.html" );
        try
        {
            template.process( this.content, writer );
        } finally
        {
            writer.close();
        }
    }

    private Predicate<ClassPath.ClassInfo> filter( final String packageName )
    {
        return new Predicate<ClassPath.ClassInfo>()
        {
            public boolean test( ClassPath.ClassInfo ci )
            {
                return isNotATest( ci )
                        && ci.getPackageName().equals( packageName );
            }
        };
    }

    private Predicate<ClassPath.ClassInfo> filter( final String prefix, final String layer )
    {
        return new Predicate<ClassPath.ClassInfo>()
        {
            public boolean test( ClassPath.ClassInfo ci )
            {
                final boolean nameConvention = ci.getPackageName().startsWith( prefix )
                        && isNotATest( ci )
                        && ci.getPackageName().endsWith( "." + layer );
                return nameConvention;
            }
        };
    }

    private Predicate<ClassPath.ClassInfo> filterNot( final String prefix, final String layer )
    {
        return new Predicate<ClassPath.ClassInfo>()
        {
            public boolean test( ClassPath.ClassInfo ci )
            {
                final boolean nameConvention = ci.getPackageName().startsWith( prefix )
                        && isNotATest( ci )
                        && !ci.getPackageName().endsWith( "." + layer );
                return nameConvention;
            }

        };
    }

    private boolean isNotATest( ClassPath.ClassInfo ci )
    {
        return !ci.getSimpleName().endsWith( "Test" ) && !ci.getSimpleName().endsWith( "IT" );
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

    public MavenProject getProject()
    {
        return project;
    }

    public void setProject( MavenProject project )
    {
        this.project = project;
    }
}
