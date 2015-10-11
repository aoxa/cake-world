package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;

/**
 * Seccion horizontal de una torta.
 */
@ConceptoCentral
public class Piso extends Entity
{
    //TODO: Extraer el precio para que sea configurable.
    public static final int PRECIO_POR_KILO = 107;
    private Relleno relleno;
    private String masa;
    private double peso;

    /**
     * Se calcula mediante el peso de la torta y el costo por kilo.
     *
     * @return - El precio total del piso.
     */
    @ComportamientoCentral
    public double getPrecio()
    {
        double precio = peso * PRECIO_POR_KILO;
        if( null != relleno ) {
            precio += relleno.getPrecio();
        }
        return precio;
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

    public void setPeso( double peso ) {
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }
}
