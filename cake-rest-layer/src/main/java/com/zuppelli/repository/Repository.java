package com.zuppelli.repository;

import java.util.Collection;

/**

 */
@Deprecated
public interface Repository<T, K> {
    T store(T entity);
    void remove(K identifier);
    T retrieve(K identifier);
    Collection<T> retrieve();
}
