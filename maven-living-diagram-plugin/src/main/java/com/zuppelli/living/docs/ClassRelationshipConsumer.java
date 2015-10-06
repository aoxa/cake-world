package com.zuppelli.living.docs;

import com.google.common.reflect.ClassPath;
import org.livingdocumentation.dotdiagram.DotGraph;
import org.livingdocumentation.dotdiagram.DotStyles;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.function.Consumer;

public class ClassRelationshipConsumer implements Consumer<ClassPath.ClassInfo>
{
    final DotGraph.Digraph digraph;

    public ClassRelationshipConsumer( final DotGraph.Digraph digraph ) {
        this.digraph = digraph;
    }

    @Override
    public void accept( ClassPath.ClassInfo ci )
    {
        final Class clazz = ci.load();
        // API
        for ( Field field : clazz.getDeclaredFields() )
        {
            final Class<?> type = field.getType();
            if ( !type.isPrimitive() )
            {
                if( field.getGenericType().getTypeName().startsWith( "java.util" ))   {
                    ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();

                    digraph.addExistingAssociation( clazz.getName(),  ((Class)parameterizedType.getActualTypeArguments()[0]).getName(), "[1,0]...*", null,
                            DotStyles.ASSOCIATION_EDGE_STYLE );

                }else {
                    digraph.addExistingAssociation( clazz.getName(), type.getName(), null, null,
                            DotStyles.ASSOCIATION_EDGE_STYLE );
                }

            }
        }

        // SPI
        for ( Class intf : clazz.getInterfaces() )
        {
            digraph.addExistingAssociation( intf.getName(), clazz.getName(), null, null, DotStyles.IMPLEMENTS_EDGE_STYLE );
        }
        if( null != clazz.getSuperclass() && !clazz.getSuperclass().getName().equals( Object.class.getName() ))
                digraph.addExistingAssociation( clazz.getSuperclass().getName(), clazz.getName(), null, null, DotStyles.EXTENDS_EDGE_STYLE );

    }
}
