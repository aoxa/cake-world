package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ConceptoCentral;

/**
 * Seccion horizontal de una torta.
 */
@ConceptoCentral
public class Piso
{
    private double precio;
    private Relleno relleno;

    public double getPrecio()
    {
        return precio;
    }

    public void setPrecio( double precio )
    {
        this.precio = precio;
    }

    public Relleno getRelleno()
    {
        return relleno;
    }

    public void setRelleno( Relleno relleno )
    {
        this.relleno = relleno;
    }
}
