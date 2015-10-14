package com.zuppelli.repository;

import com.zuppelli.cake.modelo.dominio.Cobertura;
import com.zuppelli.livingdocs.ReemplazadoPor;
import com.zuppelli.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Deprecated
@ReemplazadoPor( referencia = RepositorioGenerico.class, razon = "En vez de tener un repositorio por entidad, pase a uno generico." )
public class RepositorioCobertura implements Repository<Cobertura, Long>
{
    @Autowired private Storage storage;

    @Override
    public Cobertura store( Cobertura entity )
    {
        return storage.store( Cobertura.class, entity );
    }

    @Override
    public void remove( Long identifier )
    {
        storage.delete( Cobertura.class, identifier );
    }

    @Override
    public Cobertura retrieve( Long identifier )
    {
        return storage.retrieve( Cobertura.class, identifier );
    }

    @Override
    public Collection<Cobertura> retrieve()
    {
        return storage.retrieve( Cobertura.class );
    }
}
