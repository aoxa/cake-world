package com.zuppelli.cake.comercio.descuento;

import com.zuppelli.cake.comercio.Cupon;

/**
 * Aplica el descuento utilizando un cupon.
 */
public class PorCupon implements Descuento
{
    private Cupon cupon;

    public PorCupon( Cupon cupon ) {
        this.cupon = cupon;
    }

    @Override
    public double aplicar( double total )
    {
        return total * (1 - cupon.getDescuento() );
    }
}
