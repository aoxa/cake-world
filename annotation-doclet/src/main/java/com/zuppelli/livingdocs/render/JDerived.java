package com.zuppelli.livingdocs.render;

public class JDerived
{
    private final String name;
    private final String comment;

    public JDerived( String name, String comment )
    {
        this.name = name;
        this.comment = comment;
    }

    public String getComment()
    {
        return comment;
    }

    public String getName()
    {
        return name;
    }
}
