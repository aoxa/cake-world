package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Masa de harina, con otros ingredientes, de forma redonda, que se cuece a fuego lento.
 */
@ConceptoCentral
@JsonIgnoreProperties(ignoreUnknown = true)
public class Torta extends Entity
{
    private final List<Piso> pisos = new ArrayList<Piso>(  );
    private Piso base;
    private Cobertura cobertura;

    /**
     * Precio total de la torta.
     * @return el precio.
     */
    @ComportamientoCentral
    public double getPrecio( )
    {
        double precio = base.getPrecio();

        for( Piso piso : pisos ) {
            precio += piso.getPrecio();
        }

        if( null != cobertura ) {
            precio += cobertura.getPrecio();
        }

        return precio;
    }

    public Cobertura getCobertura()
    {
        return cobertura;
    }

    public void setCobertura( Cobertura cobertura )
    {
        this.cobertura = cobertura;
    }

    public List<Piso> getPisos()
    {
        return pisos;
    }

    public void agregarPiso( Piso ... piso )
    {
        this.pisos.addAll( Arrays.asList(piso) ) ;
    }

    public Piso getBase()
    {
        return base;
    }

    public void setBase( Piso base )
    {
        this.base = base;
    }
}
