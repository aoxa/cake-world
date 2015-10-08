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
public class Torta implements Entity<Long>
{
    private final List<Piso> pisos = new ArrayList<Piso>(  );
    private Cobertura cobertura;
    private Long id;

    /**
     * Precio total de la torta.
     * @return el precio.
     */
    @ComportamientoCentral
    public double getPrecio( )
    {
        return 0;
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

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }
}
