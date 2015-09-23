package com.zuppelli.cake.comercio.estado;

/**
 * Se ha hecho un nuevo pedido, y aun no se empezo a trabajar en èl.
 */
public class Pedido implements EstadoPedido
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
