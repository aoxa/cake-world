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
import com.zuppelli.living.docs.helper.ClassInfoFilters;
import com.zuppelli.livingdocs.DomainContext;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.livingdocumentation.dotdiagram.DotGraph;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;


/**
 * Generates diagram based on @DomainContext annotation.
 *
 * @author Pedro Zuppelli
 */
@Mojo( name = "diagram" )
public class ContextAwareDiagramMojo extends BaseDiagramMojo
{
    private static final String PACKAGE_INFO = "package-info";

    public final Map<String, String> packages = new HashMap<String, String>();

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

    protected void populateAssociations( ImmutableSet<ClassPath.ClassInfo> allClasses, final DotGraph.Digraph digraph )
    {
        Stream<ClassPath.ClassInfo> infra;

        infra = allClasses.stream().filter( ClassInfoFilters.filterNot( this.packages ) );
        infra.forEach( new ClassRelationshipConsumer( digraph, getShowDeprecated() ) );

        for ( Map.Entry<String, String> entry : this.packages.entrySet() )
        {
            Stream<ClassPath.ClassInfo> domain = allClasses.stream().filter( ClassInfoFilters.filter( entry.getKey() ) );
            domain.forEach( new ClassRelationshipConsumer( digraph, getShowDeprecated() ));
        }
    }

    protected void populateNonPackageNodes( ImmutableSet<ClassPath.ClassInfo> allClasses, final DotGraph.Digraph digraph )
    {
        Stream<ClassPath.ClassInfo> infra = allClasses.stream().filter( ClassInfoFilters.filterNot( this.packages ) );

        infra.forEach( new Consumer<ClassPath.ClassInfo>()
        {
            public void accept( ClassPath.ClassInfo ci )
            {
                final Class clazz = ci.load();
                if ( !ignoreDeprecated( clazz ) && !PACKAGE_INFO.equals( clazz.getSimpleName() ) )
                    digraph.addNode( clazz.getName() ).setLabel( clazz.getSimpleName() ).setComment( clazz.getSimpleName() );
            }
        } );
    }

    protected void populatePackageClusters( ImmutableSet<ClassPath.ClassInfo> allClasses, DotGraph.Digraph digraph )
    {
        List<String> orderedPackages = new ArrayList<String>( this.packages.keySet() );
        Collections.sort( orderedPackages, this.stringLengthComparator );
        final Set<Class> handled = new HashSet<Class>();
        Map<String, DotGraph.Cluster> clusters = new HashMap<String, DotGraph.Cluster>();

        for ( String pkg : orderedPackages )
        {
            Stream<ClassPath.ClassInfo> layer = allClasses.stream().filter( ClassInfoFilters.filter( pkg ) );

            String name = this.packages.get( pkg );
            DotGraph.Cluster found = null;
            for ( String handledPkg : clusters.keySet() )
            {
                if ( pkg.contains( handledPkg ) )
                {
                    found = clusters.get( handledPkg );
                    break;
                }
            }

            final DotGraph.Cluster core = null == found ? digraph.addCluster( pkg ) : found.addCluster( pkg );
            clusters.put( pkg, core );
            core.setLabel( name );

            layer.forEach( new Consumer<ClassPath.ClassInfo>()
            {
                public void accept( ClassPath.ClassInfo ci )
                {
                    final Class clazz = ci.load();

                    if ( !ignoreDeprecated( clazz ) && !PACKAGE_INFO.equals( clazz.getSimpleName() ) && !handled.contains( clazz ) )
                    {
                        core.addNode( clazz.getName() ).setLabel( clazz.getSimpleName() ).setComment( clazz.getSimpleName() );
                        handled.add( clazz );
                    }
                }
            } );
        }
    }

    protected void populatePackageInfoMap( ImmutableSet<ClassPath.ClassInfo> allClasses )
    {
        Stream<ClassPath.ClassInfo> pkFilter = allClasses.stream().filter( new PackageInfoPedicate() );

        pkFilter.forEach( new Consumer<ClassPath.ClassInfo>()
        {
            public void accept( ClassPath.ClassInfo ci )
            {
                Class clazz = ci.load();

                DomainContext annotation = (DomainContext) clazz.getAnnotation( DomainContext.class );
                if ( null != annotation )
                {
                    packages.put( clazz.getPackage().getName(), annotation.name() );
                }
            }
        } );
    }

    private final Comparator<String> stringLengthComparator = new Comparator<String>()
    {
        @Override
        public int compare( String o1, String o2 )
        {
            if ( null == o1 && null == o2 )
                return 0;
            if ( null == o1 )
                return -1;
            if ( null == o2 )
                return 1;
            return Integer.compare( o1.length(), o2.length() );
        }
    };
}
