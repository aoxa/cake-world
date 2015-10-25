package com.zuppelli.living.docs.render;

import java.util.ArrayList;
import java.util.List;

public class JClass
{
    private String name;
    private String comment;
    private boolean deprecated;
    private final List<JMethod> methods;
    private final List<JDerived> deriveds;

    public JClass()
    {
        methods = new ArrayList<>();
        deriveds = new ArrayList<>();
    }

    public void addDerived( JDerived derived )
    {
        if ( null != derived )
        {
            this.deriveds.add( derived );
        }
    }

    public void addMethod( JMethod method )
    {
        if ( null != method )
        {
            this.methods.add( method );
        }
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public void setComment( String comment )
    {
        this.comment = comment;
    }

    public String getName()
    {
        return name;
    }

    public String getComment()
    {
        return this.comment;
    }

    public List<JMethod> getMethods()
    {
        return this.methods;
    }

    public List<JDerived> getDeriveds()
    {
        return this.deriveds;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated( boolean deprecated ) {
        this.deprecated = deprecated;
    }
}
