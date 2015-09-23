package com.zuppelli.cake.comercio.estado;

/**
 * El pedido fue completado y esta listo para ser entregado o retirado.
 */
public class ListoParaEntrega implements EstadoPedido
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
