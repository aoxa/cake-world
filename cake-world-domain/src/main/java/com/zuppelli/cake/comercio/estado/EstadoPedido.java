package com.zuppelli.cake.comercio.estado;

import com.zuppelli.cake.livingdocs.ComportamientoCentral;
import com.zuppelli.cake.livingdocs.ConceptoCentral;
import com.zuppelli.cake.livingdocs.State;

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
