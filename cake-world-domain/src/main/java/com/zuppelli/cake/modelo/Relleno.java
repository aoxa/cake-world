package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ConceptoCentral;

/**
 * Ingrediente que va en el centro de un piso de una torta.
 */
@ConceptoCentral
public class Relleno
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
