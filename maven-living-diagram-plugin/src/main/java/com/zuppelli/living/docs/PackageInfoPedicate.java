package com.zuppelli.living.docs;

import com.google.common.reflect.ClassPath;

import java.util.function.Predicate;

public class PackageInfoPedicate implements Predicate<ClassPath.ClassInfo>
{

    @Override
    public boolean test( ClassPath.ClassInfo classInfo )
    {
        return classInfo.getSimpleName().equals( "package-info" );
    }
}
