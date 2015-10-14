package com.zuppelli.repository;

import com.zuppelli.cake.modelo.dominio.Cobertura;
import com.zuppelli.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Deprecated
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
