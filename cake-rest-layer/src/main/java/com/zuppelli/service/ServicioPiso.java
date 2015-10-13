package com.zuppelli.service;

import com.zuppelli.cake.modelo.Piso;
import com.zuppelli.cake.modelo.Relleno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPiso extends ServicioGenerico<Piso>
{
    @Autowired private Servicio<Relleno> servicioRelleno;

    public ServicioPiso(  ) {
        super( Piso.class );
    }


    @Override
    public Piso store( Piso entity )
    {
        if ( null != entity.getRelleno() && null != entity.getRelleno().getId() ) {
            entity.setRelleno( servicioRelleno.get( entity.getRelleno().getId() ) );
        }
        return super.store( entity );
    }

}
