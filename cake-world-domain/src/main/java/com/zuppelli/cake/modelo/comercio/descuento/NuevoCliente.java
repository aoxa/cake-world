package com.zuppelli.cake.modelo.comercio.descuento;

/**
 * Aplica una sola vez para todos los clientes, en la primer compra.
 */
public class NuevoCliente extends DescuentoAcumulable {

    @Override
    protected double aplicaPropio( double total ) {
        return total * 0.85;
    }
}
