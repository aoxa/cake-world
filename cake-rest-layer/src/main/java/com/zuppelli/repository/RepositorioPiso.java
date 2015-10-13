package com.zuppelli.repository;

import com.zuppelli.cake.modelo.Piso;
import com.zuppelli.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Deprecated
public class RepositorioPiso implements Repository<Piso, Long>
{
    @Autowired private Storage storage;

    @Override
    public Piso store( Piso entity )
    {
        return storage.store( Piso.class, entity );

    }

    @Override
    public void remove( Long identifier )
    {
        this.storage.delete( Piso.class, identifier );
    }

    @Override
    public Piso retrieve( Long identifier )
    {
        return this.storage.retrieve( Piso.class, identifier );
    }

    @Override
    public Collection<Piso> retrieve()
    {
        return this.storage.retrieve( Piso.class );
    }
}