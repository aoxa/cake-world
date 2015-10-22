package com.zuppelli.repository;

import com.zuppelli.cake.modelo.dominio.Piso;
import com.zuppelli.livingdocs.ReemplazadoPor;
import com.zuppelli.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Deprecated
@ReemplazadoPor(
        referencia = RepositorioGenerico.class,
        razon = "En vez de tener un repositorio por entidad, pase a uno generico." )
public class RepositorioPiso implements Repository<Piso, Long> {
    @Autowired
    private Storage storage;

    @Override
    public Piso store( Piso entity ) {
        return storage.store( Piso.class, entity );

    }

    @Override
    public void remove( Long identifier ) {
        this.storage.delete( Piso.class, identifier );
    }

    @Override
    public Piso retrieve( Long identifier ) {
        return this.storage.retrieve( Piso.class, identifier );
    }

    @Override
    public Collection<Piso> retrieve() {
        return this.storage.retrieve( Piso.class );
    }
}
