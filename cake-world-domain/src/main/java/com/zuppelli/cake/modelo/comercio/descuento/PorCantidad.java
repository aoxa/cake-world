package com.zuppelli.cake.modelo.comercio.descuento;

/**
 * Aplica cuando la compra supera un minimo establecido.
 */
public class PorCantidad implements Descuento {
    @Override
    public double aplicar( double total ) {
        return total * 0.9;
    }
}
