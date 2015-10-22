package com.zuppelli.cake.modelo.comercio;

import com.zuppelli.livingdocs.ReemplazadoPor;

/**
 * Carrito con descuento aplicado para el usuario en su primera compra.
 */
@Deprecated
@ReemplazadoPor(
        referencia = Carrito.class,
        razon = "Se aplica el patron Strategy en el carrito, por lo que esta clase queda redundante." )
public class CarritoDescuentoPrimeraCompra extends Carrito {
    public double getPrecio() {
        return super.getPrecio() * 0.8;
    }
}
