package com.zuppelli.service;

import com.zuppelli.cake.modelo.Piso;
import com.zuppelli.cake.modelo.Relleno;
import com.zuppelli.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ServicioPiso implements Servicio<Piso>
{
    @Autowired private Repository<Piso, Long> repositorioPiso;
    @Autowired private Servicio<Relleno> servicioRelleno;

    @Override
    public Piso get( Long id )
    {
        return repositorioPiso.retrieve( id );
    }

    @Override
    public Collection<Piso> get()
    {
        return repositorioPiso.retrieve();
    }

    @Override
    public Piso store( Piso entity )
    {
        if ( null != entity.getRelleno() && null != entity.getRelleno().getId() ) {
            entity.setRelleno( servicioRelleno.get( entity.getRelleno().getId() ) );
        }
        return repositorioPiso.store( entity );
    }

    @Override
    public void remove( Long id )
    {
        this.repositorioPiso.remove( id );
    }
}
