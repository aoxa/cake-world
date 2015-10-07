package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ConceptoCentral;

/**
 * Seccion horizontal de una torta.
 */
@ConceptoCentral
public class Piso
{
    private double precio;

    public double getPrecio()
    {
        return precio;
    }

    public void setPrecio( double precio )
    {
        this.precio = precio;
    }
}
