package com.zuppelli.repository;

import com.zuppelli.cake.modelo.Entity;
import com.zuppelli.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class RepositorioGenerico<T extends Entity> implements Repositorio<T> {
    @Autowired
    private Storage storage;

    @Override
    public T store( T entity ) {
        return ( T ) storage.store(
                entity.getClass(),
                entity );

    }

    @Override
    public void remove( Long identifier, Class<T> clazz ) {
        this.storage.delete( clazz, identifier );
    }

    @Override
    public T retrieve( Long identifier, Class<T> clazz ) {
        return ( T ) this.storage.retrieve( clazz, identifier );
    }

    @Override
    public Collection<T> retrieve( Class<T> clazz ) {
        return ( Collection<T> ) this.storage.retrieve( clazz );
    }
}
