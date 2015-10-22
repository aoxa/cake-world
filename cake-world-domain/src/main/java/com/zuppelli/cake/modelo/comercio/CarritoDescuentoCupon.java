package com.zuppelli.cake.modelo.comercio;

import com.zuppelli.cake.modelo.comercio.descuento.Cupon;
import com.zuppelli.livingdocs.ReemplazadoPor;

/**
 * Carrito con cupon de descuento.
 */
@Deprecated
@ReemplazadoPor(
        referencia = Carrito.class,
        razon = "Se aplica el patron Strategy en el carrito, por lo que esta clase queda redundante." )
public class CarritoDescuentoCupon extends Carrito {
    private Cupon cupon;

    public double getPrecio() {
        return super.getPrecio() * cupon.getDescuento();
    }

}
