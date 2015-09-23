package com.zuppelli.cake.comercio.estado;

/**
 * El pedido se encuentra en desarrollo.
 */
public class EnDesarrollo implements EstadoPedido
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
