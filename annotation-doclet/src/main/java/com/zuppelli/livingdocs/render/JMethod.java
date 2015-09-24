package com.zuppelli.livingdocs.render;

public class JMethod
{
    private String signature;
    private String comment;

    public JMethod( String signature, String comment )
    {
        this.signature = signature;
        this.comment = comment;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment( String comment )
    {
        this.comment = comment;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature( String signature )
    {
        this.signature = signature;
    }
}
