package com.zuppelli.cake.comercio.descuento;

import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;
import com.zuppelli.livingdocs.Strategy;

/**
 * Modela los tipos de descuentos que se pueden aplicar.
 */
@Strategy
@ConceptoCentral
public interface Descuento
{
    /**
     * Aplica el descuento al precio.
     * @param total - el precio sin el descuento.
     * @return - El precio con el descuento.
     */
    @ComportamientoCentral
    double aplicar( double total );
}
