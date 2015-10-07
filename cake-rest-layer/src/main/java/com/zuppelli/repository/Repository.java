package com.zuppelli.repository;

/**

 */
public interface Repository<T, K> {
    T store(T entity);
    void remove(T entity);
    T retrieve(K identifier);
}
