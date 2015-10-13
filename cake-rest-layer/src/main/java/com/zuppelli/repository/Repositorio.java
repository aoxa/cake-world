package com.zuppelli.repository;

import com.zuppelli.cake.modelo.Entity;

import java.util.Collection;

/**
 * Created by pedro.zuppelli on 13/10/2015.
 */
public interface Repositorio<T extends Entity> {
    T store( T entity );

    void remove( Long identifier, Class<T> clazz );

    T retrieve( Long identifier, Class<T> clazz );

    Collection<T> retrieve( Class<T> clazz );
}
