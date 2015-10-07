package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ReemplazadoPor;

/**
 * Una torta con cobertura.
 */
@Deprecated
@ReemplazadoPor( referencia = Torta.class, razon = "Se cambio herencia por composicion.")
public class TortaConCobertura extends Torta
{
    private String tipoCobetura;

    public void setTipoCobetura( String tipoCobetura ) {
        this.tipoCobetura = tipoCobetura;
    }
    public String getTipoCobetura() {
        return tipoCobetura;
    }
}
