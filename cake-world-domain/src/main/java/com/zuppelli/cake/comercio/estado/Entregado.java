package com.zuppelli.cake.comercio.estado;

/**
 * El pedido fue completado y entregado.
 */
public class Entregado implements EstadoPedido
{
    @Override
    public EstadoPedido proximo()
    {
        return null;
    }

    @Override
    public EstadoPedido ejecutar()
    {
        return null;
    }
}
