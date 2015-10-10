package com.zuppelli.service;

import com.zuppelli.cake.modelo.Cobertura;
import com.zuppelli.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ServicioCobertura implements Servicio<Cobertura>
{
    @Autowired private Repository<Cobertura, Long> repositorioCobertura;

    @Override
    public Cobertura get( Long id )
    {
        return repositorioCobertura.retrieve( id );
    }

    @Override
    public Collection<Cobertura> get()
    {
        return repositorioCobertura.retrieve();
    }

    @Override
    public Cobertura store( Cobertura entity )
    {
        return repositorioCobertura.store( entity );
    }

    @Override
    public void remove( Long id )
    {
        repositorioCobertura.remove( id );
    }
}
