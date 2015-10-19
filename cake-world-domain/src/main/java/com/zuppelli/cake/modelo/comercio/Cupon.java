package com.zuppelli.cake.modelo.comercio;

import com.zuppelli.livingdocs.ConceptoCentral;

/**
 * Codigo que representa un descuento para una compra. Consta de un codigo y un porcentaje.
 */
@ConceptoCentral
public class Cupon extends EntityComercio
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
