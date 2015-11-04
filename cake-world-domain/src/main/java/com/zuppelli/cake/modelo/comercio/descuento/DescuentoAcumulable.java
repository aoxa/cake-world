package com.zuppelli.cake.modelo.comercio.descuento;

import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;

/**
 * Estos descuentos se pueden acumular. Se agrega un nuevo elemento descuento que se aplicará agregado
 * sobre el que ya se aplicó.
 */
@ConceptoCentral
public abstract class DescuentoAcumulable implements Descuento {
    private Descuento descuento;

    /**
     * El descuento que se aplicará ademas del descuento actual.
     * @param descuento
     */
    @ComportamientoCentral
    public void setDescuento( Descuento descuento ) {
        this.descuento = descuento;
    }

    @Override
    public double aplicar( double total ) {
        double descontado = aplicaPropio( total );
        if ( null == descuento ) {
            return  descontado;
        } else {
            return descontado - (total - descuento.aplicar( total ));
        }
    }

    /**
     * Es requerido que se implemente por la subclase para aplicar el descuento propio.
     * @param total - El total a pagar
     * @return - El total descontado.
     */
    @ComportamientoCentral
    protected abstract double aplicaPropio( double total );
}
