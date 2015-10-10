package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ConceptoCentral;

/**
 * Seccion horizontal de una torta.
 */
@ConceptoCentral
public class Piso extends Entity
{
    private double precio;
    private Relleno relleno;
    private String masa;

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

    public String getMasa()
    {
        return masa;
    }

    public void setMasa( String masa )
    {
        this.masa = masa;
    }
}
