package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;

import java.util.Arrays;
import java.util.List;

@ConceptoCentral
public class Torta
{
    private List<Piso> pisos;
    private Cobertura cobertura;

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
}
