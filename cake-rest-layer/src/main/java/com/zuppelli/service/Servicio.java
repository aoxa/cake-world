package com.zuppelli.service;

import java.util.Collection;

public interface Servicio<T>
{
    T get( Long id );

    Collection<T> get();

    T store( T entity );

    void remove( Long id );
}
