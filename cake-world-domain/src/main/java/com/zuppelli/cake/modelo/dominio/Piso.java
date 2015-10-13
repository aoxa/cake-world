package com.zuppelli.cake.modelo.dominio;

import com.zuppelli.cake.config.ConfigHelper;
import com.zuppelli.cake.modelo.Entity;
import com.zuppelli.livingdocs.ComportamientoCentral;
import com.zuppelli.livingdocs.ConceptoCentral;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Seccion horizontal de una torta.
 */
@ConceptoCentral
@JsonIgnoreProperties(ignoreUnknown = true)
public class Piso extends Entity
{
    private Relleno relleno;
    private String masa;
    private double peso;

    /**
     * Se calcula mediante el peso de la torta y el costo por kilo.
     *
     * @return - El precio total del piso.
     */
    @ComportamientoCentral
    public double getPrecio()
    {
        double precio = peso * getPrecioPorKilo();
        if( null != relleno ) {
            precio += relleno.getPrecio();
        }
        return precio;
    }

    private Double getPrecioPorKilo() {
        return ConfigHelper.getInstance().get( ConfigHelper.Keys.PRECIO_POR_KILO );
    }

    public Relleno getRelleno()
    {
        return relleno;
    }

    public void setRelleno( Relleno relleno )
    {
        this.relleno = relleno;
    }

    public String getMasa()
    {
        return masa;
    }

    public void setMasa( String masa )
    {
        this.masa = masa;
    }

    public void setPeso( double peso ) {
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }
}