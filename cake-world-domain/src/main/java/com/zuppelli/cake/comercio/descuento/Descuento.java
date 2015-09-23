package com.zuppelli.cake.comercio.descuento;

import com.zuppelli.cake.livingdocs.ComportamientoCentral;
import com.zuppelli.cake.livingdocs.ConceptoCentral;
import com.zuppelli.cake.livingdocs.Strategy;

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
