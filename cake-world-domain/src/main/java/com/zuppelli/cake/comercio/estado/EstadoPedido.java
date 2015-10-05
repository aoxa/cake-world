package com.zuppelli.cake.comercio.estado;

import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;
import com.zuppelli.livingdocs.State;

/**
 * Maquina de estados que indica el estado del pedido actual.
 */
@State
@ConceptoCentral
public interface EstadoPedido
{
    EstadoPedido proximo();

    /**
     * Ejecuta el estado actual.
     * @return retorna el proximo estado a ejecutar.
     */
    @ComportamientoCentral
    EstadoPedido ejecutar();
}
