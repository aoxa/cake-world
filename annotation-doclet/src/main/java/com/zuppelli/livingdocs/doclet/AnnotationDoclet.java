package com.zuppelli.livingdocs.doclet;

import com.sun.javadoc.*;
import com.sun.tools.doclets.formats.html.HtmlDoclet;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class AnnotationDoclet
{
    private final PrintWriter writer;

    public static int optionLength(String option)
    {
        return HtmlDoclet.optionLength(option);
    }

    public static LanguageVersion languageVersion()
    {
        return LanguageVersion.JAVA_1_5;
    }

    private AnnotationDoclet(PrintWriter writer)
    {
        this.writer = writer;
    }

    public AnnotationDoclet begin()
    {
        writer.println("# " + "Glossary");
        return this;
    }

    public void end()
    {
        writer.close();
    }

    public static boolean start(RootDoc root)
    {
        AnnotationDoclet doclet = null;
        try
        {
            doclet = new AnnotationDoclet(new PrintWriter("glossary.txt")).begin();
            doclet.process(root);
        } catch (FileNotFoundException e)
        {
            //...
        } finally
        {
            if (null != doclet)
            {
                doclet.end();
            }
        }
        return true;
    }

    public void process(RootDoc root)
    {
        final ClassDoc[] classes = root.classes();
        for (ClassDoc clss : classes)
        {
            if (isBusinessMeaningful(clss))
            {
                process(clss);
            }
        }
    }

    protected boolean isBusinessMeaningful(ProgramElementDoc doc)
    {
        final AnnotationDesc[] annotations = doc.annotations();
        for (AnnotationDesc annotation : annotations)
        {
            if (isBusinessMeaningful(annotation.annotationType()))
            {
                return true;
            }
        }
        return false;
    }

    boolean isBusinessMeaningful(final AnnotationTypeDoc annotationType)
    {
        return annotationType.qualifiedTypeName().contains("livingdocs");
    }

    protected void process(ClassDoc clss)
    {
        writer.println("");
        writer.println("## *" + clss.simpleTypeName() + "*");
        writer.println(clss.commentText());
        writer.println("");
        if (clss.isEnum())
        {
            for (FieldDoc field : clss.enumConstants())
            {
                printEnumConstant(field);
            }
            writer.println("");
            for (MethodDoc method : clss.methods(false))
            {
                printMethod(method);
            }
        } else if (clss.isInterface())
        {
            /*
            for (ClassDoc subClass : subclasses(clss))
            {
                printSubClass(subClass);
            }
            */
        } else
        {
            for (FieldDoc field : clss.fields(false))
            {
                printField(field);
            }
            for (MethodDoc method : clss.methods(false))
            {
                printMethod(method);
            }
        }
    }

    private void printField(FieldDoc field)
    {
    }

    private void printEnumConstant(FieldDoc field)
    {
    }

    private void printMethod(MethodDoc m)
    {
        if (!m.isPublic() || !hasComment(m))
        {
            return;
        }
        final String signature = m.name() + m.flatSignature() + ": " + m.returnType().simpleTypeName();
        writer.println("- " + signature + " " + m.commentText());
    }

    private boolean hasComment(ProgramElementDoc doc)
    {
        return doc.commentText().trim().length() > 0;
    }
}
