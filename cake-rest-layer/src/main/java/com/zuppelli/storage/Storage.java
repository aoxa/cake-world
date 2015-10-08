package com.zuppelli.storage;

import com.zuppelli.cake.modelo.Entity;

/**
 * Created by pedro.zuppelli on 07/10/2015.
 */
public interface Storage {
    <T, K>void store(Class<T> clazz, Entity<K> entity, K id );
    <T, K>T retrieve(Class<T> clazz, K id);
    <T, K> void delete(Class<T> clazz, Entity<K> entity);
}