package com.zuppelli.cake.modelo.dominio;

import com.zuppelli.cake.modelo.Entity;
import com.zuppelli.livingdocs.ConceptoCentral;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Lamina de distintos tipos de ingredientes que cubre una torta.
 */
@ConceptoCentral
public class Cobertura extends Entity
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

    public Long getPrecio()
    {
        return precio;
    }

    public void setPrecio( Long precio )
    {
        this.precio = precio;
    }
}
