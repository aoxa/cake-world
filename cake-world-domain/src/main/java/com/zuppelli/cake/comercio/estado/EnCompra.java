package com.zuppelli.cake.comercio.estado;

/**
 * El usuario esta en fase de compra.
 */
public class EnCompra implements EstadoPedido
{
    @Override
    public EstadoPedido proximo()
    {
        //TODO: agregar logica
        return null;
    }

    @Override
    public EstadoPedido ejecutar()
    {
        return null;
    }
}
