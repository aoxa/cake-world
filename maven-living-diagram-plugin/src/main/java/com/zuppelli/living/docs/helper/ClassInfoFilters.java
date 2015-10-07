package com.zuppelli.living.docs.helper;

import com.google.common.reflect.ClassPath;

import java.util.Map;
import java.util.function.Predicate;

public class ClassInfoFilters
{
    public static Predicate<ClassPath.ClassInfo> filter( final String layer ){
        return new Predicate<ClassPath.ClassInfo>()
        {
            public boolean test( ClassPath.ClassInfo ci )
            {
                return  isNotATest( ci )
                        && ci.getPackageName().contains( layer );
            }
        };
    }
    public static Predicate<ClassPath.ClassInfo> filter( final String prefix, final String layer )
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

    public static Predicate<ClassPath.ClassInfo> filterNot( final String prefix, final String layer )
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

    public static Predicate<ClassPath.ClassInfo> filterNot( final Map<String, String> configs )
    {
        return new Predicate<ClassPath.ClassInfo>()
        {
            public boolean test( ClassPath.ClassInfo ci )
            {
                return isNotATest( ci )
                        && null == configs.get(ci.getPackageName());
            }

        };
    }

    public static boolean isNotATest( ClassPath.ClassInfo ci )
    {
        return !ci.getSimpleName().endsWith( "Test" ) && !ci.getSimpleName().endsWith( "IT" );
    }
}
