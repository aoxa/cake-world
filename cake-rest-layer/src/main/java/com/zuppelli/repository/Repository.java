package com.zuppelli.repository;

import com.zuppelli.livingdocs.ReemplazadoPor;

import java.util.Collection;

@Deprecated
@ReemplazadoPor(
        referencia = Repositorio.class,
        razon = "En vez de tener un repositorio por entidad, pase a uno generico." )
public interface Repository<T, K> {
    T store( T entity );

    void remove( K identifier );

    T retrieve( K identifier );

    Collection<T> retrieve();
}
