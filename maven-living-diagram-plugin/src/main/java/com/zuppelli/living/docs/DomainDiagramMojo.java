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
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.livingdocumentation.dotdiagram.DotGraph;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


@Mojo( name = "domain-diagram" )
public class DomainDiagramMojo
        extends BaseDiagramMojo
{
    @Parameter( property = "diagram.domain.package", required = true )
    private String domainPackage;

    public void execute()
            throws MojoExecutionException
    {
        try
        {
            this.generateDiagram();
        } catch ( Exception e )
        {
            throw new MojoExecutionException( e.getMessage() );
        }
    }

    @Override
    protected void populatePackageClusters( ImmutableSet<ClassPath.ClassInfo> allClasses, DotGraph.Digraph digraph )
    {
        Stream<ClassPath.ClassInfo> domain = allClasses.stream().filter( filter( getPrefix(), this.getDomainPackage() ) );
        final DotGraph.Cluster core = digraph.addCluster( "hexagon" );
        core.setLabel( "Core Domain" );

        // add all domain model elements first
        domain.forEach( new Consumer<ClassPath.ClassInfo>()
        {
            public void accept( ClassPath.ClassInfo ci )
            {
                final Class clazz = ci.load();
                if( !ignoreDeprecated( clazz ) )
                    core.addNode( clazz.getName() ).setLabel( clazz.getSimpleName() ).setComment( clazz.getSimpleName() );
            }
        } );
    }

    @Override
    protected void populateNonPackageNodes( ImmutableSet<ClassPath.ClassInfo> allClasses, final DotGraph.Digraph digraph )
    {
        Stream<ClassPath.ClassInfo> infra = allClasses.stream().filter( filterNot( getPrefix(), "domain" ) );
        infra.forEach( new Consumer<ClassPath.ClassInfo>()
        {
            public void accept( ClassPath.ClassInfo ci )
            {
                final Class clazz = ci.load();
                if( !ignoreDeprecated( clazz ) )
                    digraph.addNode( clazz.getName() ).setLabel( clazz.getSimpleName() ).setComment( clazz.getSimpleName() );
            }
        } );
    }

    @Override
    protected void populateAssociations( ImmutableSet<ClassPath.ClassInfo> allClasses, final DotGraph.Digraph digraph )
    {
        Stream<ClassPath.ClassInfo> infra = allClasses.stream().filter( filterNot( getPrefix(), "domain" ) );
        infra.forEach( new ClassRelationshipConsumer( digraph, getShowDeprecated() ));

        // then wire them together
        Stream<ClassPath.ClassInfo> domain = allClasses.stream().filter( filter( getPrefix(), "domain" ) );
        domain.forEach( new ClassRelationshipConsumer( digraph, getShowDeprecated()) );
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
        return getDomainPackage();
    }

    public String getDomainPackage()
    {
        return domainPackage;
    }

    public void setDomainPackage( String domainPackage )
    {
        this.domainPackage = domainPackage;
    }
}
