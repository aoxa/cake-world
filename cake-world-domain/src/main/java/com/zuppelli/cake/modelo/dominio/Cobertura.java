package com.zuppelli.cake.modelo.dominio;

import com.zuppelli.livingdocs.ConceptoCentral;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

/**
 * Lamina de distintos tipos de ingredientes que cubre una torta.
 */
@ConceptoCentral
@AutoProperty
public class Cobertura extends EntityDominio {
    private String tipo;

    @JsonIgnore
    private Long precio;

    public String getTipo() {
        return tipo;
    }

    public void setTipo( String tipo ) {
        this.tipo = tipo;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio( Long precio ) {
        this.precio = precio;
    }

    @Override
    public boolean equals( Object o ) {
        return Pojomatic.equals( this, o );
    }

    @Override
    public int hashCode() {
        return Pojomatic.hashCode( this );
    }

}
