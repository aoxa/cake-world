package com.zuppelli.cake.modelo.dominio;

import com.zuppelli.livingdocs.ConceptoCentral;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

/**
 * Ingrediente que va en el centro de un piso de una torta.
 */
@ConceptoCentral
@AutoProperty
public class Relleno extends EntityDominio {
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
        return this.precio;
    }

    public void setPrecio( Long precio ) {
        this.precio = precio;
    }

    @Override
    public boolean equals( Object other ) {
        return Pojomatic.equals( this, other );
    }

    @Override
    public int hashCode() {
        return Pojomatic.hashCode( this );
    }
}
