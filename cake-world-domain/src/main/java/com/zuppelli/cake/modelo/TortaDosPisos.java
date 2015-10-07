package com.zuppelli.cake.modelo;

import com.zuppelli.livingdocs.ReemplazadoPor;

/**
 * Una torta con dos pisos.
 */
@Deprecated
@ReemplazadoPor( referencia = Torta.class, razon = "Se cambio herencia por composicion.")
public class TortaDosPisos extends Torta
{
}
