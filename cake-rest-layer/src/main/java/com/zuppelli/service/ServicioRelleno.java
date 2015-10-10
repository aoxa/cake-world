package com.zuppelli.service;

import com.zuppelli.cake.modelo.Relleno;
import com.zuppelli.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ServicioRelleno implements Servicio<Relleno>
{
    @Autowired private Repository<Relleno, Long> repositorioRelleno;

    @Override
    public Relleno get( Long id )
    {
        return repositorioRelleno.retrieve( id );
    }

    @Override
    public Collection<Relleno> get()
    {
        return repositorioRelleno.retrieve();
    }

    @Override
    public Relleno store( Relleno entity )
    {
        return repositorioRelleno.store( entity );
    }

    @Override
    public void remove( Long id )
    {
        repositorioRelleno.remove( id );
    }
}
