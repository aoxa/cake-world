package com.zuppelli.cake.comercio;

import com.zuppelli.livingdocs.ConceptoCentral;

/**
 * Codigo que representa un descuento para una compra.
 */
@ConceptoCentral
public class Cupon
{
    private final double descuento;
    private final String codigo;

    public Cupon(double descuento, String codigo )
    {
        this.descuento = descuento;
        this.codigo = codigo;
    }

    public double getDescuento()
    {
        return descuento;
    }

    public String getCodigo()
    {
        return codigo;
    }
}
