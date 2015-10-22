package com.zuppelli.cake.modelo.comercio.descuento;

/**
 * Aplica una sola vez para todos los clientes, en la primer compra.
 */
public class NuevoCliente implements Descuento {
    @Override
    public double aplicar( double total ) {
        return total * 0.85;
    }
}
