package com.zuppelli.repository;

import com.zuppelli.cake.modelo.dominio.Relleno;
import com.zuppelli.livingdocs.ReemplazadoPor;
import com.zuppelli.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Deprecated
@ReemplazadoPor( referencia = RepositorioGenerico.class, razon = "En vez de tener un repositorio por entidad, pase a uno generico." )
public class RepositorioRelleno implements Repository<Relleno, Long>
{
    @Autowired private Storage storage;

    @Override
    public Relleno store( Relleno entity )
    {
        return storage.store( Relleno.class, entity );

    }

    @Override
    public void remove( Long identifier )
    {
        this.storage.delete( Relleno.class, identifier );
    }

    @Override
    public Relleno retrieve( Long identifier )
    {
        return this.storage.retrieve( Relleno.class, identifier );
    }

    @Override
    public Collection<Relleno> retrieve()
    {
        return this.storage.retrieve( Relleno.class );
    }
}
