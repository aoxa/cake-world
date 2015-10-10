package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ConceptoCentral;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Ingrediente que va en el centro de un piso de una torta.
 */
@ConceptoCentral
public class Relleno extends Entity
{
    private String tipo;

    @JsonIgnore
    private Long precio;

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo( String tipo )
    {
        this.tipo = tipo;
    }

    public Long getPrecio() {
        return this.precio;
    }

    public void setPrecio( Long precio ) {
        this.precio = precio;
    }
}
