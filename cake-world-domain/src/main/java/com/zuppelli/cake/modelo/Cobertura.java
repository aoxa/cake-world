package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ConceptoCentral;

/**
 * Lamina de distintos tipos de ingredientes que cubre una torta.
 */
@ConceptoCentral
public class Cobertura
{
    private String tipo;

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo( String tipo )
    {
        this.tipo = tipo;
    }
}
