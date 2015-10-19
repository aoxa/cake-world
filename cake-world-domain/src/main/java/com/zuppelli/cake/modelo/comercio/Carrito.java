package com.zuppelli.cake.modelo.comercio;

import com.zuppelli.cake.modelo.comercio.descuento.Descuento;
import com.zuppelli.cake.modelo.dominio.Torta;
import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * El carrito de compra del usuario.
 */
@ConceptoCentral
@JsonIgnoreProperties(ignoreUnknown = true)
public class Carrito extends EntityComercio
{
    private final List<Torta> contenido;
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

    public void setContenido( List<Torta> contenido ) {
        this.contenido.addAll( contenido );
    }

    public void addContenido( Torta contenido ) {
        this.contenido.add( contenido );
    }

    public List<Torta> getContenido() {
        return contenido;
    }
}
