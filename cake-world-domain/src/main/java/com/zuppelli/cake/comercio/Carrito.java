package com.zuppelli.cake.comercio;

import com.zuppelli.cake.comercio.descuento.Descuento;
import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;

import java.util.ArrayList;
import java.util.List;

/**
 * El carrito de compra del usuario.
 */
@ConceptoCentral
public class Carrito
{
    private List<Torta> contenido;
    private Descuento descuento;

    public Carrito()
    {
        contenido = new ArrayList<Torta>();
    }

    public void limpiar()
    {
        contenido.clear();
        descuento = null;
    }


    /**
     * Calcula el precio del carrito, aplicando descuentos.
     */
    @ComportamientoCentral
    public double getPrecio() {
        double precio = 0;
        for( Torta torta : contenido )
        {
            precio += torta.getPrecio();
        }
        return null == descuento ? precio : descuento.aplicar( precio );
    }

    public void setDescuento( Descuento descuento )
    {
        this.descuento = descuento;
    }
}
