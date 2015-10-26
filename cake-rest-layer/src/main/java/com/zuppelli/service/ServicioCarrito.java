package com.zuppelli.service;

import com.zuppelli.cake.modelo.comercio.Carrito;
import com.zuppelli.cake.modelo.comercio.descuento.Descuento;
import com.zuppelli.cake.modelo.comercio.descuento.DescuentoAcumulable;
import com.zuppelli.cake.modelo.comercio.descuento.NuevoCliente;
import com.zuppelli.cake.modelo.comercio.descuento.PorCantidad;
import com.zuppelli.cake.modelo.dominio.Piso;
import com.zuppelli.cake.modelo.dominio.Torta;
import org.springframework.stereotype.Service;

@Service
public class ServicioCarrito extends ServicioGenerico<Carrito> {
    public ServicioCarrito() {
        super(Carrito.class);
    }

    public Carrito get( Long id ){
        Carrito carrito = super.get( id );
        Descuento descuento = null;
        if ( null != carrito && get().size() == 1 ) {
            descuento = new NuevoCliente();
        }

        int peso = 0;
        for( Torta torta : carrito.getContenido() ) {
            peso += torta.getBase().getPeso();
            for ( Piso piso : torta.getPisos() ) {
                peso += piso.getPeso();
            }
        }

        if( peso > 7 ) {
            if( descuento instanceof DescuentoAcumulable ) {
                ( (DescuentoAcumulable) descuento ).setDescuento( new PorCantidad() );
            } else {
                descuento = new PorCantidad();
            }
        }

        carrito.setDescuento( descuento );

        return carrito;
    }
}
