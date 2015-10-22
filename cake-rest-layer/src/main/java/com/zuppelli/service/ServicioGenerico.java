package com.zuppelli.service;

import com.zuppelli.cake.modelo.Entity;
import com.zuppelli.repository.RepositorioGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public abstract class ServicioGenerico<T extends Entity> implements Servicio<T> {
    @Autowired
    protected RepositorioGenerico<T> repositorioGenerico;

    protected final Class<T> current;

    protected ServicioGenerico( Class<T> current ) {
        this.current = current;
    }

    @Override
    public T get( Long id ) {
        return repositorioGenerico.retrieve( id, current );
    }

    @Override
    public Collection<T> get() {
        return repositorioGenerico.retrieve( current );
    }

    @Override
    public T store( T entity ) {
        return repositorioGenerico.store( entity );
    }

    @Override
    public void remove( Long id ) {
        repositorioGenerico.remove( id, current );
    }

}
