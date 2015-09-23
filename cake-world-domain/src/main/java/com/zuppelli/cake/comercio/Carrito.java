package com.zuppelli.cake.comercio;

import com.zuppelli.cake.comercio.descuento.Descuento;
import com.zuppelli.cake.livingdocs.ComportamientoCentral;
import com.zuppelli.cake.livingdocs.ConceptoCentral;
import com.zuppelli.cake.modelo.Torta;

import java.util.ArrayList;
import java.util.List;

/**
 * El carro de compra del usuario.
 */
@ConceptoCentral
public class Carrito
{
    private Descuento descuento;
    private List<Torta> contenido;

    public Carrito()
    {
        descuento = null;
        contenido = new ArrayList<Torta>();
    }

    public void limpiar()
    {
        descuento = null;
        contenido.clear();
    }

    public void setDescuento( Descuento descuento )
    {
        this.descuento = descuento;
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
        return null != descuento ? descuento.aplicar( precio ) : precio;
    }

}
